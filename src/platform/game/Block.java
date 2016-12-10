package platform.game;

import platform.util.Box;
import platform.util.Input;
import platform.util.Sprite;
import platform.util.Output;

/**
 * Actor solide qui ne fait pas grand chose
 */
public class Block extends Actor {
	
	//box dans laquelle il est inscrit
	private Box box;
	//sprite à dessiner
	private Sprite sprite;
   
   public Block(Box nBox, Sprite nSprite){
	   box = nBox;
	   sprite = nSprite;
   }
   
   @Override
	public int getPriority(){
	   	//priorité très basse
		return 0;
   }
   
   @Override
   public void draw(Input input, Output output){
	   if (sprite == null) {
		   throw new NullPointerException();
	   }else {
		   super.draw(input, output);
		   output.drawSprite(sprite, box);
		}  
   }
   
   @Override
   public boolean isSolid(){
	   //la plupart des blocs sont solides
	   return true;
   }
   
   @Override
   public Box getBox(){
	   return box;
   }
}
