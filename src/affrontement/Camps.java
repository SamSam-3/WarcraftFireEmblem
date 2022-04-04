package affrontement;

import java.util.ArrayList;
import java.util.List;

import protagonistes.EtreVivant;

public class Camps {
	private List<EtreVivant> compagnons = new ArrayList<>();

	public void ajouterEtreVivant(EtreVivant compagnon) {
		compagnons.add(compagnon);
	}

	public void supprimerCompagnon(EtreVivant compagnon) {
		compagnons.remove(compagnon);
	}

	public int nbCompagnon() {
		return compagnons.size();
	}

	public EtreVivant selectionner(int numero) {
		if (numero < compagnons.size() + 1) {
			return compagnons.get(numero - 1);
		} else {
			return null;
		}
	}

	public String afficherCamp() {
		String chaine = "";
		int i = 1;
		for (EtreVivant etreVivant : compagnons) {
			chaine += "- " + i + " - " + etreVivant.getNom() + "\n";
			i++;
		}
		return chaine;
	}

}
