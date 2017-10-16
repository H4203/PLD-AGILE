package vue;

import java.util.Observable;
import java.util.Observer;

import modeles.Plan;

public class VueGraphique implements Observer
{
	private Fenetre fenetre;
	private Plan plan;
	
	public VueGraphique(Plan plan, Fenetre fenetre)
	{
		this.fenetre = fenetre;
		this.plan = plan;
		
		// this observe le plan
		if (plan != null)
		{
			plan.addObserver(this);
		}
	}
	
	public void update(Observable observable, Object objet)
	{
		// Code pour afficher le plan dans la vue graphique
	}
}
