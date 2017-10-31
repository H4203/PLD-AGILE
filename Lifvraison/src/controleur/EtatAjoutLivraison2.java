package controleur;

import java.awt.Point;
import java.util.List;

import modeles.Intersection;
import modeles.Livraison;

public class EtatAjoutLivraison2 extends EtatDefault{
	
	protected Intersection pointDeLivraison;

	@Override
	public void clicgauche(Controleur controleur, Point point)
	{
		
		controleur.plan.getAtPoint(point);
		Intersection pointPrecedant = controleur.plan.getSelectedIntersection();
		
		// cas entrepot
		if ( controleur.demandeLivraison.getEntrepot().equals( pointPrecedant ) )
		{
			controleur.setEtatCourant( controleur.etatModificationTournee);
			controleur.calculateurTournee.ajouterLivraison(0, new Livraison (pointDeLivraison , 120));
			controleur.fenetre.setModeModificationTournee();
		}
		// cas livraison
		List<Livraison> Listelivraisons = controleur.demandeLivraison.getLivraisons();
		for ( int i = 0; i < Listelivraisons.size() ; i++)
		{
			Livraison livraison = Listelivraisons.get(i);
			
			if ( livraison.getIntersection().equals( pointPrecedant ) )
			{
				controleur.setEtatCourant( controleur.etatModificationTournee);
				controleur.calculateurTournee.ajouterLivraison(i+1, new Livraison (pointDeLivraison , 120));
				controleur.fenetre.setModeModificationTournee();
				// evite une trop grande creation de point
				break;
			}
		}
	}
}
