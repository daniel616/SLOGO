/**
 * Written by Alison Huang
 * 
 * This is one Node that will be added to the GUI scene
 * Features:
 * 		Text box for commands to be displayed (if lines exceed dimensions of box, a scroll will automatically appear)
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


public class ConsoleOutput extends Group {
	
	private static final int OUTPUT_HEIGHT = 150;
	private static final int OUTPUT_WIDTH = 300;
	private TextArea output = new TextArea();
	private BorderPane bp = new BorderPane();
	private HBox panel = new HBox();
	private Button btn_clear = new Button();
	private Controller ct;
	private ResourceBundle myResources;

	
	public ConsoleOutput(Controller control){
		ct = control;
		myResources = ct.getResourceBundle();
		output.setPrefWidth(OUTPUT_WIDTH); //hard-coded for now, we'll fix this later
		output.setPrefHeight(OUTPUT_HEIGHT);
		this.getChildren().add(output);
		makeButtonPanel();
		bp.setBottom(panel);
		this.getChildren().add(bp);
		bp.setCenter(output);
	}
	
	public void setLanguage(ResourceBundle resource){
		btn_clear.setText(resource.getString("Clear"));
	}
	
	private void makeButtonPanel(){
		makeClearBtn();
		panel.getChildren().add(btn_clear);
	}
	
	private void makeClearBtn(){
		btn_clear.setText(myResources.getString("Clear"));
		btn_clear.setOnAction(e -> output.clear());
	}
	
	public void setText(String s){ //This method will be called by someone so that descriptions of commands can be displayed
		output.setText(s + "\n");
	}
	
	
}
