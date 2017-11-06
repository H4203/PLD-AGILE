package controleur;

import vue.Fenetre;

public class EtatModeValidation extends EtatDefault{
	@Override
	public void suivant (Controleur controleur, Fenetre fenetre) {
	
		//do stuff
		controleur.setEtatCourant( controleur.etatAccueil );
	}
	
	@Override
	public void precedent (Controleur controleur, Fenetre fenetre) {
		controleur.setEtatCourant( controleur.etatModificationTournee );
	}
	
	@Override
	public void accueil(Controleur controleur, Fenetre fenetre) {
		controleur.setEtatCourant( controleur.etatAccueil );
		
	}
	
	public void gererFeuilleDeRoute(Controleur controleur, Fenetre fenetre) {
		controleur.feuilleDeRoute.gerer(controleur.tournee);
		
	}
}
