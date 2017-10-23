package controleur;

import algorithme.CalculateurTournee;
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
	private DemandeLivraison demandeLivraison;
	private Tournee tournee;
	
	private String etat;
	
	public Controleur() 
	{
		plan = new Plan();
		demandeLivraison = new DemandeLivraison();
		tournee = new Tournee(plan, demandeLivraison);
		
		parseur = new XMLParseur();
		fenetre = new Fenetre(this, plan, demandeLivraison, tournee);
		calculateurTournee = new CalculateurTournee(tournee);
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
		fenetre.setModeChargementPlan();	
		
		etat = "ChargementPlan";
	}
	
	public void setModeChargementPlan(String cheminPlan)
	{
		demandeLivraison.reset();
		tournee.reset();
		parseur.chargerPlan(plan, cheminPlan);
		
		fenetre.setModeChargementPlan();
		
		etat = "ChargementPlan";
	}
	
	public void setModeChargementDemandeLivraison()
	{
		fenetre.setModeChargementDemandeLivraison();	
		
		etat = "ChargementDemandeLivraison";
	}
	
	public void setModeChargementDemandeLivraison(String cheminDemandeLivraisons)
	{	
		tournee.reset();
		parseur.chargerLivraison(demandeLivraison, cheminDemandeLivraisons, plan.getListeIntersection());	
		
		fenetre.setModeChargementDemandeLivraison();
		
		etat = "ChargementDemandeLivraison";
	}
	
	public void setModeCalculTournee()
	{		
		calculateurTournee.run();
		
		fenetre.setModeCalculTournee();
		etat = "CalculTournee";
	}
	
	public void setModeModificationTournee()
	{
		fenetre.setModeModificationTournee();
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
