package controleur;

import vue.Fenetre;

public class EtatModeValidation extends EtatDefault{

	@Override
	public void modificationTournee(Controleur controleur, Fenetre fenetre) {
		controleur.setEtatCourant( controleur.etatModificationTournee );
		fenetre.setModeModificationTournee();
	}
	
}

