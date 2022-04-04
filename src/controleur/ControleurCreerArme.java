package controleur;

import armes.Arc;
import armes.Epee;
import armes.LancePierre;
import armes.StockArmes;

public class ControleurCreerArme {
	private StockArmes stockArmes;

	public ControleurCreerArme(StockArmes stockArmes) {
		this.stockArmes = stockArmes;
	}
	
	public void creerArme() {
		stockArmes.ajouterArme(new LancePierre());
	}
	
	public void creerArme(int nombreFleches) {
		stockArmes.ajouterArme(new Arc(nombreFleches));
	}
	
	public void creerArme(String nom) {
		stockArmes.ajouterArme(new Epee(nom));
	}
}
