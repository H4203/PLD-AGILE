package modeles;

import java.util.Date;

public class PlageHoraire {
	
	private Date heureDebut ;
	private Date heureFin ;
	
	public PlageHoraire()
	{
		heureDebut = null;
		heureFin = null;
	}
	
	public PlageHoraire(Date heureDebut, Date heureFin) {
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
	}
	
	/**
	 * @return the heureDebut
	 */
	public Date getHeureDebut() {
		return heureDebut;
	}
	/**
	 * @param heureDebut the heureDebut to set
	 */
	public void setHeureDebut(Date heureDebut) {
		this.heureDebut = heureDebut;
	}
	/**
	 * @return the heureFin
	 */
	public Date getHeureFin() {
		return heureFin;
	}
	/**
	 * @param heureFin the heureFin to set
	 */
	public void setHeureFin(Date heureFin) {
		this.heureFin = heureFin;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PlageHoraire [heureDebut=" + heureDebut + ", heureFin=" + heureFin + "]";
	}
	
	
	

}

