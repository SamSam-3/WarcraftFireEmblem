package Tests;

import java.io.IOException;
import java.nio.file.Path;

import affrontement.Bataille;
import equipement.Epee;
import equipement.StockArmes;
import etreVivant.Homme;
import etreVivant.Orc;
import plateau.Case;
import plateau.Coordonne;

public class TestSauvergarde {
	public static void main(String[] args) throws IOException {
	Bataille p = new Bataille();
	p.initialisation();
	Coordonne c = new Coordonne(3,3);
	Coordonne c1 = new Coordonne(3,1);	
	Coordonne c2 = new Coordonne(1,1);
	Coordonne c3 = new Coordonne(2,2);
	Homme Jaina = new Homme("Jaina",50);
	Homme Jainaa = new Homme("Squalala",150);
	Epee pa = new Epee("Epee des milles v�rit�s",100);
	StockArmes sk = new StockArmes();
	p.setsk(sk);
	sk.ajouterArme(pa);
	Orc potiron = new Orc("lalala", 300);
	Orc pottiron = new Orc("lololo", 300);
	Jaina.rejointBataille(p, c);
	
	Jainaa.rejointBataille(p, c1);
	potiron.rejointBataille(p, c2);
	pottiron.rejointBataille(p, c3);
	Jaina.prendre(pa);
	p.sauvegarder();
	
} }
