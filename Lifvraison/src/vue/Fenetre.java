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
		add(panelChargementPlan);
		
		//Plan plan = parseur.chargerPlan(panelChargementPlan.promptForFolder(this));
		
		Plan plan = parseur.chargerPlan("data\\planLyonGrand.xml");

		
		
		DemandeLivraison demandeLivraisons = parseur.chargerLivraison("data\\DLGrand20.xml", plan.getListeIntersection());
		
		MapPanel mapPanel = new MapPanel(plan, demandeLivraisons);
		add(mapPanel);
		
		this.vueGraphique = new VueGraphique(plan, this);
	}
}