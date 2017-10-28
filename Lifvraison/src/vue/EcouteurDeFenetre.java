package vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class EcouteurDeFenetre implements ComponentListener
{
	private VueGraphique vueGraphique;
	
	public EcouteurDeFenetre(VueGraphique vueGraphique) 
	{
		this.vueGraphique = vueGraphique;
	}
	
	public void componentResized(ComponentEvent e) 
    {
		vueGraphique.resize();        
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
