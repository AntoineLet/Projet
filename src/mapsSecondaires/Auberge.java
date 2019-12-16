package mapsSecondaires;
import java.awt.BorderLayout;
import java.util.Random;

import javax.swing.*;

import org.newdawn.slick.*;
import org.newdawn.slick.tiled.TiledMap;

import stuff.*;
import main.*;


enum Direction {
	UP, DOWN, RIGHT, LEFT
}

public class Auberge extends BasicGame{
	
	private TiledMap auberge;
	private int tilesSize;
	
	private double xCamera,yCamera;
	private int mapX,mapY;
	private Image combattant;
	private int combattant_caseX;
	private int combattant_caseY;
	private int combattant_movingStep;
	private int combattant_size;
	
	private Animation animEn;
	private Animation anim_UP;
	private Animation anim_DOWN;
	private Animation anim_RIGHT;
	private Animation anim_LEFT;
	private Direction direction;
	private float x = 500, y = 900;
	
	private Music music;

	public Auberge(String titre) {
		super(titre);
	}

	@Override
	public void render(GameContainer arg0, Graphics arg1) throws SlickException {
		
		auberge.render(0,0);
		
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
			anim_RIGHT.draw(posX, posY,combattant_size, combattant_size);
			break;
		case LEFT:
			anim_LEFT.draw(posX, posY);
			break;
		}
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
		
		this.auberge = new TiledMap("./maps/testAuberge2.tmx");
		System.out.println("Ceci est un test");	
		
		music = new Music("./sound/Auberge.wav");
		music.play();
		music.setVolume(0.05f);
		music.loop();
		
		tilesSize = 32;
		combattant_size = tilesSize;
		combattant_movingStep = 1;
		combattant_caseX = 15;
		combattant_caseY = 22;			
		combattant = new Image("./sprites/test.png");
		
		anim_UP = getAnimation(2);
		anim_LEFT = getAnimation(1);
		anim_DOWN = getAnimation(0);
		anim_RIGHT = getAnimation(3);
		direction = Direction.RIGHT;
	}

	@Override
	public void update(GameContainer gc, int i) throws SlickException {
		
		int indexCalque = auberge.getLayerIndex("Mur");
		int posX = tilesSize;
		int posY = tilesSize;
		Input input = gc.getInput();
		anim_RIGHT.update(i);
		anim_LEFT.update(i);
		anim_UP.update(i);
		anim_DOWN.update(i);
		
		
		if (input.isKeyPressed(Input.KEY_RIGHT)) {
			
			System.out.println("Perso combattant_caseX:" + combattant_caseX + "combattant_caseY:" + combattant_caseY);
			direction = Direction.RIGHT;
			if (auberge.getTileId(combattant_caseX+1, combattant_caseY , indexCalque) == 0) {
				combattant_movingStep = 1;
				//xCamera -= 5f;
				combattant_caseX += combattant_movingStep;
			}
		} else if (input.isKeyPressed(Input.KEY_LEFT)) {
			
			
			System.out.println("Perso combattant_caseX:" + combattant_caseX + " imgProfessor_caseY:" + combattant_caseY);
			direction = Direction.LEFT;
			if (auberge.getTileId(combattant_caseX - 1,combattant_caseY, indexCalque) == 0) {
				//xCamera += 5f;
				combattant_caseX -= combattant_movingStep;
			}
		} else if (input.isKeyPressed(Input.KEY_UP)) {
			
			System.out.println("Perso combattant_caseX:" + combattant_caseX + " combattant_caseY:" + combattant_caseY);
			direction = Direction.UP;
			if (auberge.getTileId( combattant_caseX,combattant_caseY - 1, indexCalque) == 0) {
				combattant_movingStep = 1;
				//yCamera +=5f;
				combattant_caseY -= combattant_movingStep;
				
			}
		} else if (input.isKeyPressed(Input.KEY_DOWN)) {
			
			System.out.println("Perso combattant_caseX:" + combattant_caseX + " combattant_caseY:" + combattant_caseY);
			direction = Direction.DOWN;
			if (auberge.getTileId(combattant_caseX, (int) combattant_caseY + 1, indexCalque) == 0) {
				combattant_movingStep = 1;
				//yCamera -= 4f;
				combattant_caseY += combattant_movingStep;
				
			}
	        
	 }
		else if(input.isKeyPressed(Input.KEY_A)) {
			Fenetreaubergiste();
		}
		
		
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
	
	
	public void Fenetreaubergiste() {
		//ouvre la boutique.
		
		Random rand = new Random();
		int item1 = rand.nextInt(100-1)+1;
		Stuff stuff1 = ItemRandom(item1);
		
		int item2 = rand.nextInt(100-1)+1;
		Stuff stuff2 = ItemRandom(item2);
		
		int item3 = rand.nextInt(100-1)+1;
		Stuff stuff3 = ItemRandom(item3);

		JFrame frame = new JFrame("Auberge");
		JPanel panel1 = new JPanel(new BorderLayout());
		JButton button1 = new JButton(stuff1.getTypeStuff());
		JButton button2 = new JButton(stuff2.getTypeStuff());
		JButton button3 = new JButton(stuff3.getTypeStuff());
		panel1.add(button1,BorderLayout.WEST);
		panel1.add(button2,BorderLayout.CENTER);
		panel1.add(button3,BorderLayout.EAST);
		frame.setSize(360,240);
		frame.getContentPane().add(panel1,BorderLayout.CENTER);
		frame.setVisible(true);
		
		button1.addActionListener(new MonListener());
	}
	
	public Stuff ItemRandom(int item) {
		
		TypeEquip equip;
		if(item<10) {
			Stuff stuff = new Stuff("Stuff en Diamant",15,50);
			return stuff;
		}
		else if(item<30) {
			Stuff stuff = new Stuff("Stuff en Fer",10,30);
			return stuff;
		}
		else if(item<75) {
			Stuff stuff = new Stuff("Stuff en Maille",5,15);
			return stuff;
		}
		else{
			Stuff stuff = new Stuff("Stuff en Cuir",3,5);
			return stuff;
		}
	}
	
	/*private void ChangeMap() throws SlickException {

		int largeurAffichage = 1920;
		int hauteurAffichage = 1080;
		boolean siPleinEcran = false;

		AppGameContainer app = new AppGameContainer (new Jeu("Oskour"));
		//app.setDisplayMode(largeurAffichage, hauteurAffichage, siPleinEcran);
		app.setDisplayMode(largeurAffichage, hauteurAffichage, siPleinEcran);

		app.start();
		
		
	}*/
	
	
	
	
}
