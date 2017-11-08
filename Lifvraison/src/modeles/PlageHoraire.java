package modeles;

import java.time.LocalTime;

/**
 * Classe contenant l'heure de debut et de fin d'une plage horaire
 */
public class PlageHoraire {
	/**
	 * L'heure de debut de la plage horaire
	 */
	private LocalTime heureDebut;
	/**
	 * L'heure de fin de la plage horaire
	 */
	private LocalTime heureFin;

	/**
	 * Constructeur par defaut
	 */
	public PlageHoraire() {
		heureDebut = null;
		heureFin = null;
	}

	/**
	 * Constructeur avec les heures de debut et de fin en parametre
	 * 
	 * @param heureDebut
	 *            L'heure de debut de la plage horaire
	 * @param heureFin
	 *            L'heure de fin de la plage horaire
	 */
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
	 * @param heureDebut
	 *            the heureDebut to set
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
	 * @param heureFin
	 *            the heureFin to set
	 */
	public void setHeureFin(LocalTime heureFin) {
		this.heureFin = heureFin;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PlageHoraire [heureDebut=" + heureDebut + ", heureFin=" + heureFin + "]";
	}

}
