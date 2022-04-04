package protagonistes;

import affrontement.Bataille;

public class Dragon extends EtreVivant{

	public Dragon(String n) {
		super(n);
		vie = 200;
		nbBoule = 10;
	}
	private int nbBoule;
	public String rejointBataille(Bataille b) {
		this.bataille = b;
		b.ajouter(this);
		if (b.donnerNombreDragons() == 1) {
		return "Le dragon "+this.nom+" fut le premier à arriver sur le champ de bataille.";
		}
		else
		{
			return "Le dragon "+this.nom+" arrive sur le champ de bataille.";
		}
	}
	public String mourir() {
		String a = " c’est ainsi que le dragon " + this.getNom()+" mourut";
		return a + "\n "+ this.bataille.eliminer(this);
	}
	public String cracherBouleFeu(Homme h) {
		if(this.nbBoule > 0 ) {
			this.nbBoule--;
			h.setVie(h.getVie()-100);
			if (h.getVie() < 0){
				h.setVie(0);
			}
			String a = this.getNom() + " crache une boule de feu sur "+h.getNom();
			if (h.getVie() == 0) { 
				a = a + " trop violente pour survivre"; 
				a = a + h.mourir();
			}
			else {
				a = a +" mais il se relève";
			}
			return a;
		}
		return this.getNom()+" n'a plus de feu ,"+h.getNom()+" a eu beaucoup de chance !";
	}
}
