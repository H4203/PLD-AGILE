package vue;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import controleur.Controleur;
import modeles.Plan;
import modeles.DemandeLivraison;
import modeles.Tournee;

public class VueGraphique extends JPanel implements Observer
{
	private static final long serialVersionUID = 1L;
	
	private MapPanel mapPanel;

	public VueGraphique(Fenetre fenetre, Plan plan, DemandeLivraison demandeLivraison, Tournee tournee, Controleur controleur) 
	{
		super();
		
		setLayout(new CardLayout(50, 50));
		setBackground(Color.white);
		
		// modification plan peut etre null
		if ( plan != null)
		{
			plan.addObserver(this);
			if ( demandeLivraison != null)
			{
				demandeLivraison.addObserver(this);
				if ( tournee != null)
				{
					tournee.addObserver(this);
				}
			}
		}

		mapPanel = new MapPanel(fenetre, plan, demandeLivraison, tournee, controleur);
		add(mapPanel);
	}
	
	@Override
	public void paintComponent(Graphics g) 
	{
		//mapPanel.repaint();
		
		//repaint();
	}
	
	@Override
	public void update(Observable o, Object arg) 
	{
		mapPanel.resize();
		mapPanel.repaint();
		
		//repaint();
	}
	
	public void resize()
	{
		mapPanel.resize();
	}
	
	public void setModeAccueil()
	{
		mapPanel.setAffichagePlan(false);
		mapPanel.setAffichageDemandeLivraison(false);
		mapPanel.setAffichageTournee(false);
	}
	
	public void setModeChargementPlan()
	{
		mapPanel.resize();
		
		mapPanel.setAffichagePlan(true);
		mapPanel.setAffichageDemandeLivraison(false);
		mapPanel.setAffichageTournee(false);
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
	
	public MapPanel getMapPanel()
	{
		return mapPanel;
	}

	// modification plan peut etre null
	public void nouveauPlan ( Plan plan)
	{
		plan.addObserver(this);
		mapPanel.setPlan(plan);
	}
	public void nouvelleDemandeLivraison ( DemandeLivraison demandeLivraison)
	{
		mapPanel.setDemandeLivraison(demandeLivraison);
		demandeLivraison.addObserver(this);
	}
	public void nouvelleTournee ( Tournee tournee)
	{
		mapPanel.setTournee(tournee);
		tournee.addObserver(this);
	}
	public int getToleranceClic()
	{
		return mapPanel.getToleranceClic();
	}
}
