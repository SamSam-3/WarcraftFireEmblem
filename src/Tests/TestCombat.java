package Tests;

import affrontement.Bataille;
import equipement.Arc;
import equipement.Epee;
import equipement.StockArmes;
import etreVivant.*;
import plateau.*;
public class TestCombat {
/*ce test n'est plus valide*/
	public static void main(String[] args) {
		Bataille p = new Bataille();
		Coordonne a = new Coordonne(1,1);
		Case b = new Case(a,p);
		Coordonne c = new Coordonne(3,3);
		Coordonne d = new Coordonne(3,1);
		Case e = new Case(c,p);
		Case f = new Case(d,p);
		Orc Thrall = new Orc("Thrall",50);
		Homme Jaina = new Homme("Jaina",50);
		Epee epee = new Epee("L'épée des milles vérités");
		Arc arc = new Arc(10);
		StockArmes sk = new StockArmes();
		p.setsk(sk);
		sk.ajouterArme(arc);
		sk.ajouterArme(epee);
		
		System.out.println("Il y a "+sk.donnerNombreArme()+"armes");
		Thrall.rejointBataille(p);
		Jaina.rejointBataille(p);
		
		Thrall.setDisponible(true);
		System.out.println(Thrall.AfficherDisp());
		Thrall.sedeplacer(e);
		Thrall.sedeplacer(f);
		Thrall.sedeplacer(e);
		Thrall.sedeplacer(f);
		Thrall.sedeplacer(b);

	}

	
}
