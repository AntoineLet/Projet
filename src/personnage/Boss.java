package personnage;

public class Boss extends Personnage{
	
	

	public Boss(int pv,int atk, int def) {
		this.pv = pv;
		attaque = atk;
		defense = def;
		
	}
	
	public void Berserk(int dmgSubis) {
		attaque += (dmgSubis/2);
	}
}
