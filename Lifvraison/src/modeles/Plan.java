package modeles;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import donnees.ParseurException;

public class Plan extends Observable
{	
	private HashMap<Long, Intersection> listeIntersection;
	private HashMap<Integer, Troncon> listeTroncons;
	
	private Intersection selectedIntersection;
	private Troncon selectedTroncon;

	private int idTroncon = 1;
	
	private int xMin, xMax, yMin, yMax;
	
	public Plan() 
	{
		this.listeIntersection = new HashMap<Long, Intersection>();
		this.listeTroncons = new HashMap<Integer, Troncon>();
		
		init();
	}
	
	public Plan(HashMap<Long, Intersection> listeIntersection, HashMap<Integer, Troncon> listeTroncons) 
	{
		this.listeIntersection = listeIntersection;
		this.listeTroncons = listeTroncons;
		
		init();
	}
	
	private void init()
	{
		xMin = 999999;
		xMax = 0;
		yMin = 999999;
		yMax = 0;
		
		resetBounds();
		
		notifyObservers();
	}
	
	public void reset()
	{
		listeIntersection.clear();
		listeTroncons.clear();
		
		resetBounds();
	}

	public HashMap<Long, Intersection> getListeIntersection() {
		return listeIntersection;
	}

	public void setListeIntersection(HashMap<Long, Intersection> listeIntersection) 
	{
		this.listeIntersection = listeIntersection;
		
		resetBounds();
	}
	
	public void ajouterIntersection(Intersection aAjouter) throws ParseurException
	{
		if ( listeIntersection.containsKey(aAjouter.getId()) )
		{
			throw new ParseurException ("L'id "+ aAjouter.getId() +" est en double...");
		}
		this.listeIntersection.put(aAjouter.getId(), aAjouter);
		
		resetBounds();
	}
	

	public void ajouterIntersection(Long id, int x, int y) throws ParseurException
	{
		if ( listeIntersection.containsKey(id) )
		{
			throw new ParseurException ("L'id "+ id +" est en double...");
		}
		this.listeIntersection.put( id, new Intersection(id,  x,  y) );
		
		resetBounds();
	}
	
	public void  ajouterTroncon(String nomDeRue, Intersection intersectionDepart, Intersection intersectionArrivee, double longueur)
	{
		Troncon troncon = new Troncon(nomDeRue, intersectionDepart, intersectionArrivee, longueur);
		this.listeTroncons.put(idTroncon, troncon);
		idTroncon = idTroncon + 1;
		/* on met a jour les valeurs des listes de troncons */
		intersectionDepart.addTronconSortant(troncon);
		intersectionArrivee.addTronconEntrant(troncon);
		
		setChanged();
		notifyObservers();
	}
	
	public HashMap<Integer, Troncon> getListeTroncons() 
	{
		return listeTroncons;
	}

	public void setListeTroncons(HashMap<Integer, Troncon> listeTroncons) {
		this.listeTroncons = listeTroncons;
		
		setChanged();
		notifyObservers();
	}

	@Override
	public String toString() {
		return "Plan [listeIntersection=" + listeIntersection + ", \nlisteTroncons=" + listeTroncons + "]";
	}

	public Intersection getSelectedIntersection()
	{
		return selectedIntersection;
	}
	
	public Troncon getSelectedTroncon()
	{
		return selectedTroncon;
	}
	
	public void getAtPoint(Point point, int tolerance)
	{
		selectedIntersection = null;
		selectedTroncon = null;
		
		for (Map.Entry<Long, Intersection> intersection : listeIntersection.entrySet()) 
<<<<<<< HEAD
		{
			if (point.getX() < intersection.getValue().getX() + tolerance && point.getX() > intersection.getValue().getX() - tolerance
					&& point.getY() < intersection.getValue().getY() + tolerance && point.getY() > intersection.getValue().getY() - tolerance) 
			{
				selectedIntersection = intersection.getValue();
			}
		}
		
		if (selectedIntersection == null)
		{
=======
		{
			if (point.getX() < intersection.getValue().getX() + tolerance && point.getX() > intersection.getValue().getX() - tolerance
					&& point.getY() < intersection.getValue().getY() + tolerance && point.getY() > intersection.getValue().getY() - tolerance) 
			{
				selectedIntersection = intersection.getValue();
			}
		}
		
		if (selectedIntersection == null)
		{
>>>>>>> Dev
			double departX, departY, arriveeX, arriveeY, minX, minY, maxX, maxY;
			double a, b;
			
			for (Map.Entry<Integer, Troncon> troncon : listeTroncons.entrySet()) 
			{
				departX = troncon.getValue().getIntersectionDepart().getX();
				departY = troncon.getValue().getIntersectionDepart().getY();
				arriveeX = troncon.getValue().getIntersectionArrive().getX();
				arriveeY = troncon.getValue().getIntersectionArrive().getY();
				
				minX = Math.min(departX, arriveeX);
				maxX = Math.max(departX, arriveeX);
				minY = Math.min(departY, arriveeY);
				maxY = Math.max(departY, arriveeY);
				
				a = (arriveeX - departX) / (arriveeY - departY);				
				b = departX - a * departY;
			
				if (a * point.getY() + b < point.getX() + tolerance * Math.pow(1.5, Math.abs(a)) && a * point.getY() + b > point.getX() - tolerance * Math.pow(1.5, Math.abs(a)) &&
						point.getX() <= maxX + tolerance && point.getX() >= minX - tolerance && point.getY() <= maxY + tolerance && point.getY() >= minY - tolerance)
				{
					selectedTroncon = troncon.getValue();
				}
			}
		}
		
		setChanged();
		notifyObservers();
	}
	
	public void getLivraison (Livraison livraison)
	{
		selectedIntersection = livraison.getIntersection();
		
		setChanged();
		notifyObservers();
	}
	
	public void getEntrepot (Intersection entrepot)
	{
		selectedIntersection = entrepot;
		
		setChanged();
		notifyObservers();
	}
	
	public void resetBounds()
	{
		xMax = 0;
		yMax = 0;
		xMin = 999999;
		yMin = 999999;
		
		for (Map.Entry<Long, Intersection> mapentry : listeIntersection.entrySet()) 
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
		
		setChanged();
		notifyObservers();
	}
	
	public int getXMin()
	{
		return xMin;
	}
	public int getXMax()
	{
		return xMax;
	}
	public int getYMin()
	{
		return yMin;
	}
	public int getYMax()
	{
		return yMax;
	}
}
