package controleur;

import javax.swing.JOptionPane;

import algorithme.CalculateurTournee;
import donnees.ParseurException;
import modeles.DemandeLivraison;
import modeles.Plan;
import modeles.Tournee;
import vue.Fenetre;

public class EtatChargementLivraison extends EtatDefault{
	@Override
	public void suivant (Controleur controleur, Fenetre fenetre) {
	
		controleur.setEtatCourant( controleur.etatCalculTournee );
		
		// modif plan peut etre null
		if ( controleur.tournee == null)
		{
			controleur.tournee = new Tournee ( controleur.plan , controleur.demandeLivraison);
			fenetre.chargerTournee(controleur.tournee);
			controleur.calculateurTournee = new CalculateurTournee(controleur.tournee);
		}
		
		fenetre.setModeCalculTournee();
	}
	
	@Override
	public void precedent (Controleur controleur, Fenetre fenetre) {
		controleur.setEtatCourant( controleur.etatChargementPlan );
		fenetre.setModeChargementPlan();
	}
	
	@Override
	public void accueil(Controleur controleur, Fenetre fenetre) {
		controleur.setEtatCourant( controleur.etatAccueil );
		fenetre.setModeAccueil ();
	}
	
	@Override
	public void chargerDemandeLivraison ( Controleur controleur, Fenetre fenetre, String chemin) {
		DemandeLivraison newDemandeLivraison = new DemandeLivraison ();
		try{
			controleur.parseur.chargerLivraison(newDemandeLivraison, chemin, controleur.plan.getListeIntersection());
		} catch (ParseurException e) {
			JOptionPane.showMessageDialog(fenetre, e.getMessage(), "Erreur lors du parsage", JOptionPane.ERROR_MESSAGE);
		}
		controleur.demandeLivraison = newDemandeLivraison;
		controleur.tournee = null;
		fenetre.chargerDemandeLivraison(controleur.demandeLivraison);
	}
	
	@Override
	public void chargementPlan(Controleur controleur, Fenetre fenetre) {
		controleur.setEtatCourant( controleur.etatChargementPlan );
		fenetre.setModeChargementPlan ();
		
	}
}
