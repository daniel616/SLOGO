package commands.BooleanCommands;

import java.util.List;
import java.util.Queue;

import exceptions.CommandException;
import model.Model;

/**
 * NotEqual returns 1 if the two arguments passed to the constructor are not
 * equal.
 * 
 * @author Kyle
 *
 */
public class NotEqual extends BooleanCommands {

	public static final int VARIABLES = 2;
	public static final int NUMBER_VARIABLE_LISTS = 0;

	public NotEqual(List<String> args) throws CommandException {
		super(args);
		validNumberVariables(args.size(),VARIABLES);
	}

	@Override
	public double execute(Model model, List<Queue<String>> variableLists) {
		return (doubleArguments.get(0) != doubleArguments.get(1)) ? 1.0 : 0.0;
	}

}
