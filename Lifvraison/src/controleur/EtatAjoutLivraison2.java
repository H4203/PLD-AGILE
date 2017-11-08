package controleur;

import java.awt.Point;
import java.util.List;

import vue.Fenetre;

import modeles.Intersection;
import modeles.Livraison;

public class EtatAjoutLivraison2 extends EtatDefault {

	protected Intersection pointDeLivraison;

	@Override
	public void clicgauche(Controleur controleur, Fenetre fenetre, Point point, ListeDeCommandes listeDeCommandes) {

		controleur.plan.getAtPoint(point, controleur.getToleranceClic());
		Intersection pointPrecedant = controleur.plan.getSelectedIntersection();

		// cas entrepot
		if (controleur.demandeLivraison.getEntrepot().equals(pointPrecedant)) {
			controleur.setEtatCourant(controleur.etatModificationTournee);
			Livraison nouvelleLivraison = new Livraison(pointDeLivraison, 120);
			listeDeCommandes.ajoute(new CommandeAjoutLivraison(0, nouvelleLivraison, controleur.calculateurTournee));
			controleur.fenetre.setModeModificationTournee();

			fenetre.getVueTextuelle().getListPanel().setSelectedIndex(0);
		}
		// cas livraison
		List<Livraison> Listelivraisons = controleur.tournee.getLivraisonsOrdonnees();
		for (int i = 0; i < Listelivraisons.size(); i++) {
			Livraison livraison = Listelivraisons.get(i);

			if (livraison.getIntersection().equals(pointPrecedant)) {
				controleur.setEtatCourant(controleur.etatModificationTournee);
				Livraison nouvelleLivraison = new Livraison(pointDeLivraison, 120);
				listeDeCommandes
						.ajoute(new CommandeAjoutLivraison(i + 1, nouvelleLivraison, controleur.calculateurTournee));
				controleur.fenetre.setModeModificationTournee();
				fenetre.getVueTextuelle().getListPanel().setSelectedIndex(i + 1);
				// evite une trop grande creation de point
				break;
			}
		}
	}

	@Override
	public void modificationDansLaListe(Controleur controleur, ListeDeCommandes listeDeCommandes) {
		int index = controleur.fenetre.getVueTextuelle().getListPanel().getCurrentSelection();
		List<Livraison> Listelivraisons = controleur.tournee.getLivraisonsOrdonnees();
		if (index > 0 && index <= Listelivraisons.size()) {
			Livraison livraison = Listelivraisons.get(index - 1);
			controleur.plan.getLivraison(livraison);

			controleur.setEtatCourant(controleur.etatModificationTournee);
			Livraison nouvelleLivraison = new Livraison(pointDeLivraison, 120);
			listeDeCommandes
					.ajoute(new CommandeAjoutLivraison(index, nouvelleLivraison, controleur.calculateurTournee));
			controleur.fenetre.setModeModificationTournee();
		} else if (index == 0 || index == Listelivraisons.size() + 1) {
			controleur.plan.getEntrepot(controleur.tournee.getDemandeLivraison().getEntrepot());

			controleur.setEtatCourant(controleur.etatModificationTournee);
			Livraison nouvelleLivraison = new Livraison(pointDeLivraison, 120);
			listeDeCommandes.ajoute(new CommandeAjoutLivraison(0, nouvelleLivraison, controleur.calculateurTournee));
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
