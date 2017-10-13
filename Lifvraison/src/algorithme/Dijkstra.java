package algorithme;
import modeles.Intersection;
import modeles.Plan; 
import java.util.HashMap;
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
		
		Collection<Intersection> c = lePlan.getIntersection().values();
		
		for(Intersection intersection : c )
		{
			//TODO : Ajouter les noeuds à pi et cout et initialiser les valeurs
		}
	}
	
	public void run()
	{
		//TODO : Methode de Dijkstra
	}
	
	public void relacher(Intersection si, Intersection sj)
	{
		//TODO : Gerer le relachement des axes
	}
}
