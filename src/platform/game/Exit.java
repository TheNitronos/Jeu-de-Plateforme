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


public class Exit extends Actor {
	private final Level LEVEL;
	private final Signal SIGNAL;
	private final Vector POSITION;
	private final double SIZE = 1.0;
	
	public Exit(Vector nPosition, Signal nSignal, Level nLevel) {
		SIGNAL = nSignal;
		POSITION = nPosition;
		LEVEL = nLevel;
	}
	
	@Override
	public Box getBox() {
		return new Box(POSITION, SIZE, SIZE);
	}
	
	@Override
	public int getPriority() {
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
		
		if (getBox().isColliding(other.getBox()) 
				&& (other instanceof Player) && SIGNAL.isActive()) {
			
			getWorld().setNextLevel(LEVEL);
			getWorld().nextLevel();
		}
	}
	
	@Override
	public void draw(Input input, Output output) {
		super.draw(input, output);
		
		String name = "door.";
		
		if (SIGNAL.isActive()) {
			name += "open";
		} else {
			name += "closed";
		}
		
		output.drawSprite(getSprite(name), getBox());
	}
}
