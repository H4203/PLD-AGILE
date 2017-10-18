package vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
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

public class Fenetre extends JFrame
{
	private VueGraphique vueGraphique;
	private JButton jButtonChargement;
	private JButton jButtonValider;
	private JButton jButtonCalculTournee;
	private JPanel jPanelBienvenue;
	private EcouteurDeBoutons ecouteurDeBoutons;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	public Fenetre (Controleur controleur)
	{
		super();
		ecouteurDeBoutons = new EcouteurDeBoutons ( controleur );
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		jPanelBienvenue = new JPanel();
		jPanelBienvenue.setBounds(400, screenSize.height-190, 500, 500);
		JLabel jLabelBienvenue = new JLabel("~LIfvraison~");
		jLabelBienvenue.setFont(new Font("Serif", Font.PLAIN, 30));
		jPanelBienvenue.add(jLabelBienvenue);
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
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(screenSize.width, screenSize.height);
		
		setVisible(true);
		this.setLayout(null);
		setModeAccueil();

		//this.vueGraphique = new VueGraphique(plan, this);
	}
	public void setModeAccueil ()
	{
		getContentPane().removeAll();
		//getContentPane().setLayout((LayoutManager) new FlowLayout(FlowLayout.RIGHT));
		jButtonChargement = new JButton ( "Charger Plan" );
		jButtonChargement.setBounds(screenSize.width-200,screenSize.height-200,150,50);
		//getContentPane().add( jButtonChargement, BorderLayout.SOUTH );
		getContentPane().add( jButtonChargement );
		getContentPane().add( jPanelBienvenue );
		
		JPanel imagePanel = new JPanel();
		JLabel imageLabel = new JLabel(new ImageIcon("ihm\\image_livreur.jpg"));
		imagePanel.setBounds(0,0,screenSize.width,screenSize.height);
		imagePanel.add(imageLabel);
		getContentPane().add(imagePanel);
		
		jButtonChargement.addActionListener( ecouteurDeBoutons );
		
		setVisible(true);
	}
	
	public void setModePlan ( Plan plan )
	{
		
		setVisible(false);
		getContentPane().removeAll();
		MapPanel mapPanel = new MapPanel(plan, null, null);
		getContentPane().add(mapPanel);
		jButtonChargement = new JButton ( "Charger Livraison" );
		jButtonChargement.setBounds(screenSize.width-200,screenSize.height-200,150,50);
		this.add( jButtonChargement );
		
		jButtonChargement.addActionListener( ecouteurDeBoutons );
		getContentPane().add( jPanelBienvenue );
		setVisible(true);
	}

	public void setModeDemandeLivraison (Plan plan, DemandeLivraison demandeLivraisons)
	{
		setVisible(false);
		getContentPane().removeAll();

		MapPanel mapPanel = new MapPanel(plan, demandeLivraisons);
		getContentPane().add(mapPanel);
		
		jButtonCalculTournee = new JButton ( "Calculer Tournee" );
		jButtonCalculTournee.setBounds(screenSize.width-200,screenSize.height-200,150,50);

		getContentPane().add( jButtonCalculTournee);
		getContentPane().add( jPanelBienvenue );
		jButtonCalculTournee.addActionListener( ecouteurDeBoutons );
		
		setVisible(true);
	}
	
	public void setModeTournee (Plan plan, DemandeLivraison demandeLivraisons, Tournee tournee)
	{
		setVisible(false);
		getContentPane().removeAll();

		MapPanel mapPanel = new MapPanel(plan, demandeLivraisons, tournee);
		getContentPane().add(mapPanel);
		
		jButtonValider = new JButton ( "Valider Tournee" );
		jButtonValider.setBounds(screenSize.width-200,screenSize.height-200,150,50);

		getContentPane().add( jButtonValider);
		getContentPane().add( jPanelBienvenue );
		jButtonValider.addActionListener( ecouteurDeBoutons );
		
		setVisible(true);
	}
	
	public void setModeValiderTournee (Plan plan, DemandeLivraison demandeLivraisons, Tournee tournee)
	{
		setVisible(false);
		getContentPane().removeAll();

		MapPanel mapPanel = new MapPanel(plan, demandeLivraisons, tournee);
		getContentPane().add(mapPanel);
		
		JPanel jPanelDeLaFin = new JPanel ();
		JLabel jLabelBonneChance = new JLabel("Bonne Tournee ( ･ω･)ﾉ!!");
		jPanelDeLaFin.add(jLabelBonneChance);
		jPanelDeLaFin.setBounds(screenSize.width-200,screenSize.height-200,150,50);

		getContentPane().add( jPanelDeLaFin);
		getContentPane().add( jPanelBienvenue );
		jButtonValider.addActionListener( ecouteurDeBoutons );
		
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