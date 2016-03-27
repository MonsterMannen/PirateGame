package pirategame;

import javafx.scene.image.ImageView;
import pirategame.Ship.Direction;

/**
 * @author Viktor
 */
public class CannonBallDir {
	private Direction direction;
	private ImageView cannonBall;
	
	public CannonBallDir(Direction d, ImageView c){
		direction = d;
		cannonBall = c;
	}
	
	public ImageView getCannonBall(){
		return cannonBall;
	}
	
	public Direction getDirection(){
		return direction;
	}
}
