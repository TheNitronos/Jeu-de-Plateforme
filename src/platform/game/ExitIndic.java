package platform.game;

import platform.util.Vector;

public class ExitIndic extends SimpleSprite {
	//pour afficher un panneau il suffit de connaitre la position où l'afficher
	public ExitIndic(Vector nPos) {
		//le contrstructeur de SimpleSprite se charge du reste
		super("exit", nPos, 1.0, false, 1);
	}	
}