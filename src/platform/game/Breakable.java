package platform.game;

import platform.game.Block;
import platform.game.Signal;
import platform.util.Vector;
import platform.util.Box;
import platform.util.Sprite;
import platform.util.Input;
import platform.util.Output;

public class Breakable extends Block{
	
	private int damage;
	
	public Breakable(Box box, Sprite sprite) {
		super(box, sprite);
		damage = 10;
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
		} else {
			output.drawSprite(this.getSprite("box.double"), getBox());
		}
		
	}

}
