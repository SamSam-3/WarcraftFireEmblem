package armes;

import protagonistes.Dragon;

public class Arc extends Arme {
	private int nombreFleche;
	public Arc(int f) {
		super(30, "arc");
		nombreFleche = f;
		// TODO Auto-generated constructor stub
	}
	public int nombreFleche() {
		return this.nombreFleche;
	}
	public String attaquer(Dragon d) {
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
		return this.proprietaire.getNom() + " attaque "+d.getNom()+" avec son "+ this.getNature() + a;   
	}
		else {
			return "Malheuresement il ne possèdait plus de flèches";
		}
	
}
}
