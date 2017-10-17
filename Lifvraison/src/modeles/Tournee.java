package modeles;

import java.util.List;

public class Tournee 
{
	private Plan plan;
	private DemandeLivraison demandeLivraison;
	private List <Itineraire> listeItineraires;
	private double longueur;
	
	public Tournee(Plan plan, DemandeLivraison demandeLivraison)
	{
		this.plan = plan;
		this.demandeLivraison = demandeLivraison;

		longueur = 0;
	}
	
	public double getLongueur()
	{
		return longueur;
	}
	
	public void setListeItineraires(List <Itineraire> listeItineraires)
	{
		this.listeItineraires = listeItineraires;
		
		updateLongueur();
	}
	
	public List <Itineraire> getListeItineraires()
	{
		return listeItineraires;
	}
	
	private void updateLongueur()
	{
		longueur = 0;
		
		for (Itineraire itineraire : listeItineraires)
		{
			longueur = longueur + itineraire.getLongueur();
		}
	}

	public Plan getPlan() {
		return plan;
	}

	public DemandeLivraison getDemandeLivraison() {
		return demandeLivraison;
	}
	
	
}
