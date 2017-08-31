package commands.TurtleCommands;

import java.util.List;
import java.util.Queue;

import exceptions.CommandException;
import exceptions.StateNotFoundException;
import model.Model;

/** ShowTurtle makes the Turtle object visible where it is being viewed. Returns 1.
 * 
 * @author Kyle
 *
 */
public class ShowTurtle extends TurtleCommands {
	
	public static final int VARIABLES = 0;
	public static final int NUMBER_VARIABLE_LISTS = 0;
	private final boolean isShowing = true;

	public ShowTurtle(List<String> args) throws CommandException {
		super(args);
		validNumberVariables(args.size(),VARIABLES);
	}

	@Override
	public double execute(Model model, List<Queue<String>> variableLists) throws StateNotFoundException {
		model.getTurtleModel().getMainTurtle().showTurtle(isShowing);
		return 1;
	}

}
