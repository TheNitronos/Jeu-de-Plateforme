/*
 *	Author:      Samuel Chassot
 *	Date:        8 déc. 2016
 */


package platform.game;

import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Vector;

public class Slime extends Actor implements Signal{
	
	private Vector position;
	private Vector velocity;
	private double health;
	private final double countdown;
	private double maj;
	private final double SIZE;
	private double cooldownDamage;
	
	public Slime(Vector pos){
		position = pos;
		velocity = new Vector(2, 0);
		health = 10.0;
		countdown = 0.75;
		maj = countdown;
		SIZE = 2.0;
		cooldownDamage = 0.0;
			
	}
	
	@Override
	public void update(Input input){
		super.update(input);
		
		maj -= input.getDeltaTime();
		if (maj < 0.0){
			maj = countdown;
		}
		if (health < 0.0){
			getWorld().unregister(this);
		}
		double delta = input.getDeltaTime();
		Vector acceleration = this.getWorld().getGravity();
		velocity = velocity.add(acceleration.mul(delta));
		position = position.add(velocity.mul(delta));
		
		cooldownDamage -= input.getDeltaTime();
		if (cooldownDamage < 0.0){
			cooldownDamage = 0.0;
		}
	}
	
	@Override
	public int getPriority(){
		return 50;
	}
	
	@Override
	public void draw(Input input, Output output){
		super.draw(input, output);
		String name = "slime.";
		if (velocity.getX() >= 0.0){
			name += "right.";
		}
		else{
			name += "left.";
		}
		if (maj > 3*countdown /4){
			name += "1";
		}
		else if (maj > countdown/2){
			name += "2";
		}
		else if (maj > countdown/4){
			name += "3";
		}
		else{
			name += "2";
		}
		output.drawSprite(getSprite(name), getBox());

	}
	
	public Box getDrawBox(){
		return new Box(position, SIZE, SIZE/2);
	}
	
	public Box getBox(){
		return new Box(position, SIZE, SIZE/2);
//		double high;
//		Vector newCenter;
//		if (maj > 3*countdown /4){
//			high = SIZE/2;
//			newCenter = position.sub(new Vector(0.0, 0.0));
//		}
//		else if (maj > countdown/2){
//			high = SIZE/3 ;
//			newCenter = position.sub(new Vector(0.0, SIZE/12));
//		}
//		else if (maj > countdown/4){
//			high = SIZE/6;
//			newCenter = position.sub(new Vector(0.0, SIZE/3));
//		}
//		else {
//			high = SIZE/3;
//			
//			newCenter = position.sub(new Vector(0.0, SIZE/12));
//		}
//		return new Box(newCenter, SIZE, high);
	}
	
	@Override
	public void register(World world){
		super.register(world);
	}
	
	@Override
	public boolean hurt(Actor instigator, Damage type, double amount, Vector location){
		
		switch (type){
			case FIRE:
				health -= amount;
				
				return true;
			default:
				return super.hurt(instigator, type, amount, location);
		}
	}
	
	@Override
	public boolean isActive(){
		if (health <= 0.0){
			return true;
		}
		return false;
	}
	
	@Override
	public void interact(Actor other){
		super.interact(other);
				
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
				if (other.getBox().isColliding(getBox()) && cooldownDamage <= 0.0){
					if(other.hurt(this, Damage.MONSTER, 2.0, getPosition())){
						cooldownDamage = 2.0;
						
					}
				}
			}
		

}
