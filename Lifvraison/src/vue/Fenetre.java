package vue;

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
import javax.swing.JScrollPane;

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
		setSize(1800, 1000);
		setVisible(true);
		this.setLayout(null);
		setModeAccueil();

		//this.vueGraphique = new VueGraphique(plan, this);
	}
	public void setModeAccueil ()
	{
		getContentPane().removeAll();
		jButtonChargement = new JButton ( "Chargement Plan" );
		jButtonChargement.setBounds(1700, 900, 100, 50);
		getContentPane().add( jButtonChargement );
		
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
		jButtonChargement.setBounds(1700, 900, 100, 50);
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
		JScrollPane jsp = new JScrollPane(mapPanel);
		getContentPane().add(jsp);
		setVisible(true);
		
	}*/
	
}