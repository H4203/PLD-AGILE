package modeles;
import java.util.Date;

public class PlageHoraire {
	Date heureDebut;
	Date heureFin;
	public PlageHoraire(Date heureDebut, Date heureFin) {
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
	}
	public Date getHeureDebut() {
		return heureDebut;
	}
	public void setHeureDebut(Date heureDebut) {
		this.heureDebut = heureDebut;
	}
	public Date getHeureFin() {
		return heureFin;
	}
	public void setHeureFin(Date heureFin) {
		this.heureFin = heureFin;
	}
	
	
}
