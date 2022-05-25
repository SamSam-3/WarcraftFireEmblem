package etreVivant;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import affrontement.Bataille;
import equipement.Arme;
import equipement.Armure;
import equipement.Epee;
import equipement.StockArmes;
import plateau.Case;
import plateau.Coordonne;

public abstract class EtreVivant {
	private String nom;
	private int vie;
	
	private Case position;
	protected int mouvement;
	protected boolean disponible;
	protected Bataille bataille;
	private Arme maPossession;
	protected String Initial;
	protected Armure monArmure;
	private int vieMax; 
	
	protected EtreVivant(String n,int v){ //constructeur
		nom = n;
		vie = v;
		vieMax = v; // sert pour rendre tout ses pv à un combattant 
	}
	
	public void mourir() {
		this.lacher(); //le perso lache son arme
		this.perdreArmure(); // le perso lache son armure
		this.getPosition().setOccupant(null); //la case qui l'occupait devient vide
		this.setPosition(null); //il n'existe plus dans le plateau
		this.bataille.eliminer(this); //il est retirer de la bataille
	}
	private void lacher() {
		if(maPossession != null ){
			this.maPossession.lacher(); //l'arme équipée n'a plus de propriétaire
			this.setMaPossession(null); //le combattant n'a plus d'arme d'équipé
		}

	}
	public void perdreArmure() {
		if(monArmure != null) {
			this.monArmure.lacher(); //l'armure équipée n'a plus de propriétaire
			this.setMonArmure(null); //le combattant n'a plus d'armure d'équipé
		}
		}
	public void PrendreCoup(int dg) { //prise de dégat
		if (this.getMonArmure() != null){ //si le perso à une armure 
			System.out.print("mon armure prend pour moi");
			this.setVie(this.getVie()-this.getMonArmure().prendreCoup(dg)); //le reste des dégats après passage de l'armure est retirer au perso
		}
		else {
			this.setVie(this.getVie()-dg); //tout les dégats sont pris par les pv du perso
		}
	}
	public void combat(EtreVivant h) { //ne peut être déclancher qu'a partir d'un déplacement sur une case déjà occupé
		while(this.getVie() > 0 && h.getVie() > 0 ) {
			int min = 1;
			int max = 10;
			
			Random random = new Random(); //initalisation de l'aléatoire 

			int value = random.nextInt(max + min); //tirage entre min et max
			System.out.println(value);
			if(value < 5) { //si le tirage est inférieur à 5(50%)
				if (this.getMaPossession() != null) { //si le perso à une arme
					System.out.println("j'attaque avec"+this.getMaPossession().getNom() );
					
				this.getMaPossession().attaquer(h); //le perso attaque l'arme équipée
				}
				else {
					h.PrendreCoup(10); //au sinon il inflige 10 de dégats
				}
				System.out.println(""+ h.getNom()+" a "+h.getVie()+"point de vie" );
				if(h.getVie() <= 0 ) { //si l'adversaire meurt
					System.out.println(""+ h.getNom()+" est morte");
					
					this.getPosition().setOccupant(null); //la case avant l'attaque se vide
					this.setPosition(h.getPosition()); //le perso occupe la case de l'adversaire battu
					
					h.mourir(); //l'adversaire battu meurt
					this.getPosition().setOccupant(this); //la case occupé précédement par l'adversaire battu devient occupé par le perso
					this.obtenir(); //le perso obtient un équipement au hasard dans l'arsenal
					this.setVie(this.getVieMax()); //l'attaquant regagne toute sa vie
					
				}
			}
			else { //si le tirage est supérieur a 5 (50%)
				if (h.getMaPossession() != null) { //si le défensseur à une arme
					h.getMaPossession().attaquer(this); //il attaque avec son arme
					}
					else {
						this.PrendreCoup(10); //l'attaquant prend 10 de dégat
					}
				System.out.println(" "+ this.getNom()+" a "+this.getVie()+"point de vie" );
				if(this.getVie() <= 0 ) { //si l'attaquant n'a plus de vie
					System.out.println(" "+ this.getNom()+" est mort");
					this.mourir(); // l'attaquant meurt
					h.obtenir(); //le défensseur obtient un équipement de l'arsenal
					h.setVie(h.getVieMax()); //le défensseur regagne toute sa vie
				}
			}
		
		}
	
		
	}
	public void prendre(Arme d) { //prise d'une arme
		
		if (this.maPossession != null) { //si le perso a déjà une arme
			this.lacher(); //il lache son arme
		}
		if(d.estPris()) { //si l'arme a déjà un propriétaire
			System.out.println(d.getProprietaire().getNom() + " lache "+ d.getNom());
			d.getProprietaire().lacher(); //le propriétaire lache l'arme ciblé
		}
		this.maPossession = d; //prise de l'arme
		d.setProprietaire(this); //l'arme prend pour propriétaire le perso
		System.out.println(this.getNom() + " prend possession de "+ d.getNom());
		}
	
public void prendre(Armure d) { //prise d'une armure
		
		if (this.monArmure != null) { // si le perso a déjà une armure
			this.perdreArmure(); //il perd son armure
		}
		if(d.getProprietaire() != null) { //si l'armure a déjà un propriétaire
			System.out.println(d.getProprietaire().getNom() + " lache "+ d.getNom());
			d.getProprietaire().perdreArmure();//le propriétaire lache l'armure ciblé
		}
		this.monArmure = d;//prise de l'armure
		d.setProprietaire(this);//l'armure prend pour propriétaire le perso
		System.out.println(this.getNom() + " prend possession de "+ d.getNom());
		}

public void obtenir() { //tirage pour savoir si le perso obtient une armure ou une arme
	int min = 0;
	int max = 2;
	Random random = new Random(); 
	int value = random.nextInt(max + min); //tirage alléatoire entre mix et max
	if (value == 1) { // 1 chance sur 3 d'avoir une armure
		this.obtenirArmure(); // il obtient une armure
	}
	else {
		this.obtenirArme(); //il obtient une arme
	}
	
}
	public void obtenirArme() { //obtention d'une arme
		
		int min = 0;
		int max = this.bataille.getsk().donnerNombreArme(); //max = nombre d'arme dans l'arsenal

		Random random = new Random();
		int value = random.nextInt(max + min); //tirage alétoire
		this.prendre(this.bataille.getsk().selectionner(value)); //prise d'une arme à l'index du tirage aléatoire
	}
public void obtenirArmure() { //obtention d'une armure
		
		int min = 0;
		int max = this.bataille.getArmures().donnerNombreArmure(); //max = nombre d'armure dans l'arsenal

		Random random = new Random();
		int value = random.nextInt(max + min); // tirage alétoire
		this.prendre(this.bataille.getArmures().selectionnerA(value)); //prise d'une armure à l'index du tirage aléatoire
	}
	public void sedeplacer(Case c) {
		if(this.position != null){ //si le preso existe dans le plateau
		if(!this.ActionDisponible().contains(c)) { // si la case cible ne fait pas partie des déplacement possible
		
			System.out.println(this.nom + " ne peux pas acceder à cette case");
		}
		else {
			if (c.getOccupant() != null) { //si la case cible à un occupant
				this.attaquer(c.getOccupant()); //le perso attaque la personne qui occupe la case
			}
			else {
				this.position.setOccupant(null); //la case départ deviens vide 
				this.position = c; //le perso occupe la case cible
				System.out.println("Je me d�place en ["+this.getPosition().getPosition().getX() + ":"+ this.getPosition().getPosition().getY()+"]" );
				c.setOccupant(this); //la case cible prend pour propriétaire le perso
				this.setDisponible(false); // le perso termine son tour
				this.getBataille().setTourJouee(true);
			}
		}
	}
		else {
			System.out.println(this.getNom()+" est mort et ne peut pas se deplacer ");
		}
		
	}
	public void sedeplacer(Coordonne e) { //se deplacer qu'avec une coordonnée
		List<Case> a = this.bataille.getPt(); //recupération du plateau
		for (Case q :a) {
			if (q.getPosition().getX() == e.getX() && q.getPosition().getY() == e.getY()) {
				this.sedeplacer(q); //selection de la case avec les coordonnées donnés
			}
		}
		
	}
		public void attaquer(EtreVivant e) { //methode utilisé par les enfants d'EtreVivant
		
	}
		public List<Case> ActionDisponible() { //envois la liste des coups possible
			if (this.disponible) {
				List<Case> a = this.bataille.getPt();
				List<Case> r = new ArrayList<>();
				for (Case b :a) {
					System.out.println("Test de la case ["+ b.getPosition().getX()+"/"+b.getPosition().getY()+"]");
					int c = Math.abs(b.getPosition().getX()-this.getPosition().getPosition().getX()); //recuperation de la distance potentiel en x 
					c = c + Math.abs(b.getPosition().getY()-this.getPosition().getPosition().getY()); //recuperation de la distance potentiel en y
					if (c < this.getMouvement() && b != this.getPosition()) { //si elle est atteignable
						System.out.println("Ajout de la case ["+ b.getPosition().getX()+"/"+b.getPosition().getY()+"]");
						r.add(b); //ajout de la case si elle est valide
						
					}
				}
				return r; 
			}
			else {
				System.out.println("Aucune action n'est possible , ce n'est pas votre tour");
				return null;
			}
			
		}
		public String AfficherDisp(){ //sortie string de la fonction ActionDisponible() 
			String a ="";
			List<Case> r = this.ActionDisponible();
			if (r != null) {
				
			
			for (Case b : r)
			{
				
				a = a +"["+ b.getPosition().getX()+"/"+b.getPosition().getY()+"]";
				if (b.getOccupant() != null){
					a = a + "est occup� par : "+ b.getOccupant().getNom();
				}
				a = a + "\n";
			}
			
			}
			return a ;
		}
		
		
		
		
	
	
	public String description() { //Sortie caratère des infos du perso pour la sauveragde
		String a = "-"+this.getPosition().getPosition().getX()+"/"+this.getPosition().getPosition().getY()+","+this.getNom()+","+this.getVie()+","+this.getMouvement();
		if (this.maPossession != null) {
			a = a + "\n*"+this.maPossession.getClass().getSimpleName()+","+this.maPossession.getNom() +" | Dégats : " + this.maPossession.getDegat();//Sortie caratère des infos de l'arme du perso pour la sauveragde
		} 
		//if (this.monArmure != null) { pas implémenter dans la reprise
			//a = a + "\n+"+this.monArmure.getClass().getSimpleName()+","+this.monArmure.getPA(); //Sortie caratère des infos de l'armure du perso pour la sauveragde
		//}
		return a;
		
	}
	//getter et setter
	public boolean getDisponible() {
			return disponible;
		}

		public void setDisponible(boolean disponible) {
			this.disponible = disponible;
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

	

}

