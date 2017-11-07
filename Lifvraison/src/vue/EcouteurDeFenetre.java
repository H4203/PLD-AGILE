package vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class EcouteurDeFenetre implements ComponentListener
{
	private VueGraphique vueGraphique;
	private VueTextuelle vueTextuelle;
	
	public EcouteurDeFenetre(VueGraphique vueGraphique, VueTextuelle vueTextuelle) 
	{
		this.vueGraphique = vueGraphique;
		this.vueTextuelle = vueTextuelle;
	}
	
	public void componentResized(ComponentEvent e) 
    {
		vueGraphique.resize();
		//vueTextuelle.resize();
    }

	@Override
	public void componentHidden(ComponentEvent arg0) 
	{
		
	}

	@Override
	public void componentMoved(ComponentEvent arg0) 
	{
		
	}

	@Override
	public void componentShown(ComponentEvent arg0) 
	{
		
	}
}
