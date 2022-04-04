package vue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Fichier implements ILivre {
	@Override
	public void ecrire(String texte) {
		String chemin = "./src/document/histoire.txt";
		try (FileWriter fichier = new FileWriter(new File(chemin), true)) {
			fichier.write(texte);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
