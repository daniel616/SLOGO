package commands.MultipleTurtleCommands;

import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import exceptions.CommandException;
import exceptions.StateNotFoundException;
import model.Model;

/**
 * Deactivate removes Turtles from the list of Turtles that are currently
 * executing Turtle Commands based on the entered queue of Turtle IDs.
 * Deactivate returns 1 if the Turtle's were removed.
 * 
 * @author Kyle
 *
 */
public class Deactivate extends MultipleTurtles {

	public static final int VARIABLES = 0;
	public static final int NUMBER_VARIABLE_LISTS = 1;

	public Deactivate(List<String> arguments) {
		super(arguments);
		validNumberVariables(arguments.size(),VARIABLES);
	}

	@Override
	public double execute(Model model, List<Queue<String>> variableLists)
			throws CommandException, StateNotFoundException {
		Set<Integer> deactiveTurtles = new HashSet<Integer>();
		try {
			variableLists.get(0).forEach(c -> deactiveTurtles.add(Integer.parseInt(c)));
			model.getTurtleModel().deactivateTurtles(deactiveTurtles);
		} catch (Exception e) {
			throw new CommandException(getCommandException("DeactivateIDs"));
		}
		return 1;
	}

}
