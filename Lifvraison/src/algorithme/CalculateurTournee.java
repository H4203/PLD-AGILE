package algorithme;


import modeles.*;
import java.util.List;
import java.util.ArrayList;
import tsp.*;
import donnees.XMLParseur;

public class CalculateurTournee {

	private Tournee laTournee;
	private List<Itineraire> lesItineraires;

	
	public CalculateurTournee(Tournee laTournee){
		this.laTournee = laTournee;
		lesItineraires = new ArrayList<Itineraire>();
	}
	
	public void run() 
	{
		Plan lePlan = laTournee.getPlan();
		
		DemandeLivraison dl = laTournee.getDemandeLivraison();
		List<Livraison> livraisonsOrdonnees = new ArrayList<Livraison>();
		List<Livraison> livraisons = dl.getLivraisons();
		
		List<Intersection> intersections = new ArrayList<Intersection>();
		intersections.add(laTournee.getDemandeLivraison().getEntrepot());
		for(Livraison l : livraisons) {
			intersections.add(l.getIntersection());
		}
		
		int[][] coutTsp = new int[intersections.size()][intersections.size()];
		int[] duree = new int[intersections.size()];
		
		List<Dijkstra> dijkstra = new ArrayList<Dijkstra>();
		
		
		for(int i = 0; i< intersections.size(); i++) {
			Intersection lIntersection = intersections.get(i);
			Dijkstra d = new Dijkstra(lePlan, lIntersection);
			d.run();
			if(i == 0)
			{
				duree[0] = 0;
			}
			else {
			duree[i] = livraisons.get(i-1).getDureeDechargement();}
			for(int j = 0; j < intersections.size(); j++) {
				if(j == i) {
					coutTsp[i][j] = 0;
				}
				else
				{
					coutTsp[i][j] = (int)  d.getItineraire(intersections.get(j).getId()).getLongueur();
				}
			}
			dijkstra.add(d);
		}
		
		TSP1 tsp = new TSP1();
		tsp.chercheSolution(Integer.MAX_VALUE, intersections.size(), coutTsp, duree);
		int sommetCourant = 0;
		livraisonsOrdonnees.add(livraisons.get(sommetCourant));
		for(int i = 1; i < intersections.size(); i++) {
			int prochainSommet = tsp.getMeilleureSolution(i);
			livraisonsOrdonnees.add(livraisons.get(prochainSommet-1));
			lesItineraires.add(dijkstra.get(sommetCourant).getItineraire(intersections.get(prochainSommet).getId()));
			sommetCourant = prochainSommet;
		}
		lesItineraires.add(dijkstra.get(sommetCourant).getItineraire(intersections.get(0).getId()));
		laTournee.setLivraisonsOrdonnees(livraisonsOrdonnees);
		laTournee.setListeItineraires(lesItineraires);
	}
	
	public List<Itineraire> getLesItineraires() {
		return lesItineraires;
	}

	/*public static void main (String[] args)
	{
		XMLParseur xml = new XMLParseur();
		Plan lePlan = xml.chargerPlan("data/testPlan1.xml");
		DemandeLivraison dl = xml.chargerLivraison("data/testDL1_1.xml",lePlan.getListeIntersection());
		
		Tournee laTournee = new Tournee(lePlan, dl, new ArrayList<Itineraire>());
		
		CalculateurTournee cl = new CalculateurTournee(laTournee);
		cl.run();
		
		List<Itineraire> itineraires = cl.getLesItineraires();
		for(Itineraire i : itineraires) {
			System.out.println("-----------------------------------------");
			List<Troncon> lesTroncons = i.getTroncons();
			for(Troncon troncon : lesTroncons) {
				System.out.println("depart :" + troncon.getIntersectionDepart().getId()+ "; arrivee :" + troncon.getIntersectionArrive().getId() + "; nom de rue :" + troncon.getNomDeRue() + "; Longueur : " + troncon.getLongeur());
			}
			
		}
	}*/

}
