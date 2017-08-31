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
 * IfElse receives two queues of commands in its execute method. If the value
 * passed to its constructor is non-zero, the first queue of commands is
 * executed, otherwise the second queue of commands is executed.
 * 
 * @author Kyle
 *
 */
public class IfElse extends AdvancedCommands {

	public static final int VARIABLES = 1;
	public static final int NUMBER_VARIABLE_LISTS = 2;

	public IfElse(List<String> arguments) throws CommandException {
		super(arguments);
		validNumberVariables(arguments.size(),VARIABLES);
		stringsToDoubles();
	}

	@Override
	public double execute(Model model, List<Queue<String>> variableLists)
			throws CommandException, StateNotFoundException, ParserException, UnrecognizedIDException {

		if (doubleArguments.get(0) != 0) {
			if (variableLists.get(0) != null && !variableLists.get(0).isEmpty()) {
				Parser parser = new Parser(model);
				return parser.addCode(variableLists.get(0), model.getLanguageModel().readOnlyLanguage().getValue());
			}
		} else {
			if (variableLists.get(1) != null && !variableLists.get(1).isEmpty()) {
				Parser parser = new Parser(model);
				return parser.addCode(variableLists.get(1), model.getLanguageModel().readOnlyLanguage().getValue());
			}
		}
		return 0;
	}

}
