/*
 *	Author:      Samuel Chassot
 *	Date:        28 nov. 2016
 */


package platform.game;

import platform.util.Box;
import platform.util.Input;
import platform.util.Sprite;
import platform.util.Output;
import platform.util.Vector;
import platform.game.Signal;


public class Torch extends Actor implements Signal{
	private final double SIZE = 0.8;
	private Vector position;
	private boolean lit;
	private double variation;
	
	public Torch(Vector pos, boolean lit){
		if (pos == null){
			throw new NullPointerException();
		}
		position = pos;
		this.lit = lit;
		}
	
	@Override
	public int getPriority(){
		return 32;
	}
	
	@Override
	public void draw(Input input, Output output){
		super.draw(input, output);
		String name;
		if (lit){
			name = "torch.lit.1";
			if (variation < 0.3){
				name = "torch.lit.2";
			}
		}
		else{
			name = "torch";
		}
		output.drawSprite(this.getSprite(name), getBox());
	}
	
	@Override
	public Box getBox(){
		return new Box(position, SIZE, SIZE);
	}

	
	@Override
	public boolean hurt(Actor instigator, Damage type, double amount, Vector location){
		
		switch (type){
			case FIRE:
				if (lit){
					return false;
				}
				else{
					lit = true;
					return true;
				}
				
			case AIR:
				lit = false;
				return true;
			default:
				return super.hurt(instigator, type, amount, location);
		}
	}
	
	@Override
	public void update(Input input){
		super.update(input);
		variation -= input.getDeltaTime();
		if(variation <0.0){
			variation = 0.6;
		}
	}
	
	@Override
	public boolean isActive(){
		return lit;
	}
}
