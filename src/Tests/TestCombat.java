package Tests;

import affrontement.Bataille;
import etreVivant.*;
import plateau.*;
public class TestCombat {

	public static void main(String[] args) {
		Bataille p = new Bataille();
		Coordonne a = new Coordonne(1,1);
		Case b = new Case(a);
		Coordonne c = new Coordonne(3,3);
		Coordonne d = new Coordonne(3,1);
		Case e = new Case(c);
		Case f = new Case(d);
		Orc Thrall = new Orc("Thrall",50,b);
		Homme Jaina = new Homme("Jaina",50,e);
		Epee epee = new Epee("L'épée des milles vérités");
		Arc arc = new Arc(10);
		StockArmes sk = new StockArmes();
		p.setsk(sk);
		sk.ajouterArme(arc);
		sk.ajouterArme(epee);
		
		System.out.println("Il y a "+sk.donnerNombreArme()+"armes");
		Thrall.rejoindBataille(p);
		Jaina.rejoindBataille(p);
		Thrall.sedeplacer(e);
		Thrall.sedeplacer(f);
		Thrall.sedeplacer(e);
		Thrall.sedeplacer(f);
		Thrall.sedeplacer(b);

	}

	
}
