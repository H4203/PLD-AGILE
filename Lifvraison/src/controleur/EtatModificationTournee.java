package controleur;

import java.awt.Point;
import vue.Fenetre;

public class EtatModificationTournee extends EtatDefault{
	@Override
	public void suivant (Controleur controleur, Fenetre fenetre) {
	
		controleur.setEtatCourant( controleur.etatModeValidation );
		fenetre.setModeValidationTournee();
	}
	
	@Override
	public void precedent (Controleur controleur, Fenetre fenetre) {
		controleur.setEtatCourant( controleur.etatCalculTournee );
		fenetre.setModeCalculTournee();
	}
	
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
		System.out.println("je suis là");
	}
	@Override
	public void supprimerLivraison ( Controleur controleur, Fenetre fenetre)
	{
		controleur.setEtatCourant( controleur.etatSupprimerLivraison);
		System.out.println("je suis là");
	}
	
	@Override
	public void chargementPlan(Controleur controleur, Fenetre fenetre) {
		controleur.setEtatCourant( controleur.etatChargementPlan );
		fenetre.setModeChargementPlan ();
	}
	@Override
	public void chargementDemandeLivraison(Controleur controleur, Fenetre fenetre) {
		controleur.setEtatCourant( controleur.etatChargementLivraison );
		fenetre.setModeChargementDemandeLivraison ();
	}
	@Override
	public void calculTournee(Controleur controleur, Fenetre fenetre) {
		controleur.setEtatCourant( controleur.etatCalculTournee );
		fenetre.setModeCalculTournee();	
	}
	@Override
	public void modificationTournee(Controleur controleur, Fenetre fenetre) {
		controleur.setEtatCourant( controleur.etatModificationTournee );
		fenetre.setModeModificationTournee();
		
	}
	@Override
	public void clicgauche(Controleur controleur, Point point, ListeDeCommandes listeDeCommandes)
	{
		controleur.plan.getAtPoint(point);
	}
	@Override
	public void drag(Controleur controleur, Point delta)
	{
		controleur.plan.drag(delta);
	}
	@Override
	public void undo(ListeDeCommandes listeDeCommandes) {
		listeDeCommandes.undo();
	}

	@Override
	public void redo(ListeDeCommandes listeDeCommandes) {
		listeDeCommandes.redo();
	}
	
}
