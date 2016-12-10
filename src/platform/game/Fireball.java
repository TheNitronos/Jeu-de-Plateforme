package platform.game;

import platform.util.Vector;
import platform.util.Box;
import platform.util.Input;
import platform.util.Output;

/**
 * Boule de feu
 */
public class Fireball extends Actor {
	private final double SIZE = 0.4;
	//durée de vie de la boule de feu
	private double cooldown;
	private Vector velocity;
	private Vector position;
	private final Actor OWNER;

	public Fireball(Vector nVelocity, Vector nPosition, Actor nOwner) {
		if (nVelocity == null || nPosition == null) {
			throw new NullPointerException();
		}
		
		velocity = nVelocity;
		position = nPosition;
		cooldown = 3.0;
		OWNER = nOwner;
	}
	
	@Override
	public int getPriority() {
		//très haute priorité pour pouvoir interagir avec les autres acteurs
		return 666;
	}
	
	@Override
	public Box getBox() {
		return new Box(position, SIZE, SIZE);
	}
	
	public Vector getVelocity() {
		return velocity;
	}
	
	@Override
	public void update(Input input) {
		super.update(input);
		//variation du temps
		double delta = input.getDeltaTime();
		Vector acceleration = getWorld().getGravity();
		
		//augmentation de la vitesse selon l'acceleration multipliée par le temps
		velocity = velocity.add(acceleration.mul(delta));
		//nouvelle position selon la vitesse multipliée par le temps
		position = position.add(velocity.mul(delta));
		//diminution de la durée de vie à chaque update
		cooldown -= input.getDeltaTime();
		
		/*
		 * si la durée de vie devient négative, la boule meurt
		 */
		if(cooldown < 0.0) {
			endLife();
		}
	}
	
	@Override
	public void draw(Input in, Output out){
		super.draw(in, out);
		//dessin de fireball avec un angle pour simuler la rotation
		out.drawSprite(getSprite("fireball"), getBox(), in.getTime()*30);
	}
	
	@Override
	public void interact(Actor other){
		super.interact(other);
		
		/*
		 * si la boule de feu interagit avec un acteur solide 
		 * elle rebondit dessus
		 */
		if (other.isSolid()){
			Vector location = other.getBox().getCollision(position);
			
			if (location != null){
				position = position.add(location);
				velocity = velocity.mirrored(location);
			}
		}
		
		/*
		 * si la boule interagit avec un acteur qui n'est pas le lanceur de la boule meurt
		 */
		if (other.getBox().isColliding(getBox())){
			if (other != OWNER && other.hurt(this, Damage.FIRE, 3.5 , getPosition())) {
				endLife();
			}
		}
	}
	
	//la mort de la boule laisse place à une instance de fumée
	private void endLife() {
		getWorld().register(new Smoke(position, SIZE));
		getWorld().unregister(this);
	}
	
}
