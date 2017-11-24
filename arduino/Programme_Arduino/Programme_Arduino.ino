//Ajout des différentes librairies que l'on va utiliser
#include <math.h> //Pour les calculs
#include "Adafruit_Sensor.h" //Exploitation des capteurs
#include "DHT.h" //Communication avec l'hygromètre


#define DHTPIN 2 //Définition du PIN de branchement de l'hygromètre
#define DHTTYPE DHT11 //Définition du type d'hygromètre
#define PIN_NTC 0 //PIN de la thermistance

double Rref = 10000.0; //Résistance de référence à 25°C selon les données du constructeur
double V_IN = 5.0; //Alimentation électrique

//Données de la thermistance calculées à partir de l'excel fourni
double A_1 = 0.00109613;
double B_1 = 0.00024016;
double C_1 = 0;
double D_1 = 5.8743E-8;

//Consigne de température demandée
double order = 18;

//Marche du ventilateur
boolean power = true;
boolean frooze = false;

//Calcul de la relation SteinhartHart
double SteinhartHart(double R)
{
  /*
   * L'équation a été divisée en plusieurs partie. Elle suis le modèle suivant :
   * 
   * 1
   * - = A + B*ln(R) + C*(ln(R)^2) + D*(ln(R)^3)
   * T
   * 
   * Les valeurs de A à D sont définies au dessus.
   * 
   * Il n'y a pas besoin d'écrire l'équation de A car A est une variable seule.
   */

  double equationB1 = B_1 * log(R/Rref); //Définition de l'équation de B
  double equationC1 = C_1 * pow(log(R/Rref), 2); //Définition de l'équation de C (qui est égale à 0)
  double equationD1 = D_1 * pow(log(R/Rref), 3); //Définition de l'équation de D
  double equation = A_1 + equationB1 + equationC1 + equationD1; //Réalisation de l'équation 1/T
  return pow(equation, -1); //Exposant -1 sur l'équation pour obtenir la valeur de T

}


DHT dht(DHTPIN, DHTTYPE); //Déclaration de l'Hygromètre

void setup() {
  pinMode(12,OUTPUT); //Définition du PIN 12 en sortie
  Serial.begin(9600); //Ouvre le port série et fixe le debit de communication à 9600 bauds
  dht.begin(); //Lancement du fonctionnement de l'hygromètre

}

/*
 * Définition :
 * 
 * Le baud est une unité de mesure utilisée dans le domaine des télécommunications en général, 
 * et dans le domaine informatique en particulier. 
 * Le baud est l'unité de mesure du nombre de symboles transmissibles par seconde.
 */

void loop() {

  //Cette partie a pour but de calculer la tension sur la borne analogique
  double valeurAnalog = analogRead(PIN_NTC); //Ici on lit la valeur de la tension présente sur le PIN de la thermistance
  double V =  valeurAnalog / 1024 * V_IN; //Calcul de la tension présente sur la borne analogique - V_IN = 5V

  /*
   * Explication du calcul au dessus
   * 
   *          U(PIN)
   * U(BAna)= ------
   *          1024*5
   * 
   * U(BAna) est la tension sur la borne analogique
   * U(PIN) est la valeur de la tension sur le PIN
   */

  //Calcul de la résistance de la thermistance
  double Rth = (Rref * V ) / (V_IN - V);

  /*
   * Démonstration de la formule ci-dessus
   * 
   *     Rth * V_IN
   * V = ----------
   *     Rref * Rth
   *     
   * <=> V * (Rref + Rth) = Rth * V_IN
   * <=> VRref + VRth = Rth * V_IN
   * <=> VRref = V_IN * Rth - VRth
   * <=> VRref = Rth * (V_IN - V)
   *           V * Rref
   * <=> Rth = --------
   *           V_IN - V
   * 
   */
  
  //Calcul de la température en kelvin
  double kelvin = SteinhartHart(Rth); //Calcul de SteinhartHart avec la valeur de la résistance trouvée au calcul précédent
  double celsius = kelvin - 273.15; //Conversion en celsius

  /*
   * Lire la température ou l'humidité prend 250 millisecondes !
   * En revanche, les capteurs peuvent mettre jusqu'à 2 secondes pour en faire la lecture
   * (les capteurs que l'on a sont lents)
   */

  // Lecture du taux d'humidité
  float h = dht.readHumidity();
  
  // Lecture de la température en Celcius
  float t = dht.readTemperature();
  
  // Pour lire la température en Fahrenheit
  float f = dht.readTemperature(true);

  /*
   * Partie pour le test sans le java
   * 
   *   // Stop le programme et renvoie un message d'erreur si le capteur ne renvoie aucune mesure
   *   if (isnan(h) || isnan(t) || isnan(f)) {
   *   Serial.println("Echec de lecture !");
   *   return;
   *   }
   * 
   */

 
  // Calcul la température ressentie. Il calcul est effectué à partir de la température en Fahrenheit
  // On fait la conversion en Celcius dans la foulée
  float hi = dht.computeHeatIndex(f, h);
  
  // Le calcul de la chaleur se fait en Fahrenheit (unité par défaut)
  float hif = dht.computeHeatIndex(f, h);

  // Calcul de la chaleur en Celsius (siFahreheit = false)
  float hic = dht.computeHeatIndex(t, h, false);

  float temperature = t;
  float humidite = h/100;

  //Calcul de la rosée (magnus)
  float alpha = ((17.27 * temperature)/(237.7 + temperature)) + log(humidite);
  float rosee = (237.7 * alpha) / (17.27 - alpha);

  // Effet Peltier en fonction des deg
  float difference = celsius - order; //Calcul de la différence entre la valeur réel et celle demandée

  if (power == true) //Si le frigo fonctionne
  {
    if(difference > 2) //S'il y a une différence de 2 deg
    {
      frooze = true; //Activer l'effet Peltier
      digitalWrite(12, frooze); //Mise à jour du niveau logique
    } 
    
    else if (difference < -2) //Si la diff est de -2 deg
    {
      frooze = false; //Arrêter le refroidissement
      digitalWrite(12, frooze); //Mise à jour du niveau logique
    }
  } 
  
  else //Si le frigo est etteint
  {
    frooze = false; //Arrêter le refroidissement
    digitalWrite(12, frooze); //Màj du niveau logique
  }


  //Programme sans java
  //Serial.print("Humidite: "); 

  Serial.print(h);
  Serial.print(";");
  //Serial.print(" %\t ");
  //Serial.print("Temperature: "); 
  Serial.print(t);
  Serial.print(";");
  //Serial.print(" *C ");
  //Serial.print("Temperature ressentie: ");
  Serial.print(dht.convertFtoC(hi));
  Serial.print(";");
  //Serial.println(" *C ");
  //Serial.print("Rosee: ");
  Serial.print(rosee);
  Serial.print(";");
  //Serial.print("Difference de temp: ");
  Serial.println(difference);
  Serial.print(";");
  Serial.print(consigne);
  Serial.print(";");
  

  //Serial.print(h,t,dht.convertFtoC(hi),rosee,difference);
  
  delay(5000); //Pause d'1 sec puis reprise du programme
  
}
