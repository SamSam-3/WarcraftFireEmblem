package etreVivant;

import affrontement.Bataille;
import plateau.Case;
import plateau.Coordonne;

public class Homme extends EtreVivant{

	public Homme(String n, int v) {
		super(n, v);
		mouvement = 5;
		Initial= "  H  " ;
	}

	public void attaquer(EtreVivant v) {
		if (v instanceof Homme) {
			System.out.println("Je ne peux attaquer quelqu'un de mon camps");
			
		}
		else {
			this.combat(v);
			this.getBataille().setTourJouee(true);
		}
		
	}
	public void rejointBataille(Bataille b,Coordonne e) {
		
		this.bataille = b;
		b.ajouter(this, e);
	}
	
	public String parler(String string) {
		// TODO Auto-generated method stub
		return null;
	}

}
