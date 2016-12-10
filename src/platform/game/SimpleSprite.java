package platform.game;

import platform.util.Vector;
import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.game.Actor;

public class SimpleSprite extends Actor{
	private final Vector POSITION;
	private boolean cooldownBool;
	private double cooldown;
	private final double SIZE;
	private final boolean SPINING;
	private String sprite;
	private final int PRIORITY;
	
	public SimpleSprite(String nSprite, Vector nPos, Double nCoold, double nSize, boolean nSpin, int nPriority) {
		POSITION = nPos;
		cooldown = nCoold;
		SIZE = nSize;
		SPINING = nSpin;
		sprite = nSprite;
		PRIORITY = nPriority;
		
		cooldownBool = true;
	}
	
	public SimpleSprite(String nSprite, Vector nPos, double nSize, boolean nSpin, int nPriority) {
		POSITION = nPos;
		SIZE = nSize;
		SPINING = nSpin;
		sprite = nSprite;
		PRIORITY = nPriority;
		
		cooldownBool = false;
	}
	
	@Override
	public int getPriority() {
		return PRIORITY;
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
