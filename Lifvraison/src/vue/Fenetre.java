package vue;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controleur.Controleur;
import modeles.DemandeLivraison;
import modeles.Plan;
import modeles.Tournee;

import javax.swing.SwingConstants;

/**
 * Classe Fenetre Herite de JFrame Gere la VueGraphique, la VueTextuelle et les
 * boutons
 * 
 * @author H4203
 */
public class Fenetre extends JFrame {
	private static final long serialVersionUID = 1L;

	/**
	 * VueGraphique contenant le plan
	 */
	private VueGraphique vueGraphique;
	/**
	 * VueTextuelle contenant les details des Livraisons et elements du plan
	 */
	private VueTextuelle vueTextuelle;

	/**
	 * Panel des boutons des actions de modification de Tournee
	 */
	private JPanel overActionButtonsPanel;

	/**
	 * Boutons des differentes actions de modification de Tournee
	 */
	private JButton buttonAjouterLivraison;
	private JButton buttonSupprimerLivraison;
	private JButton buttonEchangerLivraisons;
	private JButton buttonUndo;
	private JButton buttonRedo;

	/**
	 * Boutons des differents etats
	 */
	private JButton buttonChargerPlan;
	private JButton buttonChargerDemandeLivraison;
	private JButton buttonCalulerTournee;
	private JButton buttonValiderTournee;
	private JButton buttonGenererFeuilleDeRoute;

	/**
	 * Indication pour l'utilisateur affichee en dessous du plan
	 */
	private JLabel indicationsLabel;

	/**
	 * Constructeur de Fenetre Cree et ajoute tous les elements de la fenetre
	 * 
	 * @param controleur
	 * @param plan
	 * @param demandeLivraison
	 * @param tournee
	 */
	public Fenetre(Controleur controleur, Plan plan, DemandeLivraison demandeLivraison, Tournee tournee) {
		super();

		EcouteurDeBoutons ecouteurDeBoutons = new EcouteurDeBoutons(controleur);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 1 mainPanel
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		getContentPane().add(mainPanel);

		// 1.1 mainPanel/centerPanel
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(1, 2, 20, 20));
		mainPanel.add(centerPanel, BorderLayout.CENTER);

		// 1.1.1 mainPanel/centerPanel/leftPanel
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BorderLayout());
		centerPanel.add(leftPanel);

		// 1.1.1.1 mainPanel/centerPanel/leftPanel/vueGraphique
		vueGraphique = new VueGraphique(this, plan, demandeLivraison, tournee, controleur);
		leftPanel.add(vueGraphique, BorderLayout.CENTER);

		// 1.1.1.2 mainPanel/centerPanel/leftPanel/indicationsPanel
		JPanel indicationsPanel = new JPanel();
		indicationsPanel.setLayout(new CardLayout(50, 0));
		indicationsPanel.setPreferredSize(new Dimension(500, 70));
		leftPanel.add(indicationsPanel, BorderLayout.SOUTH);

		// 1.1.1.2.1 mainPanel/centerPanel/leftPanel/indicationsPanel/indicationsLabel
		indicationsLabel = new JLabel("~LIfvraison~", SwingConstants.LEFT);
		indicationsLabel.setFont(new Font("Serif", Font.PLAIN, 15));
		indicationsPanel.add(indicationsLabel);

		// 1.1.2 mainPanel/centerPanel/rightPanel
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BorderLayout());
		centerPanel.add(rightPanel);

		// 1.1.2.1 mainPanel/centerPanel/rightPanel/vueTextuelle
		vueTextuelle = new VueTextuelle(this, demandeLivraison, tournee, controleur);
		rightPanel.add(vueTextuelle, BorderLayout.CENTER);

		// 1.1.2.2 mainPanel/centerPanel/rightPanel/overActionButtonsPanel
		overActionButtonsPanel = new JPanel();
		overActionButtonsPanel.setLayout(new CardLayout(40, 40));
		rightPanel.add(overActionButtonsPanel, BorderLayout.SOUTH);

		// 1.1.2.2.1
		// mainPanel/centerPanel/rightPanel/overActionButtonsPanel/actionButtonsPanel
		JPanel actionButtonsPanel = new JPanel();
		actionButtonsPanel.setLayout(new GridLayout(2, 3, 20, 20));
		overActionButtonsPanel.add(actionButtonsPanel);

		// 1.1.2.2.1.1
		// mainPanel/centerPanel/rightPanel/overActionButtonsPanel/actionButtonsPanel/buttonAjouterLivraison
		buttonAjouterLivraison = new JButton("Ajouter une Livraison");
		buttonAjouterLivraison.addActionListener(ecouteurDeBoutons);
		actionButtonsPanel.add(buttonAjouterLivraison);
		// 1.1.2.2.1.2
		// mainPanel/centerPanel/rightPanel/overActionButtonsPanel/actionButtonsPanel/buttonSupprimerLivraison
		buttonSupprimerLivraison = new JButton("Retirer une Livraison");
		buttonSupprimerLivraison.addActionListener(ecouteurDeBoutons);
		actionButtonsPanel.add(buttonSupprimerLivraison);
		// 1.1.2.2.1.3
		// mainPanel/centerPanel/rightPanel/overActionButtonsPanel/actionButtonsPanel/buttonEchangerLivraisons
		buttonEchangerLivraisons = new JButton("Echanger 2 Livraisons");
		buttonEchangerLivraisons.addActionListener(ecouteurDeBoutons);
		actionButtonsPanel.add(buttonEchangerLivraisons);
		// 1.1.2.2.1.4
		// mainPanel/centerPanel/rightPanel/overActionButtonsPanel/actionButtonsPanel/buttonUndo
		buttonUndo = new JButton("Annuler");
		buttonUndo.addActionListener(ecouteurDeBoutons);
		actionButtonsPanel.add(buttonUndo);
		// 1.1.2.2.1.5
		// mainPanel/centerPanel/rightPanel/overActionButtonsPanel/actionButtonsPanel/buttonRedo
		buttonRedo = new JButton("Retablir");
		buttonRedo.addActionListener(ecouteurDeBoutons);
		actionButtonsPanel.add(buttonRedo);

		// 1.2 mainPanel/bottomPanel
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new CardLayout(40, 40));
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);

		// 1.2.1 mainPanel/bottomPanel/stateButtonsPanel
		JPanel stateButtonsPanel = new JPanel();
		stateButtonsPanel.setLayout(new GridLayout(1, 5, 20, 20));
		bottomPanel.add(stateButtonsPanel);

		// 1.2.1.1 mainPanel/bottomPanel/stateButtonsPanel/buttonChargerPlan
		buttonChargerPlan = new JButton("Charger Plan");
		buttonChargerPlan.addActionListener(ecouteurDeBoutons);
		stateButtonsPanel.add(buttonChargerPlan);
		// 1.2.1.2 mainPanel/bottomPanel/stateButtonsPanel/buttonChargerDemandeLivraison
		buttonChargerDemandeLivraison = new JButton("Charger Demande Livraison");
		buttonChargerDemandeLivraison.addActionListener(ecouteurDeBoutons);
		stateButtonsPanel.add(buttonChargerDemandeLivraison);
		// 1.2.1.3 mainPanel/bottomPanel/stateButtonsPanel/buttonChargerDemandeLivraison
		buttonCalulerTournee = new JButton("Calculer Tournee");
		buttonCalulerTournee.addActionListener(ecouteurDeBoutons);
		stateButtonsPanel.add(buttonCalulerTournee);
		// 1.2.1.4 mainPanel/bottomPanel/stateButtonsPanel/buttonValiderTournee
		buttonValiderTournee = new JButton("Valider Tournee");
		buttonValiderTournee.addActionListener(ecouteurDeBoutons);
		stateButtonsPanel.add(buttonValiderTournee);
		// 1.2.1.5 mainPanel/bottomPanel/stateButtonsPanel/buttonGenererFeuilleDeRoute
		buttonGenererFeuilleDeRoute = new JButton("Generer Feuille de Route");
		buttonGenererFeuilleDeRoute.addActionListener(ecouteurDeBoutons);
		stateButtonsPanel.add(buttonGenererFeuilleDeRoute);

		addComponentListener(new EcouteurDeFenetre(vueGraphique, vueTextuelle));

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(screenSize.width, screenSize.height - 50);

		repaint();
		setVisible(true);
	}

	/**
	 * Fait suivre le nouveau Plan a la VueGraphique Change le mode de la Fenetre
	 * pour le mode chargement de DemandeLivraison
	 * 
	 * @param plan
	 *            nouveau Plan
	 */
	public void chargerPlan(Plan plan) {
		vueGraphique.nouveauPlan(plan);
		setModeChargementDemandeLivraison();
	}

	/**
	 * Fait suivre la nouvelle DemandeLivraison a la VueGraphique et a la
	 * VueTextuelle Change le mode de la Fenetre pour le mode calcul de Tournee
	 * 
	 * @param demandeLivraison
	 *            nouvelle DemandeLivraison
	 */
	public void chargerDemandeLivraison(DemandeLivraison demandeLivraison) {
		vueGraphique.nouvelleDemandeLivraison(demandeLivraison);
		vueTextuelle.nouvelleDemandeLivraison(demandeLivraison);
		setModeCalculTournee();
	}

	/**
	 * Fait suivre la nouvelle Tournee a la VueGraphique et a la VueTextuelle Change
	 * le mode de la Fenetre pour le mode modification de Tournee
	 * 
	 * @param tournee
	 *            nouvelle Tournee
	 */
	public void chargerTournee(Tournee tournee) {
		vueGraphique.nouvelleTournee(tournee);
		vueTextuelle.nouvelleTournee(tournee);
		setModeModificationTournee();
	}

	/**
	 * Change le mode de la Fenetre pour le mode chargement de Plan Change les modes
	 * de VueGraphique et VueTextuelle pour le mode Plan
	 */
	public void setModeChargementPlan() {
		setIndicationLabel("<html>Cliquez sur Charger Plan pour choisir le fichier du plan</html>");

		vueGraphique.setModePlan();
		vueTextuelle.setModePlan();

		overActionButtonsPanel.setVisible(false);

		buttonChargerPlan.setEnabled(true);
		buttonChargerDemandeLivraison.setEnabled(false);
		buttonCalulerTournee.setEnabled(false);
		buttonValiderTournee.setEnabled(false);
		buttonGenererFeuilleDeRoute.setEnabled(false);

		repaint();
		setVisible(true);
	}

	/**
	 * Change le mode de la Fenetre pour le mode chargement de DemandeLivraison
	 * Change le mode de VueGraphique pour le mode DemandeLivraison Change le mode
	 * de VueTextuelle pour le mode Plan
	 */
	public void setModeChargementDemandeLivraison() {
		setIndicationLabel(
				"<html>Plan chargé avec succès<br>Cliquez sur Charger Demande Livraison pour choisir le fichier de demande de livraison</html>");

		vueGraphique.setModeDemandeLivraison();
		vueTextuelle.setModePlan();

		overActionButtonsPanel.setVisible(false);

		buttonChargerPlan.setEnabled(true);
		buttonChargerDemandeLivraison.setEnabled(true);
		buttonCalulerTournee.setEnabled(false);
		buttonValiderTournee.setEnabled(false);
		buttonGenererFeuilleDeRoute.setEnabled(false);

		repaint();
		setVisible(true);
	}

	/**
	 * Change le mode de la Fenetre pour le mode calcul de Tournee Change les modes
	 * de VueGraphique et VueTextuelle pour le mode DemandeLivraison
	 */
	public void setModeCalculTournee() {
		setIndicationLabel(
				"<html>Demande de livraison chargée avec succès<br>Cliquez sur Calculer Tournee pour lancer le calcul de la tournee</html>");

		vueGraphique.setModeDemandeLivraison();
		vueTextuelle.setModeDemandeLivraison();

		overActionButtonsPanel.setVisible(false);

		buttonChargerPlan.setEnabled(true);
		buttonChargerDemandeLivraison.setEnabled(true);
		buttonCalulerTournee.setEnabled(true);
		buttonValiderTournee.setEnabled(false);
		buttonGenererFeuilleDeRoute.setEnabled(false);

		repaint();
		setVisible(true);
	}

	/**
	 * Change le mode de la Fenetre pour le mode modification de Tournee Dans l'etat
	 * par defaut
	 */
	public void setModeModificationTournee() {
		setModeModificationTournee("");
	}

	/**
	 * Change le mode de la Fenetre pour le mode modification de Tournee Dans l'etat
	 * actuel Change les modes de VueGraphique et VueTextuelle pour le mode Tournee
	 * 
	 * @param etat
	 *            etat actuel de la modification de Tournee
	 */
	public void setModeModificationTournee(String etat) {
		vueGraphique.setModeTournee();
		vueTextuelle.setModeTournee();

		overActionButtonsPanel.setVisible(true);

		if (etat.equals("AjoutLivraison") || etat.equals("SuppressionLivraison")
				|| etat.equals("IntervertirLivraisons")) {
			buttonAjouterLivraison.setEnabled(false);
			buttonSupprimerLivraison.setEnabled(false);
			buttonEchangerLivraisons.setEnabled(false);
			buttonRedo.setEnabled(false);

			buttonChargerPlan.setEnabled(false);
			buttonChargerDemandeLivraison.setEnabled(false);
			buttonCalulerTournee.setEnabled(false);
			buttonValiderTournee.setEnabled(false);
			buttonGenererFeuilleDeRoute.setEnabled(false);
		} else {
			setIndicationLabel(
					"<html>Tournée calculée avec succès<br>Utilisez les boutons de droite pour modifier votre tournee, puis cliquez sur Valider Tournee pour valider votre tournee</html>");

			buttonAjouterLivraison.setEnabled(true);
			buttonSupprimerLivraison.setEnabled(true);
			buttonEchangerLivraisons.setEnabled(true);
			buttonRedo.setEnabled(true);

			buttonChargerPlan.setEnabled(true);
			buttonChargerDemandeLivraison.setEnabled(true);
			buttonCalulerTournee.setEnabled(true);
			buttonValiderTournee.setEnabled(true);
			buttonGenererFeuilleDeRoute.setEnabled(false);
		}

		repaint();
		setVisible(true);
	}

	/**
	 * Change le mode de la Fenetre pour le mode generation de FeuilleDeRoute Change
	 * les modes de VueGraphique et VueTextuelle pour le mode Tournee
	 */
	public void setModeGenerationFeuilleDeRoute() {
		setIndicationLabel(
				"<html>Tournee validee<br>Cliquez sur Generer Feuille de Route pour enregistrer votre fichier</html>");

		vueGraphique.setModeTournee();
		vueTextuelle.setModeTournee();

		overActionButtonsPanel.setVisible(false);

		buttonChargerPlan.setEnabled(true);
		buttonChargerDemandeLivraison.setEnabled(true);
		buttonCalulerTournee.setEnabled(true);
		buttonValiderTournee.setEnabled(true);
		buttonGenererFeuilleDeRoute.setEnabled(true);

		repaint();
		setVisible(true);
	}

	/**
	 * Getter VueGraphique
	 * 
	 * @return vueGraphique
	 */
	public VueGraphique getVueGraphique() {
		return vueGraphique;
	}

	/**
	 * Getter VueTextuelle
	 * 
	 * @return vueTextuelle
	 */
	public VueTextuelle getVueTextuelle() {
		return vueTextuelle;
	}

	/**
	 * Change l'indication affichee en dessous du plan
	 * 
	 * @param indication
	 *            a afficher
	 */
	public void setIndicationLabel(String indication) {
		indicationsLabel.setText(indication);
	}
}