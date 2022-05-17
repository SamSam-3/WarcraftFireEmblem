package etreVivant;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import affrontement.Bataille;
import equipement.Arc;
import equipement.Arme;
import equipement.Armure;
import equipement.Epee;
import equipement.StockArmes;
import plateau.Case;
import plateau.Coordonne;

public abstract class EtreVivant {
	private String nom;
	private int vie;
	protected String Initial;
	private Case position;
	protected int mouvement;
	protected boolean disponible;
	protected Bataille bataille;
	protected Armure monArmure;
	private StockArmes inventaireArme;
	private StockArmes inventaireArmure;
	private Arme maPossession;
	private int vieMax; 
	

	protected EtreVivant(String n,int v){
		nom = n;
		vie = v;
		vieMax = v;
	}
	
	public void mourir() {
		this.getPosition().setOccupant(null);
		System.out.println("a" );
		this.setPosition(null);
		this.bataille.eliminer(this);
	}
	private void lacher() {
		this.maPossession.lacher();
		this.setMaPossession(null);
	}
	public void perdreArmure() {
		this.monArmure.lacher();
		this.setMonArmure(null);
	}
	
	public void PrendreCoup(int dg) {
		if (this.getMonArmure() != null){
			System.out.print("mon armure prend pour moi");
			this.setVie(this.getVie()-this.getMonArmure().prendreCoup(dg));
		}
		else {
			this.setVie(this.getVie()-dg);
		}
	}
	public void combat(EtreVivant h) {
		while(this.getVie() > 0 && h.getVie() > 0 ) {
			int min = 1;
			int max = 10;
			
			Random random = new Random();

			int value = random.nextInt(max + min);
			System.out.println(value);
			if(value < 5) {
				if (this.getMaPossession() != null) {
					System.out.println("j'attaque avec"+this.getMaPossession().getNom() );
					
				this.getMaPossession().attaquer(h);
				}
				else {
					h.PrendreCoup(10);
				}
				System.out.println(""+ h.getNom()+" a "+h.getVie()+"point de vie" );
				if(h.getVie() <= 0 ) {
					System.out.println(""+ h.getNom()+" est morte");
					
					this.getPosition().setOccupant(null);
					this.setPosition(h.getPosition());
					
					h.mourir();
					this.getPosition().setOccupant(this);
					this.obtenir();
					this.setVie(this.getVieMax());
					
				}
			}
			else {
				if (h.getMaPossession() != null) {
					h.getMaPossession().attaquer(this);
					}
					else {
						this.PrendreCoup(10);
					}
				System.out.println(" "+ this.getNom()+" a "+this.getVie()+"point de vie" );
				if(this.getVie() <= 0 ) {
					System.out.println(" "+ this.getNom()+" est mort");
					this.mourir();
					h.obtenir();
					h.setVie(h.getVieMax());
				}
			}
		
		}
	
		
	}
	

	public void prendre(Arme d) {
		
		if (this.maPossession != null) {
			this.lacher();
		}
		if(d.estPris()) {
			System.out.println(d.getProprietaire().getNom() + " lache "+ d.getNom());
			d.getProprietaire().lacher();
		}
		this.maPossession = d;
		d.setProprietaire(this);
		System.out.println(this.getNom() + " prend possession de "+ d.getNom());
		}
	
public void prendre(Armure d) {
		
		if (this.monArmure != null) {
			this.perdreArmure();
		}
		if(d.getProprietaire() != null) {
			System.out.println(d.getProprietaire().getNom() + " lache "+ d.getNom());
			d.getProprietaire().lacher();
		}
		this.monArmure = d;
		d.setProprietaire(this);
		System.out.println(this.getNom() + " prend possession de "+ d.getNom());
		}

public void obtenir() {
	int min = 0;
	int max = 2;
	Random random = new Random();
	int value = random.nextInt(max + min);
	if (value == 1) {
		this.obtenirArmure();
	}
	else {
		this.obtenirArme();
	}
	
}
	public void obtenirArme() {
		
		int min = 0;
		int max = this.bataille.getsk().donnerNombreArme();

		Random random = new Random();
		int value = random.nextInt(max + min);
		this.prendre(this.bataille.getsk().selectionner(value));
	}
public void obtenirArmure() {
		
		int min = 0;
		int max = this.bataille.getArmures().donnerNombreArmure();

		Random random = new Random();
		int value = random.nextInt(max + min);
		this.prendre(this.bataille.getArmures().selectionnerA(value));
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
				this.setDisponible(false);
				this.getBataille().setTourJouee(true);
				
			}
			
		}
	}
		else {
			System.out.println(this.getNom()+" est mort et ne peut pas se deplacer ");
		}
		
	}
	public void sedeplacer(Coordonne e) {
		List<Case> a = this.bataille.getPt();
		for (Case q :a) {
			if (q.getPosition().getX() == e.getX() && q.getPosition().getY() == e.getY()) {
				this.sedeplacer(q);
			}
		}
		
	}
		public void attaquer(EtreVivant e) {
		
	}
		public List<Case> ActionDisponible() {
			if (this.getDisponible()) {
				List<Case> a = this.bataille.getPt();
				List<Case> r = new ArrayList<>();
				for (Case b :a) {
					
					int c = Math.abs(b.getPosition().getX()-this.getPosition().getPosition().getX());
					c = c + Math.abs(b.getPosition().getY()-this.getPosition().getPosition().getY());
					if (c < this.getMouvement() && b != this.getPosition()) {
						
						r.add(b);
						
					}
				}
				return r; 
			}
			else {
				System.out.println("Aucune action n'est possible , ce n'est pas votre tour");
				return null;
			}
			
		}
		public String AfficherDisp() {
			String a ="";
			List<Case> r = this.ActionDisponible();
			if (r != null) {
				
			
			for (Case b : r)
			{
				
				a = a +"["+ b.getPosition().getX()+"/"+b.getPosition().getY()+"]";
				if (b.getOccupant() != null){
					a = a + "est occupé par : "+ b.getOccupant().getNom();
				}
				a = a + "\n";
			}
			
			}
			return a ;
		}
		
		
		
		public boolean getDisponible() {
			return disponible;
		}

		public void setDisponible(boolean disponible) {
			this.disponible = disponible;
		}
	
	public String description() {
		String a = "-"+this.getPosition().getPosition().getX()+"/"+this.getPosition().getPosition().getY()+","+this.getNom()+","+this.getVie()+","+this.getMouvement();
		if (this.maPossession != null) {
			a = a + "\n*"+this.maPossession.getClass().getSimpleName()+","+this.maPossession.getNom();
		}
		if (this.monArmure != null) {
			a = a + "\n+"+this.monArmure.getClass().getSimpleName()+","+this.monArmure.getPA();
		}
		return a;
		
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
	public Arme getMaPossession() {
		return maPossession;
	}

	public void setMaPossession(Arme maPossession) {
		this.maPossession = maPossession;
	}

	public String getInitial() {
		return Initial;
	}
	public void setInitial(String g) {
		this.Initial = g;
	}

	public void setCamp(String ini) {
		this.Initial = ini;
	}

	public Armure getMonArmure() {
		return monArmure;
	}

	public void setMonArmure(Armure monArmure) {
		this.monArmure = monArmure;
	}

	public int getVieMax() {
		return vieMax;
	}

	public void setVieMax(int vieMax) {
		this.vieMax = vieMax;
	}
	public Bataille getBataille() {
		return bataille;
	}

	public void setBataille(Bataille bataille) {
		this.bataille = bataille;
	}

	public StockArmes getInventaireArme() {
		return inventaireArme;
	}

	public void setInventaireArme(StockArmes inventaireArme) {
		this.inventaireArme = inventaireArme;
	}

	public StockArmes getInventaireArmure() {
		return inventaireArmure;
	}

	public void setInventaireArmure(StockArmes inventaireArmure) {
		this.inventaireArmure = inventaireArmure;
	}

}
