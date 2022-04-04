package vue;

import affrontement.Bataille;
import armes.StockArmes;
import controleur.ControleurCreerArme;
import controleur.ControleurCreerProtagoniste;
import controleur.ControleurFaireCombattre;
import controleur.ControleurFairePrendreArme;
import controleur.ControleurFaireRejoindreBataille;
import protagonistes.StockEtreVivant;

public class SetUp {
	public static void main(String[] args) {
		SupportEcriture supportEcriture;
		int choixSupport;
		do {
			System.out.println("Souhaitez-vous afficher votre texte à l’écran ou l'écrire dans un fichier ?");
			System.out.println("1 - Affichage écran");
			System.out.println("2 - Ecriture fichier");
			choixSupport = Clavier.entrerClavierInt();
		} while (choixSupport < 1 || choixSupport > 2);
		if (choixSupport == 1) {
			supportEcriture = new SupportEcriture(new Ecran());
		} else {
			supportEcriture = new SupportEcriture(new Fichier());
		}

		StockArmes stockArmes = new StockArmes();
		StockEtreVivant stockEtreVivant = new StockEtreVivant();
		Bataille bataille = new Bataille();

		ControleurCreerProtagoniste controleurCreerProtagoniste = new ControleurCreerProtagoniste(stockEtreVivant);
		ControleurCreerArme controleurCreerArme = new ControleurCreerArme(stockArmes);
		ControleurFaireRejoindreBataille controleurFaireRejoindreBataille = new ControleurFaireRejoindreBataille(
				stockEtreVivant, bataille);
		ControleurFairePrendreArme controleurFairePrendreArme = new ControleurFairePrendreArme(bataille, stockArmes);
		ControleurFaireCombattre controleurFaireCombattre = new ControleurFaireCombattre(bataille);

		BoundaryCreerProtagoniste boundaryCreerProtagoniste = new BoundaryCreerProtagoniste(
				controleurCreerProtagoniste);
		BoundaryCreerArme boundaryCreerArme = new BoundaryCreerArme(controleurCreerArme);

		BoundaryFaireRejoindreBataille boundaryFaireRejoindreBataille = new BoundaryFaireRejoindreBataille(
				controleurFaireRejoindreBataille, supportEcriture);
		BoundaryFaireCombattre boundaryFaireCombattre = new BoundaryFaireCombattre(controleurFaireCombattre,
				supportEcriture);
		BoundaryFairePrendreArme boundaryFairePrendreArme = new BoundaryFairePrendreArme(controleurFairePrendreArme,
				supportEcriture);

		BoundaryEcrivain boundaryEcrivain = new BoundaryEcrivain(boundaryCreerProtagoniste, boundaryCreerArme,boundaryFaireCombattre, boundaryFairePrendreArme, boundaryFaireRejoindreBataille);
		boundaryEcrivain.menuEcrivain();
	}

}
