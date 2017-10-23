package vue;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import modeles.Plan;
import modeles.DemandeLivraison;
import modeles.Tournee;

public class VueGraphique extends JPanel implements Observer
{
	private static final long serialVersionUID = 1L;

	private MapPanel mapPanel;

	public VueGraphique(Fenetre fenetre, Plan plan, DemandeLivraison demandeLivraison, Tournee tournee) 
	{
		super();
		
		setLayout(new CardLayout(50, 50));
		setBackground(Color.white);
		
		plan.addObserver(this);
		demandeLivraison.addObserver(this);
		tournee.addObserver(this);
		
		mapPanel = new MapPanel(plan, demandeLivraison, tournee);
		add(mapPanel);
	}
	
	@Override
	public void paintComponent(Graphics g) 
	{
		mapPanel.paintComponent(g);
	}
	
	@Override
	public void update(Observable o, Object arg) 
	{
		mapPanel.repaint();
		repaint();
	}
	
	public void setModeAccueil()
	{
		mapPanel.setAffichagePlan(false);
		mapPanel.setAffichageDemandeLivraison(false);
		mapPanel.setAffichageTournee(false);
		
		mapPanel.repaint();
		repaint();
	}
	
	public void setModeChargementPlan()
	{
		mapPanel.resize();
		
		mapPanel.setAffichagePlan(true);
		mapPanel.setAffichageDemandeLivraison(false);
		mapPanel.setAffichageTournee(false);
		
		mapPanel.repaint();
		repaint();
	}
	
	public void setModeChargementDemandeLivraison()
	{
		mapPanel.setAffichagePlan(true);
		mapPanel.setAffichageDemandeLivraison(true);
		mapPanel.setAffichageTournee(false);
		
		mapPanel.repaint();
		repaint();
	}
	
	public void setModeCalculTournee()
	{
		mapPanel.setAffichagePlan(true);
		mapPanel.setAffichageDemandeLivraison(true);
		mapPanel.setAffichageTournee(true);
		
		mapPanel.repaint();
		repaint();
	}
}
