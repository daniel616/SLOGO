package gui;

import java.util.ResourceBundle;

import controls.Controller;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import turtleDetails.ReadableState;

public class PopUpSettings extends Stage {
	private static final String DEACTIVATE = "Deactivate";
	private static final String TELL = "Tell";
	private static final String INACTIVE = "Inactive";
	private static final String ACTIVE = "Active";
	private static final String THIS_TURTLE_IS = "ThisTurtle";
	private static final String PEN_POSITION = "PenPosition";
	private static final String PENUP = "PenUp";
	private static final String PENDOWN = "PenDown";
	private static final String UP = "Up";
	private static final String DOWN = "Down";
	private static final String SETPS = "SetPenSize";
	private static final String PEN_SIZE = "PenSize";
	private static final String SETHEADING = "SetHeading";
	private static final String HEADING = "Heading";
	private static final String SETXY = "SetPosition";
	private static final String CHANGE = "Change";
	private static final String X_Y = "XandY";
	private static final String THIS_IS_TURTLE = "ThisIsTurtle";
	private static final String PARAMETERS_OF_THIS_TURTLE = "ParametersTurtle";
	private static final int SIZE = 10;
	public static final int TURTLE_SIZE = 45;
	private VBox myRoot = new VBox();
	private Controller ct;

	private SimpleBooleanProperty switchedOn;

	private ReadableState state;

	private static final String DEFAULT_RESOURCE_PACKAGE = "resources/languages/";
	private static final String CSS = "style.css";
	private ResourceBundle myResources;
	
	public PopUpSettings(Controller c, ReadableState st){
		
		ct = c;
		myRoot.setSpacing(SIZE);
		myResources = ct.getResourceBundle();
		switchedOn = new SimpleBooleanProperty(false);

		state = st;
		makeID();
		makeXandY();
		makeHeading();
		makePenSetting();
		makeActiveSetting();
		//makeImageChooser();
		//makeColorChooser();
		
		Scene s = new Scene(myRoot);
		s.getStylesheets().setAll(DEFAULT_RESOURCE_PACKAGE+CSS);
		this.setScene(s);
		
		this.setTitle(myResources.getString(PARAMETERS_OF_THIS_TURTLE));
	}
	
	private void makeID(){
		Label lab = new Label(myResources.getString(THIS_IS_TURTLE) + state.getID());
		myRoot.getChildren().add(lab);
	}
	
	private void makeXandY(){
		HBox coor = new HBox();
		coor.setSpacing(SIZE);
		Label lab = new Label(myResources.getString(X_Y));
		TextArea xval = new TextArea("" + state.getX());
		Label comma = new Label(",");
		TextArea yval = new TextArea("" + state.getY());
		xval.setMaxWidth(SIZE);
		xval.setMaxHeight(SIZE);	
		yval.setMaxWidth(SIZE);
		yval.setMaxHeight(SIZE);	
		Button btn = new Button(myResources.getString(CHANGE));
		btn.setOnAction(e -> {
			sendToController(myResources.getString(SETXY) + " " + xval.getText() + " " + yval.getText());
		});
		coor.getChildren().addAll(lab, xval, comma, yval, btn);

		myRoot.getChildren().add(coor);
		
	}
	
	private void makeHeading(){
		HBox head = new HBox();
		head.setSpacing(SIZE);
		Label lab = new Label(myResources.getString(HEADING));
		TextArea val = new TextArea();
		val.setText("" + state.getHeading());
		val.setMaxWidth(SIZE);
		val.setMaxHeight(SIZE);
		Button btn = new Button(myResources.getString(CHANGE));
		btn.setOnAction(e -> {
			sendToController(myResources.getString(SETHEADING).split("[|]")[0] + " " + val.getText());
		});
		head.getChildren().addAll(lab, val, btn);
		
		myRoot.getChildren().add(head);
	}
	
	private void makePenSetting(){
		makePickupPutdown();
		makeSizeSlider();
	}
	
	private void makePickupPutdown(){
		ToggleSwitch penToggle = new ToggleSwitch(myResources.getString(PEN_POSITION), myResources.getString(DOWN), myResources.getString(UP), 
				(state.penDown() ? myResources.getString(DOWN) : myResources.getString(UP)));
		penToggle.setAction(e -> {
			penToggle.toggle();
			sendToController(penToggle.getText().equals(myResources.getString(DOWN)) ? myResources.getString(PENDOWN).split("[|]")[0] : myResources.getString(PENUP).split("[|]")[0]);
		});
		myRoot.getChildren().add(penToggle);
		
	}
	
	private void makeSizeSlider(){
		HBox slider = new HBox();
		
		Label lab = new Label(myResources.getString(PEN_SIZE) + state.getPenSize());
		Slider slide = new Slider(0, 100, state.getPenSize());
		slide.setShowTickLabels(true);
		slide.setShowTickMarks(true);
		Button btn_slide = new Button(myResources.getString(CHANGE));
		btn_slide.setOnAction(e -> {
			sendToController(myResources.getString(SETPS).split("[|]")[0] + " " + Math.round(slide.getValue()));
		});
		
		slider.getChildren().addAll(lab, slide, btn_slide);
		
		myRoot.getChildren().add(slider);
	}
	
	private void makeActiveSetting(){
		//TODO: the initial label is not correctly indicated but the activating and deactivating is working?
		ToggleSwitch act = new ToggleSwitch(myResources.getString(THIS_TURTLE_IS), myResources.getString(ACTIVE), myResources.getString(INACTIVE), 
				state.isActive() ? myResources.getString(ACTIVE) : myResources.getString(INACTIVE));
		act.setAction(e -> {
			act.toggle();
			System.out.println("" + state.isActive());
			sendToController(act.getText().equals(myResources.getString(ACTIVE)) ? myResources.getString(TELL) + " [ "+state.getID()+" ]" : 
				myResources.getString(DEACTIVATE).split("[|]")[0] + " [ "+state.getID()+" ]");
		});
		myRoot.getChildren().add(act);
	}
	
	private void sendToController(String s){
		String message = myResources.getString(TELL) + " [ "+state.getID()+" ] " + s;
		ct.parseInput(message);
	}
	
}
