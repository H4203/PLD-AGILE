package controleur;

import algorithme.CalculateurTournee;
import modeles.DemandeLivraison;
import modeles.Livraison;
import modeles.Tournee;

public class CommandeAjoutLivraison implements Commande {

	private Livraison livraison;
	private DemandeLivraison demandeLivraison;
	private Tournee tournee;
	private CalculateurTournee calculateurTournee;
	
	CommandeAjoutLivraison ( Livraison livraison, DemandeLivraison demandeLivraison, Tournee tournee, CalculateurTournee calculateurTournee)
	{
		this.livraison = livraison;
		this.demandeLivraison = demandeLivraison;
		this.tournee = tournee;
		this.calculateurTournee = calculateurTournee;
	}
	
	@Override
	public void doCommande() {
		//demandeLivraison.ajouterLivraison(livraison);
		// recalcul avec algo
		calculateurTournee.ajouterLivraison(livraison);
	}

	@Override
	public void undoCommande() {
		calculateurTournee.supprimerLivraison(livraison);
		
	}

}
