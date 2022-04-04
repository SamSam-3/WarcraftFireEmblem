package test_fonctionnel;

import affrontement.Bataille;
import armes.Arc;
import armes.Epee;
import armes.LancePierre;
import protagonistes.Dragon;
import protagonistes.Heros;
import protagonistes.Homme;

public class TestArme {
	public static void main(String[] args) {
		Bataille bataille = new Bataille();
		Homme homme = new Homme("Thomas");
		Heros heros = new Heros("Arthur");
		Dragon dragon = new Dragon("Dragonnet");
		System.out.print(homme.rejointBataille(bataille));
		System.out.print(heros.rejointBataille(bataille));
		System.out.print(dragon.rejointBataille(bataille));
		LancePierre lancePierre = new LancePierre();
		Epee epee = new Epee("Excalibur");
		Arc arc = new Arc(1);
		System.out.println("Le lance-pierre a un propri�taire ? " + lancePierre.estPris());
		System.out.println("Thomas prend le " + lancePierre.getNature() + ".");
		lancePierre.setProprietaire(homme);
		System.out.println("Le lance-pierre a un propriétaire ? " + lancePierre.estPris());
		lancePierre.attaquer(dragon);
		System.out.println("Thomas lache le lance-pierre.");
		lancePierre.lacher();
		System.out.println("Le lance-pierre � un propri�taire ? " + lancePierre.estPris());
		arc.setProprietaire(homme);
		System.out.println("Thomas prend son " + arc.getNature() + ".");
		System.out.print(arc.attaquer(dragon));
		System.out.print(arc.attaquer(dragon));
		epee.setProprietaire(heros);
		System.out.println("Arthur se saisit de son " + epee.getNature() + " " + epee.getNom() + ".");
		System.out.print(epee.attaquer(dragon));
		System.out.print(epee.attaquer(dragon));

		// RESULAT :
				// Thomas est le premier à se joindre à la dernière bataille entre les Hommes et
				// les dragons.
				// Le héros Arthur : Bonjour, je m'appelle Arthur et je viens me joindre au
				// combat.
				// Le dragon Dragonnet fut le premier à arriver sur le champ de bataille.
				// Le lance-pierre a un propriétaire ? false
				// Thomas prend le lance-pierre.
				// Le lance-pierre a un propriétaire ? true
				// Thomas lache le lance-pierre.
				// Le lance-pierre a un propriétaire ? false
				// Thomas prend son arc.
				// Thomas attaque Dragonnet avec son arc.
				// Dragonnet subit une violente attaque, mais il parvient à se relever.
				// Thomas attaque Dragonnet avec son arc.
				// Malheureusement il ne possèdait plus de flèches.
				// Arthur se saisit de son épée Excalibur.
				// Arthur attaque Dragonnet avec son épée.
				// Dragonnet subit une violente attaque, mais il parvient à se relever.
				// Arthur attaque Dragonnet avec son épée.
				// Dragonnet subit une violente attaque, trop violente pour survivre.
				// C'est ainsi que le dragon Dragonnet mourut.
				// Les Hommes ont conquis la lande, leurs villages n'auront plus jamais à trembler devant les dragons.
	}
}