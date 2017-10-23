package tsp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collections;

public class TSP2 extends TemplateTSP {
	
	@Override
	protected Iterator<Integer> iterator(Integer sommetCrt, ArrayList<Integer> nonVus, int[][] cout, int[] duree) {
		
		return new IteratorSeq(nonVus, sommetCrt);
	}

	@Override
	protected int bound(Integer sommetCourant, ArrayList<Integer> nonVus, int[][] cout, int[] duree) {
		int somme = 0;
		
		int minPtCrt = Integer.MAX_VALUE;
		for(Integer i : nonVus) {
			//Pour le sommet courant
			if(cout[sommetCourant][i] < minPtCrt) {
				minPtCrt = cout[sommetCourant][i];
			}
		}
		somme = somme + minPtCrt;
		
		for(Integer i : nonVus) {
			//Pour les sommets non vus
			int min = cout[i][0];
			for(Integer j : nonVus) {
				if(cout[i][j] < min  ) {
					min = cout[i][j];
				}
			}
			somme = somme + min + duree[i];
		}
		
		//System.out.println(somme);
		return somme;
	}
}