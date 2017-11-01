package controleur;

import java.awt.Point;
import java.util.List;

import modeles.Intersection;
import modeles.Livraison;

public class EtatAjoutLivraison2 extends EtatDefault{
	
	protected Intersection pointDeLivraison;

	@Override
	public void clicgauche(Controleur controleur, Point point, ListeDeCommandes listeDeCommandes)
	{
		
		controleur.plan.getAtPoint(point);
		Intersection pointPrecedant = controleur.plan.getSelectedIntersection();
		
		// cas entrepot
		if ( controleur.demandeLivraison.getEntrepot().equals( pointPrecedant ) )
		{
			controleur.setEtatCourant( controleur.etatModificationTournee);
			Livraison nouvelleLivraison = new Livraison (pointDeLivraison , 120);
			listeDeCommandes.ajoute( new CommandeAjoutLivraison ( 0, nouvelleLivraison, controleur.calculateurTournee));
			controleur.fenetre.setModeModificationTournee();
		}
		// cas livraison
		List<Livraison> Listelivraisons = controleur.tournee.getLivraisonsOrdonnees();
		for ( int i = 0; i < Listelivraisons.size() ; i++)
		{
			Livraison livraison = Listelivraisons.get(i);
			
			if ( livraison.getIntersection().equals( pointPrecedant ) )
			{
				controleur.setEtatCourant( controleur.etatModificationTournee);
				Livraison nouvelleLivraison = new Livraison (pointDeLivraison , 120);
				listeDeCommandes.ajoute( new CommandeAjoutLivraison ( i+1, nouvelleLivraison, controleur.calculateurTournee));
				controleur.fenetre.setModeModificationTournee();
				// evite une trop grande creation de point
				break;
			}
		}
	}
}
