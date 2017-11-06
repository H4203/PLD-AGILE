package controleur;

import java.awt.Point;

import modeles.Intersection;

public class EtatAjoutLivraison1 extends EtatDefault{

	@Override
	public void clicgauche(Controleur controleur, Point point, ListeDeCommandes listeDeCommandes)
	{
		
		controleur.plan.getAtPoint(point, controleur.getToleranceClic());
		Intersection pointDeLivraison = controleur.plan.getSelectedIntersection();
		if (pointDeLivraison != null)
		{
			controleur.setEtatCourant( controleur.etatAjoutLivraison2);
			controleur.etatAjoutLivraison2.pointDeLivraison = pointDeLivraison;
			System.out.println("phase 2");
		}
		
		
	}

}
