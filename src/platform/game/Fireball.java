/*
 *	Author:      Samuel Chassot
 *	Date:        24 nov. 2016
 */

package platform.game;

import platform.util.Vector;
import platform.util.Box;
import platform.util.Input;
import platform.util.Sprite;
import platform.util.Output;

public class Fireball extends Actor {
	
	private final double SIZE = 0.4;
	private double cooldown;
	private Vector velocity;
	private Vector position;
	private Sprite sprite;
	private Actor owner;

	
	public Fireball(Vector vel, Vector pos, Actor owner){
		
		if(vel == null || pos == null){
			throw new NullPointerException();
		}
		
		velocity = vel;
		position = pos;
		cooldown = 3.0;
	}
	
	@Override
	public int getPriority(){
		return 666;
	}
	
	@Override
	public Box getBox(){
		return new Box(position, SIZE, SIZE);
	}
	
	public Vector getVelocity(){
		return velocity;
	}
	
	@Override
	public void update(Input input){
		super.update(input);
		double delta = input.getDeltaTime();
		Vector acceleration = this.getWorld().getGravity();
		velocity = velocity.add(acceleration.mul(delta));
		position = position.add(velocity.mul(delta));
		
		cooldown -= input.getDeltaTime();
		if(cooldown < 0.0){
			getWorld().register(new Smoke(position, SIZE));
			getWorld().unregister(this);
		}
	}
	
	@Override
	public void draw(Input in, Output out){
		super.draw(in, out);
		
		if (sprite != null) {			;
			out.drawSprite(sprite, getBox(), in.getTime()*30);
		}
	}
	
	@Override
	public void register(World world){
		super.register(world);
		sprite = super.getSprite("fireball");
	}
	
	@Override
	public void interact(Actor other){
		super.interact(other);
		if (other.isSolid()){
			Vector delta = other.getBox().getCollision(position);
			if (delta != null){
				position = position.add(delta);
				velocity = velocity.mirrored(delta);
			}
		}
		if (other.getBox().isColliding(getBox())){
			if(other != owner && other.hurt(this, Damage.FIRE, 1.0, getPosition())){
				//la boule disparait si elle a brule qqn ou qqch
				getWorld().unregister(this);
			}
		}
	}
}
