package exceptions;
/**
 * 
 * @author Daniel
 * Exception that is thrown when the Turtle has less states than one of the methods needs.
 * I was thinking it should be caught by the GUI.
 * The throw-catch chain would be: Turtle throws to TurtleBox, TurtleBox throws to GUI, GUI catches and shows a popup or something.
 */
public class StateNotFoundException extends Exception{
	private static final long serialVersionUID = 1L;

	public StateNotFoundException(int n){		
		super("State unfound- turtle only has "+n+" states");
	}

}
