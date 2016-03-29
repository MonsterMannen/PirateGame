package pirategame;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.ImageView;
import pirategame.Ship.Direction;

/**
 * AI
 * @author viktor
 */
public class Bot implements Runnable {
	
	private Ship ship;
	private ImageView view;
	
	public Bot(Ship s, ImageView v){
		ship = s;
		view = v;
		s.setAutoChangeDir(true);
	}

	@Override
	public void run() {
		while(ship.getHp() > 0){
			// change direction at random
			int delayTime = (int) (Math.random()*5);
			
			try {
				Thread.sleep(delayTime*1000);
			} catch (InterruptedException ex) {
				// error that never occurs :^)
			}
			
			int randomDir = (int) (Math.random()*4);
			
			switch(randomDir){
				case 0:
					ship.setDirection(Direction.north);
					view.setRotate(180); 
					break;
				case 1:
					ship.setDirection(Direction.east);
					view.setRotate(-90); 
					break;
				case 2:
					ship.setDirection(Direction.south);
					view.setRotate(0); 
					break;
				case 3:
					ship.setDirection(Direction.west);
					view.setRotate(90); 
					break;
			}
			
		}
	}
	
}
