package test_fonctionnel;

import affrontement.Bataille;
import protagonistes.Dragon;
import protagonistes.Heros;

public class TestAttaqueDragon {
	public static void main(String[] args) {
		Heros arthur = new Heros("Arthur");
		Heros hector = new Heros("Hector");
		Heros lancelot = new Heros("Lancelot");
		Heros perceval = new Heros("Perceval");
		Heros tristan = new Heros("Tristan");
		Heros yvain = new Heros("Yvain");
		Dragon dragonNoir = new Dragon("Dragon noir");

		Bataille bataille = new Bataille();

		arthur.rejointBataille(bataille);
		hector.rejointBataille(bataille);
		lancelot.rejointBataille(bataille);
		perceval.rejointBataille(bataille);
		tristan.rejointBataille(bataille);
		yvain.rejointBataille(bataille);
		dragonNoir.rejointBataille(bataille);

		System.out.print(dragonNoir.cracherBouleFeu(tristan));
		System.out.print(dragonNoir.cracherBouleFeu(tristan));
		System.out.print(dragonNoir.cracherBouleFeu(perceval));
		System.out.print(dragonNoir.cracherBouleFeu(perceval));
		System.out.print(dragonNoir.cracherBouleFeu(lancelot));
		System.out.print(dragonNoir.cracherBouleFeu(lancelot));
		System.out.print(dragonNoir.cracherBouleFeu(hector));
		System.out.print(dragonNoir.cracherBouleFeu(hector));
		System.out.print(dragonNoir.cracherBouleFeu(yvain));
		System.out.print(dragonNoir.cracherBouleFeu(yvain));
		System.out.print(dragonNoir.cracherBouleFeu(arthur));

		// RESULTAT ATTENDU
		// Dragon noir crache une boule de feu sur Tristan
		// Tristan subit une violente attaque, mais il parvient � se relever.
		// Dragon noir crache une boule de feu sur Tristan
		// Tristan subit une violente attaque, trop violente pour survivre.
		// C�est ainsi que le courageux Tristan mourut.
		// Dragon noir crache une boule de feu sur Perceval
		// Perceval subit une violente attaque, mais il parvient � se relever.
		// Dragon noir crache une boule de feu sur Perceval
		// Perceval subit une violente attaque, trop violente pour survivre.
		// C�est ainsi que le courageux Perceval mourut.
		// Dragon noir crache une boule de feu sur Lancelot
		// Lancelot subit une violente attaque, mais il parvient � se relever.
		// Dragon noir crache une boule de feu sur Lancelot
		// Lancelot subit une violente attaque, trop violente pour survivre.
		// C�est ainsi que le courageux Lancelot mourut.
		// Dragon noir crache une boule de feu sur Hector
		// Hector subit une violente attaque, mais il parvient � se relever.
		// Dragon noir crache une boule de feu sur Hector
		// Hector subit une violente attaque, trop violente pour survivre.
		// C�est ainsi que le courageux Hector mourut.
		// Dragon noir crache une boule de feu sur Yvain
		// Yvain subit une violente attaque, mais il parvient � se relever.
		// Dragon noir crache une boule de feu sur Yvain
		// Yvain subit une violente attaque, trop violente pour survivre.
		// C�est ainsi que le courageux Yvain mourut.
		// Dragon noir a voulu attaquer Arthur mais il n�avait plus de feu en lui.
		// Arthur a eu beaucoup de chance !
	}

}