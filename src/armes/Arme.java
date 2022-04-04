package armes;

import protagonistes.Dragon;
import protagonistes.Homme;

public class Arme {
	protected Homme proprietaire;
	
	protected int degat;
	protected String nature;
	public Arme(int d,String n){
		this.degat = d;
		this.nature = n;
		this.proprietaire = null;
	}
	public String getNature() {
		return nature;
	}
	public Homme getProprietaire() {
		return proprietaire;
	}
	public void setProprietaire(Homme proprietaire) {
		this.proprietaire = proprietaire;
	}
	public boolean estPris() {
		if(this.proprietaire == null) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public void lacher() {
		this.proprietaire = null;
	}
	public String attaquer(Dragon d) {
		d.setVie(d.getVie()-this.degat);
		if (d.getVie() < 0){
			d.setVie(0);
		}
		String a = d.getNom() + "subit une attaque violente";
		if (d.getVie() == 0) {
			a = a + " trop violente pour survivre"; 
			a = a +d.mourir();
		}
		else {
			a = a + " mais il parvient a se relever"; 
		}
		return this.proprietaire.getNom() + " attaque "+d.getNom()+" avec son "+ this.getNature() + a;   
	}
}
