package controleur;

public interface Commande {
	/**
	 * Execute la commande
	 */
	public void doCommande();

	/**
	 * Execute la commande inverse
	 */
	public void undoCommande();

}
