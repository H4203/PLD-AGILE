package algorithme;


import modeles.*;
import java.util.List;
import java.util.ArrayList;
import tsp.*;
import donnees.XMLParseur;

public class CalculateurTournee {

	private Tournee laTournee;
	private List<Itineraire> lesItineraires;
	private List<Dijkstra> lesDijkstra;

	
	public CalculateurTournee(Tournee laTournee){
		this.laTournee = laTournee;
		lesItineraires = new ArrayList<Itineraire>();
		lesDijkstra = new ArrayList<Dijkstra>();
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
			this.lesDijkstra.add(d);
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
	
	public Tournee supprimerLivraison(Livraison livraison) {
		int index = -1;
		for(int i = 0; i < lesItineraires.size(); i++) {
			if(lesItineraires.get(i).getDepart().getId() == livraison.getIntersection().getId())
			{
				index = i;
			}
		}
		List<Itineraire> nouvelleTournee = new ArrayList<Itineraire>();
		if(index != -1) {
			for(int i = 0; i < index; i++) {
				nouvelleTournee.add(this.lesItineraires.get(i));
			}
			if(index != (this.lesItineraires.size()-1)) {
				nouvelleTournee.add(lesDijkstra.get(index-1).getItineraire(lesItineraires.get(index+1).getDepart().getId()));
				for(int i = index+1; i < lesItineraires.size()-1; i++) {
					nouvelleTournee.add(this.lesItineraires.get(i));
				}
			}
			this.lesItineraires = nouvelleTournee;
			this.lesDijkstra.remove(index);
			laTournee.setListeItineraires(lesItineraires);
			laTournee.getDemandeLivraison().getLivraisons().remove(index);
		}
		
		return this.laTournee;
	}
	
	public Tournee ajouterLivraison(Livraison livraison) {
		int index = -1;
		for(int i = 0; i < lesItineraires.size(); i++) {
			if(lesItineraires.get(i).getDepart().getId() == livraison.getIntersection().getId())
			{
				index = i;
			}
		}
		List<Itineraire> nouvelleTournee = new ArrayList<Itineraire>();
		List<Livraison> nouvellesLivraisons = new ArrayList<Livraison>();
		List<Dijkstra> nouveauxDijkstra = new ArrayList<Dijkstra>();
		if(index != -1) {
			for(int i = 0; i < index; i++) {
				nouveauxDijkstra.add(this.lesDijkstra.get(i));
				nouvelleTournee.add(this.lesItineraires.get(i));
				nouvellesLivraisons.add(laTournee.getLivraisonsOrdonnees().get(i));
			}
			
			nouvellesLivraisons.add(livraison);
			Dijkstra d = new Dijkstra(this.laTournee.getPlan(), livraison.getIntersection());
			d.run();
			nouveauxDijkstra.add(d);
			nouvelleTournee.add(this.lesDijkstra.get(index).getItineraire(livraison.getIntersection().getId()));
			nouvelleTournee.add(d.getItineraire(this.lesItineraires.get(index+1).getDepart().getId()));
			
			for(int i = index + 1; i < lesItineraires.size(); i++) {
				nouveauxDijkstra.add(this.lesDijkstra.get(i));
				nouvelleTournee.add(this.lesItineraires.get(i));
				nouvellesLivraisons.add(laTournee.getLivraisonsOrdonnees().get(i));
			}
			
			this.lesDijkstra = nouveauxDijkstra;
			this.lesItineraires = nouvelleTournee;
			this.laTournee.setListeItineraires(nouvelleTournee);
		}
		
		
		
		return this.laTournee;
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
