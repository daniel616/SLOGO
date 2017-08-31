package commands.BooleanCommands;

import java.util.List;
import java.util.Queue;

import exceptions.CommandException;
import model.Model;

/**
 * And determines if two numbers are both true (i.e. not equal to zero) and
 * returns one if they're both true, or zero otherwise.
 * 
 * @author Kyle
 *
 */
public class And extends BooleanCommands {

	public static final int VARIABLES = 2;
	public static final int NUMBER_VARIABLE_LISTS = 0;

	public And(List<String> args) throws CommandException {
		super(args);
		validNumberVariables(args.size(),VARIABLES);
	}

	@Override
	public double execute(Model model, List<Queue<String>> variableLists) {
		return (doubleArguments.get(0) != 0 && doubleArguments.get(1) != 0) ? 1.0 : 0.0;
	}
}
