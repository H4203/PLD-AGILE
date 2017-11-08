package tsp;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;

public class IteratorMinFirst implements Iterator<Integer> {

	/**
	 * Listes des sommets a visiter
	 */
	private ArrayList<Integer> candidats;
	/**
	 * Matrice des couts, premettant de trouver le sommet dont le cout depuis le point courant est le moins couteux
	 */
	private int[][] cout;
	/**
	 * Sommet actuel
	 */
	private Integer sommetCourant;

	/**
	 * Cree un iterateur pour iterer sur l'ensemble des sommets de nonVus
	 * @param nonVus Liste des sommets pas encore visites
	 * @param sommetCrt
	 */
	public IteratorMinFirst(Collection<Integer> nonVus, int sommetCrt, int[][] cout){
		this.candidats = new ArrayList<Integer>();
		this.cout = cout;
		this.sommetCourant = sommetCrt;
		for (Integer s : nonVus){
			candidats.add(s);
		}
	}
	
	@Override
	public boolean hasNext() {
		return candidats.size() > 0;
	}

	@Override
	public Integer next() {
		Integer min = candidats.get(0);
		int index = 0;

		for(int i = 1; i < candidats.size(); i++) {
			if(cout[sommetCourant][candidats.get(i)] < cout[sommetCourant][min]) {
				min = candidats.get(i);
				index = i;
			}
		}
		candidats.remove(index);
		return min;
	}

	@Override
	public void remove() {}

}
