package commands.TurtleCommands;

import java.util.List;
import java.util.Queue;

import exceptions.CommandException;
import exceptions.StateNotFoundException;
import model.Model;
import turtleDetails.Turtle;

/**
 * Left turns the passed Turtle's heading left by the entered degrees and
 * returns degrees turned
 * 
 * @author Kyle
 *
 */
public class Left extends TurtleCommands {

	public static final int VARIABLES = 1;
	public static final int NUMBER_VARIABLE_LISTS = 0;

	public Left(List<String> args) throws CommandException {
		super(args);
		validNumberVariables(args.size(), VARIABLES);
	}

	@Override
	public double execute(Model model, List<Queue<String>> variableLists) throws StateNotFoundException {
		Turtle turtle = model.getTurtleModel().getMainTurtle();
		double normalizedHeading = turtle.getFinalTurtleState().getHeading() + doubleArguments.get(0);
		turtle.setHeading(normalizedHeading);
		
		return doubleArguments.get(0);
	}

}
