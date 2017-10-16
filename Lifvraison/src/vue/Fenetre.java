package vue;

import javax.swing.JFrame;
import modeles.DemandeLivraison;
import modeles.Plan;

public class Fenetre extends JFrame
{
	private VueGraphique vueGraphique;
	
	public Fenetre(String titre, Plan plan, DemandeLivraison demandeLivraison)
	{
		super(titre);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MapPanel mapPanel = new MapPanel(plan, demandeLivraison);
		add(mapPanel);
		
		setSize(800, 600);
		setVisible(true);
		
		this.vueGraphique = new VueGraphique(plan, this);
	}
}