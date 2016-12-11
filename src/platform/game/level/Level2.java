package platform.game.level;

import platform.game.*;

import platform.util.Box;
import platform.util.Vector;


public class Level2 extends Level{
	@Override
    public void register(World world) {
        super.register(world);
        
        //mise en place des controles de menus, fond et limites
        super.niveauDeJeu(world);
        
        //préparation du prochain niveau qui est lui-même au cas où on veut recommencer ce niveau
        world.setNextLevel(new Level2());
        
        //boucle pour simplifier l'affichage de la lignée de blocs
        for (int i = -6 ; i <= 8 ; i += 2){
        	world.register(new Block (new Box(new Vector(i, 0), 2, 1), world.getLoader().getSprite("stone.2")));
        }
        //quelques blocs
        world.register(new Block (new Box(new Vector(-7, 1), 2, 1), world.getLoader().getSprite("stone.2")));
        world.register(new Block (new Box(new Vector(7.5, 1), 3, 1), world.getLoader().getSprite("stone.3")));
        world.register(new Block (new Box(new Vector(-7.5, 0), 1, 1), world.getLoader().getSprite("stone.1")));
        world.register(new Block (new Box(new Vector(12.5, -1), 3, 1), world.getLoader().getSprite("stone.3")));
        world.register(new Block (new Box(new Vector(15.5, -1), 3, 1), world.getLoader().getSprite("stone.3")));

        world.register(new Block (new Box(new Vector(8, -6), 2, 1), world.getLoader().getSprite("stone.2")));
        world.register(new Block (new Box(new Vector(6, -6), 2, 1), world.getLoader().getSprite("stone.2")));
        
        world.register(new Block (new Box(new Vector(4, -7), 2, 1), world.getLoader().getSprite("stone.3")));
        world.register(new Block (new Box(new Vector(-1, -7), 2, 1), world.getLoader().getSprite("stone.3")));
        
        world.register(new Block (new Box(new Vector(1.5 , -12), 3, 1), world.getLoader().getSprite("stone.3")));
        
        world.register(new Block(new Box(new Vector(-5.5 , -3.5), 1, 6), world.getLoader().getSprite("stone.7")));
        
        world.register(new Block (new Box(new Vector(-3.5 , -6), 3, 1), world.getLoader().getSprite("stone.3")));
     
        //lignée de piques
        for(int i = 0; i <= 5 ; ++i){
        	world.register(new Spike(new Vector(11.5 + i, -0.25)));
        }
        world.register(new Spike(new Vector(0.5, -11.25)));
        world.register(new Spike(new Vector(2.5, -11.25)));
        
        //torches
        Torch torch1 = new Torch(new Vector(-3.5, 4), false);
        Torch torch2 = new Torch(new Vector(3.5, 4), false);

        world.register(torch1);
        world.register(torch2);
        
        //slime
        Slime slime = new Slime(new Vector(0, 1));
        world.register(slime);
        
        Lever lever1 = new Lever(new Vector(8.5, 2), 4.0);
        world.register(lever1);
        
        world.register(new Door(new Box(new Vector(8.0, 2.0), 2, 1), new And( new And(slime, torch1), torch2), world.getLoader().getSprite("stone.2")));
        //mise en place du joueur et de son overlay avec la méthode de Level
        miseEnPlaceJoueur(world, new Vector(-6.5, 2.0));
        
        world.register(new Mover(new Box(new Vector(10.0, 1.0), 2, 1), world.getLoader().getSprite("stone.broken.2"), new Vector(10.0, -5), lever1));
       
        world.register(new Jumper(new Vector(1.5, -11)));
        
        Key key1 = new Key(new Vector(1.5, -9), Color.RED);
        world.register(key1);
        
        //sortie vers niveau 3 et indicateur
        world.register(new Exit(new Vector(-4.5, -5), key1, new Level3()));
        world.register(new ExitIndic(new Vector(-3.5, -5)));
        //quelques vies
        world.register(new Heart(new Vector(6.5, 2)));
        world.register(new Heart(new Vector(7.5, -5)));
	}
}
