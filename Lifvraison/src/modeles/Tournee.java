package modeles;

import java.util.List;

public class Tournee 
{
	private Plan plan;
	private DemandeLivraison demandeLivraison;
	private List <Itineraire> listeItineraires;
	private double longueur;
	
	public Tournee(Plan plan, DemandeLivraison demandeLivraison, List <Itineraire> listeItineraires)
	{
		this.plan = plan;
		this.demandeLivraison = demandeLivraison;
		this.listeItineraires = listeItineraires;

		updateLongueur();
	}
	
	public double getLongueur()
	{
		return longueur;
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
