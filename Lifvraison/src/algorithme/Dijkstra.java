package algorithme;
import modeles.Intersection;
import modeles.Plan; 
import modeles.Troncon;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Dijkstra {
	
	//Contient les intersection associées à leur antécédent
	private HashMap<Long, Intersection> pi;
	//Contient les id des intersections associées à leur cout
	private HashMap<Long, Double> cout;
	
	//Contient les intersections blanches, associées à leur cout
	private HashMap<Long, Double> intersectionsBlanches;
	//Contient les intersections grises, associées à leur cout
	private HashMap<Long, Double> intersectionsGrises;
	//Inverse la map intersectionsGrises
	private HashMap<Double, List<Long> > intersectionsGrisesInversees;
	
	//L'objet Plan de notre cas d'étude
	Plan lePlan;
	//Intersection d'où l'on part
	Intersection ptDepart;
	
	/**
	 * Initialise les différentes map et conteneurs avant de lancer run()
	 * 
	 * @param lePlan
	 * @param pointDepart
	 */
	Dijkstra(Plan lePlan, Intersection pointDepart)
	{
		this.lePlan = lePlan;
		this.ptDepart = pointDepart;
		
		Collection<Intersection> c = lePlan.getListeIntersection().values();
		
		//On ajoute les intersections dans les differentes Hashmap
		for(Intersection intersection : c )
		{
			pi.put(intersection.getId(), null);
			cout.put(intersection.getId(), Double.MAX_VALUE);
			intersectionsBlanches.put(intersection.getId(), Double.MAX_VALUE);
		}
		
		//On met le point de départ dans les maps grises et on l'enleve de la map intersectionsBlanches
		cout.put(pointDepart.getId(), 0.0);
		intersectionsBlanches.remove(pointDepart.getId());
		intersectionsGrises.put(pointDepart.getId(), 0.0);
		List<Long> aAjouter = new ArrayList<Long>();
		aAjouter.add(pointDepart.getId());
		intersectionsGrisesInversees.put(0.0, aAjouter);
		
	}
	/**
	 * Execute la méthode de Dijkstra
	 */
	public void run()
	{
		//On continue tant que l'on a des intersections grises
		while(!intersectionsGrises.isEmpty()) {
			double min = Collections.min(intersectionsGrises.values());
			Long idMin = intersectionsGrisesInversees.get(min).get(0);
			
			Intersection lIntersection = lePlan.getListeIntersection().get(idMin);
			
			//On visite tous les successeurs du point courant
			for(Troncon t : lIntersection.getTronconsSortants()) 
			{
				
				//On n'agit que sur les intersections blanches ou grises
				if(intersectionsBlanches.containsKey(t.getIntersectionArrive().getId()) || intersectionsGrises.containsKey(t.getIntersectionArrive().getId()))
				{
					
					//On relache l'arc en le point courant et le successeur que l'on est en train de visiter
					relacher(t.getIntersectionDepart(),t.getIntersectionArrive());
					
					//On colorie le nouveau sommet visité en gris
					if(intersectionsBlanches.containsKey(t.getIntersectionArrive().getId())) 
					{
						//On l'ajoute dans la map "à l'endroit"
						intersectionsGrises.put(t.getIntersectionArrive().getId(), cout.get(t.getIntersectionArrive().getId()));
						
						//On l'ajoute à la map inversé, en vérifiant si la valeur de cout existe
						if(intersectionsGrisesInversees.containsKey(min)) 
						{
							intersectionsGrisesInversees.get(min).add(t.getIntersectionArrive().getId());
						} 
						else 
						{
							List<Long> aAjouter = new ArrayList<Long>();
							aAjouter.add(t.getIntersectionArrive().getId());
							intersectionsGrisesInversees.put(cout.get(t.getIntersectionArrive().getId()), aAjouter);
						}
						
						//On enleve l'intersection des intersections blanches, vu qu'elle vient d'etre coloriée en grise
						intersectionsBlanches.remove(t.getIntersectionArrive().getId());
							
					}
				}
				
				//Il faut maintenant ajouter le sommet aux intersections noires (ici, il suffit qu'il ne soit dans aucune map contenant les intersections grises ou blanches
				intersectionsGrises.remove(idMin);
				
				if(intersectionsGrisesInversees.get(min).size() != 1)
				{
					intersectionsGrisesInversees.get(min).remove(idMin);
				}
				else
				{
					intersectionsGrisesInversees.remove(min);
				}
				
				
			}
		}
		
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
			cout.put(sj.getId(), cout.get(si.getId()) + leTroncon.getLongeur());
			pi.put(sj.getId(), si);
		}
	}
}
