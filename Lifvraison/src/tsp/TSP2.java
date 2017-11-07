package tsp;

import java.util.ArrayList;
public class TSP2 extends TSP1 {

	@Override
	protected int bound(Integer sommetCourant, ArrayList<Integer> nonVus, int[][] cout, int[] duree, int[] tempsDebutPlage, int[] tempsFinPlage, int coutVus) {
		
		for(Integer i : nonVus) {
			if(coutVus + cout[sommetCourant][i] + duree[i] > tempsFinPlage[i]) {
				return Integer.MAX_VALUE;
			}
		}
		
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
				if( (cout[i][j] < min) && (i != j)  ) {
					min = cout[i][j];
				}
			}
			somme = somme + min + duree[i];
		}
		
		//System.out.println(somme);
		return somme;
	}
}