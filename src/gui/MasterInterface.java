package gui;

import controls.Controller;
import exceptions.ParserExceptions.ParserException;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This class creates the outermost user interface that allows for the creation
 * of multiple slogo environments to run in separate tabs side-by-side. It is
 * called by the Main.java class and instantiates new UserInterface classes
 * to allow for multiple tabs. I did not write the handleKeyInput method, so I
 * am not sure why it is here (~Jake).
 * 
 * Created March 1st, 2017
 * 
 * @author Jake Conroy
 *
 */

public class MasterInterface {
	
	private HBox tabHolder = new HBox();
	private Button newTabButton = new Button("+");
	private TabPane tp = new TabPane();
	private int tabNumber = 0;
	private static final String TAB_NAME = "Turtle Environment ";
	private VBox toPutInScene = new VBox();
	private Scene myScene = new Scene(toPutInScene);
	private static final String DEFAULT_RESOURCE_PACKAGE = "resources/languages/";
	private static final String CSS = "style.css";
	
	public MasterInterface(Stage ps) throws ParserException {
		setUpButton();
		toPutInScene.getChildren().add(newTabButton);
		tabHolder.getChildren().add(tp);
		addUI(new Controller().getMyUI());
		toPutInScene.getChildren().add(tabHolder);
		myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
		//myScene.getStylesheets().setAll(DEFAULT_RESOURCE_PACKAGE+CSS);
	}
	
	public void addUI(UserInterface ui) {
		tabNumber += 1;
		Tab newTab = new Tab(TAB_NAME + tabNumber, ui);
		tp.getTabs().add(newTab);
	}
	
	public Scene getScene() {
		return myScene;
	}
	
	private void setUpButton() {
		newTabButton.setOnAction(e -> {
			try {
				addUI(new Controller().getMyUI());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
	}
	
	private void handleKeyInput (KeyCode code) { //any key inputs?
		if (code == KeyCode.RIGHT){
			
		}
	}
 
}
