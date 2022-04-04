package etreVivant;

import plateau.Case;

public class Homme extends EtreVivant{

	public Homme(String n, int v, Case c) {
		super(n, v, c);
		mouvement = 5;
	}

	public void attaquer(EtreVivant v) {
		if (v instanceof Homme) {
			System.out.println("Je ne peux attaquer quelqu'un de mon camps");
		}
		else {
			this.combat(v);
		}
		
	}
	
	public String parler(String string) {
		// TODO Auto-generated method stub
		return null;
	}

}
