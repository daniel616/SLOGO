package commands.AdvancedCommands;

import java.util.List;
import java.util.Queue;

import exceptions.CommandException;
import exceptions.StateNotFoundException;
import exceptions.UnrecognizedIDException;
import exceptions.ParserExceptions.ParserException;
import model.Model;

/**
 * Repeat loops through a queue of commands for the number of times specified in
 * the constructor.
 * 
 * @author Kyle
 *
 */
public class Repeat extends AdvancedCommands {

	public static final int VARIABLES = 1;
	public static final int NUMBER_VARIABLE_LISTS = 1;
	private final String REPCOUNT = ":repcount";

	public Repeat(List<String> args) throws CommandException {
		super(args);
		validNumberVariables(args.size(), VARIABLES);
		stringsToDoubles();
	}

	@Override
	public double execute(Model model, List<Queue<String>> variableLists)
			throws CommandException, StateNotFoundException, ParserException, UnrecognizedIDException {
		if (variableLists.get(0).isEmpty()) {
			return 0;
		}

		double returnValue = commandLoop(1, doubleArguments.get(0), 1, variableLists.get(0), REPCOUNT, model);

		return returnValue;

	}

}
