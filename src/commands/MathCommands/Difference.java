package commands.MathCommands;

import java.util.List;
import java.util.Queue;

import exceptions.CommandException;
import model.Model;

/**
 * Difference returns the second argument passed in subtracted from the second
 * argument entered
 * 
 * @author Kyle
 *
 */
public class Difference extends MathCommands {

	public static final int VARIABLES = 2;
	public static final int NUMBER_VARIABLE_LISTS = 0;

	public Difference(List<String> args) throws CommandException {
		super(args);
		validNumberVariables(args.size(),VARIABLES);
	}

	@Override
	public double execute(Model model, List<Queue<String>> variableLists) {
		return doubleArguments.get(0) - doubleArguments.get(1);
	}

}
