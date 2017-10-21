package vue;

import java.awt.Component;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class PanelChargementDemandeLivraison extends JPanel 
{
	private static final long serialVersionUID = 1L;

	public String promptForFolder( Component parent )
	{
		String osName = System.getProperty("os.name");
	    //String homeDir = System.getProperty("user.home");
	    //File selectedPath = null;
		
		if (osName.equals("Mac OS X")) {
			String folder = System.getProperty("user.dir");
            JFileChooser fc = new JFileChooser(folder);
            fc.showOpenDialog(parent);
            
            return fc.getSelectedFile().getAbsolutePath();
		}
	    JFileChooser fc = new JFileChooser();
	    fc.setFileSelectionMode( JFileChooser.FILES_AND_DIRECTORIES );

	    fc.setCurrentDirectory(new File("./data"));
	    int resultat = fc.showOpenDialog( parent ) ;
	    if(resultat == JFileChooser.APPROVE_OPTION )
	    {
	        return fc.getSelectedFile().getAbsolutePath();
	    }

	    return null;
	}
}
