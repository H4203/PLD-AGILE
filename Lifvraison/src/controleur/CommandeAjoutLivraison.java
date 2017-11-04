package controleur;

import algorithme.CalculateurTournee;
import modeles.DemandeLivraison;
import modeles.Livraison;
import modeles.Tournee;

public class CommandeAjoutLivraison implements Commande {

	private Livraison livraison;
	private CalculateurTournee calculateurTournee;
	private int position;
	
	CommandeAjoutLivraison ( int position, Livraison livraison, CalculateurTournee calculateurTournee)
	{
		this.livraison = livraison;
		this.calculateurTournee = calculateurTournee;
		this.position = position;
	}
	
	@Override
	public void doCommande() {
		//demandeLivraison.ajouterLivraison(livraison);
		// recalcul avec algo
		calculateurTournee.ajouterLivraison(position, livraison);
	}

	@Override
	public void undoCommande() {
		calculateurTournee.supprimerLivraison(livraison);
		
	}

}
