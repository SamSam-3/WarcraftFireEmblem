package test_fonctionnel;

import controleur.ControleurCreerProtagoniste;
import protagonistes.StockEtreVivant;
import protagonistes.TypeEtreVivant;

public class TestControleur {
	public static void main(String[] args) {
		StockEtreVivant stockEtreVivant = new StockEtreVivant();
		ControleurCreerProtagoniste controleurCreerProtagoniste = new ControleurCreerProtagoniste(stockEtreVivant);

		controleurCreerProtagoniste.CreerEtreVivant(TypeEtreVivant.HEROS, "Arthur");
		controleurCreerProtagoniste.CreerEtreVivant(TypeEtreVivant.HOMME, "Thomas");
		controleurCreerProtagoniste.CreerEtreVivant(TypeEtreVivant.DRAGON, "Dragon noir");
		controleurCreerProtagoniste.CreerEtreVivant(TypeEtreVivant.HOMME, "Jacques");
		
		System.out.println("La liste des personnages non affect�es a une bataille est : ");
		System.out.println(stockEtreVivant.afficherEtreVivant());

		// RESULTAT ATTENDU
		// La liste des personnages non affectées a une bataille est :
		// - 1 - le héros Arthur
		// - 2 - l'homme Thomas
		// - 3 - l'homme Jacques
		// - 4 - le dragon Dragon noir
	}
}
