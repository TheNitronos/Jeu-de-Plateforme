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


public class Torch extends Actor{
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
		if (lit){
			Sprite sprite = this.getSprite("torch.lit.1");
		}
		else{
			Sprite sprite = this.getSprite("torch");
		}
	}
	
	@Override
	public Box getBox(){
		return new Box(position, SIZE, SIZE);
	}

	
	@Override
	public boolean hurt(Actor instigator, Damage type, double amount, Vector location){
		
		switch (type){
			case FIRE:
				lit = true;
				return true;
			case AIR:
				lit = false;
				return true;
			default:
				return super.hurt(instigator, type, amount, location);
		}
	}
}
