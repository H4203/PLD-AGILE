package controleur;

import javax.swing.JOptionPane;

import donnees.ParseurException;
import modeles.Plan;
import vue.Fenetre;

public class EtatChargementPlan extends EtatDefault{
	
	@Override
	public void suivant (Controleur controleur, Fenetre fenetre) {
	
		controleur.setEtatCourant( controleur.etatChargementLivraison );
		if ( controleur.demandeLivraison == null)
		{
			fenetre.setModeChargementDemandeLivraison();
		}
		else
		{
			fenetre.setModeChargementDemandeLivraison();
		}
	}
	
	@Override
	public void precedent (Controleur controleur, Fenetre fenetre) {
		controleur.setEtatCourant( controleur.etatAccueil );
		fenetre.setModeAccueil();
	}
	
	@Override
	public void accueil(Controleur controleur, Fenetre fenetre) {
		controleur.setEtatCourant( controleur.etatAccueil );
		fenetre.setModeAccueil();
		
	}
	
	@Override
	public void chargerPlan ( Controleur controleur, Fenetre fenetre, String chemin) {
		Plan newPlan = new Plan ();
		try{
			controleur.parseur.chargerPlan(newPlan, chemin);
		} catch (ParseurException e) {
			JOptionPane.showMessageDialog(fenetre, e.getMessage(), "Erreur lors du parsage", JOptionPane.ERROR_MESSAGE);
		}
		// on attribut le nouveau plan
		controleur.plan = newPlan;
		controleur.demandeLivraison = null;
		controleur.tournee = null;
		fenetre.chargerPlan(controleur.plan);
	}
}
