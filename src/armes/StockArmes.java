package armes;

import java.util.ArrayList;
import java.util.List;

public class StockArmes {
	private List<Epee> epees = new ArrayList<>();
	private List<Arc> arcs = new ArrayList<>();
	private List<LancePierre> lancePierres = new ArrayList<>();

	public void ajouterArme(Epee epee) {
		epees.add(epee);
	}

	public void ajouterArme(Arc arc) {
		arcs.add(arc);
	}

	public void ajouterArme(LancePierre lancePierre) {
		lancePierres.add(lancePierre);
	}

	public String afficherArmes() {
		String chaine = "";
		int i = 1;
		for (Epee epee : epees) {
			chaine += "- " + i + " - l'épée " + epee.getNom() + "\n";
			i++;
		}
		for (Arc arc : arcs) {
			chaine += "- " + i + " - un arc avec " + arc.nombreFleche();
			if (arc.nombreFleche() > 1) {
				chaine += " flèches\n";
			} else {
				chaine += " flèche\n";
			}
			i++;
		}

		for (int j = 0; j < lancePierres.size(); j++) {
			chaine += "- " + i + " - un lance-pierre\n";
			i++;
		}
		return chaine;
	}

	public Arme selectionner(int numero) {
		numero--;
		if (numero < epees.size()) {
			return epees.get(numero);
		}
		if (numero < epees.size() + arcs.size()) {
			numero -= epees.size();
			return arcs.get(numero);
		}
		numero -= epees.size();
		numero -= arcs.size();
		return lancePierres.get(numero);
	}

	public void supprimerArme(Arme arme) {
		if (arme instanceof Epee) {
			epees.remove(arme);
		} else {
			if (arme instanceof Arc) {
				arcs.remove(arme);
			} else {
				lancePierres.remove(arme);
			}

		}
	}
	
	public int donnerNombreArme() {
		return epees.size() + arcs.size() + lancePierres.size();
	}
}
