package vue;

import controleur.ControleurFairePrendreArme;

public class BoundaryFairePrendreArme {
	ControleurFairePrendreArme controleur;
	SupportEcriture supportEcriture;
	
	public BoundaryFairePrendreArme(ControleurFairePrendreArme controleur, SupportEcriture supportEcriture) {
		this.controleur = controleur;
		this.supportEcriture = supportEcriture;
	}


	public void fairePrendreArme() {
		int nombreArmes = controleur.donnerNombreArmes();
		int nombreHommes = controleur.donnerNombreHommes();
		if (nombreArmes == 0) {
			System.out.println("Il n'y a aucune armes disponibles");
		}
		if (nombreHommes == 0) {
			System.out.println("Il n'y a aucun combattant");
		}
		if (nombreHommes != 0 && nombreArmes != 0) {
			int numHomme = choisirHomme(nombreHommes);
			int numArme = choisirArme(nombreArmes);
			supportEcriture.ecrire(controleur.fairePrendreArme(numHomme, numArme));
		}

	}

	private int choisirHomme(int nombreHommes) {
		String listeHommes = controleur.afficherCombattantHomme();
		int numHomme;
		do {
			System.out.println("Quel homme va prendre l'arme ?");
			System.out.println(listeHommes);
			numHomme = Clavier.entrerClavierInt();
		} while (numHomme < 1 || numHomme > nombreHommes);
		return numHomme;
	}

	private int choisirArme(int nombreArmes) {
		String listeArmes = controleur.afficherArmes();
		int numArme;
		do {
			System.out.println("Quel arme va prendre l'homme ?");
			System.out.println(listeArmes);
			numArme = Clavier.entrerClavierInt();
		} while (numArme < 1 || numArme > nombreArmes);
		return numArme;
	}
}
