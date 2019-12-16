package main;


	import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
	import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.newdawn.slick.Animation;
	import org.newdawn.slick.AppGameContainer;
	import org.newdawn.slick.BasicGame;
	import org.newdawn.slick.GameContainer;
	import org.newdawn.slick.Graphics;
	import org.newdawn.slick.Image;
	import org.newdawn.slick.Input;
	import org.newdawn.slick.Music;
	import org.newdawn.slick.SlickException;
	import org.newdawn.slick.Sound;
	import org.newdawn.slick.tiled.TiledMap;

	import mapsSecondaires.Auberge;
import mapsSecondaires.MonListener;
import personnage.Ennemi;
	import personnage.PersMain;



	enum Direction {
		UP, DOWN, RIGHT, LEFT
	}

	public class Jeu extends BasicGame {
		private double xCamera,yCamera;
		private int mapX,mapY;
		private int tilesSize;
		private Image combattant;
		private Image crabe;
		private int crabe_size;
		private int crabe_caseX;
		private int crabe_caseY;
		private int crabe_movingStep;
		private int combattant_caseX;
		private int combattant_caseY;
		private int combattant_movingStep;
		private int combattant_size;
		private TiledMap map;
		private Animation animEn;
		private Animation anim_UP;
		private Animation anim_DOWN;
		private Animation anim_RIGHT;
		private Animation anim_LEFT;
		private Direction direction;
		private float x = 500, y = 900;
		private float xVision = x, yVision = y;
		
		private int largeurAffichageAuberge = 384;
		private int hauteurAffichageAuberge = 384;
		private boolean siPleinEcran = false;
		private Auberge auberge;
		private TiledMap mapAuberge;
		private Music music;
		
		private PersMain persPrinc;
		private Ennemi ennemi;
		
		
		public Jeu(String titre) {
			super(titre);
			
		}
		

		@Override
		public void render(GameContainer gc, Graphics grphcs) throws SlickException {
			//Fais le rendu de la carte
			map.render(0,0);
			
			
			int posX = combattant_caseX * tilesSize;
			int posY = combattant_caseY * tilesSize;	
			
		

			switch (direction) {
			case UP:
				anim_UP.draw(posX, posY);
				break;
			case DOWN:
				anim_DOWN.draw(posX, posY);
				break;
			case RIGHT:
				anim_RIGHT.draw(posX, posY);
				break;
			case LEFT:
				anim_LEFT.draw(posX, posY);
				break;
			}

		}

		@Override
		public void init(GameContainer gc) throws SlickException {
			
			try {
				
				//Créer le personnage principal avec ses attributs et le boss final
				persPrinc = new PersMain(5,3,0,0,50,"Pepito",10);
				ennemi = new Ennemi(persPrinc);
				
				
				
				//Choisis la map utilisée dans cette partie du jeux
				this.map = new TiledMap("maps/MapPrincipal1.tmx");
				
				
				//Ajoute de la musique qui boucle
				music = new Music("./sound/Spooky.wav");
				music.play();
				music.setVolume(0.05f);
				music.loop();
			
				//Créer le personnage et l'anime
				tilesSize = 32;
				combattant_size = tilesSize;
				combattant_movingStep = 1;
				combattant_caseX = 12;
				combattant_caseY = 29;			
				combattant = new Image("./sprites/link-petit.png");
				
				anim_UP = getAnimation(2);
				anim_LEFT = getAnimation(1);
				anim_DOWN = getAnimation(0);
				anim_RIGHT = getAnimation(3);
				direction = Direction.RIGHT;
				
				//Ennemi
				crabe = new Image("./sprites/ennemi.png");
				tilesSize = 32;
				crabe_size = tilesSize;
				crabe_movingStep = 1;
				crabe_caseX = 15;
				crabe_caseY = 10;
				animEn = getAnimationEnnemi();
				
				

			} catch (SlickException ex) {
				Logger.getLogger(Jeu.class.getName()).log(Level.SEVERE, null, ex);
				System.out.println(ex.getMessage());
			}
			
			

		}
		
		private Animation getAnimationEnnemi() {
			Animation animEn = new Animation(false);
			for (int x =0;x<9;x++) 
			{
				for(int y = 0;y<6;y++) {
					animEn.addFrame(crabe.getSubImage(x*38, (int) (y*48), 70, 70), 100);
				}
				
			}
			return animEn;
			
		}


		private Animation getAnimation(int rowY) {
			Animation anim = new Animation(false);
		//	int x=7;
		//	rowY=3;
			for (int x = 0; x < 9; x++) {
				anim.addFrame(combattant.getSubImage(x*35, rowY*39, 36,38),100);
			}
			return anim;					
		}
	@Override
		public void update(GameContainer gc, int i) throws SlickException {
		
		    	
			//Permet de donner la position du joueur à la caméra
			int w = gc.getWidth() / 2;
			if (this.combattant_caseX > this.xVision + w) {
				this.xVision = this.combattant_caseX - w;
			} else if (this.combattant_caseX < this.xVision - w) {
				this.xVision = this.combattant_caseX + w;
			}
			int h = gc.getHeight() / 2;
			if (this.combattant_caseY > this.yVision + h) {
				this.yVision = this.combattant_caseY - h;
			} else if (this.combattant_caseY < this.yVision - h) {
				this.yVision = this.combattant_caseY + h;
			}
		 
		    //
			int indexCalque = map.getLayerIndex("Murs");
			int posX = tilesSize;
			int posY = tilesSize;
			Input input = gc.getInput();
			anim_RIGHT.update(i);
			anim_LEFT.update(i);
			anim_UP.update(i);
			anim_DOWN.update(i);
			int indexChange = map.getLayerIndex("Changement");
			
			//permet de détecter quand il change de map
			if (map.getTileId(combattant_caseX, combattant_caseY, indexChange)!=0)
			{
				changeMap();
			}
			
			/* for (int objectID = 0; objectID < map.getObjectCount(0); objectID++) {
			        if (x > map.getObjectX(0, objectID)
			                && x < map.getObjectX(0, objectID) + map.getObjectWidth(0, objectID)
			                && y > map.getObjectY(0, objectID)
			                && y < map.getObjectY(0, objectID) + map.getObjectHeight(0, objectID)) {
			            if ("teleport".equals(map.getObjectType(0, objectID))) {
			                x = Float.parseFloat(map.getObjectProperty(0, objectID, "dest-x", Float.toString(x)));
			                y = Float.parseFloat(map.getObjectProperty(0, objectID, "dest-y", Float.toString(y)));
			            } 
			        }
			        
			 }*/
			
			Random rand = new Random();
			//Déplacement
			if (input.isKeyPressed(Input.KEY_RIGHT)) {
				
				System.out.println("Perso combattant_caseX:" + combattant_caseX + "combattant_caseY:" + combattant_caseY + "   getTileId:"+map.getTileId((int) combattant_caseX, (int) combattant_caseY - 1, indexCalque));
				direction = Direction.RIGHT;
				
				if (map.getTileId((int) combattant_caseX+1, (int) combattant_caseY , indexCalque) == 0) {
					combattant_movingStep = 1;
					//xCamera -= 5f;
					combattant_caseX += combattant_movingStep;
					int spawn = rand.nextInt(100-1)+1;
					if (spawn<40) {
						combat();
					}
					
				}
			} else if (input.isKeyPressed(Input.KEY_LEFT)) {
				
				
				System.out.println("Perso combattant_caseX:" + combattant_caseX + " imgProfessor_caseY:" + combattant_caseY);
				direction = Direction.LEFT;
				if (map.getTileId((int) combattant_caseX - 1, (int) combattant_caseY, indexCalque) == 0) {
					//xCamera += 5f;
					combattant_caseX -= combattant_movingStep;
					int spawn = rand.nextInt(100-1)+1;
					if (spawn<40) {
						combat();
					}
				}

			} else if (input.isKeyPressed(Input.KEY_UP)) {
				
				System.out.println("Perso combattant_caseX:" + combattant_caseX + " combattant_caseY:" + combattant_caseY);
				direction = Direction.UP;
				if (map.getTileId((int) combattant_caseX, (int) combattant_caseY - 1, indexCalque) == 0) {
					combattant_movingStep = 1;
					//yCamera +=5f;
					combattant_caseY -= combattant_movingStep;
					int spawn = rand.nextInt(100-1)+1;
					if (spawn<40) {
						combat();
					}
				}
			} else if (input.isKeyPressed(Input.KEY_DOWN)) {
				
				System.out.println("Perso combattant_caseX:" + combattant_caseX + " combattant_caseY:" + combattant_caseY);
				direction = Direction.DOWN;
				if (map.getTileId(combattant_caseX, (int) combattant_caseY + 1, indexCalque) == 0) {
					combattant_movingStep = 1;
					//yCamera -= 4f;
					combattant_caseY += combattant_movingStep;
					int spawn = rand.nextInt(100-1)+1;
					if (spawn<40) {
						combat();
					}
				}
				
			}
			

		}

		

		private void changeMap() throws SlickException {
			//permet de passer dans l'auberge
			int largeurAffichage = 1920;
			int hauteurAffichage = 1080;
			AppGameContainer app = new AppGameContainer (new Auberge("Oskour"));
			app.setDisplayMode(largeurAffichage, hauteurAffichage, siPleinEcran);

			app.start();
			
			
		}
		
		public void combat() {
			//Entre en état de combat avec le choix de fuire
			Ennemi ennemi = new Ennemi(persPrinc);
			ennemi.afficherStats();
			
			JFrame frame = new JFrame("Auberge");
			JPanel panel1 = new JPanel(new BorderLayout());
			JButton button1 = new JButton("Je combat!");
			JButton button2 = new JButton("Je fuis comm un lâche");
			panel1.add(button1,BorderLayout.WEST);
			panel1.add(button2,BorderLayout.EAST);
			frame.setSize(360,240);
			frame.getContentPane().add(panel1,BorderLayout.CENTER);
			frame.setVisible(true);
		}


		

	/*private void updateCamera(GameContainer container) {
		if (!container.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
			int w = container.getWidth() / 4;
			if (this.combattant_caseX > this.xVision + w) {
				this.xVision = this.combattant_caseX - w;
			} else if (this.combattant_caseX < this.xVision - w) {
				this.xVision = this.combattant_caseX + w;
			}
			int h = container.getHeight() / 4;
			if (this.combattant_caseY > this.yVision + h) {
				this.yVision = this.combattant_caseY - h;
			} else if (this.combattant_caseY < this.yVision - h) {
				this.yVision = this.combattant_caseY + h;
			}
		}*/
		

		
	//}
	



}
