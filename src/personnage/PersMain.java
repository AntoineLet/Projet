package personnage;

import stuff.*;

public class PersMain extends Personnage{


	private int lvl;
	
	public PersMain( ) {
		
		lvl = 0;
		xp = 0;
		pv = 0;
		nom = "";
		or = 0;
		defense = 0; 
		
	} 
	
	public PersMain(int atk, int def, int lvl, float xp, int pv, String nom, int or) {
		attaque = atk;
		defense = def;
		this.lvl = lvl;
		this.xp = xp;
		this.pv = pv;
		this.nom = nom;
		this.or = or;
	}
	

	public int getLvl() {
		return lvl;
	}


	public void setLvl(int lvl) {
		this.lvl = lvl;
	}


	public float getXp() {
		return xp;
	}


	public void setXp(int xp) {
		this.xp = xp;
	}
	
	public void affStat()
	{
		System.out.println("Nom du héros: " + nom);
		System.out.println("Lvl du héros: " + lvl);
		System.out.println("Défense du héros: " + defense);
		System.out.println("Attaque du héros: " + attaque);
		System.out.println("Le héros a: " + pv + " points de vie");
	}
	
	public void fuite() {
		or -= (int)(Math.random()*(10-1)+1);
		if (or<0) {
			or =0;
			pv -= 5;
		}
	}
	 
	public void victoire(Ennemi ennemi) {
		or += ennemi.getOr();
	}
	public void niveau(int xp) {
		
		this.xp += xp;
		int xpNec = 100+(100*lvl);
		if (xp >= xpNec) {
			xp = xp-xpNec;
			lvl++;
		}
	}
}
 