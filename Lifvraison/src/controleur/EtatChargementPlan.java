package controleur;

import java.awt.Point;

import javax.swing.JOptionPane;

import donnees.ParseurException;
import modeles.Plan;
import vue.Fenetre;

public class EtatChargementPlan extends EtatDefault{
	
	@Override
	public void accueil(Controleur controleur, Fenetre fenetre) {
		controleur.setEtatCourant( controleur.etatAccueil );
		fenetre.setModeAccueil();
		
	}
	
	@Override
	public void chargerPlan ( Controleur controleur, Fenetre fenetre, String chemin) {
		Plan newPlan = new Plan ();
		try{
			controleur.parseur.chargerPlan(newPlan, chemin);
		} catch (ParseurException e) {
			JOptionPane.showMessageDialog(fenetre, e.getMessage(), "Erreur lors du parsage", JOptionPane.ERROR_MESSAGE);
		}
		// on attribut le nouveau plan
		controleur.plan = newPlan;
		controleur.demandeLivraison = null;
		controleur.tournee = null;
		fenetre.chargerPlan(controleur.plan);
	}
	
	public void mouseDrag(Controleur controleur, Point delta)
	{
		controleur.fenetre.getVueGraphique().getMapPanel().drag(delta);
	}
	public void mouseWheel(Controleur controleur, int steps, Point point)
	{
		controleur.fenetre.getVueGraphique().getMapPanel().zoom(steps, point);
	}
	
	@Override
	public void clicgauche(Controleur controleur, Fenetre fenetre, Point point, ListeDeCommandes listeDeCommandes)
	{
		controleur.plan.getAtPoint(point, controleur.fenetre.getVueGraphique().getToleranceClic());
	}
}
