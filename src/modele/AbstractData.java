package modele;

import java.util.ArrayList;
import java.util.Date;
import java.util.function.Consumer;

import modele.Data;

public class AbstractData implements IData{

	/** Les listeners de la source de données */
	private static ArrayList<IDataListener> listeners;
	
	/** Etat d'activation de l'alimentation du réfrigérateur */
	protected boolean powerEnabled = false;
	
	// Temps en secondes
	private long tempsAllumage = 0;
	private Date powerOnTime;
	
	public AbstractData() {
		listeners = new ArrayList<IDataListener>();
	}
	
	@Override
	public void addListener(IDataListener obs) {
		listeners.add(obs);
	}
	
	@Override
	public void removeListener(IDataListener obs) {
		listeners.remove(obs);
	}

	public  void notifyListeners(final Data data) {
		listeners.forEach(new Consumer<IDataListener>() {
			public void accept(IDataListener observer) {
				observer.onNewDataRead(data);
			}
		});
	}
	
	@Override
	public void notifyListeners(final boolean powerOn) {
		// On incrémente le temps d'allumage du frigo
		if (powerOn) {
			this.powerOnTime = new Date();
		}
		else {
			this.tempsAllumage = getPowerUptime();
			this.powerOnTime = null;
		}
		listeners.forEach(new Consumer<IDataListener>() {
			public void accept(IDataListener observer) {
				observer.onPowerStatusChanged(powerOn);
			}
		});
	}

	protected void sleep(long duration) {
		try {
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			System.exit(-2);
		}
	}
	
	@Override
	public boolean isPowerEnabled() {
		return this.powerEnabled;
	}
	
	/**
	 * @return En secondes
	 */
	public long getPowerUptime() {
		long time = tempsAllumage;
		if (powerOnTime != null) {
			time += (new Date().getTime() - powerOnTime.getTime()) / 1000;
		}
		return time;
	}

	@Override
	public void init() throws Throwable {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPowerEnabled(boolean value) {
		// TODO Auto-generated method stub
		
	}
	
}
