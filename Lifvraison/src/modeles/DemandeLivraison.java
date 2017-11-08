package modeles;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;

/**
 * Classe contenant toutes les informations d'une demande de livraison
 */
public class DemandeLivraison extends Observable {
	/**
	 * La liste des adresses ou le livreur doit passer
	 */
	private List<Livraison> livraisons;
	/**
	 * L'heure de depart de l'entrepot
	 */
	private LocalTime heureDepart;
	/**
	 * Point de depart
	 */
	private Intersection entrepot;

	/**
	 * Constructeur par defaut
	 */
	public DemandeLivraison() {
		entrepot = new Intersection();
		livraisons = new ArrayList<Livraison>();
	}

	/**
	 * Initialise les attributs à partir des parametres
	 * @param livraisons La liste des livraisons
	 * @param heureDepart L'heure de depart de l'entrepot
	 * @param entrepot Point de depart
	 */
	public DemandeLivraison(List<Livraison> livraisons, LocalTime heureDepart, Intersection entrepot) {
		this.livraisons = livraisons;
		this.heureDepart = heureDepart;
		this.entrepot = entrepot;
		notifyObservers();
	}

	/**
	 * Permet de remettre a zero la liste des livraisons
	 */
	public void reset() {
		livraisons.clear();
	}

	/**
	 * Ajoute une livraison a la liste de livraisons
	 * @param intersection L'intersection de la livraison
	 * @param dureeDechargement La duree de dechargement
	 * @param arrivee L'horaire de debut de la plage horaire (null si non precise)
	 * @param depart L'horaire de fin de la plage horaire (null si non precise)
	 */
	public void ajouterLivraison(Intersection intersection, int dureeDechargement, LocalTime arrivee,
			LocalTime depart) {
		if (arrivee == null) {
			livraisons.add(new Livraison(intersection, dureeDechargement));
		} else {
			livraisons.add(new Livraison(intersection, dureeDechargement, new PlageHoraire(arrivee, depart)));
		}
		notifyObservers();
	}

	/**
	 * Ajoute une livraison aux livraisons a partir d'un objet Livraison
	 * @param livraison La livraison a ajouter
	 */
	public void ajouterLivraison(Livraison livraison) {
		livraisons.add(livraison);
		notifyObservers();
	}

	/**
	 * @return the livraisons
	 */
	public List<Livraison> getLivraisons() {
		return livraisons;
	}

	/**
	 * @param livraisons
	 *            the livraisons to set
	 */
	public void setLivraisons(List<Livraison> livraisons) {
		this.livraisons = livraisons;
		notifyObservers();
	}

	/**
	 * @return the heureDepart
	 */
	public LocalTime getHeureDepart() {
		return heureDepart;
	}

	/**
	 * @param heureDepart
	 *            the heureDepart to set
	 */
	public void setHeureDepart(LocalTime heureDepart) {
		this.heureDepart = heureDepart;
	}

	/**
	 * @return the entrepot
	 */
	public Intersection getEntrepot() {
		return entrepot;
	}

	/**
	 * @param entrepot
	 *            the entrepot to set
	 */
	public void setEntrepot(Intersection entrepot) {
		this.entrepot = entrepot;
		notifyObservers();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DemandeLivraison [livraisons=" + livraisons + ", heureDepart=" + heureDepart + ", entrepot=" + entrepot
				+ "]";
	}

}
