package controleur;

import java.awt.Point;
import java.util.List;

import javax.swing.JOptionPane;

import algorithme.CalculateurTournee;
import donnees.FeuilleDeRoute;
import donnees.ParseurException;
import modeles.DemandeLivraison;
import modeles.Intersection;
import modeles.Livraison;
import modeles.Plan;
import modeles.Tournee;
import vue.Fenetre;

public class EtatGenererFeuilleDeRoute extends EtatDefault {
	@Override
	public void chargerPlan(Controleur controleur, Fenetre fenetre, String chemin) {
		Plan newPlan = new Plan();
		try {
			controleur.parseur.chargerPlan(newPlan, chemin);
		} catch (ParseurException e) {
			JOptionPane.showMessageDialog(fenetre, e.getMessage(), "Erreur lors du parsage", JOptionPane.ERROR_MESSAGE);
		}
		// on attribut le nouveau plan
		controleur.plan = newPlan;
		controleur.demandeLivraison = null;
		controleur.tournee = null;
		controleur.setEtatCourant(controleur.etatChargementLivraison);
		fenetre.chargerPlan(controleur.plan);
	}

	@Override
	public void chargerDemandeLivraison(Controleur controleur, Fenetre fenetre, String chemin) {
		DemandeLivraison newDemandeLivraison = new DemandeLivraison();
		try {
			controleur.parseur.chargerLivraison(newDemandeLivraison, chemin, controleur.plan.getListeIntersection());
		} catch (ParseurException e) {
			JOptionPane.showMessageDialog(fenetre, e.getMessage(), "Erreur lors du parsage", JOptionPane.ERROR_MESSAGE);
		}
		controleur.demandeLivraison = newDemandeLivraison;
		controleur.tournee = null;
		controleur.calculateurTournee = null;
		// controleur.tournee = new Tournee ( controleur.plan ,
		// controleur.demandeLivraison);
		// controleur.calculateurTournee = new CalculateurTournee(controleur.tournee);
		controleur.setEtatCourant(controleur.etatCalculTournee);
		fenetre.chargerDemandeLivraison(controleur.demandeLivraison);
	}

	@Override
	public void calculerTournee(Controleur controleur, Fenetre fenetre) {
		controleur.tournee = new Tournee(controleur.plan, controleur.demandeLivraison);
		controleur.calculateurTournee = new CalculateurTournee(controleur.tournee);
		controleur.calculateurTournee.run();
		controleur.setEtatCourant(controleur.etatModificationTournee);
		fenetre.chargerTournee(controleur.tournee);
	}

	@Override
	public void mouseWheel(Controleur controleur, int steps, Point point) {
		controleur.fenetre.getVueGraphique().getMapPanel().zoom(steps, point);
	}

	@Override
	public void mouseDrag(Controleur controleur, Point delta) {
		controleur.fenetre.getVueGraphique().getMapPanel().drag(delta);
	}

	@Override
	public void clicgauche(Controleur controleur, Fenetre fenetre, Point point, ListeDeCommandes listeDeCommandes) {
		controleur.plan.getAtPoint(point, controleur.fenetre.getVueGraphique().getToleranceClic());

		Intersection pointSelectionne = controleur.plan.getSelectedIntersection();

		// cas entrepot
		if (controleur.demandeLivraison.getEntrepot().equals(pointSelectionne)) {
			fenetre.getVueTextuelle().getListPanel().setSelectedIndex(0);
		}
		// cas livraison
		List<Livraison> Listelivraisons = controleur.tournee.getLivraisonsOrdonnees();
		for (int i = 0; i < Listelivraisons.size(); i++) {
			Livraison livraison = Listelivraisons.get(i);
			if (livraison.getIntersection().equals(pointSelectionne)) {
				fenetre.getVueTextuelle().getListPanel().setSelectedIndex(i + 1);
				break;
			}
		}
	}

	@Override
	public void validerTournee(Controleur controleur, Fenetre fenetre) {
		controleur.setEtatCourant(controleur.etatGenererFeuilleDeRoute);
		fenetre.setModeGenerationFeuilleDeRoute();
	}

	@Override
	public void modificationDansLaListe(Controleur controleur, ListeDeCommandes listeDeCommandes) {
		int index = controleur.fenetre.getVueTextuelle().getListPanel().getCurrentSelection();
		List<Livraison> Listelivraisons = controleur.tournee.getLivraisonsOrdonnees();
		if (index > 0 && index <= Listelivraisons.size()) {
			Livraison livraison = Listelivraisons.get(index - 1);

			controleur.plan.getLivraison(livraison);
		} else if (index == 0 || index == Listelivraisons.size() + 1) {
			controleur.plan.getEntrepot(controleur.tournee.getDemandeLivraison().getEntrepot());
		}
	}

	@Override
	public void genererFeuilleDeRoute(Controleur controleur, Fenetre fenetre, String chemin) {
		controleur.feuilleDeRoute = new FeuilleDeRoute();
		controleur.feuilleDeRoute.gerer(controleur.tournee, chemin);
		fenetre.setModeGenerationFeuilleDeRoute();
	}
}
