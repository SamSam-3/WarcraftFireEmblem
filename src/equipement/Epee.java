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
}
