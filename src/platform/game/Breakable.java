package platform.game;

import platform.game.Block;
import platform.util.Vector;
import platform.util.Box;
import platform.util.Sprite;
import platform.util.Input;
import platform.util.Output;

public class Breakable extends Block{
	private double life;
	private Fire fire;
	
	public Breakable(Box box, Sprite sprite) {
		super(box, sprite);
		
		life = 100.0;
		fire = new Fire(this.getPosition(), 0.0);
	}
	
	public double getLife() {
		return life;
	}
	
	@Override
	public boolean hurt(Actor instigator, Damage type, double amount, Vector location){
		switch (type) {
			case FIRE:
				super.getWorld().unregister(fire);
				
				fire = new Fire(this.getPosition(), 3.0);
				super.getWorld().register(fire);
				
				--life;
				
				return true;
				
			default:
				return super.hurt(instigator, type, amount, location);
		}
	}
	
	@Override
	public void draw(Input input, Output output){
		super.draw(input, output);
		
		if (life <= 50) {
			output.drawSprite(this.getSprite("box.empty"), getBox());
			
		} else {
			output.drawSprite(this.getSprite("box.double"), getBox());
		}
		
	}
	
	@Override
	public void update(Input input) {
		if (fire.getCooldown() > 0) {
			life -= input.getDeltaTime()*30;
		}
		
		if (life <= 0.0) {
			endLife();
		}
	}
	
	private void endLife() {
		getWorld().register(new Smoke(this.getPosition(), 2));
		getWorld().unregister(fire);
		getWorld().unregister(this);
	}
}
