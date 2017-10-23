package tsp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collections;

public class TSP3 extends TemplateTSP {
	
	@Override
	protected Iterator<Integer> iterator(Integer sommetCrt, ArrayList<Integer> nonVus, int[][] cout, int[] duree) {
		
		boolean tableauNonTrie = true;
		while(tableauNonTrie) {
			tableauNonTrie = false;
			for(int i = 0; i < nonVus.size()-1; i++) {
				if(cout[sommetCrt][i] > cout[sommetCrt][i+1]) {
					Collections.swap(nonVus, i, i+1);
				}
			}
		}
		
		return new IteratorSeq(nonVus, sommetCrt);
	}

	@Override
	protected int bound(Integer sommetCourant, ArrayList<Integer> nonVus, int[][] cout, int[] duree) {
		int somme = 0;
		
		
		
		int minPtCrt = Integer.MAX_VALUE;
		for(Integer i : nonVus) {
			
			if(cout[sommetCourant][i] < minPtCrt) {
				minPtCrt = cout[sommetCourant][i];
			}

			int min = cout[i][0];
			for(Integer j : nonVus) {
				if(cout[i][j] < min  ) {
					min = cout[i][j];
				}
			}
			somme = somme + min + duree[i];
		}
		somme = somme + minPtCrt;
		//System.out.println(somme);
		return somme;
	}
}