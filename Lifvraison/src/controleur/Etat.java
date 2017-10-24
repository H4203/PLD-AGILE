package controleur;

import modeles.Livraison;
import vue.Fenetre;

public interface Etat {
	
	public void chargerPlan( String chemin);
	public void chargerLivraison( String chemin);
	public void ajouterLivraison(Controleur controleur, Fenetre fenetre);
	public void suprimerLivraison(Controleur controleur, Fenetre fenetre, int positon );
	public void intervertirLivraison(Controleur controleur, Fenetre fenetre, Livraison livraison1, Livraison livraison2);
	public void suivant (Controleur controleur, Fenetre fenetre);
	public void precedent (Controleur controleur, Fenetre fenetre);
	public void accueil (Controleur controleur, Fenetre fenetre);
	public void clicgauche ( Controleur controleur, Fenetre fenetre, int positonPrecedente, Livraison livraison);
	public void validationTournee (Controleur controleur, Fenetre fenetre);

}
