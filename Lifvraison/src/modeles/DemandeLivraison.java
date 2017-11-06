package modeles;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;

public class DemandeLivraison extends Observable 
{
	private List<Livraison> livraisons;
	private LocalTime heureDepart;
	private Intersection entrepot;

	public DemandeLivraison() 
	{
		entrepot = new Intersection();
		livraisons = new ArrayList<Livraison>();
	}

	public DemandeLivraison(List<Livraison> livraisons, LocalTime heureDepart, Intersection entrepot) 
	{
		this.livraisons = livraisons;
		this.heureDepart = heureDepart;
		this.entrepot = entrepot;
		notifyObservers();
	}
	
	public void reset()
	{
		livraisons.clear();
	}

	public void ajouterLivraison(Intersection intersection, int dureeDechargement, LocalTime arrivee,
			LocalTime depart) {
		if(arrivee == null) {
			livraisons.add(new Livraison(intersection, dureeDechargement));
		}
		else {
			livraisons.add(new Livraison(intersection, dureeDechargement, new PlageHoraire(arrivee, depart)));
		}
    notifyObservers();
	}
	public void ajouterLivraison( Livraison livraison) {
		livraisons.add(livraison);
    notifyObservers();
	}

	/**
	 * @return the livraisons
	 */
	public List<Livraison> getLivraisons() 
	{
		return livraisons;
	}

	/**
	 * @param livraisons
	 *            the livraisons to set
	 */
	public void setLivraisons(List<Livraison> livraisons) 
	{
		this.livraisons = livraisons;
		notifyObservers();
	}

	/**
	 * @return the heureDepart
	 */
	public LocalTime getHeureDepart() 
	{
		return heureDepart;
	}

	/**
	 * @param heureDepart
	 *            the heureDepart to set
	 */
	public void setHeureDepart(LocalTime heureDepart) 
	{
		this.heureDepart = heureDepart;
	}
	
	/**
	 * @return the entrepot
	 */
	public Intersection getEntrepot() 
	{
		return entrepot;
	}

	/**
	 * @param entrepot
	 *            the entrepot to set
	 */
	public void setEntrepot(Intersection entrepot) 
	{
		this.entrepot = entrepot;
		notifyObservers();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() 
	{
		return "DemandeLivraison [livraisons=" + livraisons + ", heureDepart=" + heureDepart + ", entrepot=" + entrepot
				+ "]";
	}

}
