package platform.game;

import platform.game.Signal;

/**
 * Class qui représente l'inverse d'un signal
 */
public class Not implements Signal {
	//signal correspondant
	private final Signal SIGNAL;
	
	public Not(Signal nSignal) {
		if (nSignal == null) {
			throw new NullPointerException();
		}
		
		SIGNAL = nSignal;
	}
	
	@Override
	public boolean isActive() {
		//retour de l'inverse du signal
		return !SIGNAL.isActive();
	}	
}
