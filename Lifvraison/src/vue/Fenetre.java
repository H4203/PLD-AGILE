package vue;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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

public class Fenetre extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	// 1.1.1 mainPanel/leftPanel/vueGraphique
	private VueGraphique vueGraphique;
	
	private VueTextuelle vueTextuelle;
	// 1.1.1 mainPanel/leftPanel/overMapPanel
	//private JPanel overMapPanel;
	// 1.1.1.1 mainPanel/leftPanel/overMapPanel/mapPanel
	//private MapPanel mapPanel;	
	// 1.2.1.1 mainPanel/overRightPanel/rightPanel/listeLivraisonsPanel
	private JPanel listeLivraisonsPanel;
	// 1.2.1.2.1 mainPanel/overRightPanel/rightPanel/buttonsPanel/topButtonsPanel
	private JPanel actionButtonsPanel;	
	// 1.3.1 mainPanel/overButtonsPanel/stateButtonsPanel
	private JPanel stateButtonsPanel;

	// 1.2.1.2.1.1 mainPanel/overRightPanel/rightPanel/buttonsPanel/topButtonsPanel/buttonAjouterLivraison	
	JButton buttonAjouterLivraison;
	// 1.2.1.2.1.2 mainPanel/overRightPanel/rightPanel/buttonsPanel/topButtonsPanel/buttonSupprimerLivraison
	JButton buttonSupprimerLivraison;
	// 1.2.1.2.1.3 mainPanel/overRightPanel/rightPanel/buttonsPanel/topButtonsPanel/buttonEchangerLivraisons
	JButton buttonEchangerLivraisons;
	// 1.2.1.2.1.4 mainPanel/overRightPanel/rightPanel/buttonsPanel/topButtonsPanel/buttonUndo
	JButton buttonUndo;
	// 1.2.1.2.1.5 mainPanel/overRightPanel/rightPanel/buttonsPanel/topButtonsPanel/buttonRedo
	JButton buttonRedo;
	
	/*// 1.2.1.2.2.1 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel/buttonPrecedent
	private JButton buttonPrecedent;
	// 1.2.1.2.2.2 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel/buttonSuivant
	private JButton buttonSuivant;*/
	/*// 1.3.1 mainPanel/ongletsPanel/buttonAccueil
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
	private JButton buttonValidationTournee;*/
	
	// 1.2.1.2.2.1 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel/buttonChargerPlan
	private JButton buttonChargerPlan;
	// 1.2.1.2.2.2 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel/buttonChargerDemandeLivraison
	private JButton buttonChargerDemandeLivraison;
	// 1.2.1.2.2.3 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel/buttonCalculerTournee
	private JButton buttonCalulerTournee;
	// 1.2.1.2.2.4 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel/buttonValiderTournee
	private JButton buttonValiderTournee;
	// 1.2.1.2.2.5 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel/buttonGenererFeuilleDeRoute
	private JButton buttonGenererFeuilleDeRoute;	
	
	// 1.2.1.1.1.1 mainPanel/overRightPanel/rightPanel/listeLivraisonsPanel/labelListeLivraison/texteListe
	//private JList<String> listTexteLivraison;
	
	//private JPanel buttonsPanel;
	
	private JPanel barreChargementPanel;
	private JLabel chargementLabel;
	
	private EcouteurDeBoutons ecouteurDeBoutons;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	public Fenetre(Controleur controleur, Plan plan, DemandeLivraison demandeLivraison, Tournee tournee)
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
		
		// 1.1.1 mainPanel/leftPanel/vueGraphique
		vueGraphique = new VueGraphique(this, plan, demandeLivraison, tournee, controleur);
		leftPanel.add(vueGraphique, BorderLayout.CENTER);
		
		// 1.1.1 mainPanel/leftPanel/overMapPanel
		//overMapPanel = new JPanel();
		//overMapPanel.setLayout(new CardLayout(50, 50));
		//leftPanel.add(overMapPanel, BorderLayout.CENTER);
		
		// 1.1.1.1 mainPanel/leftPanel/overMapPanel/mapPanel
		//mapPanel = new MapPanel(null, null, null);
		
		// 1.1.2 mainPanel/leftPanel/titlePanel
		barreChargementPanel = new JPanel();
		barreChargementPanel.setLayout(new BorderLayout());
		//titlePanel.setPreferredSize(new Dimension(screenSize.width, screenSize.height / 10));
		leftPanel.add(barreChargementPanel, BorderLayout.SOUTH);
		
		// 1.1.2.1 mainPanel/leftPanel/titlePanel/titleLabel
		chargementLabel = new JLabel("~LIfvraison~", SwingConstants.LEFT);
		chargementLabel.setFont(new Font("Serif", Font.PLAIN, 15));
		barreChargementPanel.add(chargementLabel, BorderLayout.CENTER);
		
		// 1.2 mainPanel/overRightPanel
		JPanel overRightPanel = new JPanel();
		overRightPanel.setLayout(new CardLayout(50, 50));
		//overRightPanel.setPreferredSize(new Dimension(screenSize.width / 3, screenSize.height));
		mainPanel.add(overRightPanel, BorderLayout.EAST);
		
		// 1.2.1 mainPanel/overRightPanel/rightPanel
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BorderLayout());
		overRightPanel.add(rightPanel);
		
		// 1.1.1 mainPanel/leftPanel/vueGraphique
		vueTextuelle = new VueTextuelle(this, demandeLivraison, tournee, controleur);
		rightPanel.add(vueTextuelle, BorderLayout.CENTER);
		
		addComponentListener(new EcouteurDeFenetre(vueGraphique, vueTextuelle));

		// 1.2.1.2 mainPanel/overRightPanel/rightPanel/buttonsPanel
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(2, 0, 20, 20));
		rightPanel.add(buttonsPanel, BorderLayout.SOUTH);
		
		// 1.2.1.2.1 mainPanel/overRightPanel/rightPanel/buttonsPanel/topButtonsPanel
		actionButtonsPanel = new JPanel();
		actionButtonsPanel.setLayout(new GridLayout(2, 3, 20, 20));
		//topButtonsPanel.setPreferredSize(new Dimension(screenSize.width / 5, screenSize.height / 20));
		buttonsPanel.add(actionButtonsPanel);
		
		// 1.2.1.2.1.1 mainPanel/overRightPanel/rightPanel/buttonsPanel/topButtonsPanel/buttonAjouterLivraison
		buttonAjouterLivraison = new JButton("Ajouter une Livraison");
		buttonAjouterLivraison.addActionListener(ecouteurDeBoutons);
		actionButtonsPanel.add(buttonAjouterLivraison);
		// 1.2.1.2.1.2 mainPanel/overRightPanel/rightPanel/buttonsPanel/topButtonsPanel/buttonSupprimerLivraison
		buttonSupprimerLivraison = new JButton("Retirer une Livraison");
		buttonSupprimerLivraison.addActionListener(ecouteurDeBoutons);
		actionButtonsPanel.add(buttonSupprimerLivraison);
		// 1.2.1.2.1.3 mainPanel/overRightPanel/rightPanel/buttonsPanel/topButtonsPanel/buttonEchangerLivraisons
		buttonEchangerLivraisons = new JButton("Echanger 2 Livraisons");
		buttonEchangerLivraisons.addActionListener(ecouteurDeBoutons);
		actionButtonsPanel.add(buttonEchangerLivraisons);
		// 1.2.1.2.1.4 mainPanel/overRightPanel/rightPanel/buttonsPanel/topButtonsPanel/buttonUndo
		buttonUndo = new JButton("Annuler");
		buttonUndo.addActionListener(ecouteurDeBoutons);
		actionButtonsPanel.add(buttonUndo);
		// 1.2.1.2.1.5 mainPanel/overRightPanel/rightPanel/buttonsPanel/topButtonsPanel/buttonRedo
		buttonRedo = new JButton("Retablir");
		buttonRedo.addActionListener(ecouteurDeBoutons);
		actionButtonsPanel.add(buttonRedo);

		// 1.3 mainPanel/overBottomButtonsPanel
		JPanel overBottomButtonsPanel = new JPanel();
		overBottomButtonsPanel.setLayout(new CardLayout(50,50));
		mainPanel.add(overBottomButtonsPanel, BorderLayout.SOUTH);
		
		// 1.3.1 mainPanel/overBottomButtonsPanel/stateButtonsPanel		
		stateButtonsPanel = new JPanel();
		stateButtonsPanel.setLayout(new GridLayout(1, 5, 20, 20));
		//bottomButtonsPanel.setPreferredSize(new Dimension(screenSize.width / 5, screenSize.height / 20));
		overBottomButtonsPanel.add(stateButtonsPanel, BorderLayout.SOUTH);

		
		// 1.3.1.1 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel/buttonChargerPlan
		buttonChargerPlan = new JButton("Charger Plan");
		buttonChargerPlan.addActionListener(ecouteurDeBoutons);
		stateButtonsPanel.add(buttonChargerPlan);
		// 1.3.1.2 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel/buttonChargerDemandeLivraison
		buttonChargerDemandeLivraison = new JButton("Charger Demande Livraison");
		buttonChargerDemandeLivraison.addActionListener(ecouteurDeBoutons);

		stateButtonsPanel.add(buttonChargerDemandeLivraison);
		// 1.3.1.3 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel/buttonChargerDemandeLivraison
		buttonCalulerTournee = new JButton("Calculer Tournee");
		buttonCalulerTournee .addActionListener(ecouteurDeBoutons);
		stateButtonsPanel.add(buttonCalulerTournee );
		// 1.3.1.4 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel/buttonValiderTournee
		buttonValiderTournee = new JButton("Valider Tournee");
		buttonValiderTournee.addActionListener(ecouteurDeBoutons);
		stateButtonsPanel.add(buttonValiderTournee);
		// 1.3.1.5 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel/buttonGenererFeuilleDeRoute
		buttonGenererFeuilleDeRoute = new JButton("Generer Feuille de Route");
		buttonGenererFeuilleDeRoute.addActionListener(ecouteurDeBoutons);
		stateButtonsPanel.add(buttonGenererFeuilleDeRoute );
		
		/*// 1.2.1.2.2.1 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel/buttonPrecedent
		buttonPrecedent = new JButton("Precedent");
		buttonPrecedent.addActionListener(ecouteurDeBoutons);
		bottomButtonsPanel.add(buttonPrecedent);
		
		// 1.2.1.2.2.2 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel/buttonSuivant
		buttonSuivant = new JButton("Suivant");
		buttonSuivant.addActionListener(ecouteurDeBoutons);
		bottomButtonsPanel.add(buttonSuivant);*/
		
		/*// 1.3 mainPanel/ongletsPanel
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
		ongletsPanel.add(buttonValidationTournee);*/
		
		
		
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
	
	// modification plan non null
	public void chargerPlan(Plan plan)
	{
		vueGraphique.nouveauPlan(plan);
		setModeChargementDemandeLivraison();
		setBarreChargement("Plan chargé avec succès \n Cliquez sur le bouton Charger plan pour choisir le fichier du plan");
	}
	public void chargerDemandeLivraison(DemandeLivraison demandeLivraison)
	{
		vueGraphique.nouvelleDemandeLivraison(demandeLivraison);
		vueTextuelle.nouvelleDemandeLivraison(demandeLivraison);
		setModeCalculTournee();
		setBarreChargement("Demande de livraison chargée avec succès \n Cliquez sur Calculer tournee pour lancer le calcul de la tournee");
	}
	public void chargerTournee(Tournee tournee)
	{
		vueGraphique.nouvelleTournee(tournee);
		vueTextuelle.nouvelleTournee(tournee);
		setModeModificationTournee();
		setBarreChargement("Calcul de la tournée chargée avec succès \n Cliquez sur un des boutons pour modifier votre tournee et cliquez sur Valider tournee pour valider votre tournee");
	}
	
	public void setModeChargementPlan()
	{	
		// 1.1.1 mainPanel/leftPanel/vueGraphique
		vueGraphique.setModePlan();
		vueTextuelle.setModePlan();
		
		// 1.2.1.2.1 mainPanel/overRightPanel/rightPanel/buttonsPanel/topButtonsPanel
		actionButtonsPanel.setVisible(false);

		/*// 1.2.1.2.2.1 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel/buttonChargerPlan
		JButton buttonChargerPlan = new JButton("Charger Plan");
		buttonChargerPlan.addActionListener(ecouteurDeBoutons);
		topButtonsPanel.add(buttonChargerPlan);*/
		
		// 1.2.1.2.2.1 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel/buttonChargerPlan
		buttonChargerPlan.setEnabled(true);
		// 1.2.1.2.2.2 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel/buttonChargerDemandeLivraison
		buttonChargerDemandeLivraison.setEnabled(false);
		// 1.2.1.2.2.3 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel/buttonCalculerTournee
		buttonCalulerTournee.setEnabled(false);
		// 1.2.1.2.2.4 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel/buttonValiderTournee
		buttonValiderTournee.setEnabled(false);
		// 1.2.1.2.2.5 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel/buttonGenererFeuilleDeRoute
		buttonGenererFeuilleDeRoute.setEnabled(false);
		
		// 1.3.x mainPanel/ongletsPanel/buttons		
		//resetOngletsPanelButtons();
		// 1.3.1 mainPanel/ongletsPanel/buttonAccueil
		//buttonAccueil.setEnabled(true);
		// 1.3.2 mainPanel/ongletsPanel/buttonChargementPlan
		//buttonChargementPlan.setBackground(new Color(200, 200, 255));
		setBarreChargement("Cliquez sur le bouton Charger plan pour choisir le fichier du plan");
		
		repaint();
		setVisible(true);
	}
	
	public void setModeChargementDemandeLivraison()
	{
		// 1.1.1 mainPanel/leftPanel/vueGraphique
		vueGraphique.setModeDemandeLivraison();
		vueTextuelle.setModeDemandeLivraison();
		
		// 1.2.1.2.1 mainPanel/overRightPanel/rightPanel/buttonsPanel/topButtonsPanel
		actionButtonsPanel.setVisible(false);
		
		/*// 1.2.1.2.2.1 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel/buttonChargerDemandeLivraison
		JButton buttonChargerDemandeLivraison = new JButton("Charger Demande Livraison");
		buttonChargerDemandeLivraison.addActionListener(ecouteurDeBoutons);
		topButtonsPanel.add(buttonChargerDemandeLivraison);*/
		
		// 1.2.1.2.2.1 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel/buttonChargerPlan
		buttonChargerPlan.setEnabled(true);
		// 1.2.1.2.2.2 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel/buttonChargerDemandeLivraison
		buttonChargerDemandeLivraison.setEnabled(true);
		// 1.2.1.2.2.3 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel/buttonCalculerTournee
		buttonCalulerTournee.setEnabled(false);
		// 1.2.1.2.2.4 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel/buttonValiderTournee
		buttonValiderTournee.setEnabled(false);
		// 1.2.1.2.2.5 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel/buttonGenererFeuilleDeRoute
		buttonGenererFeuilleDeRoute.setEnabled(false);		
		
		// 1.3.x mainPanel/ongletsPanel/buttons		
		//resetOngletsPanelButtons();
		/*// 1.3.1 mainPanel/ongletsPanel/buttonAccueil
		buttonAccueil.setEnabled(true);
		// 1.3.2 mainPanel/ongletsPanel/buttonChargementPlan
		buttonChargementPlan.setEnabled(true);
		// 1.3.3 mainPanel/ongletsPanel/buttonChargementDemandeLivraison
		buttonChargementDemandeLivraison.setBackground(new Color(200, 200, 255));*/
		setBarreChargement("Cliquez sur Chargez demande de livraison pour choisir le fichier de demande de livraison");
		
		repaint();
		setVisible(true);
	}
	
	public void setModeCalculTournee()
	{
		// 1.1.1 mainPanel/leftPanel/vueGraphique
		vueGraphique.setModeTournee();
		// 1.2.1.2.1 mainPanel/overRightPanel/rightPanel/buttonsPanel/topButtonsPanel
		actionButtonsPanel.setVisible(false);

		// 1.2.1.2.2.1 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel/buttonChargerPlan
		buttonChargerPlan.setEnabled(true);
		// 1.2.1.2.2.2 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel/buttonChargerDemandeLivraison
		buttonChargerDemandeLivraison.setEnabled(true);
		// 1.2.1.2.2.3 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel/buttonCalculerTournee
		buttonCalulerTournee.setEnabled(true);
		// 1.2.1.2.2.4 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel/buttonValiderTournee
		buttonValiderTournee.setEnabled(false);
		// 1.2.1.2.2.5 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel/buttonGenererFeuilleDeRoute
		buttonGenererFeuilleDeRoute.setEnabled(false);	
		
		/*// 1.3.x mainPanel/ongletsPanel/buttons		
		//resetOngletsPanelButtons();
		// 1.3.1 mainPanel/ongletsPanel/buttonAccueil
		buttonAccueil.setEnabled(true);
		// 1.3.2 mainPanel/ongletsPanel/buttonChargementPlan
		buttonChargementPlan.setEnabled(true);
		// 1.3.3 mainPanel/ongletsPanel/buttonChargementDemandeLivraison
		buttonChargementDemandeLivraison.setEnabled(true);
		// 1.3.4 mainPanel/ongletsPanel/buttonCalculTournee
		buttonCalculTournee.setBackground(new Color(200, 200, 255));*/
		setBarreChargement("Cliquez sur Calculer tournee pour lancer le calcul de la tournee");
		
		repaint();
		setVisible(true);
	}
	
	public void setModeModificationTournee()
	{
		setModeModificationTournee("");
	}
	
	public void setModeModificationTournee(String etat)
	{
		vueGraphique.setModeTournee();
		vueTextuelle.setModeTournee();
	
		/*// 1.2.1.1.1.1 mainPanel/overRightPanel/rightPanel/listeLivraisonsPanel/labelListeLivraison/texteListe

		String tableauTexteList[] = new String[tournee.getLivraisonsOrdonnees().size()+2]; //+2 pour entrepot depart et arrive
		tableauTexteList[0] = "Depart de l'entrepot a " + tournee.getListeHoraire().get(0).getHeureDebut().toString();
		
		int i = 0;
		for(PlageHoraire plgrhoraire : tournee.getListeHoraire())
		{
			if(i < tournee.getLivraisonsOrdonnees().size()-1)
			{
				i = i + 1;
				tableauTexteList[i] = + i + " - de " + tournee.getListeHoraire().get(i).getHeureDebut().toString() 
						+ " a " + tournee.getListeHoraire().get(i).getHeureFin().toString();
			}
		}

		texteListe = texteListe + "Retour a l'entrepot - " + tournee.getListeHoraire().get(i).getHeureFin().toString() + "\n";
		labelListeLivraison.setText(texteListe);

		i = i + 1;
		tableauTexteList[i] = "Retour a l'entrepot - " + tournee.getListeHoraire().get(i).getHeureFin().toString();
		
		listTexteLivraison = new JList<String>();
		listTexteLivraison.setListData(tableauTexteList);
		listTexteLivraison.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		listTexteLivraison.setLayoutOrientation(JList.VERTICAL);
		listTexteLivraison.setVisibleRowCount(-1); //show max number of item
		JScrollPane listScroller = new JScrollPane(listTexteLivraison);
		listScroller.setPreferredSize(new Dimension(250, 80));
		listeLivraisonsPanel.add(listScroller);*/
		
		// 1.2.1.2.1 mainPanel/overRightPanel/rightPanel/buttonsPanel/topButtonsPanel
		actionButtonsPanel.setVisible(true);
		
		/*// 1.2.1.2.2.1 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel/buttonValiderTournee
		JButton buttonValiderTournee = new JButton("Valider Tournee");
		buttonValiderTournee.addActionListener(ecouteurDeBoutons);
		topButtonsPanel.add(buttonValiderTournee);*/
		
		if (etat.equals("AjoutLivraison") || etat.equals("SuppressionLivraison") || etat.equals("IntervertirLivraisons"))
		{
			buttonAjouterLivraison.setEnabled(false);
			buttonSupprimerLivraison.setEnabled(false);
			buttonEchangerLivraisons.setEnabled(false);
			buttonRedo.setEnabled(false);
		}
		else
		{
			buttonAjouterLivraison.setEnabled(true);
			buttonSupprimerLivraison.setEnabled(true);
			buttonEchangerLivraisons.setEnabled(true);
			buttonRedo.setEnabled(true);
		}
		
		// 1.2.1.2.2.1 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel/buttonChargerPlan
		buttonChargerPlan.setEnabled(true);
		// 1.2.1.2.2.2 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel/buttonChargerDemandeLivraison
		buttonChargerDemandeLivraison.setEnabled(true);
		// 1.2.1.2.2.3 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel/buttonCalculerTournee
		buttonCalulerTournee.setEnabled(true);
		// 1.2.1.2.2.4 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel/buttonValiderTournee
		buttonValiderTournee.setEnabled(true);
		// 1.2.1.2.2.5 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel/buttonGenererFeuilleDeRoute
		buttonGenererFeuilleDeRoute.setEnabled(false);		
		
		// 1.3.x mainPanel/ongletsPanel/buttons		
		//resetOngletsPanelButtons();
		/*// 1.3.1 mainPanel/ongletsPanel/buttonAccueil
		buttonAccueil.setEnabled(true);
		// 1.3.2 mainPanel/ongletsPanel/buttonChargementPlan
		buttonChargementPlan.setEnabled(true);
		// 1.3.3 mainPanel/ongletsPanel/buttonChargementDemandeLivraison
		buttonChargementDemandeLivraison.setEnabled(true);
		// 1.3.4 mainPanel/ongletsPanel/buttonCalculTournee
		buttonCalculTournee.setEnabled(true);
		// 1.3.5 mainPanel/ongletsPanel/buttonModificationTournee
		buttonModificationTournee.setBackground(new Color(200, 200, 255));*/
		
		repaint();
		setVisible(true);
	}
	
	public void setModeGenerationFeuilleDeRoute()
	{
		vueGraphique.setModeTournee();

		
		// 1.2.1.2.1 mainPanel/overRightPanel/rightPanel/buttonsPanel/topButtonsPanel
		actionButtonsPanel.setVisible(false);

		// 1.2.1.2.2.1 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel/buttonChargerPlan
		buttonChargerPlan.setEnabled(true);
		// 1.2.1.2.2.2 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel/buttonChargerDemandeLivraison
		buttonChargerDemandeLivraison.setEnabled(true);
		// 1.2.1.2.2.3 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel/buttonCalculerTournee
		buttonCalulerTournee.setEnabled(true);
		// 1.2.1.2.2.4 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel/buttonValiderTournee
		buttonValiderTournee.setEnabled(true);
		// 1.2.1.2.2.5 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel/buttonGenererFeuilleDeRoute
		buttonGenererFeuilleDeRoute.setEnabled(true);			
		
		// 1.3.x mainPanel/ongletsPanel/buttons		
		//resetOngletsPanelButtons();
		/*// 1.3.1 mainPanel/ongletsPanel/buttonAccueil
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
		buttonValidationTournee.setBackground(new Color(200, 200, 255));*/

		setBarreChargement("Tournee validee\nCliquez sur Generer feuille de route pour enregistrer votre fichier");
		
		repaint();
		setVisible(true);
	}
	
	public void setModeFin()
	{
		vueGraphique.setModeTournee();
		vueTextuelle.setModeTournee();	
		// 1.2.1.2.1 mainPanel/overRightPanel/rightPanel/buttonsPanel/topButtonsPanel
		actionButtonsPanel.setVisible(false);
		
		// 1.2.1.2.2 mainPanel/overRightPanel/rightPanel/buttonsPanel/bottomButtonsPanel
		stateButtonsPanel.removeAll();
		
		repaint();
		setVisible(true);
	}
	
	/*public void resetOngletsPanelButtons()
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
	}*/
	
	public VueGraphique getVueGraphique(){
		return vueGraphique;
	}
	
	public VueTextuelle getVueTextuelle() {
		return vueTextuelle;
	}
	
	public void setBarreChargement(String nouveauTexte)
	{
		chargementLabel.setText(nouveauTexte);
		barreChargementPanel.repaint();
		repaint();
	}
}