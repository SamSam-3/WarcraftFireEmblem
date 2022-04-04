package test_fonctionnel;

import controleur.ControleurCreerProtagoniste;
import protagonistes.StockEtreVivant;
import vue.BoundaryCreerProtagoniste;

public class TestBoundary {
	public static void main(String[] args) {
		StockEtreVivant stockEtreVivant = new StockEtreVivant();
		ControleurCreerProtagoniste controleurCreerProtagoniste = new ControleurCreerProtagoniste(stockEtreVivant);
		BoundaryCreerProtagoniste boundaryCreerProtagoniste = new BoundaryCreerProtagoniste(
				controleurCreerProtagoniste);
		boundaryCreerProtagoniste.creerProtagoniste();
		boundaryCreerProtagoniste.creerProtagoniste();
		boundaryCreerProtagoniste.creerProtagoniste();
		System.out.println("La liste des personnages non affectées à une bataille est : ");
		System.out.println(stockEtreVivant.afficherEtreVivant());

		// ENTRER CLAVIER && RESULTAT ATTENDU
		// Quel type de personnage souhaitez-vous créer ?
		// 1 - un héros,
		// 2 - un homme,
		// 3 - un dragon.
		// 0
		// Vous devez entrer un nombre entre 1 et 3
		// Quel type de personnage souhaitez-vous créer ?
		// 1 - un héros,
		// 2 - un homme,
		// 3 - un dragon.
		// 4
		// Vous devez entrer un nombre entre 1 et 3
		// Quel type de personnage souhaitez-vous créer ?
		// 1 - un héros,
		// 2 - un homme,
		// 3 - un dragon.
		// 1
		// Comment souhaitez-vous l'appeler ?
		// Arthur
		// Quel type de personnage souhaitez-vous créer ?
		// 1 - un héros,
		// 2 - un homme,
		// 3 - un dragon.
		// 2
		// Comment souhaitez-vous l'appeler ?
		// Thomas
		// Quel type de personnage souhaitez-vous créer ?
		// 1 - un héros,
		// 2 - un homme,
		// 3 - un dragon.
		// 3
		// Comment souhaitez-vous l'appeler ?
		// Dragonnet
		// La liste des personnages non affectées à une bataille est :
		// - 1 - le héros Arthur
		// - 2 - l'homme Thomas
		// - 3 - le dragon Dragonnet
	}
}
