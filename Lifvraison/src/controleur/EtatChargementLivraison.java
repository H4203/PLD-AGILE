package controleur;

import java.awt.Point;

import javax.swing.JOptionPane;

import algorithme.CalculateurTournee;
import donnees.ParseurException;
import modeles.DemandeLivraison;
import modeles.Plan;
import modeles.Tournee;
import vue.Fenetre;

public class EtatChargementLivraison extends EtatDefault{
	
	
	@Override
	public void chargerDemandeLivraison ( Controleur controleur, Fenetre fenetre, String chemin) {
		DemandeLivraison newDemandeLivraison = new DemandeLivraison ();
		try{
			controleur.parseur.chargerLivraison(newDemandeLivraison, chemin, controleur.plan.getListeIntersection());
		} catch (ParseurException e) {
			JOptionPane.showMessageDialog(fenetre, e.getMessage(), "Erreur lors du parsage", JOptionPane.ERROR_MESSAGE);
		}
		controleur.demandeLivraison = newDemandeLivraison;
		controleur.tournee = new Tournee ( controleur.plan , controleur.demandeLivraison);
		controleur.calculateurTournee = new CalculateurTournee(controleur.tournee);
		controleur.calculateurTournee.run();
		controleur.setEtatCourant(controleur.etatModificationTournee);
		fenetre.chargerDemandeLivraison(controleur.demandeLivraison);
		fenetre.chargerTournee(controleur.tournee);
	}
	
	@Override
	public void calculerTournee ( Controleur controleur, Fenetre fenetre )
	{
		controleur.calculateurTournee.run();
		controleur.setEtatCourant( controleur.etatModificationTournee);
	}
}
