package personnage;

public class Ennemi extends Personnage{
	
	private Difficulte difficulte;
	private int lvlPers;

	public Ennemi(PersMain persMain) {
		lvlPers = persMain.getLvl();
		pv = 50 + (50*(lvlPers/10));	
		attaque = 7 + (7*(lvlPers/10));
		defense = 2 + (2*(lvlPers/5));
		or = (int)(Math.random() * (10 - 1)+1);
		int xpBase = (int)(Math.random()*(20-1)+1);
		xp = xpBase + (xpBase*(lvlPers/4));
	}
	
	public void afficherStats() {
		System.out.println("Le monstre a: " + pv + " hp");
		System.out.println("Le monstre a: " + attaque + " d'attaque");
		System.out.println("Le monstre a: " + defense + " de défense");
		System.out.println("Le monstre a: " + or + " d'or sur lui");
	}

	public Difficulte getDifficulte() {
		return difficulte;
	}
	public void setDifficulte(Difficulte difficulte) {
		this.difficulte = difficulte;
	}

	public int getLvlPers() {
		return lvlPers;
	}
	public void setLvlPers(int lvlPers) {
		this.lvlPers = lvlPers;
	}
	
	public void drop(PersMain persMain) {
		persMain.setOr(or);
	}
}
