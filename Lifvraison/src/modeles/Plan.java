package modeles;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;

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
	
	public void ajouterIntersection(Intersection aAjouter)
	{
		this.listeIntersection.put(aAjouter.getId(), aAjouter);
	}
	

	public void ajouterIntersection(Long id, int x, int y)
	{
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
		String chaineDeRetour = " Plan : \n" ;
		 for (Map.Entry mapentry : listeIntersection.entrySet()) {
	           chaineDeRetour+= "id: "+mapentry.getKey() 
	                              + " | valeur: " + mapentry.getValue() + "\n";
	        }
		return chaineDeRetour;
		 //return "Plan [listeIntersection=" + listeIntersection + ", \nlisteTroncons=" + listeTroncons + "]";
	}

	
}
