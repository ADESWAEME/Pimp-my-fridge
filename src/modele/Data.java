package modele;

public class Data {

	private double humidity;
	private double tempIn;
	private double tempRess;
	private double cons;
	private double peltier;
	

	/**
	 * @param humidity En pourcentage d'humidité.
	 * @param tempIn   La température intérieure.
	 * @param diffTemp  Différence de température.
	 */
	public Data() {
	}
	
//	public void test (){
//		System.out.printf("humi : ",getHumidityRate());

	
	/**
	 * @return the h
	 */
	public void setHumidityRate(double h) {
		humidity = h;
		
	}

	/**
	 * @return the inte
	 */
	public void setInteriorTemperature(double tin) {
		tempIn = tin;
	}
	
	/**
	 * @return the tempRessenti
	 */
	public void setTemperatureRessenti(double temRess) {
		tempRess = temRess;
	}
	
	
	/**
	 * @return the tempRessenti
	 */
	public void setConsigne(double consi) {
		cons = consi;
	}
	
	
	
	/**
	 * @return the tempRessenti
	 */
	public void setPeltier(double pelt) {
		peltier = pelt;
	}
	
	/**
	 * @return the h
	 */
	public double getConsigne() {
		return cons;
	}


	/**
	 * @return the h
	 */
	public double getPeltier() {
		return peltier;
	}

	
	
	/**
	 * @return the h
	 */
	public double getHumidityRate() {
		return humidity;
	}

	/**
	 * @return the inte
	 */
	public double getInteriorTemperature() {
		return tempIn;
	}
	
	/**
	 * @return the tempRessenti
	 */
	public double getTemperatureRessenti() {
		return tempRess;
	}
	
	
	
	@Override
	public String toString() {
		return String.format("Tin=%s°C TempRess=%s°C H=%s%%", tempIn,tempRess, humidity);
	}





}
