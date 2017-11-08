package modeles;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import donnees.ParseurException;

/**
 * Classe contenant toutes les informations relatives au plan
 */
public class Plan extends Observable {
	/**
	 * List des intersections du plan
	 */
	private HashMap<Long, Intersection> listeIntersection;
	/**
	 * List des troncons du plan
	 */
	private HashMap<Integer, Troncon> listeTroncons;

	/**
	 * Intersection selectionnee dans l'IHM
	 */
	private Intersection selectedIntersection;
	/**
	 * Troncon selectionne dans l'IHM
	 */
	private Troncon selectedTroncon;

	private int idTroncon = 1;

	private int xMin, xMax, yMin, yMax;

	/**
	 * Constructeur par defaut
	 */
	public Plan() {
		this.listeIntersection = new HashMap<Long, Intersection>();
		this.listeTroncons = new HashMap<Integer, Troncon>();

		init();
	}

	/**
	 * Constructeur a partir d'une liste de troncons et d'une liste d'intersection
	 * 
	 * @param listeIntersection
	 * @param listeTroncons
	 */
	public Plan(HashMap<Long, Intersection> listeIntersection, HashMap<Integer, Troncon> listeTroncons) {
		this.listeIntersection = listeIntersection;
		this.listeTroncons = listeTroncons;

		init();
	}

	private void init() {
		xMin = 999999;
		xMax = 0;
		yMin = 999999;
		yMax = 0;

		resetBounds();

		notifyObservers();
	}

	/**
	 * Reinitialise le plan
	 */
	public void reset() {
		listeIntersection.clear();
		listeTroncons.clear();

		resetBounds();
	}

	public HashMap<Long, Intersection> getListeIntersection() {
		return listeIntersection;
	}

	public void setListeIntersection(HashMap<Long, Intersection> listeIntersection) {
		this.listeIntersection = listeIntersection;

		resetBounds();
	}

	/**
	 * Ajoute une intersection au plan
	 * 
	 * @param aAjouter
	 *            L'intersection a ajouter
	 * @throws ParseurException
	 *             renvoie une exception si l'intersection existe deja
	 */
	public void ajouterIntersection(Intersection aAjouter) throws ParseurException {
		if (listeIntersection.containsKey(aAjouter.getId())) {
			throw new ParseurException("L'id" + aAjouter.getId() + "est en double...");
		}
		this.listeIntersection.put(aAjouter.getId(), aAjouter);

		resetBounds();
	}

	/**
	 * Ajout d'une livraison a partir des parametre de l'id et des coordonnes
	 * 
	 * @param id
	 *            id de la livraison a ajouter
	 * @param x
	 *            coordonnee x de la livraison a ajouter
	 * @param y
	 *            coordonnee y de la livraison a ajouter
	 * @throws ParseurException
	 *             renvoie une exception si l'intersection existe deja
	 */
	public void ajouterIntersection(Long id, int x, int y) throws ParseurException {
		if (listeIntersection.containsKey(id)) {
			throw new ParseurException("L'id" + id + "est en double...");
		}
		this.listeIntersection.put(id, new Intersection(id, x, y));

		resetBounds();
	}

	/**
	 * Ajoute un troncon a partir de ses caracteristiques
	 * 
	 * @param nomDeRue
	 *            Nom de rue du nouveau troncon
	 * @param intersectionDepart
	 *            intersection depart du nouveau troncon
	 * @param intersectionArrivee
	 *            intersection arrivee du nouveau troncon
	 * @param longueur
	 *            longueur du nouveau troncon
	 */
	public void ajouterTroncon(String nomDeRue, Intersection intersectionDepart, Intersection intersectionArrivee,
			double longueur) {
		Troncon troncon = new Troncon(nomDeRue, intersectionDepart, intersectionArrivee, longueur);
		this.listeTroncons.put(idTroncon, troncon);
		idTroncon = idTroncon + 1;
		/* on met a jour les valeurs des listes de troncons */
		intersectionDepart.addTronconSortant(troncon);
		intersectionArrivee.addTronconEntrant(troncon);

		setChanged();
		notifyObservers();
	}

	public HashMap<Integer, Troncon> getListeTroncons() {
		return listeTroncons;
	}

	public void setListeTroncons(HashMap<Integer, Troncon> listeTroncons) {
		this.listeTroncons = listeTroncons;

		setChanged();
		notifyObservers();
	}

	@Override
	public String toString() {
		return "Plan [listeIntersection=" + listeIntersection + ", \nlisteTroncons=" + listeTroncons + "]";
	}

	public Intersection getSelectedIntersection() {
		return selectedIntersection;
	}

	public Troncon getSelectedTroncon() {
		return selectedTroncon;
	}

	/**
	 * Permet d'obtenir un point lorsque l'on clique sur l'IHM
	 * 
	 * @param point
	 *            Point reel de l'IHM
	 * @param tolerance
	 *            tolerance autorisee par rapport au point selectionne
	 */
	public void getAtPoint(Point point, int tolerance) {
		selectedIntersection = null;
		selectedTroncon = null;

		for (Map.Entry<Long, Intersection> intersection : listeIntersection.entrySet()) {
			if (point.getX() < intersection.getValue().getX() + tolerance
					&& point.getX() > intersection.getValue().getX() - tolerance
					&& point.getY() < intersection.getValue().getY() + tolerance
					&& point.getY() > intersection.getValue().getY() - tolerance) {
				selectedIntersection = intersection.getValue();
			}
		}

		if (selectedIntersection == null) {
			double departX, departY, arriveeX, arriveeY, minX, minY, maxX, maxY;
			double a, b;

			for (Map.Entry<Integer, Troncon> troncon : listeTroncons.entrySet()) {
				departX = troncon.getValue().getIntersectionDepart().getX();
				departY = troncon.getValue().getIntersectionDepart().getY();
				arriveeX = troncon.getValue().getIntersectionArrive().getX();
				arriveeY = troncon.getValue().getIntersectionArrive().getY();

				minX = Math.min(departX, arriveeX);
				maxX = Math.max(departX, arriveeX);
				minY = Math.min(departY, arriveeY);
				maxY = Math.max(departY, arriveeY);

				a = (arriveeX - departX) / (arriveeY - departY);
				b = departX - a * departY;

				if (a * point.getY() + b < point.getX() + tolerance * Math.pow(1.5, Math.abs(a))
						&& a * point.getY() + b > point.getX() - tolerance * Math.pow(1.5, Math.abs(a))
						&& point.getX() <= maxX + tolerance && point.getX() >= minX - tolerance
						&& point.getY() <= maxY + tolerance && point.getY() >= minY - tolerance) {
					selectedTroncon = troncon.getValue();
				}
			}
		}

		setChanged();
		notifyObservers();
	}

	/**
	 * Permet de reinitialiser les bounds
	 */
	public void resetBounds() {
		xMax = 0;
		yMax = 0;
		xMin = 999999;
		yMin = 999999;

		for (Map.Entry<Long, Intersection> mapentry : listeIntersection.entrySet()) {
			if (((Intersection) mapentry.getValue()).getX() > xMax) {
				xMax = ((Intersection) mapentry.getValue()).getX();
			} else if (((Intersection) mapentry.getValue()).getX() < xMin) {
				xMin = ((Intersection) mapentry.getValue()).getX();
			}

			if (((Intersection) mapentry.getValue()).getY() > yMax) {
				yMax = ((Intersection) mapentry.getValue()).getY();
			} else if (((Intersection) mapentry.getValue()).getY() < yMin) {
				yMin = ((Intersection) mapentry.getValue()).getY();
			}
		}

		setChanged();
		notifyObservers();
	}

	public int getXMin() {
		return xMin;
	}

	public int getXMax() {
		return xMax;
	}

	public int getYMin() {
		return yMin;
	}

	public int getYMax() {
		return yMax;
	}
}
