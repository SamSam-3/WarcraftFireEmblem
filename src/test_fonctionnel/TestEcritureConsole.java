package test_fonctionnel;

import vue.Ecran;
import vue.SupportEcriture;

public class TestEcritureConsole {
	public static void main(String[] args) {
		SupportEcriture supportEcriture = new SupportEcriture(new Ecran());

		supportEcriture.ecrire("Hello World");

		// RESULTAT ATTENDU
		// Hello World
	}
}
