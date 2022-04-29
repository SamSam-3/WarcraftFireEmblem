package affrontement;


import java.util.*;

import etreVivant.EtreVivant;

public class Camps {
	private List<EtreVivant> compagnons = new ArrayList<>();

	public void ajouterEtreVivant(EtreVivant compagnon) {
		compagnons.add(compagnon);
		System.out.print("t");
	}

	public void supprimerCompagnon(EtreVivant compagnon) {
		compagnons.remove(compagnon);
	}

	public int nbCompagnon() {
		System.out.print("y");
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
	
	public List<EtreVivant> getCompagnons() {
		
		return this.compagnons ;
	}

}