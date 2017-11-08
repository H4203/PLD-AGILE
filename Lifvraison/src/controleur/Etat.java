package controleur;

import java.awt.Point;

import modeles.Livraison;
import vue.Fenetre;

public interface Etat {

	/**
	 * Methode appelee par controleur apres un clic sur le bouton "Charger Plan"
	 * 
	 * @param controleur
	 * @param fenetre
	 */
	public void chargerPlan(Controleur controleur, Fenetre fenetre, String chemin);

	/**
	 * Methode appelee par controleur apres un clic sur le bouton "Charger Demande
	 * Livraison"
	 * 
	 * @param controleur
	 * @param fenetre
	 */
	public void chargerDemandeLivraison(Controleur controleur, Fenetre fenetre, String chemin);

	/**
	 * Methode appelee par controleur apres un clic sur le bouton "Calculer Tournee"
	 * 
	 * @param controleur
	 * @param fenetre
	 */
	public void calculerTournee(Controleur controleur, Fenetre fenetre);

	/**
	 * Methode appelee par controleur apres un clic sur le bouton "Ajouter une
	 * Livraison"
	 * 
	 * @param controleur
	 * @param fenetre
	 */
	public void ajouterLivraison(Controleur controleur, Fenetre fenetre);

	/**
	 * Methode appelee par controleur apres un clic sur le bouton "Retirer une
	 * Livraison"
	 * 
	 * @param controleur
	 * @param fenetre
	 */
	public void supprimerLivraison(Controleur controleur, Fenetre fenetre);

	/**
	 * Methode appelee par controleur apres un clic sur le bouton "Echanger 2
	 * Livraisons"
	 * 
	 * @param controleur
	 * @param fenetre
	 */
	public void intervertirLivraisons(Controleur controleur, Fenetre fenetre);

	/**
	 * Methode appelee par controleur apres un clic sur le bouton "Valider Tournee"
	 * 
	 * @param controleur
	 * @param fenetre
	 */
	public void validerTournee(Controleur controleur, Fenetre fenetre);

	/**
	 * Methode appelee par fenetre apres un clic gauche sur un point de la vue
	 * graphique
	 * 
	 * @param controleur
	 * @param fenetre
	 * @param listeDeCommandes
	 * @param point
	 *            = coordonnees du plan correspondant au point clique
	 */
	public void clicgauche(Controleur controleur, Fenetre fenetre, Point point, ListeDeCommandes listeDeCommandes);

	/**
	 * Methode appelee par fenetre apres avoir bouge la souris en maintenant appuye
	 * le clic gauche
	 * 
	 * @param controleur
	 * @param delta
	 *            = coordonnees du plan correspondant au point pointe par la souris
	 */
	public void mouseDrag(Controleur controleur, Point delta);

	/**
	 * Methode appelee par fenetre apres avoir bouge la molette de la souris
	 * 
	 * @param controleur
	 * @param point
	 *            = coordonnees du plan correspondant au point pointe par la souris
	 * @param steps
	 *            = le nombre de pas de la molette
	 */
	public void mouseWheel(Controleur controleur, int steps, Point point);

	/**
	 * Methode appelee par fenetre apres un clic sur le bouton "Annuler"
	 * 
	 * @param controleur
	 * @param fenetre
	 * @param listeDeCommandes
	 */
	public void undo(Controleur controleur, ListeDeCommandes listeDeCommandes, Fenetre fenetre);

	/**
	 * Methode appelee par fenetre apres un clic sur le bouton "Retablir"
	 * 
	 * @param controleur
	 * @param fenetre
	 * @param listeDeCommandes
	 */
	public void redo(Controleur controleur, ListeDeCommandes listeDeCommandes, Fenetre fenetre);

	/**
	 * Methode appelee par controleur apres un clic sur le bouton "Modifier Tournee"
	 * 
	 * @param controleur
	 * @param fenetre
	 */
	public void modificationTournee(Controleur controleur, Fenetre fenetre);

	/**
	 * Methode appelee par controleur apres un clic sur le bouton "Generer Feuille
	 * de Route"
	 * 
	 * @param controleur
	 * @param fenetre
	 * @param chemin
	 *            = le chemin d'acces du fichier a creer
	 */
	public void genererFeuilleDeRoute(Controleur controleur, Fenetre fenetre, String chemin);

	/**
	 * Methode appelee par fenetre apres un clic sur un element de la liste de la
	 * vue textuelle
	 * 
	 * @param controleur
	 * @param listeDeCommandes
	 */
	public void modificationDansLaListe(Controleur controleur, ListeDeCommandes listeDeCommandes);
}
