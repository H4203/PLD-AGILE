package vue;

import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

import modeles.Plan;

public class Fenetre extends JFrame
{
	private VueGraphique vueGraphique;
	
	public Fenetre(String titre, Plan plan)
	{
		super(titre);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MapPanel mapPanel = new MapPanel(plan);
		add(mapPanel);
		
		setSize(800, 600);
		setVisible(true);
		
		this.vueGraphique = new VueGraphique(plan, this);
	}
}