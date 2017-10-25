package controleur;

import algorithme.CalculateurTournee;
import modeles.DemandeLivraison;
import modeles.Livraison;
import modeles.Tournee;

public class CommandeSupprimerLivraison implements Commande {

	private Livraison livraison;
	private DemandeLivraison demandeLivraison;
	private Tournee tournee;
	private CalculateurTournee calculateurTournee;
	private int position;
	
	CommandeSupprimerLivraison ( Livraison livraison, int position, DemandeLivraison demandeLivraison, Tournee tournee)
	{
		this.livraison = livraison;
		this.demandeLivraison = demandeLivraison;
		this.tournee = tournee;
		this.position = position;
	}
	
	@Override
	public void doCommande() {
		// TODO Auto-generated method stub
		calculateurTournee.supprimerLivraison(livraison);
	}

	@Override
	public void undoCommande() {
		// TODO Auto-generated method stub
		calculateurTournee.ajouterLivraison(position, livraison);
	}
	
	

}
