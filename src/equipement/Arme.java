package equipement;

import etreVivant.EtreVivant;

public class Arme {
	protected EtreVivant proprietaire;
	
	protected int degat;
	protected String nature;
	protected String nom;
	public Arme(int d,String n){
		this.degat = d;
		this.nature = n;
		this.proprietaire = null;
	}
	public String getNature() {
		return nature;
	}
	public EtreVivant getProprietaire() {
		return proprietaire;
	}
	public void setProprietaire(EtreVivant proprietaire) {
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
	public String getNom() {
		return this.nom;
	}
	public void setNom(String n) {
		this.nom = n;
	}
	public void lacher() {
		this.proprietaire = null;
	}
	public void attaquer(EtreVivant d) {
		d.setVie(d.getVie()-this.degat);
		if (d.getVie() < 0){
			d.setVie(0);
		}
		String a = d.getNom() + "subit une attaque violente";
		if (d.getVie() == 0) {
			a = a + " trop violente pour survivre"; 
			d.mourir();
		}
		else {
			a = a + " mais il parvient a se relever"; 
		}
		System.out.println(a);
	}
}