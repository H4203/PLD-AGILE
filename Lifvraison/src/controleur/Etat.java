package controleur;

import java.awt.Point;

import modeles.Livraison;
import vue.Fenetre;

public interface Etat {
	
	public void chargerPlan( Controleur controleur, Fenetre fenetre, String chemin);
	public void chargerDemandeLivraison( Controleur controleur, Fenetre fenetre, String chemin);
	public void calculerTournee ( Controleur controleur, Fenetre fenetre );
	public void ajouterLivraison(Controleur controleur, Fenetre fenetre);
	public void supprimerLivraison(Controleur controleur, Fenetre fenetre );
	public void intervertirLivraisons(Controleur controleur, Fenetre fenetre);
	public void clicgauche ( Controleur controleur, Fenetre fenetre, int positonPrecedente, Livraison livraison);
	public void validerTournee (Controleur controleur, Fenetre fenetre);
	public void clicgauche(Controleur controleur, Point point);
	public void clicgauche(Controleur controleur, Fenetre fenetre, Point point, ListeDeCommandes listeDeCommandes);
	public void mouseDrag(Controleur controleur, Point delta);
	public void mouseWheel(Controleur controleur, int steps, Point point);
	public void undo (Controleur controleur, ListeDeCommandes listeDeCommandes, Fenetre fenetre);
	public void redo (Controleur controleur, ListeDeCommandes listeDeCommandes, Fenetre fenetre);
	public void modificationTournee(Controleur controleur, Fenetre fenetre);
	public void genererFeuilleDeRoute(Controleur controleur, Fenetre fenetre, String chemin);
}
