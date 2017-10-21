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
	
	public void setModeChargementPlan(String cheminPlan)
	{
		if (cheminPlan != null)
		{
			plan = parseur.chargerPlan(cheminPlan);
			demandeLivraison = null;
			tournee = null;
		}
		
		fenetre.setModeChargementPlan(plan);
		etat = "ChargementPlan";
	}
	
	public void setModeChargementDemandeLivraison(String cheminDemandeLivraisons)
	{
		if (cheminDemandeLivraisons != null && plan != null)
		{
			demandeLivraison = parseur.chargerLivraison(cheminDemandeLivraisons, plan.getListeIntersection());
			tournee = null;
		}
		
		fenetre.setModeChargementDemandeLivraison(demandeLivraison);
		etat = "ChargementDemandeLivraison";
	}
	
	public void setModeCalculTournee(String calcul)
	{
		if (calcul != null)
		{
			tournee = new Tournee(plan, demandeLivraison);
			calculateurTournee = new CalculateurTournee(tournee);
			calculateurTournee.run();
		}
		
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
				setModeChargementPlan(null);
				break;
			}
			case "ChargementPlan" :
			{
				setModeChargementDemandeLivraison(null);
				break;
			}
			case "ChargementDemandeLivraison" :
			{
				setModeCalculTournee(null);
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
