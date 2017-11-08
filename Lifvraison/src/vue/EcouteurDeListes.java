package vue;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controleur.Controleur;

public class EcouteurDeListes implements ListSelectionListener{

	private Controleur controleur;
	private ListPanel listPanel;

	public EcouteurDeListes(Controleur controleur, ListPanel listPanel)
	{
		this.controleur = controleur;
		this.listPanel = listPanel;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
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
				System.out.println("what");
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
