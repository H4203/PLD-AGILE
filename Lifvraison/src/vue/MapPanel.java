package vue;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Map;

import javax.swing.JPanel;

import controleur.Controleur;
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
	private static final long serialVersionUID = 1L;
	
	private Plan plan;
	private DemandeLivraison demandeLivraison;
	private Tournee tournee;

	private int sideLength;
	
	private int xMin;
	private int yMin;
	private double coefX;
	private double coefY;
	
	private boolean affichagePlan;
	private boolean affichageDemandeLivraison;
	private boolean affichageTournee;

	public MapPanel(Fenetre fenetre, Plan plan, DemandeLivraison demandeLivraison, Tournee tournee, Controleur controleur)
	{
		this.plan = plan;
		this.demandeLivraison = demandeLivraison;
		this.tournee = tournee;
		
		affichagePlan = false;
		affichageDemandeLivraison = false;
		affichageTournee = false;
		
		EcouteurDeSouris ecouteurDeSouris = new EcouteurDeSouris(this, controleur);
		
		addMouseMotionListener(ecouteurDeSouris);
		addMouseListener(ecouteurDeSouris);
	}
	
	public void repaint(Graphics g)
	{
		super.repaint();
		
		paintComponent(g);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(1));
		
		// Border
		
		g2.drawLine(0, 0, sideLength - 1, 0);
		g2.drawLine(0, 0, 0, sideLength - 1);
		g2.drawLine(sideLength - 1, sideLength - 1, sideLength - 1, 0);
		g2.drawLine(sideLength - 1, sideLength - 1, 0, sideLength - 1);
		
		// Map
		
		if (plan != null && affichagePlan == true)
		{
			for (Map.Entry<Integer, Troncon> mapentry : plan.getListeTroncons().entrySet()) 
	        {	
        		g2.drawLine((int)Math.round((((Troncon) mapentry.getValue()).getIntersectionDepart().getY() + plan.getFocus().x / coefY - yMin) * coefY),
        				sideLength - (int)Math.round((((Troncon) mapentry.getValue()).getIntersectionDepart().getX() - plan.getFocus().y / coefX - xMin) * coefX),
        				(int)Math.round((((Troncon) mapentry.getValue()).getIntersectionArrive().getY() + plan.getFocus().x / coefY - yMin) * coefY),
        				sideLength - (int)Math.round((((Troncon) mapentry.getValue()).getIntersectionArrive().getX() - plan.getFocus().y / coefX - xMin) * coefX));
        	}
		}
		
        // Selected Intersection
        
        g2.setColor(Color.RED);
        
        if (plan != null && plan.getSelectedIntersection() != null)
        {
        	g2.fillRect((int)Math.round((plan.getSelectedIntersection().getY() + plan.getFocus().x / coefY - yMin) * coefY) - 4, 
        			sideLength - (int)Math.round((plan.getSelectedIntersection().getX() - plan.getFocus().y / coefX - xMin) * coefX) - 4,
        			8, 8);
    	}
        
        g2.setColor(Color.BLACK);
        
        g2.setColor(Color.BLUE);
        g2.setStroke(new BasicStroke(3));
        
        // affichage itineraire 
        // gestion immonde mais temporaire
        if ( demandeLivraison != null && affichageDemandeLivraison == true)
        {
        	/* affiche la tournee */
        	if ( tournee != null && affichageTournee == true) 
        	{
        		int i = 0;
        		for (Itineraire itineraire : tournee.getListeItineraires())
    	        {
    	        	for (Troncon troncon : itineraire.getTroncons())
    	        	{
    	        		g2.drawLine((int)Math.round((troncon.getIntersectionDepart().getY() + plan.getFocus().x / coefY - yMin) * coefY),
	        				sideLength - (int)Math.round((troncon.getIntersectionDepart().getX() - plan.getFocus().y / coefX - xMin) * coefX),
	            			(int)Math.round((troncon.getIntersectionArrive().getY() + plan.getFocus().x / coefY - yMin) * coefY),
	            			sideLength - (int)Math.round((troncon.getIntersectionArrive().getX() - plan.getFocus().y / coefX - xMin) * coefX));
    	        	}
    	        	
    	        	g2.setFont(new Font("default", Font.BOLD, 16));
    	        	g2.setColor(Color.RED);
    	        	i++;
    	        	if ( i < tournee.getListeItineraires().size())
    	        	{
		        		/* horaires */
						/*g2.drawString(tournee.getListeHoraire().get(i).getHeureDebut().toString()
								+ " - " +
								tournee.getListeHoraire().get(i).getHeureFin().toString(),
								(int)Math.round((itineraire.getArrivee().getY() - yMin) * coefY),
		            			(int)(screenSize.height-bord) - (int)Math.round((itineraire.getArrivee().getX() - xMin) * coefX));
		            			*/
    	        		g2.drawString(" "+i,
	        				(int)Math.round((itineraire.getArrivee().getY() + plan.getFocus().x / coefY - yMin) * coefY),
	        				sideLength - (int)Math.round((itineraire.getArrivee().getX() - plan.getFocus().y / coefX - xMin) * coefX));
    	        	}
    	        	g2.setColor(Color.BLUE);
    	        }
        	}
        	
        	/* affiche les points */
        	/* entrepot */
	        g2.setColor(new Color(0, 150, 0));
	        
	        if (demandeLivraison.getEntrepot() != null && demandeLivraison.getEntrepot().getY() != null && demandeLivraison.getEntrepot().getX() != null)
    		{
	        	g2.fillRect((int)Math.round((demandeLivraison.getEntrepot().getY() + plan.getFocus().x / coefY - yMin) * coefY) - 5, 
	        			sideLength - (int)Math.round((demandeLivraison.getEntrepot().getX() - plan.getFocus().y / coefX - xMin) * coefX) - 5, 
        			10, 10);
    		}
	        
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
			if (demandeLivraison != null && tournee != null && tournee.getListeHoraire().size() != 0 && demandeLivraison.getEntrepot() != null 
					&& demandeLivraison.getEntrepot().getY() != null && demandeLivraison.getEntrepot().getX() != null)
			{
				g2.drawString(tournee.getListeHoraire().get(0).getHeureDebut().toString() + " - " + tournee.getListeHoraire().get(tournee.getListeHoraire().size()-1).getHeureDebut().toString(),
					(int)Math.round((demandeLivraison.getEntrepot().getY() + plan.getFocus().x / coefY - yMin) * coefY) - 5, 
					sideLength - (int)Math.round((demandeLivraison.getEntrepot().getX() - plan.getFocus().y / coefX - xMin) * coefX) - 5);
			}
			else if (demandeLivraison != null && demandeLivraison.getHeureDepart() != null 
					&& demandeLivraison.getEntrepot().getY() != null && demandeLivraison.getEntrepot().getX() != null)
			{
				g2.drawString(demandeLivraison.getHeureDepart().toString(),
					(int)Math.round((demandeLivraison.getEntrepot().getY() + plan.getFocus().x / coefY - yMin) * coefY) - 5, 
					sideLength - (int)Math.round((demandeLivraison.getEntrepot().getX() - plan.getFocus().y / coefX - xMin) * coefX) - 5);
			}
			
			/* points de livraisons */
	        g2.setColor(Color.RED);
	        for (Livraison livraison : demandeLivraison.getLivraisons()) 
	        {
	        	/* points de livraison */
	        	g2.fillRect((int)Math.round((livraison.getIntersection().getY() + plan.getFocus().x / coefY- yMin) * coefY) - 4, 
        			sideLength - (int)Math.round((livraison.getIntersection().getX() - plan.getFocus().y / coefX - xMin) * coefX) - 4,
        			8, 8);
              
	        	if (livraison.getPlagehoraire() == null || livraison.getPlagehoraire().getHeureDebut() == null || livraison.getPlagehoraire().getHeureFin() == null)
	        	{
	        		g2.setColor(Color.BLACK);
	        		g2.fillRect((int)Math.round((livraison.getIntersection().getY() + plan.getFocus().x / coefY - yMin) * coefY) - 4, 
        				sideLength - (int)Math.round((livraison.getIntersection().getX() - plan.getFocus().y / coefX - xMin) * coefX) - 4, 
	        			8, 8);
	        		g2.setColor(Color.RED);
	        	}

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
							(int)Math.round((livraison.getIntersection().getY() + plan.getFocus().x / coefY - yMin) * coefY) - 4,
							sideLength - (int)Math.round((livraison.getIntersection().getX() - plan.getFocus().y / coefX - xMin) * coefX) - 4);
					}
				}
				g2.setColor(Color.RED);
	        }
        }
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(1));
	}
	
	public void setAffichagePlan(boolean etat)
	{
		affichagePlan = etat;
	}
	
	public void setAffichageDemandeLivraison(boolean etat)
	{
		affichageDemandeLivraison = etat;
	}
	
	public void setAffichageTournee(boolean etat)
	{
		affichageTournee = etat;
	}
	
	public void resize()
	{
		int xMax = 0;
		int yMax = 0;
		xMin = 999999;
		yMin = 999999;
		
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
		
		if (getSize().height < getSize().width)
		{
			sideLength = getSize().height;
			
			coefX = (double)(getSize().height) / (double)(xMax - xMin);
			coefY = (double)(getSize().height) / (double)(yMax - yMin);
		}
		else
		{
			sideLength = getSize().width;
			
			coefX = (double)(getSize().width) / (double)(xMax - xMin);
			coefY = (double)(getSize().width) / (double)(yMax - yMin);
		}
	}
	
	public Point convertPoint(Point point)
	{
		//System.out.println("Raw " + point);
		
		Point convertedPoint = new Point((int)Math.round((sideLength - point.getY()) / coefX + xMin), (int)Math.round(point.getX() / coefY + yMin));

		//System.out.println("Converted " + convertedPoint);
		
		return convertedPoint;
		
		
	}
}

