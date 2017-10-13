package modeles;
import java.util.HashMap;

public class Plan {

	private HashMap<Integer, Intersection> listeIntersection;
	private HashMap<Integer, Troncon> listeTroncons;

	public Plan(HashMap<Integer, Intersection> listeIntersection, HashMap<Integer, Troncon> listeTroncons) {
		this.listeIntersection = listeIntersection;
		this.listeTroncons = listeTroncons;
	}

	public HashMap<Integer, Intersection> getListeIntersection() {
		return listeIntersection;
	}

	public void setListeIntersection(HashMap<Integer, Intersection> listeIntersection) {
		this.listeIntersection = listeIntersection;
	}
	
	public void ajouterIntersection(Intersection aAjouter)
	{
		this.listeIntersection.put(aAjouter.getId(), aAjouter);
	}
	
	public void ajouterTroncons() {
		
	}
	
}
