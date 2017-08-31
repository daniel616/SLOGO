package commands.MathCommands;

import java.util.List;
import java.util.Queue;

import exceptions.CommandException;
import model.Model;

/**
 * Quotient returns the result of the first value passed in divided by the
 * second value passed in. However, an exception is throw if the second value is
 * zero.
 * 
 * @author Kyle
 *
 */
public class Quotient extends MathCommands {

	public static final int VARIABLES = 2;
	public static final int NUMBER_VARIABLE_LISTS = 0;

	public Quotient(List<String> args) throws CommandException {
		super(args);
		validNumberVariables(args.size(),VARIABLES);
	}

	@Override
	public double execute(Model model, List<Queue<String>> variableLists) throws CommandException {
		if (doubleArguments.get(1) == 0) {
			throw new CommandException(getCommandException("DivideByZero"));
		}
		return doubleArguments.get(0) / doubleArguments.get(1);
	}

}
