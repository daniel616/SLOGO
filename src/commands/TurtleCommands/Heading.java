package commands.TurtleCommands;

import java.util.List;
import java.util.Queue;

import exceptions.CommandException;
import exceptions.StateNotFoundException;
import model.Model;

/** Heading returns the Turtle's heading in degrees.
 * 
 * @author Kyle
 *
 */
public class Heading extends TurtleCommands {
	
	public static final int VARIABLES = 0;
	public static final int NUMBER_VARIABLE_LISTS = 0;

	public Heading(List<String> args) throws CommandException {
		super(args);
		validNumberVariables(args.size(),VARIABLES);
	}

	@Override
	public double execute(Model model, List<Queue<String>> variableLists) throws StateNotFoundException {
		return model.getTurtleModel().getMainTurtle().getFinalTurtleState().getHeading();
	}

}
