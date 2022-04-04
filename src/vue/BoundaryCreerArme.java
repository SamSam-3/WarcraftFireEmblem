package vue;

import controleur.ControleurCreerArme;

public class BoundaryCreerArme {
	ControleurCreerArme controleur;

	public BoundaryCreerArme(ControleurCreerArme controleur) {
		this.controleur = controleur;
	}

	public void creerArme() {
		int numTypeArme;
		do {
			System.out.println("Quel type d'arme souhaitez-vous créer ?");
			System.out.println("1 - une épée,");
			System.out.println("2 - un arc,");
			System.out.println("3 - un lance-pierre.");
			numTypeArme = Clavier.entrerClavierInt();
			switch (numTypeArme) {
			case 1:
				System.out.println("Donnez le nom de votre épée : ");
				String nom = Clavier.entrerClavierString();
				controleur.creerArme(nom);
				break;
			case 2:
				System.out.println("Donnez le nombre de flèches : ");
				int nombreFleches = Clavier.entrerClavierInt();
				controleur.creerArme(nombreFleches);
				break;
			case 3:
				controleur.creerArme();
				break;
			default:
				System.out.println("Vous devez entrer un nombre entre 1 et 3");
				break;
			}
		} while (numTypeArme < 1 || numTypeArme > 3);
	}
}
