package controleur;

import java.awt.Point;

import vue.Fenetre;

public class EtatModificationTournee extends EtatDefault{
	@Override
	public void suivant (Controleur controleur, Fenetre fenetre) {
	
		controleur.setEtatCourant( controleur.etatModeValidation );
		fenetre.setModeValidationTournee();
	}
	
	@Override
	public void precedent (Controleur controleur, Fenetre fenetre) {
		controleur.setEtatCourant( controleur.etatCalculTournee );
		fenetre.setModeCalculTournee();
	}
	
	@Override
	public void accueil(Controleur controleur, Fenetre fenetre) {
		controleur.setEtatCourant( controleur.etatAccueil );
		fenetre.setModeAccueil ();
	}
	
	@Override
	public void validerTournee ( Controleur controleur, Fenetre fenetre ) {
		controleur.setEtatCourant( controleur.etatModeValidation );
		fenetre.setModeValidationTournee();
	}
	
	@Override
	public void ajouterLivraison ( Controleur controleur, Fenetre fenetre)
	{
		controleur.setEtatCourant( controleur.etatAjoutLivraison1);
	}

	// start
	@Override
	public void clicgauche(Controleur controleur, Point point)
	{
		controleur.plan.getAtPoint(point);
	}
	public void drag(Controleur controleur, Point delta)
	{
		controleur.plan.drag(delta);
	}
	// end
}
