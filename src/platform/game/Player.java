package platform.game;

import platform.util.Vector;

import com.sun.glass.events.KeyEvent;

import platform.game.level.Level;
import platform.util.Box;
import platform.util.Input;
import platform.util.Sprite;
import platform.util.Output;

public class Player extends Actor {
	private final double SIZE = 1.0;
	private Vector velocity;
	private Vector position;
	private boolean colliding;
	private double health;
	private double maxHealth;
	double maxSpeed;
	
	
	
	public Player(Vector vel, Vector pos) {
		
		if(vel == null || pos == null){
			throw new NullPointerException();
		}
		
		velocity = vel;
		position = pos;
		
		maxHealth = 10.0;
		health = maxHealth;
		maxSpeed = 6.0;
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
		out.drawSprite(super.getSprite("blocker.happy"), getBox());
	}
	
	@Override
	public void register(World world){
		super.register(world);
		
	}
	
	@Override
	public void update(Input input){
		super.update(input);
		
		if (colliding){
			double scale = Math.pow(0.001, input.getDeltaTime());
			velocity = velocity.mul(scale);
		}
		
		if (input.getKeyboardButton(KeyEvent.VK_RIGHT).isDown()){
			if (velocity.getX() < maxSpeed) {
				double increase = 60.0 * input.getDeltaTime();
				double speed = velocity.getX() + increase;
				
				if (speed > maxSpeed) {
					speed = maxSpeed;
				}
				
				velocity = new Vector(speed, velocity.getY());
			}
		}
		
		if (input.getKeyboardButton(KeyEvent.VK_LEFT).isDown()){
			if (velocity.getX() > -maxSpeed ){
				double increase = 60.0 * input.getDeltaTime();
				double speed = velocity.getX() - increase;
				
				if (speed < -maxSpeed) {
					speed = -maxSpeed;
				}
				
				velocity = new Vector(speed, velocity.getY());
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
		
		if (input.getKeyboardButton(KeyEvent.VK_B).isPressed()){
			getWorld().hurt(getBox(), this, Damage.AIR, 1.0, getPosition());
		}
		
		if (input.getKeyboardButton(KeyEvent.VK_E).isPressed()){
			getWorld().hurt(getBox(), this, Damage.ACTIVATION, 1.0, getPosition());
		}
		
		
		double delta = input.getDeltaTime();
		Vector acceleration = this.getWorld().getGravity();
		velocity = velocity.add(acceleration.mul(delta));
		position = position.add(velocity.mul(delta));
		
		//si le joueur n'a plus de vie, il meurt
		if(health <= 0) {
			this.death();
		}
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
		getWorld().setView(position, 10.0);
	}
	
	@Override
	public boolean hurt(Actor instigator, Damage type, double amount, Vector location){
		switch (type){
			case AIR:
				velocity = getPosition().sub(location).resized(amount);
				return true;
			case VOID:
				health -= amount;
				return true;
			case HEAL:
				health += amount;
				if (health >= maxHealth){
					health = maxHealth;
				}
				return true;
			case PHYSICAL:
				health -= amount;
				velocity = new Vector(velocity.getX(), 7.0);
			default:
				return super.hurt(instigator, type, amount, location);		
		}
				
	}
	
	public void death(){
		getWorld().setNextLevel(Level.createDefaultLevel());
		getWorld().nextLevel();
	}
	
	public double getHealth(){
		return health;
	}
	
	public double getHealthMax(){
		return maxHealth;
	}
	
	@Override
	public double getVelocityY(){
		return velocity.getY();
	}
}
