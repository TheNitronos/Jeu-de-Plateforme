package platform.game;

import platform.util.Box;
import platform.util.Input;
import platform.util.Sprite;
import platform.util.Output;
import platform.util.Vector;

/**
 * trampoline pour sauter plus haut
 */
public class Jumper extends Actor {
	private final double SIZE = 1.0;
	private Vector position;
	private Sprite sprite;
	private double cooldown;

	public Jumper(Vector pos) {
		position = pos;
		cooldown = 0.0;
	}
	
	@Override
	public void update(Input input) {
		super.update(input);
		
		cooldown -= input.getDeltaTime();
		
		//affichage du trampoline compressé ou étendu selon le compteur
		if (cooldown >= 0) {
			sprite = super.getSprite("jumper.extended");
		}
		else {
			sprite = super.getSprite("jumper.normal");
		}	
	}
	
	@Override
	public int getPriority() {
		//priorité supérieure à Player pour l'interaction
		return 50;
	}
	
	@Override
	public Box getBox() {
		return new Box(position, SIZE, SIZE);
	}
	
	@Override
	public void interact(Actor other) {
		super.interact(other);
		
		//si le Player vient sur le trampoline
		if (cooldown <= 0 && getBox().isColliding(other.getBox())) {
			//le vector below se situe en dessous de la position du trampoline, d'où la soustraction
			Vector below = new Vector(position.getX(), position.getY() - 1.0);
			//dommage de type air d'intensité 10
			if (other.hurt(this, Damage.AIR, 10.0, below)){
				//remontage du compteur du trampoline étendu
				cooldown = 0.5;
			}
		}
	}
	
	@Override
	public void draw(Input in, Output out) {
		if (sprite != null){
			super.draw(in, out);
			out.drawSprite(sprite, getBox());
		}
	}
}
