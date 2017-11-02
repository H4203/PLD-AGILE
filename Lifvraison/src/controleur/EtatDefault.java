package controleur;

import java.awt.Point;

import modeles.Livraison;
import vue.Fenetre;

public class EtatDefault implements Etat{

	@Override
	public void chargerPlan(Controleur controleur, Fenetre fenetre, String chemin) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void chargerDemandeLivraison(Controleur controleur, Fenetre fenetre, String chemin) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void calculerTournee ( Controleur controleur, Fenetre fenetre ) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void ajouterLivraison(Controleur controleur, Fenetre fenetre) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void supprimerLivraison(Controleur controleur, Fenetre fenetre) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void intervertirLivraison(Controleur controleur, Fenetre fenetre, Livraison livraison1, Livraison livraison2) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void suivant (Controleur controleur, Fenetre fenetre) {
		
	}
	
	@Override
	public void precedent (Controleur controleur, Fenetre fenetre) {
		
	}

	@Override
	public void accueil(Controleur controleur, Fenetre fenetre) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clicgauche(Controleur controleur, Fenetre fenetre, int positonPrecedente, Livraison livraison) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validerTournee(Controleur controleur, Fenetre fenetre) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void chargementPlan(Controleur controleur, Fenetre fenetre) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void chargementDemandeLivraison(Controleur controleur, Fenetre fenetre) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void calculTournee(Controleur controleur, Fenetre fenetre) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificationTournee(Controleur controleur, Fenetre fenetre) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validationTournee(Controleur controleur, Fenetre fenetre) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void clicgauche(Controleur controleur, Point point, ListeDeCommandes listeDeCommandes) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void mouseDrag(Controleur controleur, Point delta) {
		// TODO Auto-generated method stub	
	}
	
	@Override
	public void mouseWheel(Controleur controleur, int steps, Point point) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void undo(ListeDeCommandes listeDeCommandes, Fenetre fenetre) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void redo(ListeDeCommandes listeDeCommandes, Fenetre fenetre) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clicgauche(Controleur controleur, Point point) {
		// TODO Auto-generated method stub
		
	}
	

}
