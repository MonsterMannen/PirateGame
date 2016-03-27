package pirategame;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import pirategame.Ship.Direction;

/**
 *
 * @author Viktor
 */
public class PirateGameFXMLController implements Initializable {
	
	@FXML
	private AnchorPane anchorPane;
	@FXML
	private Pane waterPane;
	@FXML
	private ImageView image1;
	@FXML
	private Pane hpBar;
	@FXML
	private Pane hpBarDamage;
	@FXML
	private Pane shipInfoPane;
	@FXML
	private Label shipNameLabel;
	
	private List<Ship> ships = new ArrayList<>();
	private List<ImageView> images = new ArrayList<>();
	public static Timer timer = new Timer();
	private static int delay = 20;
	
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		Ship.setPaneSize(
			1600,	//(int)anchorPane.getWidth(), 
			900-100		//(int)anchorPane.getHeight()-80
		);
		
		// put imageViews in list
		images.add(image1);
		
		// create a ship
		Ship ship1 = new PirateShip("The Walrus");
		ship1.setSails(true);
		ship1.setSpeed(2);
		
		// put ships in list
		ships.add(ship1);
		
		shipNameLabel.setText(ship1.getName());
		hpBar.setPrefWidth(100);
		hpBarDamage.setPrefWidth(0);
		
		setImages();
		initTimer();
		
		// add controls
		image1.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent key) {
				
				switch(key.getCode()){
					case UP:
						System.out.println("up");
						ship1.setDirection(Direction.north);
						image1.setRotate(180);
						break;
					case RIGHT:
						System.out.println("right");
						ship1.setDirection(Direction.east);
						image1.setRotate(-90);
						break;
					case DOWN:
						System.out.println("down");
						ship1.setDirection(Direction.south);
						image1.setRotate(0);
						break;
					case LEFT:
						System.out.println("left");
						ship1.setDirection(Direction.west);
						image1.setRotate(90);
						break;
					case SPACE:
						System.out.println("space");
						PirateShip ship = (PirateShip) ship1;
						ImageView fireCannonView = ship.fireCannon();
						anchorPane.getChildren().add(fireCannonView);
						//ship1.damage(10);
						//hpBar.setPrefWidth(ship1.getHp());
						break;
				}
				
            }

        });
        image1.setFocusTraversable(true);
	}
	
	private void setImages(){
		int i = 0;
		for(Ship ship : ships){
			images.get(i++).setImage(ship.getImage());
		}
	}
	
	private void updateGUI(){
		int i = 0;
		for(Ship ship : ships){
			double x = ship.getPosition().getX();
			double y = ship.getPosition().getY();
			images.get(i).setX(x);
			images.get(i).setY(y);
			shipInfoPane.setLayoutX(x+30);
			shipInfoPane.setLayoutY(y+5);
			i++;
		}
	}
	
	private void initTimer(){
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				updateGUI();
			}
		}, 0, delay);
	}
	
}
