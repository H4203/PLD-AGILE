package controleur;

import java.awt.Point;

import javax.swing.JOptionPane;

import donnees.ParseurException;
import vue.Fenetre;

public class EtatChargementPlan extends EtatDefault{
	
	@Override
	public void suivant (Controleur controleur, Fenetre fenetre) {
	
		controleur.setEtatCourant( controleur.etatChargementLivraison );
		if ( controleur.demandeLivraison == null)
		{
			fenetre.setModeChargementDemandeLivraison();
		}
		else
		{
			fenetre.setModeChargementDemandeLivraison();
		}
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
	
	@Override
	public void chargerPlan ( Controleur controleur, Fenetre fenetre, String chemin) {
		try{
			controleur.parseur.chargerPlan(controleur.plan, chemin);
		} catch (ParseurException e) {
			JOptionPane.showMessageDialog(fenetre, e.getMessage(), "Erreur lors du parsage", JOptionPane.ERROR_MESSAGE);
		}
		fenetre.setModeChargementPlan();
	}
	
	public void mouseDrag(Controleur controleur, Point delta)
	{
		controleur.fenetre.getVueGraphique().getMapPanel().drag(delta);
	}
	public void mouseWheel(Controleur controleur, int steps, Point point)
	{
		controleur.fenetre.getVueGraphique().getMapPanel().zoom(steps, point);
	}
}
