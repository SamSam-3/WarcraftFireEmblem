package etreVivant;
import java.util.*;

import affrontement.Bataille;
import plateau.Case;
import plateau.Coordonne;
public class Orc extends EtreVivant{
	
	

	public Orc(String n, int v) {
		super(n, v);
		mouvement = 3;
		Initial  ="  O  ";

	}
	public void attaquer(EtreVivant v) {
		if (v instanceof Orc) {
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
	

}
