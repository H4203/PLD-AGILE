
package controleur;

import algorithme.CalculateurTournee;
import modeles.Livraison;

public class CommandeSupprimerLivraison implements Commande {

	private Livraison livraison;
	private CalculateurTournee calculateurTournee;
	private int position;

	/**
	 * Cree la commande qui supprime la livraison livraison
	 * 
	 * @param livraison
	 * @param calculateurTournee
	 */
	CommandeSupprimerLivraison(Livraison livraison, CalculateurTournee calculateurTournee) {
		this.livraison = livraison;
		this.calculateurTournee = calculateurTournee;
	}

	@Override
	public void doCommande() {
		// TODO Auto-generated method stub
		position = calculateurTournee.supprimerLivraison(livraison);
	}

	@Override
	public void undoCommande() {
		// TODO Auto-generated method stub
		calculateurTournee.ajouterLivraison(position - 1, livraison);
	}

}
