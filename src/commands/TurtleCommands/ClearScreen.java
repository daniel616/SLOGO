package commands.TurtleCommands;

import java.util.List;
import java.util.Queue;

import exceptions.CommandException;
import exceptions.StateNotFoundException;
import exceptions.UnrecognizedIDException;
import model.Model;

/**
 * ClearScreen returns the Turtle to the home position, (0,0), and clears any
 * trails the Turtle has made.
 * 
 * @author Kyle
 *
 */
public class ClearScreen extends TurtleCommands {

	public static final int VARIABLES = 0;
	public static final int NUMBER_VARIABLE_LISTS = 0;

	public ClearScreen(List<String> args) throws CommandException {
		super(args);
		validNumberVariables(args.size(),VARIABLES);
	}

	public double execute(Model model, List<Queue<String>> variableLists)
			throws StateNotFoundException, CommandException, UnrecognizedIDException {
		Home home = new Home(stringArguments);
		double distanceHome = home.execute(model, variableLists);
		model.getTurtleModel().getMainTurtle().getTrails().forEach(c -> model.getTurtleModel().removeTrail(c));
		return distanceHome;
	}
}
