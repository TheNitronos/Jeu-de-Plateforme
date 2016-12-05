package platform.game;

import platform.util.Box;
import platform.util.Input;
import platform.util.Sprite;
import platform.util.Output;
import platform.util.Vector;

public class Jumper extends Actor{
	private final double SIZE = 1.0;
	private Vector position;
	private Sprite sprite;
	private double coolDown;

	public Jumper(Vector pos){
		position = pos;
		coolDown = 0.0;
	}
	
	@Override
	public void update(Input input){
		super.update(input);
		coolDown -= input.getDeltaTime();
		
		if(coolDown >= 0){
			sprite = super.getSprite("jumper.extended");
		}
		else{
			sprite = super.getSprite("jumper.normal");
		}	
	}
	
	@Override
	public int getPriority(){
		return 50;
	}
	
	@Override
	public Box getBox(){
		return new Box(position, SIZE, SIZE);
	}
	
	@Override
	public void interact(Actor other){
		super.interact(other);
		
		if (coolDown <= 0 && getBox().isColliding(other.getBox())){
			Vector below = new Vector(position.getX(), position.getY() - 1.0);
			if (other.hurt(this, Damage.AIR, 10.0, below)){
				coolDown = 0.5;
			}
			
		}
	}
	
	@Override
	public void draw(Input in, Output out){
		if (sprite != null){
			super.draw(in, out);
			out.drawSprite(sprite, getBox());
		   }
	}
}
