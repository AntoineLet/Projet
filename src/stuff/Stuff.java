package stuff;


public class Stuff {
	
	private String typeStuff;
	private int valDef;
	private int prix;
	
	public Stuff() {
		typeStuff = "";
		valDef = 0;
	}
	
	public Stuff(String typeStuff, int valDef,int prix) {
		this.typeStuff = typeStuff;
		this.valDef = valDef;
		this.prix = prix;
	}

	public int getPrix() {
		return prix;
	}

	public void setPrix(int prix) {
		this.prix = prix;
	}

	public String getTypeStuff() {
		return typeStuff;
	}

	public void setTypeStuff(String typeStuff) {
		this.typeStuff = typeStuff;
	}

	
	public int getValDef() {
		return valDef;
	}
	public void setValDef(int valDef) {
		this.valDef = valDef;
	}
	
	
}
