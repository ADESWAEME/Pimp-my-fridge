package modele;

import modele.Data;

public interface IDataListener {

	/**
	 * Quand une nouvelle donnée est lue.
	 */
	public void onNewDataRead(Data data);

	/**
	 * Quand l'état d'allumage du réfrigérateur a changé.
	 */
	public void onPowerStatusChanged(boolean powerOn);
	
}
