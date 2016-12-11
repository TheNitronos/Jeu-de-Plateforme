package platform.game.level;

import platform.game.World;
import platform.game.Block;
import platform.util.Box;
import platform.util.Vector;
import platform.game.Player;
import platform.game.SimpleSprite;
import platform.game.Jumper;
import platform.game.Limits;
import platform.game.Overlay;
import platform.game.Spike;
import platform.game.Lever;
import platform.game.Mover;
import platform.game.Exit;
import platform.game.ExitIndic;
import platform.game.Breakable;
import platform.game.Color;
import platform.game.Command;
import platform.game.Key;
import platform.game.Door;
import platform.game.Snail;


public class Level1 extends Level {
	@Override
    public void register(World world) {
        super.register(world);
        //mise en place des controles de menus, fond et limites
        super.niveauDeJeu(world);
        //préparation du prochain niveau qui est lui-même au cas où on veut recommencer ce niveau
        world.setNextLevel(new Level1());
        //lignée de blocs inférieures
        world.register(new Block(new Box(new Vector(-9.0, 0.0), 3, 1), world.getLoader().getSprite("stone.3")));
        world.register(new Block(new Box(new Vector(-6.0, 0.0), 3, 1), world.getLoader().getSprite("stone.3")));
        world.register(new Block(new Box(new Vector(-3.0, 0.0), 3, 1), world.getLoader().getSprite("stone.3")));
        world.register(new Block(new Box(new Vector(0.0, 0.0), 3, 1), world.getLoader().getSprite("stone.3")));
        world.register(new Block(new Box(new Vector(3.0, 0.0), 3, 1), world.getLoader().getSprite("stone.3")));
        world.register(new Block(new Box(new Vector(6.0, 0.0), 3, 1), world.getLoader().getSprite("stone.3")));
        //lignée de blocs supérieures
        world.register(new Block(new Box(new Vector(-9.0, 2.0), 3, 1), world.getLoader().getSprite("stone.3")));
        world.register(new Block(new Box(new Vector(-6.0, 2.0), 3, 1), world.getLoader().getSprite("stone.3")));
        world.register(new Block(new Box(new Vector(-3.0, 2.0), 3, 1), world.getLoader().getSprite("stone.3")));
        world.register(new Block(new Box(new Vector(0.0, 2.0), 3, 1), world.getLoader().getSprite("stone.3")));
        world.register(new Block(new Box(new Vector(3.0, 2.0), 3, 1), world.getLoader().getSprite("stone.3")));
        world.register(new Block(new Box(new Vector(6.0, 2.0), 3, 1), world.getLoader().getSprite("stone.3")));
        //panneau de sortie
        world.register(new ExitIndic(new Vector(-8.0, 3.0)));
        //serpent
        world.register(new Snail(new Vector(0.0, 3.0), new Vector(0.1,0.0)));
        //bout de pierre avec les piques et le trampoline
        world.register(new Block(new Box(new Vector(7.0, 3.0), 1, 1), world.getLoader().getSprite("stone.1")));
        //panneau de sortie
        world.register(new ExitIndic(new Vector(-8.0, 3.0)));
        //bloc a droite avec piques et trampoline
        world.register(new Block(new Box(new Vector(9.0, -5.0), 3, 1), world.getLoader().getSprite("stone.3")));
        world.register(new Jumper(new Vector(9.0, -4.0)));
        world.register(new Spike(new Vector(8.0, -4.25)));
        world.register(new Spike(new Vector(10.0, -4.25)));
        //trois boites inflammables
        world.register(new Breakable(new Box(new Vector(7.0, 1.0), 1, 1), world.getLoader().getSprite("box.double")));
        world.register(new Breakable(new Box(new Vector(6.0, 1.0), 1, 1), world.getLoader().getSprite("box.double")));
        world.register(new Breakable(new Box(new Vector(5.0, 1.0), 1, 1), world.getLoader().getSprite("box.double")));
        //levier pour déplacer le bloc... ça serte à rien mais c'est drôle
        Lever lever1 = new Lever(new Vector (-5.0, 1.0), 15.0);
        world.register(lever1);
        world.register(new Mover(new Box(new Vector(-8.0, 1), 1, 1), world.getLoader().getSprite("stone.broken.1"), new Vector(3.0, 1.0), lever1));
        //levier pour ouvrir la porte
        Lever lever2 = new Lever(new Vector (-1.0, 3.0), 5.0);
        world.register(lever2);
        world.register(new Exit(new Vector(-10.0, 3.0), lever2, new Level2()));
        //clef pour la boite qui cache le levier du niveau suivant
        Key key1 = new Key(new Vector(-9.0, 1), Color.GREEN);
        world.register(key1); 
        world.register(new Door(new Box(new Vector(-1.0, 3.0), 1.0, 1.0), key1, world.getLoader().getSprite("lock.green")));
        //mise en place du joueur et de son overlay avec la méthode de Level
        miseEnPlaceJoueur(world, new Vector(0.0, 1.0));
	}
}