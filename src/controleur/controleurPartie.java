package controleur;

import vue.Clavier;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import affrontement.Bataille;
import plateau.Coordonne;
public class controleurPartie {
	private Bataille p;
	public static void main(String[] args) throws IOException {
		controleurPartie p = new controleurPartie();
		p.Start();
		

	}
	
	
	public void Start() throws IOException {
		
		Bataille q = new Bataille(); //creation bataille
		Clavier c = new Clavier(); //entrée clavier
		System.out.println("Voulez vous démarrez une nouvelle partie ?");
		System.out.println("1 - Nouvelle Partie");
		System.out.println("2 - Reprise de la dernière partie");
		int a = 0 ;
		while((a != 1 || a!= 2)) {
		a = c.entrerClavierInt(); //lecture clavier
		
		if (a== 1) //si nouvelle partie
			{
			System.out.println("Intialisation de la nouvelle Partie");
			q.initialisation(); //initalisation plateau
			q.remplissageInit(); //remplisasge initiale d'une nouvelle partie
			break;
			}
		else if(a== 2) {
			System.out.println("Reprise de la sauvegarde");
			Path chemin = Paths.get("sauvegarde.txt"); 
			q.reprise(chemin); //reprise sur fichier de sauvegarde
			break;
			
		}
		System.out.println("Veuillez rentrer un chiffre valide");
		}
		
		
		q.passerletour(); //initalisation de l'activité des perso
		
	int stop = 0;
	while(stop == 0) { //boucle de jeu
		
		System.out.println(q.afficherPlateau()); //affichage plateau
		q.AnnoncerTour();
		System.out.println("Selectioner une case au format X/Y ou sauvegarder");
		String Action = c.entrerClavierString(); 
		System.out.println(Action);
		if (Action.contains("sauvegarder")) {
			
			q.sauvegarder(); //sauvegarde de la partie
			System.out.println("sauvegarde faite");
		}
		else {
			int x =  Integer.valueOf(Action.substring(0,1));
			//System.out.println("x : "+ x);
			int y =  Integer.valueOf(Action.substring(2,3));
			//System.out.println("y : "+ y);
			Coordonne cor = new Coordonne(x,y);
			System.out.println(q.AfficherCase(cor)); //affichage des détails de la case sélectionné par coordonnée
			if (q.SelectionerCase(cor).getOccupant() != null )
			{
				if(q.getPersoActif().getDisponible()) {
					
				
				System.out.println("1 - Deplacer ");
				System.out.println("2 - Inventaire ");
				System.out.println("3 - Retour ");
				int Aperso = c.entrerClavierInt();
				switch(Aperso){
					case 1 :
						System.out.println(q.getPersoActif().AfficherDisp()); //afficher tout les cases possible ou se deplacer et s'il y a quelqu'un dessus
						String cible = c.entrerClavierString(); //lecteur de la coordonnée
						int xCible =  Integer.valueOf(cible.substring(0,1));
						int yCible =  Integer.valueOf(cible.substring(2,3));
						Coordonne corCible = new Coordonne(xCible,yCible);
						q.getPersoActif().sedeplacer(corCible);  //deplacement à la coordonnée donnée
						break;
					case 2 :
						//q.getPersoActif().Inventaire()
						break;
					default :
						
						break;
				}
				}
					
				
			}
			
		}
		if(q.isTourJouee() == true) { //si l'une des actions à entrainer un tour joue 
		q.passerletour(); //le tour passe d'un camp à l'autre
		q.setTourJouee(false); //le tour devient non jouer
	}
		if(q.getVictoire() != null) { //si victoire
			stop = 1; //on sort de la boucle de jeu
			System.out.print("Victoire des "+q.getVictoire());
		}
		}
		}



}
