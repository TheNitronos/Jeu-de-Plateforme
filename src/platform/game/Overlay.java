package platform.game;

import platform.util.Box;
import platform.util.Input;
import platform.util.Vector;
import platform.util.Output;

/**
 * barre de coeurs de vie située au-dessus du joueur
 */
public class Overlay extends Actor {
	//joueur concerné
	private final Player PLAYER;
	private final double SIZE = 0.25;
	
	public Overlay(Player nPlayer) {
		PLAYER = nPlayer;
	}
	
	@Override
	public void draw(Input input, Output output) {
		//rapport de vies sur 5
		double health = 5.0 * PLAYER.getHealth() / PLAYER.getHealthMax();
		//on fait 5 itérations correspondants à chacun des coeurs
		for (int i = 1 ; i <= 5 ; ++i){
			String name;
			//selon la vie, on affiche un coeur vide, demi plein ou plein
			if (health >= i) {
				name = "heart.full";
			} else if (health >= i - 0.5) {
				name = "heart.half";
			} else {
				name = "heart.empty";
			}
			//positionnement symétrique au dessus du joueur
			Vector center = getBox().add(new Vector((-3 + i)*SIZE , 1.0)).getCenter();
			Box box = new Box(center, SIZE, SIZE);
			output.drawSprite(getSprite(name), box);
		}
	}
		
	@Override
	public int getPriority(){
		//même priorité que le joueur
		return 42;
	}
	
	@Override
	public Box getBox(){
		//même boîte que le joueur
		return PLAYER.getBox();
	}
	
	@Override
	public void update(Input input){
		//si le joueur meurt ou disparait, le overlay disparait aussi
		if(PLAYER.getWorld() == null){
			getWorld().unregister(this);
		}
		if (PLAYER.getHealth() <= 0.0){
			getWorld().unregister(this);
		}
	}		
}
