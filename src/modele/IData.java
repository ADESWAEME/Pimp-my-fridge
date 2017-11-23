package modele;
import modele.Data;

public interface IData {
	/**
	 * Initialise le système.
	 * 
	 * @throws Exception Si le système ne peut être intialisé.
	 */
	public void init() throws Throwable;
	
	/**
	 * Démarrer le système.
	 */
	public void start();
	
	/**
	 * Arrêter le système.
	 */
	public void stop();
	
	/**
	 * Ajouter un observateur.
	 */
	public void addListener(IDataListener obs);
	
	/**
	 * Retirer un observateur.
	 */
	public void removeListener(IDataListener obs);
	
	/**
	 * Notifier les observateurs qu'une nouvelle donnée a été lue.
	 */
	public void notifyListeners(Data data);
	
	/**
	 * Notifier les observateurs quand l'état d'allumage du réfrigérateur est modifié.
	 */
	public void notifyListeners(boolean powerOn);
	
	/**
	 * Activer ou désactiver l'alimentation électrique du réfrigérateur.
	 */
	public void setPowerEnabled(boolean value);
	
	/**
	 * Renvoie TRUE si l'alimentation électrique est activée.
	 */
	public boolean isPowerEnabled();

	/**
	 * Temps d'allumage depuis le début.
	 */
	public long getPowerUptime();
}
