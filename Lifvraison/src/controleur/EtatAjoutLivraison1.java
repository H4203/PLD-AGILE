package controleur;

import java.awt.Point;
import vue.Fenetre;

import modeles.Intersection;

public class EtatAjoutLivraison1 extends EtatDefault{

	@Override
	public void clicgauche(Controleur controleur, Fenetre fenetre, Point point, ListeDeCommandes listeDeCommandes)
	{
		controleur.plan.getAtPoint(point, controleur.getToleranceClic());
		Intersection pointDeLivraison = controleur.plan.getSelectedIntersection();
		if (pointDeLivraison != null)
		{
			controleur.setEtatCourant( controleur.etatAjoutLivraison2);
			controleur.etatAjoutLivraison2.pointDeLivraison = pointDeLivraison;
			System.out.println("phase 2");
			fenetre.setIndicationLabel("<html>Selectionnez le point de livraison apres lequel ajouter votre nouveau point de livraison<br>sur le plan ou dans la liste</html>");
		}
	}
	
	@Override
	public void undo(Controleur controleur, ListeDeCommandes listeDeCommandes, Fenetre fenetre)
	{
		controleur.setEtatCourant( controleur.etatModificationTournee);
		fenetre.setModeModificationTournee();
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

}
