package pirategame;

import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javax.imageio.ImageIO;
import pirategame.Ship.Direction;

/**
 *
 * @author Viktor
 */
public class Cannon {
	
	private static int paneWidth;
	private static int paneHeight;
	
	//protected Timer timer = new Timer();
	//protected int delay = 20;
	private BufferedImage img;
	private String filePath = "src/images/CannonBall.png";
	
	
	public Cannon(int paneWidth, int paneHeight){
		// set size of gamearea
		Cannon.paneWidth = paneWidth;
		Cannon.paneHeight = paneHeight;
		
		// set image
		img = (BufferedImage) readImage();
	}
	
	/**
	 * Fire cannon!
	 * @param startPos ship pos
	 * @param direction to shoot
	 * @return imageView of cannonball
	 */
	public ImageView fire(Point startPos, Direction direction){
		System.out.println("fire cannon: " + startPos.getX() + ", " + startPos.getY());
		
		ImageView cannonBall = new ImageView();
		cannonBall.setImage(getImage());
		cannonBall.setFitWidth(20);
        cannonBall.setPreserveRatio(true);
		cannonBall.setX(startPos.getX());
		cannonBall.setY(startPos.getY());
		
		return cannonBall;
	}
	
	public WritableImage getImage(){
        WritableImage wr = null;
		wr = new WritableImage(img.getWidth(), img.getHeight());
		PixelWriter pw = wr.getPixelWriter();
		for (int x = 0; x < img.getWidth(); x++) {
			for (int y = 0; y < img.getHeight(); y++) {
				pw.setArgb(x, y, img.getRGB(x, y));
			}
		}
		return wr;
	}
	
	private Image readImage(){
		Image image = null;
		try {
			image = ImageIO.read(new File(filePath));
		} catch (IOException ex) {
			System.out.println("can't load image");
		}
		return image;
	}
	
}
