package modeles;

public class PointDeLivraison {
	private Intersection noeud ;

	/**
	 * @return the noeud
	 */
	public Intersection getNoeud() {
		return noeud;
	}

	/**
	 * @param noeud the noeud to set
	 */
	public void setNoeud(Intersection noeud) {
		this.noeud = noeud;
	}

	public PointDeLivraison(Intersection noeud) {
		super();
		this.noeud = noeud;
	}
	
	

	public PointDeLivraison() {
		super();
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PointDeLivraison [noeud=" + noeud + "]";
	}
	
	
}
