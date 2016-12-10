package platform.game;

import platform.util.Box;
import platform.util.Input;
import platform.util.Sprite;
import platform.util.Output;
import platform.util.Vector;

public class Heart extends Actor {
	private final double SIZE = 0.5;
	private Vector position;
	//compteur
	private double countdown;
	//valeur max du compteur
	private final double COOLDOWN;
	
	
	public Heart(Vector pos){
		position = pos;
		COOLDOWN = 10.0;	
	}
	
	@Override
	public void update(Input input) {
		if(countdown > -1.0){
			//diminution du compteur s'il est positif jusqu'à ce qu'il soit négatif
			countdown -= input.getDeltaTime();
		}
	}
	
	@Override
	public Box getBox() {
		return new Box(position, SIZE, SIZE);
	}
	
	@Override 
	public int getPriority() {
		//priorité supérieure à Player pour l'interaction
		return 62;
	}
	
	@Override
	public void interact(Actor other){
		super.interact(other);
		
		//si le compteur est négatif et que le player va sur le coeur
		if (countdown <= 0 && getBox().isColliding(other.getBox())) {
			//le Player ramasse le coeur 
			if (other.hurt(this, Damage.HEAL, 2.0 ,position)){
				//remise du compteur au max donc disparition du coeur
				countdown = COOLDOWN;	
			}
		}
	}
	
	@Override
	public void draw(Input input, Output output){
		Sprite sprite = this.getSprite("heart.full");
		
		//si le compteur est négatif, on affiche le coeur
		if(sprite != null && countdown <= 0.0){
			super.draw(input, output);
			output.drawSprite(sprite, getBox());
		}
	}
}

