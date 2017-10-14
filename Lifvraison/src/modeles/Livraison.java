package modeles;

public class Livraison {
	
	private PointDeLivraison pointDeLivraison ;
	private int dureeDechargement ;
	private PlageHoraire plageHoraire ;
	
	public Livraison()
	{
	}
	
	public Livraison(PointDeLivraison pointdelivraison, int dureeDechargement) {
		this.pointDeLivraison = pointdelivraison;
		this.dureeDechargement = dureeDechargement;
	}
	
	public Livraison(PointDeLivraison pointdelivraison, int dureeDechargement, PlageHoraire plagehoraire) {
		this.plageHoraire = plagehoraire;
		this.pointDeLivraison = pointdelivraison;
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
	 * @return the pointdelivraison
	 */
	public PointDeLivraison getPointdelivraison() {
		return pointDeLivraison;
	}
	/**
	 * @param pointdelivraison the pointdelivraison to set
	 */
	public void setPointdelivraison(PointDeLivraison pointdelivraison) {
		this.pointDeLivraison = pointdelivraison;
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
		return "Livraison [plagehoraire=" + plageHoraire + ", pointdelivraison=" + pointDeLivraison
				+ ", dureeDechargement=" + dureeDechargement + "]";
	}

}

