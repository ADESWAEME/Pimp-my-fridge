package modele;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import gnu.io.CommPortIdentifier; 
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent; 
import gnu.io.SerialPortEventListener; 
import java.util.Enumeration;



public class SerialTest  implements SerialPortEventListener {
	SerialPort serialPort;
        /** The port we're normally going to use. */
	private static final String PORT_NAMES = "COM3"; // Windows
	/**
	* A BufferedReader which will be fed by a InputStreamReader 
	* converting the bytes into characters 
	* making the displayed results codepage independent
	*/
	private BufferedReader input;
	/** The output stream to the port */
	private OutputStream output;
	/** Milliseconds to block while waiting for port open */
	private static final int TIME_OUT = 2000;
	/** Default bits per second for COM port. */
	private static final int DATA_RATE = 9600;
	/** Identifiant des trames de donn�es */
//	private static final String DataIdentifier = "D:";
	/** Identifiant des trames de feedback */
//	private static final String FeedbackIdentifier = "R:";

	/** Message d'erreur en cas de buffer vide */
//	private static final String EmptyBufferErrorMessage = "Underlying input stream returned zero bytes";
	private Data data;
	
	float h;
	float tin;
	float tempRess;
	float consigne;
	
	public SerialTest( Data d) {
		this.data = d;
	}
	
	public void initialize() {
                // the next line is for Raspberry Pi and 
                // gets us into the while loop and was suggested here was suggested http://www.raspberrypi.org/phpBB3/viewtopic.php?f=81&t=32186
                System.setProperty("gnu.io.rxtx.properties", PORT_NAMES);

		CommPortIdentifier portId = null;
		
		Enumeration<?> portEnum = CommPortIdentifier.getPortIdentifiers();

		//First, Find an instance of serial port as set in PORT_NAMES.
		while (portEnum.hasMoreElements()) {
			CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
				if (currPortId.getName().equals(PORT_NAMES)) {
					System.out.println("trouv�");
					portId = currPortId;
					break;
				}
		}
		if (portId == null) {
			System.out.println("Could not find COM port.");
			return;
		}

		try {
			// open serial port, and use class name for the appName.
			serialPort = (SerialPort) portId.open(this.getClass().getName(),
					TIME_OUT);

			// set port parameters
			serialPort.setSerialPortParams(DATA_RATE,
					SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);

			// open the streams
			input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
			output = serialPort.getOutputStream();

			// add event listeners
			serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	/**
	 * This should be called when you stop using the port.
	 * This will prevent port locking on platforms like Linux.
	 */
	public synchronized void close() {
		if (serialPort != null) {
			serialPort.removeEventListener();
			serialPort.close();
		}
	}

	/**
	 * Handle an event on the serial port. Read the data and print it.
	 */
	public synchronized void serialEvent(SerialPortEvent oEvent) {
		
		if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			try {
				String inputLine = input.readLine();
//				System.out.println(inputLine);
				String[] tokens = inputLine.substring(1).split(";", 7);
				float[] values = parseFloatArray(tokens);
				System.out.println(String.format("%s + %s + %s + %s + %s + %s + %s", values[0],values[1],values[2],values[3],values[4],values[5],values[6]));
				
				float h = values[0];
				float tin = values[1];
				float temRess = values[2];
				float consigne = values[5];
				float peltier = values[6];
				
				data.setHumidityRate(h);
				data.setInteriorTemperature(tin);
				data.setTemperatureRessenti(temRess);
				data.setConsigne(consigne);
				data.setPeltier(peltier);
			}
			 catch (Exception e) {
				
				 //e.printStackTrace();	//Permet de traquer les erreurs �ventuel..
			}
		}
		
	
	}




	private float[] parseFloatArray(String[] tokens) {
		float[] r = new float[tokens.length];
		int i = 0;
		for (String tok : tokens) {
			r[i++] = tok.toLowerCase().equals("nan") ? -1 : Float.parseFloat(tok);
		}
		return r;
	}	
	
}
