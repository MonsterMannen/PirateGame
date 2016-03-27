package pirategame;

import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;

/**
 *
 * @author Viktor
 */
public class PirateShip extends Ship {
	// variables
	private Cannon cannon;
	private List<CannonBallDir> cannonBallsFired = new ArrayList<>();
	private int cannonBallSpeed = 8;
	
	// default constructor
	public PirateShip(){
		this("Pirate Ship");
	}
	
	// constructor with name
	public PirateShip(String name){
		super();
		this.name = name;
		img = (BufferedImage) readImage();
		cannon = new Cannon(paneWidth, paneHeight);
	}
	
	public ImageView fireCannon(){
		Point p = new Point();
		p.x = (int) (position.x + img.getWidth());
		p.y = (int) (position.y + img.getHeight()/2 + 5);
		
		ImageView iv = cannon.fire(p, direction);
		cannonBallsFired.add(new CannonBallDir(direction, iv));
		
		return iv;
		// should fire sideways
	}
	
	@Override
	protected void update(){
		move();
		handleCannonBalls();
	}
	  
	private void handleCannonBalls(){
		for(int i = 0; i < cannonBallsFired.size(); i++){
			int toBeRemoved = -1;
			if(
				cannonBallsFired.get(i).getCannonBall().getX() < 0 
				|| cannonBallsFired.get(i).getCannonBall().getX() > paneWidth 
				|| cannonBallsFired.get(i).getCannonBall().getY() < 0 
				|| cannonBallsFired.get(i).getCannonBall().getY() > paneHeight
			){
				// cannonball outside game area
				cannonBallsFired.get(i).getCannonBall().setX(-20);
				cannonBallsFired.get(i).getCannonBall().setY(0);
				//cannonBallsFired.remove(i);
				toBeRemoved = i;
				System.out.println("removed cb");
			}else{
				CannonBallDir temp = cannonBallsFired.get(i);
				
				switch (temp.getDirection()){
					case north:	
						temp.getCannonBall().setY(
							temp.getCannonBall().getY()-cannonBallSpeed
						);
						break;
					case east:	
						temp.getCannonBall().setX(
							temp.getCannonBall().getX()+cannonBallSpeed
						);
						break;
					case south:	
						temp.getCannonBall().setY(
							temp.getCannonBall().getY()+cannonBallSpeed
						);
						break;
					case west:	
						temp.getCannonBall().setX(
							temp.getCannonBall().getX()-cannonBallSpeed
						);
						break;
				}
			}
			
			if(toBeRemoved != -1){
				cannonBallsFired.remove(toBeRemoved);
			}
		}
	}
	
	private Image readImage(){
		Image image = null;
		try {
			image = ImageIO.read(new File("src/images/PirateShip2.png"));
		} catch (IOException ex) {
			System.out.println("can't load image");
		}
		return image;
	}
	
	@Override
	public String toString(){
		return "pirateship: " + name;
	}
}
