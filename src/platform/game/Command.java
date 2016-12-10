package platform.game;

import com.sun.glass.events.KeyEvent;
import platform.game.level.Selection;
import platform.util.Input;

/**
 * Classe qui permet de gérer les commandes générales
 * dans un niveau, celui-ci est intégré dans les niveaux
 */
public class Command extends Actor{
	
	private World world;
	
	public Command(World nWorld){
		world = nWorld;
	}
	
	@Override
	public int getPriority(){
		//avec une énorme priorité personne n'interagit avec les commandes
		return Integer.MAX_VALUE;
	}
	
	@Override
	public void update(Input input){
		/* si la touche R est pressée, on passe au niveau
		 * qui est le niveau actuel puisque <next> n'a pas été
		 * modifié
		 */
		if (input.getKeyboardButton(KeyEvent.VK_R).isPressed()) {
			world.nextLevel();
		}
		
		/* si la touche q est pressée, on selectionne une instance
		 * du niveau de selection et on y passe
		 */
		if (input.getKeyboardButton(KeyEvent.VK_Q).isPressed()) {
			world.setNextLevel(new Selection());
			world.nextLevel();
		}
	}
}
