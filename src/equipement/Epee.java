package equipement;

public class Epee extends Arme{
	private String name;
	public Epee( String n,int d) { //constructeur
		degat = d;
		name = n;
		
	}
	//getter
	public String getNom() {
		return this.name;
	}
	public void attaquer(EtreVivant d) {

		d.PrendreCoup(this.degat);

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
