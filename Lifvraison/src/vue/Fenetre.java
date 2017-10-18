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
import controleur.Controleur;
import donnees.XMLParseur;
import modeles.DemandeLivraison;
import modeles.Livraison;
import modeles.Plan;
import modeles.Tournee;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Fenetre extends JFrame
{
	private VueGraphique vueGraphique;
	private JButton jButtonChargementPlan;
	private JButton jButtonChargementLivraison;
	private JButton jButtonValider;
	private JButton jButtonAjouterLivraison;
	private JButton jButtonSupprimerLivraison;
	private JButton jButtonEchangerLivraisons;
	private JButton jButtonUndo;
	private JButton jButtonRedo;
	private JButton jButtonRetourAccueil;
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
		jPanelBienvenue.setBounds(screenSize.height/2, screenSize.height-200, 500, 500);
		JLabel jLabelBienvenue = new JLabel("~ LIFvraison ~");
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
		
		jButtonRetourAccueil = new JButton("Accueil");
		jButtonRetourAccueil.setBounds(50,screenSize.height-150,150,50);
		jButtonRetourAccueil.addActionListener( ecouteurDeBoutons );
		
		setVisible(true);
		this.setLayout(null);
		setModeAccueil();

		//this.vueGraphique = new VueGraphique(plan, this);
	}
	public void setModeAccueil ()
	{
		setVisible(false);
		getContentPane().removeAll();
		//getContentPane().setLayout((LayoutManager) new FlowLayout(FlowLayout.RIGHT));
		jButtonChargementPlan = new JButton ( "Charger Plan" );

		jButtonChargementPlan.setBounds(screenSize.width-180,screenSize.height-150,150,50);
		//getContentPane().add( jButtonChargement, BorderLayout.SOUTH );
		getContentPane().add( jButtonChargementPlan );
		getContentPane().add( jPanelBienvenue );
		
		JPanel imagePanel = new JPanel();
		JLabel imageLabel = new JLabel(new ImageIcon("ihm\\image_livreur.jpg"));
		imagePanel.setBounds(0,0,screenSize.width,screenSize.height);
		imagePanel.add(imageLabel);
		getContentPane().add(imagePanel);
		
		jButtonChargementPlan.addActionListener( ecouteurDeBoutons );
		
		setVisible(true);
	}
	
	public void setModePlan ( Plan plan )
	{
		
		setVisible(false);
		getContentPane().removeAll();
		MapPanel mapPanel = new MapPanel(plan, null, null);
		getContentPane().add(mapPanel);
		jButtonChargementLivraison = new JButton ( "Charger Livraison" );

		jButtonChargementLivraison.setBounds(screenSize.width-180,screenSize.height-220,150,50);

		this.add( jButtonChargementLivraison );
		
		jButtonChargementLivraison.addActionListener( ecouteurDeBoutons );
		getContentPane().add( jButtonChargementPlan );
		getContentPane().add( jButtonRetourAccueil );
		setVisible(true);
	}

	public void setModeDemandeLivraison (Plan plan, DemandeLivraison demandeLivraisons)
	{
		setVisible(false);
		getContentPane().removeAll();

		MapPanel mapPanel = new MapPanel(plan, demandeLivraisons);
		getContentPane().add(mapPanel);
		
		jButtonCalculTournee = new JButton ( "Calculer Tournee" );
		jButtonCalculTournee.setBounds(screenSize.width-180,screenSize.height-300,150,50);

		getContentPane().add( jButtonCalculTournee);
		getContentPane().add( jButtonChargementLivraison );
		getContentPane().add( jButtonChargementPlan );
		getContentPane().add( jButtonRetourAccueil );
		jButtonCalculTournee.addActionListener( ecouteurDeBoutons );
		
		setVisible(true);
	}
	
	public void setModeTournee (Plan plan, DemandeLivraison demandeLivraisons, Tournee tournee)
	{
		setVisible(false);
		getContentPane().removeAll();

		MapPanel mapPanel = new MapPanel(plan, demandeLivraisons, tournee);
		getContentPane().add(mapPanel);
		
		/* liste */
		JPanel jPanelListe = new JPanel ();
		JTextArea jLabelListeLivraison = new JTextArea();
		String texteListe = "Liste des livraisons\n";
		
		int i = 0;
		texteListe += "Depart - " + tournee.getListeHoraire().get(i).getHeureDebut().toString() + "\n";
		for (Livraison livraison : tournee.getLivraisonsOrdonnees() )
		{
			if (i < tournee.getLivraisonsOrdonnees().size()-1 )
			{
			i++;
			texteListe += i + " - de " + tournee.getListeHoraire().get(i).getHeureDebut().toString() 
					+ " a " + tournee.getListeHoraire().get(i).getHeureFin().toString() + "\n";
			}
		}
		texteListe += "Retour à l'entrepot - " + tournee.getListeHoraire().get(i).getHeureFin().toString() + "\n";
		
		jLabelListeLivraison.setText(texteListe);
		System.out.println(texteListe);
		jPanelListe.add(jLabelListeLivraison);
		jPanelListe.setBounds(screenSize.width-250,300,300,200);
		jLabelListeLivraison.setEditable(false);
		getContentPane().add( jPanelListe);
		
		/* bouton */
		jButtonAjouterLivraison = new JButton("+");
		jButtonSupprimerLivraison = new JButton("-");
		jButtonEchangerLivraisons = new JButton("<-/->");
		jButtonUndo = new JButton("undo");
		jButtonRedo = new JButton("redo");
		
		jButtonAjouterLivraison.setBounds(screenSize.width-180,0,100,50);
		jButtonSupprimerLivraison.setBounds(screenSize.width-180,60,100,50);
		jButtonEchangerLivraisons.setBounds(screenSize.width-180,120,100,50);
		jButtonUndo.setBounds(screenSize.width-180,180,100,50);
		jButtonRedo.setBounds(screenSize.width-180,240,100,50);
		
		getContentPane().add( jButtonAjouterLivraison);
		getContentPane().add( jButtonSupprimerLivraison);
		getContentPane().add( jButtonEchangerLivraisons);
		getContentPane().add( jButtonUndo);
		getContentPane().add( jButtonRedo);
		
		jButtonValider = new JButton ( "Valider Tournee" );
		jButtonValider.setBounds(screenSize.width-180,screenSize.height-200,150,50);

		getContentPane().add( jButtonValider);
		getContentPane().add( jButtonRetourAccueil );
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
		JLabel jLabelBonneChance = new JLabel("Bonne Tournee :)");
		jPanelDeLaFin.add(jLabelBonneChance);
		jPanelDeLaFin.setBounds(screenSize.width-200,screenSize.height-200,150,50);

		getContentPane().add( jPanelDeLaFin);
		getContentPane().add( jButtonRetourAccueil );
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