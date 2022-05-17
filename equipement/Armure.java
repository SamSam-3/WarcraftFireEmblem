package equipement;
import etreVivant.EtreVivant;

public class Armure {

	private int PA;
	private String nom;
	protected EtreVivant proprietaire;
	
	public Armure(String n,int pa){
		nom = n;
		PA = pa;
	}
	
	public int prendreCoup(int dg){
		int a = 0;
		this.setPA(this.getPA()-dg);
		if (this.getPA() >= 0) {
			a = Math.abs(this.getPA());
			this.getProprietaire().perdreArmure();
		}
		return a; 
	}
	
	public void lacher() {
		this.proprietaire = null;
	}
	

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
