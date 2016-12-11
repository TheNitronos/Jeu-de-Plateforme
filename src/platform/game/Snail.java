package platform.game;

import platform.util.Vector;
import platform.util.Box;
import platform.util.Output;
import platform.util.Input;

/**
 * petit escargot méga chou
 * qui décore en se déplacant super lentement
 * qui se cache en cas de boule de feu
 */
public class Snail extends Actor {
	private Vector position;
	//valeur initiale du compteur
	private final double COOLDOWN = 3.0;
	//compteur qui indique s'il est caché ou non (positif : il se cache)
	private double countdown;
	private Vector velocity;
	private final double SIZE = 0.5;
	
	//compteur lui permettant d'altérner entre les 2 positions
	private double maj;

	
	public Snail(Vector nPosition, Vector nVelocity) {
		position = nPosition;
		velocity = nVelocity;
		maj = COOLDOWN/4;
	}
	
	@Override
	public int getPriority(){
		return 20;
	}
	
	@Override
	public void draw(Input input, Output output){
		/*
		 * Dessin de la coquille de l'escargot selon
		 * le compteur et sa direction 
		 */
		String name = "snail.";
		
		if (velocity.getX() >= 0.0){
			name += "right.";
		} else {
			name += "left.";
		}
		
		if (countdown > 0.0){
			name += "shell";
		} else {
			if (maj > COOLDOWN/8){
				name += "2";
			} else {
				name += "1";
			}
		}
		
		output.drawSprite(getSprite(name), getBox());
	}
	
	@Override
	public Box getBox(){
		return new Box(position.add(new Vector(0.0, 0.1)), SIZE, SIZE);
	}
	
	@Override
	public void update(Input input) {
		maj -= input.getDeltaTime();
		
		if (maj < 0.0) {
			maj = COOLDOWN/4;
		}
		
		countdown -= input.getDeltaTime();
		
		//décrémente le compteur et le laisse négatif mais évite qu'il devienne trop petit
		if (countdown < -1) {
			countdown = -1;
		}

		
		double delta = input.getDeltaTime();
		Vector acceleration = this.getWorld().getGravity();
		
		if (countdown <= 0.0) {
			velocity = velocity.add(acceleration.mul(delta));
			position = position.add(velocity.mul(delta));
		}
	}
	
	@Override
	public void interact(Actor other) {
		super.interact(other);
		//si l'escargot choque contre qqch de solide		
		if (other.isSolid()) {
			//on récupère l'endroit du choc
			Vector delta = other.getBox().getCollision(getBox());
			
			if (delta != null) {
				position = position.add(delta);
				
				//on inverse la vitesse horizontal s'il va contre qqch
				if (delta.getX() != 0.0) {
					velocity = new Vector(-1.0 * velocity.getX(), velocity.getY());
				}
				//on le laisse continuer s'il tombe sur qqch
				if (delta.getY() != 0.0) {
					velocity = new Vector(velocity.getX(), 0.0);
				}
			}
		}			
	}
	
	@Override
	public boolean hurt(Actor instigator, Damage type, double amount, Vector location) {
		switch (type) {
		 case FIRE:
			 //remise à son état maximal si l'escargot interagit avec du feu
			 countdown = COOLDOWN;
			 
			 return true;
			 
		default:
			return false;
			 
		}
	}
}
