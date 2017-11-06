package controleur;

import java.awt.Point;
import java.util.List;
import vue.Fenetre;

import modeles.Intersection;
import modeles.Livraison;

public class EtatSupprimerLivraison extends EtatDefault{

	@Override
	public void clicgauche(Controleur controleur, Fenetre fenetre, Point point, ListeDeCommandes listeDeCommandes)
	{
		controleur.plan.getAtPoint(point, controleur.getToleranceClic());
		Intersection pointAsupprimer = controleur.plan.getSelectedIntersection();
		
		// cas livraison
		List<Livraison> Listelivraisons = controleur.tournee.getLivraisonsOrdonnees();
		for ( int i = 0; i < Listelivraisons.size() ; i++)
		{
			Livraison livraison = Listelivraisons.get(i);
			
			if ( livraison.getIntersection().equals( pointAsupprimer ) )
			{
				listeDeCommandes.ajoute( new CommandeSupprimerLivraison ( livraison, controleur.calculateurTournee ));
				controleur.setEtatCourant(controleur.etatModificationTournee);
				controleur.fenetre.setModeModificationTournee();
				// evite une trop grande supression de point
				break;
			}
		}
	}
	
	@Override
	public void undo(ListeDeCommandes listeDeCommandes, Fenetre fenetre)
	{
		fenetre.setModeModificationTournee();
	}
}
