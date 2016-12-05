package platform.game;

import platform.util.Box;
import platform.util.Input;
import platform.util.Vector;
import platform.util.Output;

public class Overlay extends Actor {
	private Player player;
	private final double SIZE = 0.25;
	
	public Overlay(Player player){
		this.player = player;
	}
	
	@Override
	public void draw(Input input, Output output){
		double health = 5.0 * player.getHealth() / player.getHealthMax();
		
		for (int i = 1 ; i <= 5 ; ++i){
			String name;
			if (health >= i){
				name = "heart.full";
			}
			else if (health >= i - 0.5){
				name = "heart.half";
			}
			else{
				name = "heart.empty";
			}
			
			Vector center = player.getBox().add(new Vector((-3 + i)*SIZE , 1.0)).getCenter();
			Box box = new Box(center, SIZE, SIZE);
			output.drawSprite(getSprite(name), box);
		}
	}
		
	@Override
	public int getPriority(){
		return 42;
	}
	@Override
	public Box getBox(){
		return player.getBox();
	}
	
	@Override
	public void update(Input input){
		if(player.getWorld() == null){
			this.getWorld().unregister(this);
		}
	}		
}
