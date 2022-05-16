package etreVivant;

import affrontement.Bataille;
import plateau.Case;
import plateau.Coordonne;

public class Homme extends EtreVivant{

	public Homme(String n, int v) { //constructeur
		super(n, v);
		mouvement = 3;
		Initial= "  H  " ;
	}

	public void attaquer(EtreVivant v) { //atttaquer
		if (v instanceof Homme) { //si la personne attaqué est dans le camp de l'attaquant , il ne se passe rien
			System.out.println("Je ne peux attaquer quelqu'un de mon camps");
		}
		else {//au sinon
			this.combat(v); //combat entre l'attaquant et le défensseur
			this.getBataille().setTourJouee(true); //le tour est joué
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
