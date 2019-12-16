package stuff;

public enum TypeEquip {
	
	DIAMANT("Diamants"),
	FER("Fer"),
	MAILLE("Maille"),
	CUIR("Cuir");

private String nom = "";
	
	TypeEquip(String name){
		this.nom = name;
	}

	public String toString() {
		return nom;
	}
}
