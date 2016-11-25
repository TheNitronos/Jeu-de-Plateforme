package platform.game;

import platform.util.Vector;
import platform.util.Box;
import platform.util.Input;
import platform.util.Sprite;
import platform.util.Output;

public class Player extends Actor {
	private final double SIZE = 0.5;
	private Vector velocity;
	private Vector position;
	private Sprite sprite;
	
	public Player(Vector vel, Vector pos) {
		super(42);
		
		if(vel == null || pos == null){
			throw new NullPointerException();
		}
		
		velocity = vel;
		position = pos;
	}
	
	@Override
	public Vector getPosition() {
		return position;
	}
	
	@Override
	public Box getBox() {
		return new Box(position, SIZE, SIZE);
	}
	
	@Override
	public void draw(Input in, Output out){
		super.draw(in, out);
		
		if (sprite != null) {			;
			out.drawSprite(sprite, getBox());
		}
	}
	
	@Override
	public void register(World world){
		super.register(world);
		sprite = super.getSprite("blocker.happy");
	}
	
	@Override
	public void update(Input input){
		super.update(input);
		double delta = input.getDeltaTime();
		Vector acceleration = this.getWorld().getGravity();
		velocity = velocity.add(acceleration.mul(delta));
		position = position.add(velocity.mul(delta));
	}
	
	@Override
	public void interact(Actor other){
		super.interact(other);
		if (other.isSolid()){
			Vector delta = other.getBox().getCollision(getBox());
			if (delta != null){
				position = position.add(delta);
				if (delta.getX() != 0.0) {
					velocity = new Vector(0.0, velocity.getY());
				}
				if (delta.getY() != 0.0) {
					velocity = new Vector(velocity.getX(), 0.0);
				}
			}
		}
	}
}
