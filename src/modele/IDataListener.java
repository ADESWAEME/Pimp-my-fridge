package modele;

import modele.Data;

public interface IDataListener {

	/**
	 * Quand une nouvelle donn�e est lue.
	 */
	public void onNewDataRead(Data data);

	/**
	 * Quand l'�tat d'allumage du r�frig�rateur a chang�.
	 */
	public void onPowerStatusChanged(boolean powerOn);
	
}
