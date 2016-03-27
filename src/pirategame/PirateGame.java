/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pirategame;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Viktor
 */
public class PirateGame extends Application {
	
	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("PirateGameFXML.fxml"));
		
		Scene scene = new Scene(root);
		
		// killing timers on program exit
		stage.setOnCloseRequest((WindowEvent t) -> {
			System.out.println("CLOSING");
			PirateGameFXMLController.timer.cancel();
			Ship.timer.cancel();
		});
		
		stage.setTitle("Pirate Game");
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
}
