package armes;

public class Epee extends Arme{
	private String name;
	public Epee(String n) {
		super(80, "epee");
		name = n;
		
	}
	public String getNom() {
		return this.name;
	}
}
