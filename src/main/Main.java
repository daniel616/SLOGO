package main;

import gui.MasterInterface;
import javafx.application.Application;
import javafx.stage.Stage;
import utilities.MessageFactory;

/**
 * 
 * @author Daniel Instantiates all starting classes and the user display, and
 *         populates the scene with nodes from classes.
 */

public class Main extends Application {
	private final String INFO="SLOGO commands supports variable declaration, stored commands, and recursion. "
			+ "Try the load library button for a demonstration."
			+ "When using multiple turtles, try using commands such as 'fd id' to separate turtles.";

	public void start(Stage primaryStage) {
		try {
			MasterInterface mi = new MasterInterface(primaryStage);
			primaryStage.setScene(mi.getScene());
			primaryStage.setTitle("SLogo");
			primaryStage.show();
			MessageFactory.showMessage(INFO);
		} catch (Exception e) {
			System.out.println("OH HO HO IT'S AN EXCEPTION FROM MAIN");
		}
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}