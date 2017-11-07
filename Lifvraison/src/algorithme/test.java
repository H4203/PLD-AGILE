package algorithme;

import donnees.ParseurException;
import donnees.XMLParseur;
import modeles.DemandeLivraison;
import modeles.Livraison;
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
			xml.chargerLivraison(dl, "data/demandeLivraison/DLgrand10.xml",lePlan.getListeIntersection());
		} catch (ParseurException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Tournee laTournee = new Tournee(lePlan, dl);
		
		CalculateurTournee cl = new CalculateurTournee(laTournee);
		cl.run();
		laTournee = cl.getLaTournee();
		for(Livraison l : laTournee.getLivraisonsOrdonnees()) {
			System.out.print(" " + l.getIntersection().getId() + ";");
		}
		System.out.println();
		//cl.ajouterLivraison(4, new Livraison(lePlan.getListeIntersection().get((long) 245039019), 300));
		cl.echangerDeuxLivraison(4, 5);
		laTournee = cl.getLaTournee();
		for(Livraison l : laTournee.getLivraisonsOrdonnees()) {
			System.out.print(" " + l.getIntersection().getId() + ";");
		}
		
	}
}
