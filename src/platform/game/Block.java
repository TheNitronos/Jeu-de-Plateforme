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
	   super(0);
	   box = b;
	   sprite = s;
	   
   }
   public void draw(Input in, Output out){
	   if (sprite != null){
		   super.draw(in, out);
		   out.drawSprite(sprite, box);
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
