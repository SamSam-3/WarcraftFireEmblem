package equipement;

import java.util.ArrayList;
import java.util.List;

public class StockArmes {
	private List<Epee> epees = new ArrayList<>();
	private List<Arc> arcs = new ArrayList<>();
	private List<LancePierre> lancePierres = new ArrayList<>();
	private List<Arme> armes = new ArrayList<>();
	public void ajouterArme(Epee epee) {
		epees.add(epee);
		armes.add(epee);
	}

	public void ajouterArme(Arc arc) {
		arcs.add(arc);
		armes.add(arc);
	}

	public void ajouterArme(LancePierre lancePierre) {
		lancePierres.add(lancePierre);
		armes.add(lancePierre);
	}

	public String afficherArmes() {
		String chaine = "";
		int i = 1;
		for (Epee epee : epees) {
			chaine += "- " + i + " - l'�p�e " + epee.getNom() + "\n";
			i++;
		}
		for (Arc arc : arcs) {
			chaine += "- " + i + " - un arc avec " + arc.nombreFleche();
			if (arc.nombreFleche() > 1) {
				chaine += " fl�ches\n";
			} else {
				chaine += " fl�che\n";
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
		return armes.get(numero);
		
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
		return armes.size();
	}
}
