package test_fonctionnel;

import affrontement.Bataille;
import protagonistes.Dragon;
import protagonistes.Heros;
import protagonistes.Homme;

public class TestBataille {

	public static void main(String[] args) {
		Bataille bataille = new Bataille();
		Homme homme = new Homme("Thomas");
		Heros heros = new Heros("Arthur");
		Dragon dragon = new Dragon("Dragonnet");
		System.out.println(homme.rejointBataille(bataille));
		System.out.println(heros.rejointBataille(bataille));
		System.out.println(dragon.rejointBataille(bataille));
		System.out.println(homme.mourir());
		System.out.println(heros.mourir());
		Homme homme2 = new Homme("Hector");
		homme2.rejointBataille(bataille);
		System.out.print(dragon.mourir());

		// RESULAT :
		// Thomas est le premier à se joindre à la dernière bataille entre les Hommes et
		// les dragons.
		// Le héros Arthur : Bonjour, je m'appelle Arthur et je viens me joindre au
		// combat.
		// Le dragon Dragonnet fut le premier à arriver sur le champ de bataille.
		// C'est ainsi que le courageux Thomas mourut.
		// C'est ainsi que le courageux Arthur mourut.
		// Les dragons ont conquit la lande, les Hommes n'ont pas pu survivre dans ce
		// monde hostile.
		// C'est ainsi que le dragon Dragonnet mourut.
		// Les Hommes ont conquis la lande, leurs villages n'auront plus jamais !
		// trembler devant les dragons.
	}
}
