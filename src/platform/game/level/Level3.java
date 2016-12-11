package platform.game.level;

import platform.game.World;
import platform.game.Block;
import platform.util.Box;
import platform.util.Vector;
import platform.game.Player;
import platform.game.Jumper;
import platform.game.Limits;
import platform.game.Overlay;
import platform.game.Heart;
import platform.game.Spike;
import platform.game.Torch;

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
