package controleur;

import algorithme.CalculateurTournee;
import modeles.DemandeLivraison;
import modeles.Livraison;
import modeles.Tournee;

public class CommandeSupprimerLivraison implements Commande {

	private Livraison livraison;
	private CalculateurTournee calculateurTournee;
	private int position;
	
	CommandeSupprimerLivraison ( Livraison livraison, CalculateurTournee calculateurTournee)
	{
		this.livraison = livraison;
		this.calculateurTournee = calculateurTournee;
	}
	
	@Override
	public void doCommande() {
		position = calculateurTournee.supprimerLivraison(livraison)	;
	}

	@Override
	public void undoCommande() {
		calculateurTournee.ajouterLivraison(position, livraison);
	}
	
	

}
