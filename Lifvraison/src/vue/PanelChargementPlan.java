package vue;

import java.awt.Component;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class PanelChargementPlan extends JPanel 
{
	public String promptForFolder( Component parent )
	{
	    JFileChooser fc = new JFileChooser();
	    fc.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY );

	    if( fc.showOpenDialog( parent ) == JFileChooser.APPROVE_OPTION )
	    {
	        return fc.getSelectedFile().getAbsolutePath();
	    }

	    return null;
	}
}
