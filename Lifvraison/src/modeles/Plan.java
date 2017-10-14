package modeles;
import java.util.HashMap;

public class Plan {

	private HashMap<Long, Intersection> listeIntersection;
	private HashMap<Integer, Troncon> listeTroncons;

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
	
	public void ajouterTroncons(String nomDeRue, Long idDepart, Long idArrivee, double longeur)
	{
		this.listeTroncons.put((int) (idDepart/2+idArrivee/2), new Troncon(nomDeRue, idDepart, idArrivee, longeur));
	}

	
}
