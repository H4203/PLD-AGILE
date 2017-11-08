package controleur;

import java.awt.Point;

import javax.swing.JOptionPane;

import algorithme.CalculateurTournee;
import donnees.FeuilleDeRoute;
import donnees.ParseurException;
import donnees.XMLParseur;
import modeles.Plan;
import modeles.Tournee;
import vue.Fenetre;
import modeles.DemandeLivraison;
import modeles.Livraison;

/**
 * 
 * @author H4203
 * Traduit les interactions entre l’utilisateur et la vue en
 * actions pour le modele ou la vue
 */
public class Controleur 
{	
	protected XMLParseur parseur;
	protected CalculateurTournee calculateurTournee;
	protected Fenetre fenetre;
	protected Plan plan;
	protected Tournee tournee;
	protected DemandeLivraison demandeLivraison;
	protected String etat;
	protected Etat etatCourant;
	protected FeuilleDeRoute feuilleDeRoute;
	
	// differents etats possible
	protected EtatChargementPlan etatChargementPlan  = new EtatChargementPlan();
	protected EtatChargementLivraison etatChargementLivraison  = new EtatChargementLivraison();
	protected EtatCalculTournee etatCalculTournee = new EtatCalculTournee();
	protected EtatModificationTournee etatModificationTournee  = new EtatModificationTournee();
	protected EtatGenererFeuilleDeRoute etatGenererFeuilleDeRoute = new EtatGenererFeuilleDeRoute();
	protected EtatAjoutLivraison1 etatAjoutLivraison1 = new EtatAjoutLivraison1();
	protected EtatAjoutLivraison2 etatAjoutLivraison2 = new EtatAjoutLivraison2();
	protected EtatSupprimerLivraison etatSupprimerLivraison = new EtatSupprimerLivraison();	
	protected EtatIntervertirLivraisons1 etatIntervertirLivraisons1 = new EtatIntervertirLivraisons1();
	protected EtatIntervertirLivraisons2 etatIntervertirLivraisons2 = new EtatIntervertirLivraisons2();
	private ListeDeCommandes listeDeCommandes;
	
	
	/**
	 * Cree le controleur de l'application
	 */
	public Controleur() 
	{
		plan = null;
		demandeLivraison = null;
		tournee = null;
		listeDeCommandes = new ListeDeCommandes();
		
		try 
		{
			parseur = new XMLParseur ();
			fenetre = new Fenetre (this, plan, demandeLivraison, tournee);
		} catch (ParseurException e) {
			JOptionPane.showMessageDialog(fenetre, e.getMessage(), "Erreur lors du parsage", JOptionPane.ERROR_MESSAGE);
		}
	}
	/**
	 * lance l'application
	 */
	public void launch () {
		setEtatCourant ( etatChargementPlan );
		fenetre.setModeChargementPlan();
	}
	/**
	 * definit l'etat courant
	 * @param etat = le nouvel etat
	 */
	protected void setEtatCourant(Etat etat){
		etatCourant = etat;
	}
	/**
	 * Methode appelee par fenetre apres un clic sur le bouton "Charger Plan"
	 * @param chemin = le chemin d'acces du plan a ouvrir
	 */
	public void chargerPlan( String chemin)
	{
		etatCourant.chargerPlan(this, fenetre, chemin);
	}
	/**
	 * Methode appelee par fenetre apres un clic sur le bouton "Charger Demande Livraison"
	 * @param chemin = le chemin d'acces de la demande de livraison
	 */
	public void chargerDemandeLivraison( String chemin)
	{
		etatCourant.chargerDemandeLivraison(this, fenetre, chemin);
	}
	/**
	 * Methode appelee par fenetre apres un clic sur le bouton "Modifier Tournee" 
	 */
	public void modifierTournee()
	{
		etatCourant.modificationTournee(this, fenetre);
	}
	/**
	 * Methode appelee par fenetre apres un clic sur le bouton "Calculer Tournee"
	 */
	public void calculerTournee()
	{
		etatCourant.calculerTournee(this, fenetre);
	}
	/**
	 * Methode appelee par fenetre apres un clic sur le bouton "Ajouter une Livraison"
	 */
	public void ajouterLivraison()
	{
		etatCourant.ajouterLivraison(this, fenetre);
	}
	/**
	 * Methode appelee par fenetre apres un clic sur le bouton "Retirer une Livraison" 
	 */
	public void supprimerLivraison( )
	{
		etatCourant.supprimerLivraison(this, fenetre);
	}
	/**
	 * Methode appelee par fenetre apres un clic sur le bouton "Echanger 2 Livraisons"
	 */
	public void intervertirLivraisons()
	{
		etatCourant.intervertirLivraisons(this, fenetre);
	}
	/**
	 * Methode appelee par fenetre apres un clic sur un element de la liste de la vue textuelle
	 */
	public void modificationDansLaListe ( )
	{
		etatCourant.modificationDansLaListe(this, listeDeCommandes);
	}
	/**
	 * Methode appelee par fenetre apres un clic gauche sur un point de la vue graphique
	 * @param point = coordonnees du plan correspondant au point clique
	 */
	public void clicGauche(Point point)
	{
		etatCourant.clicgauche(this, fenetre, point, listeDeCommandes);
	}
	/**
	 * Methode appelee par fenetre apres avoir bouge  la souris en maintenant appuye le clic gauche
	 * @param delta = coordonnees du plan correspondant au point pointe par la souris
	 */
	public void mouseDrag(Point delta)
	{
		etatCourant.mouseDrag(this, delta);
	}
	/**
	 * Methode appelee par fenetre apres avoir bouge la molette de la souris
	 * @param point = coordonnees du plan correspondant au point pointe par la souris
	 * @param steps = le nombre de pas de la molette
	 */
	public void mouseWheel(int steps, Point point)
	{
		etatCourant.mouseWheel(this, steps, point);
	}
	/**
	 * Methode appelee par fenetre apres un clic sur le bouton "Annuler"
	 */
	public void undo ()
	{
		etatCourant.undo(this, listeDeCommandes, fenetre);
	}
	/**
	 * Methode appelee par fenetre apres un clic sur le bouton "Retablir"
	 */
	public void redo ()
	{
		etatCourant.redo(this, listeDeCommandes, fenetre);
	}
	/**
	 * Methode appelee par fenetre apres un clic sur le bouton "Valider Tournee"
	 */
	public void validerTournee() 
	{
		etatCourant.validerTournee(this, fenetre);
	}
	/**
	 * Methode appelee par fenetre apres un clic sur le bouton "Generer Feuille de Route"
	 * @param chemin = le chemin d'acces du fichier a creer
	 */
	public void genererFeuilleDeRoute(String chemin) 
	{
		etatCourant.genererFeuilleDeRoute(this, fenetre, chemin);
	}
	/**
	 * fait le lien entre tolerance en pixel et tolerance en metre
	 * @return
	 * la tolerance en pixel
	 */
	public int getToleranceClic()
	{
		return fenetre.getVueGraphique().getToleranceClic();
	}

	
}

