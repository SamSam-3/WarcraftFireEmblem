package vue;

import controleur.ControleurCreerProtagoniste;
import protagonistes.TypeEtreVivant;

public class BoundaryCreerProtagoniste {
	protected ControleurCreerProtagoniste controleur;
	public BoundaryCreerProtagoniste(ControleurCreerProtagoniste c) {
		controleur = c;
	}
	public void creerProtagoniste() {
		String nom;
		TypeEtreVivant typeEtreVivant = null;
		int n = 0;
		System.out.println("quel type de personnage voulez vous creer ?");
		System.out.println("1 -un héros");
		System.out.println("2 -un homme");
		System.out.println("3 -un dragon");
			n = Clavier.entrerClavierInt();
		switch(n) {
			case 1:
				typeEtreVivant = TypeEtreVivant.HEROS;
				break;
			case 2:
				typeEtreVivant = TypeEtreVivant.HOMME;
				break;
			case 3:
				typeEtreVivant = TypeEtreVivant.DRAGON;
				break;
			default:
				System.out.println("Vous devez entrer un nombre entre 1 et 3");			
		}
		System.out.println("Comment souhaitez-vous l'appeler?");
		nom = Clavier.entrerClavierString();
		controleur.CreerEtreVivant(typeEtreVivant, nom);
	}
}
