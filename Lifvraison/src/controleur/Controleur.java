package controleur;

import javax.swing.JOptionPane;

import algorithme.CalculateurTournee;
import donnees.ParseurException;
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
	public Controleur() {
		
		try {
			parseur = new XMLParseur ();
			fenetre = new Fenetre ( this );
		} catch (ParseurException e) {
			JOptionPane.showMessageDialog(fenetre, e.getMessage(), "Erreur lors du parsage", JOptionPane.ERROR_MESSAGE);
		}
	
	}
	
	public void importerPlan ( String cheminPlan)
	{
		try {
			plan = parseur.chargerPlan( cheminPlan );
			fenetre.setModePlan( plan );
		} catch (ParseurException e) {
			JOptionPane.showMessageDialog(fenetre, e.getMessage(), "Erreur lors du parsage", JOptionPane.ERROR_MESSAGE);
		}
		

	}
	
	public void ImporterDemande ( String cheminDemandeLivraisons)
	{
		try {
			demandeLivraisons = parseur.chargerLivraison( cheminDemandeLivraisons, plan.getListeIntersection() );
			fenetre.setModeDemandeLivraison(plan, demandeLivraisons);
		} catch (ParseurException e) {
			JOptionPane.showMessageDialog(fenetre, e.getMessage(), "Erreur lors du parsage", JOptionPane.ERROR_MESSAGE);
		}
		//DemanderCalculTournee()
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
	
	public void ValiderTournee ()
	{
		
		fenetre.setModeValiderTournee(plan, demandeLivraisons, tournee);
		
	}
	
	public void RetourALAccueil()
	{
		fenetre.setModeAccueil();
	}
	
	
}
