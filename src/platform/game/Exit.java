package platform.game;

//importations de util
import platform.util.Vector;
import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
//importation de game
import platform.game.Signal;
import platform.game.level.Level;
import platform.game.Player;

/**
 * Porte de sortie
 */
public class Exit extends Actor {
	//prochain niveau auquel on passe en entrant dans la porte
	private final Level LEVEL;
	//signal associé à la porte
	private final Signal SIGNAL;
	private final Vector POSITION;
	private final double SIZE;
	
	public Exit(Vector nPosition, Signal nSignal, Level nLevel) {
		SIGNAL = nSignal;
		POSITION = nPosition;
		LEVEL = nLevel;
		SIZE = 1.0;
	}
	
	@Override
	public Box getBox() {
		/*
		 * création d'une instance de Box
		 * avec le centre de la porte et ses
		 * dimensions
		 */
		return new Box(POSITION, SIZE, SIZE);
	}
	
	@Override
	public int getPriority() {
		/*
		 * si le signal est actif, la priorité croît ainsi le joueur
		 * passe derrière, ce qui donne une impression de passer derrière
		 * et la porte peut interagir avec le joueur du fait que sa propre priorité
		 * est plus grande que celle du joueur
		 */
		if (SIGNAL.isActive()) {
			return 60;
		}
		else{
			return -3;
		}
	}
	
	@Override
	public void interact(Actor other) {
		super.interact(other);
		
		/*
		 * si la porte est en contact avec la Box du joueur
		 * on passe au prochain niveau <LEVEL>
		 * il n'y a pas besoin de contrôler l'état du signal
		 * puisque si le signal n'est pas activé et ne donne pas
		 * la priorité à l'instance d'executer cette méthode,
		 * celle-ci n'a pas lieu
		 */
		if (getBox().isColliding(other.getBox()) 
				&& (other instanceof Player)) {
			Player player = (Player) other;
			//si le joueur est mort, il ne peut pas passé au niveau suivant
			if (!player.isDead()){
				getWorld().setNextLevel(LEVEL);
				getWorld().nextLevel();
			}
			
		}
	}
	
	@Override
	public void draw(Input input, Output output) {
		super.draw(input, output);
		
		String name = "door.";
		
		/*
		 * selon l'état de la porte, on affiche l'une ou l'autre sprite
		 */
		if (SIGNAL.isActive()) {
			name += "open";
		} else {
			name += "closed";
		}
		
		output.drawSprite(getSprite(name), getBox());
	}
}
