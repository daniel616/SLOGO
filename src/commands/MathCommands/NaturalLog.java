package commands.MathCommands;

import java.util.List;
import java.util.Queue;

import exceptions.CommandException;
import model.Model;

/**
 * NaturalLog returns the of the entered value if it is greater than 0.
 * Otherwise, an error is thrown.
 * 
 * @author Kyle
 *
 */
public class NaturalLog extends MathCommands {

	public static final int VARIABLES = 1;
	public static final int NUMBER_VARIABLE_LISTS = 0;

	public NaturalLog(List<String> args) throws CommandException {
		super(args);
		validNumberVariables(args.size(), VARIABLES);
	}

	@Override
	public double execute(Model model, List<Queue<String>> variableLists) throws CommandException {
		if (doubleArguments.get(0) <= 0) {
			throw new CommandException(getCommandException("NaturalLogValue"));
		}
		return Math.log(doubleArguments.get(0));
	}
}
