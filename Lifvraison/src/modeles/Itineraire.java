package modeles;

import java.util.List;

public class Itineraire {
	
	private List<Troncon> troncons;
	private Intersection depart;
	private Intersection arrivee;
	private double longueur;
	
	/**
	 * @return the troncons
	 */
	public List<Troncon> getTroncons() 
	{
		return troncons;
	}
	/**
	 * @param troncons the troncons to set
	 */
	public void setTroncons(List<Troncon> troncons) 
	{
		this.troncons = troncons;
		
		updateLongueur();
	}
	/**
	 * @return the depart
	 */
	public Intersection getDepart() {
		return depart;
	}
	/**
	 * @param depart the depart to set
	 */
	public void setDepart(Intersection depart) {
		this.depart = depart;
	}
	/**
	 * @return the arrivee
	 */
	public Intersection getArrivee() {
		return arrivee;
	}
	/**
	 * @param arrivee the arrivee to set
	 */
	public void setArrivee(Intersection arrivee) {
		this.arrivee = arrivee;
	}
	
	public double getLongueur()
	{
		return longueur;
	}
	
	public Itineraire(List<Troncon> troncons, Intersection depart, Intersection arrivee) 
	{
		super();
		
		this.troncons = troncons;
		this.depart = depart;
		this.arrivee = arrivee;
		
		updateLongueur();
	}
	
	private void updateLongueur()
	{
		longueur = 0;
		
		for (Troncon troncon : troncons)
		{
			longueur = longueur + troncon.getLongueur();
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Itineraire [troncons=" + troncons + ", depart=" + depart + ", arrivee=" + arrivee + "]";
	}
	
	
	
	
	
	
}

