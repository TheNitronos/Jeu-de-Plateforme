package platform.game;

import platform.util.Box;
import platform.util.Input;
import platform.util.Sprite;
import platform.util.Output;
import platform.util.Vector;

/**
 * piques qui infligent de la perte de vie et qui
 * font sauter le player de douleur
 */
public class Spike extends Actor {
	private final double SIZE = 1.0;
	private final Vector POSITION;
	
	public Spike(Vector nPosition) {
		POSITION = nPosition;
	}
	
	@Override
	public int getPriority() {
		//haute priorité pour interagir avec le joueur
		return 333;
	}
	
	@Override
	public Box getBox() {
		//la hauteur est deux foix plus petite que la largeur
		return new Box(POSITION, SIZE, SIZE/2);
	}
	
	@Override
	public void draw(Input input, Output output) {
		Sprite sprite = this.getSprite("spikes");
		
		if (sprite != null) {
			output.drawSprite(sprite, getBox());
		}
	}
	
	@Override
	public void interact(Actor other) {
		//si un acteur tombe verticalement sur les piques, il subit des dégâts physiques
		if(other.getVelocityY() < -1 && getBox().isColliding(other.getBox())) {
			other.hurt(this, Damage.PHYSICAL, 2.0 , POSITION);
		}	
	}
}
