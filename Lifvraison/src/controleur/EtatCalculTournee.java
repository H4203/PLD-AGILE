package controleur;

import java.util.List;

import algorithme.CalculateurTournee;
import modeles.Livraison;
import modeles.Tournee;
import vue.Fenetre;

public class EtatCalculTournee extends EtatDefault{
	@Override
	public void suivant (Controleur controleur, Fenetre fenetre) {
	
		controleur.setEtatCourant( controleur.etatModificationTournee );
		if ( controleur.tournee == null)
		{
			calculerTournee(controleur, fenetre);
		}
		fenetre.setModeModificationTournee();
		
	}
	
	@Override
	public void precedent (Controleur controleur, Fenetre fenetre) {
		controleur.setEtatCourant( controleur.etatChargementLivraison );
		fenetre.setModeChargementDemandeLivraison();
	}
	
	@Override
	public void accueil(Controleur controleur, Fenetre fenetre) {
		controleur.setEtatCourant( controleur.etatAccueil );
		fenetre.setModeAccueil();
	}
	
	@Override
	public void calculerTournee ( Controleur controleur, Fenetre fenetre )
	{
		controleur.calculateurTournee.run();
		fenetre.setModeCalculTournee();
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
	
}
