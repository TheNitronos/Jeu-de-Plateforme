package platform.game;

import platform.util.Vector;
import platform.util.Box;
import platform.game.Color;
import platform.util.Input;
import platform.util.Output;

/**
 * clef à ramasser pour débloquer d'autres acteurs
 */
public class Key extends Actor implements Signal{
	private final double SIZE = 1.0;
	private Vector position;
	//clef prise ou pas (true ou false)
	private boolean taken;
	private final Color COLOR;
	private String name;
	
	public Key(Vector nPosition, Color nColor){
		position = nPosition;
		COLOR = nColor;
		taken = false;
	}
	
	@Override
	public void draw(Input input, Output output){
		if (!taken){
			super.draw(input, output);
			
			//choix de la couleur de la clef selon l'énumération de couleurs
			switch(COLOR){
				case GREEN:
					name = "key.green";
					break;
					
				case YELLOW:
					name = "key.yellow";
					break;
					
				case BLUE:
					name = "key.blue";
					break;
					
				case RED:
					name = "key.red";
					break;
					
				default:
					name = "key.green";
			}
			
			output.drawSprite(getSprite(name), getBox());
		}
	
	}
	
	@Override
	public Box getBox() {
		/*
		 * si la clef n'a pas été prise, il y a une Box
		 * si oui, la Box est à null
		 */
		if (!taken) {
			return new Box(position, SIZE, SIZE);
		} else {
			return null;
		}
	}
	
	@Override
	public int getPriority() {
		//priorité supérieure à Player pour l'interaction
		return 122;
	}
	
	@Override
	public void interact(Actor other) {
		super.interact(other);
		
		//si le Player prend la clef le booléen passe à true et disparaît
		if (other instanceof Player && other.getBox().isColliding(getBox())) {
			taken = true;
			getWorld().unregister(this);
		}
	}
	
	@Override 
	public boolean isActive() {
		return taken;
	}
}
