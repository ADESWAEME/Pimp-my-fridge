package vue;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import org.jfree.chart.ChartPanel;

import modele.Data;


public class Fenetre implements ActionListener {

	private JFrame frame;
	
	private DataCourbe chart;
	//private Regul regul;
	
	private JLabel temperature;
	private JLabel tempRess;
	private JLabel consigneLabel;
	private JLabel hLabel;
	
	
	public JLabel labelConsigneTemp;
	public JLabel labelConsignePower;
	public JLabel labelTempInt;
	public JLabel labelHumitidy;
	public JLabel labelConsoWatt;
	public JLabel alertCondensation;
	public JLabel alertTempGap;
	public DataCourbe graphCourbe;
	public JButton btnConsignePlus;
	public JButton btnConsigneMoins;
	public JButton btnFullscreen;
	private Data data;
	

	/**
	 * Create the application.
	 */
	public Fenetre(Data d) {
		fenetre1();
		this.data = d;
		frame.setVisible(true);
		int x = 0;
		while (x == 0){
			onNewDataRead();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		
		
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void fenetre1() {
		frame = new JFrame();
		//frame.setBounds(2000, 2000, 1700, 1700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Mini frigo USB");
		frame.setSize(1700, 1450);
		frame.setLocationRelativeTo(null);
		JLabel logoExia = new JLabel("");
		logoExia.setIcon(new ImageIcon("C:/Users/Deswaeme Alexandra/git/Projet - Pimp my fridge/img/logo-exia-cesi.png"));		
		JLabel pimpMyFridge = new JLabel("Pimp My Fridge");
		pimpMyFridge.setForeground(Color.RED);
		Font caracteristiques = new Font("Courier", Font.BOLD, 30);
		pimpMyFridge.setFont(caracteristiques);

		
		
		JPanel panel = new JPanel();
		chart = new DataCourbe(
			"Courbe des températures" ,
				"Températures");
		panel.setLayout(new CardLayout(0, 0));	// Taille de notre graphique sur le panel

		ChartPanel component = new ChartPanel(chart.getDCourbe());
		panel.add(component, "name_318427173349897");
		
		JSeparator separator = new JSeparator();
		
		JSeparator separator_1 = new JSeparator();
		
		JPanel panel_3 = new JPanel();
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 826, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(logoExia, GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE , GroupLayout.PREFERRED_SIZE)	// Largeur dédié au logoExia.
							.addPreferredGap(ComponentPlacement.RELATED, 413, Short.MAX_VALUE)
							.addComponent(pimpMyFridge, GroupLayout.PREFERRED_SIZE, 825, Short.MAX_VALUE))) // Centre le titre "Pimp My Fridge" dans la fenêtre horizontallement
					.addContainerGap())
				.addComponent(separator, GroupLayout.DEFAULT_SIZE, 846, Short.MAX_VALUE)
				.addComponent(separator_1, GroupLayout.DEFAULT_SIZE, 846, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 850, Short.MAX_VALUE)
					.addContainerGap())
		);
	groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(pimpMyFridge, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE) // Permet de situer le texte en modifiant sa haute dans la fenêtre.
						.addComponent(logoExia, GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)	//Caractéristiques du séparateur 1 entre le titre et le graphique
					.addGap(0)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 900, GroupLayout.PREFERRED_SIZE)	// Dimension du tableau courbe en hauteur.
					.addGap(5)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)	//Caractéristiques des séparateur 2 entre le praphrique et les valeurs
					.addGap(0)
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 850, GroupLayout.PREFERRED_SIZE)
					.addGap(0))
		);
		
		JPanel panel_1 = new JPanel();
		
		JButton buttonMoins = new JButton("-");
		buttonMoins.addActionListener(this);
		
		JButton buttonPlus = new JButton("+");
		buttonPlus.addActionListener(this);
		
		
		temperature = new JLabel("0\u00B0C");
		temperature.setFont(new Font("Tahoma", Font.PLAIN, 23));
		temperature.setHorizontalAlignment(SwingConstants.CENTER);
		temperature.setForeground(new Color(0, 0, 0));
		
		JLabel temperatureActuelle = new JLabel("Temperature actuelle :");
		temperatureActuelle.setForeground(new Color(0, 0, 0));
		temperatureActuelle.setFont(new Font("Myriad Pro", Font.PLAIN, 23));
		
		consigneLabel = new JLabel("0\u00B0C");
		consigneLabel.setForeground(new Color(240, 128, 128));
		consigneLabel.setFont(new Font("Tahoma", Font.PLAIN, 46));
		consigneLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblConsigne = new JLabel("CONSIGNE");
		lblConsigne.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsigne.setForeground(new Color(240, 128, 128));
		lblConsigne.setFont(new Font("Myriad Pro", Font.PLAIN, 40));
		
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblConsigne, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)	//Texte "CONSIGNE"
								.addComponent(consigneLabel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE))	//Valeur de consigne
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(buttonMoins, GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)	// Largeur du boutton - de la consigne
								.addComponent(buttonPlus, GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)))	// Largeur du boutton + de la consigne
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(temperatureActuelle)
							.addGap(15)
							.addComponent(temperature, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(1)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(temperatureActuelle, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(temperature, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(consigneLabel, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
								.addComponent(buttonMoins, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
							.addGap(1)
							.addComponent(lblConsigne, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE))
						.addComponent(buttonPlus, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panel_1.setLayout(gl_panel_1);
		
		JPanel panel_2 = new JPanel();
		
		JLabel lblTauxDhumiditer = new JLabel("Taux d'humidité :");	// Texte pour le taux d'humidité
		lblTauxDhumiditer.setForeground(new Color(0, 0, 0));	// Couleur du texte
		lblTauxDhumiditer.setFont(new Font("Myriad Pro", Font.PLAIN, 23));	// Police du texte avec sa taille
		
		hLabel = new JLabel("0%");	// Valeur en % de l'humidié 
		hLabel.setForeground(new Color(0, 0, 0));	// Couleur du texte
		hLabel.setHorizontalAlignment(SwingConstants.CENTER);	// Position du texte dans le panel_2
		hLabel.setFont(new Font("Myriad Pro", Font.PLAIN, 23));	// Police et taille du texte
		
		tempRess = new JLabel("0\u00B0C");
		tempRess.setFont(new Font("Tahoma", Font.PLAIN, 23));
		tempRess.setHorizontalAlignment(SwingConstants.CENTER);
		tempRess.setForeground(new Color(0, 0, 0));
		
		JLabel lblTempRess = new JLabel("Temperature resssentis :");
		lblTempRess.setForeground(new Color(0, 0, 0));
		lblTempRess.setFont(new Font("Myriad Pro", Font.PLAIN, 23));
		
		
		JLabel lblRalisationCesiExia = new JLabel("CESI Exia A2 - Alexandra DESWAEME _ Paul BELIN _ Vincent NAVARRO ");
		lblRalisationCesiExia.setForeground(new Color(0, 0, 0));
		lblRalisationCesiExia.setFont(new Font("Tahoma", Font.ITALIC, 11));
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
				gl_panel_2.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_panel_2.createSequentialGroup()
						.addContainerGap(45, Short.MAX_VALUE)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
							.addComponent(lblRalisationCesiExia)
							.addGroup(gl_panel_2.createSequentialGroup()
								.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(lblTauxDhumiditer, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblTempRess, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
									.addComponent(hLabel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
									.addComponent(tempRess, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))))
						.addContainerGap())
			);
			gl_panel_2.setVerticalGroup(
				gl_panel_2.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_panel_2.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
							.addComponent(tempRess, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblTempRess, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
							.addComponent(hLabel, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblTauxDhumiditer, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
						.addComponent(lblRalisationCesiExia)
						.addContainerGap())
		);
		panel_2.setLayout(gl_panel_2);
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
					.addGap(72)
					.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
							.addGap(650))
						.addGroup(gl_panel_3.createSequentialGroup()
							.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					//		.addGap(fen2.alertCondensation)
							.addGap(650))))
		);
		panel_3.setLayout(gl_panel_3);
		frame.getContentPane().setLayout(groupLayout);
		
	}

	
	

	public void onNewDataRead() {
		
		// On met à jour les labels
		temperature.setText(String.format("%.2f °C", data.getInteriorTemperature()));
		hLabel.setText(String.format("%.1f", data.getHumidityRate()) + " %");
		tempRess.setText(String.format("%.2f °C", data.getTemperatureRessenti()));
		consigneLabel.setText(String.format("%.2f °C", data.getConsigne()));
		// On ajoute la donnée au chart
		chart.addData((float)data.getInteriorTemperature(),(float) data.getTemperatureRessenti());
		
	}


	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getActionCommand().equals("+")) {
			data.setConsigne(data.getConsigne() + 1);
		}
		else
		{
			data.setConsigne(data.getConsigne() - 1);
		}
		
		consigneLabel.setText(data.getConsigne() + "°C");
		
	}



//	public void consigneTempChanged() {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				consigneLabel.setText(data.getConsigne() + "°C");
//			}
//		});
//	}
//
//	@Override
//	public void consigneAllumageChanged(boolean enabled) {
//	}
//
//	@Override
//	public void alertCondChanged(boolean state) {
//	}
//
//	@Override
//	public void alertTempGapChanged(boolean state) {
//	}
//
//	@Override
//	public void onPowerStatusChanged(boolean powerOn) {
//	}*/

	
}

