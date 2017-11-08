package controleur;

import java.awt.Point;
import java.util.List;
import vue.Fenetre;

import modeles.Intersection;
import modeles.Livraison;

public class EtatSupprimerLivraison extends EtatDefault {

	@Override
	public void clicgauche(Controleur controleur, Fenetre fenetre, Point point, ListeDeCommandes listeDeCommandes) {
		controleur.plan.getAtPoint(point, controleur.getToleranceClic());
		Intersection pointAsupprimer = controleur.plan.getSelectedIntersection();
		// cas livraison
		List<Livraison> Listelivraisons = controleur.tournee.getLivraisonsOrdonnees();
		for (int i = 0; i < Listelivraisons.size(); i++) {
			Livraison livraison = Listelivraisons.get(i);

			if (livraison.getIntersection().equals(pointAsupprimer)) {
				listeDeCommandes.ajoute(new CommandeSupprimerLivraison(livraison, controleur.calculateurTournee));
				controleur.setEtatCourant(controleur.etatModificationTournee);
				controleur.fenetre.setModeModificationTournee();

				break;
			}
		}
	}

	@Override
	public void modificationDansLaListe(Controleur controleur, ListeDeCommandes listeDeCommandes) {
		int index = controleur.fenetre.getVueTextuelle().getListPanel().getCurrentSelection();
		List<Livraison> Listelivraisons = controleur.tournee.getLivraisonsOrdonnees();
		if (index > 0 && index <= Listelivraisons.size()) {
			Livraison livraison = Listelivraisons.get(index - 2);

			listeDeCommandes.ajoute(new CommandeSupprimerLivraison(livraison, controleur.calculateurTournee));
			controleur.setEtatCourant(controleur.etatModificationTournee);
			controleur.fenetre.setModeModificationTournee();
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
