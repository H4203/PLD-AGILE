package controleur;

import algorithme.CalculateurTournee;

public class CommandeIntervertirCommande implements Commande{
	
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

		calculateurTournee.echangerDeuxLivraison(positionLivraison1, positionLivraison2);
	}

	@Override
	public void undoCommande() {
		calculateurTournee.echangerDeuxLivraison(positionLivraison2, positionLivraison1);
		
	}
}
