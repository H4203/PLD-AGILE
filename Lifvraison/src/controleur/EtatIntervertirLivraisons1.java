package controleur;

import java.awt.Point;
import java.util.List;

import modeles.Intersection;
import modeles.Livraison;
import vue.Fenetre;

public class EtatIntervertirLivraisons1 extends EtatDefault {
	@Override
	public void clicgauche(Controleur controleur, Fenetre fenetre, Point point, ListeDeCommandes listeDeCommandes) {
		System.out.println("test1");
		controleur.plan.getAtPoint(point, controleur.getToleranceClic());
		Intersection livraison1 = controleur.plan.getSelectedIntersection();
		List<Livraison> Listelivraisons = controleur.tournee.getLivraisonsOrdonnees();
		int posLivraison1 = -1;
		for (int i = 0; i < Listelivraisons.size(); i++) {
			Livraison livraison = Listelivraisons.get(i);
			if (livraison.getIntersection().equals(livraison1)) {
				posLivraison1 = i;
			}
		}
		if (posLivraison1 != -1) {
			controleur.setEtatCourant(controleur.etatIntervertirLivraisons2);
			controleur.etatIntervertirLivraisons2.posLivraison1 = posLivraison1;
<<<<<<< HEAD
			fenetre.setIndicationLabel("<html>Premier point de livraison selectionne<br>Selectionnez le second point de livraison sur le plan </html>");
=======
			fenetre.setIndicationLabel(
					"<html>Premier point de livraison selectionne<br>Sélectionnez le second point de livraison, sur le plan ou dans la liste</html>");
>>>>>>> Dev
		}

	}

	@Override
	public void modificationDansLaListe(Controleur controleur, ListeDeCommandes listeDeCommandes) {
		int index = controleur.fenetre.getVueTextuelle().getListPanel().getCurrentSelection();
		List<Livraison> Listelivraisons = controleur.tournee.getLivraisonsOrdonnees();
		if (index > 0 && index <= Listelivraisons.size()) {
			Livraison livraison = Listelivraisons.get(index - 1);
			controleur.plan.getLivraison(livraison);

			controleur.setEtatCourant(controleur.etatIntervertirLivraisons2);
			controleur.etatIntervertirLivraisons2.posLivraison1 = index;
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
