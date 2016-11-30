/*
 *	Author:      Samuel Chassot
 *	Date:        30 nov. 2016
 */


package platform.game;

import platform.game.Signal;

public class Constant implements Signal {
	
	private final boolean value;
	
	public Constant(boolean val){
		value = val;
	}
	
	@Override
	public boolean isActive(){
		return value;
	}

}
