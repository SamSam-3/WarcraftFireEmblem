package etreVivant;
import java.util.*;

import affrontement.Bataille;
import plateau.Case;
import plateau.Coordonne;
public class Orc extends EtreVivant{
	
	

	public Orc(String n, int v) { //constructeur
		super(n, v);
		mouvement = 3;
		Initial  ="  O  ";

	}
	public void attaquer(EtreVivant v) { //attaque
		if (v instanceof Orc) { //si le défensseur est du même camp , il ne se passe rien
			System.out.println("Je ne peux attaquer quelqu'un de mon camps");
		}
		else {//au sinon
			this.combat(v); //attaque de l'occupant 
			this.getBataille().setTourJouee(true); //tour jouée
		}
		
	}
public void rejointBataille(Bataille b,Coordonne e) {
		
		this.bataille = b;
		b.ajouter(this, e);
	}
	

}
