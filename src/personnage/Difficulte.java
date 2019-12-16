package personnage;


public enum Difficulte {
	
	FACILE("Facile"),
	NORMAL("Normal"),
	DIFFICILE("Difficile");
	
	private String difficulte = "";
	
	Difficulte(String difficulte){
		this.setDifficulte(difficulte);
	}

	public String getDifficulte() {
		return difficulte;
	}

	public void setDifficulte(String difficulte) {
		this.difficulte = difficulte;
	}
}
