package test_fonctionnel;

import vue.Fichier;
import vue.SupportEcriture;

public class TestEcritureFichier {
	public static void main(String[] args) {
		SupportEcriture supportEcriture = new SupportEcriture(new Fichier());
		supportEcriture.ecrire("Hello World");

		// RESULTAT ATTENDU DANS LE FICHIER histoire.txt sous le paquetage document
		// Hello World
	}
}
