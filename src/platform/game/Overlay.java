/*
 *	Author:      Samuel Chassot
 *	Date:        27 nov. 2016
 */


package platform.game;

import platform.util.Box;
import platform.util.Input;
import platform.util.Vector;
import platform.util.Output;
import platform.util.Sprite;


public class Overlay extends Actor {
	private Player player;
	
	public Overlay(Player player){
		this.player = player;
	}
	
	@Override
	public void draw(Input input, Output output){
		double health = 5.0 * player.getHealth() / player.getHealthMax();
	}
}
