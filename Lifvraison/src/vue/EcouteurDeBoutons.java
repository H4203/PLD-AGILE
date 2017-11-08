package vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import controleur.Controleur;

public class EcouteurDeBoutons implements ActionListener
{
	private Controleur controleur;
	private JFileChooser fileChooser;
	
	/**
	 * @param controleur
	 */
	public EcouteurDeBoutons(Controleur controleur) 
	{
		this.controleur = controleur;
		
		fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		fileChooser.setCurrentDirectory(new File("./data"));
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{			
		switch(e.getActionCommand())
		{
			case "Charger Plan" :
			{
				String chemin = "";
				
				fileChooser.setCurrentDirectory(new File("./data/plan"));
				fileChooser.setFileFilter(new FileNameExtensionFilter("*.xml", "xml"));
	
			    if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
			    {
			       chemin = fileChooser.getSelectedFile().getAbsolutePath();
			       controleur.chargerPlan(chemin);
			    }
				break;
			}
			case "Charger Demande Livraison" :
			{
				String chemin = "";
				
				fileChooser.setCurrentDirectory(new File("./data/demandeLivraison"));
				fileChooser.setFileFilter(new FileNameExtensionFilter("*.xml", "xml"));
	
			    if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
			    {
			       chemin = fileChooser.getSelectedFile().getAbsolutePath();
			    }
				controleur.chargerDemandeLivraison(chemin);
				break;
			}
			case "Calculer Tournee" :
			{
				controleur.calculerTournee();
				break;
			}
			case "Modifier Tournee" :
			{
				controleur.modifierTournee();
				break;
			}
			case "Valider Tournee" :
			{
				controleur.validerTournee();
				break;
			}
			case "Ajouter une Livraison" :
				controleur.ajouterLivraison();
				break;
			case "Retirer une Livraison" :
				controleur.supprimerLivraison();
				break;
			case "Echanger 2 Livraisons" :
				controleur.intervertirLivraisons();
				break;
			case "Annuler":
				controleur.undo();
				break;
			case "Retablir":
				controleur.redo();
				break;
			case "Generer Feuille de Route" :
			{
				String chemin = "";
				
				fileChooser.setCurrentDirectory(new File("./data/feuilleDeRoute"));
				fileChooser.setFileFilter(new FileNameExtensionFilter("*.txt", "txt"));
	
				
			    if(fileChooser.showSaveDialog(fileChooser) == JFileChooser.APPROVE_OPTION)
			    {
			       chemin = fileChooser.getSelectedFile().getAbsolutePath();
			       controleur.genererFeuilleDeRoute(chemin);
			    }

				break;
			}
		}
		
		//PanelChargementPlan panelChargementPlan = new PanelChargementPlan();
	}
}	

