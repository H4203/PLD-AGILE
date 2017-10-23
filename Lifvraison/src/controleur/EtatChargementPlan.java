package controleur;

import vue.Fenetre;

public class EtatChargementPlan extends EtatDefault{
	
	@Override
	public void suivant (Controleur controleur, Fenetre fenetre) {
	
		//do stuff
		controleur.setEtatCourant( controleur.etatChargementLivraison );
	}
	
	@Override
	public void precedent (Controleur controleur, Fenetre fenetre) {
		controleur.setEtatCourant( controleur.etatAccueil );
	}
	
	@Override
	public void accueil(Controleur controleur, Fenetre fenetre) {
		controleur.setEtatCourant( controleur.etatAccueil );
		
	}
}
