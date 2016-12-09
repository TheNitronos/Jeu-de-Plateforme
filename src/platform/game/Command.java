/*
 *	Author:      Samuel Chassot
 *	Date:        9 déc. 2016
 */


package platform.game;

import com.sun.glass.events.KeyEvent;

import platform.game.level.Level;
import platform.game.level.Selection;
import platform.util.Input;


public class Command extends Actor{
	private World world;
	
	public Command(World w){
		world = w;
	}
	
	@Override
	public int getPriority(){
		return Integer.MAX_VALUE;
	}
	
	@Override
	public void update(Input input){
		if (input.getKeyboardButton(KeyEvent.VK_R).isPressed()) {
			world.nextLevel();
		}
		if (input.getKeyboardButton(KeyEvent.VK_Q).isDown()) {
			world.setNextLevel(new Selection());
			world.nextLevel();
		}
	}
}
