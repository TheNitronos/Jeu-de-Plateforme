package platform.game;

import platform.game.Signal;

/**
 * Signal dont l'état d'activité est toujours le même
 */
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
