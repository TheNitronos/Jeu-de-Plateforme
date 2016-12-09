package platform.game;

import platform.util.Vector;
import platform.util.Box;
import platform.util.Input;
import platform.util.Output;

public class Lever extends Actor implements Signal {
	private Vector position;
	private final double SIZE = 1.0;
	private boolean value;
	private double time;
	private double duration;
	
	public Lever(Vector pos, double duration){
		position = pos;
		value = false;
		this.duration = duration;
		
	}
	
	@Override
	public Box getBox(){
		return new Box(position, SIZE, SIZE);
	}
	
	@Override
	public int getPriority(){
		return -1;
	}
	
	@Override
	public boolean isActive(){
		return value;
	}
	
	@Override
	public void draw(Input input, Output output){
		super.draw(input, output);
		String name;
		if(!value){
			name = "lever.left";		//a gauche c'est inactif
			
		}
		else{
			name = "lever.right";
		}
		output.drawSprite(getSprite(name), getBox());
	}
	
	@Override
	public void update(Input input){
		
		if (time < 0.0){
			value = false;
		}
		else{
			time -= input.getDeltaTime();
		}
	}
	
	@Override
	public boolean hurt(Actor instigator, Damage type, double amount, Vector location){
		
		switch(type){
			case ACTIVATION:
				value = !value;
				time = duration;
				return true;
			default:
				return super.hurt(instigator, type, amount, location);
		}
	}
}
