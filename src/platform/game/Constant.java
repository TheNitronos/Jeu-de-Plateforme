package platform.game;

import platform.game.Signal;

public class Constant implements Signal {
	private final boolean VALUE;
	
	public Constant(boolean nValue){
		VALUE = nValue;
	}
	
	@Override
	public boolean isActive(){
		return VALUE;
	}
}
