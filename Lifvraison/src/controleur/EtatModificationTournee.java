package controleur;

import vue.Fenetre;

public class EtatModificationTournee extends EtatDefault{
	@Override
	public void suivant (Controleur controleur, Fenetre fenetre) {
	
		//do stuff
		controleur.setEtatCourant( controleur.etatModeValidation );
	}
	
	@Override
	public void precedent (Controleur controleur, Fenetre fenetre) {
		controleur.setEtatCourant( controleur.etatCalculTournee );
	}
	
	@Override
	public void accueil(Controleur controleur, Fenetre fenetre) {
		controleur.setEtatCourant( controleur.etatAccueil );
		
	}
	
	@Override
	public void ajouterLivraison ( Controleur controleur, Fenetre fenetre)
	{
		controleur.setEtatCourant( controleur.etatAjoutLivraison1);
	}
}
