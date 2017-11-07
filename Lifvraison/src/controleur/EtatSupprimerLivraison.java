package controleur;

import java.awt.Point;
import java.util.List;

import modeles.Intersection;
import modeles.Livraison;

public class EtatSupprimerLivraison extends EtatDefault{

	@Override
	public void clicgauche(Controleur controleur, Point point, ListeDeCommandes listeDeCommandes)
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
				listeDeCommandes.ajoute( new CommandeSupprimerLivraison ( livraison, controleur.calculateurTournee ));
				controleur.setEtatCourant(controleur.etatModificationTournee);
				controleur.fenetre.setModeModificationTournee();
				// evite une trop grande supression de point
				break;
			}
		}
	}
	
	public void modificationDansLaListe(Controleur controleur, ListeDeCommandes listeDeCommandes)
	{
		
		int index = controleur.fenetre.getVueTextuelle().getListPanel().getCurrentSelection() - 1;
		List<Livraison> Listelivraisons = controleur.tournee.getLivraisonsOrdonnees();
		Livraison livraison = Listelivraisons.get(index);
		
		listeDeCommandes.ajoute( new CommandeSupprimerLivraison ( livraison, controleur.calculateurTournee ));
		controleur.setEtatCourant(controleur.etatModificationTournee);
		controleur.fenetre.setModeModificationTournee();
		
	}
}
