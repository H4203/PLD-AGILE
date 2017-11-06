package vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

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
			case "Accueil" :
			{
				controleur.accueil();
				break;
			}
			case "Charger Plan" :
			{
				String chemin = "";
				
				fileChooser.setCurrentDirectory(new File("./data/plan"));
				
			    if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
			    {
			       chemin = fileChooser.getSelectedFile().getAbsolutePath();
			       controleur.chargerPlan(chemin);
			    }
				break;
			}
			case "Chargement Plan" :
			{
				controleur.chargementPlan();
				break;
			}
			case "Charger Demande Livraison" :
			{
				String chemin = "";
				
				fileChooser.setCurrentDirectory(new File("./data/demandeLivraison"));
				
			    if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
			    {
			       chemin = fileChooser.getSelectedFile().getAbsolutePath();
			    }
				controleur.chargerDemandeLivraison(chemin);
				break;
			}
			case "Chargement Demande Livraison" :
			{
				controleur.chargementDemandeLivraison();
				break;
			}
			case "Calculer Tournee" :
			{
				controleur.calculerTournee();
				break;
			}
			case "Calcul Tournee" :
			{
				controleur.calculTournee();
				break;
			}
			case "Modifier Tournee" :
			{
				controleur.suivant();
				break;
			}
			case "Modification Tournee" :
			{
				controleur.modificationTournee();
				break;
			}
			case "Valider Tournee" :
			{
				controleur.validerTournee();
				break;
			}
			case "Validation Tournee" :
			{
				controleur.validationTournee();
				break;
			}
			case "Suivant" :
			{	
				controleur.suivant();
				break;
			}
			case "Precedent" :
			{	
				controleur.precedent();
				break;
			}
			case "Ajouter une Livraison" :
				controleur.ajouterLivraison();
				break;
			case "Retirer une Livraison" :
				controleur.supprimerLivraison();
				break;
			case "Annuler":
				controleur.undo();
				break;
			case "Retablir":
				controleur.redo();
				break;
		}
		
		//PanelChargementPlan panelChargementPlan = new PanelChargementPlan();
	}
}	
