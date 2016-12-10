package platform.game;

import platform.util.Box;
import platform.util.Input;
import platform.util.Vector;
import platform.util.Output;
import platform.util.Sprite;

/**
 * Classe de base de tous les acteurs simulés dans une monde (World)
 */
public abstract class Actor implements Comparable<Actor> {
	
	//monde auquel est rattaché l'acteur
	private World world;

	//faire évoluer l'acteur au cours du temps	
	public void update(Input input) {}
	
	//faire évoluer l'acteur avant la physique et après le dessin
	public void preUpdate(Input input) {}
	
	//faire évoluer l'acteur après la physique
	public void postUpdate(Input input) {}
	
	//dessiner l'acteur
	public void draw(Input input, Output output) {}
	
	/**
	 * @return int, la valeur d'importance de l'acteur décrite par une 'priorité'
	 */
	public abstract int getPriority();
	
	/**
	 * Méthode d'interactions entre les acteurs lorsque des 'dégâts' sont infligés
	 * @param instigator, instigateur des dégâts
	 * @param type, type de dégâts (air, feu,...)
	 * @param amount, quantité de dégâts
	 * @param location, localisation des dégâts
	 * @return boolean, true si le dégât a eu effet, false sinon
	 */
	public boolean hurt(Actor instigator, Damage type, double amount, Vector location){
		//par defaut, lors d'une interaction, les dégâts n'ont pas d'effets
		return false;
	}
	
	/**
	 * Méthode d'interactions entre les acteurs, si l'on peut passer à travers ou pas
	 * s'il est solide, on ne peut pas passer à travers
	 * s'il n'est pas solide, on peut passer à travers
	 * @return boolean, true si l'acteur est solide, false sinon
	 */
	public boolean isSolid(){
		//par défaut, aucun acteur n'est solide
		return false;
	}
	
	/**
	 * @return Box, 'boîte' dans laquelle est inscrit l'acteur
	 */
	public Box getBox(){
		return null;
	}
	
	/**
	 * @return Vector, vecteur de la position du centre de la box dans laquelle est inscrit l'acteur
	 */
	public Vector getPosition(){
		Box box = getBox();
		
		if (box == null) {
			return null;
		}
		
		return box.getCenter();
	}
	
	public void interact(Actor other) {}
	
	/**
	 * méthode de comparaison de la priorité des acteurs
	 * @other Actor, acteur auquel 'this' est comparé
	 * @return int, résultat de la comparaison des priorités
	 * 1 : l'acteur en question a une plus petite priorité que celui auquel il est comparé
	 * -1 : l'acteur en question a une plus grande priorité que celui auquel il est comparé
	 * 0 : les deux acteurs ont la même priorité
	 */
	@Override
	public int compareTo(Actor other){
		if(getPriority() < other.getPriority()){
			return 1;
		}
		if(getPriority() > other.getPriority()){
			return -1;
		}
		else{
			return 0;
		}
	}
	
	/**
	 * @param nWorld, nouveau monde à associer à l'acteur
	 */
	public void register(World nWorld){
		world = nWorld;
	}
	
	/**
	 * remet le monde auquel est associé l'acteur à null
	 */
	public void unregister(){
		world = null;
	}
	
	/**
	 * @return World, monde dans lequel évolue l'acteur
	 */
	protected World getWorld(){
		return world;
	}
	
	/**
	 * Récupération de la sprite correspondante
	 * @param name, nom du fichier de la sprite sans extension
	 * @return Sprite, 'image' chargée
	 */
	public Sprite getSprite(String name){
		return world.getLoader().getSprite(name);
	}
	
	/**
	 * Vitesse verticale de l'acteur
	 * @return double, vitesse sur l'axe y
	 */
	public double getVelocityY(){
		return 0.0;
	}
}
