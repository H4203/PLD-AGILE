package donnees;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import modeles.Tournee;

/**
 * Classe gerant la feuille de route
 * 
 * @author H4203
 *
 */
public class FeuilleDeRoute {

	/**
	 * Constructeur
	 */
	public FeuilleDeRoute() {

	}

	/**
	 * Creer, ecrit et sauvegarde le fichier de la feuillede route
	 * Si le fichier existe deja, il sera ecrase
	 * 
	 * @param tournee
	 *            la tournee du livreur
	 * @param chemin
	 *            chemin du fichier a creer
	 */
	public void gerer(Tournee tournee, String chemin) {

		FileOutputStream fop = null;
		File file;

		try {
			file = new File(chemin);
			fop = new FileOutputStream(file);

			if (!file.exists()) {
				file.createNewFile();
			}
			byte[] contentInBytes = tournee.toString().getBytes();

			fop.write(contentInBytes);
			fop.flush();
			fop.close();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fop != null) {
					fop.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
