package main;
import personnage.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;



public class Main {
	
	public static void main(String[] args) {
		
		int largeurAffichage = 1920;
		int hauteurAffichage = 1080;
		boolean siPleinEcran = false;
		
		
		
		
		//Créer la map avec une certaine taille d'affichage
		try {
			AppGameContainer app = new AppGameContainer (new Jeu("Oskour"));
			app.setDisplayMode(largeurAffichage, hauteurAffichage, siPleinEcran);
			app.start();
		}catch (SlickException ex) {
			Logger.getLogger(Main.class.getName()) .log(Level.SEVERE,null,ex);
			
	}
		
	}

}
