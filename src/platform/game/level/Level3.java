package platform.game.level;

import platform.game.World;
import platform.game.*;

public class Level3  extends Level{
	@Override
    public void register(World world) {
        super.register(world);
        
        //mise en place des controles de menus, fond et limites
        super.niveauDeJeu(world);
        
        //préparation du prochain niveau qui est lui-même au cas où on veut recommencer ce niveau
        world.setNextLevel(new Level3());
	}
	
}
