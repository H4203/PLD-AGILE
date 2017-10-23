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
	private int position;
	
	CommandeAjoutLivraison ( Livraison livraison, int position, DemandeLivraison demandeLivraison, Tournee tournee, CalculateurTournee calculateurTournee)
	{
		this.livraison = livraison;
		this.demandeLivraison = demandeLivraison;
		this.tournee = tournee;
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
