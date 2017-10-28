package modeles;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import donnees.ParseurException;

public class Plan extends Observable
{
	private final int clickSelectionTolerance = 100;
	
	private HashMap<Long, Intersection> listeIntersection;
	private HashMap<Integer, Troncon> listeTroncons;
	private Intersection selectedIntersection;

	private int idTroncon = 1;
	
	// Point Focused
	// (0, 0) : center
	private Point focus;
	private double zoom;
	
	public Plan() 
	{
		this.listeIntersection = new HashMap<Long, Intersection>();
		this.listeTroncons = new HashMap<Integer, Troncon>();
		
		focus = new Point(0, 0);
		zoom = 1.0;
		
		notifyObservers();
	}
	
	public Plan(HashMap<Long, Intersection> listeIntersection, HashMap<Integer, Troncon> listeTroncons) {
		this.listeIntersection = listeIntersection;
		this.listeTroncons = listeTroncons;
		
		focus = new Point(0, 0);
		
		notifyObservers();
	}
	
	public void reset()
	{
		listeIntersection.clear();
		listeTroncons.clear();
		resetFocus();
	}

	public HashMap<Long, Intersection> getListeIntersection() {
		return listeIntersection;
	}

	public void setListeIntersection(HashMap<Long, Intersection> listeIntersection) {
		this.listeIntersection = listeIntersection;
		resetFocus();
		notifyObservers();
	}
	
	public void ajouterIntersection(Intersection aAjouter) throws ParseurException
	{
		if ( listeIntersection.containsKey(aAjouter.getId()) )
		{
			throw new ParseurException ("L'id"+ aAjouter.getId() +"est en double...");
		}
		this.listeIntersection.put(aAjouter.getId(), aAjouter);
	}
	

	public void ajouterIntersection(Long id, int x, int y) throws ParseurException
	{
		if ( listeIntersection.containsKey(id) )
		{
			throw new ParseurException ("L'id"+ id +"est en double...");
		}
		this.listeIntersection.put( id, new Intersection(id,  x,  y) );
		
		notifyObservers();
	}
	
	public void  ajouterTroncon(String nomDeRue, Intersection intersectionDepart, Intersection intersectionArrivee, double longueur)
	{
		Troncon troncon = new Troncon(nomDeRue, intersectionDepart, intersectionArrivee, longueur);
		this.listeTroncons.put(idTroncon, troncon);
		idTroncon = idTroncon + 1;
		/* on met a jour les valeurs des listes de troncons */
		intersectionDepart.addTronconSortant(troncon);
		intersectionArrivee.addTronconEntrant(troncon);
		
		notifyObservers();
	}
	
	public HashMap<Integer, Troncon> getListeTroncons() {
		return listeTroncons;
	}

	public Point getFocus()
	{
		return focus;
	}
	
	public void setListeTroncons(HashMap<Integer, Troncon> listeTroncons) {
		this.listeTroncons = listeTroncons;
		
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
	
	public void getAtPoint(Point point)
	{
		
		for (Map.Entry<Long, Intersection> mapentry : listeIntersection.entrySet()) 
		{
			//System.out.println(mapentry.getValue().getX());
			//System.out.println(mapentry.getValue().getY());
			
			if (point.getX() < mapentry.getValue().getX() + clickSelectionTolerance && point.getX() > mapentry.getValue().getX() - clickSelectionTolerance
					&& point.getY() < mapentry.getValue().getY() + clickSelectionTolerance && point.getY() > mapentry.getValue().getY() - clickSelectionTolerance) 
			{
				selectedIntersection = mapentry.getValue();
				
				//System.out.println(mapentry.getValue());
				
				setChanged();
				notifyObservers();
			}
		}
	}
	
	public void drag(Point delta)
	{
		focus = new Point(focus.x + delta.x, focus.y + delta.y);
		
		setChanged();
		notifyObservers();
	}
	
	public void zoom(int steps)
	{
		zoom = zoom + steps / 10.0;
		
		setChanged();
		notifyObservers();
	}
	
	public double getZoom()
	{
		return zoom;
	}
	
	public void resetFocus()
	{
		focus.setLocation(0, 0);
	}
}
