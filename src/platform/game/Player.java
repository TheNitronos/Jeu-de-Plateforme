package platform.game;

import com.sun.glass.events.KeyEvent;

import platform.game.level.Level;
import platform.game.level.Selection;
import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Vector;

public class Player extends Actor {
	private final double SIZE = 1.0;
	private Vector velocity;
	private Vector position;
	private boolean colliding;
	private double health;
	private double maxHealth;
	double maxSpeed;
	private int priority;
	private double cooldown;

	public Player(Vector vel, Vector pos) {
		if(vel == null || pos == null) {
			throw new NullPointerException();
		}
		
		velocity = vel;
		position = pos;
		
		maxHealth = 10.0;
		health = maxHealth;
		
		maxSpeed = 8.0;
		
		priority = 42;
		cooldown = 1.0;
	}
	
	@Override
	public int getPriority() {
		return priority;
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
	public void draw(Input in, Output out) {
		super.draw(in, out);
		
		if (health > 5) {
			out.drawSprite(super.getSprite("blocker.happy"), getBox());
		} else if (health <= 5 && health > 1) {
			out.drawSprite(super.getSprite("blocker.sad"), getBox());
		} else {
			out.drawSprite(super.getSprite("blocker.dead"), getBox(), 0.0, cooldown);
		}
	}
	
	@Override
	public void register(World world) {
		super.register(world);
	}
	
	@Override
	public void update(Input input) {
		super.update(input);
		
		if (colliding) {
			double scale = Math.pow(0.001, input.getDeltaTime());
			velocity = velocity.mul(scale);
		}
		
		if (input.getKeyboardButton(KeyEvent.VK_RIGHT).isDown()) {
			if (velocity.getX() < maxSpeed) {
				moveRight(input);
			}
		}
		
		if (input.getKeyboardButton(KeyEvent.VK_LEFT).isDown()) {
			if (velocity.getX() > -maxSpeed ) {
				moveLeft(input);
			}
		}
		
		if (input.getKeyboardButton(KeyEvent.VK_UP).isPressed()) {
			if (colliding) {
				jump();
			}
		}
		
		if (input.getKeyboardButton(KeyEvent.VK_SPACE).isPressed()) {
			throwSomething();
		}
		
		if (input.getKeyboardButton(KeyEvent.VK_B).isPressed()) {
			blow();
		}
		
		if (input.getKeyboardButton(KeyEvent.VK_E).isPressed()) {
			activateSomethng();
		}
		
		double delta = input.getDeltaTime();
		Vector acceleration = this.getWorld().getGravity();
		velocity = velocity.add(acceleration.mul(delta));
		position = position.add(velocity.mul(delta));
		
		//si le joueur n'a plus de vie, il meurt
		if(health <= 0) {
			death(input.getDeltaTime());
		}
	}

	@Override
	public void interact(Actor other) {
		super.interact(other);
		
		if (other.isSolid()) {
			Vector delta = other.getBox().getCollision(getBox());
			
			if (delta != null) {
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
	
	public boolean isDead(){
		if (health <= 0.0){
			return true;
		}
		return false;
	}
	@Override
	public void preUpdate(Input input) {
		colliding = false;
	}
	
	@Override
	public void postUpdate(Input input) {
		super.postUpdate(input);
		getWorld().setView(position, 10.0);
	}
	
	@Override
	public boolean hurt(Actor instigator, Damage type, double amount, Vector location) {
		switch (type) {
			case AIR:
				velocity = getPosition().sub(location).resized(amount);
				return true;
			
			case VOID:
				health -= amount;
				return true;
			
			case HEAL:
				health += amount;
				
				if (health >= maxHealth) {
					health = maxHealth;
				}
				
				return true;
			
			case PHYSICAL:
				health -= amount;
				velocity = new Vector(velocity.getX(), 7.0);
				
			case MONSTER:
				health -= amount;
				getWorld().register(new Smoke(position, 0.8));
				return true;
			
			default:
				return super.hurt(instigator, type, amount, location);		
		}
				
	}
	
	public void death(double delta) {
		jump();
		priority = -10;
		cooldown -= delta;
		
		if (cooldown < 0) {	
			getWorld().nextLevel();
		}
	}
	
	public double getHealth() {
		return health;
	}
	
	public double getHealthMax() {
		return maxHealth;
	}
	
	@Override
	public double getVelocityY() {
		return velocity.getY();
	}
	
	private void jump() {
		velocity = new Vector(velocity.getX(), 7.0);
	}
	
	private void throwSomething() {
		Vector v = velocity.add(velocity.resized(4.0));
		Fireball fireball = new Fireball(v, position, this);
		super.getWorld().register(fireball); 
	}
	
	private void blow() {
		getWorld().hurt(getBox(), this, Damage.AIR, 1.0, getPosition());
		getWorld().register(new Smoke(position, 0.5));
	}
	
	private void activateSomethng() {
		getWorld().hurt(getBox(), this, Damage.ACTIVATION, 1.0, getPosition());
	}
	
	private void moveRight(Input input) {
		double increase = 60.0 * input.getDeltaTime();
		double speed = velocity.getX() + increase;
		
		if (speed > maxSpeed) {
			speed = maxSpeed;
		}
		
		velocity = new Vector(speed, velocity.getY());
	}
	
	private void moveLeft(Input input) {
		double increase = 60.0 * input.getDeltaTime();
		double speed = velocity.getX() - increase;
		
		if (speed < -maxSpeed) {
			speed = -maxSpeed;
		}
		
		velocity = new Vector(speed, velocity.getY());
	}
}
