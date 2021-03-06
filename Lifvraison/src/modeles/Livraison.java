package modeles;

public class Livraison {
	
	private Intersection intersection ;
	private int dureeDechargement ;
	private PlageHoraire plageHoraire ;
	
	public Livraison()
	{
	}
	
	public Livraison(Intersection intersection, int dureeDechargement) {
		this.intersection = intersection;
		this.dureeDechargement = dureeDechargement;
		plageHoraire = null;
	}
	
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
	 * @param plagehoraire the plagehoraire to set
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
	 * @param Intersection the Intersection to set
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
	 * @param dureeDechargement the dureeDechargement to set
	 */
	public void setDureeDechargement(int dureeDechargement) {
		this.dureeDechargement = dureeDechargement;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Livraison [plagehoraire=" + plageHoraire + ", Intersection=" + intersection
				+ ", dureeDechargement=" + dureeDechargement + "]";
	}

}

