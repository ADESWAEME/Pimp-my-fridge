#include "DHT.h"   // Librairie des capteurs DHT
 
#define DHTPIN 2    // Changer le pin sur lequel est branché le DHT
 
#define DHTTYPE DHT11     // DHT 11 
//#define DHTTYPE DHT22      // DHT 22  (AM2302)
//#define DHTTYPE DHT21     // DHT 21 (AM2301)
 
// Initialisation du capteur pour un Arduino à 16mhz par défaut
// Il faudra modifier le 3ème paramètres pour une autre carte (sinon le capteur renvoie 0). Quelques valeurs : 8mhz => 3, 16mhz => 6, 84mhz => 84
 
DHT dht(DHTPIN, DHTTYPE); 

int peltier = 3; //The N-Channel MOSFET is on digital pin 3
int power = 0; //Power level fro 0 to 99%
int peltier_level = map(power, 0, 40, 0, 255); //This is a value from 0 to 255 that actually controls the MOSFET

void setup() {
  Serial.begin(9600); 
  pinMode(peltier, OUTPUT); 
  dht.begin();
}



void loop() {
  // Délai d'1 seconde entre chaque mesure. La lecture prend 250 millisecondes
  delay(1000);
 
  // Lecture du taux d'humidité
  float h = dht.readHumidity();
  
  // Lecture de la température en Celcius
  float t = dht.readTemperature();
  
  // Pour lire la température en Fahrenheit
  float f = dht.readTemperature(true);
  
  // Stop le programme et renvoie un message d'erreur si le capteur ne renvoie aucune mesure
  if (isnan(h) || isnan(t) || isnan(f)) {
    Serial.println("Echec de lecture !");
    return;
  }
 
  // Calcul la température ressentie. Le calcul est effectué à partir de la température en Fahrenheit
  // On fait la conversion en Celcius dans la foulée
  float hi = dht.computeHeatIndex(f, h);


  if (Serial.available() > 0) {
    
    //Condition si l'humidité est supérieure à 60% on active le module peltier
    if(h == 60) {
      Serial.print("Ouvrez le réfrigérateur !");
      digitalWrite(peltier, LOW);
    }

    //Condition si la température est supérieure à 18°C on indique que le frigo doit être ouvert
    if (t == 22) {
      digitalWrite(peltier, HIGH);
      return;
    }

    //Condition si la température est inférieure à 14°C on active le module peltier
    if (t == 14) {
      power = 0;
      Serial.print("Ouvrez le réfrigérateur !");
      digitalWrite(peltier, LOW);
    }

    peltier_level = map(power, 0, 40, 0, 255);

  }
  
    
 
  Serial.print("Humidite : "); 
  Serial.print(h);
  Serial.print(" %\t");
  Serial.print("Temperature : "); 
  Serial.print(t);
  Serial.print(" °C\t");
  Serial.print("Temperature ressentie : ");
  Serial.print(dht.convertFtoC(hi));
  Serial.println(" °C");
  Serial.print("Power=");
  Serial.print(power);
  Serial.print(" PLevel=");
  Serial.println(peltier_level);

  analogWrite(peltier, peltier_level); //Ecrire cette nouvelle valeur sur le port

}

