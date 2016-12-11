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
	private final double MAXHEALTH = 10.0;
	private final double MAXSPEED = 8.0;
	
	private Vector velocity;
	private Vector position;
	
	private boolean colliding;
	private double health;
	private int priority;
	private double cooldown;

	public Player(Vector nVelocity, Vector nPosition) {
		if(nVelocity == null || nPosition == null) {
			throw new NullPointerException();
		}
		
		velocity = nVelocity;
		position = nPosition;
		priority = 42;
		cooldown = 1.0;
		health = MAXHEALTH;
		
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
	
	public double getHealth() {
		return health;
	}
	
	public double getHealthMax() {
		return MAXHEALTH;
	}
	
	@Override
	public double getVelocityY() {
		return velocity.getY();
	}
	
	@Override
	public void draw(Input in, Output out) {
		super.draw(in, out);
		
		//selon la vie, on affiche différents personnages, triste, heureux.... MORT !
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
			//s'il touche qqch, on simule un frottement
			double scale = Math.pow(0.001, input.getDeltaTime());
			velocity = velocity.mul(scale);
		}
		/*
		 * la gestion des touches a été modularisée, les méthodes
		 * associées à chaque touche se trouve plus bas
		 */
		if (input.getKeyboardButton(KeyEvent.VK_RIGHT).isDown()) {
			//aller à droite
			moveRight(input);	
		}
		
		if (input.getKeyboardButton(KeyEvent.VK_LEFT).isDown()) {
			//aller à gauche
			moveLeft(input);
		}
		
		if (input.getKeyboardButton(KeyEvent.VK_UP).isPressed()) {
			//il ne saute que s'il touche qqch
			if (colliding) {
				jump();
			}
		}
		
		if (input.getKeyboardButton(KeyEvent.VK_SPACE).isPressed()) {
			//lancer qqch
			throwSomething();
		}
		
		if (input.getKeyboardButton(KeyEvent.VK_B).isPressed()) {
			//souffler
			blow();
		}
		
		if (input.getKeyboardButton(KeyEvent.VK_E).isPressed()) {
			//interagir 
			activateSomethng();
		}
		
		//calculs de la chute en fonction de la gravité
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
				//choc à true
				colliding = true;
				position = position.add(delta);
				
				//remise à zéro des vitesses selon l'axe de choc
				if (delta.getX() != 0.0) {
					velocity = new Vector(0.0, velocity.getY());
				}
				
				if (delta.getY() != 0.0) {
					velocity = new Vector(velocity.getX(), 0.0);
				}
			}
		}
	}
	
	/*
	 * true si le player est mort false sinon
	 */
	public boolean isDead() {
		if (health <= 0.0) {
			return true;
		}
		
		return false;
	}
	
	@Override
	public void preUpdate(Input input) {
		//remis à false avant chaque update au cas où il serait dans les airs
		colliding = false;
	}
	
	@Override
	public void postUpdate(Input input) {
		super.postUpdate(input);
		//remise en place de la vue sur le joueur
		getWorld().setView(position, 10.0);
	}
	
	@Override
	public boolean hurt(Actor instigator, Damage type, double amount, Vector location) {
		//si les dégats ont un effet, on retourne true
		switch (type) {
			case AIR:
				//si le joueur subit de dégâts de type air il éjecte selon l'importance du dégât
				velocity = getPosition().sub(location).resized(amount);
				
				return true;
			
			case VOID:
				//perte de vie
				health -= amount;
				
				return true;
			
			case HEAL:
				//gain de vie
				health += amount;
				//la vie ne dépasse pas la vie maximale
				if (health >= MAXHEALTH) {
					health = MAXHEALTH;
				}
				
				return true;
			
			case PHYSICAL:
				//perte de vie
				health -= amount;
				//ejection énooooooorme selon y
				velocity = new Vector(velocity.getX(), 7.0);
				
			case MONSTER:
				//perte de vie
				health -= amount;
				//création de fumée... COUP DE FOUDRE :)
				getWorld().register(new Smoke(position, 0.8));
				
				return true;
			
			default:
				return super.hurt(instigator, type, amount, location);		
		}
				
	}
	/*
	 * faire mourrir le joueur
	 * il perd de la priorité et saute à l'infini
	 * jusqu'à passer au prochain niveau (même niveau donc)
	 */
	public void death(double delta) {
		jump();
		priority = -10;
		cooldown -= delta;
		
		if (cooldown < 0) {	
			getWorld().nextLevel();
		}
	}
	
	/*
	 * 
	 */
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
		
		if (speed > MAXSPEED) {
			speed = MAXSPEED;
		}
		
		velocity = new Vector(speed, velocity.getY());
	}
	
	private void moveLeft(Input input) {
		double increase = 60.0 * input.getDeltaTime();
		double speed = velocity.getX() - increase;
		
		if (speed < -MAXSPEED) {
			speed = -MAXSPEED;
		}
		
		velocity = new Vector(speed, velocity.getY());
	}
}
