<<<<<<< HEAD
package modeles;
import java.util.List;
import java.util.ArrayList;

public class Itineraire {
	List<Troncon> listeTroncons;
	Intersection depart;
	Intersection arrivee;
}
=======
package modeles;

import java.util.List;

public class Itineraire {
	
	private List<Troncon> troncons ;
	private Intersection depart ;
	private Intersection arrivee ;
	
	/**
	 * @return the troncons
	 */
	public List<Troncon> getTroncons() {
		return troncons;
	}
	/**
	 * @param troncons the troncons to set
	 */
	public void setTroncons(List<Troncon> troncons) {
		this.troncons = troncons;
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
	
	public Itineraire(List<Troncon> troncons, Intersection depart, Intersection arrivee) {
		super();
		this.troncons = troncons;
		this.depart = depart;
		this.arrivee = arrivee;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Itineraire [troncons=" + troncons + ", depart=" + depart + ", arrivee=" + arrivee + "]";
	}
	
	
	
	
	
	
}
>>>>>>> 419e022b30e451a39b5bbd5d75896f7defacf846
