package controleur;

import vue.Fenetre;

public class EtatChargementPlan extends EtatDefault{
	
	@Override
	public void suivant (Controleur controleur, Fenetre fenetre) {
	
		
		if ( controleur.demandeLivraison == null)
		{
			fenetre.setModeChargementDemandeLivraison();
		}
		else
		{
			fenetre.setModeChargementDemandeLivraison( controleur.demandeLivraison );
		}
		controleur.setEtatCourant( controleur.etatChargementLivraison );
		
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
}
