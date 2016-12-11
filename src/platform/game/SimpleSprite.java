package platform.game;

import platform.util.Vector;
import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.game.Actor;

/**
 * Classe abstraite prévue pour afficher des images
 * simples qui n'ont pas forcément d'interactions
 */
public abstract class SimpleSprite extends Actor {
	//position fixe
	private final Vector POSITION;
	//priorité fixe
	private final int PRIORITY;
	//taille fixe
	private final double SIZE;
	//si l'image tourne ou pas
	private final boolean SPINING;
	//si l'image s'estompe ou pas
	private boolean cooldownBool;
	//compteur d'estompage
	private double cooldown;
	//nom de l'image à afficher
	private String sprite;
	
	//constructeur pour une image qui s'estompe
	public SimpleSprite(String nSprite, Vector nPos, Double nCoold, double nSize, boolean nSpin, int nPriority) {
		POSITION = nPos;
		cooldown = nCoold;
		SIZE = nSize;
		SPINING = nSpin;
		sprite = nSprite;
		PRIORITY = nPriority;
		
		cooldownBool = true;
	}
	//constructeur pour une image éternelle :)
	public SimpleSprite(String nSprite, Vector nPos, double nSize, boolean nSpin, int nPriority) {
		POSITION = nPos;
		SIZE = nSize;
		SPINING = nSpin;
		sprite = nSprite;
		PRIORITY = nPriority;
		
		cooldownBool = false;
	}
	
	@Override
	public int getPriority() {
		return PRIORITY;
	}
	
	@Override
	public void draw(Input in, Output out) {
		super.draw(in, out);
		
		if (sprite != null) {
			//si l'image est tournante, on la fait tourner sinon elle reste fixe
			if (SPINING) {
				out.drawSprite(super.getSprite(sprite), getBox(), in.getTime()*15);
			} else {
				out.drawSprite(super.getSprite(sprite), getBox());
			}
		}
	}
	
	@Override
	public Box getBox() {
		return new Box(POSITION, SIZE, SIZE);
	}
	
	@Override
	public void update(Input input) {
		//update pour le compteur et la disparition
		if (cooldownBool) {
			cooldown -= input.getDeltaTime();
			
			if(cooldown < 0.0) {
				//suppression de la sprite et affichage de la fumée
				getWorld().unregister(this);
				getWorld().register(new Smoke(POSITION, SIZE));
			}	
		}
	}
	
	public double getCooldown() {
		return cooldown;
	}
}
