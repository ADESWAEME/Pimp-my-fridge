package modele;

public class Data {

	private double humidity;
	private double tempIn;
	private double tempRess;
	

	/**
	 * @param humidity En pourcentage d'humidit�.
	 * @param tempIn   La temp�rature int�rieure.
	 * @param diffTemp  Diff�rence de temp�rature.
	 */
	public Data( double tempIn, double tempRess,double humidity) {
		this.humidity = humidity;
		this.tempIn = tempIn;
		this.tempRess = tempRess;
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
		return String.format("Tin=%s�C TempRess=%s�C H=%s%%", tempIn,tempRess, humidity);
	}



}
