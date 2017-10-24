package controleur;

import vue.Fenetre;

public class EtatAccueil extends EtatDefault {
	
	@Override
	public void suivant (Controleur controleur, Fenetre fenetre)
	{
		//do stuff
		if (controleur.plan == null)
		{
			fenetre.setModeChargementPlan();
		}
		else
		{
			fenetre.setModeChargementPlan(controleur.plan);
		}
		controleur.setEtatCourant( controleur.etatChargementPlan );
	}
	
	// useless
	@Override
	public void precedent (Controleur controleur, Fenetre fenetre) {
		controleur.setEtatCourant( controleur.etatAccueil );
	}

}
