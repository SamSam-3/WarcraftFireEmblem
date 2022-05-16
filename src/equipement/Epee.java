package equipement;

public class Epee extends Arme{
	private String name;
	public Epee( String n,int d) {
		degat = d;
		name = n;
		
	}
	public String getNom() {
		return this.name;
	}
}
