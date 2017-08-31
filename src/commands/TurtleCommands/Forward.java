package commands.TurtleCommands;

import java.util.List;
import java.util.Queue;

import exceptions.CommandException;
import exceptions.StateNotFoundException;
import model.Model;

/**
 * Moves Turtle forward based on specified distance and current heading of
 * Turtle.
 * 
 * @author Kyle
 *
 */
public class Forward extends TurtleCommands {

	public static final int VARIABLES = 1;
	public static final int NUMBER_VARIABLE_LISTS = 0;
	private final boolean isForward = true;

	public Forward(List<String> args) throws CommandException {
		super(args);
		validNumberVariables(args.size(),VARIABLES);
	}

	@Override
	public double execute(Model model, List<Queue<String>> variableLists) throws StateNotFoundException {
		return move(model.getTurtleModel().getMainTurtle(), isForward);	
	}
}
