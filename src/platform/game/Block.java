package platform.game;

import platform.util.Box;
import platform.util.Input;
import platform.util.Sprite;
import platform.util.Output;

/**
 * Simple solid actor that does nothing.
 */
public class Block extends Actor {

   private Box box;
   private Sprite sprite;
   
   public Block(Box b, Sprite s){
	   box = b;
	   sprite = s;
	   
   }
   @Override
	public int getPriority(){
		return 0;
   }
   
   public void draw(Input input, Output output){
	   if (sprite != null){
		   super.draw(input, output);
		   output.drawSprite(sprite, box);
	   }
	   
	   
   }
   
   @Override
   public boolean isSolid(){
	   return true;
   }
   
   @Override
   public Box getBox(){
	   return box;
   }
   
  
}
