package controleur;

import javax.swing.JOptionPane;

import algorithme.CalculateurTournee;
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
	
	// differents etats possible
	protected EtatAccueil etatAccueil = new EtatAccueil();
	protected EtatChargementPlan etatChargementPlan  = new EtatChargementPlan();
	protected EtatChargementLivraison etatChargementLivraison  = new EtatChargementLivraison();
	protected EtatCalculTournee etatCalculTournee  = new EtatCalculTournee();
	protected EtatModificationTournee etatModificationTournee  = new EtatModificationTournee();
	protected EtatAjoutLivraison1 etatAjoutLivraison1 = new EtatAjoutLivraison1();
	protected EtatAjoutLivraison2 etatAjoutLivraison2 = new EtatAjoutLivraison2();
	protected EtatSupprimerLivraison1 etatSupprimerLivraison1 = new EtatSupprimerLivraison1();
	protected EtatSupprimerLivraison2 etatSupprimerLivraison2 = new EtatSupprimerLivraison2();
	protected EtatModeValidation etatModeValidation = new EtatModeValidation();
	
	/**
	 * 
	 */
	public Controleur() {
		
		
		
		try {
			parseur = new XMLParseur ();
			fenetre = new Fenetre ( this );
			etatCourant = etatAccueil;
		} catch (ParseurException e) {
			JOptionPane.showMessageDialog(fenetre, e.getMessage(), "Erreur lors du parsage", JOptionPane.ERROR_MESSAGE);
		}
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
	}
	public void calculerTournee (  )
	{
		etatCourant.calculerTournee(this, fenetre);
	}
	public void ajouterLivraison()
	{
		etatCourant.ajouterLivraison(this, fenetre);
	}
	public void suprimerLivraison( int positon )
	{
		etatCourant.suprimerLivraison(this, fenetre, positon);
	}
	public void intervertirLivraison(Livraison livraison1, Livraison livraison2)
	{
		etatCourant.intervertirLivraison(this, fenetre, livraison1, livraison2);
	}
	public void suivant ()
	{
		etatCourant.suivant(this, fenetre);
	}
	public void precedent ()
	{
		etatCourant.precedent(this, fenetre);
	}
	public void accueil ()
	{
		etatCourant.accueil(this, fenetre);
	}
	public void clicgauche ( int positonPrecedente, Livraison livraison)
	{
		etatCourant.clicgauche(this, fenetre, positonPrecedente, livraison);
	}
	
	public void validerTournee() {
		etatCourant.validerTournee(this, fenetre);
		
	}

	public void chargementPlan() {
		etatCourant.chargementPlan(this, fenetre);
		
	}

	public void chargementDemandeLivraison() {
		etatCourant.chargementDemandeLivraison(this, fenetre);
		
	}

	public void calculTournee() {
		etatCourant.calculTournee(this, fenetre);
		
	}

	public void modificationTournee() {
		etatCourant.modificationTournee(this, fenetre);
		
	}
	
	public void validationTournee() {
		etatCourant.validationTournee(this, fenetre);
	}
	
	/*
	public void run()
	{
		setModeAccueil();
	}
	
	public void setModeAccueil()
	{
		fenetre.setModeAccueil();
		etat = "Accueil";
	}
	
	public void setModeChargementPlan()
	{
		if (plan == null)
		{
			fenetre.setModeChargementPlan();
		}
		
		else
		{
			fenetre.setModeChargementPlan(plan);	
		}
		
		etat = "ChargementPlan";
		
		

	}
	
	public void setModeChargementPlan(String cheminPlan)
	{
		try {
			plan = parseur.chargerPlan( cheminPlan );
			fenetre.setModeChargementPlan( plan );
		} catch (ParseurException e) {
			JOptionPane.showMessageDialog(fenetre, e.getMessage(), "Erreur lors du parsage", JOptionPane.ERROR_MESSAGE);
		}
		demandeLivraison = null;
		tournee = null;
		
		fenetre.setModeChargementPlan(plan);
		etat = "ChargementPlan";
	}
	
	public void setModeChargementDemandeLivraison()
	{
		if (demandeLivraison == null)
		{
			fenetre.setModeChargementDemandeLivraison();
		}
		
		else
		{
			fenetre.setModeChargementDemandeLivraison(demandeLivraison);	
		}
		
		etat = "ChargementDemandeLivraison";
	}
	
	public void setModeChargementDemandeLivraison(String cheminDemandeLivraisons)
	{	
		
		try {
			demandeLivraison = parseur.chargerLivraison( cheminDemandeLivraisons, plan.getListeIntersection() );
		} catch (ParseurException e) {
			JOptionPane.showMessageDialog(fenetre, e.getMessage(), "Erreur lors du parsage", JOptionPane.ERROR_MESSAGE);
		}	
		tournee = null;
		
		fenetre.setModeChargementDemandeLivraison(demandeLivraison);
		etat = "ChargementDemandeLivraison";
	}
	
	public void setModeCalculTournee()
	{
		if (tournee == null)
		{
			fenetre.setModeCalculTournee();
		}
		
		else
		{
			fenetre.setModeCalculTournee(tournee);	
		}
		
		etat = "CalculTournee";
	}
	
	public void setModeCalculTournee(String calcul)
	{		
		tournee = new Tournee(plan, demandeLivraison);
		calculateurTournee = new CalculateurTournee(tournee);
		calculateurTournee.run();
		
		fenetre.setModeCalculTournee(tournee);
		etat = "CalculTournee";
	}
	
	public void setModeModificationTournee()
	{
		fenetre.setModeModificationTournee(tournee);
		etat = "ModificationTournee";
	}
	
	public void setModeValidationTournee()
	{
		fenetre.setModeValidationTournee();
		etat = "ValidationTournee";
	}	
	
	public void setModeSuivant()
	{
		switch (etat)
		{
			case "Accueil" :
			{
				setModeChargementPlan();
				break;
			}
			case "ChargementPlan" :
			{
				setModeChargementDemandeLivraison();
				break;
			}
			case "ChargementDemandeLivraison" :
			{
				setModeCalculTournee();
				break;
			}
			case "CalculTournee" :
			{
				setModeModificationTournee();
				break;
			}
			case "ModificationTournee" :
			{
				setModeValidationTournee();
				break;
			}
		}
	}
	public void setModePrecedent()
	{
		switch (etat)
		{
			case "ChargementPlan" :
			{
				setModeAccueil();
				break;
			}
			case "ChargementDemandeLivraison" :
			{
				setModeChargementPlan();
				break;
			}
			case "CalculTournee" :
			{
				setModeChargementDemandeLivraison();
				break;
			}
			case "ModificationTournee" :
			{
				setModeCalculTournee();
				break;
			}
			case "ValidationTournee" :
			{
				setModeModificationTournee();
				break;
			}
		}
	}*/
}
