package platform.game;

import platform.game.SimpleSprite;
import platform.util.Vector;

public class Fire extends SimpleSprite {
	//pour afficher un feu il suffit de connaitre la position où l'afficher et la durée
	public Fire(Vector nPos, double nCoold) {
		//le contrstructeur de SimpleSprite se charge du reste
		super("fireball", nPos, nCoold, 0.5, true, 1);
	}
}
