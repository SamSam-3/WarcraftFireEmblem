package vue;

import controleur.ControleurFaireCombattre;
import protagonistes.TypeEtreVivant;

public class BoundaryFaireCombattre {
	ControleurFaireCombattre controleur;
	SupportEcriture supportEcriture;

	public BoundaryFaireCombattre(ControleurFaireCombattre controleur, SupportEcriture supportEcriture) {
		this.controleur = controleur;
		this.supportEcriture = supportEcriture;
	}

	public void faireCombattre() {
		int nombreDragon = controleur.donnerNombreDragon();
		int nombreHomme = controleur.donnerNombreHommes();
		if (nombreDragon == 0 || nombreHomme == 0) {
			System.out.println("Il faut avoir au moins un combattant dans chaque camps pour pouvoir combattre !");
		} else {
			int numType;
			do {
				System.out.println("Qui attaque ?");
				System.out.println("1 - un homme,");
				System.out.println("2 - un dragon.");
				numType = Clavier.entrerClavierInt();
			} while (numType < 1 || numType > 2);

			int numAttaquant;
			int numCible;
			TypeEtreVivant typeAttaquant = TypeEtreVivant.HOMME;
			if (numType == 1) {
				numAttaquant = selectionnerHomme("qui attaque.", nombreHomme);
				numCible = selectionnerDragon("qui est attaqué.", nombreDragon);
			} else {
				numAttaquant = selectionnerDragon("qui attaque.", nombreDragon);
				numCible = selectionnerHomme("qui est attaqué.", nombreHomme);
				typeAttaquant = TypeEtreVivant.DRAGON;
			}
			supportEcriture.ecrire(controleur.faireCombattre(typeAttaquant, numAttaquant, numCible));
		}
	}

	private int selectionnerDragon(String typeActeur, int nombreDragons) {
		String listeDragons = controleur.afficherCombattantDragon();
		int numDragon;
		do {
			System.out.println("Selectionner le dragon " + typeActeur);
			System.out.println(listeDragons);
			numDragon = Clavier.entrerClavierInt();
		} while (numDragon < 1 || numDragon > nombreDragons);

		return numDragon;
	}

	private int selectionnerHomme(String typeActeur, int nombreHommes) {
		String listeHommes = controleur.afficherCombattantHomme();
		int numHomme;
		do {
			System.out.println("Selectionner l'homme " + typeActeur);
			System.out.println(listeHommes);
			numHomme = Clavier.entrerClavierInt();
		} while (numHomme < 1 || numHomme > nombreHommes);

		return numHomme;
	}
}
