package modeles;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Classe contenant toutes les informations permettant de creer, modifier la
 * tournee
 */
public class Tournee extends Observable {
	/**
	 * Le plan dans lequel la tournee est contenue
	 */
	private Plan plan;
	/**
	 * La demande de livraison depuis laquelle est creee la tournee
	 */
	private DemandeLivraison demandeLivraison;
	/**
	 * La liste des itineraires permettant de former la tournee
	 */
	private List<Itineraire> listeItineraires;
	/**
	 * La liste des horaires de passage pendant la tournee
	 */
	private List<PlageHoraire> listeHoraire;
	/**
	 * Longueur totale de la tournee
	 */
	private double longueur;
	/**
	 * List des livraisons dans l'ordre de passage
	 */
	private List<Livraison> livraisonsOrdonnees;

	/**
	 * Constructeur a partir d'un plan et d'une demande de livraison
	 * 
	 * @param plan
	 *            plan dans lequelle est contenue la tournee
	 * @param demandeLivraison
	 *            demande de livraison permettant de creer la tournee
	 */
	public Tournee(Plan plan, DemandeLivraison demandeLivraison) {
		longueur = 0;
		this.plan = plan;
		this.demandeLivraison = demandeLivraison;
		this.listeHoraire = new ArrayList<PlageHoraire>();
		this.livraisonsOrdonnees = new ArrayList<Livraison>();
		this.listeItineraires = new ArrayList<Itineraire>();
		longueur = 0;
		notifyObservers();
	}

	/**
	 * Reinitialise la tournee
	 */
	public void reset() {
		listeItineraires.clear();
		listeHoraire.clear();
		livraisonsOrdonnees.clear();
	}

	/**
	 * Met a jour les horaires de passage
	 */
	private void updateHoraire() {
		double dureeRoute;
		LocalTime debut, fin;
		listeHoraire.clear();

		/* quand on part de l'entrepot */
		PlageHoraire horaire = new PlageHoraire(demandeLivraison.getHeureDepart(), demandeLivraison.getHeureDepart());
		listeHoraire.add(horaire);

		/* on met les horaires pour chaque livraison */
		for (Itineraire itineraire : listeItineraires) {
			/* on converti la longeur de la route en temps */
			dureeRoute = itineraire.getLongueur() / 15000 * 3600;

			/* on recupere l'heure a laquelle il quitte son dernier point */
			debut = listeHoraire.get(listeHoraire.size() - 1).getHeureFin();
			/* on ajoute le temps du chemin pour arriver au nouveau point */
			debut = debut.plusSeconds((long) (dureeRoute));

			/* on recupere a quel itineraire on est */
			int indexItineraire = listeItineraires.indexOf(itineraire);
			if (indexItineraire == listeItineraires.size() - 1) {
				/* retour a l'entrepot */
				horaire = new PlageHoraire(debut, debut);
				listeHoraire.add(horaire);
				break;
			}

			Livraison liv = livraisonsOrdonnees.get(indexItineraire); // pas de +1
			PlageHoraire plhr = liv.getPlagehoraire();
			if (plhr != null) {
				if (plhr.getHeureDebut() != null && debut.isBefore(plhr.getHeureDebut())) {
					/* on ajoute le temps d'attente du debut de plage horaire */
					fin = liv.getPlagehoraire().getHeureDebut();
				} else {
					fin = debut;
				}
			} else {
				fin = debut;
			}
			/* on ajoute le temps de livrer */
			fin = fin.plusSeconds(liv.getDureeDechargement());
			horaire = new PlageHoraire(debut, fin);
			listeHoraire.add(horaire);
		}
	}

	public double getLongueur() {
		return longueur;
	}

	public void setListeItineraires(List<Itineraire> listeItineraires) {
		this.listeItineraires = listeItineraires;

		updateLongueur();
		updateHoraire();

		notifyObservers();
	}

	public List<Itineraire> getListeItineraires() {
		return listeItineraires;
	}

	private void updateLongueur() {
		longueur = 0;

		for (Itineraire itineraire : listeItineraires) {
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

	public List<Livraison> getLivraisonsOrdonnees() {
		return livraisonsOrdonnees;
	}

	public void setLivraisonsOrdonnees(List<Livraison> livraisonsOrdonnees) {
		this.livraisonsOrdonnees = livraisonsOrdonnees;
	}

	/**
	 * Ajoute une livraison apres une position
	 * 
	 * @param livraison
	 *            livraison a ajouter
	 * @param position
	 *            position a la suite de laquelle la livraison doit etre ajoutee
	 */
	public void ajouterLivraison(Livraison livraison, int position) {
		livraisonsOrdonnees.add(position, livraison);
		// updateHoraire(); inutile ?
	}

	/**
	 * Supprime une livraison a partir d'une position
	 * 
	 * @param position
	 *            position de la livraison a supprimer
	 */
	public void supprimerLivraison(int position) {
		livraisonsOrdonnees.remove(position);
		// updateHoraire(); inutile ? on doit aussi modifier les itineraires si on veut
		// appeler update horaire
	}

	public String toString() {
		String str = "";
		int indexLivraison = 0;

		for (Itineraire itineraire : listeItineraires) {

			if (itineraire == listeItineraires.get(0)) {
				str = str + "L'heure depart depuis l'entrepot : " + listeHoraire.get(indexLivraison).getHeureFin()
						+ "\r\n" + "\r\n";
			} else {
				str = str + "L'adresse de livraison n." + indexLivraison + ": "
						+ itineraire.getTroncons().get(0).getNomDeRue() + "\r\n";
				str = str + "L'heure depart de livraison n." + indexLivraison + ": "
						+ listeHoraire.get(indexLivraison).getHeureFin() + "\r\n" + "\r\n";

			}
			str = str + itineraire;
			indexLivraison = indexLivraison + 1;
			if (indexLivraison < livraisonsOrdonnees.size()) {
				str = str + "\r\n" + "L'heure arivee de livraison n." + indexLivraison + ": "
						+ listeHoraire.get(indexLivraison).getHeureDebut() + "\r\n";
			} else {
				str = str + "\r\n" + "L'heure de retour de l'entrepot: "
						+ listeHoraire.get(indexLivraison).getHeureDebut() + "\r\n";
			}
		}

		return str;
	}

}
