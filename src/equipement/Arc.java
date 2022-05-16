package equipement;

import etreVivant.EtreVivant;

public class Arc extends Arme {
	private int nombreFleche;
	private String name;
	public Arc(String n,int d) {
		degat = d;
		name = n;
		
	}
	public String getNom() {
		return this.name;
	}
	
	public int nombreFleche() {
		return this.nombreFleche;
	}
	public void attaquer(EtreVivant d) {
		if(this.nombreFleche > 0 ) {
		d.PrendreCoup(this.degat);
		this.nombreFleche--;
		if (d.getVie() < 0){
			d.setVie(0);
		}
		String a = d.getNom() + "subit une attaque violente";
		if (d.getVie() == 0) {
			a = a + " trop violente pour survivre"; 
		}
		else {
			a = a + " mais il  parvient a se relever"; 
		}
		 
	}
		
	
}
	
}
