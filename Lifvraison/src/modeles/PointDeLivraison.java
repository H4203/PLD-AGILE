package modeles;

public class PointDeLivraison {
	private Intersection noeud ;
	private Long idAdresse;

	public PointDeLivraison() {
		// TODO Auto-generated constructor stub
	}
	
	public PointDeLivraison(Long id) {
		idAdresse = id;
	}
	
	public PointDeLivraison(Intersection noeud) {
		this.noeud = noeud;
	}

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


	public Long getIdAdresse() {
		return idAdresse;
	}

	public void setIdAdresse(Long idAdresse) {
		this.idAdresse = idAdresse;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PointDeLivraison [noeud=" + noeud + "]";
	}
	
	
}
