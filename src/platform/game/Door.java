package platform.game;

import platform.game.Block;
import platform.game.Signal;
import platform.util.Box;
import platform.util.Sprite;
import platform.util.Input;
import platform.util.Output;


/**
 * bloc qui peut disparaître associé à une clef
 * le signal true signifie la disparition du bloc et inversément
 */
public class Door extends Block implements Signal {
	//signal auquel réagit le bloc
	private final Signal signal;
	//état d'activité du bloc : existant ou disparu
	private boolean active;
	
	public Door(Box nBox, Signal nSignal, Sprite nSprite){
		super(nBox, nSprite);
		signal = nSignal;
		active = false;
	}
	
	@Override
	public boolean isActive(){
		return active;
	}
	
	@Override
	public void draw(Input input, Output output){
		//si le signal n'est pas true, on affiche le bloc
		if (!signal.isActive()){
			super.draw(input, output);	
			
		}
	}
	
	@Override
	public boolean isSolid(){
		//si le bloc a disparu, il n'est plus solide sinon il l'est
		if (signal.isActive()) {
			return false;
		}
		else {
			return true;
		}
	}
}
