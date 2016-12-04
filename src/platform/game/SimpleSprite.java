package platform.game;

import platform.util.Vector;
import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Sprite;
import platform.game.Actor;

public class SimpleSprite extends Actor{
	private final Vector POSITION;
	private double cooldown;
	private final double SIZE;
	private final boolean SPINING;
	private String sprite;
	
	public SimpleSprite(String nSprite, Vector nPos, Double nCoold, double nSize, boolean nSpin) {
		POSITION = nPos;
		cooldown = nCoold;
		SIZE = nSize;
		SPINING = nSpin;
		sprite = nSprite;
	}
	
	@Override
	public int getPriority() {
		return 1;
	}
	
	@Override
	public void draw(Input in, Output out){
		super.draw(in, out);
		
		if (SPINING) {
			out.drawSprite(super.getSprite(sprite), getBox(), in.getTime()*15);
		} else {
			out.drawSprite(super.getSprite(sprite), getBox());
		}
	}
	
	@Override
	public Box getBox(){
		return new Box(POSITION, SIZE, SIZE);
	}
	
	@Override
	public void update(Input input) {
		cooldown -= input.getDeltaTime();
		
		if(cooldown < 0.0){
			getWorld().unregister(this);
		}
	}
}
