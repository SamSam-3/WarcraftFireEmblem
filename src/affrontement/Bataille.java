package affrontement;
import java.io.IOException;
import java.nio.*;
import java.nio.charset.Charset;
import java.nio.file.FileSystem;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.*;

import equipement.Arc;
import equipement.Arme;
import equipement.Epee;
import equipement.StockArmes;
import etreVivant.EtreVivant;
import etreVivant.Homme;
import etreVivant.Orc;
import etreVivant.TypeEtreVivant;
import plateau.Case;
import plateau.Coordonne;

public class Bataille {
	private String Tour ;
	private Camps campHomme = new Camps();
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
	
	
	public void ajouter(Homme homme,Coordonne e) {
		String a ="";
		for(Case q: this.getPt()){ //Pour toutes les cases du plateau
			a += "["+ q.getPosition().getX()+"/"+q.getPosition().getY()+"]"; // Affiche le X et Y
			if (q.getOccupant() != null){
				a = a + "est occup� par : "+ q.getOccupant().getNom(); // Affiche si la case est occupé
			}
			a += "\n";
			if (q.getPosition().getX() == e.getX() && q.getPosition().getY() == e.getY()) { //Si la case correspond a celle du personnage
				 homme.setPosition(q);
				 q.setOccupant(homme);//La case lui appartient
				 System.out.println(q.getOccupant().getNom()+" occupe la case "+"["+ q.getPosition().getX()+"/"+q.getPosition().getY()+"]");
			 }
		 }
		System.out.println(a); //Affiche la case
		campHomme.ajouterEtreVivant(homme); // Ajoute l'homme dans son camp
	}

	public void ajouter(Orc orc,Coordonne e) {
		String a ="";
		for(Case q: this.getPt()){ //Pour toutes les cases
			a += "["+ q.getPosition().getX()+"/"+q.getPosition().getY()+"]"; //Affiche la position de la case
			if (q.getOccupant() != null){ // Si la case est occupé
				a += "est occup� par : "+ q.getOccupant().getNom();
			}
			a += "\n";
			if (q.getPosition().getX() == e.getX() && q.getPosition().getY() == e.getY()) { // Si la case correspond a celle du personnage
				 orc.setPosition(q);
				 q.setOccupant(orc);//La case lui appartient
				 System.out.println(q.getOccupant().getNom()+" occupe la case "+"["+ q.getPosition().getX()+"/"+q.getPosition().getY()+"]");
			 }
		 }
		System.out.println(a); // Affiche la case
		campOrc.ajouterEtreVivant(orc); // Ajoute l'orc dans son camp
	}
	

	public void eliminer(EtreVivant etreVivant) {
		if (etreVivant instanceof Homme) {
			campHomme.supprimerCompagnon(etreVivant);
			if (campOrc.nbCompagnon() > 0 && campHomme.nbCompagnon() == 0) {
				this.victoire = "Homme";
		}
		}
			
			else if(etreVivant instanceof Orc) {
				campOrc.supprimerCompagnon(etreVivant);
				if (campOrc.nbCompagnon() == 0 && campHomme.nbCompagnon() > 0) {
					this.victoire = "Orc";
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

	public int donnerNombreHommes() {
		System.out.print("i");
		return campHomme.nbCompagnon();
	}
	public int donnerNombreDragons() {
		return campOrc.nbCompagnon();
	}

	public void sauvegarder() throws IOException {
		Path chemin = Paths.get("sauvegarde.txt"); // Fichier de sauvegarde
		if(Files.exists(chemin)){ //S'il le fichier existe
			System.out.println("le fichier existe d�j�");
			String a =""+this.getTour(); // String de données du tour
			a = a +"\nCamps Homme : "; // Données du camp Homme
			a = a + Integer.toString(this.donnerNombreHommes())+"\n"; // Nombre d'hommes restant
			for (EtreVivant etreVivant :this.getCampHomme().getCompagnons()) { // Pour tout les Hommes
					a = a + etreVivant.description()+"\n"; // Recupere leurs infos (pv, nom, etc...)
				}
			a = a +"Camps Orc : "; //Données du camp Orc
			a = a + Integer.toString(this.donnerNombreDragons())+"\n" ; // Nombre d'orcs restant
			for (EtreVivant etreVivant :this.getCampOrc().getCompagnons()) { // Pour tout les Orcs
					a = a + etreVivant.description()+"\n"; // Recupere leurs infos (pv, nom, etc...)
				}
			a = a + "Fin"; // Fin d la sauvegarde
			Files.write(chemin, a.getBytes()); // Ecrit sur le support (Ficheir sauvegarde)

		
	}
		else {
			System.out.println("creation du fichier");
			Path file = Files.createFile(chemin);
		}
	
		
	}
	public void reprise(Path g) throws IOException {
		if(Files.exists(g)) { // Si le fichier de sauvegarde existe
			List<String> f; //Liste de strings
			Charset charset = Charset.forName("ISO-8859-1"); //Encodage
			f = Files.readAllLines(g, charset); // La liste recupere les infos de la partie
			this.initialisation(); // Initialise le plateau avec les données

			String TourActuel = f.get(0); // Tour en cours
			int nombreHomme = Integer.parseInt(f.get(1).substring(f.get(1).length() - 1)); // Nombre d'hommes restants
			System.out.print("Il y a "+nombreHomme+" hommes dans ce fichier de sauvegarde");

			int j = 2;
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
				if (f.get(j + 1).charAt(0) == '*'){
					j++;
					System.out.println("ok crea2");
					String[] ga = f.get(j).split(",");
					String nomArme = ga[1];
					String categorie  = ga [0];
					switch(categorie) {
						case "epee":
							Epee b = new Epee(nomArme);
							this.getsk().ajouterArme(b);
							a.prendre(b);
							break;
						case "arc":
							Arc d = new Arc(nomArme);
							this.getsk().ajouterArme(d);
							a.prendre(d);
							
					}
					
				}
				j++;
			}
			
			j = j++;
			System.out.println(f.get(j));
			int nombreOrc = Integer.parseInt(f.get(j).substring(f.get(j).length() - 1));
			System.out.print("Il y a "+nombreOrc+" orcs dans ce fichier de sauvegarde");
			j++;
			for(int i = 0;i<nombreOrc;i++) {
				int X = Integer.parseInt(f.get(j).substring(1,2));
				int Y = Integer.parseInt(f.get(j).substring(3,4));
				Coordonne c = new Coordonne(X, Y);
				String[] héros = f.get(j).split(",");
				String nom = héros[1];
				int vie = Integer.parseInt(héros[2]);
				Orc a = new Orc(nom, vie);
				a.rejointBataille(this, c);
				System.out.println("ok crea");
				System.out.println(f.get(j+1).charAt(0));
				if (f.get(j+1).substring(0,1).equals("*")){
					j++;
					System.out.println("ok crea2");
					String[] ga = f.get(j).split(",");
					String nomArme = ga[1];
					String categorie  = ga [0];
					switch(categorie) {
						case "epee":
							Epee b = new Epee(nomArme);
							this.getsk().ajouterArme(b);
							a.prendre(b);
							break;
						case "arc":
							Arc d = new Arc(nomArme);
							this.getsk().ajouterArme(d);
							a.prendre(d);
							
					}
					
				}
				j++;
			}
			this.setTour(TourActuel);
			System.out.println("fin de la reprise");
			
		}
		else {
			System.out.println("Le fichier de sauvegarde n'existe pas");
		}
	}
	
	public void passerletour() {
		if (this.getTour() == "Homme") {
			this.setTour("Orc");
			for (EtreVivant c : this.getCampOrc().getCompagnons()) {
				c.setDisponible(true);
			}
		}
		else {
			this.setTour("Homme");
			for (EtreVivant c : this.getCampHomme().getCompagnons()) {
				c.setDisponible(true);
			}
		}
	}
	public List<Case> initialisation(){
		this.setTour("Homme");
		List<Case> b = new ArrayList<>();
		for(int i=0;i<21;i++) {
			for(int j=0;j<21;j++) {
				Coordonne c = new Coordonne(i, j);
				Case a = new Case(c, this);
				this.AjCase(a);
			}
		}
	return pt;	
	}
	
	
	public String etat() {
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
	
}