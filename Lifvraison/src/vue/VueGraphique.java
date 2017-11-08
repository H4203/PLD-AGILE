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

/**
 * Classe VueGraphique Herite de JPanel Implement Observer
 * 
 * @author H4203
 */
public class VueGraphique extends JPanel implements Observer {
	private static final long serialVersionUID = 1L;

	/**
	 * MapPanel gere l'affichage des elements du Modele
	 */
	private MapPanel mapPanel;

	/**
	 * Constructeur de VueGraphique Observe les objects du Modele
	 * 
	 * @param fenetre
	 * @param plan
	 * @param demandeLivraison
	 * @param tournee
	 * @param controleur
	 */
	public VueGraphique(Fenetre fenetre, Plan plan, DemandeLivraison demandeLivraison, Tournee tournee,
			Controleur controleur) {
		super();

		setLayout(new CardLayout(40, 40));
		setBackground(Color.white);

		if (plan != null) {
			plan.addObserver(this);
			if (demandeLivraison != null) {
				demandeLivraison.addObserver(this);
				if (tournee != null) {
					tournee.addObserver(this);
				}
			}
		}

		mapPanel = new MapPanel(fenetre, plan, demandeLivraison, tournee, controleur);
		add(mapPanel);
	}

	@Override
	public void paintComponent(Graphics g) {

	}

	/**
	 * Gestion de la mise a jour du modele
	 */
	@Override
	public void update(Observable o, Object arg) {
		mapPanel.resize();
	}

	/**
	 * Gestion de la mise a l'echelle du Plan
	 */
	public void resize() {
		mapPanel.resize();
	}

	/**
	 * Change le mode pour le mode Accueil Change les elements du Modele a afficher
	 */
	public void setModeAccueil() {
		mapPanel.setAffichagePlan(false);
		mapPanel.setAffichageDemandeLivraison(false);
		mapPanel.setAffichageTournee(false);
	}

	/**
	 * Change le mode pour le mode Plan Change les elements du Modele a afficher
	 */
	public void setModePlan() {
		mapPanel.resize();

		mapPanel.setAffichagePlan(true);
		mapPanel.setAffichageDemandeLivraison(false);
		mapPanel.setAffichageTournee(false);
	}

	/**
	 * Change le mode pour le mode DemandeLivraison Change les elements du Modele a
	 * afficher
	 */
	public void setModeDemandeLivraison() {
		mapPanel.setAffichagePlan(true);
		mapPanel.setAffichageDemandeLivraison(true);
		mapPanel.setAffichageTournee(false);

		mapPanel.repaint();
		repaint();
	}

	/**
	 * Change le mode pour le mode Tournee Change les elements du Modele a afficher
	 */
	public void setModeTournee() {
		mapPanel.setAffichagePlan(true);
		mapPanel.setAffichageDemandeLivraison(true);
		mapPanel.setAffichageTournee(true);

		mapPanel.repaint();
		repaint();
	}

	/**
	 * Getter du MapPanel
	 * 
	 * @return MapPanel
	 */
	public MapPanel getMapPanel() {
		return mapPanel;
	}

	/**
	 * Fait suivre le Plan au MapPanel Observe le nouveau Plan
	 * 
	 * @param plan
	 *            nouveau Plan
	 */
	public void nouveauPlan(Plan plan) {
		plan.addObserver(this);
		mapPanel.setPlan(plan);
	}

	/**
	 * Fait suivre la DemandeLivrison au MapPanel Observe la nouvelle
	 * DemandeLivraison
	 * 
	 * @param demandeLivraison
	 *            nouvelle DemandeLivraison
	 */
	public void nouvelleDemandeLivraison(DemandeLivraison demandeLivraison) {
		mapPanel.setDemandeLivraison(demandeLivraison);
		demandeLivraison.addObserver(this);
	}

	/**
	 * Fait suivre la Tournee au MapPanel Observe la nouvelle Tournee
	 * 
	 * @param tournee
	 *            nouvelle Tournee
	 */
	public void nouvelleTournee(Tournee tournee) {
		mapPanel.setTournee(tournee);
		tournee.addObserver(this);
	}

	/**
	 * Retourne la tolerance sur la selection au clic depuis MapPanel
	 * 
	 * @return
	 */
	public int getToleranceClic() {
		return mapPanel.getToleranceClic();
	}
}
