package controleur;

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
	
	/**
	 * 
	 */
	public Controleur() 
	{
		parseur = new XMLParseur ();
		fenetre = new Fenetre ( this );
	}
	
	public void importerPlan ( String cheminPlan)
	{
		plan = parseur.chargerPlan( cheminPlan );
		afficherPlan();
	}
	
	public void afficherPlan()
	{
		fenetre.setModePlan( plan );
	}
	
	public void importerDemandeLivraison ( String cheminDemandeLivraisons)
	{
		demandeLivraisons = parseur.chargerLivraison( cheminDemandeLivraisons, plan.getListeIntersection() );
		afficherDemandeLivraison();
	}
	
	public void afficherDemandeLivraison()
	{
		fenetre.setModeDemandeLivraison(plan, demandeLivraisons);
	}
	
	public void calculerTournee ()
	{
		tournee = new Tournee ( plan, demandeLivraisons );
		calculateurTournee = new CalculateurTournee ( tournee);
		calculateurTournee.run();
		afficherTournee();
	}
	
	public void afficherTournee()
	{
		fenetre.setModeTournee(plan, demandeLivraisons, tournee);
	}
	
	public void validerTournee ()
	{
		fenetre.setModeValiderTournee(plan, demandeLivraisons, tournee);
	}
	
	public void retourAccueil()
	{
		fenetre.setModeAccueil();
	}
	
}
