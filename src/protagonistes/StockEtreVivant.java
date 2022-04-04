
package protagonistes;

import java.util.ArrayList;
import java.util.List;

public class StockEtreVivant {
	private List<Homme> hommes = new ArrayList<>();
	private List<Heros> heros = new ArrayList<>();
	private List<Dragon> dragons = new ArrayList<>();

	public void ajouterHomme(Homme homme) {
		hommes.add(homme);
	}

	public void ajouterHeros(Heros heros) {
		this.heros.add(heros);
	}

	public void ajouterDragon(Dragon dragon) {
		dragons.add(dragon);
	}

	public String afficherEtreVivant() {
		String chaine = "";
		int i = 1;
		for (Heros leHeros : heros) {
			chaine += "- " + i + " - le héros " + leHeros.getNom() + "\n";
			i++;
		}
		for (Homme homme : hommes) {
			chaine += "- " + i + " - l'homme " + homme.getNom() + "\n";
			i++;
		}
		for (Dragon dragon : dragons) {
			chaine += "- " + i + " - le dragon " + dragon.getNom() + "\n";
			i++;
		}
		return chaine;
	}

	public EtreVivant selectionner(int numero) {
		numero--;
		if (numero < heros.size()) {
			return heros.get(numero);
		}
		if (numero < heros.size() + hommes.size()) {
			numero -= heros.size();
			return hommes.get(numero);
		}
		numero -= hommes.size();
		numero -= heros.size();
		return dragons.get(numero);
	}

	public void supprimerEtreVivant(EtreVivant etreVivant) {
		if (etreVivant instanceof Heros) {
			heros.remove(etreVivant);
		} else if (etreVivant instanceof Homme) {
			hommes.remove(etreVivant);
		} else {

			dragons.remove(etreVivant);

		}
	}
	public int donnerNombrePersonnage() {
		return dragons.size() + heros.size() + hommes.size();
	}
}
