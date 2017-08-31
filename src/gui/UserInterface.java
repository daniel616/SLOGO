package gui;

import java.util.ResourceBundle;

import controls.Controller;
import javafx.beans.property.SimpleListProperty;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * 
 * @author Daniel Simply stores the various components of the display, such as
 *         the consoleoutput, turtlescreen etc. Holds a scene that can be
 *         returned.
 */
public class UserInterface extends BorderPane {
	private static final String VARIABLES = "Variables";
	private static final String USER_MADE_COMMANDS = "UserMadeCommands";
	private static final String HISTORY = "History";
	private TextInput input;
	private ConsoleOutput output;
	private TurtleScreen screen;
	private MenuBar menuBar;
	private VBox textInfo;
	private boolean showHideToggle=false;
	private Controller control;
	private final int HEIGHT = 800;
	private final int WIDTH = 1000;
	private DropdownClickableObject historyCB;
	private DropdownClickableObject commandsCB;
	private DropdownClickableObject variablesCB;

	public UserInterface(Controller control) {
		this.control = control;
		initializeComponents();
		//myScene = new Scene(this, HEIGHT, WIDTH);
	}
	
	public void initializeComponents(){
		input = new TextInput(control);
		output = new ConsoleOutput(control);
		screen = new TurtleScreen(control);
		menuBar = new MenuBar(control);

		historyCB = makeComboBox(HISTORY);
		commandsCB = makeComboBox(USER_MADE_COMMANDS);
		variablesCB = makeComboBox(VARIABLES);
		textInfo = new VBox();
		textInfo.getChildren().addAll(input, output, historyCB, commandsCB, variablesCB);

		setRight(textInfo);
		setCenter(screen);
		setTop(menuBar);
		this.setHeight(HEIGHT);
		this.setWidth(WIDTH);
	}
	
	public void setLanguage(ResourceBundle resource){
		screen.setLanguage(resource);
		input.setLanguage(resource);
		output.setLanguage(resource);
		menuBar.setLanguage(resource);
		historyCB.setPromptText(resource.getString(HISTORY));
		variablesCB.setPromptText(resource.getString(VARIABLES));
		commandsCB.setPromptText(resource.getString(USER_MADE_COMMANDS));
	}
	
	public void enterCommandInput(String input) {
		this.input.setText(input);
	}

	public ConsoleOutput getOutputBox() {
		return output;
	}
	
	public TurtleScreen getTurtleScreen(){
		return screen;
	}

	public DropdownClickableObject returnHistoryCB() {
		return historyCB;
	}

	public DropdownClickableObject returnCommandsCB() {
		return commandsCB;
	}

	public DropdownClickableObject returnVariablesCB() {
		return variablesCB;
	}
	
	private DropdownClickableObject makeComboBox(String name) {
		DropdownClickableObject dco = new DropdownClickableObject(new SimpleListProperty<String>());
		dco.setPromptText(control.getResourceBundle().getString(name));
		return dco;
	}

	public void reset() {
		showHideToggle = !showHideToggle;
		if (showHideToggle) {
			setRight(null);
		} else {
			setRight(textInfo);
		}
		
	}

}