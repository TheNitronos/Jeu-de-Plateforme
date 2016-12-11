package platform.game;

import platform.util.Box;
import platform.util.Loader;
import platform.util.Vector;
import platform.game.level.*;

/**
 * Represents an environment populated by actors.
 */
public interface World {

    /** @return associated loader, not null */
    public Loader getLoader();
    
    /**
     * Set viewport location and size.
     * @param center viewport center, not null
     * @param radius viewport radius, positive
     */
    public void setView(Vector center, double radius);
    
    public void register(Actor actor);
    
    public void unregister(Actor actor);
    
    //vecteur gravité du monde
    public default Vector getGravity() {
    	return new Vector(0.0, -9.81);
    }
    
    //savoir quel est le prochain niveau prévu
    public Level getNextLevel();
    
    //indique que la transition vers un autre niveau doit se faire
    public void nextLevel();
    
    //permet de passer au niveau level
    public void setNextLevel(Level level);
    
    //méthode qui gère les interactions régies par les dégâts
    public abstract int hurt(Box area, Actor instigator, Damage type, double amount, Vector location);  
}