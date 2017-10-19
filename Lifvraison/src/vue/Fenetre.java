package vue;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controleur.Controleur;
import modeles.DemandeLivraison;
import modeles.Livraison;
import modeles.Plan;
import modeles.Tournee;

import javax.swing.JTextArea;

import javax.swing.SwingConstants;

public class Fenetre extends JFrame
{
	//private VueGraphique vueGraphique;

	// 1.1.1 mainPanel/leftPanel/overMapPanel
	private JPanel overMapPanel;
	// 1.2.1.1 mainPanel/overRightPanel/rightPanel/listeLivraisonsPanel
	private JPanel listeLivraisonsPanel;
	// 1.2.1.2.1 mainPanel/overRightPanel/rightPanel/buttonsPanel/topButtonsPanel
	private JPanel topButtonsPanel;	
	// 1.2.1.2.2 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel
	private JPanel bottomButtonsPanel;
	// 1.2.1.2.2.1 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel/buttonSuivant
	private JButton buttonSuivant;
	// 1.3.1 mainPanel/ongletsPanel/buttonAccueil
	private JButton buttonAccueil;
	// 1.3.2 mainPanel/ongletsPanel/buttonChargementPlan
	private JButton buttonChargementPlan;
	// 1.3.3 mainPanel/ongletsPanel/buttonChargementDemandeLivraison
	private JButton buttonChargementDemandeLivraison;
	// 1.3.4 mainPanel/ongletsPanel/buttonModificationTournee
	private JButton buttonModificationTournee;
	// 1.3.5 mainPanel/ongletsPanel/buttonValidationTournee
	private JButton buttonValidationTournee;
	
	private EcouteurDeBoutons ecouteurDeBoutons;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	public Fenetre(Controleur controleur)
	{
		super();	
		
		ecouteurDeBoutons = new EcouteurDeBoutons(controleur);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// 1 mainPanel
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		getContentPane().add(mainPanel);
		
		// 1.1 mainPanel/leftPanel
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BorderLayout());
		mainPanel.add(leftPanel, BorderLayout.CENTER);
		
		// 1.1.1 mainPanel/leftPanel/overMapPanel
		overMapPanel = new JPanel();
		overMapPanel.setLayout(new CardLayout(50, 50));
		leftPanel.add(overMapPanel, BorderLayout.CENTER);
		
		// 1.1.2 mainPanel/leftPanel/titlePanel
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new BorderLayout());
		titlePanel.setPreferredSize(new Dimension(screenSize.width, screenSize.height / 10));
		leftPanel.add(titlePanel, BorderLayout.SOUTH);
		
		// 1.1.2.1 mainPanel/leftPanel/titlePanel/titleLabel
		JLabel titleLabel = new JLabel("~LIfvraison~", SwingConstants.CENTER);
		titleLabel.setFont(new Font("Serif", Font.PLAIN, 30));
		titlePanel.add(titleLabel, BorderLayout.CENTER);
		
		// 1.2 mainPanel/overRightPanel
		JPanel overRightPanel = new JPanel();
		overRightPanel.setLayout(new CardLayout(50, 50));
		overRightPanel.setPreferredSize(new Dimension(screenSize.width / 3, screenSize.height));
		mainPanel.add(overRightPanel, BorderLayout.EAST);
		
		// 1.2.1 mainPanel/overRightPanel/rightPanel
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new GridLayout(0,2));
		overRightPanel.add(rightPanel);
		
		// 1.2.1.1 mainPanel/overRightPanel/rightPanel/listeLivraisonsPanel
		listeLivraisonsPanel = new JPanel();
		listeLivraisonsPanel.setLayout(new CardLayout(50, 50));
		rightPanel.add(listeLivraisonsPanel);
		
		// 1.2.1.2 mainPanel/overRightPanel/rightPanel/buttonsPanel
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(2, 0, 20, 20));
		rightPanel.add(buttonsPanel);
		
		// 1.2.1.2.1 mainPanel/overRightPanel/rightPanel/buttonsPanel/topButtonsPanel
		topButtonsPanel = new JPanel();
		topButtonsPanel.setLayout(new GridLayout(5, 0, 20, 20));
		buttonsPanel.add(topButtonsPanel);
		
		// 1.2.1.2.2 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel		
		bottomButtonsPanel = new JPanel();
		bottomButtonsPanel.setLayout(new GridLayout(2, 0, 20, 20));
		buttonsPanel.add(bottomButtonsPanel);
		
		// 1.2.1.2.2.1 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel/buttonSuivant
		buttonSuivant = new JButton("Suivant");
		buttonSuivant.addActionListener(ecouteurDeBoutons);
		bottomButtonsPanel.add(buttonSuivant);
		
		// 1.3 mainPanel/ongletsPanel
		JPanel ongletsPanel = new JPanel();
		ongletsPanel.setLayout(new FlowLayout());
		mainPanel.add(ongletsPanel, BorderLayout.NORTH);
		
		// 1.3.1 mainPanel/ongletsPanel/buttonAccueil
		buttonAccueil = new JButton("Accueil");
		buttonAccueil.addActionListener(ecouteurDeBoutons);
		buttonAccueil.setEnabled(false);
		ongletsPanel.add(buttonAccueil);
		
		// 1.3.2 mainPanel/ongletsPanel/buttonChargementPlan		
		buttonChargementPlan = new JButton("Chargement Plan");
		buttonChargementPlan.addActionListener(ecouteurDeBoutons);
		buttonChargementPlan.setEnabled(false);
		ongletsPanel.add(buttonChargementPlan);
		
		// 1.3.3 mainPanel/ongletsPanel/buttonChargementTournee
		buttonChargementDemandeLivraison = new JButton("Chargement Demande Livraison");
		buttonChargementDemandeLivraison.addActionListener(ecouteurDeBoutons);
		buttonChargementDemandeLivraison.setEnabled(false);
		ongletsPanel.add(buttonChargementDemandeLivraison);
		
		// 1.3.3 mainPanel/ongletsPanel/buttonModificationTournee
		buttonModificationTournee = new JButton("Modification Tournee");
		buttonModificationTournee.addActionListener(ecouteurDeBoutons);
		buttonModificationTournee.setEnabled(false);
		ongletsPanel.add(buttonModificationTournee);
		
		// 1.3.5 mainPanel/ongletsPanel/buttonValidationTournee
		buttonValidationTournee = new JButton("Validation Tournee");
		buttonValidationTournee.addActionListener(ecouteurDeBoutons);
		buttonValidationTournee.setEnabled(false);
		ongletsPanel.add(buttonValidationTournee);
		
		/*
		setUndecorated(true);
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

        if(gd.isFullScreenSupported()) {
            try {
                gd.setFullScreenWindow(this);
            }
            finally {
                gd.setFullScreenWindow(null);
            }
        }
        else {
            System.err.println("Full screen not supported");
        }

        setVisible(true);
        
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		this.setUndecorated(true);
		this.setVisible(true);*/
		
		this.setSize(screenSize.width, screenSize.height - 50);

		setVisible(true);
		setModeAccueil();

		//this.vueGraphique = new VueGraphique(plan, this);
	}
	
	public void setModeAccueil()
	{
		setVisible(false);
		
		buttonAccueil.setEnabled(false);
		buttonChargementPlan.setEnabled(false);
		buttonChargementDemandeLivraison.setEnabled(false);
		buttonModificationTournee.setEnabled(false);
		buttonValidationTournee.setEnabled(false);
		
		// 1.1.1 mainPanel/leftPanel/overMapPanel		
		overMapPanel.removeAll();
		
		// 1.1.1.1 mainPanel/leftPanel/overMapPanel/imageLabel
		JLabel imageLabel = new JLabel(new ImageIcon("ihm\\image_livreur.jpg"));
		overMapPanel.add(imageLabel, BorderLayout.CENTER);
		
		bottomButtonsPanel.removeAll();
		
		bottomButtonsPanel.add(new JLabel());
		bottomButtonsPanel.add(buttonSuivant);
		
		setVisible(true);
	}
	
	public void setModePlan(Plan plan)
	{
		setVisible(false);
		
		buttonAccueil.setEnabled(true);
		buttonChargementPlan.setEnabled(false);
		buttonChargementDemandeLivraison.setEnabled(false);
		buttonModificationTournee.setEnabled(false);
		buttonValidationTournee.setEnabled(false);
		
		// 1.1.1 mainPanel/leftPanel/overMapPanel
		overMapPanel.removeAll();
		
		// 1.1.1.1 mainPanel/leftPanel/overMapPanel/mapPanel
		MapPanel mapPanel = new MapPanel(plan, null, null);
		overMapPanel.add(mapPanel);
		
		bottomButtonsPanel.removeAll();
		
		JButton buttonChargerPlan = new JButton("Charger Plan");
		buttonChargerPlan.addActionListener(ecouteurDeBoutons);
		bottomButtonsPanel.add(buttonChargerPlan);
		
		bottomButtonsPanel.add(buttonSuivant);
		
		setVisible(true);
	}

	public void setModeDemandeLivraison(Plan plan, DemandeLivraison demandeLivraisons)
	{
		setVisible(false);
		
		buttonAccueil.setEnabled(true);
		buttonChargementPlan.setEnabled(true);
		buttonChargementDemandeLivraison.setEnabled(false);
		buttonModificationTournee.setEnabled(false);
		buttonValidationTournee.setEnabled(false);
		
		// 1.1.1 mainPanel/leftPanel/overMapPanel
		overMapPanel.removeAll();
		
		// 1.1.1.1 mainPanel/leftPanel/overMapPanel/mapPanel
		MapPanel mapPanel = new MapPanel(plan, demandeLivraisons, null);
		overMapPanel.add(mapPanel);
		
		bottomButtonsPanel.removeAll();
		
		JButton buttonChargerDemandeLivraison = new JButton("Charger Demande Livraison");
		buttonChargerDemandeLivraison.addActionListener(ecouteurDeBoutons);
		bottomButtonsPanel.add(buttonChargerDemandeLivraison);
		
		bottomButtonsPanel.add(buttonSuivant);
		
		setVisible(true);
	}
	
	public void setModeModifierTournee(Plan plan, DemandeLivraison demandeLivraisons, Tournee tournee)
	{
		setVisible(false);

		buttonAccueil.setEnabled(true);
		buttonChargementPlan.setEnabled(true);
		buttonChargementDemandeLivraison.setEnabled(true);
		buttonModificationTournee.setEnabled(false);
		buttonValidationTournee.setEnabled(false);
		
		// 1.1.1 mainPanel/leftPanel/overMapPanel
		overMapPanel.removeAll();
		
		// 1.1.1.1 mainPanel/leftPanel/overMapPanel/mapPanel
		MapPanel mapPanel = new MapPanel(plan, demandeLivraisons, tournee);
		overMapPanel.add(mapPanel);
		
		// 1.2.1.2.1 mainPanel/overRightPanel/rightPanel/buttonsPanel/topButtonsPanel
		topButtonsPanel.removeAll();
		
		// 1.2.1.2.1.1 mainPanel/overRightPanel/rightPanel/buttonsPanel/topButtonsPanel/buttonAjouterLivraison
		JButton buttonAjouterLivraison = new JButton("+");
		topButtonsPanel.add(buttonAjouterLivraison);
		// 1.2.1.2.1.2 mainPanel/overRightPanel/rightPanel/buttonsPanel/topButtonsPanel/buttonSupprimerLivraison
		JButton buttonSupprimerLivraison = new JButton("-");
		topButtonsPanel.add(buttonSupprimerLivraison);
		// 1.2.1.2.1.3 mainPanel/overRightPanel/rightPanel/buttonsPanel/topButtonsPanel/buttonEchangerLivraisons
		JButton buttonEchangerLivraisons = new JButton("<-/->");
		topButtonsPanel.add(buttonEchangerLivraisons);
		// 1.2.1.2.1.4 mainPanel/overRightPanel/rightPanel/buttonsPanel/topButtonsPanel/buttonUndo
		JButton buttonUndo = new JButton("undo");
		topButtonsPanel.add(buttonUndo);
		// 1.2.1.2.1.5 mainPanel/overRightPanel/rightPanel/buttonsPanel/topButtonsPanel/buttonRedo
		JButton buttonRedo = new JButton("redo");
		topButtonsPanel.add(buttonRedo);
		
		// 1.2.1.1 mainPanel/overRightPanel/rightPanel/listeLivraisonsPanel
		listeLivraisonsPanel.removeAll();
		
		// 1.2.1.1.1 mainPanel/overRightPanel/rightPanel/listeLivraisonsPanel/labelListeLivraison
		JTextArea labelListeLivraison = new JTextArea();
		labelListeLivraison.setEditable(false);
		labelListeLivraison.setPreferredSize(new Dimension(200, 300));
		listeLivraisonsPanel.add(labelListeLivraison);
		
		// 1.2.1.1.1.1 mainPanel/overRightPanel/rightPanel/listeLivraisonsPanel/labelListeLivraison/texteListe
		int i = 0;
		String texteListe = "Liste des livraisons\nDepart - " + tournee.getListeHoraire().get(i).getHeureDebut().toString() + "\n";
		for(Livraison livraison : tournee.getLivraisonsOrdonnees())
		{
			if(i < tournee.getLivraisonsOrdonnees().size()-1)
			{
				i = i + 1;
				texteListe = texteListe + i + " - de " + tournee.getListeHoraire().get(i).getHeureDebut().toString() 
						+ " a " + tournee.getListeHoraire().get(i).getHeureFin().toString() + "\n";
			}
		}
		texteListe = texteListe + "Retour à l'entrepot - " + tournee.getListeHoraire().get(i).getHeureFin().toString() + "\n";
		labelListeLivraison.setText(texteListe);
		
		bottomButtonsPanel.removeAll();
		
		JButton buttonValiderTournee = new JButton("Valider Tournee");
		buttonValiderTournee.addActionListener(ecouteurDeBoutons);
		bottomButtonsPanel.add(buttonValiderTournee);
		
		bottomButtonsPanel.add(buttonSuivant);
		
		setVisible(true);
	}
	
	public void setModeValiderTournee(Plan plan, DemandeLivraison demandeLivraisons, Tournee tournee)
	{
		setVisible(false);

		buttonAccueil.setEnabled(true);
		buttonChargementPlan.setEnabled(true);
		buttonChargementDemandeLivraison.setEnabled(true);
		buttonValidationTournee.setEnabled(true);
		buttonValidationTournee.setEnabled(false);
		
		bottomButtonsPanel.removeAll();
		
		setVisible(true);
	}
	
	/*@Override
	public void actionPerformed(ActionEvent e) {
		
		XMLParseur parseur = new XMLParseur();
		PanelChargementPlan panelChargementPlan = new PanelChargementPlan();
		getContentPane().add(panelChargementPlan);
		Plan plan = parseur.chargerPlan(panelChargementPlan.promptForFolder(this));
		
		
		getContentPane().removeAll();
		
		PanelChargementDemandeLivraison panelChargementDemandeLivraison = new PanelChargementDemandeLivraison();
		getContentPane().add(panelChargementDemandeLivraison);
		DemandeLivraison demandeLivraisons = parseur.chargerLivraison(panelChargementDemandeLivraison.promptForFolder(this), plan.getListeIntersection());
		
		getContentPane().removeAll();
		
		Tournee tournee = new Tournee(plan, demandeLivraisons);
		CalculateurTournee calculateurTournee = new CalculateurTournee(tournee);
		calculateurTournee.run();
		
		MapPanel mapPanel = new MapPanel(plan, demandeLivraisons, tournee);
		JScrollPane jsp = new JScrollPane(mapPanel);
		getContentPane().add(jsp);
		setVisible(true);
		
	}*/
	
}