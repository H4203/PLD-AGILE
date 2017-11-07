package controleur;

import java.awt.Point;
import java.util.List;

import modeles.Intersection;
import modeles.Livraison;
import vue.Fenetre;

public class EtatIntervertirLivraisons2 extends EtatDefault{
	
	protected Intersection livraison1;

	@Override
	public void clicgauche(Controleur controleur, Fenetre fenetre, Point point, ListeDeCommandes listeDeCommandes)
	{
		
		int posLivraison1 = -1;
		int posLivraison2 = -1;
		controleur.plan.getAtPoint(point, controleur.getToleranceClic());
		Intersection livraison2 = controleur.plan.getSelectedIntersection();
		
		// cas livraison
		List<Livraison> Listelivraisons = controleur.tournee.getLivraisonsOrdonnees();
		for ( int i = 0; i < Listelivraisons.size() ; i++)
		{
			Livraison livraison = Listelivraisons.get(i);
			if ( livraison.getIntersection().equals( livraison1 ) )
			{
				posLivraison1 = i;
			}
			if ( livraison.getIntersection().equals( livraison2 ) )
			{
				posLivraison2 = i;
			}
		}
		System.out.println(posLivraison1);
		System.out.println(posLivraison2);
		controleur.setEtatCourant( controleur.etatModificationTournee);
		if ( posLivraison1 != posLivraison2  && posLivraison1 != -1 && posLivraison2 != -1)
		{
			listeDeCommandes.ajoute( new CommandeIntervertirCommande ( posLivraison1, posLivraison2 , controleur.calculateurTournee));
			System.out.println("test");
		}
		controleur.fenetre.setModeModificationTournee();
	}
	
	@Override
	public void undo(Controleur controleur, ListeDeCommandes listeDeCommandes, Fenetre fenetre)
	{
		controleur.setEtatCourant(controleur.etatModificationTournee);
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
