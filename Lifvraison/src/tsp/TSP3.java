package tsp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collections;

public class TSP3 extends TSP2 {
	
	@Override
	protected Iterator<Integer> iterator(Integer sommetCrt, ArrayList<Integer> nonVus, int[][] cout, int[] duree) {
		
		boolean tableauNonTrie = true;
		while(tableauNonTrie) {
			tableauNonTrie = false;
			for(int i = 0; i < nonVus.size()-1; i++) {
				if(cout[sommetCrt][i] > cout[sommetCrt][i+1]) {
					Collections.swap(nonVus, i, i+1);
					tableauNonTrie = true;
				}
			}
		}
		
		return new IteratorSeq(nonVus, sommetCrt);
	}

	
}