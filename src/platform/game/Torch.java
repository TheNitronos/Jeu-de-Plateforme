package platform.game;

import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Vector;
import platform.game.Signal;

/**
 * torche que l'on peut allumer
 */
public class Torch extends Actor implements Signal {
	private final double SIZE = 0.8;
	private final Vector POSITION;
	//état d'allumage
	private boolean lit;
	//compteur pour la variation de la flamme pour plus de réalisme
	private double variation;
	
	public Torch(Vector nPosition, boolean nLit) {
		if (nPosition == null) {
			throw new NullPointerException();
		}
		
		POSITION = nPosition;
		lit = nLit;
	}
	
	@Override
	public int getPriority() {
		//priorité moyenne
		return 32;
	}
	
	@Override
	public void draw(Input input, Output output) {
		super.draw(input, output);
		//nom crée dans la méthode en fonction de son état d'allumage
		String name;
		
		/*
		 * si elle est allumée, on regarde le compteur pour voir quel
		 * état de la flamme on affiche sinon on affiche la torche éteinte
		 */
		if (lit) {
			name = "torch.lit.1";
			
			if (variation < 0.3) {
				name = "torch.lit.2";
			}
		} else {
			name = "torch";
		}
		
		output.drawSprite(this.getSprite(name), getBox());
	}
	
	@Override
	public Box getBox() {
		return new Box(POSITION, SIZE, SIZE);
	}

	@Override
	public boolean hurt(Actor instigator, Damage type, double amount, Vector location) {
		
		switch (type){
			/*
			 * la lumière s'allume avec un dégât de type feu si elle est éteinte en l'absorbant
			 * puisqu'elle renvoie true donc la boule de feu a provoqué un effet et donc disparait
			 * sinon, la boule de feu n'a aucun effet et continue sa route
			 */
			case FIRE:
				if (lit){
					return false;
				} else {
					lit = true;
					
					return true;
				}
			/*
			 * si l'on inflige un dégât de type air ("souffler") la flamme allumée s'éteint
			 */
			case AIR:
				lit = false;
				
				return true;
				
			default:
				return false;
		}
	}
	
	@Override
	public void update(Input input) {
		super.update(input);
		//reduction du compteur pour afficher l'état de la flamme ou remise à l'état initial
		variation -= input.getDeltaTime();
		
		if(variation < 0.0){
			variation = 0.6;
		}
	}
	
	@Override
	public boolean isActive(){
		return lit;
	}
}
