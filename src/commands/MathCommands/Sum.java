package commands.MathCommands;

import java.util.List;
import java.util.Queue;

import exceptions.CommandException;
import model.Model;

/**
 * Sum returns the sum of the two values passed to the constructor.
 * 
 * @author Kyle
 *
 */
public class Sum extends MathCommands {

	public static final int VARIABLES = 2;
	public static final int NUMBER_VARIABLE_LISTS = 0;

	public Sum(List<String> args) throws CommandException {
		super(args);
		validNumberVariables(args.size(),VARIABLES);
	}

	@Override
	public double execute(Model model, List<Queue<String>> variableLists) {
		return doubleArguments.get(0) + doubleArguments.get(1);
	}

}
