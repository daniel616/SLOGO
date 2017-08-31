package main;

import gui.MasterInterface;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * 
 * @author Daniel Instantiates all starting classes and the user display, and
 *         populates the scene with nodes from classes.
 */

public class Main extends Application {

	public void start(Stage primaryStage) {
		try {
			MasterInterface mi = new MasterInterface(primaryStage);
			primaryStage.setScene(mi.getScene());
			primaryStage.setTitle("SLogo");
			primaryStage.show();
		} catch (Exception e) {
			System.out.println("OH HO HO IT'S AN EXCEPTION FROM MAIN");
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}