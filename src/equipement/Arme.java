package equipement;

import etreVivant.EtreVivant;

public class Arme {
	protected EtreVivant proprietaire;
	
	protected int degat;
	protected String nature;
	protected String nom;
	public Arme(){ //constructeur , pas utilisé
		
		this.proprietaire = null;
	}
	// getters et setters

	public int getDegat() {
		return degat;
	}

	public void setDegat(int degat) {
		this.degat = degat;
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
		d.PrendreCoup(this.degat); //appel fonction pour le calcul des dégats pris avec l'armure
		if (d.getVie() < 0){
			d.setVie(0);
		}
		String a = d.getNom() + "subit une attaque violente";
		if (d.getVie() == 0) {
			a = a + " trop violente pour survivre"; 
			d.mourir(); //mort de l'adversaire si les pv tombe à 0
		}
		else {
			a = a + " mais il parvient a se relever"; 
		}
		System.out.println(a);
	}
}
