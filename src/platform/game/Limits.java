package platform.game;

import platform.util.Box;
import platform.game.Actor;
import platform.util.Vector;


/**
 * Limites du jeu, en dehors desquelles le joueur meurt
 */
public class Limits extends Actor {
	private Box box;
	
	public Limits(Box nBox){
		box = nBox;
	}
	
	@Override
	public void interact(Actor other){
		//si les boites des limites et du joueur ne sont plus en contact, le joueur subit des dégâts
		if (!getBox().isColliding(other.getBox())) {
			other.hurt(this, Damage.VOID, Double.POSITIVE_INFINITY, Vector.ZERO);
		}
	}
	
	@Override
	public Box getBox(){
		return box;
	}
	
	@Override
	public int getPriority(){
		//priorité très grande pour interagir avec le joueur
		return 66666;
	}
}
