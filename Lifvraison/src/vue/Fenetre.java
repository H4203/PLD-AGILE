package vue;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import algorithme.CalculateurTournee;
import controlleur.Controleur;
import donnees.XMLParseur;
import modeles.DemandeLivraison;
import modeles.Plan;
import modeles.Tournee;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class Fenetre extends JFrame
{
	private VueGraphique vueGraphique;
	
	private JPanel mainPanel;
	private JPanel leftPanel;
	private JPanel overMapPanel;
	private JPanel titlePanel;
	private JPanel rightPanel;
	private JPanel buttonsPanel;
	private JPanel topButtonsPanel;
	private JPanel bottomButtonsPanel;
	
	private EcouteurDeBoutons ecouteurDeBoutons;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	public Fenetre (Controleur controleur)
	{
		super();	
		
		ecouteurDeBoutons = new EcouteurDeBoutons ( controleur );
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		leftPanel = new JPanel();
		leftPanel.setLayout(new BorderLayout());
		
		rightPanel = new JPanel();
		rightPanel.setLayout(new CardLayout(50, 50));
		rightPanel.setPreferredSize(new Dimension(screenSize.width / 5, screenSize.height));
		
		overMapPanel = new JPanel();
		overMapPanel.setLayout(new CardLayout(50, 50));
		
		buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(2, 0, 20, 20));
		
		topButtonsPanel = new JPanel();
		topButtonsPanel.setLayout(new GridLayout(4, 0, 20, 20));
		
		bottomButtonsPanel = new JPanel();
		bottomButtonsPanel.setLayout(new GridLayout(4, 0, 20, 20));
		
		titlePanel = new JPanel();
		titlePanel.setLayout(new BorderLayout());
		titlePanel.setPreferredSize(new Dimension(screenSize.width, screenSize.height / 10));
		
		JLabel labelBienvenue = new JLabel("~LIfvraison~", SwingConstants.CENTER);
		labelBienvenue.setFont(new Font("Serif", Font.PLAIN, 30));
		
		buttonsPanel.add(topButtonsPanel);
		buttonsPanel.add(bottomButtonsPanel);
		
		titlePanel.add(labelBienvenue, BorderLayout.CENTER);
		
		rightPanel.add(buttonsPanel);
		
		getContentPane().add(mainPanel);
		
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
		mainPanel.removeAll();
		
		JButton buttonChargement = new JButton ( "Charger Plan" );
		buttonChargement.addActionListener(ecouteurDeBoutons);

		JLabel imageLabel = new JLabel(new ImageIcon("ihm\\image_livreur.jpg"));
		
		bottomButtonsPanel.add(new JLabel());
		bottomButtonsPanel.add(new JLabel());
		bottomButtonsPanel.add(new JLabel());
		bottomButtonsPanel.add(buttonChargement);
		
		leftPanel.add(imageLabel, BorderLayout.CENTER);
		leftPanel.add(titlePanel, BorderLayout.SOUTH);
		
		mainPanel.add(leftPanel, BorderLayout.CENTER);
		mainPanel.add(rightPanel, BorderLayout.EAST);
		
		setVisible(true);
	}
	
	public void setModePlan ( Plan plan )
	{
		setVisible(false);
		
		leftPanel.removeAll();
		overMapPanel.removeAll();
		bottomButtonsPanel.removeAll();
		
		MapPanel mapPanel = new MapPanel(plan, null, null);
		
		JButton buttonChargement = new JButton ( "Charger Livraison" );
		buttonChargement.addActionListener( ecouteurDeBoutons );

		bottomButtonsPanel.add(new JLabel());
		bottomButtonsPanel.add(new JLabel());
		bottomButtonsPanel.add(new JLabel());
		bottomButtonsPanel.add(buttonChargement);
		
		overMapPanel.add(mapPanel);
		
		leftPanel.add(overMapPanel, BorderLayout.CENTER);
		leftPanel.add(titlePanel, BorderLayout.SOUTH);
		
		setVisible(true);
	}

	public void setModeDemandeLivraison (Plan plan, DemandeLivraison demandeLivraisons)
	{
		setVisible(false);
		
		leftPanel.removeAll();
		overMapPanel.removeAll();
		bottomButtonsPanel.removeAll();

		MapPanel mapPanel = new MapPanel(plan, demandeLivraisons);
		
		JButton buttonCalculTournee = new JButton ( "Calculer Tournee" );
		buttonCalculTournee.addActionListener( ecouteurDeBoutons );

		bottomButtonsPanel.add(new JLabel());
		bottomButtonsPanel.add(new JLabel());
		bottomButtonsPanel.add(new JLabel());
		bottomButtonsPanel.add(buttonCalculTournee);
		
		overMapPanel.add(mapPanel);
		
		leftPanel.add(overMapPanel, BorderLayout.CENTER);
		leftPanel.add(titlePanel, BorderLayout.SOUTH);
		
		setVisible(true);
	}
	
	public void setModeTournee (Plan plan, DemandeLivraison demandeLivraisons, Tournee tournee)
	{
		setVisible(false);
		
		leftPanel.removeAll();
		overMapPanel.removeAll();
		bottomButtonsPanel.removeAll();

		MapPanel mapPanel = new MapPanel(plan, demandeLivraisons, tournee);
		
		JButton buttonValider = new JButton ( "Valider Tournee" );
		buttonValider.addActionListener( ecouteurDeBoutons );

		bottomButtonsPanel.add(new JLabel());
		bottomButtonsPanel.add(new JLabel());
		bottomButtonsPanel.add(new JLabel());
		bottomButtonsPanel.add(buttonValider);
		
		overMapPanel.add(mapPanel);
		
		leftPanel.add(overMapPanel, BorderLayout.CENTER);
		leftPanel.add(titlePanel, BorderLayout.SOUTH);
		
		setVisible(true);
	}
	
	public void setModeValiderTournee (Plan plan, DemandeLivraison demandeLivraisons, Tournee tournee)
	{
		setVisible(false);
		
		leftPanel.removeAll();
		overMapPanel.removeAll();
		bottomButtonsPanel.removeAll();

		MapPanel mapPanel = new MapPanel(plan, demandeLivraisons, tournee);
		getContentPane().add(mapPanel);
		
		//JPanel jPanelDeLaFin = new JPanel ();
		JLabel labelBonneTournee = new JLabel("Bonne Tournee !");
		//labelBonneTournee.add(jLabelBonneChance);
		//jPanelDeLaFin.setBounds(screenSize.width-200,screenSize.height-200,150,50);

		bottomButtonsPanel.add(new JLabel());
		bottomButtonsPanel.add(new JLabel());
		bottomButtonsPanel.add(new JLabel());
		bottomButtonsPanel.add(labelBonneTournee);
		
		overMapPanel.add(mapPanel);
		
		leftPanel.add(overMapPanel, BorderLayout.CENTER);
		leftPanel.add(titlePanel, BorderLayout.SOUTH);
		
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