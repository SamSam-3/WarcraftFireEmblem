package vue;

public class Ecran implements ILivre {
	@Override
	public void ecrire(String texte) {
		System.out.print(texte);
	}
}
