package commands.TurtleCommands;

import java.util.List;
import java.util.Queue;

import exceptions.CommandException;
import exceptions.StateNotFoundException;
import model.Model;

/**
 * PenDown makes the Turtle object put its pen down so that it now may draw when
 * it moves. It returns 1.
 * 
 * @author Kyle
 *
 */
public class PenDown extends TurtleCommands {

	public static final int VARIABLES = 0;
	public static final int NUMBER_VARIABLE_LISTS = 0;
	private final boolean isPenDown = true;

	public PenDown(List<String> args) throws CommandException {
		super(args);
		validNumberVariables(args.size(),VARIABLES);
	}

	@Override
	public double execute(Model model, List<Queue<String>> variableLists) throws StateNotFoundException {
		model.getTurtleModel().getMainTurtle().setPenDown(isPenDown);
		return 1;
	}

}
