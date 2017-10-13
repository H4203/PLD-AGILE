package modeles;
import java.util.HashMap;

public class Plan {

	private HashMap<Long, Intersection> listeIntersection;
	private HashMap<Integer, Troncon> listeTroncons;

	public Plan() {
		this.listeIntersection = null;
		this.listeTroncons = null;
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
	
<<<<<<< HEAD
=======
	public void ajouterIntersection(Long id, int x, int y)
	{
		this.listeIntersection.put( id, new Intersection(id,  x,  y) );
	}
	
	public void ajouterTroncons() {
		
	}
>>>>>>> 419e022b30e451a39b5bbd5d75896f7defacf846
	
}
