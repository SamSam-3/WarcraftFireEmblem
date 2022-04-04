package controleur;

import affrontement.Bataille;
import protagonistes.Dragon;
import protagonistes.EtreVivant;
import protagonistes.Homme;
import protagonistes.TypeEtreVivant;

public class ControleurFaireCombattre {
	Bataille bataille;

	public ControleurFaireCombattre(Bataille bataille) {
		this.bataille = bataille;
	}
	
	public int donnerNombreHommes() {
		return bataille.donnerNombreHommes();
	}
	
	public int donnerNombreDragon() {
		return bataille.donnerNombreDragons();
	}
	
	public String afficherCombattantHomme() {
		return bataille.afficherCamp(TypeEtreVivant.HOMME);
	}

	public String afficherCombattantDragon() {
		return bataille.afficherCamp(TypeEtreVivant.DRAGON);
	}
	
	public String faireCombattre(TypeEtreVivant typeAttaquant, int numAttaquant, int numCible) {
		EtreVivant attaquant = bataille.selectionner(typeAttaquant, numAttaquant);
		TypeEtreVivant typeCible = TypeEtreVivant.DRAGON;
		if(typeAttaquant == TypeEtreVivant.DRAGON) {
			typeCible = TypeEtreVivant.HOMME;
		}
		EtreVivant cible = bataille.selectionner(typeCible, numCible);
		if(typeAttaquant == TypeEtreVivant.HOMME) {
			Homme homme = (Homme)attaquant;
			Dragon dragon = (Dragon)cible;
			return homme.combatre(dragon);
		}
		Dragon dragon = (Dragon)attaquant;
		Homme homme = (Homme)cible;
		return dragon.cracherBouleFeu(homme);
	}
}
