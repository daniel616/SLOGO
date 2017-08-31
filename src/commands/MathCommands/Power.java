package commands.MathCommands;

import java.util.List;
import java.util.Queue;

import exceptions.CommandException;
import model.Model;

/**
 * Power returns the first number raised to the power specified by the second
 * number.
 * 
 * @author Kyle
 *
 */
public class Power extends MathCommands {

	public static final int VARIABLES = 2;
	public static final int NUMBER_VARIABLE_LISTS = 0;

	public Power(List<String> args) throws CommandException {
		super(args);
		validNumberVariables(args.size(),VARIABLES);
	}

	@Override
	public double execute(Model model, List<Queue<String>> variableLists) {
		return Math.pow(doubleArguments.get(0), doubleArguments.get(1));
	}
}
