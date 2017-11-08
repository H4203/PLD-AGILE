package modeles;

/**
 * Classe contenant toutes les informations relatives a un troncon
 */
public class Troncon {
	/**
	 * Nom de la rue du troncon
	 */
	private String nomDeRue;
	/**
	 * Intersection d'ou part le troncon
	 */
	private Intersection intersectionDepart;
	/**
	 * Intersection ou le troncon arrive
	 */
	private Intersection intersectionArrive;
	/**
	 * Longueur du troncon
	 */
	private double Longueur;

	/**
	 * Constructeur a partir de toutes les informations
	 * 
	 * @param nomDeRue
	 *            nom de rue du troncon
	 * @param intersectionDepart
	 *            intersection de depart du troncon
	 * @param intersectionArrive
	 *            intersection d'arrive du troncon
	 * @param longueur
	 *            longueur du troncon
	 */
	public Troncon(String nomDeRue, Intersection intersectionDepart, Intersection intersectionArrive, double longueur) {
		this.nomDeRue = nomDeRue;
		this.intersectionDepart = intersectionDepart;
		this.intersectionArrive = intersectionArrive;
		Longueur = longueur;
	}

	/**
	 * @return the nomDeRue
	 */
	public String getNomDeRue() {
		return nomDeRue;
	}

	/**
	 * @param nomDeRue
	 *            the nomDeRue to set
	 */
	public void setNomDeRue(String nomDeRue) {
		this.nomDeRue = nomDeRue;
	}

	/**
	 * @return the intersectionDepart
	 */
	public Intersection getIntersectionDepart() {
		return intersectionDepart;
	}

	/**
	 * @param intersectionDepart
	 *            the intersectionDepart to set
	 */
	public void setIntersectionDepart(Intersection intersectionDepart) {
		this.intersectionDepart = intersectionDepart;
	}

	/**
	 * @return the intersectionArrive
	 */
	public Intersection getIntersectionArrive() {
		return intersectionArrive;
	}

	/**
	 * @param intersectionArrive
	 *            the intersectionArrive to set
	 */
	public void setIntersectionArrive(Intersection intersectionArrive) {
		this.intersectionArrive = intersectionArrive;
	}

	/**
	 * @return the longeur
	 */
	public double getLongueur() {
		return Longueur;
	}

	/**
	 * @param longeur
	 *            the longeur to set
	 */
	public void setLongueur(double longueur) {
		Longueur = longueur;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Troncon [nomDeRue=" + nomDeRue + ", intersectionDepart=" + intersectionDepart + ", intersectionArrive="
				+ intersectionArrive + ", Longueur=" + Longueur + "]";
	}
}
