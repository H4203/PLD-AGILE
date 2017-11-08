package tsp;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * TSP implementant une meilleure gestion de l'iterator (chemin les moins
 * couteux en premier)
 */
public class TSP3 extends TSP2 {

	@Override
	protected Iterator<Integer> iterator(Integer sommetCrt, ArrayList<Integer> nonVus, int[][] cout, int[] duree) {
		return new IteratorMinFirst(nonVus, sommetCrt, cout);
	}

}