package affrontement;
import java.io.IOException;

import java.nio.*;
import java.nio.charset.Charset;
import java.nio.file.FileSystem;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
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
import java.io.*;
public class Bataille {
	private String Tour ;
	private EtreVivant PersoActif;
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
		for(Case q: this.getPt()){
			a = a +"["+ q.getPosition().getX()+"/"+q.getPosition().getY()+"]";
			if (q.getOccupant() != null){
				a = a + "est occupé par : "+ q.getOccupant().getNom();
			}
			a = a + "\n";
			if (q.getPosition().getX() == e.getX() && q.getPosition().getY() == e.getY()) {
				 homme.setPosition(q);
				 q.setOccupant(homme);
				 System.out.println(q.getOccupant().getNom()+" occupe la case "+"["+ q.getPosition().getX()+"/"+q.getPosition().getY()+"]");
			 }
		 }
		
		campHomme.ajouterEtreVivant(homme);
	}
	

		
	
	public void ajouter(Orc orc,Coordonne e) {
		String a ="";
		for(Case q: this.getPt()){
			a = a +"["+ q.getPosition().getX()+"/"+q.getPosition().getY()+"]";
			if (q.getOccupant() != null){
				a = a + "est occupé par : "+ q.getOccupant().getNom();
			}
			a = a + "\n";
			if (q.getPosition().getX() == e.getX() && q.getPosition().getY() == e.getY()) {
				 orc.setPosition(q);
				 q.setOccupant(orc);
				 System.out.println(q.getOccupant().getNom()+" occupe la case "+"["+ q.getPosition().getX()+"/"+q.getPosition().getY()+"]");
			 }
		 }
		campOrc.ajouterEtreVivant(orc);
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
		Path chemin = Paths.get("sauvegarde.txt");
		if(Files.exists(chemin)){
			System.out.println("le fichier existe déjà");
			String a =""+this.getTour();
			a = a +"\nCamps Homme : ";
			a = a + Integer.toString(this.donnerNombreHommes())+"\n" ;
			for (EtreVivant etreVivant :this.getCampHomme().getCompagnons()) {
					a = a + etreVivant.description()+"\n";
				}
			a = a +"Camps Orc : ";
			a = a + Integer.toString(this.donnerNombreDragons())+"\n" ;
			for (EtreVivant etreVivant :this.getCampOrc().getCompagnons()) {
					a = a + etreVivant.description()+"\n";
				}
			a = a + "Fin";
			Files.write(chemin, a.getBytes());

		
	}
		else {
			System.out.println("creation du fichier");
			Path file = Files.createFile(chemin);
		}
	
		
	}
	public void reprise(Path g) throws IOException {
		if(Files.exists(g)) {
			String q = "*";
			List<String> f = new ArrayList<>();
			Charset charset = Charset.forName("ISO-8859-1");
			f = Files.readAllLines(g, charset);
			this.initialisation();
			String TourActuel = f.get(0);
			int nombreHomme = Integer.parseInt(f.get(1).substring(f.get(1).length() - 1));
			System.out.println("Il y a "+nombreHomme+" hommes dans ce fichier de sauvegarde");
			int j = 2;
			for(int i = 0;i<nombreHomme;i++) {
				int X = Integer.parseInt(f.get(j).substring(1,2));
				int Y = Integer.parseInt(f.get(j).substring(3,4));
				Coordonne c = new Coordonne(X, Y);
				String[] gg = f.get(j).split(",");
				String nom = gg[1];
				int vie = Integer.parseInt(gg[2]);
				Homme a = new Homme(nom, vie);
				a.rejointBataille(this, c);
				System.out.println(f.get(j+1).substring(0,1));
				if (f.get(j+1).substring(0,1).equals(q)){
					j++;
					String[] ga = f.get(j).split(",");
					String nomArme = ga[1];
					String categorie  = ga [0];
					switch(categorie) {
						case "epee":
							Epee b = new Epee(nomArme,20*Integer.parseInt(nomArme.substring(4,5)));
							this.getsk().ajouterArme(b);
							a.prendre(b);
							break;
						case "arc":
							Arc d = new Arc(nomArme,15*Integer.parseInt(nomArme.substring(3,4)));
							this.getsk().ajouterArme(d);
							a.prendre(d);
							
					}
					
				}
				j++;
			}
			
			j = j++;
			System.out.println(f.get(j));
			int nombreOrc = Integer.parseInt(f.get(j).substring(f.get(j).length() - 1));
			System.out.println("Il y a "+nombreOrc+" orcs dans ce fichier de sauvegarde");
			j++;
			for(int i = 0;i<nombreOrc;i++) {
				int X = Integer.parseInt(f.get(j).substring(1,2));
				int Y = Integer.parseInt(f.get(j).substring(3,4));
				Coordonne c = new Coordonne(X, Y);
				String[] gg = f.get(j).split(",");
				String nom = gg[1];
				int vie = Integer.parseInt(gg[2]);
				Orc a = new Orc(nom, vie);
				a.rejointBataille(this, c);
				System.out.println(f.get(j+1).substring(0,1));
				if (f.get(j+1).substring(0,1).equals(q)){
					j++;
					String[] ga = f.get(j).split(",");
					String nomArme = ga[1];
					String categorie  = ga [0];
					switch(categorie) {
						case "epee":
							Epee b = new Epee(nomArme,20*Integer.parseInt(nomArme.substring(3,4)));
							this.getsk().ajouterArme(b);
							a.prendre(b);
							break;
						case "arc":
							Arc d = new Arc(nomArme,15*Integer.parseInt(nomArme.substring(2,3)));
							this.getsk().ajouterArme(d);
							a.prendre(d);
							break;
							
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
		for(int i=1;i<10;i++) {
			for(int j=1;j<10;j++) {
				Coordonne c = new Coordonne(j, i);
				Case a = new Case(c, this);
				this.AjCase(a);
			}
		}
		this.GenererArme(10);
	return pt;	
	}
	
	public Case SelectionerCase(Coordonne c) {
		Case a = null;
		List<Case> r = this.getPt();
		if (r != null) {
		for (Case b : r)
		{
			if (b.getPosition().getX() == c.getX() && b.getPosition().getY() == c.getY()) {
				a = b ;
			}	
		}
		}
		return a;
	}
	public String AfficherCase(Coordonne c) {
		String a ="";
		Case b = this.SelectionerCase(c);
		a = a + "["+ b.getPosition().getX()+"/"+b.getPosition().getY()+"]";
		if (b.getOccupant() != null) {
			a = a +" est occupé par "+ b.getOccupant().getNom();
			a = a +"Point de vie : "+ b.getOccupant().getVie()+ "Point de mouvement : "+ b.getOccupant().getMouvement();
			if (b.getOccupant().getMaPossession() != null)
			{
				a  = a +"\n il détient "+ b.getOccupant().getMaPossession().getNom();
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
	public void remplissageInit() {
		
	}
	public String afficherPlateau() {
		
		String a = "__________________________________________________________________________________";
		List<Case> r = this.getPt();
		int i = 1;

		for (Case b : r)
		{
			if(b.getPosition().getX() == 1) {
				a = a + "\n|" ;
			}
			if(b.getOccupant() != null) {
			a = a +" ["+b.getOccupant().getInitial()+"] ";
			}
			else {
				a = a +" [     ] ";
			}
			if(b.getPosition().getX() == 9) {
				a = a + "|" ;
			}
		}
		a = a  +"\n__________________________________________________________________________________";
		return a;
	}
	public void AnnoncerTour() {
		System.out.println("C'est au tour du camp "+this.getTour());
	}
	public void GenererArme(int n) {
		StockArmes a = new StockArmes();
		for(int i = 0;i<n;i++) {
			int min = 1;
			int max = 2;
			
			Random random = new Random();
			int type = random.nextInt(max + min);
			int qualite = random.nextInt(0+4);
			
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
	
}