package vue;

import java.awt.BorderLayout;
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
	private JPanel titlePanel;
	private JPanel rightPanel;
	
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
		rightPanel.setLayout(new GridLayout(5, 0, 20, 20));
		rightPanel.setPreferredSize(new Dimension(200, screenSize.height));
		
		titlePanel = new JPanel();
		titlePanel.setLayout(new BorderLayout());
		titlePanel.setPreferredSize(new Dimension(screenSize.width, screenSize.height / 10));
		
		JLabel labelBienvenue = new JLabel("~LIfvraison~", SwingConstants.CENTER);
		labelBienvenue.setFont(new Font("Serif", Font.PLAIN, 30));
		
		titlePanel.add(labelBienvenue, BorderLayout.CENTER);
		
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
		
		rightPanel.add(buttonChargement);
		
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
		rightPanel.removeAll();
		
		MapPanel mapPanel = new MapPanel(plan, null, null);
		
		JButton buttonChargement = new JButton ( "Charger Livraison" );
		buttonChargement.addActionListener( ecouteurDeBoutons );

		rightPanel.add(buttonChargement);
		
		leftPanel.add(mapPanel, BorderLayout.CENTER);
		leftPanel.add(titlePanel, BorderLayout.SOUTH);
		
		setVisible(true);
	}

	public void setModeDemandeLivraison (Plan plan, DemandeLivraison demandeLivraisons)
	{
		setVisible(false);
		
		leftPanel.removeAll();
		rightPanel.removeAll();

		MapPanel mapPanel = new MapPanel(plan, demandeLivraisons);
		
		JButton buttonCalculTournee = new JButton ( "Calculer Tournee" );
		buttonCalculTournee.addActionListener( ecouteurDeBoutons );

		rightPanel.add(buttonCalculTournee);
		
		leftPanel.add(mapPanel, BorderLayout.CENTER);
		leftPanel.add(titlePanel, BorderLayout.SOUTH);
		
		setVisible(true);
	}
	
	public void setModeTournee (Plan plan, DemandeLivraison demandeLivraisons, Tournee tournee)
	{
		setVisible(false);
		
		leftPanel.removeAll();
		rightPanel.removeAll();

		MapPanel mapPanel = new MapPanel(plan, demandeLivraisons, tournee);
		
		JButton buttonValider = new JButton ( "Valider Tournee" );
		buttonValider.addActionListener( ecouteurDeBoutons );

		rightPanel.add(buttonValider);
		
		leftPanel.add(mapPanel, BorderLayout.CENTER);
		leftPanel.add(titlePanel, BorderLayout.SOUTH);
		
		setVisible(true);
	}
	
	public void setModeValiderTournee (Plan plan, DemandeLivraison demandeLivraisons, Tournee tournee)
	{
		setVisible(false);
		
		leftPanel.removeAll();
		rightPanel.removeAll();

		MapPanel mapPanel = new MapPanel(plan, demandeLivraisons, tournee);
		getContentPane().add(mapPanel);
		
		//JPanel jPanelDeLaFin = new JPanel ();
		JLabel labelBonneTournee = new JLabel("Bonne Tournee !");
		//labelBonneTournee.add(jLabelBonneChance);
		//jPanelDeLaFin.setBounds(screenSize.width-200,screenSize.height-200,150,50);
		
		rightPanel.add(labelBonneTournee);
		
		leftPanel.add(mapPanel, BorderLayout.CENTER);
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