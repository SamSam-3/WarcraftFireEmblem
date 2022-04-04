package vue;

public class BoundaryEcrivain {
	BoundaryCreerProtagoniste boundaryCreerProtagoniste;
	BoundaryCreerArme boundaryCreerArme;
	BoundaryFaireCombattre boundaryFaireCombattre;
	BoundaryFairePrendreArme boundaryFairePrendreArme;
	BoundaryFaireRejoindreBataille boundaryFaireRejoindreBataille;

	public BoundaryEcrivain(BoundaryCreerProtagoniste boundaryCreerProtagoniste, BoundaryCreerArme boundaryCreerArme,BoundaryFaireCombattre boundaryFaireCombattre, BoundaryFairePrendreArme boundaryFairePrendreArme,BoundaryFaireRejoindreBataille boundaryFaireRejoindreBataille) {
		this.boundaryCreerProtagoniste = boundaryCreerProtagoniste;
		this.boundaryCreerArme = boundaryCreerArme;
		this.boundaryFaireCombattre = boundaryFaireCombattre;
		this.boundaryFairePrendreArme = boundaryFairePrendreArme;
		this.boundaryFaireRejoindreBataille = boundaryFaireRejoindreBataille;
	}



	public void menuEcrivain() {
		int numChoix;
		do {
			System.out.println("********************* MENU *********************");
			System.out.println("1 - Créer un personnage");
			System.out.println("2 - Créer une arme");
			System.out.println("3 - Faire rejoindre la bataille à un personnage");
			System.out.println("4 - Faire prendre une arme à un homme");
			System.out.println("5 - Faire combattre deux personnages");
			System.out.println("6 - SORTIR");
			System.out.println("************************************************");

			System.out.print("Selectionner le numéro de l'action que vous souhaitez faire :");
			numChoix = Clavier.entrerClavierInt();
			if (numChoix < 1 || numChoix > 6) {
				System.out.println("Veuillez entrer un numéro entre 1 et 6");
			} else {
				if (numChoix != 6) {
					selectionnerAction(numChoix);
				}
			}
		} while (numChoix != 6);

	}

	private void selectionnerAction(int numChoix) {
		switch (numChoix) {
		case 1:
			boundaryCreerProtagoniste.creerProtagoniste();
			break;
		case 2:
			boundaryCreerArme.creerArme();
			break;
		case 3:
			boundaryFaireRejoindreBataille.faireRejoindreBataille();
			break;
		case 4:
			boundaryFairePrendreArme.fairePrendreArme();
			break;
		case 5:
			boundaryFaireCombattre.faireCombattre();
			break;

		default:
			System.out.println("ce choix n'existe pas");
			break;
		}

	}

}