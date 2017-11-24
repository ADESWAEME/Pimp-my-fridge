package vue;

import java.awt.BasicStroke;
import java.awt.Color;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.Range;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.Layer;
import org.jfree.ui.RectangleEdge;


@SuppressWarnings("serial")
public class DataCourbe extends ApplicationFrame{
	private JFreeChart texte;
	@SuppressWarnings("unused")
	private DefaultCategoryDataset dataset;
	private XYSeries tempRess;
	private XYSeries tempIn;
	
	private long compteur;
	public ValueMarker consigne;
	
	public DataCourbe(String applicationTitle , String chartTitle)
	   {
	      super(applicationTitle);
	      texte = ChartFactory.createXYLineChart(
	         chartTitle,
	         "Temps","°C",
	         createDataset(),
	         PlotOrientation.VERTICAL,
	         true,true,false);
	         
	      texte.setBackgroundPaint(new Color(128,128,128));	// Fond de la fenêtre en gris
	      texte.getTitle().setPaint(new Color(255, 255, 255));	// Couleur du texte en blanc
	      
	      setTitle(null);
	    //  getJChart().setTitle((String)null); // Permet de centrer les caractères " T° ext & int " au centre de la fenêtre.
		  getDCourbe().getLegend().setPosition(RectangleEdge.RIGHT); // Met le titre en légende à droite de la fenêtre.
	      
	      ChartPanel chartPanel = new ChartPanel(texte);
	      chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
	      setContentPane( chartPanel );
	      compteur = 0;
	      
	      XYPlot plot = texte.getXYPlot();
		  consigne = new ValueMarker(0, Color.ORANGE, new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 12.0f, new float[] {5.0f}, 0.0f));
		  plot.addRangeMarker(consigne, Layer.BACKGROUND);
		  
		  // Caractéristiques du graphique (couleurs)
		  plot.setBackgroundPaint(new Color(77,77,77));
		  plot.setRangeGridlinePaint(new Color(255,255,255));
		  plot.setDomainGridlinePaint(new Color(255,255,255));
		  
		  plot.getDomainAxis().setLabelPaint(new Color(255,255,255));	// Titre "temps" en couleur
		  plot.getRangeAxis().setLabelPaint(new Color(255,255,255));	// Titre "C°" en couleur
		  
		  XYItemRenderer data = plot.getRenderer();
		   
		  // Température intérieure
		  data.setSeriesItemLabelPaint(1, new Color(0, 174, 189));
		  data.setSeriesPaint(1, new Color(0, 174, 189));
		  data.setSeriesStroke(1,  new BasicStroke(2));
		  
		  plot.getRangeAxis().setRange(new Range(- 10, 35)); // Echelle du graphique T°C
		  plot.getRangeAxis().setTickLabelPaint(new Color(225,225,225));	// Couleur de la Température
		  
		 
		  texte.getLegend().setBackgroundPaint(new Color(0,0,0)); // Couleur du fond de la légende des caractéristiques "T° int & ext".
		  texte.getLegend().setItemPaint(new Color(255,255,255)); // Couleur du texte de la légende "".
		  texte.getLegend().setBorder(0, 0, 0, 0); // Ecart entre le fond et le texte de la légende "".
	   }

	private XYSeriesCollection createDataset( )
	{
		//dataset = new DefaultCategoryDataset( );
		XYSeriesCollection dataset = new XYSeriesCollection();
		tempRess = new XYSeries("T° Ress");
		tempIn = new XYSeries("T° In");

		dataset.addSeries(tempRess);
		dataset.addSeries(tempIn);
		
		this.tempIn.setMaximumItemCount(600);
		this.tempRess.setMaximumItemCount(600);
		
		return dataset;
	}

	public JFreeChart getDCourbe() {
		return texte;
	}
	
	public void addData(float tempIn, float tempRess)
	{
		compteur++;
		this.tempIn.add(compteur, tempIn);
		this.tempRess.add(compteur,tempRess);
	}

}
