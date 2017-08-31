package controls;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import commands.AdvancedCommands.CommandDetails;
import javafx.collections.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Model;
import parser.ManyParser;
import turtleDetails.ReadableState;
import turtleDetails.Turtle;
import turtleDetails.TurtleTrail;
import xml.BackReader;
import xml.BackWriter;
import xml.FrontReader;
import xml.FrontWriter;
import xml.XMLException;
import exceptions.StateNotFoundException;
import exceptions.ParserExceptions.ParserException;
import gui.DropdownClickableObject;
import gui.UserInterface;

/**
 * The controller class acts as the conduit between the front and back ends of our
 * project and contains some getters and setters, but as the project progressed
 * we started adding more listeners and observable objects to eliminate the need
 * to call these methods. It instantiates many objects in the front and back ends
 * and also is passed to many objects in the front end so that they can utilize
 * its methods to change information stored in the Model class
 * 
 * Created February 23rd, 2017
 * 
 * @author Jake Conroy
 */
public class Controller {
	private Model model;
	private ManyParser parser;
	private UserInterface myUI;

	public Controller() {
		try {
			model = new Model();
			parser = new ManyParser(model);
			myUI = new UserInterface(this);
			bind();
			
		} catch (Exception e) {
			displayException(e);
		}
	}

	private void bind() {
		bindTurtleModel();
		bindVisualModel();
		bindLanguageModel();
		bindProgrammingDetails();
	}
	
	private void bindTurtleModel(){
		model.getTurtleModel().imageIndex().addListener((observable,oldVal, newVal)->
			myUI.getTurtleScreen().setImage(model.getVisualModel().getImages().get(newVal)));
	}

	private void bindLanguageModel() {
		model.getLanguageModel().readOnlyLanguage().addListener((v, oldVal, newVal) -> {
			myUI.setLanguage(model.getLanguageModel().makeResourceBundle());
			try {
				parser.setLanguage(newVal);	
			} catch (ParserException e) {
				displayException(e);
			}
		});
	}

	private void bindVisualModel() {
		model.getVisualModel().backgroundColorIndex()
				.addListener((v, o, n) -> myUI.getTurtleScreen().setColor(model.getVisualModel().colorAtIndex(n)));
		model.getVisualModel().getImages().addListener(new MapChangeListener<Integer, String>() {
			@Override
			public void onChanged(Change<? extends Integer, ? extends String> change) {
				myUI.getTurtleScreen().setTurtleImageChooser();
			}
		});
	}

	private void bindProgrammingDetails() {
		DropdownClickableObject historyBox = myUI.returnHistoryCB();
		historyBox.setItems(model.getProgrammingModel().readOnlyHistory());

		DropdownClickableObject variableBox = myUI.returnVariablesCB();
		model.getProgrammingModel().readOnlyVariables().addListener(new MapChangeListener<String, String>() {
			@Override
			public void onChanged(Change<? extends String, ? extends String> change) {
				Set<String> variables = model.getProgrammingModel().readOnlyVariables().keySet();
				List<String> variableList = new ArrayList<>();
				variables.forEach(v -> variableList.add(v));
				variableBox.setItems(FXCollections.observableList(variableList));
			}
		});

		DropdownClickableObject commandBox = myUI.returnCommandsCB();
		model.getProgrammingModel().readOnlyCommands().addListener(new MapChangeListener<String, CommandDetails>() {
			@Override
			public void onChanged(Change<? extends String, ? extends CommandDetails> change) {
				Set<String> commands = model.getProgrammingModel().readOnlyCommands().keySet();
				List<String> commandList = new ArrayList<>();
				commands.forEach(v -> commandList.add(v));
				commandBox.setItems(FXCollections.observableList(commandList));
			}
		});
	}

	public void parseInput(String command) {
		try {
			parser.parseInfo(command);
			model.getProgrammingModel().addHistory(command);
		} catch (Exception e) {
			parser.clearCode();
			displayException(e);
		}
	}
	
	public void displayException(Exception e){
		if (myUI == null){
			System.out.println("No UI");
			return;
		}
		myUI.getOutputBox().setText(e.getMessage());
	}

	public Set<ReadableState> getTurtleStates() {
		Set<ReadableState> turtleStates = new HashSet<>();
		try {
			turtleStates = model.getTurtleModel().currentTurtleStates();
			return turtleStates;
		} catch (StateNotFoundException e) {
			displayException(e);
			return null;
		}
	}
	
	public List<TurtleTrail> getTurtleTrails() {
		List<TurtleTrail> myTrails = model.getTurtleModel().getReadOnlyTrails();
		return myTrails;
	}

	public void resetTurtleBox() {
		model.getTurtleModel().clearTrails();
		model.getTurtleModel().resetTurtles();
	}
	
	public void updateTrails() {
		try {
			Set<Turtle> allTurtles = model.getTurtleModel().getAllTurtles();
			for (Turtle turtle : allTurtles) {
				if (turtle.numStates() > 1) {
					model.getTurtleModel().getReadOnlyTrails().add(turtle.nextTrail());
				}
			}
		} catch (StateNotFoundException e) {
			displayException(e);
		}
	}
	
	public void updateTurtles() {
		try {
			model.getTurtleModel().updateTurtles();
		} catch (StateNotFoundException e) {
			displayException(e);
		}
	}

	public void setLanguage(String language) {
		model.getLanguageModel().setLanguage(language);
	}
	
	public void addTurtle(){
		try {
			model.getTurtleModel().addTurtle();
		} catch (Exception e){
			displayException(e);
		}
	}
	
	public void setTrailColor(int index) {
		model.getTurtleModel().setTrailColor(index);	
	}
	
	public void setTurtleImage(int index){
		model.getTurtleModel().setImage(index);
	}
	
	public void setBackgroundColor(int index){
		model.getVisualModel().setBackgroundColor(index);
	}
	
	public String getBackgroundColor(){
		return model.getVisualModel().colorAtIndex(model.getVisualModel().backgroundColor().getValue());
	}
	
	public ResourceBundle getResourceBundle() {
		return model.getLanguageModel().makeResourceBundle();
	}
	
	public UserInterface getMyUI() {
		return myUI;
	}
	
	public ObservableMap<Integer,String> colorPalette(){
		return model.getVisualModel().getPalette();
	}
	
	public ObservableMap<Integer,String> imageOptions(){
		return model.getVisualModel().getImages();
	}
	
	public void saveLibrary() {
		try {
			new BackWriter(model, getFileName());
		} catch (XMLException e) {
			displayException(e);
		}
	}
	
	public void loadLibrary() {
		try {
			new BackReader(model, getFile());
		} catch (Exception e) {
			displayException(e);
		}
	}
	
	public void loadVisual() {
		try {
			new FrontReader(model, getFile());
		} catch (Exception e) {
			displayException(e);
		}
	}

	public void saveVisual() {
		try {
			new FrontWriter(model, getFileName());
		} catch (XMLException e) {
			displayException(e);
		}
	}
	
	private File getFile() {
		return fileChooser().showOpenDialog(new Stage());
	}
	
	private String getFileName() {
		return fileChooser().showSaveDialog(new Stage()).getAbsolutePath();
	}
	
	private FileChooser fileChooser() {
		FileChooser fc = new FileChooser();
		fc.setInitialDirectory(new File(System.getProperty("user.dir")));
		return fc;
	}
}