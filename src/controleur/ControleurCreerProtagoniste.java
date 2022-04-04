package controleur;

import protagonistes.Dragon;
import protagonistes.Heros;
import protagonistes.Homme;
import protagonistes.StockEtreVivant;
import protagonistes.TypeEtreVivant;

public class ControleurCreerProtagoniste {
private StockEtreVivant stockEtreVivant;

public ControleurCreerProtagoniste(StockEtreVivant a){
	stockEtreVivant = a;
	
}
public void CreerEtreVivant(TypeEtreVivant typeEtreVivant,String name) {
	switch (typeEtreVivant){
		case HEROS:
			Heros h = new Heros(name);
			stockEtreVivant.ajouterHeros(h);
			break;
		case HOMME:
			Homme g = new Homme(name);
			stockEtreVivant.ajouterHomme(g);
			break;
		case DRAGON:
			Dragon d = new Dragon(name);
			stockEtreVivant.ajouterDragon(d);
			break;
			
	}
}
}
