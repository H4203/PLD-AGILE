package controleur;

import vue.Fenetre;

public class EtatModeValidation extends EtatDefault{
	@Override
	public void suivant (Controleur controleur, Fenetre fenetre) {
	
		controleur.setEtatCourant( controleur.etatAccueil );
		fenetre.setModeAccueil();
	}
	
	@Override
	public void precedent (Controleur controleur, Fenetre fenetre) {
		controleur.setEtatCourant( controleur.etatModificationTournee );
		fenetre.setModeModificationTournee();
	}
	
	@Override
	public void accueil(Controleur controleur, Fenetre fenetre) {
		controleur.setEtatCourant( controleur.etatAccueil );
		fenetre.setModeAccueil();
	}
	
	@Override
	public void chargementPlan(Controleur controleur, Fenetre fenetre) {
		controleur.setEtatCourant( controleur.etatChargementPlan );
		fenetre.setModeChargementPlan ();
	}
	@Override
	public void chargementDemandeLivraison(Controleur controleur, Fenetre fenetre) {
		controleur.setEtatCourant( controleur.etatChargementLivraison );
		fenetre.setModeChargementDemandeLivraison ();
	}
	@Override
	public void calculTournee(Controleur controleur, Fenetre fenetre) {
		controleur.setEtatCourant( controleur.etatCalculTournee );
		fenetre.setModeCalculTournee();	
	}
	@Override
	public void modificationTournee(Controleur controleur, Fenetre fenetre) {
		controleur.setEtatCourant( controleur.etatModificationTournee );
		fenetre.setModeModificationTournee();
	}
}
