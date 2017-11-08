package vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import controleur.Controleur;

/**
 * Classe EcoueurDeBoutons Imlement ActionListener
 * 
 * @author 4203 Gere les appels au controleur en fonction des boutons de la
 *         Fenetre
 */
public class EcouteurDeBoutons implements ActionListener {
	private Controleur controleur;
	private JFileChooser fileChooser;

	/**
	 * Constructeur de EcouteurDeBoutons Initialise le FileChooser
	 * 
	 * @param controleur
	 */
	public EcouteurDeBoutons(Controleur controleur) {
		this.controleur = controleur;

		fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		fileChooser.setCurrentDirectory(new File("./data"));
	}

	/**
	 * Recupere les actions des boutons de la fenetre
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Charger Plan":
			String chemin = "";
			fileChooser.setCurrentDirectory(new File("./data/plan"));
			fileChooser.setFileFilter(new FileNameExtensionFilter("*.xml", "xml"));
			if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				chemin = fileChooser.getSelectedFile().getAbsolutePath();
				controleur.chargerPlan(chemin);
			}
			break;
		case "Charger Demande Livraison":
			String chemin1 = "";
			fileChooser.setCurrentDirectory(new File("./data/demandeLivraison"));
			fileChooser.setFileFilter(new FileNameExtensionFilter("*.xml", "xml"));
			if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				chemin1 = fileChooser.getSelectedFile().getAbsolutePath();
			}
			controleur.chargerDemandeLivraison(chemin1);
			break;
		case "Calculer Tournee":
			controleur.calculerTournee();
			break;
		case "Modifier Tournee":
			controleur.modifierTournee();
			break;
		case "Valider Tournee":
			controleur.validerTournee();
			break;
		case "Ajouter une Livraison":
			controleur.ajouterLivraison();
			break;
		case "Retirer une Livraison":
			controleur.supprimerLivraison();
			break;
		case "Echanger 2 Livraisons":
			controleur.intervertirLivraisons();
			break;
		case "Annuler":
			controleur.undo();
			break;
		case "Retablir":
			controleur.redo();
			break;
		case "Generer Feuille de Route":
			String chemin2 = "";
			fileChooser.setCurrentDirectory(new File("./data/feuilleDeRoute"));
			fileChooser.setFileFilter(new FileNameExtensionFilter("*.txt", "txt"));
			if (fileChooser.showSaveDialog(fileChooser) == JFileChooser.APPROVE_OPTION) {
				chemin2 = fileChooser.getSelectedFile().getAbsolutePath();
				controleur.genererFeuilleDeRoute(chemin2);
			}
			break;
		}
	}
}
