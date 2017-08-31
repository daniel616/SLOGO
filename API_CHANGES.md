#API Changes group 18

1. What changes have been made to the API? 

We reviewed our previous API and saw that the general outline of the API has not changed too much, but that we have split up some classes and added entire packages that we did not envision. For instance, we have (for this final sprint) created an XML package. The API of these classes can be seen below. Besides this, our original API had the turtles being passed from the back end to the front end and this is now split up into two classes, turtle states and turtle trails, each of which are rendered by the GUI independently. 

What changes have been made to the API? Are the changes major/minor? Are the changes for better or worse?

We reviewed our previous API and saw that the general outline of the API has not changed too much, but that we have split up some classes and added entire packages that we did not envision. For instance, we have (for this final sprint) created an XML package. The API of these classes can be seen below. Besides this, our original API had the turtles being passed from the back end to the front end and this is now split up into two classes, turtle states and turtle trails, each of which are rendered by the GUI independently. Additionally, we added a bunch of our own exception classes, which were not included in the original API. The execute method takes more parameters than we originally intended because we have switched to an MVC design. So, now the commands simply modify the model classes directly because this is where the data for the program is all held. We see this as an improved design in our program because the controller can relay the information contained within the model to the GUI for viewing. Additionally, the GUI has been split up into more classes than originally planned. The canvas is now called TurtleScreen and it contains some buttons besides just having the actual screen upon which the turtle sits. Finally, we added a controller when we decided to implement the MVC design. This is a major design change and we debated it extensively but decided that the best way to connect the front and back ends of the program without directly giving them access to each other was with the controller. The change has been working well for us so far and, after refactoring the controller class, we have been able to keep it at a manageable size. 

Do you envision significant changes in the API over the next couple days?

We feel that our current API is very close to what our final API will look like because we have implemented most of the requirements for the final sprint already. All additional features will likely only add a new public method or two a piece.
	
#Current API 
	package commands;
	public abstract class Commands { 
	  	public Commands(List<String> arguments) 
		public abstract double execute(Model model, List<Queue<String>> variableLists)
	}
	 
	 package controls;
	public class Controller { 
	  	public Controller()
		public void onChanged(Change<? extends String, ? extends String> change)
		public void onChanged(Change<? extends String, ? extends CommandDetails> change)
		public void addHistory(String s)
		public Set<TurtleState> getTurtleStates() 
		public Set<TurtleTrail> getTurtleTrails() 
		public void clearTrails(int turtleID) throws UnrecognizedIDException, StateNotFoundException 
		public void resetTurtleBox() 
		public void setLanguage(String language) 
		public void updateTrails() 
		public void updateTurtles() 
		public void printOutput(String output)
		public void parseInput(String command) 
		public SimpleObjectProperty<ObservableList<String>> getPreviousCommands() 
		public void setBackgroundColor(int s)
		public Set<Integer> getActiveIDs() throws StateNotFoundException
		public Turtle getActiveTurtle()
		public Set<Turtle> getTheseTurtles(Set<Integer> numbers) throws UnrecognizedIDException, StateNotFoundException
		public void setActiveTurtle(int ID) throws StateNotFoundException, UnrecognizedIDException
		public void addTurtle(int ID) throws StateNotFoundException, RepeatedVariableNameException
		public void setTrailColor(String color)
		public void addActiveTurtles(Set<Integer> numbers)
		public int getID() throws StateNotFoundException
		public ResourceBundle getResourceBundle() 
		public void saveFile() 
		public UserInterface getMyUI() 
		public Model getModel()
	}
	 
	package controls;
	public class Parser { 
	  	public Parser(Model pModel) throws ParserException 
		public double addCode(String info, String lan) throws ParserException, StateNotFoundException, CommandException, UnrecognizedIDException P
		public double addCode(Queue<String> info, String lan)
		public void setLanguage(String language) throws ParserException 
		public void clearCode()
	}
	 
	package controls;
	public class PropertiesHolder { 
	  	public ProperitesHolder() 
		public void addInfo(String info) throws PropertiesFileException 
		public String getKey(Queue<String> info) throws PropertiesInfoException 
		public String getValue(String language)
	}
	 
	package controls;
	public class ManyParser extends Parser { 
		public ManyParser(Model pModel) throws ParserException
		public void parseInfo(String info) throws ParserException, StateNotFoundException, CommandException, UnrecognizedIDException
	}
	 
	package controls;
	public class ListGetter { 
	  	public ListGetter(Queue<String> info)
		public Queue<String> getList(String start, String end) throws IncompleteListException 
		private Queue<String> getListInfo(String start, String end)
	}
	 
	package controls;
	public class GroupHandler { 
	  	public GroupHandler(Model m, Class<?> cl, Queue<String> groupInfo, int varNeeded, boolean pDependance) 
		public double handle() throws ParserException, StateNotFoundException, CommandException, UnrecognizedIDException
	}
	 
	package exceptions;
	public class ColorException extends Exception{ 
	  	public ColorException (String s)
	}
	 
	package exceptions;
	public class CommandException extends Exception { 
	  	public CommandException(String message) 
		public CommandException(Throwable cause) 
		public CommandException(String message, Throwable cause) 
	}
	 
	package exceptions.ParserExceptions;
	public class CommandClassIncompleteException extends ParserException { 
	      public CommandClassIncompleteException(String field, String message) 
	}
	 
	package exceptions.ParserExceptions;
	public class CommandNotFoundException extends ParserException { 
	      public CommandNotFoundException(String message) 
	}
	 
	package exceptions.ParserExceptions;
	public class GroupException extends ParserException { 
	      public GroupException(String message) 
	}
	 
	package exceptions.ParserExceptions;
	public class IncompleteListException extends ParserException { 
	      public IncompleteListException(String message) 
	}
	 
	package exceptions.ParserExceptions;
	public class LanguageNotFoundException extends ParserException { 
	      public LanguageNotFoundException(String message) 
	}
	 
	package exceptions.ParserExceptions;
	public class ParserException extends Exception{ 
	      public ParserException(String message) 
	}
	 
	package exceptions.ParserExceptions;
	public class PropertiesFileException extends ParserException { 
	      public PropertiesFileException() 
	}
	 
	package exceptions.ParserExceptions;
	public class PropertiesInfoException extends ParserException { 
	      public PropertiesInfoException() 
	}
	 
	package exceptions.ParserExceptions;
	public class ReflectionException extends ParserException { 
	      public ReflectionException() 
	}
	 
	package exceptions;
	public class RepeatedFunctionNameException extends Exception{ 
	  	public RepeatedFunctionNameException(String s)
	}
	 
	package exceptions;
	public class RepeatedVariableNameException extends Exception{ 
	  	public RepeatedVariableNameException(String s)
	}
	 
	package exceptions;
	public class StateNotFoundException extends Exception{ 
	  	public StateNotFoundException(int n)
	}
	 
	package exceptions;
	public class UnrecognizedIDException extends Exception{ 
	  }
	 
	package gui;
	public class ConsoleOutput extends Group { 
	  	public ConsoleOutput(Controller control)
		public void setText(String s) //This method will be called by someone so that descriptions of commands can be displayed
	}
	 
	package gui;
	public class DropdownClickableObject extends ComboBox<String> { 
	  	public DropdownClickableObject(ObservableList<String> list)
		public DropdownClickableObject()
		public void bindList(ObservableList<String> ol)
		public void addCommand(String s)
		public DropdownClickableObject returnCB()
	}
	 
	package gui;
	public class MasterInterface { 
	  	public MasterInterface(Stage ps) throws ParserException 
		public void addUI(UserInterface ui) 
		public Scene getScene() 
	}
	 
	package gui;
	public class MenuBar extends HBox { 
	  	public MenuBar(Controller c) 
	}
	 
	package gui;
	public class PopUpSettings extends Stage { 
	  	public PopUpSettings(Controller c, double x, double y, double h, Boolean isD)
		public Stage returnPopUp()
	}
	 
	package gui;
	public class TextInput extends Group { 
	  	public TextInput(Controller c)
	}
	 
	package gui;
	public class TurtleScreen extends BorderPane { 
	  	public TurtleScreen(Controller c) 
		public void setColor(String color)
		public void clearScreen() 
	}
	 
	package gui;
	public class UserInterface extends BorderPane { 
	  	public UserInterface(Controller control) 
		public void initializeComponents()
		public ConsoleOutput getOutputBox() 
		public TurtleScreen getTurtleScreen()
		public DropdownClickableObject returnHistoryCB() 
		public DropdownClickableObject returnCommandsCB() 
		public DropdownClickableObject returnVariablesCB() 
	}
	 
	package main;
	public class Main extends Application { 
	  	public void start(Stage primaryStage) 
	}
	 
	package Model;
	public class GlobalVisualOptionsModel { 
	  	public GlobalVisualOptionsModel()
		public void setPenSize(int size) 
		public void setTrailColor(int index) 
		public void setShape(int index) 
		public void changeBackgroundPalette(int index, String color) 
		public void doNotUseSetBackgroundColor(String color) 
		public void setBackgroundColor(int index) 
		public ObservableMap<Integer, String> getImages() 
	}
	 
	package Model;
	public class LanguageModel { 
	  	public LanguageModel()
		public void setLanguage(String s) 
	}
	 
	package Model;
	public class Model { 
	  	public Model() throws StateNotFoundException, UnrecognizedIDException
		public TurtleModel getTurtleModel()
		public GlobalVisualOptionsModel getVisualModel()
		public UserProgrammingModel getProgrammingModel()
		public LanguageModel getLanguageModel()
	}

	package Model;
	public class Model { 
	  	public Model() throws StateNotFoundException, UnrecognizedIDException
		public TurtleModel getTurtleModel()
		public GlobalVisualOptionsModel getVisualModel()
		public UserProgrammingModel getProgrammingModel()
		public LanguageModel getLanguageModel()
	}
	 
	package Model;
	public class TurtleModel { 
	  	public TurtleModel() throws StateNotFoundException, UnrecognizedIDException 
		public Set<Turtle> getAllTurtles()
		public void activateTurtles(Set<Integer> turtleNumbers) 
		public void deactivateTurtles(Set<Integer> turtleNumbers) 
		public Set<Integer> getActiveIDs() throws StateNotFoundException 
		public Turtle getTurtle(int ID) throws UnrecognizedIDException, StateNotFoundException 
		public int numTurtles() 
		public Set<Turtle> getTheseTurtles(Set<Integer> numbers) throws UnrecognizedIDException, StateNotFoundException 
		public void setMainTurtle(int x) throws StateNotFoundException, UnrecognizedIDException 
		public void addTurtle(int ID) throws StateNotFoundException, RepeatedVariableNameException 
		public Turtle getActiveTurtle() 
		public void resetTurtles() 
		public void removeTurtle(String turtleName) 
		public Set<TurtleState> getTurtleStates() throws StateNotFoundException 
		public void updateTurtles() throws StateNotFoundException 
		public ObservableSet<TurtleTrail> getTrails()
		public void removeTrail(TurtleTrail trail) 
		public void clearTrails() 
		public void setNumberTurtles(int n) throws StateNotFoundException, RepeatedVariableNameException, UnrecognizedIDException 
	}
	 
	package Model;
	public class UserProgrammingModel { 
	  	public UserProgrammingModel()
		public ObservableList<String> getHistory()
		public ObservableMap<String,CommandDetails> getCommands()
		public ObservableMap<String, String> getVariables()
		public void addHistory(String history)
		public void addCommand(String name, CommandDetails details) 
		public void removeCommand(String command) 
		public void removeAllCommands() 
		public void addVariable(String variable, String values) 
		public void removeVariable(String variable) 
		public Map<String, CommandDetails> readOnlyCommands() 
		public Map<String, String> readOnlyVariables() 
		public Map<String, String> copyVariablesMap() 
		public ObservableList<String> returnHistory() 
	}
	 
	package TurtleDetails;
	public class CommandDetails { 
	  	public CommandDetails(Queue<String> variables,Queue<String> command)
		public Queue<String> readOnlyVariables()
		public Queue<String> readOnlyCommand()
		public List<Queue<String>> getBoth() 
	}
	 
	package TurtleDetails;
	public class StateAdder { 
	  	public StateAdder(List<TurtleState> turtleStates)
		/*public void setPosition(double x, double y) throws StateNotFoundException
		public void setPenDown(boolean penDown) throws StateNotFoundException
		public void setColor(String color) throws StateNotFoundException
		public void showTurtle(boolean show) throws StateNotFoundException
		public void setPenSize(double size) throws StateNotFoundException
	}
	 
	package TurtleDetails;
	public class Turtle{ 	public class TurtleState { 
	
	The API for the Turtle has stayed largely consistent throughout the project. However, the amount of information stored within the Turtle has increased over time to store information such as ID and pen size. The turtle has also grown somewhat more user friendly with new setter methods that take the place of the addState() method.
	public class Turtle{ 
	  	public Turtle(int ID,double x, double y, double heading, boolean penDown,
		public Turtle(int ID)
		public void resetState()
		public TurtleTrail nextTrail() throws StateNotFoundException
		public void removeFirstState() throws StateNotFoundException
		public void addTurtleState(double x, double y, double heading, boolean penDown, 
		public TurtleState getFinalTurtleState() throws StateNotFoundException 
		public TurtleState getFirstTurtleState() throws StateNotFoundException 
		public Set<TurtleTrail> getTrails()
		public int numStates()
		public int getID()
		public void setPosition(double x, double y) throws StateNotFoundException
		public void setPenDown(boolean penDown) throws StateNotFoundException
		public void setColor(String color) throws StateNotFoundException
		public void showTurtle(boolean show) throws StateNotFoundException
		public void setPenSize(double size) throws StateNotFoundException
	}

	Setters have been added so that programmers may add turtle states by copying turtles and changing values instead of reinstantiating, which is difficult to perform. However, by using interfaces, this TurtleState can seem immutable to outside classes
	Public class TurtleState implements ReadableState{
		public TurtleState copy()
		public int getID()
		public double getX() 
		public double getY() 
		public double getHeading() 
		public boolean penDown() 
		public String getColor() 
		public boolean turtleShowing() 
		public double getPenSize()
		public String toString()
		public void setPosition(x,y)
		public void setPenUp(boolean penDown)
		public void setActive(boolean active)
		public void setPenSize(int size)
		public void setShow(boolean show)
		public void setImage(int image)
	}
	
	
	public interface ReadableState{
	public int getID()
		public double getX() 
		public double getY() 
		public double getHeading() 
		public boolean penDown() 
		public String getColor() 
		public boolean turtleShowing() 
		public double getPenSize()
		public String toString()
	}
	

	 
	package TurtleDetails;
	public class TurtleTrail { 
	  	public TurtleTrail(double x1, double y1, double x2, double y2, 
		public double getX1() 
		public double getX2() 
		public double getY1() 
		public double getY2() 
		public boolean penDown() 
		public String penColor() 
		public double penSize()
	}
	
	package gui;
	public class Chooser {
		public Chooser(Controller, String, boolean)
		public void getInfo()
		public void setLanguage(ResourceBundle)
	}
	
	public class ColorCell {
		public ColorCell(Color)
	}
	
	public class IndexInfo {
		public IndexInfo(Integer, String, ObservableMap<Integer, String>)
		public Integer getIndex()
		public String getInfo()
		public ObservableMap<Integer, String> getMap()
	}
	
	public class ToggleSwitch {
		public void setAction(EventHandler<ActionEvent> e);
		public Boolean isOn();
		public void toggle();
	}
	
	package xml;
	public class BackReader extends XMLReader{ 
	  	public BackReader(Model m, File f) throws XMLException, LanguageNotFoundException 
		public void setFile(File f) throws LanguageNotFoundException
	}
	 
	package xml;
	public class BackWriter extends XMLWriter { 
	  	public BackWriter(Model m, String add) throws XMLException 
	}
	 
	package xml;
	public class FrontReader extends XMLReader{ 
	  	public FrontReader(Model m, File f) throws Exception 
		public void setFile(File f) throws Exception
	}
	 
	package xml;
	public class FrontWriter extends XMLWriter { 
	  	public FrontWriter(Model m, String add) throws XMLException 
	}
	 
	package xml;
	public class PersonalQueue { 
	  	public PersonalQueue(Queue<String> q)
		public String toString()
	}
	 
	package xml;
	public class XMLException extends RuntimeException { 
	      public XMLException (String message, Object ... values) 
	    public XMLException (Throwable cause) 
	}
	 
	package xml;
	public abstract class XMLReader { 
	  	public XMLReader(File f) throws XMLException 
		public abstract void setFile(File f) throws Exception;
	}
	 
	package xml;
	public abstract class XMLWriter { 
	  	public static final DocumentBuilder DOCUMENT_BUILDER = getDocumentBuilder();
		public XMLWriter(String pAdd) throws XMLException 
	}
