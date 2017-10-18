package vue;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import modeles.Plan;
import modeles.Tournee;
import modeles.Troncon;
import modeles.DemandeLivraison;
import modeles.Intersection;
import modeles.Itineraire;
import modeles.Livraison;
import modeles.PlageHoraire;

public class MapPanel extends JPanel 
{
	private Plan plan;
	private DemandeLivraison demandeLivraison;
	private Tournee tournee;
	
	private int bord = 200;
	private int xMin = 999999;
	private int yMin = 999999;
	private double coefX;
	private double coefY;
	
	public MapPanel(Plan plan, DemandeLivraison demandeLivraison, Tournee tournee)
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds(10, 10, screenSize.width-bord, screenSize.height-bord);
		
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
		
		coefX = (double)(screenSize.height-bord) / (xMax - xMin);
		coefY = (double)(screenSize.width-bord) / (yMax - yMin);
	}
	
	public MapPanel(Plan plan, DemandeLivraison demandeLivraison)
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds(10, 10, screenSize.width-bord, screenSize.height-bord);
		
		this.plan = plan;
		this.demandeLivraison = demandeLivraison;
		
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
		
		coefX = (double)(screenSize.height-bord) / (xMax - xMin);
		coefY = (double)(screenSize.width-bord) / (yMax - yMin);
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
        	/* affiche la tournée */
        	if ( tournee != null ) {
        		int i = 0;
        		for (Itineraire itineraire : tournee.getListeItineraires())
    	        {
    	        	for (Troncon troncon : itineraire.getTroncons())
    	        	{
    	        		g2.drawLine((int)Math.round((troncon.getIntersectionDepart().getY() - yMin) * coefY),
    	        				(int)(screenSize.height-bord) - (int)Math.round((troncon.getIntersectionDepart().getX() - xMin) * coefX),
    	            			(int)Math.round((troncon.getIntersectionArrive().getY() - yMin) * coefY),
    	            			(int)(screenSize.height-bord) - (int)Math.round((troncon.getIntersectionArrive().getX() - xMin) * coefX));
    	        	}
    	        	
    	        	g2.setFont(new Font("default", Font.BOLD, 16));
    	        	i++;
    	        	if ( i < tournee.getListeItineraires().size())
    	        	{
		        		/* horaires */
						g2.drawString(tournee.getListeHoraire().get(i).getHeureDebut().toString()
								+ " - " +
								tournee.getListeHoraire().get(i).getHeureFin().toString(),
								(int)Math.round((itineraire.getArrivee().getY() - yMin) * coefY),
		            			(int)(screenSize.height-bord) - (int)Math.round((itineraire.getArrivee().getX() - xMin) * coefX));
    	        	}
    	        }
        	}
        	
        	/* affiche les points */
        	/* entrepot */
	        g2.setColor(new Color(0, 150, 0));
	        
			g2.fillRect((int)Math.round((demandeLivraison.getEntrepot().getY() - yMin) * coefY) - 5, 
					(int)(screenSize.height-bord) - (int)Math.round((demandeLivraison.getEntrepot().getX() - xMin) * coefX) - 5, 
	    			10, 10);
			
			/*
			BufferedImage img = null;
			try {
				img = ImageIO.read(new File("\\ihm\\mapmarker2.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	g2.drawImage(img, (int)Math.round((demandeLivraison.getEntrepot().getY() - yMin) * coefY) - -10, 
					(int)(screenSize.height-bord) - (int)Math.round((demandeLivraison.getEntrepot().getX() - xMin) * coefX) - -10, 
        			20, 20, this);
			 */
			
        	/* heure debut tournee - heure fin tournee */
			g2.setFont(new Font("default", Font.BOLD, 16));
			g2.setColor(Color.BLUE);
			if (tournee != null)
			{
				g2.drawString(tournee.getListeHoraire().get(0).getHeureDebut().toString() + " - " + tournee.getListeHoraire().get(tournee.getListeHoraire().size()-1).getHeureDebut().toString(),
						(int)Math.round((demandeLivraison.getEntrepot().getY() - yMin) * coefY) - 5, 
						(int)(screenSize.height-bord) - (int)Math.round((demandeLivraison.getEntrepot().getX() - xMin) * coefX) - 5);
			}
			else
			{
				g2.drawString(demandeLivraison.getHeureDepart().toString(),
						(int)Math.round((demandeLivraison.getEntrepot().getY() - yMin) * coefY) - 5, 
						(int)(screenSize.height-bord) - (int)Math.round((demandeLivraison.getEntrepot().getX() - xMin) * coefX) - 5);
			}
			
			/* points de livraisons */
	        g2.setColor(Color.RED);
	        for (Livraison livraison : demandeLivraison.getLivraisons()) 
	        {
	        	/* points de livraison */
	        	g2.fillRect((int)Math.round((livraison.getIntersection().getY() - yMin) * coefY) - 4, 
	        			(int)(screenSize.height-bord) - (int)Math.round((livraison.getIntersection().getX() - xMin) * coefX) - 4, 
	        			8, 8);
	        	/*
				try {
					img = ImageIO.read(new File("\\ihm\\mapmarker.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	g2.drawImage(img, (int)Math.round((livraison.getIntersection().getY() - yMin) * coefY) -10,
	        			(int)(screenSize.height-bord) - (int)Math.round((livraison.getIntersection().getX() - xMin) * coefX) -10,
	        			20, 20, this);
	        	*/
	        	
	        	/* heure depart et arrivee de la livraison */
	        	g2.setFont(new Font("default", Font.BOLD, 16));
				g2.setColor(Color.BLUE);
				if (tournee == null)
				{
					PlageHoraire plhr = livraison.getPlagehoraire();
					if ( plhr != null && plhr.getHeureDebut() != null && plhr.getHeureFin() != null )
					{
						g2.drawString(plhr.getHeureDebut() + " - " + plhr.getHeureFin(),
										(int)Math.round((livraison.getIntersection().getY() - yMin) * coefY) - 4,
										(int)(screenSize.height-bord) - (int)Math.round((livraison.getIntersection().getX() - xMin) * coefX) - 4);
					}
				}
				g2.setColor(Color.RED);
	        }
	        
	        
        }
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(1));
	}
}
