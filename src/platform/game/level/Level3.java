package platform.game.level;

import platform.game.*;
import platform.util.Box;
import platform.util.Vector;

public class Level3  extends Level{
	@Override
    public void register(World world) {
        super.register(world);
        //mise en place des controles de menus, fond et limites
        super.niveauDeJeu(world);
        //préparation du prochain niveau qui est lui-même au cas où on veut recommencer ce niveau
        world.setNextLevel(new Level3());
        
        //lignée de blocs à gauche
        world.register(new Block (new Box(new Vector(-16.0, 0.0), 1, 1), world.getLoader().getSprite("stone.1")));
        world.register(new Block(new Box(new Vector(-15.0, -1.0), 3, 1), world.getLoader().getSprite("stone.3")));
        world.register(new Block(new Box(new Vector(-12.0, -1.0), 3, 1), world.getLoader().getSprite("stone.3")));
        world.register(new Block(new Box(new Vector(-9.0, -1.0), 3, 1), world.getLoader().getSprite("stone.3")));
        world.register(new Block(new Box(new Vector(-6.0, -1.0), 3, 1), world.getLoader().getSprite("stone.3")));
        world.register(new Block(new Box(new Vector(-3.0, -1.0), 3, 1), world.getLoader().getSprite("stone.3")));
        
        //escargots
        world.register(new Snail(new Vector(-11.0, 0.0), new Vector(0.1,0.0)));
        world.register(new Snail(new Vector(-12.0, 0.0), new Vector(0.1,0.0)));
        world.register(new Snail(new Vector(-10.0, 0.0), new Vector(0.1,0.0)));
        world.register(new Snail(new Vector(-9.0, 0.0), new Vector(0.1,0.0)));
        world.register(new Snail(new Vector(12.0, 0.0), new Vector(0.1,0.0)));
        world.register(new Snail(new Vector(12.5, 0.0), new Vector(0.1,0.0)));
        world.register(new Snail(new Vector(12.75, 0.0), new Vector(0.1,0.0)));
        world.register(new Snail(new Vector(13.0, 0.0), new Vector(0.1,0.0)));
        
        //slimes
        world.register(new Slime(new Vector(6.0, -8.0)));
        world.register(new Slime(new Vector(4.0, -8.0)));
        world.register(new Slime(new Vector(2.0, -8.0)));
        
        //levier pour déplacer le bloc
        Lever lever1 = new Lever(new Vector (-2.0, 0.0), 15.0);
        world.register(lever1);
        world.register(new Mover(new Box(new Vector(0.0, -2.0), 3, 1), world.getLoader().getSprite("stone.broken.3"), new Vector(0.0, 10.0), lever1));
        
        //quelques vies
        world.register(new Heart(new Vector(0.0, 11.0)));
        
        //lignée de blocs à droite
        world.register(new Block(new Box(new Vector(3.0, -1.0), 3, 1), world.getLoader().getSprite("stone.3")));
        world.register(new Block(new Box(new Vector(6.0, -1.0), 3, 1), world.getLoader().getSprite("stone.3")));
        world.register(new Block(new Box(new Vector(9.0, -1.0), 3, 1), world.getLoader().getSprite("stone.3")));
        world.register(new Block(new Box(new Vector(12.0, -1.0), 3, 1), world.getLoader().getSprite("stone.3")));
        world.register(new Block(new Box(new Vector(15.0, -1.0), 3, 1), world.getLoader().getSprite("stone.3")));
        
        //blocs horizontaux à gauche
        world.register(new Breakable(new Box(new Vector(-7.0, -2.0), 1, 1), world.getLoader().getSprite("box.double")));
        world.register(new Block(new Box(new Vector(-7.0 , -5.5), 1, 6), world.getLoader().getSprite("stone.7")));
        world.register(new Breakable(new Box(new Vector(-6.0, -8.0), 1, 1), world.getLoader().getSprite("box.double")));
        world.register(new Block(new Box(new Vector(-6.0 , -4.5), 1, 6), world.getLoader().getSprite("stone.7")));
        
        //lignée de blocs au niveau de la porte
        world.register(new Block(new Box(new Vector(-6.0, -9.0), 3, 1), world.getLoader().getSprite("stone.3")));
        world.register(new Block(new Box(new Vector(-3.0, -9.0), 3, 1), world.getLoader().getSprite("stone.3")));
        world.register(new Block(new Box(new Vector(0.0, -9.0), 3, 1), world.getLoader().getSprite("stone.3")));
        
        //escalier de blocs
        world.register(new Block(new Box(new Vector(3.0, -9.0), 3, 1), world.getLoader().getSprite("stone.3")));
        world.register(new Block(new Box(new Vector(6.0, -9.0), 3, 1), world.getLoader().getSprite("stone.3")));
        world.register(new Block(new Box(new Vector(9.0, -9.0), 3, 1), world.getLoader().getSprite("stone.3")));
        
        world.register(new Block(new Box(new Vector(6.0, -8.0), 3, 1), world.getLoader().getSprite("stone.3")));
        world.register(new Block(new Box(new Vector(9.0, -8.0), 3, 1), world.getLoader().getSprite("stone.3")));
        
        world.register(new Block(new Box(new Vector(9.0, -7.0), 3, 1), world.getLoader().getSprite("stone.3")));
        world.register(new Block(new Box(new Vector(12.0, -7.0), 3, 1), world.getLoader().getSprite("stone.3")));
        world.register(new Block(new Box(new Vector(15.0, -7.0), 3, 1), world.getLoader().getSprite("stone.3")));
        world.register(new Block(new Box(new Vector(18.0, -7.0), 3, 1), world.getLoader().getSprite("stone.3")));
        
        //trampolines 
        world.register(new Jumper(new Vector(12.0, -6.0)));
        world.register(new Spike(new Vector(13.0, -6.25)));
        world.register(new Jumper(new Vector(14.0, -6.0)));
        world.register(new Spike(new Vector(15.0, -6.25)));
        world.register(new Jumper(new Vector(16.0, -6.0)));
        world.register(new Spike(new Vector(17.0, -6.25)));
        world.register(new Jumper(new Vector(18.0, -6.0)));
        
        //blocs horizontaux à droite avec piques et vie
        world.register(new Block(new Box(new Vector(19.0 , 2.0), 1, 6), world.getLoader().getSprite("stone.7")));
        world.register(new Block(new Box(new Vector(19.0 , -4.0), 1, 6), world.getLoader().getSprite("stone.7")));

        world.register(new Spike(new Vector(19.0, 5.25)));
        world.register(new Heart(new Vector(19.0, 7.0)));
        
        //boites inflammables sur l'escalier
        world.register(new Breakable(new Box(new Vector(10.0, -7.0), 1, 1), world.getLoader().getSprite("box.double")));
        world.register(new Breakable(new Box(new Vector(10.0, -6.0), 1, 1), world.getLoader().getSprite("box.double")));
        world.register(new Breakable(new Box(new Vector(10.0, -5.0), 1, 1), world.getLoader().getSprite("box.double")));
        world.register(new Breakable(new Box(new Vector(10.0, -4.0), 1, 1), world.getLoader().getSprite("box.double")));
        world.register(new Breakable(new Box(new Vector(10.0, -3.0), 1, 1), world.getLoader().getSprite("box.double")));
        world.register(new Breakable(new Box(new Vector(10.0, -2.0), 1, 1), world.getLoader().getSprite("box.double")));
        
        //clefs
        Key key1 = new Key(new Vector(-16.0, 3.0), Color.RED);
        world.register(key1);
        
        Key key2 = new Key(new Vector(-15, 4.0), Color.GREEN);
        world.register(key2);
        
        world.register(new Key(new Vector(-14.0, 5.0), Color.BLUE));
        
        Key key3 = new Key(new Vector(11.0, -2), Color.YELLOW);
        world.register(key3);
        
        Key key4 = new Key(new Vector(11.0, -3), Color.BLUE);
        world.register(key4);
        
        //Torches
        Torch torch1 = new Torch(new Vector(-1.0, -4.0), true);
        world.register(torch1);
        
        Torch torch2 = new Torch(new Vector(0.0, -4.0), true);
        world.register(torch2);
        
        Torch torch3 = new Torch(new Vector(1.0, -4.0), true);
        world.register(torch3);
        
        /*
         * regroupement de signaux
         * il faut attrapper les clefs sauf la bleue (trop difficile à attraper
         * et éteindre les torches
         */
        And signal = new And(key1, key2);
        signal = new And(signal, key3);
        signal = new And(signal, key4);
        signal = new And(signal, new Not(torch1));
        signal = new And(signal, new Not(torch2));
        signal = new And(signal, new Not(torch3));
        
        //sortie vers niveau 3 et indicateur
        world.register(new Exit(new Vector(-4.0, -8.0), signal, new Selection()));
        world.register(new ExitIndic(new Vector(-2.0, -8.0))); 
        
        //mise en place du joueur et de son overlay avec la méthode de Level
        miseEnPlaceJoueur(world, new Vector(-6.5, 7.0));
	}
	
}
