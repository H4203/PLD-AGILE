package controleur;

import modeles.Livraison;

public interface Etat {
	
	public void chargerPlan( String chemin);
	public void chargerLivraison( String chemin);
	public void ajouterLivraison(int positonPrecedente, Livraison livraison);
	public void suprimerLivraison( int positon );
	public void intervertirLivraison( Livraison livraison1, Livraison livraison2);

}
