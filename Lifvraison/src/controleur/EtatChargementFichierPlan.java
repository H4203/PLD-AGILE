package controleur;

import javax.swing.JOptionPane;

import donnees.ParseurException;
import vue.Fenetre;

public class EtatChargementFichierPlan extends EtatDefault{
	public void ouvrir (Controleur controleur, Fenetre fenetre, String chemin)
	{
		try{
			controleur.parseur.chargerPlan(controleur.plan, chemin);
		} catch (ParseurException e) {
			JOptionPane.showMessageDialog(fenetre, e.getMessage(), "Erreur lors du parsage", JOptionPane.ERROR_MESSAGE);
		}
		fenetre.setModeChargementPlan();
	}
	
	@Override
	public void precedent (Controleur controleur, Fenetre fenetre) {
		controleur.setEtatCourant( controleur.etatAccueil );
		fenetre.setModeAccueil();
	}
}
