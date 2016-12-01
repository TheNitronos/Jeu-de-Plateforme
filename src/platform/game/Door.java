/*
 *	Author:      Samuel Chassot
 *	Date:        30 nov. 2016
 */


package platform.game;

import platform.game.Block;
import platform.game.Signal;
import platform.util.Vector;
import platform.util.Box;
import platform.util.Sprite;
import platform.util.Input;
import platform.util.Output;


public class Door extends Block implements Signal {
	
	private final Signal signal;
	private final double SIZE = 1.0;

	private boolean active;
	
	
	public Door(Box box, Signal signal, Sprite sprite){
		super(box, sprite);
		this.signal = signal;
		active = false;

		
	}
	
	@Override
	public Box getBox(){
		return super.getBox();
	}
	
	@Override
	public boolean isActive(){
		return active;
	}
	@Override
	public void draw(Input input, Output output){
		
		if (!signal.isActive()){
			super.draw(input, output);			
		}
	}
	
	@Override
	public boolean isSolid(){
		if (signal.isActive()){
			return false;
		}
		else{
			return super.isSolid();
		}
	}
		
	
}
