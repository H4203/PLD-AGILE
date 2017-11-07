package controleur;

import java.awt.Point;

import modeles.Intersection;
import vue.Fenetre;

public class EtatIntervertirLivraisons1 extends EtatDefault{
	@Override
	public void clicgauche(Controleur controleur, Fenetre fenetre, Point point, ListeDeCommandes listeDeCommandes)
	{
		System.out.println("test1");
		controleur.plan.getAtPoint(point, controleur.getToleranceClic());
		Intersection livraison1 = controleur.plan.getSelectedIntersection();
		if (livraison1 != null)
		{
			controleur.setEtatCourant( controleur.etatIntervertirLivraisons2);
			controleur.etatIntervertirLivraisons2.livraison1 = livraison1;
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
