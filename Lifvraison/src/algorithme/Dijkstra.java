package algorithme;

import modeles.Intersection;
import modeles.Plan;
import modeles.Troncon;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import modeles.Itineraire;

import donnees.XMLParseur;

public class Dijkstra {

	// Contient les intersection associees a leur antecedent
	private HashMap<Long, Intersection> pi;
	// Contient les id des intersections associees a leur cout
	private HashMap<Long, Double> cout;

	// Contient les intersections blanches, associees a leur cout
	private HashMap<Long, Double> intersectionsBlanches;
	// Contient les intersections grises, associees a leur cout
	private HashMap<Long, Double> intersectionsGrises;
	// Inverse la map intersectionsGrises
	private HashMap<Double, List<Long>> intersectionsGrisesInversees;

	// L'objet Plan de notre cas d'etude
	private Plan lePlan;
	// Intersection d'ou l'on part
	private Intersection ptDepart;

	/**
	 * Initialise les différentes map et conteneurs avant de lancer run()
	 * 
	 * @param lePlan
	 * @param pointDepart
	 */
	Dijkstra(Plan lePlan, Intersection pointDepart) {
		this.lePlan = lePlan;
		this.ptDepart = pointDepart;
		
		pi = new HashMap<Long,Intersection>();
		cout = new HashMap<Long,Double>();
		intersectionsBlanches = new HashMap<Long, Double>();
		intersectionsGrises = new HashMap<Long, Double>();
		intersectionsGrisesInversees = new HashMap<Double, List<Long>>();
		
		Collection<Intersection> c = lePlan.getListeIntersection().values();
		int i = 0;
		// On ajoute les intersections dans les differentes Hashmap
		for (Intersection intersection : c) {
			pi.put(intersection.getId(), null);
			cout.put(intersection.getId(), Double.MAX_VALUE);
			intersectionsBlanches.put(intersection.getId(), Double.MAX_VALUE);
		}

		// On met le point de départ dans les maps grises et on l'enleve de la map
		// intersectionsBlanches
		cout.put(pointDepart.getId(), 0.0);
		intersectionsBlanches.remove(pointDepart.getId());
		intersectionsGrises.put(pointDepart.getId(), 0.0);
		System.out.println(intersectionsGrises.values().size());
		List<Long> aAjouter = new ArrayList<Long>();
		aAjouter.add(pointDepart.getId());
		intersectionsGrisesInversees.put(0.0, aAjouter);

	}

	/**
	 * Execute la méthode de Dijkstra
	 */
	public void run() {
		// On continue tant que l'on a des intersections grises
		while (intersectionsGrises.values().size() != 0) {
			double min = Collections.min(intersectionsGrises.values());
			Long idMin = intersectionsGrisesInversees.get(min).get(0);
			
			Intersection lIntersection = lePlan.getListeIntersection().get(idMin);

			// On visite tous les successeurs du point courant
			for (Troncon t : lIntersection.getTronconsSortants()) {

				// On n'agit que sur les intersections blanches ou grises
				if (intersectionsBlanches.containsKey(t.getIntersectionArrive().getId()) || intersectionsGrises.containsKey(t.getIntersectionArrive().getId())) {

					// On relache l'arc en le point courant et le successeur que l'on est en train
					// de visiter
					relacher(t.getIntersectionDepart(), t.getIntersectionArrive());
					// On colorie le nouveau sommet visite en gris
					if (intersectionsBlanches.containsKey(t.getIntersectionArrive().getId())) {
						// On l'ajoute dans la map "a l'endroit"
						intersectionsGrises.put(t.getIntersectionArrive().getId(),cout.get(t.getIntersectionArrive().getId()));

						// On l'ajoute à la map inverse, en verifiant si la valeur de cout existe
						if (intersectionsGrisesInversees.containsKey(cout.get(t.getIntersectionArrive().getId()))) {
							intersectionsGrisesInversees.get(cout.get(t.getIntersectionArrive().getId())).add(t.getIntersectionArrive().getId());
						} else {
							List<Long> aAjouter = new ArrayList<Long>();
							aAjouter.add(t.getIntersectionArrive().getId());
							intersectionsGrisesInversees.put(cout.get(t.getIntersectionArrive().getId()), aAjouter);
						}

						// On enleve l'intersection des intersections blanches, vu qu'elle vient d'etre
						// coloriee en grise
						intersectionsBlanches.remove(t.getIntersectionArrive().getId());

					}
				}

			}

			// Il faut maintenant ajouter le sommet aux intersections noires (ici, il suffit
			// qu'il ne soit dans aucune map contenant les intersections grises ou blanches
			intersectionsGrises.remove(idMin);

			if (intersectionsGrisesInversees.get(min).size() != 1) {
				intersectionsGrisesInversees.get(min).remove(idMin);
			} else {
				intersectionsGrisesInversees.remove(min);
			}
		}

	}

	private void relacher(Intersection si, Intersection sj) {

		List<Troncon> lesTroncons = si.getTronconsSortants();
		Troncon leTroncon = null;
		for (Troncon t : lesTroncons) {
			if (t.getIntersectionArrive().getId() == sj.getId()) {
				if(leTroncon == null) {
					leTroncon = t;					
				}
				else if(t.getLongueur() < leTroncon.getLongueur()) {
					leTroncon = t;
				}

			}
		}
		//System.out.println("id : " + sj.getId());
		//System.out.println(cout.get(sj.getId()));
		
		Double valeurATester = cout.get(si.getId()) + leTroncon.getLongueur();
		
		if(cout.get(sj.getId()) == Double.MAX_VALUE) {
			cout.put(sj.getId(), valeurATester);
			pi.put(sj.getId(), si);
		}
		else if (cout.get(sj.getId()) > valeurATester) {
			cout.put(sj.getId(), valeurATester);
			pi.put(sj.getId(), si);
		}
	}

	public Itineraire getItineraire(Long idArrivee) {
		Itineraire itineraire = null;

		if (lePlan.getListeIntersection().containsKey(idArrivee)) {
			Intersection intersectionArrivee = lePlan.getListeIntersection().get(idArrivee);
			Intersection intersectionCourante = lePlan.getListeIntersection().get(idArrivee);
			List<Troncon> cheminInverse = new ArrayList<Troncon>();

			while (pi.get(intersectionCourante.getId()) != null) {
				Intersection intersectionPrecedente = intersectionCourante;
				intersectionCourante = pi.get(intersectionCourante.getId());

				int tailleChemin = cheminInverse.size();

				List<Troncon> listeTroncon = intersectionCourante.getTronconsSortants();
				if(listeTroncon != null) {
					for (Troncon t : listeTroncon) {
						if (t.getIntersectionArrive().getId() == intersectionPrecedente.getId()) {
							cheminInverse.add(t);
							break;
						}
					}
				}
				// On verifie qu'il n'y a pas eu de probleme et que l'on a bien un nouveau
				// troncon
				if (tailleChemin == cheminInverse.size()) {
					return null;
				}
			}
			
			if(cheminInverse.size() == 0) {
				return null;
			}
			List<Troncon> chemin = new ArrayList<Troncon>();
			for (int i = cheminInverse.size() - 1; i != -1; i--) {
				chemin.add(cheminInverse.get(i));
			}
			itineraire = new Itineraire(chemin, ptDepart, intersectionArrivee);
		}
		return itineraire;
	}

	public HashMap<Long, Intersection> getPi() {
		return pi;
	}

	public Intersection getPrecedent(Long id) {
		return pi.get(id);
	}

	public void setPi(HashMap<Long, Intersection> pi) {
		this.pi = pi;
	}

	public HashMap<Long, Double> getCout() {
		return cout;
	}

	public Double getCoutIntersection(Long id) {
		return cout.get(id);
	}

	public void setCout(HashMap<Long, Double> cout) {
		this.cout = cout;
	}

	public HashMap<Long, Double> getIntersectionsBlanches() {
		return intersectionsBlanches;
	}

	public void setIntersectionsBlanches(HashMap<Long, Double> intersectionsBlanches) {
		this.intersectionsBlanches = intersectionsBlanches;
	}

	public HashMap<Long, Double> getIntersectionsGrises() {
		return intersectionsGrises;
	}

	public void setIntersectionsGrises(HashMap<Long, Double> intersectionsGrises) {
		this.intersectionsGrises = intersectionsGrises;
	}

	public HashMap<Double, List<Long>> getIntersectionsGrisesInversees() {
		return intersectionsGrisesInversees;
	}

	public void setIntersectionsGrisesInversees(HashMap<Double, List<Long>> intersectionsGrisesInversees) {
		this.intersectionsGrisesInversees = intersectionsGrisesInversees;
	}

	public Plan getLePlan() {
		return lePlan;
	}

	public void setLePlan(Plan lePlan) {
		this.lePlan = lePlan;
	}

	public Intersection getPtDepart() {
		return ptDepart;
	}

	public void setPtDepart(Intersection ptDepart) {
		this.ptDepart = ptDepart;
	}
	
	public static void main (String[] args)
	{
		XMLParseur xml = new XMLParseur();


		Plan lePlan = xml.chargerPlan("data\\planLyonPetit.xml");
		Long id = new Long(1029591870);

		Dijkstra d = new Dijkstra(lePlan, lePlan.getListeIntersection().get( id ));
		d.run();
		
		Long arrivee = new Long(1025933218);
		Itineraire chemin = d.getItineraire(arrivee);
		
		List<Troncon> t = chemin.getTroncons();
		
		for(Troncon troncon : t) {
			System.out.println("depart :" + troncon.getIntersectionDepart().getId()+ "; arrivee :" + troncon.getIntersectionArrive().getId() + "; nom de rue :" + troncon.getNomDeRue() + "; Longueur : " + troncon.getLongueur());
		}
	}

}


