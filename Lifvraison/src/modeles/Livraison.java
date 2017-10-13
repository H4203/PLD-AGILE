package modeles;

public class Livraison {
	PlageHoraire plageHoraire;
	Intersection pointDeLivraison;
	Integer dureeDechargement;
	
	public Livraison(PlageHoraire plageHoraire, Intersection pointDeLivraison, Integer dureeDechargement) {
		this.plageHoraire = plageHoraire;
		this.pointDeLivraison = pointDeLivraison;
		this.dureeDechargement = dureeDechargement;
	}

	public PlageHoraire getPlageHoraire() {
		return plageHoraire;
	}

	public void setPlageHoraire(PlageHoraire plageHoraire) {
		this.plageHoraire = plageHoraire;
	}

	public Intersection getPointDeLivraison() {
		return pointDeLivraison;
	}

	public void setPointDeLivraison(Intersection pointDeLivraison) {
		this.pointDeLivraison = pointDeLivraison;
	}

	public Integer getDureeDechargement() {
		return dureeDechargement;
	}

	public void setDureeDechargement(Integer dureeDechargement) {
		this.dureeDechargement = dureeDechargement;
	}
	
	
	
}
