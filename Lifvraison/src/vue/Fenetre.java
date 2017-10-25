package vue;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controleur.Controleur;
import modeles.DemandeLivraison;
import modeles.Livraison;
import modeles.PlageHoraire;
import modeles.Plan;
import modeles.Tournee;

import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

public class Fenetre extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	//private VueGraphique vueGraphique;
	
	// 1.1.1 mainPanel/leftPanel/overMapPanel
	private JPanel overMapPanel;
	// 1.1.1.1 mainPanel/leftPanel/overMapPanel/mapPanel
	private MapPanel mapPanel;	
	// 1.2.1.1 mainPanel/overRightPanel/rightPanel/listeLivraisonsPanel
	private JPanel listeLivraisonsPanel;
	// 1.2.1.2.1 mainPanel/overRightPanel/rightPanel/buttonsPanel/topButtonsPanel
	private JPanel topButtonsPanel;	
	// 1.2.1.2.2 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel
	private JPanel bottomButtonsPanel;
	// 1.2.1.2.2.1 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel/buttonPrecedent
	private JButton buttonPrecedent;
	// 1.2.1.2.2.2 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel/buttonSuivant
	private JButton buttonSuivant;
	// 1.3.1 mainPanel/ongletsPanel/buttonAccueil
	private JButton buttonAccueil;
	// 1.3.2 mainPanel/ongletsPanel/buttonChargementPlan
	private JButton buttonChargementPlan;
	// 1.3.3 mainPanel/ongletsPanel/buttonChargementDemandeLivraison
	private JButton buttonChargementDemandeLivraison;
	// 1.3.4 mainPanel/ongletsPanel/buttonCalculTournee
	private JButton buttonCalculTournee;
	// 1.3.5 mainPanel/ongletsPanel/buttonModificationTournee
	private JButton buttonModificationTournee;
	// 1.3.6 mainPanel/ongletsPanel/buttonValidationTournee
	private JButton buttonValidationTournee;
	
	//??
	// 1.2.1.1.1.1 mainPanel/overRightPanel/rightPanel/listeLivraisonsPanel/labelListeLivraison/texteListe
	private JList<String[]> listTexteLivraison;
	
	private JPanel buttonsPanel;
	
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
		
		// 1.1.1.1 mainPanel/leftPanel/overMapPanel/mapPanel
		mapPanel = new MapPanel(null, null, null);
		
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
		rightPanel.setLayout(new BorderLayout());
		overRightPanel.add(rightPanel);
		
		// 1.2.1.1 mainPanel/overRightPanel/rightPanel/listeLivraisonsPanel
		listeLivraisonsPanel = new JPanel();
		listeLivraisonsPanel.setLayout(new CardLayout(50, 50));
		rightPanel.add(listeLivraisonsPanel, BorderLayout.CENTER);
		
		// 1.2.1.2 mainPanel/overRightPanel/rightPanel/buttonsPanel
		buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(2, 0, 20, 20));
		rightPanel.add(buttonsPanel, BorderLayout.SOUTH);
		
		// 1.2.1.2.1 mainPanel/overRightPanel/rightPanel/buttonsPanel/topButtonsPanel
		topButtonsPanel = new JPanel();
		topButtonsPanel.setLayout(new FlowLayout());
		topButtonsPanel.setPreferredSize(new Dimension(screenSize.width / 5, screenSize.height / 20));
		buttonsPanel.add(topButtonsPanel);
		
		// 1.2.1.2.2 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel		
		bottomButtonsPanel = new JPanel();
		bottomButtonsPanel.setLayout(new GridLayout(0, 2, 20, 20));
		bottomButtonsPanel.setPreferredSize(new Dimension(screenSize.width / 5, screenSize.height / 20));
		buttonsPanel.add(bottomButtonsPanel);
		
		// 1.2.1.2.2.1 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel/buttonPrecedent
		buttonPrecedent = new JButton("Precedent");
		buttonPrecedent.addActionListener(ecouteurDeBoutons);
		bottomButtonsPanel.add(buttonPrecedent);
		
		// 1.2.1.2.2.2 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel/buttonSuivant
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
		
		// 1.3.3 mainPanel/ongletsPanel/buttonChargementDemandeLivraison
		buttonChargementDemandeLivraison = new JButton("Chargement Demande Livraison");
		buttonChargementDemandeLivraison.addActionListener(ecouteurDeBoutons);
		buttonChargementDemandeLivraison.setEnabled(false);
		ongletsPanel.add(buttonChargementDemandeLivraison);
		
		// 1.3.4 mainPanel/ongletsPanel/buttonCalculTournee
		buttonCalculTournee = new JButton("Calcul Tournee");
		buttonCalculTournee.addActionListener(ecouteurDeBoutons);
		buttonCalculTournee.setEnabled(false);
		ongletsPanel.add(buttonCalculTournee);
		
		// 1.3.5 mainPanel/ongletsPanel/buttonModificationTournee
		buttonModificationTournee = new JButton("Modification Tournee");
		buttonModificationTournee.addActionListener(ecouteurDeBoutons);
		buttonModificationTournee.setEnabled(false);
		ongletsPanel.add(buttonModificationTournee);
		
		// 1.3.6 mainPanel/ongletsPanel/buttonValidationTournee
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

		repaint();
		setVisible(true);

		//this.vueGraphique = new VueGraphique(plan, this);
	}
	
	public void setModeAccueil()
	{		
		// 1.1.1 mainPanel/leftPanel/overMapPanel		
		overMapPanel.removeAll();
		
		// 1.1.1.1 mainPanel/leftPanel/overMapPanel/imageLabel
		JLabel imageLabel = new JLabel(new ImageIcon("ihm\\image_livreur.jpg"));
		overMapPanel.add(imageLabel, BorderLayout.CENTER);
		
		// 1.2.1.1 mainPanel/overRightPanel/rightPanel/listeLivraisonsPanel
		listeLivraisonsPanel.removeAll();
		
		// 1.2.1.2.1 mainPanel/overRightPanel/rightPanel/buttonsPanel/topButtonsPanel
		topButtonsPanel.removeAll();
		
		// 1.3.x mainPanel/ongletsPanel/buttons		
		resetOngletsPanelButtons();
		// 1.3.1 mainPanel/ongletsPanel/buttonAccueil
		buttonAccueil.setBackground(new Color(200, 200, 255));
			
		repaint();
		setVisible(true);
	}
	
	private JButton affichageChargementPlan()
	{
		// 1.1.1 mainPanel/leftPanel/overMapPanel
		overMapPanel.removeAll();
		
		// 1.1.1.1 mainPanel/leftPanel/overMapPanel/mapPanel
		mapPanel.setAffichagePlan(true);
		mapPanel.setAffichageDemandeLivraison(false);
		mapPanel.setAffichageTournee(false);
		overMapPanel.add(mapPanel);
		mapPanel.repaint();
		
		// 1.2.1.1 mainPanel/overRightPanel/rightPanel/listeLivraisonsPanel
		listeLivraisonsPanel.removeAll();
		
		// 1.2.1.2.1 mainPanel/overRightPanel/rightPanel/buttonsPanel/topButtonsPanel
		topButtonsPanel.removeAll();

		// 1.2.1.2.2.1 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel/buttonChargerPlan
		JButton buttonChargerPlan = new JButton("Charger Plan");
		buttonChargerPlan.addActionListener(ecouteurDeBoutons);
		topButtonsPanel.add(buttonChargerPlan);
		
		// 1.3.x mainPanel/ongletsPanel/buttons		
		resetOngletsPanelButtons();
		// 1.3.1 mainPanel/ongletsPanel/buttonAccueil
		buttonAccueil.setEnabled(true);
		// 1.3.2 mainPanel/ongletsPanel/buttonChargementPlan
		buttonChargementPlan.setBackground(new Color(200, 200, 255));
		
		repaint();
		setVisible(true);
		
		return buttonChargerPlan;
	}
	
	public void setModeChargementPlan()
	{
		JButton buttonChargerPlan = affichageChargementPlan();
		
		ecouteurDeBoutons.actionPerformed(new ActionEvent(buttonChargerPlan, ActionEvent.ACTION_PERFORMED, "Charger Plan", System.currentTimeMillis(), 0));
	}
	
	public void setModeChargementPlan(Plan plan)
	{
		mapPanel.setPlan(plan);
				
		affichageChargementPlan();
	}

	private JButton affichageChargementDemandeLivraison()
	{
		// 1.1.1.1 mainPanel/leftPanel/overMapPanel/mapPanel
		mapPanel.setAffichagePlan(true);
		mapPanel.setAffichageDemandeLivraison(true);
		mapPanel.setAffichageTournee(false);
		mapPanel.repaint();

		// 1.2.1.1 mainPanel/overRightPanel/rightPanel/listeLivraisonsPanel
		listeLivraisonsPanel.removeAll();
		
		// 1.2.1.2.1 mainPanel/overRightPanel/rightPanel/buttonsPanel/topButtonsPanel
		topButtonsPanel.removeAll();
		
		// 1.2.1.2.2.1 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel/buttonChargerDemandeLivraison
		JButton buttonChargerDemandeLivraison = new JButton("Charger Demande Livraison");
		buttonChargerDemandeLivraison.addActionListener(ecouteurDeBoutons);
		topButtonsPanel.add(buttonChargerDemandeLivraison);
		
		// 1.3.x mainPanel/ongletsPanel/buttons		
		resetOngletsPanelButtons();
		// 1.3.1 mainPanel/ongletsPanel/buttonAccueil
		buttonAccueil.setEnabled(true);
		// 1.3.2 mainPanel/ongletsPanel/buttonChargementPlan
		buttonChargementPlan.setEnabled(true);
		// 1.3.3 mainPanel/ongletsPanel/buttonChargementDemandeLivraison
		buttonChargementDemandeLivraison.setBackground(new Color(200, 200, 255));
		
		repaint();
		setVisible(true);
				
		return buttonChargerDemandeLivraison;
	}
	
	public void setModeChargementDemandeLivraison()
	{
		JButton buttonChargerDemandeLivraison = affichageChargementDemandeLivraison();
		
		ecouteurDeBoutons.actionPerformed(new ActionEvent(buttonChargerDemandeLivraison, ActionEvent.ACTION_PERFORMED, "Charger Demande Livraison", System.currentTimeMillis(), 0));
	}
	
	public void setModeChargementDemandeLivraison(DemandeLivraison demandeLivraison)
	{
		mapPanel.setDemandeLivraison(demandeLivraison);
		
		affichageChargementDemandeLivraison();
	}
	
	private JButton affichageCalculTournee()
	{
		// 1.1.1.1 mainPanel/leftPanel/overMapPanel/mapPanel
		mapPanel.setAffichagePlan(true);
		mapPanel.setAffichageDemandeLivraison(true);
		mapPanel.setAffichageTournee(true);
		mapPanel.repaint();
		
		// 1.2.1.1 mainPanel/overRightPanel/rightPanel/listeLivraisonsPanel
		listeLivraisonsPanel.removeAll();
		
		// 1.2.1.2.1 mainPanel/overRightPanel/rightPanel/buttonsPanel/topButtonsPanel
		topButtonsPanel.removeAll();
		
		// 1.2.1.2.2.1 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel/buttonCalculerTournee
		JButton buttonCalculerTournee = new JButton("Calculer Tournee");
		buttonCalculerTournee.addActionListener(ecouteurDeBoutons);
		topButtonsPanel.add(buttonCalculerTournee);
		
		// 1.3.x mainPanel/ongletsPanel/buttons		
		resetOngletsPanelButtons();
		// 1.3.1 mainPanel/ongletsPanel/buttonAccueil
		buttonAccueil.setEnabled(true);
		// 1.3.2 mainPanel/ongletsPanel/buttonChargementPlan
		buttonChargementPlan.setEnabled(true);
		// 1.3.3 mainPanel/ongletsPanel/buttonChargementDemandeLivraison
		buttonChargementDemandeLivraison.setEnabled(true);
		// 1.3.4 mainPanel/ongletsPanel/buttonCalculTournee
		buttonCalculTournee.setBackground(new Color(200, 200, 255));
		
		repaint();
		setVisible(true);
		
		return buttonCalculerTournee;
	}
	
	public void setModeCalculTournee()
	{
		JButton buttonCalculerTournee = affichageCalculTournee();
		
		ecouteurDeBoutons.actionPerformed(new ActionEvent(buttonCalculerTournee, ActionEvent.ACTION_PERFORMED, "Calculer Tournee", System.currentTimeMillis(), 0));
	}
	
	public void setModeCalculTournee(Tournee tournee)
	{
		mapPanel.setTournee(tournee);
		
		affichageCalculTournee();
	}
	
	public void setModeModificationTournee(Tournee tournee)
	{
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
		for(PlageHoraire plgrhoraire : tournee.getListeHoraire())
		{
			if(i < tournee.getLivraisonsOrdonnees().size()-1)
			{
				i = i + 1;
				texteListe = texteListe + i + " - de " + tournee.getListeHoraire().get(i).getHeureDebut().toString() 
						+ " a " + tournee.getListeHoraire().get(i).getHeureFin().toString() + "\n";
			}
		}
		texteListe = texteListe + "Retour a l'entrepot - " + tournee.getListeHoraire().get(++i).getHeureFin().toString() + "\n";
		labelListeLivraison.setText(texteListe);
		
		/*listTexteLivraison.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		listTexteLivraison.setLayoutOrientation(JList.VERTICAL);
		listTexteLivraison.setVisibleRowCount(-1); //show max number of item
		JScrollPane listScroller = new JScrollPane(listTexteLivraison);
		listScroller.setPreferredSize(new Dimension(250, 80));
		*/
		
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
		
		// 1.2.1.2.2.1 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel/buttonValiderTournee
		JButton buttonValiderTournee = new JButton("Valider Tournee");
		buttonValiderTournee.addActionListener(ecouteurDeBoutons);
		topButtonsPanel.add(buttonValiderTournee);
		
		// 1.3.x mainPanel/ongletsPanel/buttons		
		resetOngletsPanelButtons();
		// 1.3.1 mainPanel/ongletsPanel/buttonAccueil
		buttonAccueil.setEnabled(true);
		// 1.3.2 mainPanel/ongletsPanel/buttonChargementPlan
		buttonChargementPlan.setEnabled(true);
		// 1.3.3 mainPanel/ongletsPanel/buttonChargementDemandeLivraison
		buttonChargementDemandeLivraison.setEnabled(true);
		// 1.3.4 mainPanel/ongletsPanel/buttonCalculTournee
		buttonCalculTournee.setEnabled(true);
		// 1.3.5 mainPanel/ongletsPanel/buttonModificationTournee
		buttonModificationTournee.setBackground(new Color(200, 200, 255));
		
		repaint();
		setVisible(true);
	}
	
	public void setModeValidationTournee()
	{
		// 1.2.1.2.1 mainPanel/overRightPanel/rightPanel/buttonsPanel/topButtonsPanel
		topButtonsPanel.removeAll();

		// 1.3.x mainPanel/ongletsPanel/buttons		
		resetOngletsPanelButtons();
		// 1.3.1 mainPanel/ongletsPanel/buttonAccueil
		buttonAccueil.setEnabled(true);
		// 1.3.2 mainPanel/ongletsPanel/buttonChargementPlan
		buttonChargementPlan.setEnabled(true);
		// 1.3.3 mainPanel/ongletsPanel/buttonChargementDemandeLivraison
		buttonChargementDemandeLivraison.setEnabled(true);
		// 1.3.4 mainPanel/ongletsPanel/buttonCalculTournee
		buttonCalculTournee.setEnabled(true);
		// 1.3.5 mainPanel/ongletsPanel/buttonModificationTournee
		buttonModificationTournee.setEnabled(true);
		// 1.3.6 mainPanel/ongletsPanel/buttonValidationTournee
		buttonValidationTournee.setBackground(new Color(200, 200, 255));

		repaint();
		setVisible(true);
	}
	
	public void resetOngletsPanelButtons()
	{
		// 1.3.1 mainPanel/ongletsPanel/buttonAccueil
		buttonAccueil.setEnabled(false);
		buttonAccueil.setBackground(null);
		// 1.3.2 mainPanel/ongletsPanel/buttonChargementPlan
		buttonChargementPlan.setEnabled(false);
		buttonChargementPlan.setBackground(null);
		// 1.3.3 mainPanel/ongletsPanel/buttonChargementDemandeLivraison
		buttonChargementDemandeLivraison.setEnabled(false);
		buttonChargementDemandeLivraison.setBackground(null);
		// 1.3.4 mainPanel/ongletsPanel/buttonCalculTournee
		buttonCalculTournee.setEnabled(false);
		buttonCalculTournee.setBackground(null);
		// 1.3.5 mainPanel/ongletsPanel/buttonModificationTournee
		buttonModificationTournee.setEnabled(false);
		buttonModificationTournee.setBackground(null);
		// 1.3.6 mainPanel/ongletsPanel/buttonValidationTournee
		buttonValidationTournee.setEnabled(false);
		buttonValidationTournee.setBackground(null);
	}
}