package platform.game;

import platform.util.Vector;
import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Sprite;
import platform.game.Actor;

public class SimpleSprite extends Actor{
	private final Vector POSITION;
	private boolean cooldownBool;
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
		
		cooldownBool = true;
	}
	
	public SimpleSprite(String nSprite, Vector nPos, double nSize, boolean nSpin) {
		POSITION = nPos;
		SIZE = nSize;
		SPINING = nSpin;
		sprite = nSprite;
		
		cooldownBool = false;
	}
	
	@Override
	public int getPriority() {
		return 1;
	}
	
	@Override
	public void draw(Input in, Output out){
		super.draw(in, out);
		
		if (sprite != null) {
			if (SPINING) {
				out.drawSprite(super.getSprite(sprite), getBox(), in.getTime()*15);
			} else {
				out.drawSprite(super.getSprite(sprite), getBox());
			}
		}
		
	}
	
	@Override
	public Box getBox(){
		return new Box(POSITION, SIZE, SIZE);
	}
	
	@Override
	public void update(Input input) {
		if (cooldownBool) {
			cooldown -= input.getDeltaTime();
			
			if(cooldown < 0.0){
				getWorld().unregister(this);
				getWorld().register(new Smoke(POSITION, SIZE));
			}	
		}
		
	}
	
	public double getCooldown() {
		return cooldown;
	}
}
