package controleur;

import java.awt.Point;
import java.util.List;

import modeles.Intersection;
import modeles.Livraison;

public class EtatSupprimerLivraison extends EtatDefault{

	@Override
	public void clicgauche(Controleur controleur, Point point)
	{
		
		controleur.plan.getAtPoint(point);
		Intersection pointAsupprimer = controleur.plan.getSelectedIntersection();
		
		// cas livraison
		List<Livraison> Listelivraisons = controleur.tournee.getLivraisonsOrdonnees();
		for ( int i = 0; i < Listelivraisons.size() ; i++)
		{
			Livraison livraison = Listelivraisons.get(i);
			
			if ( livraison.getIntersection().equals( pointAsupprimer ) )
			{
				controleur.calculateurTournee.supprimerLivraison(livraison);
				controleur.setEtatCourant(controleur.etatModificationTournee);
				controleur.fenetre.setModeModificationTournee();
				// evite une trop grande supression de point
				break;
			}
		}
	}
}
