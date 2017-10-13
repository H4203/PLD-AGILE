package modeles;
import java.util.List;

public class Intersection {
	private Long id;
	private Integer x;
	private Integer y;
	private List<Troncon> tronconsSortants;
	private List<Troncon> tronconsEntrants; // Surement a enlever
	
	Intersection(Long id, Integer x, Integer y)
	{
		this.id = id;
		this.x = x;
		this.y = y;
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
	
	public Troncon getTronconsSortantsi(int i) {
		if((i > 0) && (i < tronconsSortants.size()) ) {
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
	
	
	
}
