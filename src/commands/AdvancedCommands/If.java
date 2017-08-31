package commands.AdvancedCommands;

import java.util.List;
import java.util.Queue;

import exceptions.CommandException;
import exceptions.StateNotFoundException;
import exceptions.UnrecognizedIDException;
import exceptions.ParserExceptions.ParserException;
import model.Model;
import parser.Parser;

/**
 * If executes the commands passed to it if the value passed to its constructor
 * is non-zero.
 * 
 * @author Kyle
 *
 */
public class If extends AdvancedCommands {

	public static final int VARIABLES = 1;
	public static final int NUMBER_VARIABLE_LISTS = 1;

	public If(List<String> arguments) throws CommandException {
		super(arguments);
		validNumberVariables(arguments.size(),VARIABLES);
		stringsToDoubles();
	}

	@Override
	public double execute(Model model, List<Queue<String>> variableLists)
			throws CommandException, StateNotFoundException, ParserException, UnrecognizedIDException {

		if (doubleArguments.get(0) != 0 && variableLists.get(0) != null && !variableLists.get(0).isEmpty()) {
			Parser parser = new Parser(model);
			return parser.addCode(variableLists.get(0), model.getLanguageModel().readOnlyLanguage().getValue());

		}
		return 0;
	}

}
