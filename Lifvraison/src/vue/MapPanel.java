package vue;

import java.awt.Color;
import java.awt.Graphics;

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
	
	private int xMin = 999999;
	private int yMin = 999999;
	private double coefX;
	private double coefY;
	
	//public MapPanel(Plan plan, DemandeLivraison demandeLivraison, Tournee tournee)
	public MapPanel(Plan plan, DemandeLivraison demandeLivraison)
	{
		this.plan = plan;
		this.demandeLivraison = demandeLivraison;
		//this.tournee = tournee;
		
		int xMax = 0;
		int yMax = 0;
		
		for (Map.Entry mapentry : plan.getListeIntersection().entrySet()) 
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
		
		coefX = 950.0 / (xMax - xMin);
		coefY = 1750.0 / (yMax - yMin);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		// Affihage des Troncons du Plan
		
		for (Map.Entry mapentry : plan.getListeTroncons().entrySet()) 
        {
        	g.drawLine(25 + (int)Math.round((((Troncon) mapentry.getValue()).getIntersectionDepart().getY() - yMin) * coefY),
        			25 + 950 - (int)Math.round((((Troncon) mapentry.getValue()).getIntersectionDepart().getX() - xMin) * coefX),
        			25 + (int)Math.round((((Troncon) mapentry.getValue()).getIntersectionArrive().getY() - yMin) * coefY),
    				25 + 950 - (int)Math.round((((Troncon) mapentry.getValue()).getIntersectionArrive().getX() - xMin) * coefX));
        }
        
		g.setColor(new Color(0, 200, 0));
        
		g.fillRect(25 + (int)Math.round((demandeLivraison.getEntrepot().getY() - yMin) * coefY) - 2, 
    			25 + 950 - (int)Math.round((demandeLivraison.getEntrepot().getX() - xMin) * coefX) - 2, 
    			5, 5);
		
        g.setColor(Color.RED);
        
        for (Livraison livraison : demandeLivraison.getLivraisons()) 
        {
        	g.fillRect(25 + (int)Math.round((livraison.getIntersection().getY() - yMin) * coefY) - 2, 
        			25 + 950 - (int)Math.round((livraison.getIntersection().getX() - xMin) * coefX) - 2, 
        			5, 5);
        }
        
        g.setColor(Color.BLUE);
        
        /*for (Itineraire itineraire : tournee.getItineraires())
        {
        	for (Troncon troncon : itineraire.getTroncons())
        	{
        		g.drawLine(25 + (int)Math.round((troncon.getIntersectionDepart().getY() - yMin) * coefY),
            			25 + 500 - (int)Math.round((troncon.getIntersectionDepart().getX() - xMin) * coefX),
            			25 + (int)Math.round((troncon.getIntersectionArrive().getY() - yMin) * coefY),
        				25 + 500 - (int)Math.round((troncon.getIntersectionArrive().getX() - xMin) * coefX));
        	}
        }*/
        
        g.setColor(Color.BLACK);
	}
}
