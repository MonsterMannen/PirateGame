package pirategame;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Viktor
 */
public class MerchantShip extends Ship {
	
	// default constructor
	public MerchantShip(){
		this("Merchant Ship");
	}
	
	// constructor with name
	public MerchantShip(String name){
		super();
		this.name = name;
		img = (BufferedImage) readImage();
	}
	
	private Image readImage(){
		Image image = null;
		try {
			image = ImageIO.read(new File("src/images/Boat1.png"));
		} catch (IOException ex) {
			System.out.println("can't load image");
		}
		return image;
	}
	
	@Override
	public String toString(){
		return "merchantship: " + name;
	}
}
