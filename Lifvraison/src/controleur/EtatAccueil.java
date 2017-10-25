package controleur;

import vue.Fenetre;

public class EtatAccueil extends EtatDefault {
	
	@Override
	public void suivant (Controleur controleur, Fenetre fenetre)
	{
		controleur.setEtatCourant( controleur.etatChargementPlan );
		if (controleur.plan == null)
		{
			fenetre.setModeChargementPlan();
		}
		else
		{
			fenetre.setModeChargementPlan(controleur.plan);
		}
		
	}
	
	// useless
	@Override
	public void precedent (Controleur controleur, Fenetre fenetre) {
		controleur.setEtatCourant( controleur.etatAccueil );
		fenetre.setModeAccueil();
	}
	
	@Override
	public void accueil(Controleur controleur, Fenetre fenetre)
	{
		controleur.setEtatCourant( controleur.etatAccueil );
		fenetre.setModeAccueil();
	}

}
