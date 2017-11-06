package algorithme;

import donnees.ParseurException;
import donnees.XMLParseur;
import modeles.DemandeLivraison;
import modeles.Plan;
import modeles.Tournee;

public class test {
	public static void main (String[] args)
	{
		XMLParseur xml = null;;
		try {
			xml = new XMLParseur();
		} catch (ParseurException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		Plan lePlan = new Plan();
				
		try {
			xml.chargerPlan(lePlan, "data/plan/planLyonGrand.xml");
		} catch (ParseurException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		DemandeLivraison dl = new DemandeLivraison();
		
		try {
			xml.chargerLivraison(dl, "data/demandeLivraison/DLgrand20.xml",lePlan.getListeIntersection());
		} catch (ParseurException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Tournee laTournee = new Tournee(lePlan, dl);
		
		CalculateurTournee cl = new CalculateurTournee(laTournee);
		cl.start();
		
		System.out.println("Thread lance");
		
		while(cl.isAlive()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			Integer[] tab = cl.getCurrentSolution();
			System.out.print("[");
			for(int i = 0; i < tab.length; i++) {
				System.out.print(tab[i]);
				if(i != tab.length-1) {
					System.out.print(";");
				}
				
			}
			System.out.println("]");
		}
		
		
	}
}
