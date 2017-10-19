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
				controleur.setModeChargementPlan(null);
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
				controleur.setModeChargementDemandeLivraison(null);
				break;
			}
			case "Calculer Tournee" :
			{
				controleur.setModeCalculTournee("");
				break;
			}
			case "Calcul Tournee" :
			{
				controleur.setModeCalculTournee(null);
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
		}
		
		//PanelChargementPlan panelChargementPlan = new PanelChargementPlan();
	}
}
