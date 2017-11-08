package vue;

import java.awt.CardLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import controleur.Controleur;
import modeles.DemandeLivraison;
import modeles.Tournee;

/**
 * La vue textuelle de l'ihm
 * Elle observe les objets pour se mettre a jour
 * @author H4203
 *
 */
/**
 * @author heyhey
 *
 */
public class VueTextuelle extends JPanel implements Observer {

	/**
	 * Panel contenant la liste de la vue textuelle
	 */
	private ListPanel listPanel;

	/**
	 * Constructeur
	 * 
	 * @param fenetre
	 *            la fenetre ou il se trouvera
	 * @param demandeLivraison
	 *            la demande de livraison de la session en cours
	 * @param tournee
	 *            la tournee de la session en cours
	 * @param controleur
	 *            controlleur de la session en cours
	 */
	public VueTextuelle(Fenetre fenetre, DemandeLivraison demandeLivraison, Tournee tournee, Controleur controleur) {
		super();

		setLayout(new CardLayout(40, 40));

		if (demandeLivraison != null) {
			demandeLivraison.addObserver(this);
			if (tournee != null) {
				tournee.addObserver(this);
			}
		}

		listPanel = new ListPanel(fenetre, demandeLivraison, tournee, controleur);
		add(listPanel);
	}

	/**
	 * Change le mode de la vue textuelle pour le mode ou le plan (troncon et
	 * intersection) est affiche La Vue textuelle est alors vide
	 */
	public void setModePlan() {
		listPanel.setAffichageDemandeLivraison(false);
		listPanel.setAffichageTournee(false);

		listPanel.remplirListe();
		repaint();
	}

	/**
	 * Change le mode de la vue textuelle pour le mode ou le plan est affiche avec
	 * une demande de livraison
	 */
	public void setModeDemandeLivraison() {
		listPanel.setAffichageDemandeLivraison(true);
		listPanel.setAffichageTournee(false);

		listPanel.remplirListe();
		repaint();
	}

	/**
	 * Change le mode de la vue textuelle pour le mode ou le plan est affiche avec
	 * une tournee
	 */
	public void setModeTournee() {
		listPanel.setAffichageDemandeLivraison(true);
		listPanel.setAffichageTournee(true);

		listPanel.remplirListe();
		repaint();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		listPanel.repaint();
		listPanel.remplirListe();
		repaint();
	}

	/**
	 * Met a jour la demande de livraison
	 * 
	 * @param demandeLivraison
	 *            la nouvelle demande de livraison
	 */
	public void nouvelleDemandeLivraison(DemandeLivraison demandeLivraison) {
		listPanel.setDemandeLivraison(demandeLivraison);
		demandeLivraison.addObserver(this);
	}

	/**
	 * Met a jour la tournee
	 * 
	 * @param tournee
	 *            la nouvelle tournee
	 */
	public void nouvelleTournee(Tournee tournee) {
		listPanel.setTournee(tournee);
		tournee.addObserver(this);
	}

	/**
	 * Renvoie la liste de la vue textuelle
	 * 
	 * @return la liste de la vue
	 */
	public ListPanel getListPanel() {
		return listPanel;
	}
}
