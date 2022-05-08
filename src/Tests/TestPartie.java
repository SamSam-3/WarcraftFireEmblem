package Tests;

import java.io.IOException;

import affrontement.Bataille;
import equipement.Arc;
import equipement.Epee;
import equipement.StockArmes;
import etreVivant.Homme;
import etreVivant.Orc;
import plateau.Case;
import plateau.Coordonne;

public class TestPartie {
	 public static void main(String[] args) throws IOException {
	 Bataille p = new Bataille();
	 Epee epee = new Epee("Epée des milles vérités",50);
		Arc arc = new Arc("Arc de la mort qui tue",50);
		StockArmes sk = new StockArmes();
		p.setsk(sk);
		
		sk.ajouterArme(epee);
	 
	 p.initialisation();
	 
	 Coordonne e = new Coordonne(3, 3);
	 Coordonne o = new Coordonne(1,1);
	 Coordonne s = new Coordonne(1,3);
	 Coordonne g = new Coordonne(1,2);
	 Orc Thrall = new Orc("Thrall",100);
	 Thrall.rejointBataille(p, e);
	 Thrall.setDisponible(true);
	 Homme Jaina = new Homme("Jaina",20);
	 Homme Jainaa = new Homme("Jainaa",200);
	 Jaina.rejointBataille(p, o);
	 Jainaa.rejointBataille(p, g);
	 System.out.println(Thrall.AfficherDisp());
	 System.out.println(Jaina.AfficherDisp());
	 Thrall.sedeplacer(o);
	
	 Thrall.sedeplacer(s);
	 Thrall.sedeplacer(o);
	 Thrall.sedeplacer(g);
	 p.sauvegarder();
	 }

}
