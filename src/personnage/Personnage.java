package personnage;

import stuff.*;

public class Personnage {

	protected float pv;
	protected int defense;
	protected int attaque;
	protected String nom;
	protected int or;
	protected float xp;
	
	public Personnage() {
	
	}
	
	public void updateStuff() {
		
	}

	public float getPv() {
		return pv;
	}

	public void setPv(float pv) {
		this.pv = pv;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public int getAttaque() {
		return attaque;
	}

	public void setAttaque(int attaque) {
		this.attaque = attaque;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getOr() {
		return or;
	}

	public void setOr(int or) {
		this.or = or;
	}

	
}
