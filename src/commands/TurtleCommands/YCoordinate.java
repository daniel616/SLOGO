package commands.TurtleCommands;

import java.util.List;
import java.util.Queue;

import exceptions.CommandException;
import exceptions.StateNotFoundException;
import model.Model;

/**
 * YCoordinate returns the Turtles Y coordinate from the center of the screen.
 * 
 * @author Kyle
 *
 */
public class YCoordinate extends TurtleCommands {

	public static final int VARIABLES = 0;
	public static final int NUMBER_VARIABLE_LISTS = 0;

	public YCoordinate(List<String> args) throws CommandException {
		super(args);
		validNumberVariables(args.size(),VARIABLES);
	}

	@Override
	public double execute(Model model, List<Queue<String>> variableLists) throws StateNotFoundException {
		return model.getTurtleModel().getMainTurtle().getFinalTurtleState().getY();
	}

}
