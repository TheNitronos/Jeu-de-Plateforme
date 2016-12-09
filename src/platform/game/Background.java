/*
 *	Author:      Samuel Chassot
 *	Date:        9 déc. 2016
 */


package platform.game;

import platform.util.Sprite;
import platform.util.Input;
import platform.util.Output;
import platform.util.Vector;
import platform.util.Box;


public class Background extends Actor{
	
	private Sprite sprite;
	private final double SIZE;
	private Vector position;
	
	public Background(Vector pos, Sprite s, double size){
		sprite = s;
		SIZE = size;
		position = pos;
	}
	@Override
	public int getPriority(){
		return Integer.MIN_VALUE;
	}
	
	@Override
	public void draw(Input input, Output output){
		output.drawSprite(sprite, getBox());
	}
	
	@Override
	public Box getBox(){
		return new Box(position, SIZE, SIZE);
	}
}
