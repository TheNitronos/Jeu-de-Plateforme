package platform.game;

import platform.util.Vector;
import platform.util.Box;
import platform.util.Input;
import platform.util.Output;

/**
 * levier pour actionner d'autres acteurs
 */
public class Lever extends Actor implements Signal {
	private final Vector POSITION;
	private final double SIZE = 1.0;
	//état d'activité du levier
	private boolean value;
	//compteur d'état du levier
	private double time;
	//temps d'inactivité du levier
	private final double DURATION;
	
	public Lever(Vector nPosition, double nDuration) {
		POSITION = nPosition;
		value = false;
		DURATION = nDuration;	
	}
	
	@Override
	public Box getBox(){
		return new Box(POSITION, SIZE, SIZE);
	}
	
	@Override
	public int getPriority(){
		//très petite priorité
		return -1;
	}
	
	@Override
	public boolean isActive(){
		return value;
	}
	
	@Override
	public void draw(Input input, Output output) {
		super.draw(input, output);
		String name;
		
		if (!value) {
			//le levier inactif se trouve à gauche sinon à droite
			name = "lever.left";	
		} else {
			name = "lever.right";
		}
		//dessin de la sprite correspondante
		output.drawSprite(getSprite(name), getBox());
	}
	
	@Override
	public void update(Input input){
		//si le compteur est au négatif, le levier revient à false, sinon on réduit le compteur
		if (time < 0.0){
			value = false;
		} else {
			time -= input.getDeltaTime();
		}
	}
	
	@Override
	public boolean hurt(Actor instigator, Damage type, double amount, Vector location){
		
		switch(type){
			case ACTIVATION:
				//si le levier est activé, on inverse la valeur, et on remet le compteur à DURATION
				value = !value;
				time = DURATION;
				
				return true;
				
			default:
				return false;
		}
	}
}
