package controleur;

import java.awt.Point;
import java.util.List;

import modeles.Intersection;
import modeles.Livraison;
import vue.Fenetre;

public class EtatIntervertirLivraisons2 extends EtatDefault {

	protected int posLivraison1 = -1;
	protected int posLivraison2 = -1;

	@Override
	public void clicgauche(Controleur controleur, Fenetre fenetre, Point point, ListeDeCommandes listeDeCommandes) {

		controleur.plan.getAtPoint(point, controleur.getToleranceClic());
		Intersection livraison2 = controleur.plan.getSelectedIntersection();
		// cas livraison
		List<Livraison> Listelivraisons = controleur.tournee.getLivraisonsOrdonnees();
		for (int i = 0; i < Listelivraisons.size(); i++) {
			Livraison livraison = Listelivraisons.get(i);
			if (livraison.getIntersection().equals(livraison2)) {
				posLivraison2 = i;
			}
		}
		System.out.println(posLivraison1);
		System.out.println(posLivraison2);
		if (posLivraison1 != posLivraison2 && posLivraison2 != -1) {
			controleur.setEtatCourant(controleur.etatModificationTournee);
			listeDeCommandes.ajoute(
					new CommandeIntervertirCommande(posLivraison1, posLivraison2, controleur.calculateurTournee));
			System.out.println("test");
			controleur.fenetre.setModeModificationTournee();
		}

	}

	@Override
	public void modificationDansLaListe(Controleur controleur, ListeDeCommandes listeDeCommandes) {
		int index = controleur.fenetre.getVueTextuelle().getListPanel().getCurrentSelection();
		List<Livraison> Listelivraisons = controleur.tournee.getLivraisonsOrdonnees();
		if (index > 0 && index <= Listelivraisons.size()) {
			Livraison livraison = Listelivraisons.get(index - 1);

			if (posLivraison1 != posLivraison2 && posLivraison2 != -1) {
				controleur.plan.getLivraison(livraison);
				controleur.setEtatCourant(controleur.etatModificationTournee);
				listeDeCommandes.ajoute(
						new CommandeIntervertirCommande(posLivraison1, posLivraison2, controleur.calculateurTournee));
				controleur.fenetre.setModeModificationTournee();
			}
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
