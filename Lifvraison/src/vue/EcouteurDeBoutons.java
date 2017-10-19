package vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import controleur.Controleur;
import donnees.XMLParseur;
import modeles.DemandeLivraison;
import modeles.Plan;

public class EcouteurDeBoutons implements ActionListener
{
	private Controleur controleur;
	private int etat;
	
	/**
	 * @param controleur
	 */
	public EcouteurDeBoutons(Controleur controleur) 
	{
		this.controleur = controleur;
		etat = 0;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{	
		JFileChooser fc;
		String chemin ="";
		
		switch ( e.getActionCommand() )
		{
			case "Charger Plan" :
				
				fc = new JFileChooser();
				chemin ="";
				
				fc.setFileSelectionMode( JFileChooser.FILES_AND_DIRECTORIES );
			    
			    fc.setCurrentDirectory(new File("./data"));
	
			    if( fc.showOpenDialog( null ) == JFileChooser.APPROVE_OPTION )
			    {
			       chemin = fc.getSelectedFile().getAbsolutePath();
			    }
			    
				controleur.importerPlan(chemin);
				
				etat = 1;
				
				break;
			
			case "Chargement Plan" :
				
				controleur.afficherPlan();
				
				etat = 1;
				
				break;
				
			case "Charger Demande Livraison" :
				
				fc = new JFileChooser();
				chemin ="";
				
				fc.setFileSelectionMode( JFileChooser.FILES_AND_DIRECTORIES );
			    
			    fc.setCurrentDirectory(new File("./data"));
	
			    if( fc.showOpenDialog( null ) == JFileChooser.APPROVE_OPTION )
			    {
			       chemin = fc.getSelectedFile().getAbsolutePath();
			    }
			    
				controleur.importerDemandeLivraison( chemin );
				
				etat = 2;
				
				break;
				
			case "Chargement Demande Livraison" :
				
				controleur.afficherDemandeLivraison();
				
				etat = 2;
				
				break;
				
			case "Calculer Tournee" :
				
				controleur.calculerTournee();
				
				etat = 3;
				
				break;
				
			case "Calcul Tournee" :
				
				controleur.afficherTournee();
				
				etat = 3;
				
				break;
				
			case "Valider Tournee" :
				
				controleur.validerTournee();
				
				etat = 4;
				
				break;
				
			case "Accueil" :
				
				controleur.retourAccueil();
				
				etat = 0;
				
				break;
				
			case "Suivant" :
				
				switch (etat)
				{
					case 0 :
						
						fc = new JFileChooser();
						chemin ="";
						
						fc.setFileSelectionMode( JFileChooser.FILES_AND_DIRECTORIES );
					    
					    fc.setCurrentDirectory(new File("./data"));
			
					    if( fc.showOpenDialog( null ) == JFileChooser.APPROVE_OPTION )
					    {
					       chemin = fc.getSelectedFile().getAbsolutePath();
					    }
					    
						controleur.importerPlan(chemin);
						
						break;
						
					case 1 :
						
						fc = new JFileChooser();
						chemin ="";
						
						fc.setFileSelectionMode( JFileChooser.FILES_AND_DIRECTORIES );
					    
					    fc.setCurrentDirectory(new File("./data"));
			
					    if( fc.showOpenDialog( null ) == JFileChooser.APPROVE_OPTION )
					    {
					       chemin = fc.getSelectedFile().getAbsolutePath();
					    }				
					    
						controleur.importerDemandeLivraison( chemin );
						
						break;
					
					case 2 :
						
						controleur.calculerTournee();
						
						break;
					
					case 3 :
						
						controleur.validerTournee();
						
						break;
					}
				
				etat = etat + 1;
				
				break;
		}
		
		//PanelChargementPlan panelChargementPlan = new PanelChargementPlan();
	}
}
