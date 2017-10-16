package modeles;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Tournee 
{
	private Plan plan;
	private DemandeLivraison demandeLivraison; //on en a vraiment besoin ????
	private List <Itineraire> listeItineraires;
	private List <PlageHoraire> listeHoraire; /* commence avec l'heure de depart a l'entrepot */
	private double longueur;
	
	public Tournee(Plan plan, DemandeLivraison demandeLivraison, List <Itineraire> listeItineraires)
	{
		this.plan = plan;
		this.demandeLivraison = demandeLivraison;
		this.listeItineraires = listeItineraires;
		this.listeHoraire = new ArrayList<PlageHoraire>();
		updateLongueur();
		updateHoraire();
	}
	
	private void updateHoraire() {
		double dureeRoute;
		LocalTime debut,fin;
		listeHoraire.clear();
		PlageHoraire horaire = new PlageHoraire(demandeLivraison.getHeureDepart(), demandeLivraison.getHeureDepart());
		listeHoraire.add(horaire);
		for (Itineraire itineraire : listeItineraires)
		{
			dureeRoute = itineraire.getLongueur()*0.1/15000*3600;
			debut = listeHoraire.get(listeHoraire.size()-1).getHeureFin();
			debut = debut.plusSeconds((long)(dureeRoute));
			if (debut.isBefore(demandeLivraison.getLivraisons().get(listeItineraires.indexOf(itineraire)).getPlagehoraire().getHeureDebut()))
			{
				fin = demandeLivraison.getLivraisons().get(listeItineraires.indexOf(itineraire)).getPlagehoraire().getHeureDebut();
			}
			else
			{
				fin = debut;
			}
			fin = fin.plusSeconds(demandeLivraison.getLivraisons().get(listeItineraires.indexOf(itineraire)).getDureeDechargement());
			horaire = new PlageHoraire(debut,fin);
			listeHoraire.add(horaire);
		}
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
}
