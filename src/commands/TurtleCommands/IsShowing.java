package commands.TurtleCommands;

import java.util.List;
import java.util.Queue;

import exceptions.CommandException;
import exceptions.StateNotFoundException;
import model.Model;

/**
 * IsShowing returns 1 if Turtle is showing or 0 if it is invisible
 * 
 * @author Kyle
 *
 */
public class IsShowing extends TurtleCommands {

	public static final int VARIABLES = 0;
	public static final int NUMBER_VARIABLE_LISTS = 0;

	public IsShowing(List<String> args) throws CommandException {
		super(args);
		validNumberVariables(args.size(),VARIABLES);
	}

	@Override
	public double execute(Model model, List<Queue<String>> variableLists) throws StateNotFoundException {
		return (model.getTurtleModel().getMainTurtle().getFinalTurtleState().turtleShowing()) ? 1.0 : 0.0;
	}

}
