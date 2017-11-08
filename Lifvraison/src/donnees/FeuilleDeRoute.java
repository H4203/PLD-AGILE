package donnees;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import modeles.Tournee;

public class FeuilleDeRoute {

	public FeuilleDeRoute() {
		
	}
	public void gerer(Tournee tournee,String chemin) {

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
