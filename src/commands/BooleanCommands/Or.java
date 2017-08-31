package commands.BooleanCommands;

import java.util.List;
import java.util.Queue;

import exceptions.CommandException;
import model.Model;

/**
 * Or returns 1 if either argument passed to the constructor is non-zero.
 * 
 * @author Kyle
 *
 */
public class Or extends BooleanCommands {

	public static final int VARIABLES = 2;
	public static final int NUMBER_VARIABLE_LISTS = 0;

	public Or(List<String> args) throws CommandException {
		super(args);
		validNumberVariables(args.size(),VARIABLES);
	}

	@Override
	public double execute(Model model, List<Queue<String>> variableLists) {
		return (doubleArguments.get(0) != 0 || doubleArguments.get(1) != 0) ? 1.0 : 0.0;
	}

}
