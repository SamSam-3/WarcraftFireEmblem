package equipement;
import etreVivant.EtreVivant;

public class Armure {

	private int PA; //point d'armure
	private String nom;
	protected EtreVivant proprietaire;
	
	public Armure(String n,int pa){ //constructeur
		nom = n;
		PA = pa;
	}
	
	public int prendreCoup(int dg){ //calcul de prise de dégat , renvois le reste des dégats non réduits
		int a = 0; //variable contenant le reste de dégat
		this.setPA(this.getPA()-dg); //valeur d'armure - dgt
		if (this.getPA() >= 0) { //si l'armure n'a plus de point d'amure
			a = Math.abs(this.getPA()); //le reste des dégats prend l'opposé de la soustraction
			this.getProprietaire().perdreArmure(); //le personnage perd son armure
		}
		return a; 
	}
	
	public void lacher() {
		this.proprietaire = null;
	}
	//getter et setters

	public int getPA() {
		return PA;
	}

	public void setPA(int pA) {
		PA = pA;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public EtreVivant getProprietaire() {
		return proprietaire;
	}

	public void setProprietaire(EtreVivant proprietaire) {
		this.proprietaire = proprietaire;
	}

	
}
