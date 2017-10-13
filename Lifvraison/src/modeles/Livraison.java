package modeles;

public class Livraison {
	
	private PlageHoraire plagehoraire ;
	private PointDeLivraison pointdelivraison ;
	private int dureeDechargement ;
	
	/**
	 * @return the plagehoraire
	 */
	public PlageHoraire getPlagehoraire() {
		return plagehoraire;
	}
	/**
	 * @param plagehoraire the plagehoraire to set
	 */
	public void setPlagehoraire(PlageHoraire plagehoraire) {
		this.plagehoraire = plagehoraire;
	}
	/**
	 * @return the pointdelivraison
	 */
	public PointDeLivraison getPointdelivraison() {
		return pointdelivraison;
	}
	/**
	 * @param pointdelivraison the pointdelivraison to set
	 */
	public void setPointdelivraison(PointDeLivraison pointdelivraison) {
		this.pointdelivraison = pointdelivraison;
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
	
	public Livraison(PlageHoraire plagehoraire, PointDeLivraison pointdelivraison, int dureeDechargement) {
		super();
		this.plagehoraire = plagehoraire;
		this.pointdelivraison = pointdelivraison;
		this.dureeDechargement = dureeDechargement;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Livraison [plagehoraire=" + plagehoraire + ", pointdelivraison=" + pointdelivraison
				+ ", dureeDechargement=" + dureeDechargement + "]";
	}
	
	
	
	

}

