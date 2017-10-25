package controleur;

import javax.swing.JOptionPane;

import donnees.ParseurException;
import vue.Fenetre;

public class EtatChargementLivraison extends EtatDefault{
	@Override
	public void suivant (Controleur controleur, Fenetre fenetre) {
	
		controleur.setEtatCourant( controleur.etatCalculTournee );
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
		try{
			controleur.parseur.chargerLivraison(controleur.demandeLivraison, chemin, controleur.plan.getListeIntersection());
		} catch (ParseurException e) {
			JOptionPane.showMessageDialog(fenetre, e.getMessage(), "Erreur lors du parsage", JOptionPane.ERROR_MESSAGE);
		}
		fenetre.setModeChargementDemandeLivraison();
	}
}
