package platform.game;

import platform.util.Box;
import platform.util.Vector;
import platform.util.Input;
import platform.util.Output;
import platform.util.Sprite;

public class LevelBlock extends Block {
	int level;
	
	public LevelBlock(int nLevel, Box b, Sprite s) {
		super(b, s);
		level = nLevel;
	}
	
	@Override
	public void draw(Input input, Output output){
		if (super.getSprite("box.empty") != null &&
			super.getSprite("digit.0") != null){
				super.draw(input, output);
				
				if (level < 10 && level > 0) {
					output.drawSprite(super.getSprite("digit." + level), new Box(super.getPosition(), super.getBox().getHeight()/3, super.getBox().getWidth()/3));
				} else if (level < 100 && level > 0){
					output.drawSprite(super.getSprite("digit." + (int)(level/10)), new Box(new Vector(super.getPosition().getX()-super.getBox().getWidth()/6, super.getPosition().getY()), super.getBox().getHeight()/3, super.getBox().getWidth()/3));
					output.drawSprite(super.getSprite("digit." + level%10), new Box(new Vector(super.getPosition().getX()+super.getBox().getWidth()/6, super.getPosition().getY()), super.getBox().getHeight()/3, super.getBox().getWidth()/3));
				}
		}
	}
}
