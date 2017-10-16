package vue;

import javax.swing.JFrame;

import donnees.XMLParseur;
import modeles.DemandeLivraison;
import modeles.Plan;

public class Fenetre extends JFrame
{
	private VueGraphique vueGraphique;
	
	public Fenetre()
	{
		super();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1800, 1000);
		setVisible(true);
		
		XMLParseur parseur = new XMLParseur();
		
		PanelChargementPlan panelChargementPlan = new PanelChargementPlan();
		getContentPane().add(panelChargementPlan);
		Plan plan = parseur.chargerPlan(panelChargementPlan.promptForFolder(this));
		
		getContentPane().removeAll();
		
		PanelChargementDemandeLivraison panelChargementDemandeLivraison = new PanelChargementDemandeLivraison();
		getContentPane().add(panelChargementDemandeLivraison);
		DemandeLivraison demandeLivraisons = parseur.chargerLivraison(panelChargementDemandeLivraison.promptForFolder(this), plan.getListeIntersection());
		
		getContentPane().removeAll();
		
		MapPanel mapPanel = new MapPanel(plan, demandeLivraisons);
		getContentPane().add(mapPanel);
		getContentPane().repaint();
		
		//this.vueGraphique = new VueGraphique(plan, this);
	}
}