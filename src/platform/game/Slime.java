/*
 *	Author:      Samuel Chassot
 *	Date:        8 déc. 2016
 */


package platform.game;

import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Vector;

public class Slime extends Actor{
	
	private Vector position;
	private Vector velocity;
	private double health;
	
	public Slime(Vector pos, Vector velo){
		position = pos;
		velocity = velo;
		health = 10.0;
			
	}
	
	public void update(){
		position.add(velocity);
	}
	
	
}
