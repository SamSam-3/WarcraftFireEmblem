package equipement;

import java.util.ArrayList;
import java.util.List;

public class StockArmes {
	private List<Epee> epees = new ArrayList<>();
	private List<Arc> arcs = new ArrayList<>();
	private List<Armure> armures = new ArrayList<>();
	private List<Arme> armes = new ArrayList<>();
	public void ajouterArme(Epee epee) {
		epees.add(epee);
		armes.add(epee);
	}

	public void ajouterArme(Arc arc) {
		arcs.add(arc);
		armes.add(arc);
	}

	public void ajouterArmure(Armure d) {
		armures.add(d);
		
	}
	public String afficherArmes() { //affiches les différentes armes
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
		return chaine;
	}

	public Arme selectionner(int numero) { //renvois l'arme avec l'index n
		return armes.get(numero);
		
	}
	public Armure selectionnerA(int numero) { //renvois l'armure avec l'index n
		return armures.get(numero);
		
	}


	public void supprimerArme(Arme arme) {
		if (arme instanceof Epee) {
			epees.remove(arme);
		} else {
			if (arme instanceof Arc) {
				arcs.remove(arme);
			}

		}
	}
	//renvois le nombres des armes 
	public int donnerNombreArme() {
		return armes.size();
	}
	//renvois le nombres des armures
	public int donnerNombreArmure() {
		
		return armures.size();
	}
}
