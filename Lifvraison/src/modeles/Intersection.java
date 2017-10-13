package modeles;
import java.util.List;

public class Intersection {
	private Integer id;
	private Integer x;
	private Integer y;
	private List<Troncon> tronconsSortants;
	private List<Troncon> tronconsEntrants; // Surement a enlever
	
	Intersection(Integer id, Integer x, Integer y)
	{
		this.id = id;
		this.x = x;
		this.y = y;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
