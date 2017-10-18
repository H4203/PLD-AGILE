package modeles;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Tournee 
{
	private Plan plan;
	private DemandeLivraison demandeLivraison; //on en a vraiment besoin ????
	private List <Itineraire> listeItineraires;
	private List <PlageHoraire> listeHoraire; /* commence avec l'heure de depart a l'entrepot et finit avec l'heure de retour a l'entrepot */
	private double longueur;

	public Tournee(Plan plan, DemandeLivraison demandeLivraison)
	{
		longueur = 0;
		this.plan = plan;
		this.demandeLivraison = demandeLivraison;
		this.listeHoraire = new ArrayList<PlageHoraire>();
		longueur = 0;
	}

	private void updateHoraire() {
		double dureeRoute;
		LocalTime debut,fin;
		listeHoraire.clear();

		/* quand on part de l'entrepot */
		PlageHoraire horaire = new PlageHoraire(demandeLivraison.getHeureDepart(), demandeLivraison.getHeureDepart());
		listeHoraire.add(horaire);

		/* on met les horaires pour chaque livraison */
		for (Itineraire itineraire : listeItineraires)
		{
			/* on converti la longeur de la route en temps */
			dureeRoute = itineraire.getLongueur()*0.1/15000*3600;

			/* on recupere l'heure a laquelle il quitte son dernier point */
			debut = listeHoraire.get(listeHoraire.size()-1).getHeureFin();
			/* on ajoute le temps du chemin pour arriver au nouveau point */
			debut = debut.plusSeconds((long)(dureeRoute));

			/* on recupere a quel itineraire on est */
			int indexItineraire = listeItineraires.indexOf(itineraire);

			if (indexItineraire == listeItineraires.size()-1)
			{
				/* retour a l'entrepot */
				horaire = new PlageHoraire(debut, debut);
				listeHoraire.add(horaire);
				break;
			}
			
			List<Livraison> listLiv = demandeLivraison.getLivraisons();
			Livraison liv = listLiv.get(indexItineraire);
			PlageHoraire plhr =  liv.getPlagehoraire();
			if (plhr != null)
			{
				if (plhr.getHeureDebut() != null && debut.isBefore(plhr.getHeureDebut()))
				{
					/* on ajoute le temps d'attente du debut de plage horaire */
					fin = demandeLivraison.getLivraisons().get(listeItineraires.indexOf(itineraire)).getPlagehoraire().getHeureDebut();
				}
				else
				{
					fin = debut;
				}
			}
			else
			{
				fin = debut;
			}
			/* on ajoute le temps de livrer */
			fin = fin.plusSeconds(demandeLivraison.getLivraisons().get(indexItineraire).getDureeDechargement());
			horaire = new PlageHoraire(debut,fin);
			listeHoraire.add(horaire);
		}
	}

	public double getLongueur()
	{
		return longueur;
	}

	public void setListeItineraires(List <Itineraire> listeItineraires)
	{
		this.listeItineraires = listeItineraires;

		updateLongueur();
		updateHoraire();
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
	
	public List<PlageHoraire> getListeHoraire() {
		return listeHoraire;
	}

	public void setListeHoraire(List<PlageHoraire> listeHoraire) {
		this.listeHoraire = listeHoraire;
	}
	
}
