/*
 *	Author:      Samuel Chassot
 *	Date:        30 nov. 2016
 */


package platform.game;

import platform.game.Signal;

public class And implements Signal{
	
	private final Signal signal1;
	private final Signal signal2;
	
	public And(Signal signal1, Signal signal2){
		if (signal1==null || signal2 == null){
			throw new NullPointerException();
		}
		this.signal1 = signal1;
		this.signal2 = signal2;
	}
	
	@Override
	public boolean isActive(){
		return signal1.isActive() && signal2.isActive();
	}
}
