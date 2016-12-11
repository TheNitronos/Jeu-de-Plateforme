package platform.game;

import platform.game.Block;
import platform.game.Signal;
import platform.util.Vector;
import platform.util.Box;
import platform.util.Sprite;
import platform.util.Input;
import platform.util.Output;

/**
 * Bloc qui peuvent bouger sous l'interaction d'autres signaux
 */
public class Mover extends Block {
	//position du bloc au signal actif
	private final Vector ON;
	//position du bloc au signal inactif
	private final Vector OFF;
	//signal correspondant
	private final Signal SIGNAL;
	private final Sprite SPRITE;
	//rapport de déplacement intermédiaire
	private double current;

	
	public Mover(Box nBox, Sprite nSprite, Vector nOn, Signal nSignal){
		super(nBox, nSprite);
		OFF = nBox.getCenter();
		ON = nOn;
		SIGNAL = nSignal;
		SPRITE = nSprite;
	}
	
	@Override
	public void update(Input input) {
		super.update(input);
		
		if (SIGNAL.isActive()) {
			//si le signal est actif on augmente le rapport de deplacement une fois à 100% on le laisse à 1
			current += input.getDeltaTime();
			
			if (current > 1.0){
				current = 1.0;
			}
		} else {
			//inverse du cas où le signal est actif
			current -= input.getDeltaTime();
			
			if (current < 0.0) {
				current = 0.0;
			}
		}
	}
	
	@Override
	public Box getBox(){
		/*
		 * la Box correspondante est la boîte initiale que l'on déplace en fonction du rapport
		 * de déplacement et des deux extrêmes de position
		 */
		if (SIGNAL.isActive()){
			return super.getBox().add(ON.sub(OFF).mul(current));
		} else {
			return super.getBox().add(OFF.sub(ON).mul(current).mul(-1.0));
		}
	}
	
	@Override
	public void draw(Input input, Output output){
		//mise à jour de la méthode draw pour qu'elle redessine en fontion de la nouvelle boîte 
		output.drawSprite(SPRITE, getBox());
	}	
}
