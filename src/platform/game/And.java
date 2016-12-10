package platform.game;

import platform.game.Signal;

/**
 *Classe qui sert à combiner deux classes qui implémentent Signal
 *en une seule qui nous informe sur l'état des deux signaux simultanément
 */
public class And implements Signal{
	//deux signaux concernés
	private final Signal signal1;
	private final Signal signal2;
	
	public And(Signal signal1, Signal signal2){
		/*si des signaux données en paramètres sont 'null' on lance une exception
		sinon on construit l'instance*/
		if (signal1==null || signal2 == null){
			throw new NullPointerException();
		} else {
			this.signal1 = signal1;
			this.signal2 = signal2;
		}
	}
	
	/**
	 * le signal est actif si les deux signaux sont actifs
	 * @return boolean, true le signal est actif, false sinon
	 */
	@Override
	public boolean isActive(){
		return signal1.isActive() && signal2.isActive();
	}
}
