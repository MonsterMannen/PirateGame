package pirategame;

import java.util.logging.Level;
import java.util.logging.Logger;
import pirategame.Ship.Direction;

/**
 * AI
 * @author viktor
 */
public class Bot implements Runnable {
	
	private Ship ship;
	
	public Bot(Ship s){
		ship = s;
		s.setAutoChangeDir(true);
	}

	@Override
	public void run() {
		while(ship.getHp() > 0){
			// change direction at random
			int delayTime = (int) (Math.random()*10);
			
			try {
				Thread.sleep(delayTime*1000);
			} catch (InterruptedException ex) {
				// error that never occurs :^)
			}
			
			int randomDir = (int) (Math.random()*4);
			
			switch(randomDir){
				case 0:
					ship.setDirection(Direction.north);
					break;
				case 1:
					ship.setDirection(Direction.east);
					break;
				case 2:
					ship.setDirection(Direction.south);
					break;
				case 3:
					ship.setDirection(Direction.west);
					break;
			}
			
		}
	}
	
}
