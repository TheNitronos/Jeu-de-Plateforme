package platform.game;

import platform.util.Box;
import platform.util.Vector;
import platform.util.Input;
import platform.util.Output;
import platform.util.Sprite;
import platform.util.View;
import platform.game.level.*;

public class LevelBlock extends Block {
	int level;
	Level levelName;
	
	public LevelBlock(int nLevel, Box b, Sprite s, Level nLevelName) {
		super(b, s);
		level = nLevel;
		levelName = nLevelName;
	}
	
	@Override
	public void draw(Input input, Output output){
		if (super.getSprite("box.empty") != null &&
			super.getSprite("digit.0") != null){
				super.draw(input, output);
				
				if (level < 10 && level > -1) {
					output.drawSprite(super.getSprite("digit." + level), new Box(super.getPosition(), super.getBox().getHeight()/3, super.getBox().getWidth()/3));
				} else if (level < 100 && level > 0){
					output.drawSprite(super.getSprite("digit." + (int)(level/10)), new Box(new Vector(super.getPosition().getX()-super.getBox().getWidth()/6, super.getPosition().getY()), super.getBox().getHeight()/3, super.getBox().getWidth()/3));
					output.drawSprite(super.getSprite("digit." + level%10), new Box(new Vector(super.getPosition().getX()+super.getBox().getWidth()/6, super.getPosition().getY()), super.getBox().getHeight()/3, super.getBox().getWidth()/3));
				}
		}
	}
	
	@Override
	public void update(Input input) {
		if (input.getMouseButton(1).isPressed()) {
			if (isInBox(input.getMouseLocation(), super.getBox())) {
				getWorld().register(new Smoke(super.getBox().getCenter(), super.getBox().getHeight()*2));
				getWorld().setNextLevel(levelName);
				getWorld().nextLevel();
			}
		}
	}
	
	public boolean isInBox(Vector Position, Box frame) {
		if (Position.getX() > frame.getMax().getX()) {
			return false;
		}
		if (Position.getY() > frame.getMax().getY()) {
			return false;
		}
		if (Position.getX() < frame.getMin().getX()) {
			return false;
		}
		if (Position.getY() < frame.getMin().getY()) {
			return false;
		}
		
		return true;
	}
}
