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

		
		return chaine;
	}

	public Arme selectionner(int numero) {
		return armes.get(numero);
		
	}
	public Armure selectionnerA(int numero) {
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
	
	public int donnerNombreArme() {
		return armes.size();
	}

	public int donnerNombreArmure() {
		// TODO Auto-generated method stub
		return armures.size();
	}
}
