package gui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import controls.Controller;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import turtleDetails.ReadableState;
import turtleDetails.TurtleTrail;
import javafx.stage.Stage;

/**
 * This class will be able to return a screen that contains the turtle objects
 * and a button panel. It will also contain the ability to ask 
 * the back end for new commands for each turtle and update their screen
 * positions accordingly. Finally, it contains the ability to graphically update
 * the positions of active turtles.
 * 
 * Created February 23rd, 2017
 * 
 * @author Jake Conroy
 *
 */

public class TurtleScreen extends BorderPane {
	private static final String NO = "No";
	private static final String YES = "Yes";
	private static final String SHOW_ACTIVE_TURTLES = "ShowActiveTurtles";
	private static final Color WHITE = Color.WHITE;
	private static final String CHOOSE_IMAGE = "ChooseImage";
	private static final String ADD_TURTLE = "AddTurtle";
	private static final String CLEAR = "Clear";
	private static final String START_STOP = "StartStop";
	private static final int PADDING_SIZE = 5;
	private static final int SPACING_SIZE = 10;
	private HBox buttonsBox = new HBox();
	private Button startStop;
	private Button clearScreen;
	private Button newTurtle;
	private ToggleSwitch showActiveToggle;
	private Chooser chooseTurtleImage;
	private Rectangle canvas = new Rectangle();
	private Group turtleBox = new Group();
	private boolean run = true;
	private Controller control;
	private Chooser backgroundColorChooser;
	private Chooser turtleTrailColorChooser;
	private HBox colorChooserMenu = new HBox();
	private Image image;
	private Timeline animation;
	private double TIME_BETWEEN_FRAME = 0.01;
	private static final String DEFAULT_TURTLE_IMAGE = "cute_GIF.gif";
	public static final int SCREEN_SIZE = 480;
	public static final int TURTLE_SIZE = 45;
	private static final String BACKGROUND_COLOR = "BackgroundColors";
	private static final String TRAIL_COLOR = "TrailColors";	
	
	private List<Circle> activeCircles = new ArrayList<Circle>();
	
	public TurtleScreen(Controller c) {
		control = c;
		makeButtonBox();
		makeButtons();
		makeMenu();
		makeScreen();
		animate();
	}
	
	public void setLanguage(ResourceBundle resource){
		startStop.setText(resource.getString(START_STOP));
		clearScreen.setText(resource.getString(CLEAR));
		newTurtle.setText(resource.getString(ADD_TURTLE));
		backgroundColorChooser.setLanguage(resource);
		turtleTrailColorChooser.setLanguage(resource);
		chooseTurtleImage.setLanguage(resource);
	}
	
	public void setImage(String imageName){
		image=chooseNewTurtleImage(imageName);
	}
	
	/**
	 * Will clear the screen of all lines and send the turtle back to the home
	 * position
	 */
	public void clearScreen() {
		activeCircles.clear();
		turtleBox.getChildren().clear();
		turtleBox.getChildren().add(canvas);
	}

	private void animate() {
		animation = new Timeline();
		KeyFrame frame = new KeyFrame(Duration.seconds(TIME_BETWEEN_FRAME), e -> {
			if (run) {
				step();
			}
		});
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}

	private void step() {
		clearScreen();
		control.updateTrails();
		control.updateTurtles();
		Collection<TurtleTrail> turtleTrails = control.getTurtleTrails();
		Collection<ReadableState> turtleStates = control.getTurtleStates();
		for (TurtleTrail trail : turtleTrails) {
			paintTrail(trail);
		}
		for (ReadableState state : turtleStates) {
			if (state.turtleShowing()) {
				makeClickableTurtle(state);
			}
		}
	}

	private void paintTrail(TurtleTrail trail) {
		double xDistance=trail.getX1()-trail.getX2();
		double yDistance=trail.getY1()-trail.getY2();
		
		if (trail.penDown()&&(xDistance!=0||yDistance!=0)) {
			Line recentlyDrawnTrail = new Line();
			recentlyDrawnTrail.setStrokeWidth(trail.penSize());
			recentlyDrawnTrail.setStroke(Color.web(trail.penColor()));
			recentlyDrawnTrail.setStartX(translateCoords(trail.getX1()));
			recentlyDrawnTrail.setStartY(translateCoords(trail.getY1()));
			recentlyDrawnTrail.setEndX(translateCoords(trail.getX2()));
			recentlyDrawnTrail.setEndY(translateCoords(trail.getY2()));
			turtleBox.getChildren().add(recentlyDrawnTrail);
		}
	}
	
	private void makeClickableTurtle(ReadableState state){
		ImageView turtle = makeTurtleImage(state);
		if (state.isActive()){
			Circle circle = makeActiveCircle(state, turtle);
			activeCircles.add(circle);
			turtleBox.getChildren().add(circle);
		}
		Rectangle area = makeClickableRec(state, turtle);
		turtleBox.getChildren().addAll(turtle, area);
	}

	private ImageView makeTurtleImage(ReadableState state){
		ImageView turtle = new ImageView(image);
		turtle.setX(translateCoords(state.getX()) - image.getWidth() / 2);
		turtle.setY(translateCoords(state.getY()) - image.getHeight() / 2);
		turtle.setRotate(-state.getHeading());
		return turtle;
	}
	
	private Circle makeActiveCircle(ReadableState state, ImageView turtle){
		Circle circle = new Circle((turtle.getBoundsInLocal().getMaxX() - turtle.getBoundsInLocal().getMinX())/3*2);
		circle.setStroke(!showActiveToggle.isOn() ? Color.LIMEGREEN : Color.TRANSPARENT);
		circle.setFill(Color.TRANSPARENT);
		double xloc = (translateCoords(state.getX()) - image.getWidth() / 2) + (turtle.getBoundsInLocal().getMaxX() - turtle.getBoundsInLocal().getMinX())/2;
		double yloc = (translateCoords(state.getY()) - image.getWidth() / 2) + (turtle.getBoundsInLocal().getMaxY() - turtle.getBoundsInLocal().getMinY())/2;
		circle.setCenterX(xloc);
		circle.setCenterY(yloc);
		activeCircles.add(circle);
		
		return circle;
	}
	
	private Rectangle makeClickableRec(ReadableState state, ImageView turtle){
		Rectangle area = new Rectangle();
		area.setFill(Color.TRANSPARENT);
		area.setX(translateCoords(state.getX()) - image.getWidth() / 2);
		area.setY(translateCoords(state.getY()) - image.getHeight() / 2);
		area.setWidth(turtle.getBoundsInLocal().getMaxX() - turtle.getBoundsInLocal().getMinX());
		area.setHeight(turtle.getBoundsInLocal().getMaxY() - turtle.getBoundsInLocal().getMinY());
		area.setOnMousePressed(e -> makePopUp(state));
		
		return area;
	}

	private void makePopUp(ReadableState state){
		PopUpSettings popup = new PopUpSettings(control, state);
		popup.show();
	}


	private double translateCoords(double curr) {
		return (curr + SCREEN_SIZE / 2);
	}

	private void makeButtonBox() {
		buttonsBox.setPadding(new Insets(PADDING_SIZE, PADDING_SIZE, PADDING_SIZE, PADDING_SIZE));
		buttonsBox.setSpacing(SPACING_SIZE);
	}

	private void makeButtons(){
		ResourceBundle resources = control.getResourceBundle();
		startStop = new Button(resources.getString(START_STOP));
		startStop.setOnAction(e -> toggleAnimation());
		clearScreen = new Button(resources.getString(CLEAR));
		clearScreen.setOnAction(e -> clearScreenOfTurtles());
		newTurtle = new Button(resources.getString(ADD_TURTLE));
		newTurtle.setOnAction(e -> addTurtleToScreen());
		showActiveToggle = new ToggleSwitch(resources.getString(SHOW_ACTIVE_TURTLES), resources.getString(YES), resources.getString(NO), resources.getString(YES));
		showActiveToggle.setAction(e -> {
			showActiveToggle.toggle();	
		});
		buttonsBox.getChildren().addAll(startStop, clearScreen, newTurtle, showActiveToggle);
		chooseTurtleImage = new Chooser(control, CHOOSE_IMAGE, false);
		chooseTurtleImage.setOnAction(e -> control.setTurtleImage(chooseTurtleImage.getValue().getIndex()));//changeTurtleImage());
		//= new Button(myResources.getString(CHOOSE_IMAGE));

	}
	
	public void setTurtleImageChooser() {
		chooseTurtleImage.getChildrenUnmodifiable();
	}

	private void makeMenu() {
		backgroundColorChooser = new Chooser(control, BACKGROUND_COLOR, true);
		backgroundColorChooser.setMaxHeight(SPACING_SIZE);
		backgroundColorChooser.setOnAction(e -> {
			control.setBackgroundColor(backgroundColorChooser.getValue().getIndex());
		});
		turtleTrailColorChooser = new Chooser(control, TRAIL_COLOR, true);
		turtleTrailColorChooser.setOnAction(e -> control.setTrailColor(turtleTrailColorChooser.getValue().getIndex()));
		colorChooserMenu = new HBox();
		colorChooserMenu.setPadding(new Insets(PADDING_SIZE, PADDING_SIZE, PADDING_SIZE, PADDING_SIZE));
		colorChooserMenu.setSpacing(SPACING_SIZE);
		colorChooserMenu.getChildren().addAll(backgroundColorChooser, chooseTurtleImage, turtleTrailColorChooser);
	}

	private void makeScreen() {
		canvas.setWidth(SCREEN_SIZE);
		canvas.setHeight(SCREEN_SIZE);
		canvas.setFill(WHITE);
		turtleBox.getChildren().add(canvas);
		setBottom(buttonsBox);
		setCenter(turtleBox);
		setTop(colorChooserMenu);
		image = new Image(getClass().getClassLoader().getResourceAsStream(DEFAULT_TURTLE_IMAGE), TURTLE_SIZE,TURTLE_SIZE, true, true);
	}

	private void clearScreenOfTurtles() {
		turtleBox.getChildren().clear();
		turtleBox.getChildren().add(canvas);
		control.resetTurtleBox();
	}

	private void toggleAnimation() {
		run = !run;
	}

	private void addTurtleToScreen() {
		control.addTurtle();
	}
	
	private Image chooseNewTurtleImage(String fileName) {
		Image turtleImage = new Image(getClass().getClassLoader().getResourceAsStream(fileName), TURTLE_SIZE, TURTLE_SIZE,
				true, true);
		return turtleImage;
	}
	
	public void setColor(String color){
		canvas.setFill(Color.web(color));
	}
}
