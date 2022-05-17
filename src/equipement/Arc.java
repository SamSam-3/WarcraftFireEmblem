package equipement;

import etreVivant.EtreVivant;

public class Arc extends Arme {
	private int nombreFleche;
	private String name;
	public Arc(String n,int d) { //constructeur
		degat = d;
		name = n;
		nombreFleche = 30;
		
	}
	public String getNom() {
		return this.name;
	}
	
	public int nombreFleche() {
		return this.nombreFleche;
	}
	public void attaquer(EtreVivant d) {
		if(this.nombreFleche > 0 ) {
		d.PrendreCoup(this.degat);  //application des dégats
		this.nombreFleche--; //on retire une flèche
		if (d.getVie() < 0){
			d.setVie(0); 
		}
		String a = d.getNom() + "subit une attaque violente";
		if (d.getVie() == 0) { //si les pv de l'adversaire sont null
			a = a + " trop violente pour survivre"; 
			d.mourir(); //mort de l'adversaire
		}
		else {
			a = a + " mais il  parvient a se relever"; 
		}
		 
	}
		
	
}
	
}
