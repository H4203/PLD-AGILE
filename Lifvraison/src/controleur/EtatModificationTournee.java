package controleur;

import java.awt.Point;
import vue.Fenetre;

public class EtatModificationTournee extends EtatDefault{
	@Override
	public void suivant (Controleur controleur, Fenetre fenetre) {
	
		controleur.setEtatCourant( controleur.etatModeValidation );
		fenetre.setModeValidationTournee();
	}
	
	/*@Override
	public void precedent (Controleur controleur, Fenetre fenetre) {
		controleur.setEtatCourant( controleur.etatCalculTournee );
		fenetre.setModeCalculTournee();
	}*/
	
	@Override
	public void accueil(Controleur controleur, Fenetre fenetre) {
		controleur.setEtatCourant( controleur.etatAccueil );
		fenetre.setModeAccueil ();
	}
	
	@Override
	public void validerTournee ( Controleur controleur, Fenetre fenetre ) {
		controleur.setEtatCourant( controleur.etatModeValidation );
		fenetre.setModeValidationTournee();
	}
	
	@Override
	public void ajouterLivraison ( Controleur controleur, Fenetre fenetre)
	{
		controleur.setEtatCourant( controleur.etatAjoutLivraison1);
		fenetre.setModeModificationTournee("AjoutLivraison");
	}
	@Override
	public void supprimerLivraison ( Controleur controleur, Fenetre fenetre)
	{
		controleur.setEtatCourant( controleur.etatSupprimerLivraison);
		fenetre.setModeModificationTournee("SuppressionLivraison");
	}
	
	@Override
	public void clicgauche(Controleur controleur, Fenetre fenetre, Point point, ListeDeCommandes listeDeCommandes)
	{
		controleur.plan.getAtPoint(point, controleur.fenetre.getVueGraphique().getToleranceClic());
	}

	@Override
	public void mouseDrag(Controleur controleur, Point delta)
	{
		controleur.fenetre.getVueGraphique().getMapPanel().drag(delta);
	}
	
	@Override
	public void mouseWheel(Controleur controleur, int steps, Point point)
	{
		controleur.fenetre.getVueGraphique().getMapPanel().zoom(steps, point);
	}
	
	@Override
	public void undo(ListeDeCommandes listeDeCommandes, Fenetre fenetre) {
		listeDeCommandes.undo();
		fenetre.setModeModificationTournee();
	}

	@Override
	public void redo(ListeDeCommandes listeDeCommandes, Fenetre fenetre) {
		listeDeCommandes.redo();
		fenetre.setModeModificationTournee();
	}
	
}
