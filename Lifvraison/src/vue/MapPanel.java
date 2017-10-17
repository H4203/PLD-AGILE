package vue;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.util.Map;

import javax.swing.JPanel;

import modeles.Plan;
import modeles.Tournee;
import modeles.Troncon;
import modeles.DemandeLivraison;
import modeles.Intersection;
import modeles.Itineraire;
import modeles.Livraison;

public class MapPanel extends JPanel 
{
	private Plan plan;
	private DemandeLivraison demandeLivraison;
	private Tournee tournee;
	
	private int bord = 10;
	private int xMin = 999999;
	private int yMin = 999999;
	private double coefX;
	private double coefY;
	
	public MapPanel(Plan plan, DemandeLivraison demandeLivraison, Tournee tournee)
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds(0,0,screenSize.width-bord, screenSize.height-bord);
		
		this.plan = plan;
		this.demandeLivraison = demandeLivraison;
		this.tournee = tournee;
		
		int xMax = 0;
		int yMax = 0;
		
		for (Map.Entry<Long, Intersection> mapentry : plan.getListeIntersection().entrySet()) 
        {
			if (((Intersection) mapentry.getValue()).getX() > xMax)
			{
				xMax = ((Intersection) mapentry.getValue()).getX();
			}
			else if (((Intersection) mapentry.getValue()).getX() < xMin)
			{
				xMin = ((Intersection) mapentry.getValue()).getX();
			}
			
			if (((Intersection) mapentry.getValue()).getY() > yMax)
			{
				yMax = ((Intersection) mapentry.getValue()).getY();
			}
			else if (((Intersection) mapentry.getValue()).getY() < yMin)
			{
				yMin = ((Intersection) mapentry.getValue()).getY();
			}
        }
		
		coefX = (double)(screenSize.height) / (xMax - xMin);
		coefY = (double)(screenSize.width) / (yMax - yMin);
	}
	
	public void repaint(Graphics g)
	{
		super.repaint();
		
		paintComponent(g);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		Graphics2D g2 = (Graphics2D) g;
		
		// Affihage des Troncons du Plan
		
		for (Map.Entry<Integer, Troncon> mapentry : plan.getListeTroncons().entrySet()) 
        {
        	g2.drawLine( (int)Math.round((((Troncon) mapentry.getValue()).getIntersectionDepart().getY() - yMin) * coefY),
        			(int)(screenSize.height-bord) - (int)Math.round((((Troncon) mapentry.getValue()).getIntersectionDepart().getX() - xMin) * coefX),
        			(int)Math.round((((Troncon) mapentry.getValue()).getIntersectionArrive().getY() - yMin) * coefY),
        			(int)(screenSize.height-bord) - (int)Math.round((((Troncon) mapentry.getValue()).getIntersectionArrive().getX() - xMin) * coefX));
        }
        
        g2.setColor(Color.BLUE);
        g2.setStroke(new BasicStroke(3));
        
        
        // affichage itineraire 
        // gestion immonde mais temporaire
        if ( demandeLivraison != null)
        {
	        for (Itineraire itineraire : tournee.getListeItineraires())
	        {
	        	for (Troncon troncon : itineraire.getTroncons())
	        	{
	        		g2.drawLine((int)Math.round((troncon.getIntersectionDepart().getY() - yMin) * coefY),
	        				(int)(screenSize.height-bord) - (int)Math.round((troncon.getIntersectionDepart().getX() - xMin) * coefX),
	            			(int)Math.round((troncon.getIntersectionArrive().getY() - yMin) * coefY),
	            			(int)(screenSize.height-bord) - (int)Math.round((troncon.getIntersectionArrive().getX() - xMin) * coefX));
	        	}
	        }
	        
	        g2.setColor(new Color(0, 150, 0));
	        
			g2.fillRect((int)Math.round((demandeLivraison.getEntrepot().getY() - yMin) * coefY) - 5, 
					(int)(screenSize.height-bord) - (int)Math.round((demandeLivraison.getEntrepot().getX() - xMin) * coefX) - 5, 
	    			10, 10);
			
	        g2.setColor(Color.RED);
	        
	        for (Livraison livraison : demandeLivraison.getLivraisons()) 
	        {
	        	g2.fillRect((int)Math.round((livraison.getIntersection().getY() - yMin) * coefY) - 4, 
	        			(int)(screenSize.height-bord) - (int)Math.round((livraison.getIntersection().getX() - xMin) * coefX) - 4, 
	        			8, 8);
	        }
	        
	        
        }
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(1));
	}
}
