package commands.TurtleCommands;

import java.util.List;
import java.util.Queue;

import exceptions.CommandException;
import exceptions.StateNotFoundException;
import model.Model;

/**
 * Backward moves the Turtle backward based on its current heading by the
 * entered distance and returns the distance moved.
 * 
 * @author Kyle
 *
 */
public class Backward extends TurtleCommands {

	public static final int VARIABLES = 1;
	public static final int NUMBER_VARIABLE_LISTS = 0;
	private final boolean isForward = false;

	public Backward(List<String> args) throws CommandException {
		super(args);
		validNumberVariables(args.size(),VARIABLES);
	}

	@Override
	public double execute(Model model, List<Queue<String>> variableLists) throws StateNotFoundException {
		return move(model.getTurtleModel().getMainTurtle(), isForward);
	}

}
