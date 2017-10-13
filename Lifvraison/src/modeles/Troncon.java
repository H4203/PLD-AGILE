package modeles;

public class Troncon {
	private String nomDeRue;
	private Intersection intersectionDepart;
	private Intersection intersectionArrive;
	private double Longeur;
	
	public Troncon(String nomDeRue, Intersection intersectionDepart, Intersection intersectionArrive, double longeur) {
		this.nomDeRue = nomDeRue;
		this.intersectionDepart = intersectionDepart;
		this.intersectionArrive = intersectionArrive;
		Longeur = longeur;
	}

	public String getNomDeRue() {
		return nomDeRue;
	}

	public void setNomDeRue(String nomDeRue) {
		this.nomDeRue = nomDeRue;
	}

	public Intersection getIntersectionDepart() {
		return intersectionDepart;
	}

	public void setIntersectionDepart(Intersection intersectionDepart) {
		this.intersectionDepart = intersectionDepart;
	}

	public Intersection getIntersectionArrive() {
		return intersectionArrive;
	}

	public void setIntersectionArrive(Intersection intersectionArrive) {
		this.intersectionArrive = intersectionArrive;
	}

	public double getLongeur() {
		return Longeur;
	}

	public void setLongeur(double longeur) {
		Longeur = longeur;
	}

	
}
