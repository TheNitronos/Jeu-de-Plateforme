package platform.game;

import platform.util.Vector;

import com.sun.glass.events.KeyEvent;

import platform.util.Box;
import platform.util.Input;
import platform.util.Sprite;
import platform.util.Output;

public class Player extends Actor {
	private final double SIZE = 0.5;
	private Vector velocity;
	private Vector position;
	private Sprite sprite;
	private boolean colliding;
	
	public Player(Vector vel, Vector pos) {
		
		if(vel == null || pos == null){
			throw new NullPointerException();
		}
		
		velocity = vel;
		position = pos;
	}
	
	@Override
	public int getPriority(){
		return 42;
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
		
		if (colliding){
			double scale = Math.pow(0.001, input.getDeltaTime());
			velocity = velocity.mul(scale);
		}
		
		double maxSpeed = 4.0;
		if (input.getKeyboardButton(KeyEvent.VK_RIGHT).isDown()){
			if (velocity.getX() < maxSpeed){
				double increase = 4000.0 * input.getDeltaTime();
				double speed = velocity.getX() + increase;
				if (speed > maxSpeed){
					speed = maxSpeed;
				velocity = new Vector(speed, velocity.getY());
				}
			}
		}
		
		if (input.getKeyboardButton(KeyEvent.VK_LEFT).isDown()){
			if (velocity.getX() > -maxSpeed){
				double increase = 4000.0 * input.getDeltaTime();
				double speed = velocity.getX() - increase;
				if (speed < -maxSpeed){
					speed = -maxSpeed;
				velocity = new Vector(speed, velocity.getY());
				}
			}
		}
		
		if (input.getKeyboardButton(KeyEvent.VK_UP).isPressed()){
			if (colliding){
				velocity = new Vector(velocity.getX(), 7.0);
			}
		}
		
		if (input.getKeyboardButton(KeyEvent.VK_SPACE).isPressed()){
			Vector v = velocity.add(velocity.resized(4.0));
			Fireball fireball = new Fireball(v, position, this);
			super.getWorld().register(fireball);
					
		}
		
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
				colliding = true;
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
	
	@Override
	public void preUpdate(Input input){
		colliding = false;
	}
	
	@Override
	public void postUpdate(Input input){
		super.postUpdate(input);
		getWorld().setView(position, 7.0);
	}
	
	@Override
	public boolean hurt(Actor instigator, Damage type, double amount, Vector location){
		switch (type){
			case AIR:
				velocity = getPosition().sub(location).resized(amount);
				return true;
			default:
				return super.hurt(instigator, type, amount, location);
				
				
		}
		
		
				
	}
}
