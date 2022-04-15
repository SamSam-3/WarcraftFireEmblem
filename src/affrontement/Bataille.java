package affrontement;


import etreVivant.*;

public class Bataille {
	private Camps campHomme = new Camps();
	private Camps campOrc = new Camps();
	private String victoire;
	private StockArmes sk; 
	
	public void setsk(StockArmes s) {
		this.sk = s;
	}
	public StockArmes getsk() {
		return this.sk;
	}
	public void ajouter(EtreVivant etreVivant) {
		if (etreVivant instanceof Homme) {
		campHomme.ajouterEtreVivant(etreVivant);
		}
		else if(etreVivant instanceof Orc) {
			campOrc.ajouterEtreVivant(etreVivant);
		}
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
		return campHomme.nbCompagnon();
	}
	public int donnerNombreDragons() {
		return campOrc.nbCompagnon();
	}

}
