package protagonistes;

import affrontement.Bataille;

public class Heros extends Homme{

	public Heros(String n) {
		super(n);
		vie = 150;
	}
public String parler(String d) {
	String a = "Le h�ros "+this.getNom()+" : "+d;
	return a;
}

}
