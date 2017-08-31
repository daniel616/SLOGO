package commands.MultipleTurtleCommands;

import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import exceptions.CommandException;
import exceptions.StateNotFoundException;
import exceptions.UnrecognizedIDException;
import model.Model;

/**
 * Tell sets the group of Turtles passed to it based on their IDs active so that
 * all following Turtle commands run on all of these Turtles until Tell is
 * called again. The last Turtle set active becomes the primary active Turtle
 * for future commands.
 * 
 * @author Kyle
 *
 */
public class Tell extends MultipleTurtles {

	public static final int VARIABLES = 0;
	public static final int NUMBER_VARIABLE_LISTS = 1;

	public Tell(List<String> arguments) {
		super(arguments);
		validNumberVariables(arguments.size(), VARIABLES);
	}

	@Override
	public double execute(Model model, List<Queue<String>> variableLists)
			throws CommandException, StateNotFoundException, UnrecognizedIDException {
		
		model.getTurtleModel().deactivateTurtles(model.getTurtleModel().getActiveIDs());
		Set<Integer> newActiveTurtles = new HashSet<Integer>();
		try {
			for (String turtleID : variableLists.get(0)) {
				newActiveTurtles.add(Integer.parseInt(turtleID));
				model.getTurtleModel().activateTurtles(newActiveTurtles);
				model.getTurtleModel().setMainTurtle(Integer.parseInt(turtleID));
			}
		} catch (Exception e) {
			throw new CommandException(getCommandException("TellIDs"));
		}
		return model.getTurtleModel().getMainTurtle().getID();
	}

}
