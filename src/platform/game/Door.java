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

public class Door extends Block implements Signal {
	
	private final Signal signal;
	private final double SIZE = 1.0;
	private Box box;
	private boolean active;
	
	
	public Door(Box box, Signal signal, Sprite sprite){
		super(box, sprite);
		this.signal = signal;
		this.box = box;
		active = false;

		
	}
	
	@Override
	public Box getBox(){
		return box;
	}
	
	@Override
	public boolean isActive(){
		return active;
	}
	
	
}
