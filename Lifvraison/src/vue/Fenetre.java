package vue;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	
	private EcouteurDeBoutons ecouteurDeBoutons;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	public Fenetre (Controleur controleur)
	{
		super();	
		
		ecouteurDeBoutons = new EcouteurDeBoutons ( controleur );
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// 1 mainPanel
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(0,2));
		getContentPane().add(mainPanel);
		
		// 1.1 mainPanel/leftPanel
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BorderLayout());
		mainPanel.add(leftPanel);
		
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
		
		// 1.1.2.2 mainPanel/leftPanel/titlePanel/buttonAccueil
		JButton buttonAccueil = new JButton("Accueil");
		buttonAccueil.addActionListener(ecouteurDeBoutons);
		titlePanel.add(buttonAccueil, BorderLayout.WEST);
		
		// 1.2 mainPanel/overRightPanel
		JPanel overRightPanel = new JPanel();
		overRightPanel.setLayout(new CardLayout(50, 50));
		overRightPanel.setPreferredSize(new Dimension(screenSize.width / 3, screenSize.height));
		mainPanel.add(overRightPanel);
		
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
		bottomButtonsPanel.setLayout(new GridLayout(4, 0, 20, 20));
		buttonsPanel.add(bottomButtonsPanel);
		
		/*
		setUndecorated(true);
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

        if (gd.isFullScreenSupported()) {
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
	
	public void setModeAccueil ()
	{
		setVisible(false);
		
		// 1.1.1 mainPanel/leftPanel/overMapPanel		
		overMapPanel.removeAll();
		
		// 1.1.1.1 mainPanel/leftPanel/overMapPanel/imageLabel
		JLabel imageLabel = new JLabel(new ImageIcon("ihm\\image_livreur.jpg"));
		overMapPanel.add(imageLabel, BorderLayout.CENTER);
		
		// 1.2.1.2.2 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel
		bottomButtonsPanel.removeAll();
		
		// 1.2.1.2.2.1 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel/Buttons		
		bottomButtonsPanel.add(new JLabel());
		bottomButtonsPanel.add(new JLabel());
		bottomButtonsPanel.add(new JLabel());
		
		// 1.2.1.2.2.2 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel/buttonChargementPlan		
		JButton buttonChargementPlan = new JButton("Charger Plan");
		buttonChargementPlan.addActionListener(ecouteurDeBoutons);
		bottomButtonsPanel.add(buttonChargementPlan);
		
		setVisible(true);
	}
	
	public void setModePlan ( Plan plan )
	{
		setVisible(false);
		
		// 1.1.1 mainPanel/leftPanel/overMapPanel
		overMapPanel.removeAll();
		
		// 1.1.1.1 mainPanel/leftPanel/overMapPanel/mapPanel
		MapPanel mapPanel = new MapPanel(plan, null, null);
		overMapPanel.add(mapPanel);
		
		// 1.2.1.2.2 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel
		bottomButtonsPanel.removeAll();
		
		// 1.2.1.2.2.1 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel/Buttons
		bottomButtonsPanel.add(new JLabel());
		bottomButtonsPanel.add(new JLabel());

		// 1.2.1.2.2.2 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel/buttonChargementPlan		
		JButton buttonChargementPlan = new JButton("Charger Plan");
		buttonChargementPlan.addActionListener(ecouteurDeBoutons);
		bottomButtonsPanel.add(buttonChargementPlan);
		
		// 1.2.1.2.2.3 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel/buttonChargementTournee
		JButton buttonChargementTournee = new JButton ("Charger Livraison");
		buttonChargementTournee.addActionListener(ecouteurDeBoutons);
		bottomButtonsPanel.add(buttonChargementTournee);
		
		setVisible(true);
	}

	public void setModeDemandeLivraison (Plan plan, DemandeLivraison demandeLivraisons)
	{
		setVisible(false);
		
		// 1.1.1 mainPanel/leftPanel/overMapPanel
		overMapPanel.removeAll();
		
		// 1.1.1.1 mainPanel/leftPanel/overMapPanel/mapPanel
		MapPanel mapPanel = new MapPanel(plan, demandeLivraisons, null);
		overMapPanel.add(mapPanel);
		
		// 1.2.1.2.2 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel
		bottomButtonsPanel.removeAll();
		
		// 1.2.1.2.2.1 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel/Buttons
		bottomButtonsPanel.add(new JLabel());

		// 1.2.1.2.2.2 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel/buttonChargementPlan		
		JButton buttonChargementPlan = new JButton("Charger Plan");
		buttonChargementPlan.addActionListener(ecouteurDeBoutons);
		bottomButtonsPanel.add(buttonChargementPlan);
		
		// 1.2.1.2.2.3 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel/buttonChargementTournee
		JButton buttonChargementTournee = new JButton ("Charger Livraison");
		buttonChargementTournee.addActionListener(ecouteurDeBoutons);
		bottomButtonsPanel.add(buttonChargementTournee);
		
		// 1.2.1.2.2.4 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel/buttonCalculTournee
		JButton buttonCalculTournee = new JButton("Calculer Tournee");
		buttonCalculTournee.addActionListener(ecouteurDeBoutons);
		bottomButtonsPanel.add(buttonCalculTournee);
		
		setVisible(true);
	}
	
	public void setModeTournee (Plan plan, DemandeLivraison demandeLivraisons, Tournee tournee)
	{
		setVisible(false);

		// 1.1.1 mainPanel/leftPanel/overMapPanel
		overMapPanel.removeAll();
		
		// 1.1.1.1 mainPanel/leftPanel/overMapPanel/mapPanel
		MapPanel mapPanel = new MapPanel(plan, demandeLivraisons, tournee);
		overMapPanel.add(mapPanel);
		
		// 1.2.1.2.2 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel
		bottomButtonsPanel.removeAll();

		// 1.2.1.2.2.1 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel/Buttons
		bottomButtonsPanel.add(new JLabel());
		
		// 1.2.1.2.2.2 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel/buttonChargementPlan		
		JButton buttonChargementPlan = new JButton("Charger Plan");
		buttonChargementPlan.addActionListener(ecouteurDeBoutons);
		bottomButtonsPanel.add(buttonChargementPlan);
		
		// 1.2.1.2.2.3 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel/buttonChargementTournee
		JButton buttonChargementTournee = new JButton ("Charger Livraison");
		buttonChargementTournee.addActionListener(ecouteurDeBoutons);
		bottomButtonsPanel.add(buttonChargementTournee);
		
		// 1.2.1.2.2.4 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel/buttonValiderTournee
		JButton buttonValider = new JButton("Valider Tournee");
		buttonValider.addActionListener(ecouteurDeBoutons);
		bottomButtonsPanel.add(buttonValider);
		
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
		for (Livraison livraison : tournee.getLivraisonsOrdonnees())
		{
			if (i < tournee.getLivraisonsOrdonnees().size()-1 )
			{
				i = i + 1;
				texteListe = texteListe + i + " - de " + tournee.getListeHoraire().get(i).getHeureDebut().toString() 
						+ " a " + tournee.getListeHoraire().get(i).getHeureFin().toString() + "\n";
			}
		}
		texteListe = texteListe + "Retour � l'entrepot - " + tournee.getListeHoraire().get(i).getHeureFin().toString() + "\n";
		labelListeLivraison.setText(texteListe);
		
		setVisible(true);
	}
	
	public void setModeValiderTournee (Plan plan, DemandeLivraison demandeLivraisons, Tournee tournee)
	{
		setVisible(false);

		// 1.2.1.2.2 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel
		bottomButtonsPanel.removeAll();
		
		// 1.2.1.2.2.1 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel/Buttons
		bottomButtonsPanel.add(new JLabel());
		bottomButtonsPanel.add(new JLabel());
		bottomButtonsPanel.add(new JLabel());
		
		// 1.2.1.2.2.2 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel/labelBonneTournee		
		bottomButtonsPanel.add(new JLabel("Bonne Tournee !"));
		
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