package controleur;

import java.awt.Point;

import javax.swing.JOptionPane;

import algorithme.CalculateurTournee;
import donnees.ParseurException;
import modeles.DemandeLivraison;
import modeles.Plan;
import modeles.Tournee;
import vue.Fenetre;

public class EtatCalculTournee extends EtatDefault{
	@Override
	public void chargerPlan ( Controleur controleur, Fenetre fenetre, String chemin) {
		Plan newPlan = new Plan ();
		try{
			controleur.parseur.chargerPlan(newPlan, chemin);
			// on attribut le nouveau plan
			controleur.plan = newPlan;
			controleur.demandeLivraison = null;
			controleur.tournee = null;
			controleur.setEtatCourant( controleur.etatChargementLivraison);
			fenetre.chargerPlan(controleur.plan);
		} catch (ParseurException e) {
			JOptionPane.showMessageDialog(fenetre, e.getMessage(), "Erreur lors du parsage", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	@Override
	public void chargerDemandeLivraison ( Controleur controleur, Fenetre fenetre, String chemin) {
		DemandeLivraison newDemandeLivraison = new DemandeLivraison ();
		try{
			controleur.parseur.chargerLivraison(newDemandeLivraison, chemin, controleur.plan.getListeIntersection());
			controleur.demandeLivraison = newDemandeLivraison;
			controleur.tournee = null;
			controleur.calculateurTournee = null;
			//controleur.tournee = new Tournee ( controleur.plan , controleur.demandeLivraison);
			//controleur.calculateurTournee = new CalculateurTournee(controleur.tournee);
			controleur.setEtatCourant(controleur.etatCalculTournee);
			fenetre.chargerDemandeLivraison(controleur.demandeLivraison);
		} catch (ParseurException e) {
			JOptionPane.showMessageDialog(fenetre, e.getMessage(), "Erreur lors du parsage", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	@Override
	public void calculerTournee ( Controleur controleur, Fenetre fenetre )
	{
		controleur.tournee = new Tournee ( controleur.plan , controleur.demandeLivraison);
		controleur.calculateurTournee = new CalculateurTournee(controleur.tournee);
		controleur.calculateurTournee.run();
		controleur.setEtatCourant( controleur.etatModificationTournee);
		fenetre.chargerTournee(controleur.tournee);
	}
	
	@Override
	public void mouseWheel(Controleur controleur, int steps, Point point)
	{
		controleur.fenetre.getVueGraphique().getMapPanel().zoom(steps, point);
	}
	@Override
	public void mouseDrag(Controleur controleur, Point delta)
	{
		controleur.fenetre.getVueGraphique().getMapPanel().drag(delta);
	}
	@Override
	public void clicgauche(Controleur controleur, Fenetre fenetre, Point point, ListeDeCommandes listeDeCommandes)
	{
		controleur.plan.getAtPoint(point, controleur.fenetre.getVueGraphique().getToleranceClic());
	}
	
}
