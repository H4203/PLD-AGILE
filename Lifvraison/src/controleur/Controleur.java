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
	protected EtatAjoutLivraison1 etatAjoutLivraison1 = new EtatAjoutLivraison1();
	protected EtatAjoutLivraison2 etatAjoutLivraison2 = new EtatAjoutLivraison2();
	protected EtatSupprimerLivraison etatSupprimerLivraison = new EtatSupprimerLivraison();	
	private ListeDeCommandes listeDeCommandes;
	
	
	/**
	 * 
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
	public void launch () {
		setEtatCourant ( etatChargementPlan );
		fenetre.setModeChargementPlan();
	}
	
	protected void setEtatCourant(Etat etat){
		etatCourant = etat;
	}
	
	public void chargerPlan( String chemin)
	{
		etatCourant.chargerPlan(this, fenetre, chemin);
	}
	public void chargerDemandeLivraison( String chemin)
	{
		etatCourant.chargerDemandeLivraison(this, fenetre, chemin);
		etatCourant.calculerTournee(this, fenetre);
	}
	public void modifierTournee()
	{
		etatCourant.modificationTournee(this, fenetre);
	}
	public void calculerTournee (  )
	{
		etatCourant.calculerTournee(this, fenetre);
	}
	public void ajouterLivraison()
	{
		etatCourant.ajouterLivraison(this, fenetre);
	}
	public void supprimerLivraison( )
	{
		etatCourant.supprimerLivraison(this, fenetre);
	}
	public void intervertirLivraison(Livraison livraison1, Livraison livraison2)
	{
		etatCourant.intervertirLivraison(this, fenetre, livraison1, livraison2);
	}
	public void clicgauche ( int positonPrecedente, Livraison livraison)
	{
		etatCourant.clicgauche(this, fenetre, positonPrecedente, livraison);
	}
	
	// Start
	public void clicGauche(Point point)
	{
		etatCourant.clicgauche(this, fenetre, point, listeDeCommandes);
	}
	
	public void mouseDrag(Point delta)
	{
		etatCourant.mouseDrag(this, delta);
	}
	
	public void mouseWheel(int steps, Point point)
	{
		etatCourant.mouseWheel(this, steps, point);
	}
	// End
	
	public void undo ()
	{
		etatCourant.undo(this, listeDeCommandes, fenetre);
	}
	public void redo ()
	{
		etatCourant.redo(this, listeDeCommandes, fenetre);
	}
	public void validerTournee() {
		etatCourant.validerTournee(this, fenetre);
		
	}
	
	public void gererFeuilleDeRoute() {
		etatCourant.genererFeuilleDeRoute(this, fenetre);
	}
	public int getToleranceClic()
	{
		return fenetre.getVueGraphique().getToleranceClic();
	}

	
}

