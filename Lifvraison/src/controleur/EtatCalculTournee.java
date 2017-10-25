package controleur;

import algorithme.CalculateurTournee;
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
		fenetre.setModeModificationTournee(controleur.tournee);
		
	}
	
	@Override
	public void precedent (Controleur controleur, Fenetre fenetre) {
		controleur.setEtatCourant( controleur.etatChargementLivraison );
		fenetre.setModeChargementDemandeLivraison ( controleur.demandeLivraison );
	}
	
	@Override
	public void accueil(Controleur controleur, Fenetre fenetre) {
		controleur.setEtatCourant( controleur.etatAccueil );
		fenetre.setModeAccueil();
	}
	
	@Override
	public void calculerTournee ( Controleur controleur, Fenetre fenetre )
	{
		controleur.tournee = new Tournee(controleur.plan, controleur.demandeLivraison);
		controleur.calculateurTournee = new CalculateurTournee(controleur.tournee);
		controleur.calculateurTournee.run();
		fenetre.setModeCalculTournee(controleur.tournee);
	}
}
