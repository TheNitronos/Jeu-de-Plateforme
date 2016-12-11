package platform.game;

import platform.game.Signal;

/**
 * classe qui représente le "or" de 2 signaux
 * dès qu'un signal est true, le signal des 2
 * devient true
 */
public class Or implements Signal{
	private final Signal SIGNAL1;
	private final Signal SIGNAL2;
	
	public Or(Signal nSignal1, Signal nSignal2){
		if (nSignal1 == null || nSignal2 == null){
			throw new NullPointerException();
		}
		
		SIGNAL1 = nSignal1;
		SIGNAL2 = nSignal2;
	}
	
	@Override
	public boolean isActive() {
		return SIGNAL1.isActive() || SIGNAL2.isActive();
	}
}