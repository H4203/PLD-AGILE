package modeles;

public class Troncon {
	
	private String nomDeRue;
	private Intersection intersectionDepart;
	private Intersection intersectionArrive;
	private double Longeur;
	
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
	public double getLongeur() {
		return Longeur;
	}
	/**
	 * @param longeur the longeur to set
	 */
	public void setLongeur(double longeur) {
		Longeur = longeur;
	}
	
	public Troncon(String nomDeRue, Intersection intersectionDepart, Intersection intersectionArrive, double longeur) {
		super();
		this.nomDeRue = nomDeRue;
		this.intersectionDepart = intersectionDepart;
		this.intersectionArrive = intersectionArrive;
		Longeur = longeur;
	}
	
	public Troncon(String nomDeRue2, Long idDepart, Long idArrivee, double longeur2)
	{
		// TODO Auto-generated constructor stub
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Troncon [nomDeRue=" + nomDeRue + ", intersectionDepart=" + intersectionDepart + ", intersectionArrive="
				+ intersectionArrive + ", Longeur=" + Longeur + "]";
	}
	
	
	
	
}

