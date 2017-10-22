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
	private Tournee tournee;
	DemandeLivraison demandeLivraison;
	
	private String etat;
	
	public Controleur() 
	{
		parseur = new XMLParseur();
		fenetre = new Fenetre(this);
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
		plan = parseur.chargerPlan(cheminPlan);
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
		demandeLivraison = parseur.chargerLivraison(cheminDemandeLivraisons, plan.getListeIntersection());	
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
}
