package model;
import exceptions.StateNotFoundException;
import exceptions.UnrecognizedIDException;
import javafx.beans.value.ChangeListener;
import turtleDetails.Turtle;

/**
 * Contains all the info that is specific to each running SLogo application. All
 * data structures are observable. Returns read only structures.
 * 
 * Needs to throw exceptions for unfound names.
 * 
 * @author Daniel
 *
 */
public class Model {
	private final TurtleModel turtleData;
	private final GlobalVisualOptionsModel visualData;
	private final UserProgrammingModel programmingData;
	private final LanguageModel languageData;
	
	public Model() throws StateNotFoundException, UnrecognizedIDException{
		turtleData=new TurtleModel();
		visualData=new GlobalVisualOptionsModel();
		programmingData=new UserProgrammingModel();
		languageData=new LanguageModel();
		internalBinding();
	}
	
	public TurtleModel getTurtleModel(){
		return turtleData;
	}
	
	public GlobalVisualOptionsModel getVisualModel(){
		return visualData;
	}
	
	public UserProgrammingModel getProgrammingModel(){
		return programmingData;
	}
	
	public LanguageModel getLanguageModel(){
		return languageData;
	}
	
	private void internalBinding() throws StateNotFoundException {
		turtleData.trailColorIndex().addListener((ChangeListener<Integer>)(ob,o1,o2)->updateGlobalTurtleSettings());
		turtleData.penSizeIndex().addListener((ChangeListener<Integer>)(ob,o1,o2)->updateGlobalTurtleSettings());
		turtleData.penDown().addListener((ChangeListener<Boolean>)(ob,o1,o2)->updateGlobalTurtleSettings());
		turtleData.imageIndex().addListener((ChangeListener<Integer>)(observable,oldValue,newValue)->updateGlobalTurtleSettings());
	}
	
	/**
	 * Some redundancy, but I don't think it will be costly in terms of runtime.
	 * @throws StateNotFoundException
	 */
	private void updateGlobalTurtleSettings(){
		for(Turtle turtle:turtleData.getAllTurtles()){
			try {
				turtle.setColor(visualData.colorAtIndex(turtleData.trailColorIndex().getValue()));
				turtle.setPenSize(turtleData.penSizeIndex().getValue());
				turtle.setPenDown(turtleData.penDown().getValue());
				turtle.setImage(turtleData.imageIndex().getValue());
			} catch (StateNotFoundException e) {
				visualData.setBackgroundColor(0);
			}
		}
	}

}