package platform.game;

import platform.game.Block;
import platform.game.Signal;
import platform.util.Vector;
import platform.util.Box;
import platform.util.Sprite;
import platform.util.Input;
import platform.util.Output;

public class Mover extends Block{
	private Vector on;
	private Vector off;
	private Signal signal;
	private double current;
	private Sprite sprite;

	
	public Mover(Box box, Sprite sprite, Vector on, Signal signal){
		super(box, sprite);
		this.off = box.getCenter();
		this.on = on;
		this.signal = signal;
		this.sprite = sprite;
	}
	
	@Override
	public void update(Input input){
		super.update(input);
		if (signal.isActive()){
			current += input.getDeltaTime();
			if (current > 1.0){
				current = 1.0;
			}
		}
		else{
			current -= input.getDeltaTime();
			if (current < 0.0){
				current = 0.0;
			}
		}
	}
	
	@Override
	public Box getBox(){
		if (signal.isActive()){
			return super.getBox().add(on.sub(off).mul(current));
		}
		else {
			return super.getBox().add(off.sub(on).mul(current).mul(-1.0));
		}
	}
	
	@Override
	public void draw(Input input, Output output){
		output.drawSprite(sprite, getBox());
		
	}	
}
