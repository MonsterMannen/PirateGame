package pirategame;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

/**
 * @author Viktor
 */
public abstract class Ship {
	protected static Timer timer = new Timer();
	protected static int delay = 20;
	public static int paneWidth;
	public static int paneHeight;
	protected Point position;
	protected String name;
	protected int speed;
	protected int hp;
	protected final int hp_max = 100;
	protected boolean sailsUp;
	protected Direction direction;
	protected BufferedImage img;
	protected boolean autoChangeDir;
	
	protected enum Direction {
		north, northeast, east, southeast,
		south, southwest, west, northwest
	}
	
	public Ship(){
		// init variables
		position = new Point();
		direction = Direction.south;
		speed = 0;
		sailsUp = false;
		hp = hp_max;
		autoChangeDir = false;
		
		// init timer
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				update();
			}
		}, 0, delay);
	}
	
	public static void setPaneSize(int x, int y){
		paneWidth = x;
		paneHeight = y;
	}
	
	protected void update(){
		move();
		checkCrash();
	}
	
	protected void move(){
		if(!sailsUp) return;
		
		switch(direction){
			case north: 
				position.y -= speed;
				break;
				
			case northeast:
				position.y -= speed;
				position.x += speed;
				break;	
					
			case east:
				position.x += speed;
				break;
				
			case southeast:
				position.y += speed;
				position.x += speed;
				break;
				
			case south:
				position.y += speed;
				break;
				
			case southwest:
				position.y += speed;
				position.x -= speed;
				break;
				
			case west:
				position.x -= speed;
				break;
				
			case northwest:
				position.y -= speed;
				position.x -= speed;
				break;
		}
	}
	
	private void checkCrash(){
		if(!autoChangeDir) return;
		
		if(getPosition().getY() > paneHeight){
			setDirection(Direction.north);
		}else if(getPosition().getX() > paneWidth){
			setDirection(Direction.west);
		}else if(getPosition().getX() < 0){
			setDirection(Direction.east);
		}else if(getPosition().getY() < 0){
			setDirection(Direction.south);
		}
	}
	
	public void damage(int dmg){
		hp -= dmg;
		if(hp < 0) hp = 0;
	}
	
	public String getName(){
		return name;
	}
	
	public int getHp(){
		return hp;
	}
	
	public void setDirection(Direction dir){
		direction = dir;
	}
	
	public Point getPosition(){
		return position;
	}
	
	public void setSails(boolean b){
		sailsUp = b;
	}
	
	public void setSpeed(int amount){
		speed = amount;
	}
	
	public void setAutoChangeDir(boolean b){
		autoChangeDir = b;
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
	
}
