package tests;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import algorithme.Dijkstra;
import modeles.*;
import controleur.*;
import donnees.*;

/* Il faut télécharger la librairie JUnit pour faire fonctionner cette classe de test */

public class testDijkstraa {
	
	testDijkstraa() {
		
	}
	
	/* variables globales */
	private final static String cheminPetitLyon = "./data/plan/planLyonPetit.xml" ; // mot clé final car valeur inchangée.
	private final static String cheminMoyenLyon = "./data/plan/planLyonMoyen.xml" ;
	private final static String cheminGrandLyon = "./data/plan/planLyonGrand.xml" ;
	private static Plan petitLyon ;
	private static Plan moyenLyon ;
	private static Plan grandLyon ;
	private static Intersection pointDeDepartPetit;
	private static Intersection pointDeDepartMoyen;
	private static Intersection pointDeDepartGrand;
	private static Dijkstra dPetit;
	private static Dijkstra dMoyen;
	private static Dijkstra dGrand;
	
	/* test initialisation des HashMap pi, cout, intersectionBlanches, intersectionsGrises, intersectionsGrisesInversees */
	
	@BeforeEach
	public void Initialisation() throws ParseurException {
		
		XMLParseur parseur = new XMLParseur();
		petitLyon = new Plan();
		moyenLyon = new Plan();
		grandLyon = new Plan();
		
		parseur.chargerPlan(petitLyon,cheminPetitLyon);
		
		parseur.chargerPlan(moyenLyon,cheminMoyenLyon);
		
		parseur.chargerPlan(grandLyon,cheminGrandLyon);
		
		Random       random    = new Random();
		List<Long> keys      = new ArrayList<Long>(petitLyon.getListeIntersection().keySet());
		Long       randomKey = keys.get( random.nextInt(keys.size()) );
		pointDeDepartPetit     = petitLyon.getListeIntersection().get(randomKey);
		//System.out.println(pointDeDepartPetit.toString());
		
		Random       random2    = new Random();
		List<Long> keys2     = new ArrayList<Long>(moyenLyon.getListeIntersection().keySet());
		Long       randomKey2 = keys2.get( random2.nextInt(keys2.size()) );
		pointDeDepartMoyen     = moyenLyon.getListeIntersection().get(randomKey2);
		//System.out.println(pointDeDepartMoyen.toString());
		
		Random       random3    = new Random();
		List<Long> keys3      = new ArrayList<Long>(grandLyon.getListeIntersection().keySet());
		Long       randomKey3 = keys3.get( random3.nextInt(keys3.size()) );
		pointDeDepartGrand     = grandLyon.getListeIntersection().get(randomKey3);
		//System.out.println(pointDeDepartGrand.toString());
		
		dPetit = new Dijkstra(petitLyon,pointDeDepartPetit);
		
		dMoyen = new Dijkstra(moyenLyon,pointDeDepartMoyen);
		
		dGrand = new Dijkstra(grandLyon,pointDeDepartGrand);
			
	}
	
	
	@Test
	public void testInitialisationMaps() {
		
		boolean returnValue = true;
				
		// check pi
		for(Long key : petitLyon.getListeIntersection().keySet()) {
	        if(!dPetit.getPi().containsKey(key)) {
	        	returnValue = false;
	        	break;
	        }
        }
		assertTrue("testInitialisationMaps échoué : pi mal initialisée", returnValue);
		if(returnValue==false) return ;
		
		//check cout
		for(Long key : petitLyon.getListeIntersection().keySet()) {							
	        if(!dPetit.getCout().containsKey(key)) {
	        	returnValue = false;
	        	break;
	        }
	        if(dPetit.getCout().get(key)<10000 && key!= pointDeDepartPetit.getId()) /* car le cout du pdp est init à 0 */ {
	        	returnValue = false;
	        	break;
	        }
	        if(key== pointDeDepartPetit.getId() && dPetit.getCout().get(key)!=0) {
	        	returnValue = false;
	        	break;
	        }
        }
		assertTrue("testInitialisationMaps échoué : cout mal initialisée", returnValue);
		if(returnValue==false) return ;
		
		// check intersectionsBlanches
		for(Long key : petitLyon.getListeIntersection().keySet()) {
	        if(!dPetit.getIntersectionsBlanches().containsKey(key) && key!= pointDeDepartPetit.getId()) /* car on retire le pdp de la map à l'init */ {
	        	returnValue = false;
	        	break;
	        }
	        if(dPetit.getCout().get(key)<10000 && key!= pointDeDepartPetit.getId()) /* car on retire le pdp de la map à l'init */ {
	        	returnValue = false;
	        	break;
	        }
	    }
		if(dPetit.getIntersectionsBlanches().containsKey(pointDeDepartPetit.getId())) {
        	returnValue = false;
        }
		assertTrue("testInitialisationMaps échoué : intersectionsBlanches mal initialisée", returnValue);
		if(returnValue==false) return ;
		
		// check intersectionsGrises
		if(dPetit.getIntersectionsGrises().size()!=1 || dPetit.getIntersectionsGrises().get(pointDeDepartPetit.getId())==null ) returnValue = false;
		assertTrue("testInitialisationMaps échoué : intersectionsGrises mal initialisée", returnValue);
		if(returnValue==false) return ;
		
		// check intersectionsGrisesInversees
		//List<Long> aAjouter = new ArrayList<Long>();
		//aAjouter.add(pointDeDepartPetit.getId());
		if(dPetit.getIntersectionsGrisesInversees().size()!=1 /*| dPetit.getIntersectionsGrisesInversees().get(0.0)!=aAjouter*/ ) returnValue = false;
		assertTrue("testInitialisationMaps échoué : intersectionsGrisesInversees mal initialisée", returnValue);
		if(returnValue==false) return ;
		
	}
	
	
	
	@Test
	public void testRelacher() {
		
		Intersection i1 = new Intersection();
		Intersection i2 = new Intersection();
		Troncon leTroncon = null;
		boolean returnValue = true;
		
		while(leTroncon==null){
			Random       randomR1    = new Random();
			List<Long> keysR1      = new ArrayList<Long>(petitLyon.getListeIntersection().keySet());
			Long       randomKeyR1 = keysR1.get( randomR1.nextInt(keysR1.size()) );
			i1     = petitLyon.getListeIntersection().get(randomKeyR1);
			
			Random       randomR2    = new Random();
			List<Long> keysR2      = new ArrayList<Long>(petitLyon.getListeIntersection().keySet());
			Long       randomKeyR2 = keysR2.get( randomR2.nextInt(keysR2.size()) );
			i2     = petitLyon.getListeIntersection().get(randomKeyR2);
			
			List<Troncon> lesTroncons = i1.getTronconsSortants();
			for (Troncon t : lesTroncons) {
				if (t.getIntersectionArrive().getId() == i2.getId()) {	
					if(leTroncon == null) {
						leTroncon = t;					
					}
					else if(t.getLongueur() < leTroncon.getLongueur()) {
						leTroncon = t;
					}

				}
			}
		}
		
		Random r = new Random();
		double randomValue = 0.0 + (100.0 - 0.0) * r.nextDouble(); // valeur de cout random entre 0 et 100. En soi, les bornes ne changent rien, c'est plus dans une optique de logique
		dPetit.getCout().put(i1.getId(), randomValue) ; // pour que l'exécution de relacher soit logique car la valeur du cout de i1 est à l'infini à l'origine.
		dPetit.relacher(i1,i2);
		if(dPetit.getCout().get(i2.getId()) > dPetit.getCout().get(i1.getId()) + leTroncon.getLongueur()) returnValue = false;
		assertTrue("testRelacher échoué", returnValue);
				
	}
	
	
	
	@Test
	public void testGetItineraire() {
		
		/* 	private List<Troncon> troncons;
	private Intersection depart;
	private Intersection arrivee;
	private double longueur; */
		
		/* Il faut vérifier que l'itinéraire entre le pt de départ (paramètre de l'objet Dijkstra) et le point d'arrivée (paramètre de la méthode getItineraire) 
		 * est bien logique et bien formé.
		 */
		
		
		/* On génère une intersection d'arrivée random */ 
		Intersection iArrivee;
		Random       randomR1    = new Random();
		List<Long> keysR1      = new ArrayList<Long>(petitLyon.getListeIntersection().keySet());
		Long       randomKeyR1 = keysR1.get( randomR1.nextInt(keysR1.size()) );
		iArrivee     = petitLyon.getListeIntersection().get(randomKeyR1);

		boolean boolTest = true;
		
		Itineraire itineraireTest = dPetit.getItineraire(iArrivee.getId());
		
		if(itineraireTest != null) {
			List<Troncon> listeTroncons = itineraireTest.getTroncons();
			System.out.println(listeTroncons.toString());
			Troncon tronconCourant = listeTroncons.get(0);
			Troncon tronconSuivant = listeTroncons.get(1);
			
			if(listeTroncons.get(listeTroncons.size()-1).getIntersectionArrive() != iArrivee) boolTest = false ;
			else {
				for (Troncon t : listeTroncons) {
					if(tronconCourant.getIntersectionArrive() != tronconSuivant.getIntersectionDepart()) {
						boolTest = false;
						break;
					}
					tronconCourant=tronconSuivant;
					tronconSuivant = listeTroncons.get(listeTroncons.indexOf(t)+2);
					
				
				}
			
			}
			
		}
		
		
		assertTrue("testGetItineraire échoué", boolTest);
		
		
	}
	
	
	
	@AfterEach
	public void clean() {
		petitLyon = null ;
		moyenLyon = null ;
		grandLyon = null ;
		pointDeDepartPetit = null ;
		pointDeDepartMoyen = null ;
		pointDeDepartGrand = null ;
		dPetit = null ;
		dMoyen = null ;
		dGrand = null ;
		
	}

}




