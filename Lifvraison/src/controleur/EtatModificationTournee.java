package controleur;

import java.awt.Point;

import javax.swing.JOptionPane;

import algorithme.CalculateurTournee;
import donnees.FeuilleDeRoute;
import donnees.ParseurException;
import modeles.DemandeLivraison;
import modeles.Plan;
import modeles.Tournee;
import vue.Fenetre;

public class EtatModificationTournee extends EtatDefault{

	
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
		controleur.setEtatCourant( controleur.etatChargementLivraison);
		fenetre.chargerPlan(controleur.plan);
	}
	
	@Override
	public void chargerDemandeLivraison ( Controleur controleur, Fenetre fenetre, String chemin) {
		DemandeLivraison newDemandeLivraison = new DemandeLivraison ();
		try{
			controleur.parseur.chargerLivraison(newDemandeLivraison, chemin, controleur.plan.getListeIntersection());
		} catch (ParseurException e) {
			JOptionPane.showMessageDialog(fenetre, e.getMessage(), "Erreur lors du parsage", JOptionPane.ERROR_MESSAGE);
		}
		controleur.demandeLivraison = newDemandeLivraison;
		controleur.tournee = null;
		controleur.calculateurTournee = null;
		controleur.setEtatCourant(controleur.etatCalculTournee);
		fenetre.chargerDemandeLivraison(controleur.demandeLivraison);
	}
	
	@Override
	public void calculerTournee ( Controleur controleur, Fenetre fenetre )
	{
		controleur.calculateurTournee.run();
		controleur.setEtatCourant( controleur.etatModificationTournee);
	}
	
	
	@Override
	public void ajouterLivraison ( Controleur controleur, Fenetre fenetre)
	{
		controleur.setEtatCourant( controleur.etatAjoutLivraison1);
		fenetre.setModeModificationTournee("AjoutLivraison");
	}
	@Override
	public void supprimerLivraison ( Controleur controleur, Fenetre fenetre)
	{
		controleur.setEtatCourant( controleur.etatSupprimerLivraison);
		fenetre.setModeModificationTournee("SuppressionLivraison");
	}
	@Override
	public void intervertirLivraison(Controleur controleur, Fenetre fenetre) {
		controleur.setEtatCourant( controleur.etatIntervertirLivraisons1);
		fenetre.setModeModificationTournee("IntervertirLivraison");
	}
	@Override
	public void clicgauche(Controleur controleur, Fenetre fenetre, Point point, ListeDeCommandes listeDeCommandes)
	{
		controleur.plan.getAtPoint(point, controleur.fenetre.getVueGraphique().getToleranceClic());
	}

	@Override
	public void mouseDrag(Controleur controleur, Point delta)
	{
		controleur.fenetre.getVueGraphique().getMapPanel().drag(delta);
	}
	
	@Override
	public void mouseWheel(Controleur controleur, int steps, Point point)
	{
		controleur.fenetre.getVueGraphique().getMapPanel().zoom(steps, point);
	}
	
	@Override
	public void undo(Controleur controleur, ListeDeCommandes listeDeCommandes, Fenetre fenetre) {
		listeDeCommandes.undo();
		fenetre.setModeModificationTournee();
	}

	@Override
	public void redo(Controleur controleur, ListeDeCommandes listeDeCommandes, Fenetre fenetre) {
		listeDeCommandes.redo();
		fenetre.setModeModificationTournee();
	}
	
	@Override
	public void validerTournee(Controleur controleur, Fenetre fenetre)
	{
		controleur.feuilleDeRoute = new FeuilleDeRoute();
		controleur.feuilleDeRoute.gerer(controleur.tournee);
		fenetre.setModeValidationTournee();
	}
	@Override
	public void genererFeuilleDeRoute(Controleur controleur, Fenetre fenetre) {
		controleur.feuilleDeRoute = new FeuilleDeRoute();
		controleur.feuilleDeRoute.gerer(controleur.tournee);
		fenetre.setModeValidationTournee();
		
	}
	
}
