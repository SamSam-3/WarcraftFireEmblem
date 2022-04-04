package protagonistes;

import affrontement.Bataille;

public class EtreVivant {
protected int vie;
protected String nom;
protected Bataille bataille;
public EtreVivant(String n) {
	this.nom = n;
}

public int getVie() {
	return vie;
}

public void setVie(int vie) {
	this.vie = vie;
}

public String getNom() {
	return nom;
}

public void setNom(String nom) {
	this.nom = nom;
}

public String rejointBataille(Bataille b) {
	this.bataille = b;
	return "";
}

}
