package vue;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**
 * Classe EcouteurDeFenetre Implemente ComponentListener
 * 
 * @author H4203
 */
public class EcouteurDeFenetre implements ComponentListener {
	private VueGraphique vueGraphique;
<<<<<<< HEAD
	public EcouteurDeFenetre(VueGraphique vueGraphique, VueTextuelle vueTextuelle) 
	{
=======

	public EcouteurDeFenetre(VueGraphique vueGraphique, VueTextuelle vueTextuelle) {
>>>>>>> Dev
		this.vueGraphique = vueGraphique;
	}

	/**
	 * Gestion du redimensionnement Appelle la mise a l'echelle de la VueGraphique
	 */
	public void componentResized(ComponentEvent e) {
		vueGraphique.resize();
<<<<<<< HEAD
    }
=======
	}
>>>>>>> Dev

	@Override
	public void componentHidden(ComponentEvent arg0) {

	}

	@Override
	public void componentMoved(ComponentEvent arg0) {

	}

	@Override
	public void componentShown(ComponentEvent arg0) {

	}
}
