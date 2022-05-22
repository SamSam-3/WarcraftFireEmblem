package affrontement;
import java.io.IOException;
import java.nio.*;
import java.nio.charset.Charset;
import java.nio.file.FileSystem;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.*;
import java.util.Random;

import equipement.*;
import etreVivant.EtreVivant;
import etreVivant.Homme;
import etreVivant.Orc;
import etreVivant.TypeEtreVivant;
import plateau.Case;
import plateau.Coordonne;

public class Bataille {
	private String Tour ;
	private Camps campHomme = new Camps();

	private EtreVivant PersoActif;
	private Camps campOrc = new Camps();
	public Camps getCampHomme() {
		return campHomme;
	}
	public void setCampHomme(Camps campHomme) {
		this.campHomme = campHomme;
	}
	public Camps getCampOrc() {
		return campOrc;
	}
	public void setCampOrc(Camps campOrc) {
		this.campOrc = campOrc;
	}

	private String victoire;
	private StockArmes sk; 
	private List<Case> pt = new ArrayList<>();
	private boolean tourJouee;
	private StockArmes Armures;
	
	
	public void ajouter(Homme homme,Coordonne e) { //ajout d'un homme à une coordonne donné
		String a ="";
		for(Case q: this.getPt()){ //Pour toutes les cases du plateau
			a += "["+ q.getPosition().getX()+"/"+q.getPosition().getY()+"]"; // Affiche le X et Y
			if (q.getOccupant() != null){
				a = a + "est occup� par : "+ q.getOccupant().getNom(); // Affiche si la case est occupé
			}
			a += "\n";
			if (q.getPosition().getX() == e.getX() && q.getPosition().getY() == e.getY()) { //Si la case correspond a celle donné
				 homme.setPosition(q); //il occupe la case
				 q.setOccupant(homme);//La case est occupé par l'homme
				 System.out.println(q.getOccupant().getNom()+" occupe la case "+"["+ q.getPosition().getX()+"/"+q.getPosition().getY()+"]");
			 }
		 }
		System.out.println(a); //Affiche la case
		campHomme.ajouterEtreVivant(homme); // Ajoute l'homme dans son camp
	}

	public void ajouter(Orc orc,Coordonne e) {//ajout d'un orc  à une coordonne donné
		String a ="";
		for(Case q: this.getPt()){ //Pour toutes les cases
			a += "["+ q.getPosition().getX()+"/"+q.getPosition().getY()+"]"; //Affiche la position de la case
			if (q.getOccupant() != null){ // Si la case est occupé
				a += "est occup� par : "+ q.getOccupant().getNom();
			}
			a += "\n";
			if (q.getPosition().getX() == e.getX() && q.getPosition().getY() == e.getY()) { // Si la case correspond a celle du personnage
				 orc.setPosition(q); //il occupe la case
				 q.setOccupant(orc); //la case est occupé par l'orc
				 System.out.println(q.getOccupant().getNom()+" occupe la case "+"["+ q.getPosition().getX()+"/"+q.getPosition().getY()+"]");
			 }
		 }
		System.out.println(a); // Affiche la case
		campOrc.ajouterEtreVivant(orc); // Ajoute l'orc dans son camp
	}
	

	public void eliminer(EtreVivant etreVivant) { //retire un etreVivant de la bataille
		if (etreVivant instanceof Homme) {
			campHomme.supprimerCompagnon(etreVivant);
			if (campOrc.nbCompagnon() > 0 && campHomme.nbCompagnon() == 0) {
				this.victoire = "Homme"; //si le dernier orc est éliminé , la victoire reviens au camp adverse
		}
		}
			
			else if(etreVivant instanceof Orc) {
				campOrc.supprimerCompagnon(etreVivant);
				if (campOrc.nbCompagnon() == 0 && campHomme.nbCompagnon() > 0) {
					this.victoire = "Orc"; //si le dernier orc est éliminé , la victoire reviens au camp adverse
			}
			}
	}


	public EtreVivant selectionner(TypeEtreVivant typeEtreVivant, int numero) { 
		if (typeEtreVivant == TypeEtreVivant.HOMME) {
			return campHomme.selectionner(numero);
		} else {
			return campOrc.selectionner(numero);
		}
	}

	public String afficherCamp(TypeEtreVivant typeEtreVivant) {
		if (typeEtreVivant == TypeEtreVivant.HOMME) {
			return campHomme.afficherCamp();
		} else {
			return campOrc.afficherCamp();
		}
	}

	public int donnerNombreHommes() { //retourne le nombres d'homme
		System.out.print("i");
		return campHomme.nbCompagnon();
	}
	public int donnerNombreOrc() { //retourn le nombres d'orc
		return campOrc.nbCompagnon();
	}

	public void sauvegarder() throws IOException { 
		Path chemin = Paths.get("sauvegarde.txt"); // Fichier de sauvegarde
		if(Files.exists(chemin)){ //S'il le fichier existe
			System.out.println("le fichier existe d�j�");
			String a =""+this.getTour(); // String de données du tour
			a = a +"\nCamps Homme : \n"; // Données du camp Homme
			a = a + Integer.toString(this.donnerNombreHommes())+"\n"; // Nombre d'hommes restant
			for (EtreVivant etreVivant :this.getCampHomme().getCompagnons()) { // Pour tout les Hommes
					a = a + etreVivant.description()+"\n"; // Recupere leurs infos (pv, nom, etc...)
				}
			a = a +"Camps Orc : \n"; //Données du camp Orc
			a = a + Integer.toString(this.donnerNombreOrc())+"\n" ; // Nombre d'orcs restant
			for (EtreVivant etreVivant :this.getCampOrc().getCompagnons()) { // Pour tout les Orcs
					a = a + etreVivant.description()+"\n"; // Recupere leurs infos (pv, nom, etc...)
				}
			a = a + "Fin"; // Fin d la sauvegarde
			Files.write(chemin, a.getBytes()); // Ecrit sur le support (Ficheir sauvegarde)

		
	}
		else {
			System.out.println("creation du fichier");
			Path file = Files.createFile(chemin); //creation du fichier de sauvegarde
			this.sauvegarder(); //bouclage 
		}
	
		
	}
	public void reprise(Path g) throws IOException {
		if(Files.exists(g)) { // Si le fichier de sauvegarde existe
			List<String> f; //Liste de strings
			Charset charset = Charset.forName("ISO-8859-1"); //Encodage
			f = Files.readAllLines(g, charset); // La liste recupere les infos de la partie
			this.initialisation(); // Initialise le plateau avec les données

			String TourActuel = f.get(0); // Tour en cours
			int nombreHomme = Integer.parseInt(f.get(2)); // Nombre d'hommes restants
			System.out.print("Il y a "+nombreHomme+" hommes dans ce fichier de sauvegarde");

			int j = 3;
			for(int i = 0;i<nombreHomme;i++) { // Pour le nombre d'hommes restants
				// Récupère les coordonées X et Y
				int X = Integer.parseInt(f.get(j).substring(1,2));
				int Y = Integer.parseInt(f.get(j).substring(3,4));
				Coordonne c = new Coordonne(X, Y);

				String[] héros = f.get(j).split(","); //Récupère les infos du héro
				String nom = héros[1]; // récupère son nom
				int vie = Integer.parseInt(héros[2]); // Ses pts de vies
				Homme a = new Homme(nom, vie); // Crée un objet Homme avec ses infos
				a.rejointBataille(this, c); // Le fait rejoindre la bataille

				System.out.println(f.get(j+1).charAt(0)); 
				if (f.get(j + 1).charAt(0) == '*'){  //si la ligne suivante commence par une * 
					j++;
					String[] ga = f.get(j).split(","); //récupération des infos
					String nomArme = ga[1]; //récupération du nom
					String categorie  = ga [0]; //récuperaton du type
					switch(categorie) {
						case "epee":
							Epee b = new Epee(nomArme,20*Integer.parseInt(nomArme.substring(3,4))); //création d'une épée
							this.getsk().ajouterArme(b); //ajout dans l'arsenal
							a.prendre(b); //on équipe l'arme
							break;
						case "arc":
							Arc d = new Arc(nomArme,15*Integer.parseInt(nomArme.substring(2,3))); //création d'un arc
							this.getsk().ajouterArme(d); //ajout dans l'arsenal
							a.prendre(d); //on équipe l'arme
							break;
							
					}
					
				}
				j++;
			}
			
			j = j+ 2;
			System.out.println(f.get(j)); 
			int nombreOrc = Integer.parseInt(f.get(j)); //nombre d'orc dans le fichier de sauvegarde
			System.out.print("Il y a "+nombreOrc+" orcs dans ce fichier de sauvegarde");
			j++;
			for(int i = 0;i<nombreOrc;i++) {
				int X = Integer.parseInt(f.get(j).substring(1,2)); // x du perso
				int Y = Integer.parseInt(f.get(j).substring(3,4)); // y du perso
				Coordonne c = new Coordonne(X, Y);
				String[] héros = f.get(j).split(","); //recupération des autres donnes
				String nom = héros[1]; //nom du perso
				int vie = Integer.parseInt(héros[2]); //vie du perso
				Orc a = new Orc(nom, vie); //creation d'un orc avec les donnes du fichier
				a.rejointBataille(this, c); //instanciation dans la bataille
				System.out.println(f.get(j+1).charAt(0));
				if (f.get(j+1).substring(0,1).equals("*")){ //si la ligne suivante commence par une *  
					j++;
					
					String[] ga = f.get(j).split(","); //recupération info armes
					String nomArme = ga[1]; //nom arme
					String categorie  = ga [0]; //type arme
					switch(categorie) {
						case "epee":
							Epee b = new Epee(nomArme,20*Integer.parseInt(nomArme.substring(3,4))); //création d'une épée
							this.getsk().ajouterArme(b); //ajout arsenal
							a.prendre(b); //on équipe l'arme
							break;
						case "arc":
							Arc d = new Arc(nomArme,15*Integer.parseInt(nomArme.substring(2,3))); //création d'un arc
							this.getsk().ajouterArme(d); //ajout arsenal
							a.prendre(d); //on équipe l'arme
							
					}
					
				}
				j++;
			}
			this.setTour(TourActuel); //reprise au tour de la sauvegarde
			System.out.println("fin de la reprise");
			
		}
		else {
			System.out.println("Le fichier de sauvegarde n'existe pas");
		}
	}
	
	public void passerletour() { //passer le tour
		if (this.getTour() == "Homme") { //si le tour actuel est celui des hommes
			this.setTour("Orc"); //passe le tour aux orcs 
			for (EtreVivant c : this.getCampOrc().getCompagnons()) {
				c.setDisponible(true); //rend tout les orcs actifs
			}
			for (EtreVivant c : this.getCampHomme().getCompagnons()) {
				c.setDisponible(false); //rend tout les hommes inactifs
			}
		} else { //si le tour actuel est celui des orcs
			this.setTour("Homme");//passe le tour aux hommes
			for (EtreVivant c : this.getCampHomme().getCompagnons()) {
				c.setDisponible(true); //rend tout les hommes actifs
			}
			for (EtreVivant c : this.getCampOrc().getCompagnons()) {
				c.setDisponible(false); //rend tout les orcs inactifs
			}
		}
	}

	public List<Case> initialisation(){ //initalisation
		this.setTour("Homme"); //donne le tour aux hommmes
		List<Case> b = new ArrayList<>();
		for(int i=0;i<10;i++) { //creation du plateau
			for(int j=0;j<10;j++) {
				Coordonne c = new Coordonne(j, i);
				Case a = new Case(c, this);  //creation d'une case
				this.AjCase(a); //ajout au plateau
			}
		}
		this.GenererArme(10); //remplissage arsenal 
		this.GenererArmure(10); //remplissage arsenal armure
	return pt;	
	}
	public Case SelectionerCase(Coordonne c) { //renvois la case qui a les coordonnées données
		Case a = null;
		List<Case> r = this.getPt();
		if (r != null) { //si le plateau n'est pas vide
		for (Case b : r) //pour toutes les cases du tableau
		{
			if (b.getPosition().getX() == c.getX() && b.getPosition().getY() == c.getY()) {
				a = b ; //recherche de la case 
			}	
		}
		}
		return a;
	}
	public String AfficherCase(Coordonne c) { //afficher la case et son potentiel occupant
		String a ="";
		Case b = this.SelectionerCase(c);
		a = a + "["+ b.getPosition().getX()+"/"+b.getPosition().getY()+"]";
		if (b.getOccupant() != null) {
			a = a +" est occupé par "+ b.getOccupant().getNom();
			a = a +"Point de vie : "+ b.getOccupant().getVie()+ "Point de mouvement : "+ b.getOccupant().getMouvement();
			if (b.getOccupant().getMaPossession() != null)
			{
				a  = a +"\n il détient "+ b.getOccupant().getMaPossession().getNom(); //arme de l'occupant
			}
			this.setPersoActif(b.getOccupant());
		}
		else {
			a = a + ("\n la case est vide");
		}
		return a;
		
	}
	public String etatDetail() { 
		String a ="";
		List<Case> r = this.getPt();
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
	public void remplissageInit() { //remplissage d'une nouvelle partie
		Orc PionO1 = new Orc("Orc 1", 100);
		Orc PionO2 = new Orc("Orc 2", 100);
		Orc PionO3 = new Orc("Orc 3", 100);
		Orc PionO4 = new Orc("Orc 4", 100);
		Orc PionO5 = new Orc("Orc 5", 100);
		Orc PionO6 = new Orc("Orc 6", 100);
		Orc PionO7 = new Orc("Orc 7", 100);
		Orc PionO8 = new Orc("Orc 8", 100);
		Orc PionO9 = new Orc("Orc 9", 100);
		Coordonne o1 = new Coordonne(2,1);
		Coordonne o2 = new Coordonne(2,2);
		Coordonne o3 = new Coordonne(2,3);
		Coordonne o4 = new Coordonne(2,4);
		Coordonne o5 = new Coordonne(2,5);
		Coordonne o6 = new Coordonne(2,6);
		Coordonne o7 = new Coordonne(2,7);
		Coordonne o8 = new Coordonne(2,8);
		Coordonne o9 = new Coordonne(2,9);
		PionO1.rejointBataille(this, o1);
		PionO2.rejointBataille(this, o2);
		PionO3.rejointBataille(this, o3);
		PionO4.rejointBataille(this, o4);
		PionO5.rejointBataille(this, o5);
		PionO6.rejointBataille(this, o6);
		PionO7.rejointBataille(this, o7);
		PionO8.rejointBataille(this, o8);
		PionO9.rejointBataille(this, o9);
		
		Homme PionH1 = new Homme("Homme 1", 100);
		Homme PionH2 = new Homme("Homme 2", 100);
		Homme PionH3 = new Homme("Homme 3", 100);
		Homme PionH4 = new Homme("Homme 4", 100);
		Homme PionH5 = new Homme("Homme 5", 100);
		Homme PionH6 = new Homme("Homme 6", 100);
		Homme PionH7 = new Homme("Homme 7", 100);
		Homme PionH8 = new Homme("Homme 8", 100);
		Homme PionH9 = new Homme("Homme 9", 100);
		Coordonne h1 = new Coordonne(8,1);
		Coordonne h2 = new Coordonne(8,2);
		Coordonne h3 = new Coordonne(8,3);
		Coordonne h4 = new Coordonne(8,4);
		Coordonne h5 = new Coordonne(8,5);
		Coordonne h6 = new Coordonne(8,6);
		Coordonne h7 = new Coordonne(8,7);
		Coordonne h8 = new Coordonne(8,8);
		Coordonne h9 = new Coordonne(8,9);
		PionH1.rejointBataille(this, h1);
		PionH2.rejointBataille(this, h2);
		PionH3.rejointBataille(this, h3);
		PionH4.rejointBataille(this, h4);
		PionH5.rejointBataille(this, h5);
		PionH6.rejointBataille(this, h6);
		PionH7.rejointBataille(this, h7);
		PionH8.rejointBataille(this, h8);
		PionH9.rejointBataille(this, h9);		
		
		Homme ChH1 = new Homme("Chevalier Homme 1", 80); //creation chevaliers
		ChH1.setInitial(" ChH ");
		ChH1.setMouvement(5);
		Coordonne hch1 = new Coordonne(9,8);
		ChH1.rejointBataille(this, hch1);
		Homme ChH2 = new Homme("Chevalier Homme 2", 80);
		ChH2.setInitial(" ChH ");
		ChH2.setMouvement(5);
		Coordonne hch2 = new Coordonne(9,2);
		ChH2.rejointBataille(this, hch2);
		
		Orc ChO1 = new Orc("Chevalier Orc 1", 80);
		ChO1.setInitial(" ChO ");
		ChO1.setMouvement(5);
		Coordonne hco1 = new Coordonne(1,8);
		ChO1.rejointBataille(this, hco1);
		Orc ChO2 = new Orc("Chevalier Orc 2", 80);
		ChO2.setInitial(" ChO ");
		ChO2.setMouvement(5);
		Coordonne hco2 = new Coordonne(1,2);
		ChO2.rejointBataille(this, hco2);
		
		
	}
	public String afficherPlateau() {  // affichage textuel du plateau
		
		String a = "__________________________________________________________________________________"; //délimitation supérieur
		List<Case> r = this.getPt();
		int i = 1;

		for (Case b : r) //affichage de chaque cellule
		{
			if(b.getPosition().getX() == 1) { //delimitation gauche
				a = a + "\n|" ;
			}
			if(b.getOccupant() != null) {
			a = a +" ["+b.getOccupant().getInitial()+"] ";
			}
			else {
				a = a +" [     ] ";
			}
			if(b.getPosition().getX() == 9) { //delimitation droite
				a = a + "|" ;
			}
		}
		a = a  +"\n__________________________________________________________________________________"; //délimitation inférieur
		return a;
	}
	public void AnnoncerTour() {
		System.out.println("C'est au tour du camp "+this.getTour());
	}
	public void GenererArme(int n) { //génération aléatoire d'armes de type et force différentes
		StockArmes a = new StockArmes();
		for(int i = 0;i<n;i++) { //pour le nombre d'arme à générer
			int min = 1;
			int max = 2;
			
			Random random = new Random();
			int type = random.nextInt(max + min); //tirage du type
			int qualite = random.nextInt(0+4); //tyrage de la qualité
			
			switch(type) { 
			case 1 : 
				if (qualite == 0){
					Epee epee = new Epee("Epee",20);
					a.ajouterArme(epee);
				}
				else {
					Epee epee = new Epee("Epee+"+qualite,20*qualite);
					a.ajouterArme(epee);
				}
				break;
			
			case 2 :
				if (qualite == 0){
					Arc arc = new Arc("Arc",20);
					a.ajouterArme(arc);
				}
				else {
					Arc arc = new Arc("Arc+"+qualite,20*qualite);
					a.ajouterArme(arc);
				}
				break;
			
		}
		}
		this.setsk(a);
	}
	public void GenererArmure(int n) { //génération aléatoire d'armures de force différentes
		StockArmes a = new StockArmes();
		for(int i = 0;i<n;i++) {
			
			
			Random random = new Random();
			
			int qualite = random.nextInt(0+4);
			if (qualite == 0){
				Armure armure = new Armure("Armure",20);
				a.ajouterArmure(armure);
			}
			else {
				Armure armure = new Armure("Armure+"+qualite,20*qualite);
				a.ajouterArmure(armure);
			}
		}
		this.setArmures(a);
	}
	
	public String etat() { //renvois la description d'une case particulière
		String a ="";
		List<Case> r = this.getPt();
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
	
	//getter et setters des attributs
	public void setsk(StockArmes s) {
		this.sk = s;
	}
	public StockArmes getsk() {
		return this.sk;
	}
	public List<Case> getPt() {
		return pt;
	}
	public void setPt(List<Case> pt) {
		this.pt = pt;
	}
	public void AjCase(Case a) {
		this.pt.add(a);
	}
	public String getTour() {
		return Tour;
	}
	public void setTour(String tour) {
		Tour = tour;
	}
	public EtreVivant getPersoActif() {
		return PersoActif;
	}
	public void setPersoActif(EtreVivant persoActif) {
		PersoActif = persoActif;
	}
	public boolean isTourJouee() {
		return tourJouee;
	}
	public void setTourJouee(boolean tourJouee) {
		this.tourJouee = tourJouee;
	}
	public String getVictoire() {
		return victoire;
	}
	public void setVictoire(String victoire) {
		this.victoire = victoire;
	}
	public StockArmes getArmures() {
		return Armures;
	}
	public void setArmures(StockArmes armures) {
		Armures = armures;
	}
	

	
}
