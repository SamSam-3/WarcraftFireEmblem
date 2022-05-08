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
		
		Bataille q = new Bataille();
		Clavier c = new Clavier();
		System.out.println("Voulez vous démarrez une nouvelle partie ?");
		System.out.println("1 - Nouvelle Partie");
		System.out.println("2 - Reprise de la dernière partie");
		int a = 0 ;
		while((a != 1 || a!= 2)) {
		a = c.entrerClavierInt();
		
		if (a== 1)
			{
			System.out.println("Intialisation de la nouvelle Partie");
			q.initialisation();
			q.remplissageInit();
			break;
			}
		else if(a== 2) {
			System.out.println("Reprise de la sauvegarde");
			Path chemin = Paths.get("sauvegarde.txt");
			q.reprise(chemin);
			break;
			
		}
		System.out.println("Veuillez rentrer un chiffre valide");
		}
		
		
		q.passerletour();
		
	
		while(true) {
		
		System.out.println(q.afficherPlateau());
		q.AnnoncerTour();
		System.out.println("Selectioner une case au format X/Y ou sauvegarder");
		String Action = c.entrerClavierString();
		System.out.println(Action);
		if (Action.contains("sauvegarder")) {
			
			q.sauvegarder();
			System.out.println("sauvegarde faite");
		}
		else {
			int x =  Integer.valueOf(Action.substring(0,1));
			//System.out.println("x : "+ x);
			int y =  Integer.valueOf(Action.substring(2,3));
			//System.out.println("y : "+ y);
			Coordonne cor = new Coordonne(x,y);
			System.out.println(q.AfficherCase(cor));
			if (q.SelectionerCase(cor).getOccupant() != null )
			{
				if(q.getPersoActif().getDisponible()) {
					
				
				System.out.println("1 - Deplacer ");
				System.out.println("2 - Inventaire ");
				System.out.println("3 - Retour ");
				int Aperso = c.entrerClavierInt();
				switch(Aperso){
					case 1 :
						System.out.println(q.getPersoActif().AfficherDisp());
						String cible = c.entrerClavierString();
						int xCible =  Integer.valueOf(cible.substring(0,1));
						int yCible =  Integer.valueOf(cible.substring(2,3));
						Coordonne corCible = new Coordonne(xCible,yCible);
						q.getPersoActif().sedeplacer(corCible);
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
		if(q.isTourJouee() == true) {
		q.passerletour();
	}
		}
		}



}