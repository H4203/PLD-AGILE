package controleur;

import java.awt.Point;
import vue.Fenetre;

import modeles.Intersection;
import modeles.Livraison;

public class EtatAjoutLivraison1 extends EtatDefault {

	@Override
	public void clicgauche(Controleur controleur, Fenetre fenetre, Point point, ListeDeCommandes listeDeCommandes) {
		controleur.plan.getAtPoint(point, controleur.getToleranceClic());
		Intersection pointDeLivraison = controleur.plan.getSelectedIntersection();
		// on verifie que le point n'est pas deja une livraison
		for (Livraison livraison : controleur.tournee.getLivraisonsOrdonnees()) {
			if (pointDeLivraison.getId() == livraison.getIntersection().getId()) {
				// on oubli ce point
				pointDeLivraison = null;
				//on n'a pas besoin d'aller plus loin
				break;
			}
		}
		if (pointDeLivraison != null) {
			controleur.setEtatCourant(controleur.etatAjoutLivraison2);
			controleur.etatAjoutLivraison2.pointDeLivraison = pointDeLivraison;

			fenetre.setIndicationLabel("<html>Selectionnez le point de livraison apres lequel ajouter votre nouveau point de livraison<br>sur le plan ou dans la liste</html>");

		}
	}

	@Override
	public void undo(Controleur controleur, ListeDeCommandes listeDeCommandes, Fenetre fenetre) {
		controleur.setEtatCourant(controleur.etatModificationTournee);
		fenetre.setModeModificationTournee();
	}

	@Override
	public void mouseDrag(Controleur controleur, Point delta) {
		controleur.fenetre.getVueGraphique().getMapPanel().drag(delta);
	}

	@Override
	public void mouseWheel(Controleur controleur, int steps, Point point) {
		controleur.fenetre.getVueGraphique().getMapPanel().zoom(steps, point);
	}

}
