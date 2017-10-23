package controleur;

import javax.swing.JOptionPane;

import algorithme.CalculateurTournee;
import donnees.ParseurException;
import donnees.XMLParseur;
import modeles.Plan;
import modeles.Tournee;
import vue.Fenetre;
import modeles.DemandeLivraison;

public class Controleur 
{	
	private XMLParseur parseur;
	private CalculateurTournee calculateurTournee;
	private Fenetre fenetre;
	private Plan plan;
	private Tournee tournee;
	DemandeLivraison demandeLivraison;
	private String etat;
	
	/**
	 * 
	 */
	public Controleur() {
		
		try {
			parseur = new XMLParseur ();
			fenetre = new Fenetre ( this );
		} catch (ParseurException e) {
			JOptionPane.showMessageDialog(fenetre, e.getMessage(), "Erreur lors du parsage", JOptionPane.ERROR_MESSAGE);
		}
	}
	
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
	}
}
