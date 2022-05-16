package Tests;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import affrontement.Bataille;

public class TestReprise {
	public static void main(String[] args) throws IOException {
	Bataille p = new Bataille();
	Path chemin = Paths.get("sauvegarde.txt");
	p.reprise(chemin);
	System.out.print(p.etatDetail());
	}
}
