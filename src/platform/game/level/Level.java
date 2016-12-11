package platform.game.level;

import platform.game.Actor;
import platform.game.Background;
import platform.game.World;
import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Sprite;
import platform.util.Vector;
import platform.game.Command;
import platform.game.Limits;
import platform.game.Overlay;
import platform.game.Player;

/**
 * Base class for level factories, which provides fade in transition. Subclasses
 * are requires to override <code>register</code>.
 */
public abstract class Level extends Actor {
	//compteur pour le mise en place de l'effet de fondu
    private double fadein;

    public Level() {
        fadein = 1.1;
    }
    
    @Override
    public int getPriority() {
    	//priorité maximale pour éviter les interactions
        return Integer.MAX_VALUE;
    }

    @Override
    public void update(Input input) {
        fadein -= input.getDeltaTime();
        //si l'effet de fondu est terminé, on supprime le niveau
        if (fadein <= 0.0) {
            getWorld().unregister(this);
        }
    }

    @Override
    public void draw(Input input, Output output) {
    	//dessin de l'effet de fondu avec du 'noir'
        Sprite sprite = getSprite("pixel.black");
        output.drawSprite(sprite, output.getBox(), 0.0, fadein);
    }
    
    /** @return a new instance of default level */
    public static Level createDefaultLevel() {
    	//le niveau par défaut est le niveau de selection avec les boîtes
        return new Selection();
    }
    /*
	 * méthode pour la création de nouveaux niveaux de jeu en mettant en place, les commandes
	 * de menus, le fond et les limites de jeu
	 */
    protected void niveauDeJeu(World nWorld) {
    	nWorld.register(new Command(nWorld));
    	nWorld.register(new Background());	
        nWorld.register(new Limits(new Box(new Vector(0.0,0.0), 60, 60)));
    }
    /*
     * création du player dans le niveau et de son overlay
     */
    protected void miseEnPlaceJoueur(World nWorld, Vector nPosition){
    	Player player = new Player(new Vector(0, 0), nPosition);
        nWorld.register(player);
        nWorld.register(new Overlay(player));	
    }
}
