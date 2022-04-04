package etreVivant;
import java.util.*;

import plateau.Case;
public class Orc extends EtreVivant{
	
	

	public Orc(String n, int v, Case c) {
		super(n, v, c);
		mouvement = 2;

	}
	public void attaquer(EtreVivant v) {
		if (v instanceof Orc) {
			System.out.println("Je ne peux attaquer quelqu'un de mon camps");
		}
		else {
			this.combat(v);
		}
		
	}
	

}
