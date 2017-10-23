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
				controleur.setModeAccueil();
				break;
			}
			case "Charger Plan" :
			{
				String chemin = "";
				
			    if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
			    {
			       chemin = fileChooser.getSelectedFile().getAbsolutePath();
			    }

				controleur.setModeChargementPlan(chemin);
				break;
			}
			case "Chargement Plan" :
			{
				controleur.setModeChargementPlan();
				break;
			}
			case "Charger Demande Livraison" :
			{
				String chemin = "";
				
			    if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
			    {
			       chemin = fileChooser.getSelectedFile().getAbsolutePath();
			    }

				controleur.setModeChargementDemandeLivraison(chemin);
				break;
			}
			case "Chargement Demande Livraison" :
			{
				controleur.setModeChargementDemandeLivraison();
				break;
			}
			case "Calculer Tournee" :
			{
				controleur.setModeCalculTournee("");
				break;
			}
			case "Calcul Tournee" :
			{
				controleur.setModeCalculTournee();
				break;
			}
			case "Modifier Tournee" :
			{
				controleur.setModeModificationTournee();
				break;
			}
			case "Modification Tournee" :
			{
				controleur.setModeModificationTournee();
				break;
			}
			case "Valider Tournee" :
			{
				controleur.setModeValidationTournee();
				break;
			}
			case "Validation Tournee" :
			{
				controleur.setModeValidationTournee();
				break;
			}
			case "Suivant" :
			{	
				controleur.setModeSuivant();
				break;
			}
			case "Precedent" :
			{	
				controleur.setModePrecedent();
				break;
			}
		}
		
		//PanelChargementPlan panelChargementPlan = new PanelChargementPlan();
	}
}
