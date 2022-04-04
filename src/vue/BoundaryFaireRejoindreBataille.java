package vue;

import controleur.ControleurFaireRejoindreBataille;

public class BoundaryFaireRejoindreBataille {
	ControleurFaireRejoindreBataille controleur;
	SupportEcriture supportEcriture;
	
	public BoundaryFaireRejoindreBataille(ControleurFaireRejoindreBataille controleur, SupportEcriture supportEcriture) {
		this.controleur = controleur;
		this.supportEcriture = supportEcriture;
	}

	public void faireRejoindreBataille() {
		int nombreCombattant = controleur.nombreCombattant();
		if (nombreCombattant == 0) {
			System.out.println("Il n'y a pas de combattant a faire entrer dans la bataille");
		} else {
			int numTypeProtagoniste;
			String listePersonnage = controleur.afficherEtreVivant();
			do {
				System.out.println("Quel personnage souhaitez-vous faire entrer dans la bataille ?");
				System.out.println(listePersonnage);
				numTypeProtagoniste = Clavier.entrerClavierInt();
				if (numTypeProtagoniste < 1 || numTypeProtagoniste > nombreCombattant) {
					System.out.println("Vous devez entrer un nombre entre 1 et " + nombreCombattant);
				}
			} while (numTypeProtagoniste < 1 || numTypeProtagoniste > nombreCombattant);
			supportEcriture.ecrire(controleur.faireRejoindreBataille(numTypeProtagoniste)); 

		}
	}

}