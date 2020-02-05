package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainMenuController implements Initializable {

	// Event Listener on Button.onAction
	@FXML
	public void test(ActionEvent event) {
		try {

			Parent InGameViewParent = FXMLLoader.load(getClass().getResource("/application/ChessGame.fxml")); // retrieves
																												// the
																												// location
																												// of
																												// the
																												// fxml
																												// file
			Scene inGameScene = new Scene(InGameViewParent); // initialize the new scene as the fxml we loaded
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow(); // this line gets to the stage
																						// information
			window.setScene(inGameScene); // changes the stage scene to be our new scene
			window.show();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Can't load new game");
		}
	}

	public void startAI(ActionEvent event) {
		try {

			Parent InGameViewParent = FXMLLoader.load(getClass().getResource("/application/ChessGameAI.fxml")); // retrieves
																												// the
																												// location
																												// of
																												// the
																												// fxml
																												// file
			Scene inGameScene = new Scene(InGameViewParent); // initialize the new scene as the fxml we loaded
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow(); // this line gets to the stage
																						// information
			window.setScene(inGameScene); // changes the stage scene to be our new scene
			window.show();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Can't load new game");
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}
}
