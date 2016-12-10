package platform.game;

import platform.util.Box;
import platform.util.Vector;
import platform.util.Input;
import platform.util.Output;
import platform.util.Sprite;
import platform.game.level.*;

/**
 * block qui affiche les niveaux à l'écran de sélection
 */
public class LevelBlock extends Block {
	//numéro du niveau
	int level;
	//nom de niveau
	Level levelName;
	
	public LevelBlock(int nLevel, Box nBox, Sprite nSprite, Level nLevelName) {
		super(nBox, nSprite);
		level = nLevel;
		levelName = nLevelName;
	}
	
	@Override
	public void draw(Input input, Output output){
		super.draw(input, output);
		
		//formation du sprite correspondant et de la Box en fonction de la position de la taille divisée par 3
		output.drawSprite(super.getSprite("digit." + level), new Box(super.getPosition(), super.getBox().getHeight()/3, super.getBox().getWidth()/3));
	}
	
	@Override
	public void update(Input input) {
		if (input.getMouseButton(1).isPressed()) {
			//si le clic a eu lieu dans la boite, on passe au niveau correspondant
			if (isInBox(input.getMouseLocation(), super.getBox())) {
				getWorld().setNextLevel(levelName);
				getWorld().nextLevel();
			}
		}
	}
	
	/**
	 * Méthode pour évaluer les clics 
	 * @param Position, position du clic
	 * @param frame, Box dans laquelle le clic doit avoir lieu
	 * @return true si le clic a lieu dans la Box, false sinon
	 */
	private boolean isInBox(Vector Position, Box frame) {
		if (Position.getX() > frame.getMax().getX()) {
			return false;
		}
		if (Position.getY() > frame.getMax().getY()) {
			return false;
		}
		if (Position.getX() < frame.getMin().getX()) {
			return false;
		}
		if (Position.getY() < frame.getMin().getY()) {
			return false;
		}
		
		return true;
	}
}
