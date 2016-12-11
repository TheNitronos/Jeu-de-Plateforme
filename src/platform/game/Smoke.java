package platform.game;

import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Vector;

/**
 * fumée, utile lors de souffle ou de destructions, etc...
 */
public class Smoke extends Actor {
	//durée de vie
	private final double COOLDOWN;
	//compteur de durée de vie
	private  double countdown;
	private double size;
	private final Vector POSITION;
	
	public Smoke(Vector nPosition, double nSize) {
		size = nSize;
		POSITION = nPosition;
		COOLDOWN = 0.3;
		countdown = COOLDOWN;
	}
	
	@Override
	public int getPriority() {
		//très grande priorité pour s'afficher devant
		return 10000;
	}
	
	@Override
	public void update(Input input) {
		countdown -= input.getDeltaTime();
		//si le compteur devient négatif, la fumée s'estompe
		if(countdown < 0.0){
			getWorld().unregister(this);
		}
	}
	
	@Override
	public Box getBox() {
		return new Box(POSITION, size, size);
	}
	
	@Override
	public void draw(Input input, Output output) {
		String name;
		//selon la durée de vie restante, on affiche différentes images
		if (countdown >= COOLDOWN * 2/3) {
			name = "smoke.gray.1";
		} else if (countdown >= COOLDOWN/3) {
			name = "smoke.gray.2";
		} else {
			name = "smoke.gray.3";
		}
		
		output.drawSprite(getSprite(name), getBox());
	}
}
