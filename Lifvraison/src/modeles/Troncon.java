package modeles;

public class Troncon {
	
	private String nomDeRue;
	private Intersection intersectionDepart;
	private Intersection intersectionArrive;
	private double Longueur;
	
	
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
	 * @param nomDeRue the nomDeRue to set
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
	 * @param intersectionDepart the intersectionDepart to set
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
	 * @param intersectionArrive the intersectionArrive to set
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
	 * @param longeur the longeur to set
	 */
	public void setLongueur(double longueur) {
		Longueur = longueur;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Troncon [nomDeRue=" + nomDeRue + ", intersectionDepart=" + intersectionDepart + ", intersectionArrive="
				+ intersectionArrive + ", Longueur=" + Longueur + "]";
	}
}

