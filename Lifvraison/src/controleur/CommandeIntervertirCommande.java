package controleur;

import algorithme.CalculateurTournee;


public class CommandeIntervertirCommande implements Commande {
	private CalculateurTournee calculateurTournee;
	private int positionLivraison1;
	private int positionLivraison2;

	/**
	 * Cree la commande qui intervertit la livraison livraison1 a la position
	 * position1 et la livraison livraison2 a la position position2
	 * 
	 * @param positionLivraison1
	 * @param positionLivraison2
	 * @param calculateurTournee
	 */
	CommandeIntervertirCommande(int positionLivraison1, int positionLivraison2, CalculateurTournee calculateurTournee) {
		this.calculateurTournee = calculateurTournee;
		this.positionLivraison1 = positionLivraison1;
		this.positionLivraison2 = positionLivraison2;
	}

	@Override
	public void doCommande() {
		// recalcul avec algo
		calculateurTournee.echangerDeuxLivraison(positionLivraison1, positionLivraison2);
	}

	@Override
	public void undoCommande() {
		calculateurTournee.echangerDeuxLivraison(positionLivraison2, positionLivraison1);

	}
}
