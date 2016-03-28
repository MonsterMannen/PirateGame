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
	private static final int delay = 20;

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		Ship.setPaneSize(
				// (int)anchorPane.getWidth(), 
				// (int)anchorPane.getHeight()-100
				// 1600
				// 900-100
				1366,
				768 - 100
		);

		// put imageViews in list
		images.add(image1);
		
		// test add new
		ImageView view = new ImageView();
		images.add(view);
		anchorPane.getChildren().add(view);

		// create a ship
		Ship ship1 = new PirateShip("The Walrus");
		ship1.setSails(true);
		ship1.setSpeed(2);
		
		Ship mShip = new MerchantShip("xD");
		mShip.setSails(true);
		mShip.setSpeed(1);
		(new Thread(new Bot(mShip))).start();

		// put ships in list
		ships.add(ship1);
		ships.add(mShip);
		
		// test ship name + hp
		shipNameLabel.setText(ship1.getName());
		hpBar.setPrefWidth(100);
		hpBarDamage.setPrefWidth(0);

		setImages();
		initTimer();
		initControls(ship1);
		
	}
	
	private void initControls(Ship ship){
		// add controls
		image1.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent key) {

				switch (key.getCode()) {
					case UP:
						System.out.println("up");
						ship.setDirection(Direction.north);
						image1.setRotate(180);
						break;
					case RIGHT:
						System.out.println("right");
						ship.setDirection(Direction.east);
						image1.setRotate(-90);
						break;
					case DOWN:
						System.out.println("down");
						ship.setDirection(Direction.south);
						image1.setRotate(0);
						break;
					case LEFT:
						System.out.println("left");
						ship.setDirection(Direction.west);
						image1.setRotate(90);
						break;
					case SPACE:
						System.out.println("space");
						PirateShip pship = (PirateShip) ship;
						ImageView fireCannonView = pship.fireCannon();
						anchorPane.getChildren().add(fireCannonView);
						//ship1.damage(10);
						//hpBar.setPrefWidth(ship1.getHp());
						break;
						
					case ESCAPE:
						System.exit(0);
						break;
				}

			}

		});
		image1.setFocusTraversable(true);
	}

	// connect imageView to ship
	private void setImages() {
		int i = 0;
		for (Ship ship : ships) {
			images.get(i).setImage(ship.getImage());
			images.get(i).setFitHeight(150);
			images.get(i).setFitWidth(50);
			i++;
		}
	}
	
	private void updateGUI() {
		int i = 0;
		for (Ship ship : ships) {
			double x = ship.getPosition().getX();
			double y = ship.getPosition().getY();
			images.get(i).setX(x);
			images.get(i).setY(y);
			if(i == 0){
				// for playership. test
				shipInfoPane.setLayoutX(x + 30);
				shipInfoPane.setLayoutY(y + 5);
			}
			i++;
		}
	}

	private void initTimer() {
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				updateGUI();
			}
		}, 0, delay);
	}

}
