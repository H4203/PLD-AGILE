package modeles;
/**
 * Classe contenant les informations d'une livraison
 */
public class Livraison {
	/**
	 * Intersection ou se situe la livraison
	 */
	private Intersection intersection;
	/**
	 * Duree de dechargement de la livraison
	 */
	private int dureeDechargement;
	/**
	 * Plage horaire dans laquelle la livraison doit etre faite
	 */
	private PlageHoraire plageHoraire;

	/**
	 * Constructeur par defaut
	 */
	public Livraison() {
	}

	/**
	 * Constructeur d'une livraison sans plage horaire
	 * 
	 * @param intersection
	 *            Intersection de la livraison
	 * @param dureeDechargement
	 *            Duree de dechangement
	 */
	public Livraison(Intersection intersection, int dureeDechargement) {
		this.intersection = intersection;
		this.dureeDechargement = dureeDechargement;
		plageHoraire = null;
	}

	/**
	 * Constructeur avec une livraison
	 * 
	 * @param Intersection
	 *            Intersection de la livraison
	 * @param dureeDechargement
	 *            Duree de dechargement
	 * @param plagehoraire
	 *            Plage Horaire
	 */
	public Livraison(Intersection Intersection, int dureeDechargement, PlageHoraire plagehoraire) {
		this.plageHoraire = plagehoraire;
		this.intersection = Intersection;
		this.dureeDechargement = dureeDechargement;
	}

	/**
	 * @return the plagehoraire
	 */
	public PlageHoraire getPlagehoraire() {
		return plageHoraire;
	}

	/**
	 * @param plagehoraire
	 *            the plagehoraire to set
	 */
	public void setPlagehoraire(PlageHoraire plagehoraire) {
		this.plageHoraire = plagehoraire;
	}

	/**
	 * @return the Intersection
	 */
	public Intersection getIntersection() {
		return intersection;
	}

	/**
	 * @param Intersection
	 *            the Intersection to set
	 */
	public void setIntersection(Intersection Intersection) {
		this.intersection = Intersection;
	}

	/**
	 * @return the dureeDechargement
	 */
	public int getDureeDechargement() {
		return dureeDechargement;
	}

	/**
	 * @param dureeDechargement
	 *            the dureeDechargement to set
	 */
	public void setDureeDechargement(int dureeDechargement) {
		this.dureeDechargement = dureeDechargement;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Livraison [plagehoraire=" + plageHoraire + ", Intersection=" + intersection + ", dureeDechargement="
				+ dureeDechargement + "]";
	}

}
