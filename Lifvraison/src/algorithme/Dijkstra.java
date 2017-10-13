package algorithme;
import modeles.Intersection;
import modeles.Plan; 
import modeles.Troncon;
import java.util.HashMap;
import java.util.List;
import java.util.Collection;

public class Dijkstra {
	private HashMap<Integer, Intersection> pi;
	private HashMap<Integer, Integer> cout;
	
	Plan lePlan;
	Intersection ptDepart;
	
	Dijkstra(Plan lePlan, Intersection pointDepart)
	{
		this.lePlan = lePlan;
		this.ptDepart = pointDepart;
		
		Collection<Intersection> c = lePlan.getListeIntersection().values();
		
		for(Intersection intersection : c )
		{
			//TODO : Ajouter les noeuds à pi et cout et initialiser les valeurs
			pi.put(intersection.getId(),intersection);
			cout.put(intersection.getId(), Integer.MAX_VALUE);
		}
	}
	
	public void run()
	{
		//TODO : Methode de Dijkstra
	}
	
	public void relacher(Intersection si, Intersection sj)
	{
		
		
		List<Troncon> lesTroncons = si.getTronconsSortants();
		Troncon leTroncon = null;
		
		for(Troncon t : lesTroncons) {
			if(t.getIntersectionArrive().getId() == sj.getId()) {
				leTroncon = t;
			}
		}
		
		
		if(  (cout.get(sj.getId()) > (cout.get(si.getId()) + leTroncon.getLongeur() )) && (leTroncon != null)  ) {
		
		}
	}
}
