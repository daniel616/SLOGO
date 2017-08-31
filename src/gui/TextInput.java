/**
 * Written by Alison Huang
 * 
 * This is one Node that will be added to the overall GUI Scene
 * Features:
 * 		Text box for user to input commands (if lines exceed dimensions of box, a scroll automatically appears)
 * 		Execute button to send one raw string to back-end to get parsed
 * 		Clear button to clear contents of the box
 */

package gui;

import java.util.ResourceBundle;

import controls.Controller;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class TextInput extends Group {
	
	private static final String CLEAR = "Clear";
	private static final String EXCECUTE = "Execute";
	private static final int BUTTON_HEIGHT = 150;
	private static final int BUTTON_WIDTH = 300;
	private TextArea input = new TextArea();
	private BorderPane bp = new BorderPane();
	private HBox panel = new HBox();
	private Button btn_execute = new Button();
	private Button btn_clear = new Button();
	private Controller ct;
	private ResourceBundle myResources;
	
	public TextInput(Controller c){
		ct = c;
		myResources = ct.getResourceBundle();
		input.setPrefWidth(BUTTON_WIDTH);
		input.setPrefHeight(BUTTON_HEIGHT);
		this.getChildren().add(input);
		makeButtonPanel();
	}
	
	public void setText(String text) {
		input.setText(text);
	}
	
	public void setLanguage(ResourceBundle resource){
		btn_execute.setText(resource.getString(EXCECUTE));
		btn_clear.setText(resource.getString(CLEAR));
	}
	
	private void makeButtonPanel(){
		makeBtns();
		panel.getChildren().addAll(btn_execute, btn_clear);
		bp.setBottom(panel);
		this.getChildren().add(bp);
		bp.setCenter(input);
	}
	
	private void makeBtns(){
		btn_execute.setText(myResources.getString(EXCECUTE));
		btn_clear.setText(myResources.getString(CLEAR));
		btn_execute.setOnAction(e -> {
			ct.parseInput(input.getText());
		});
		btn_clear.setOnAction(e -> input.clear());
	}
}