package modeles;
import java.util.HashMap;
import java.util.Observable;

import donnees.ParseurException;

public class Plan extends Observable
{
	private HashMap<Long, Intersection> listeIntersection;
	private HashMap<Integer, Troncon> listeTroncons;

	private int idTroncon = 1;
	
	public Plan() {
		this.listeIntersection = new HashMap<Long, Intersection>();
		this.listeTroncons =new HashMap<Integer, Troncon>();
	}
	
	public Plan(HashMap<Long, Intersection> listeIntersection, HashMap<Integer, Troncon> listeTroncons) {
		this.listeIntersection = listeIntersection;
		this.listeTroncons = listeTroncons;
	}

	public HashMap<Long, Intersection> getListeIntersection() {
		return listeIntersection;
	}

	public void setListeIntersection(HashMap<Long, Intersection> listeIntersection) {
		this.listeIntersection = listeIntersection;
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
	}
	
	public void  ajouterTroncon(String nomDeRue, Intersection intersectionDepart, Intersection intersectionArrivee, double longueur)
	{
		Troncon troncon = new Troncon(nomDeRue, intersectionDepart, intersectionArrivee, longueur);
		this.listeTroncons.put(idTroncon, troncon);
		idTroncon = idTroncon + 1;
		/* on met a jour les valeurs des listes de troncons */
		intersectionDepart.addTronconSortant(troncon);
		intersectionArrivee.addTronconEntrant(troncon);
	}
	
	public HashMap<Integer, Troncon> getListeTroncons() {
		return listeTroncons;
	}

	public void setListeTroncons(HashMap<Integer, Troncon> listeTroncons) {
		this.listeTroncons = listeTroncons;
	}

	@Override
	public String toString() {
		return "Plan [listeIntersection=" + listeIntersection + ", \nlisteTroncons=" + listeTroncons + "]";
	}

	
}
