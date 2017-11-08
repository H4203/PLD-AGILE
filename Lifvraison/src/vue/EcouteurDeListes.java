package vue;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controleur.Controleur;

/**
 * Ecoute la liste
 * @author H4203
 *
 */
public class EcouteurDeListes implements ListSelectionListener{

	/**
	 * Le controleur
	 */
	private Controleur controleur;
	/**
	 * Le contenant de la liste de la vue textuelle
	 */
	private ListPanel listPanel;

	/**
	 * Constructeur
	 * @param controleur le controleur
	 * @param listPanel le contenant de la liste
	 */
	public EcouteurDeListes(Controleur controleur, ListPanel listPanel)
	{
		this.controleur = controleur;
		this.listPanel = listPanel;
	}

	/* (non-Javadoc)
	 * @see javax.swing.event.ListSelectionListener#valueChanged(javax.swing.event.ListSelectionEvent)
	 */
	@Override
	public void valueChanged(ListSelectionEvent e) {
		ListSelectionModel lsm = (ListSelectionModel)e.getSource();

		if (lsm.isSelectionEmpty()) {
			//set visible to false for detailspanel
			listPanel.cacherDetails();
		} else {
			// Find out which indexes are selected.
			int minIndex = lsm.getMinSelectionIndex();
			int maxIndex = lsm.getMaxSelectionIndex();
			if (maxIndex != minIndex )
			{
				//impossible !?
			}
			else
			{
				listPanel.remplirDetails();
			}
		}
		controleur.modificationDansLaListe();
	}
}
