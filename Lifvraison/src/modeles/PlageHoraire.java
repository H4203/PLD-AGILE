package modeles;

import java.time.LocalTime;

public class PlageHoraire {
	
	private LocalTime heureDebut ;
	private LocalTime heureFin ;
	
	public PlageHoraire()
	{
		heureDebut = null;
		heureFin = null;
	}
	
	public PlageHoraire(LocalTime heureDebut, LocalTime heureFin) {
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
	}
	
	/**
	 * @return the heureDebut
	 */
	public LocalTime getHeureDebut() {
		return heureDebut;
	}
	/**
	 * @param heureDebut the heureDebut to set
	 */
	public void setHeureDebut(LocalTime heureDebut) {
		this.heureDebut = heureDebut;
	}
	/**
	 * @return the heureFin
	 */
	public LocalTime getHeureFin() {
		return heureFin;
	}
	/**
	 * @param heureFin the heureFin to set
	 */
	public void setHeureFin(LocalTime heureFin) {
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

