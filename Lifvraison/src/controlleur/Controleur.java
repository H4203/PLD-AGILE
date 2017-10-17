package controlleur;

import algorithme.CalculateurTournee;
import donnees.XMLParseur;
import modeles.Plan;
import modeles.Tournee;
import vue.Fenetre;
import modeles.DemandeLivraison;

public class Controleur {

	
	private XMLParseur parseur;
	private CalculateurTournee calculateurTournee;
	private Fenetre fenetre;
	private Plan plan;
	private Tournee tournee;
	DemandeLivraison demandeLivraisons;
	
	public void importerPlan ( String cheminPlan)
	{
		plan = parseur.chargerPlan( cheminPlan );
		fenetre.setModePlan( plan );
		

	}
	
	public void ImporterDemande ( String cheminDemandeLivraisons)
	{
		demandeLivraisons = parseur.chargerLivraison( cheminDemandeLivraisons, plan.getListeIntersection() );
		DemanderCalculTournee();
		
		
	}
	
	public void DemanderCalculTournee ()
	{
		
		tournee = new Tournee ( plan, demandeLivraisons );
		calculateurTournee = new CalculateurTournee ( tournee);
		calculateurTournee.run();
		// pas necessaire
		//tournee.setListeItineraires( calculateurTournee.getLesItineraires() );
		fenetre.setModeTournee(plan, demandeLivraisons, tournee);
		
	}

	/**
	 * 
	 */
	public Controleur() {
		
		parseur = new XMLParseur ();
		fenetre = new Fenetre ( this );
	
	}
	
	
}
