package controleur;

import algorithme.CalculateurTournee;
import modeles.Livraison;

public class CommandeIntervertirCommande implements Commande{
	
	private Livraison livraison;
	private CalculateurTournee calculateurTournee;
	private int positionLivraison1;
	private int positionLivraison2;
	
	CommandeIntervertirCommande ( int positionLivraison1, int positionLivraison2, CalculateurTournee calculateurTournee)
	{
		this.calculateurTournee = calculateurTournee;
		this.positionLivraison1 = positionLivraison1;
		this.positionLivraison2 = positionLivraison2;
	}
	
	@Override
	public void doCommande() {
		//demandeLivraison.ajouterLivraison(livraison);
		// recalcul avec algo
		calculateurTournee.echangerDeuxLivraison(positionLivraison1, positionLivraison2);
	}

	@Override
	public void undoCommande() {
		calculateurTournee.echangerDeuxLivraison(positionLivraison2, positionLivraison1);
		
	}
}