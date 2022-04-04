package controleur;

import affrontement.Bataille;
import armes.Arme;
import armes.StockArmes;
import protagonistes.Homme;
import protagonistes.TypeEtreVivant;

public class ControleurFairePrendreArme {
	Bataille bataille;
	StockArmes stockArmes;

	public ControleurFairePrendreArme(Bataille bataille, StockArmes stockArmes) {
		this.bataille = bataille;
		this.stockArmes = stockArmes;
	}

	public int donnerNombreHommes() {
		return bataille.donnerNombreHommes();
	}

	public int donnerNombreArmes() {
		return stockArmes.donnerNombreArme();
	}

	public String afficherCombattantHomme() {
		return bataille.afficherCamp(TypeEtreVivant.HOMME);
	}

	public String afficherArmes() {
		return stockArmes.afficherArmes();
	}

	public String fairePrendreArme(int numHomme, int numArme) {
		Homme homme = (Homme) bataille.selectionner(TypeEtreVivant.HOMME, numHomme);
		Arme arme = stockArmes.selectionner(numArme);
		return homme.prendre(arme);
	}
}
