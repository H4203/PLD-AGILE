package vue;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import controleur.Controleur;
import modeles.Plan;
import modeles.DemandeLivraison;
import modeles.Livraison;
import modeles.PlageHoraire;
import modeles.Tournee;

public class VueTextuelle extends JPanel implements Observer
{
	private static final long serialVersionUID = 1L;

	private Fenetre fenetre;

	private DemandeLivraison demandeLivraison;
	private Tournee tournee;

	private ListPanel listPanel;
	
	private JPanel listeLivraison;
	private JPanel listeLivraisonsPanel;
	private JList<String> listTexteLivraison;

	public VueTextuelle(Fenetre fenetre, DemandeLivraison demandeLivraison, Tournee tournee, Controleur controleur) 
	{
		super();

		this.fenetre = fenetre;

		setLayout(new CardLayout(40, 40));
		//setLayout(new BorderLayout());
		
		if (demandeLivraison != null )
		{
			this.demandeLivraison = demandeLivraison;
			demandeLivraison.addObserver(this);
			if (tournee != null )
			{
				this.tournee = tournee;
				tournee.addObserver(this);
			}
		}

		listPanel = new ListPanel(fenetre, demandeLivraison, tournee, controleur);
		//add(listPanel, BorderLayout.CENTER);
		add(listPanel);
	}

	public void setModePlan()
	{
		listPanel.setAffichageDemandeLivraison(false);
		listPanel.setAffichageTournee(false);
		
		listPanel.remplirListe();
		repaint();
	}

	public void setModeDemandeLivraison()
	{
		listPanel.setAffichageDemandeLivraison(true);
		listPanel.setAffichageTournee(false);
		
		listPanel.remplirListe();
		repaint();
	}

	public void setModeTournee()
	{
		listPanel.setAffichageDemandeLivraison(true);
		listPanel.setAffichageTournee(true);
		
		listPanel.remplirListe();
		repaint();
	}

	@Override
	public void update(Observable o, Object arg) 
	{
		listPanel.repaint();
		listPanel.remplirListe();
		repaint();
	}

	public void nouvelleDemandeLivraison ( DemandeLivraison demandeLivraison)
	{
		listPanel.setDemandeLivraison(demandeLivraison);
		demandeLivraison.addObserver(this);
	}
	
	public void nouvelleTournee ( Tournee tournee)
	{
		listPanel.setTournee(tournee);
		tournee.addObserver(this);
	}
	
	public ListPanel getListPanel()
	{
		return listPanel;
	}
}
