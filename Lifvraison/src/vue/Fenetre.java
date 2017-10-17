package vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import algorithme.CalculateurTournee;
import controlleur.Controleur;
import donnees.XMLParseur;
import modeles.DemandeLivraison;
import modeles.Plan;
import modeles.Tournee;

public class Fenetre extends JFrame
{
	private VueGraphique vueGraphique;
	private JButton jButtonChargement;
	private EcouteurDeBoutons ecouteurDeBoutons;
	
	public Fenetre (Controleur controleur)
	{
		super();
		ecouteurDeBoutons = new EcouteurDeBoutons ( controleur );
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

        if (gd.isFullScreenSupported()) {
            try {
                gd.setFullScreenWindow(this);
            }
            finally {
                gd.setFullScreenWindow(null);
            }
        }
        
		/*this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
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
		jButtonChargement = new JButton ( "Chargement Plan" );
		jButtonChargement.setBounds(0,0,100,100);
		//getContentPane().add( jButtonChargement, BorderLayout.SOUTH );
		getContentPane().add( jButtonChargement);
		
		jButtonChargement.addActionListener( ecouteurDeBoutons );
		
		setVisible(true);
	}
	
	public void setModePlan ( Plan plan )
	{
		
		setVisible(false);
		getContentPane().removeAll();
		MapPanel mapPanel = new MapPanel(plan, null, null);
		getContentPane().add(mapPanel);
		jButtonChargement = new JButton ( "Chargement Livraison" );
		jButtonChargement.setBounds(0,0,100,100);
		this.add( jButtonChargement );
		
		jButtonChargement.addActionListener( ecouteurDeBoutons );
		
		setVisible(true);
	}

	public void setModeTournee (Plan plan, DemandeLivraison demandeLivraisons, Tournee tournee)
	{
		setVisible(false);
		getContentPane().removeAll();
		MapPanel mapPanel = new MapPanel(plan, demandeLivraisons, tournee);
		getContentPane().add(mapPanel);
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
		getContentPane().add(mapPanel);
		setVisible(true);
		
	}*/
	
}