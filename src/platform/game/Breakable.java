package platform.game;

import platform.game.Block;
import platform.util.Vector;
import platform.util.Box;
import platform.util.Sprite;
import platform.util.Input;
import platform.util.Output;


/**
 * extension de la classe Block
 * ces blocs ont la particularités de prendre feu
 * et d'exploser à terme
 */
public class Breakable extends Block {
	//durée de vie du bloc
	private double life;
	//feu qui s'affiche dessus
	private Fire fire;
	
	public Breakable(Box box, Sprite sprite) {
		super(box, sprite);
		
		life = 100.0;
		//l'instance de feu associée n'est pas visible car elle a une durée nulle
		fire = new Fire(this.getPosition(), 0.0);
	}
	
	public double getLife() {
		return life;
	}
	
	@Override
	public boolean hurt(Actor instigator, Damage type, double amount, Vector location){
		switch (type) {
			//si le bloc subit un dégât de type feu
			case FIRE:
				/*relancement du feu dans le cas où plusieurs boules de feu seraient lancées
				 * supression du feu actuel
				 */
				super.getWorld().unregister(fire);
				//création d'un nouveau feu avec une durée de 3 secondes
				fire = new Fire(this.getPosition(), 3.0);
				super.getWorld().register(fire);
				//l'espérance de vie du bloc est réduite
				life -= 20;
				//les dégâts ont eu un effet
				return true;
				
			default:
				//les dégâts n'ont pas eu d'effet
				return false;
		}
	}
	
	
	@Override
	public void draw(Input input, Output output){
		super.draw(input, output);
		
		//si la vie est en dessous de 50% on affiche une boite vide sinon une boite différente
		if (life <= 50) {
			output.drawSprite(this.getSprite("box.empty"), getBox());
			
		} else {
			output.drawSprite(this.getSprite("box.double"), getBox());
		}
		
	}
	
	@Override
	public void update(Input input) {
		//si le feu brule sur la boite, la vie de celle-ci est réduite
		if (fire.getCooldown() > 0) {
			life -= input.getDeltaTime()*20;
		}
		//si la vie est nulle ou négative, la boîte 'meurt'
		if (life <= 0.0) {
			endLife();
		}
	}
	
	/**
	 * mort de la boîte
	 */
	private void endLife() {
		//de la fumée est créée
		getWorld().register(new Smoke(this.getPosition(), 2));
		//le feu est supprimé
		getWorld().unregister(fire);
		//la boîte est supprimée
		getWorld().unregister(this);
	}
}
