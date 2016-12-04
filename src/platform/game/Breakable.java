package platform.game;

import platform.game.Block;
import platform.game.Fireball;
import platform.game.Signal;
import platform.util.Vector;
import platform.util.Box;
import platform.util.Sprite;
import platform.util.Input;
import platform.util.Output;

public class Breakable extends Block{
	
	private int damage;
	private Fire fire;
	
	public Breakable(Box box, Sprite sprite) {
		super(box, sprite);
		damage = 10;
		fire = new Fire(this.getPosition(), 0.5);
	}
	
	public int getDamage() {
		return damage;
	}
	
	@Override
	public boolean hurt(Actor instigator, Damage type, double amount, Vector location){
		
		switch (type) {
			case FIRE:
				if (damage == 1){
					getWorld().register(new Smoke(this.getPosition(), 2));
					getWorld().unregister(this);
					getWorld().unregister(fire);
				} else {
					--damage;
				}
				return true;
				
			default:
				return super.hurt(instigator, type, amount, location);
		}
	}
	
	@Override
	public void draw(Input input, Output output){
		super.draw(input, output);
		
		if (damage <= 6) {
			output.drawSprite(this.getSprite("box.empty"), getBox());
			super.getWorld().register(fire);
		} else {
			output.drawSprite(this.getSprite("box.double"), getBox());
			if (damage <= 9) {
				super.getWorld().register(fire);
			}
		}
		
	}

}
