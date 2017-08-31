package commands.BooleanCommands;

import java.util.List;
import java.util.Queue;

import exceptions.CommandException;
import model.Model;

/**
 * GreaterThan returns 1 if the first argument passed to the constructor is
 * greater than the second argument passed to the constructor.
 * 
 * @author Kyle
 *
 */
public class GreaterThan extends BooleanCommands {

	public static final int VARIABLES = 2;
	public static final int NUMBER_VARIABLE_LISTS = 0;

	public GreaterThan(List<String> args) throws CommandException {
		super(args);
		validNumberVariables(args.size(),VARIABLES);
	}

	@Override
	public double execute(Model model, List<Queue<String>> variableLists) {
		return (doubleArguments.get(0) > doubleArguments.get(1)) ? 1.0 : 0.0;
	}

}
