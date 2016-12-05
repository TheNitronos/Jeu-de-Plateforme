package platform.game;

import platform.util.Box;
import platform.util.Input;
import platform.util.Sprite;
import platform.util.Output;
import platform.util.Vector;

public class Heart extends Actor{
	private final double SIZE = 0.5;
	private double cooldown;
	private Vector position;
	private double countdown;
	
	public Heart(Vector pos){
		position = pos;
		cooldown = 10.0;	
	}
	
	@Override
	public void update(Input input){
		if(countdown >0.0){
			countdown -= input.getDeltaTime();
		}
		else{
			countdown = 0.0;
		}
	}
	
	@Override
	public Box getBox(){
		return new Box(position, SIZE, SIZE);
	}
	
	@Override 
	public int getPriority(){
		return 62;
	}
	@Override
	public void interact(Actor other){
		super.interact(other);
		if (countdown <= 0 && getBox().isColliding(other.getBox())){
			if (other.hurt(this, Damage.HEAL, 2.0 ,position)){
				countdown = cooldown;
				
			}
			
		}
	}
	
	@Override
	public void draw(Input input, Output output){
		Sprite sprite = this.getSprite("heart.full");

		if(sprite != null && countdown <= 0.0){
			super.draw(input, output);
			output.drawSprite(sprite, getBox());
		}
	}
}

