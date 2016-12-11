package platform.game;

import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Vector;

/**
 * animal dangereux pour le joueur qui se déplace
 * et peut servir de signal
 */
public class Slime extends Actor implements Signal {
	//durée entre 2 dégâts
	private final double COUNTDOWN;
	private final double SIZE;
	
	private Vector position;
	private Vector velocity;
	
	private double health;
	//compteur sur l'indication de la forme du slime pour l'effet de deplacement
	private double maj;
	//compteur de durée entre 2 dégats
	private double cooldownDamage;
	
	public Slime(Vector pos){
		position = pos;
		velocity = new Vector(2, 0);
		health = 10.0;
		COUNTDOWN = 0.75;
		maj = COUNTDOWN;
		SIZE = 2.0;
		cooldownDamage = 0.0;
	}
	
	@Override
	public void update(Input input) {
		super.update(input);
		//maj est réduit à chaque tour
		maj -= input.getDeltaTime();
		//si maj devient négatif, on remet à son maximum
		if (maj < 0.0) {
			maj = COUNTDOWN;
		}
		//si le slime n'a plus de vie, il disparait
		if (health < 0.0) {
			getWorld().unregister(this);
		}
		//chute du slime
		double delta = input.getDeltaTime();
		Vector acceleration = this.getWorld().getGravity();
		velocity = velocity.add(acceleration.mul(delta));
		position = position.add(velocity.mul(delta));
		//le compteur de dégâts est réduit
		cooldownDamage -= input.getDeltaTime();
	}
	
	@Override
	public int getPriority() {
		//priorité plus grande pour interagir avec le Player
		return 50;
	}
	
	@Override
	public void draw(Input input, Output output){
		super.draw(input, output);
		//dessin du slime en fonction de maj (compteur d'état)
		String name = "slime.";
		
		if (velocity.getX() >= 0.0) {
			name += "right.";
		} else {
			name += "left.";
		}
		
		if (maj > 3*COUNTDOWN /4) {
			name += "1";
		} else if (maj > COUNTDOWN/2) {
			name += "2";
		} else if (maj > COUNTDOWN/4) {
			name += "3";
		} else {
			name += "2";
		}
		
		output.drawSprite(getSprite(name), getBox());
	}
	
	@Override
	public Box getBox() {
		//slime 2 foix plus long que haut
		return new Box(position, SIZE, SIZE/2);
	}
	
	@Override
	public void register(World world) {
		super.register(world);
	}
	
	@Override
	public boolean hurt(Actor instigator, Damage type, double amount, Vector location) {
		switch (type) {
			case FIRE:
				//en cas de feu, la vie est décrémentée et on affiche une petiiiiiteee fumée
				health -= amount;
				getWorld().register(new Smoke(position, 1.5));
				
				return true;
			
			default:
				return super.hurt(instigator, type, amount, location);
		}
	}
	
	@Override
	public boolean isActive() {
		//l'état du signal du slime depend de son état de vie
		if (health <= 0.0) {
			return true;
		} else {
			return false;	
		}
	}
	
	@Override
	public void interact(Actor other){
		super.interact(other);
		//déplacement autonome du slime		
		if (other.isSolid()) {
			Vector delta = other.getBox().getCollision(getBox());
			
			if (delta != null) {
				position = position.add(delta);
				
				if (delta.getX() != 0.0) {
					velocity = new Vector(-1.0 * velocity.getX(), velocity.getY());
				}
				
				if (delta.getY() != 0.0) {
					velocity = new Vector(velocity.getX(), 0.0);
				}
			}
		}
		//on inflige des dégâts de type monster et on remet le compteur à 2.0 pour que le player ait le temps de fuir et pas se faire tuer en continu
		if (other.getBox().isColliding(getBox()) && cooldownDamage <= 0.0) {
			if (other.hurt(this, Damage.MONSTER, 2.0, getPosition())){
				cooldownDamage = 2.0;	
			}
		}
	}
}
