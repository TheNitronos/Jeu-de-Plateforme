/*
 *	Author:      Samuel Chassot
 *	Date:        30 nov. 2016
 */


package platform.game;


import platform.game.Signal;

public class Not implements Signal{
	
	private final Signal signal;
	
	public Not(Signal signal){
		if (signal == null){
			throw new NullPointerException();
		}
		this.signal = signal;
	}
	
	@Override
	public boolean isActive(){
		return !signal.isActive();
	}
	
}
