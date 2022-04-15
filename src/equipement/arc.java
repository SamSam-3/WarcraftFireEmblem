package equipement;

import etreVivant.EtreVivant;

public class Arc extends Arme {
	private int nombreFleche;
	private String name;
	public Arc(int f) {
		super(30, "arc");
		nombreFleche = f;
		name = "arc";
		// TODO Auto-generated constructor stub
	}
	public int nombreFleche() {
		return this.nombreFleche;
	}
	public void attaquer(EtreVivant d) {
		if(this.nombreFleche > 0 ) {
		d.setVie(d.getVie()-this.degat);
		this.nombreFleche--;
		if (d.getVie() < 0){
			d.setVie(0);
		}
		String a = d.getNom() + "subit une attaque violente";
		if (d.getVie() == 0) {
			a = a + " trop violente pour survivre"; 
			d.mourir();
		}
		else {
			a = a + " mais il  parvient a se relever"; 
		}
		 
	}
		
	
}
	public String getNom() {
		return this.name;
	}
}

