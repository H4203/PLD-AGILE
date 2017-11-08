package modeles;

import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;

/**
 * Classe contenant toutes les informations relatives a une intersection
 */
public class Intersection {

	/**
	 * id de l'intersection
	 */
	private Long id;
	/**
	 * Coordonnee x de l'intersection
	 */
	private Integer x;
	/**
	 * Coordonnee y de l'intersection
	 */
	private Integer y;
	/**
	 * List des troncons ayant pour point de depart l'intersection
	 */
	private List<Troncon> tronconsSortants;
	/**
	 * List des troncons ayant pour point d'arrivee l'intersection
	 */
	private List<Troncon> tronconsEntrants;

	/**
	 * Constructeur par defaut
	 */
	public Intersection() {
	}

	/**
	 * Constructeur a partir de l'id et des coordonnees
	 * 
	 * @param id
	 * @param x
	 * @param y
	 */
	public Intersection(Long id, Integer x, Integer y) {
		this.id = id;
		this.x = x;
		this.y = y;
		tronconsEntrants = new ArrayList<Troncon>();
		tronconsSortants = new ArrayList<Troncon>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public List<Troncon> getTronconsSortants() {
		return tronconsSortants;
	}

	/**
	 * Methode permettant d'obtenir le troncon a l'index i
	 * 
	 * @param i
	 *            position du troncon souhaite
	 * @return retourne le troncon, null si i non valide
	 */
	public Troncon getTronconsSortantsi(int i) {
		if ((i > 0) && (i < tronconsSortants.size())) {
			return tronconsSortants.get(i);
		}
		return null;
	}

	public void setTronconsSortants(List<Troncon> tronconsSortants) {
		this.tronconsSortants = tronconsSortants;
	}

	public List<Troncon> getTronconsEntrants() {
		return tronconsEntrants;
	}

	public void setTronconsEntrants(List<Troncon> tronconsEntrants) {
		this.tronconsEntrants = tronconsEntrants;
	}

	public void addTronconEntrant(Troncon troncon) {
		tronconsEntrants.add(troncon);
	}

	public void addTronconSortant(Troncon troncon) {
		tronconsSortants.add(troncon);
	}

	@Override
	public String toString() {
		return "Intersection [id=" + id + ", x=" + x + ", y=" + y + ", nombre de tronconsSortants="
				+ tronconsSortants.size() + ", nombre de tronconsEntrants=" + tronconsEntrants.size() + "]";
	}

}
