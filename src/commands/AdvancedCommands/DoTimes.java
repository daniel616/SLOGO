package commands.AdvancedCommands;

import java.util.List;
import java.util.Queue;

import exceptions.CommandException;
import exceptions.StateNotFoundException;
import exceptions.UnrecognizedIDException;
import exceptions.ParserExceptions.ParserException;
import model.Model;

/**
 * DoTimes loops through the the list of commands passed to it the number of
 * times passed to the constructor.
 * 
 * @author Kyle
 *
 */
public class DoTimes extends AdvancedCommands {

	public static final int VARIABLES = 0;
	public static final int NUMBER_VARIABLE_LISTS = 2;

	public DoTimes(List<String> args) {
		super(args);
		validNumberVariables(args.size(), VARIABLES);
	}

	@Override
	public double execute(Model model, List<Queue<String>> variableLists)
			throws CommandException, StateNotFoundException, ParserException, UnrecognizedIDException {

		if (variableLists.get(1) == null || variableLists.get(1).isEmpty()) {
			return 0;
		}

		double limit;
		String variable;

		try {
			variable = variableLists.get(0).poll();
		} catch (Exception e) {
			throw new RuntimeException(getCommandException("DoTimesVariable"));
		}
		try {
			limit = Double.parseDouble(variableLists.get(0).peek());
		} catch (Exception e) {
			limit = checkVariables(variableLists.get(0).poll(),model);
		}

		return commandLoop(1, limit, 1, variableLists.get(1), variable, model);
	}

}
