package protagonistes;

import affrontement.Bataille;
import armes.Arme;
import armes.Epee;

public class Homme extends EtreVivant {

	public Homme(String n) {
		super(n);
		this.vie = 100;
	}
	private Arme maPossession;
	
public String parler(String d) {
	String a = this.getNom()+ " : " + d;
	return a ;
}
public String rejointBataille(Bataille b) {
	this.bataille = b;
	b.ajouter(this);
	if (b.donnerNombreHommes() == 1) {
	return this.nom+" est le premier à se joindre à la dernière bataille entre les Hommes et les dragons.";
}
	else {
		return this.parler("Bonjour, je m'appelle "+this.nom+" et je viens me joindre au combat.");
	}
}
public String mourir() {
	String a = " c’est ainsi que le courageux " + this.getNom()+" mourut";
	a = a + this.lacher();
	return a +"\n"+ this.bataille.eliminer(this);
}
public String lacher() {
	if (this.maPossession != null) {
		this.maPossession.lacher();
		String b = this.maPossession.getNature();
		this.maPossession = null;
		return this.nom +"a laché "+b;
	}
	else {
		return "Personne ne peut lacher cette arme car personne ne la posède";
	}
}
public String prendre(Arme d) {
	String a = "";
	if (this.maPossession != null) {
		a = a +this.lacher();
	}
	if(d.estPris()) {
		a = a +this.parler(""+d.getProprietaire().getNom()+" peux tu me laisser ton"+d.getNature() +" ?");
		a = a+ d.getProprietaire().parler("pas de souci, cela me permettra de reprendre mon soufle");
		a =a +d.getProprietaire().lacher();
	}
	if(d instanceof Epee) {
		a = a + prendreEpee(d);
		if (this.getVie()>0) {
			a = a + this.parler("Je prends mon "+d.getNature()+".");
			this.maPossession = d;
			d.setProprietaire(this);
		}
	}
	else {
	a = a + this.parler("Je prends mon "+d.getNature()+".");
	this.maPossession = d;
	d.setProprietaire(this);
	}
		return a;
	}
public String combatre(Dragon d) {
	String a ="";
	if (this.maPossession == null) {
		a = a + this.getNom() +"s'attaque sans aucune arme à"+ d.getNom();
		a = a + this.mourir();
		
	}
	else {
		a = a + this.maPossession.attaquer(d);
	}
	
	return a ;
}
public String prendreEpee(Arme d) {
	String a = "";
	a = a +this.parler(" Je prends l'épée même si pour cela je dois en perde des forces");
	this.setVie(this.getVie()-10);
	if (this.getVie()<= 0) {
		a = a +this.nom +" a voulu prendre l'épée mais son état de fatigue ne lui permettra pas cet ultime effort"+ this.mourir();
	}
	
	return a;
}
}

