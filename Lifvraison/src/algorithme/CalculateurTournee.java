package algorithme;


import modeles.*;
import java.util.List;
import java.util.ArrayList;
import tsp.*;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;;

public class CalculateurTournee extends Thread{
	/**
	 * La tournee calculee par le tsp
	 */
	private Tournee laTournee;
	/**
	 * la liste des itineraires ranges (contenu aussi dans la tournee)
	 */
	private List<Itineraire> lesItineraires;
	/**
	 * la liste des plus court chemin par point
	 */
	private List<Dijkstra> lesDijkstra;
	
	private TSP3 tsp;

	
	public CalculateurTournee(Tournee laTournee)
	{
		this.laTournee = laTournee;
		lesItineraires = new ArrayList<Itineraire>();
		lesDijkstra = new ArrayList<Dijkstra>();
		this.tsp = new TSP3();
	}
	
	public void run() 
	{
		lesItineraires.clear();
		lesDijkstra.clear();
    
		Plan lePlan = laTournee.getPlan();
		
		DemandeLivraison dl = laTournee.getDemandeLivraison();
		List<Livraison> livraisonsOrdonnees = new ArrayList<Livraison>();
		List<Livraison> livraisons = dl.getLivraisons();
		
		//On stocke toutes les intersections par lesquels on souhaite passer
		List<Intersection> intersections = new ArrayList<Intersection>();
		//On commence par ajouter l'entrepot
		intersections.add(laTournee.getDemandeLivraison().getEntrepot());
		//On ajoute tous les endroits de livraison
		for(Livraison l : livraisons) {
			intersections.add(l.getIntersection());
		}
		
		//On initialise les tableaux cout et duree qui vont etre remplis par le tsp
		int[][] coutTsp = new int[intersections.size()][intersections.size()];
		int[] duree = new int[intersections.size()];
		
		int[] tempsDebutPlage = new int[intersections.size()];
		int[] tempsFinPlage = new int[intersections.size()];
		
		List<Dijkstra> dijkstra = new ArrayList<Dijkstra>();
		
		//On remplit, point par point, la matrice duree et la matrice cout
		for(int i = 0; i< intersections.size(); i++) {
			Intersection lIntersection = intersections.get(i);
			Dijkstra d = new Dijkstra(lePlan, lIntersection);
			//this.lesDijkstra.add(d);
			d.run();
			if(i == 0)
			{
				//La duree passee a l'entrepot est initialisee a 0
				duree[0] = 0;
				tempsDebutPlage[0] = 0;
				tempsFinPlage[0] = Integer.MAX_VALUE;
			}
			else {
				//get(i-1) car l'entrepot a decale toutes les livraisons dans la matrice intersections
				duree[i] = livraisons.get(i-1).getDureeDechargement();
				if(dl.getLivraisons().get(i-1).getPlagehoraire() != null) {
					tempsDebutPlage[i] = (int) ChronoUnit.SECONDS.between(dl.getHeureDepart(), livraisons.get(i-1).getPlagehoraire().getHeureDebut());
					tempsFinPlage[i] = (int) ChronoUnit.SECONDS.between(dl.getHeureDepart(), livraisons.get(i-1).getPlagehoraire().getHeureFin());
				}
				else {
					tempsDebutPlage[i] = 0;
					tempsFinPlage[i] = Integer.MAX_VALUE;
				}
				
			}
			
			//Grace au dijkstra, on remplit la matrice de cout, car on peut obtenir le plus court chemin du point courant vers tous les autres points
			for(int j = 0; j < intersections.size(); j++) {
				if(j == i) {
					//Pour simplifier l'optimisation de bound, nous initialisons la diagonale de cout a l'infini
					coutTsp[i][j] = Integer.MAX_VALUE;
				}
				else
				{
					//On remplit la matrice cout avec le dijkstra. On fait la conversion en seconde
					coutTsp[i][j] = (int)  (d.getItineraire(intersections.get(j).getId()).getLongueur()*3.6/15);
					
					//Note : /10 permet d'avoir des m; *3,6/15 permet d'avoir des seconde, le 3,6 permet de faire km/h->m/s
				}
			}
			dijkstra.add(d);
		}
		
		//On cree un objet tsp de l'ordre que l'on veut
		//TSP3 tsp = new TSP3();
		
		//On prend une valeur de temps pour des calculs de temps si on le souhaite
		long temps = System.currentTimeMillis();
		
		//On run le tsp
		tsp.chercheSolution(Integer.MAX_VALUE, intersections.size(), coutTsp, duree,tempsDebutPlage, tempsFinPlage);
		
		//Affichage du temps mis
		System.out.println(System.currentTimeMillis() - temps);
		
		int sommetCourant = 0;
		//On demande au tsp les sommets dans l'ordre pour ajouter les livraisons
		for(int i = 1; i < intersections.size(); i++) {
			int prochainSommet = tsp.getMeilleureSolution(i);
			livraisonsOrdonnees.add(livraisons.get(prochainSommet-1));
			lesItineraires.add(dijkstra.get(sommetCourant).getItineraire(intersections.get(prochainSommet).getId()));
			this.lesDijkstra.add(dijkstra.get(prochainSommet));
			sommetCourant = prochainSommet;
		}
		//On obtient les chemins à partir des dijkstras
		lesItineraires.add(dijkstra.get(sommetCourant).getItineraire(intersections.get(0).getId()));
		laTournee.setLivraisonsOrdonnees(livraisonsOrdonnees);
		laTournee.setListeItineraires(lesItineraires);
	}
	
	public List<Itineraire> getLesItineraires() {
		return lesItineraires;
	}
	
	public int supprimerLivraison(Livraison livraison) {
		int index = -1;
		for(int i = 0; i < lesItineraires.size(); i++) {
			if(lesItineraires.get(i).getDepart().getId() == livraison.getIntersection().getId())
			{
				index = i;
			}
		}
		List<Itineraire> nouvelleTournee = new ArrayList<Itineraire>();
		if(index != -1) {
			for(int i = 0; i < index-1; i++) {
				nouvelleTournee.add(this.lesItineraires.get(i));
			}
			if(index != (this.lesItineraires.size()-1)) {
				if(index != 1)
					nouvelleTournee.add(lesDijkstra.get(index-2).getItineraire(lesDijkstra.get(index).getPtDepart().getId()));
				else {
					//nouvelleTournee.add(lesDijkstra.get(index+1).getItineraire(this.laTournee.getDemandeLivraison().getEntrepot().getId()));
					Dijkstra d = new Dijkstra(this.laTournee.getPlan(), this.laTournee.getDemandeLivraison().getEntrepot());
					d.run();
					nouvelleTournee.add(d.getItineraire(this.lesDijkstra.get(index).getPtDepart().getId()));
				}
				for(int i = index+1; i < lesItineraires.size(); i++) {
					nouvelleTournee.add(this.lesItineraires.get(i));
				}
			}
			else
			{
				nouvelleTournee.add(lesDijkstra.get(index-2).getItineraire(this.laTournee.getDemandeLivraison().getEntrepot().getId()));
			}
			this.lesItineraires = nouvelleTournee;
			this.lesDijkstra.remove(index-1);
			laTournee.getDemandeLivraison().getLivraisons().remove(livraison);
			laTournee.supprimerLivraison(index-1);
			laTournee.setListeItineraires(lesItineraires);
			
		}
		
		return index;
	}
	
	public Tournee ajouterLivraison(int index, Livraison livraison) {
		List<Itineraire> nouvelleTournee = new ArrayList<Itineraire>();
		List<Livraison> nouvellesLivraisons = new ArrayList<Livraison>();
		List<Dijkstra> nouveauxDijkstra = new ArrayList<Dijkstra>();
		if( (index >=0) && (index <= laTournee.getLivraisonsOrdonnees().size()) ) {
			for(int i = 0; i < index; i++) {
				nouveauxDijkstra.add(this.lesDijkstra.get(i));
				nouvelleTournee.add(this.lesItineraires.get(i));
				nouvellesLivraisons.add(laTournee.getLivraisonsOrdonnees().get(i));
			}
			// ajout du dijksta de la livraison precedente
			if ( index != this.lesDijkstra.size())
			{
				
				
			}
			
			nouvellesLivraisons.add(livraison);
			Dijkstra d = new Dijkstra(this.laTournee.getPlan(), livraison.getIntersection());
			d.run();
			nouveauxDijkstra.add(d);
			if(index != this.lesDijkstra.size() && index != 0) {
				nouvelleTournee.add(this.lesDijkstra.get(index-1).getItineraire(livraison.getIntersection().getId()));
				nouveauxDijkstra.add(this.lesDijkstra.get(index));
			}
			else if(index != this.lesDijkstra.size()) {
				//nouvelleTournee.add(d.getItineraire(laTournee.getDemandeLivraison().getEntrepot().getId()));
				Dijkstra d2 = new Dijkstra(this.laTournee.getPlan(), this.laTournee.getDemandeLivraison().getEntrepot());
				d2.run();
				nouvelleTournee.add(d2.getItineraire(livraison.getIntersection().getId()));
				nouveauxDijkstra.add(this.lesDijkstra.get(index));
			}
			else if ( index == this.lesDijkstra.size()) {
			
				nouvelleTournee.add(this.lesDijkstra.get(index-1).getItineraire(livraison.getIntersection().getId()));
			}
			
				

			if ( index+1 < lesItineraires.size() )
			{
				nouvelleTournee.add(d.getItineraire(this.lesItineraires.get(index).getArrivee().getId()));
			}
			else
			{
				nouvelleTournee.add(d.getItineraire(this.lesItineraires.get(0).getDepart().getId()));
			}
			for(int i = index +1; i < lesItineraires.size()-1; i++) {
				nouveauxDijkstra.add(this.lesDijkstra.get(i));
				nouvelleTournee.add(this.lesItineraires.get(i));
				nouvellesLivraisons.add(laTournee.getLivraisonsOrdonnees().get(i-1));
			}
			// ajout que si on est pas sur la derniere livraison
			if ( index != this.lesDijkstra.size()) {
			nouvelleTournee.add(this.lesItineraires.get(lesItineraires.size()-1));
			}
			double longueur = 0;
			for(int i = 0; i < nouvellesLivraisons.size(); i++) {
				double aAjouter = nouvelleTournee.get(i).getLongueur()*3.6/15;
				if(nouvellesLivraisons.get(i).getPlagehoraire() == null) {
					longueur = longueur + aAjouter + nouvellesLivraisons.get(i).getDureeDechargement();
				} else {
					longueur = longueur + aAjouter + nouvellesLivraisons.get(i).getDureeDechargement();
					LocalTime lt = laTournee.getDemandeLivraison().getHeureDepart().plusSeconds((long) longueur);
					if(lt.isAfter(nouvellesLivraisons.get(i).getPlagehoraire().getHeureFin())) {
						return this.laTournee;
					}
					if(laTournee.getDemandeLivraison().getHeureDepart().plusSeconds( (long) (longueur + aAjouter)).isBefore((nouvellesLivraisons.get(i).getPlagehoraire().getHeureDebut()))) {
						longueur = (int) ChronoUnit.SECONDS.between(lt, nouvellesLivraisons.get(i).getPlagehoraire().getHeureDebut()) + nouvellesLivraisons.get(i).getDureeDechargement();
					}
				}
			}
			this.lesDijkstra = nouveauxDijkstra;
			this.lesItineraires = nouvelleTournee;
			// on actualise demande liste livraison ordonn� et on ajoute la livraison � la demande de livraison
			this.laTournee.ajouterLivraison(livraison, index);
			//this.laTournee.setLivraisonsOrdonnees(nouvellesLivraisons);
			this.laTournee.getDemandeLivraison().ajouterLivraison(livraison);
			this.laTournee.setListeItineraires(nouvelleTournee);
			System.out.println();
		}
		
		
		
		return this.laTournee;
	}
	
	public Integer[] getCurrentSolution()
	{
		if(this.tsp.isSolutionPossibleTrouvee()) {
			return tsp.getMeilleureSolution();
		}
		return null;
	}

	public Tournee echangerDeuxLivraison(int index1, int index2) {
		if( (index1 < 0) || (index2 < 0) || (index1 >= this.laTournee.getLivraisonsOrdonnees().size()) || (index2 >=this.laTournee.getLivraisonsOrdonnees().size()) || (index1 == index2)) {
			return this.laTournee;
		}
		
		if(index2 < index1) {
			int temp = index1;
			index1 = index2;
			index2 = temp;
		}
		
		Livraison dl1 = this.laTournee.getLivraisonsOrdonnees().get(index1);
		Livraison dl2 = this.laTournee.getLivraisonsOrdonnees().get(index2);
		
		
		this.supprimerLivraison(dl2);
		
		this.ajouterLivraison(index1, dl2);
		this.supprimerLivraison(dl1);
		this.ajouterLivraison(index2, dl1);
		
		return this.laTournee;
	}

	public Tournee getLaTournee() {
		return laTournee;
	}

}


