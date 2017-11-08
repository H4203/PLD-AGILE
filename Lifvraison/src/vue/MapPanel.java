package vue;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import controleur.Controleur;
import modeles.Plan;
import modeles.Tournee;
import modeles.Troncon;
import modeles.DemandeLivraison;
import modeles.Itineraire;
import modeles.Livraison;
import modeles.PlageHoraire;

public class MapPanel extends JPanel
{
	private final double toleranceSelectionIntersection = 20.0;
	
	private static final long serialVersionUID = 1L;
	
	private Plan plan;
	private DemandeLivraison demandeLivraison;
	private Tournee tournee;

	private int sideLength;
	
	private double coefX;
	private double coefY;
	private Point focus;
	private double zoom;
	
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
		addMouseWheelListener(ecouteurDeSouris);
		
		init();
	}
	
	private void init()
	{
		coefX = 0;
		coefY = 0;
		focus = new Point();
		
		resetZoomFocus();
		
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
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

        // BackGround
        
        g2.setColor(Color.WHITE);
        
        g2.fillRect(0, 0, getSize().width, getSize().height);
        
        g2.setColor(Color.BLACK);
        
        // Selected Intersection
        
        g2.setColor(Color.RED);
        
        if (plan != null && plan.getSelectedIntersection() != null)
        {
        	g2.fillRect((int)Math.round((plan.getSelectedIntersection().getY() + focus.x / coefY - plan.getYMin()) * coefY) - 5, 
        			sideLength - (int)Math.round((plan.getSelectedIntersection().getX() - focus.y / coefX - plan.getXMin()) * coefX) - 5,
        			10, 10);
    	}
        
        g2.setColor(Color.BLACK);
        
        // Selected Troncon
        
        g2.setColor(Color.RED);
        g2.setStroke(new BasicStroke(4));
        
        if (plan != null && plan.getSelectedTroncon() != null)
        {
        	g2.drawLine((int)Math.round((plan.getSelectedTroncon().getIntersectionDepart().getY() - plan.getYMin()) * coefY + focus.x),
    				sideLength - (int)Math.round((plan.getSelectedTroncon().getIntersectionDepart().getX() - plan.getXMin()) * coefX - focus.y),
    				(int)Math.round((plan.getSelectedTroncon().getIntersectionArrive().getY() - plan.getYMin()) * coefY + focus.x),
    				sideLength - (int)Math.round((plan.getSelectedTroncon().getIntersectionArrive().getX() - plan.getXMin()) * coefX - focus.y));
        }
        
        g2.setStroke(new BasicStroke(1));
        g2.setColor(Color.BLACK);
		
		// Map

		if (plan != null && affichagePlan == true)
		{
			for (Map.Entry<Integer, Troncon> mapentry : plan.getListeTroncons().entrySet()) 
	        {	
        		g2.drawLine((int)Math.round((( (Troncon) mapentry.getValue() ).getIntersectionDepart().getY() - plan.getYMin()) * coefY + focus.x),
        				sideLength - (int)Math.round((( (Troncon) mapentry.getValue() ).getIntersectionDepart().getX() - plan.getXMin()) * coefX - focus.y),
        				(int)Math.round((( (Troncon) mapentry.getValue() ).getIntersectionArrive().getY() - plan.getYMin()) * coefY + focus.x),
        				sideLength - (int)Math.round((( (Troncon) mapentry.getValue() ).getIntersectionArrive().getX() - plan.getXMin()) * coefX - focus.y));
        	}
		}
		
        // Itineraire
        
        g2.setColor(Color.BLUE);
        g2.setStroke(new BasicStroke(2));
        
        if ( demandeLivraison != null && affichageDemandeLivraison == true)
        {
        	// Tournee
        	
        	if ( tournee != null && affichageTournee == true) 
        	{
        		int i = 0;
        		for (Itineraire itineraire : tournee.getListeItineraires())
    	        {
    	        	for (Troncon troncon : itineraire.getTroncons())
    	        	{
    	        		g2.drawLine((int)Math.round((troncon.getIntersectionDepart().getY() + focus.x / coefY - plan.getYMin()) * coefY),
	        				sideLength - (int)Math.round((troncon.getIntersectionDepart().getX() - focus.y / coefX - plan.getXMin()) * coefX),
	            			(int)Math.round((troncon.getIntersectionArrive().getY() + focus.x / coefY - plan.getYMin()) * coefY),
	            			sideLength - (int)Math.round((troncon.getIntersectionArrive().getX() - focus.y / coefX - plan.getXMin()) * coefX));
    	        	}
    	        	
    	        	g2.setFont(new Font("default", Font.BOLD, 16));
    	        	g2.setColor(Color.RED);
    	        	i++;
    	        	if ( i < tournee.getListeItineraires().size())
    	        	{
    	        		g2.drawString(" "+i,
	        				(int)Math.round((itineraire.getArrivee().getY() + focus.x / coefY - plan.getYMin()) * coefY),
	        				sideLength - (int)Math.round((itineraire.getArrivee().getX() - focus.y / coefX - plan.getXMin()) * coefX));
    	        	}
    	        	g2.setColor(Color.BLUE);
    	        }
        	}
        	
        	// Entrepot
        	
	        g2.setColor(new Color(0, 150, 0));
	        
	        if (demandeLivraison.getEntrepot() != null && demandeLivraison.getEntrepot().getY() != null && demandeLivraison.getEntrepot().getX() != null)
    		{
	        	g2.fillRect((int)Math.round((demandeLivraison.getEntrepot().getY() + focus.x / coefY - plan.getYMin()) * coefY) - 3, 
	        			sideLength - (int)Math.round((demandeLivraison.getEntrepot().getX() - focus.y / coefX - plan.getXMin()) * coefX) - 3, 
        			6, 6);
    		}
			
        	// Debut et Fin de Tournee
	        
			g2.setFont(new Font("default", Font.BOLD, 16));
			g2.setColor(Color.BLUE);
			if (demandeLivraison != null && tournee != null && tournee.getListeHoraire().size() != 0 && demandeLivraison.getEntrepot() != null 
					&& demandeLivraison.getEntrepot().getY() != null && demandeLivraison.getEntrepot().getX() != null)
			{
				g2.drawString(tournee.getListeHoraire().get(0).getHeureDebut().toString() + " - " + tournee.getListeHoraire().get(tournee.getListeHoraire().size()-1).getHeureDebut().toString(),
					(int)Math.round((demandeLivraison.getEntrepot().getY() + focus.x / coefY - plan.getYMin()) * coefY) - 5, 
					sideLength - (int)Math.round((demandeLivraison.getEntrepot().getX() - focus.y / coefX - plan.getXMin()) * coefX) - 5);
			}
			else if (demandeLivraison != null && demandeLivraison.getEntrepot() != null && demandeLivraison.getHeureDepart() != null 
					&& demandeLivraison.getEntrepot().getY() != null && demandeLivraison.getEntrepot().getX() != null)
			{
				g2.drawString(demandeLivraison.getHeureDepart().toString(),
					(int)Math.round((demandeLivraison.getEntrepot().getY() + focus.x / coefY - plan.getYMin()) * coefY) - 5, 
					sideLength - (int)Math.round((demandeLivraison.getEntrepot().getX() - focus.y / coefX - plan.getXMin()) * coefX) - 5);
			}
			
			// Points de Livraison
			
	        for (Livraison livraison : demandeLivraison.getLivraisons()) 
	        {
	        	g2.setColor(Color.RED);	
	        	if (livraison.getPlagehoraire() == null || livraison.getPlagehoraire().getHeureDebut() == null || livraison.getPlagehoraire().getHeureFin() == null)
	        	{
	        		g2.setColor(Color.BLACK);
	        	}
	        	
	        	g2.fillRect((int)Math.round((livraison.getIntersection().getY() + focus.x / coefY - plan.getYMin()) * coefY) - 3, 
    				sideLength - (int)Math.round((livraison.getIntersection().getX() - focus.y / coefX - plan.getXMin()) * coefX) - 3, 
        			6, 6);
	        	
	        	// Plage Horaire
	        	
	        	g2.setFont(new Font("default", Font.BOLD, 16));
				g2.setColor(Color.BLUE);
				if (tournee == null)
				{
					PlageHoraire plhr = livraison.getPlagehoraire();
					if ( plhr != null && plhr.getHeureDebut() != null && plhr.getHeureFin() != null )
					{
						g2.drawString(plhr.getHeureDebut() + " - " + plhr.getHeureFin(),
							(int)Math.round((livraison.getIntersection().getY() + focus.x / coefY - plan.getYMin()) * coefY) - 4,
							sideLength - (int)Math.round((livraison.getIntersection().getX() - focus.y / coefX - plan.getXMin()) * coefX) - 4);
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
		if (plan != null)
		{
			if (getSize().height < getSize().width)
			{
				sideLength = getSize().height;
				
				coefX = (double)(getSize().height) / (double)(plan.getXMax() - plan.getXMin()) * zoom;
				coefY = (double)(getSize().height) / (double)(plan.getYMax() - plan.getYMin()) * zoom;
			}
			else
			{
				sideLength = getSize().width;
				
				coefX = (double)(getSize().width) / (double)(plan.getXMax() - plan.getXMin()) * zoom;
				coefY = (double)(getSize().width) / (double)(plan.getYMax() - plan.getYMin()) * zoom;
			}
		}
		else
		{
			sideLength = 0;
			coefX = 1;
			coefY = 1;
		}
		
		repaint();
	}
	
	public Point convertPoint(Point point)
	{
		Point convertedPoint = new Point((int)Math.round((sideLength - point.getY()) / coefX + plan.getXMin() + focus.y / coefX), (int)Math.round(point.getX() / coefY + plan.getYMin() - focus.x / coefY));

		return convertedPoint;
	}
	
	public void drag(Point delta)
	{
		focus.setLocation(focus.x + delta.x, focus.y + delta.y);
		repaint();
	}
	
	public void zoom(int steps, Point point)
	{
		point.setLocation(point.x, point.y - sideLength);
		
		zoom = zoom * Math.pow(2, -steps);
		
		if (steps > 0)
		{	
			focus.setLocation((focus.x + point.x) / 2,
					(focus.y + point.y) / 2);
		}
		else
		{
			focus.setLocation(2 * focus.x - point.x,
					2 * focus.y - point.y);
		}
		
		resize();
	}
	
	public int getToleranceClic()
	{
		int tolerance = (int)(Math.round(toleranceSelectionIntersection / (coefX + coefY))); 
		
		if (tolerance < 10)
		{
			return 10;
		}
		
		if (tolerance > 800)
		{
			return 800;
		}
		
		return tolerance;
	}
	
	public void resetZoomFocus()
	{
		zoom = 1;
		focus.setLocation(0, 0);
	}
	
	public void setPlan ( Plan plan)
	{
		this.plan = plan;
		setDemandeLivraison(null);
		setTournee(null);
		
		resetZoomFocus();
		resize();
		repaint();
	}
	
	public void setDemandeLivraison ( DemandeLivraison demandeLivraison)
	{
		this.demandeLivraison = demandeLivraison;
		setTournee(null);
	}
	
	public void setTournee ( Tournee tournee)
	{
		this.tournee = tournee;
	}
}

