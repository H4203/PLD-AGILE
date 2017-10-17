package vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import controlleur.Controleur;
import donnees.XMLParseur;
import modeles.DemandeLivraison;
import modeles.Plan;

public class EcouteurDeBoutons implements ActionListener{
	private Controleur controleur;

	
	
	/**
	 * @param controleur
	 */
	public EcouteurDeBoutons(Controleur controleur) {
		this.controleur = controleur;
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		
		JFileChooser fc;
		String chemin ="";
		
		switch ( e.getActionCommand() )
		{
			case "Chargement Plan" :
				fc = new JFileChooser();
				chemin ="";
				
				fc.setFileSelectionMode( JFileChooser.FILES_AND_DIRECTORIES );
			    
			    fc.setCurrentDirectory(new File("./data"));
	
			    if( fc.showOpenDialog( null ) == JFileChooser.APPROVE_OPTION )
			    {
			       chemin = fc.getSelectedFile().getAbsolutePath();
			    }
				controleur.importerPlan(chemin);
				System.out.println(chemin);
				
				break;
			case "Chargement Livraison" :
				
				fc = new JFileChooser();
				chemin ="";
				
				fc.setFileSelectionMode( JFileChooser.FILES_AND_DIRECTORIES );
			    
			    fc.setCurrentDirectory(new File("./data"));
	
			    if( fc.showOpenDialog( null ) == JFileChooser.APPROVE_OPTION )
			    {
			       chemin = fc.getSelectedFile().getAbsolutePath();
			    }
				controleur.ImporterDemande( chemin );
				
				break;
		}
		
		
		//PanelChargementPlan panelChargementPlan = new PanelChargementPlan();
		
		
		
		
	}
	
	

}
