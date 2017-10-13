package modeles;
import java.util.Date;
import java.util.List;


public class DemandeLivraison {
	
	private List<Livraison> livraisons ;
	private Date heureDepart ;
	private Intersection entrepot ;
	
	/**
	 * @return the livraisons
	 */
	public List<Livraison> getLivraisons() {
		return livraisons;
	}
	/**
	 * @param livraisons the livraisons to set
	 */
	public void setLivraisons(List<Livraison> livraisons) {
		this.livraisons = livraisons;
	}
	/**
	 * @return the heureDepart
	 */
	public Date getHeureDepart() {
		return heureDepart;
	}
	/**
	 * @param heureDepart the heureDepart to set
	 */
	public void setHeureDepart(Date heureDepart) {
		this.heureDepart = heureDepart;
	}
	/**
	 * @return the entrepot
	 */
	public Intersection getEntrepot() {
		return entrepot;
	}
	/**
	 * @param entrepot the entrepot to set
	 */
	public void setEntrepot(Intersection entrepot) {
		this.entrepot = entrepot;
	}
	
	public DemandeLivraison(List<Livraison> livraisons, Date heureDepart, Intersection entrepot) {
		super();
		this.livraisons = livraisons;
		this.heureDepart = heureDepart;
		this.entrepot = entrepot;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DemandeLivraison [livraisons=" + livraisons + ", heureDepart=" + heureDepart + ", entrepot=" + entrepot
				+ "]";
	}
	
	
	
	
	
	
	
}
