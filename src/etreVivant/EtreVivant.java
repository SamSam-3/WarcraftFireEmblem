package etreVivant;

import java.util.Random;

import affrontement.Bataille;
import plateau.Case;

public abstract class EtreVivant {
	private String nom;
	private int vie;
	private Case position;
	protected int mouvement;
	
	protected Bataille bataille;
	
	protected EtreVivant(String n,int v,Case c){
		nom = n;
		vie = v;
		position = c;
		c.setOccupant(this);
	}
	public void rejointBataille(Bataille b) {
		this.bataille = b;
		b.ajouter(this);
	}
	public void mourir() {
		this.getPosition().setOccupant(null);
		this.setPosition(null);
		this.bataille.eliminer(this);
	}
	private void lacher() {
		// TODO Auto-generated method stub
	}
	public void combat(EtreVivant h) {
		while(this.getVie() > 0 && h.getVie() > 0 ) {
			int min = 1;
			int max = 10;

			Random random = new Random();

			int value = random.nextInt(max + min);
			System.out.println(value);
			if(value < 5) {
				h.setVie(h.getVie()-10);
				System.out.println("L'homme "+ h.getNom()+" a "+h.getVie()+"point de vie" );
				if(h.getVie() <= 0 ) {
					System.out.println("L'homme "+ h.getNom()+" est mort");
					this.getPosition().setOccupant(null);
					this.setPosition(h.getPosition());
					h.mourir();
				}
			}
			else {
				this.setVie(this.getVie()-10);
				System.out.println("L'orc "+ this.getNom()+" a "+this.getVie()+"point de vie" );
				if(this.getVie() <= 0 ) {
					System.out.println("L'orc "+ this.getNom()+" est mort");
					this.mourir();
				}
			}
		
		}
	
		
	}
	
	public void sedeplacer(Case c) {
		if(this.position != null){
		System.out.println(this.getNom()+" : je suis en "+this.getPosition().getPosition().getX() +":"+this.getPosition().getPosition().getY()+"]");
		int distancePot = 0;
		if (c.getPosition().getX()<this.getPosition().getPosition().getX()) {
			distancePot = distancePot + this.getPosition().getPosition().getX() - c.getPosition().getX();
		}
		else {
			distancePot = distancePot + c.getPosition().getX() - this.getPosition().getPosition().getX() ;
		}
		if (c.getPosition().getY()<this.getPosition().getPosition().getY()) {
			distancePot = distancePot + this.getPosition().getPosition().getY() - c.getPosition().getY();
		}
		else {
			distancePot = distancePot + c.getPosition().getY() - this.getPosition().getPosition().getY() ;
		} /* distance = distance x + distance y */
		if (distancePot > this.getMouvement()) {
			System.out.println(this.nom + " ne peux pas acceder à cette case");
		}
		else {
			if (c.getOccupant() != null) {
				this.attaquer(c.getOccupant());
			}
			else {
				this.position.setOccupant(null);
				this.position = c;
				System.out.println("Je me déplace en ["+this.getPosition().getPosition().getX() + ":"+ this.getPosition().getPosition().getY()+"]" );
				c.setOccupant(this);
			}
		}
	}
		else {
			System.out.println(this.getNom()+" est mort et ne peut pas se deplacer ");
		}
		
	}
		public void attaquer(EtreVivant e) {
		
	}
	
	
	


	public int getVie() {
		return vie;
	}
	public void setVie(int vie) {
		this.vie = vie;
	}
	public int getMouvement() {
		return mouvement;
	}
	public void setMouvement(int m) {
		this.mouvement = m;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Case getPosition() {
		return position;
	}
	public void setPosition(Case position) {
		this.position = position;
	}
	public void rejoindBataille(Bataille b ) {
		this.bataille = b;
	}
}
