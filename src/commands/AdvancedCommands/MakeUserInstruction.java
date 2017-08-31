package commands.AdvancedCommands;

import java.util.List;
import java.util.Queue;

import exceptions.CommandException;
import exceptions.StateNotFoundException;
import exceptions.ParserExceptions.CommandNotFoundException;
import exceptions.ParserExceptions.ReflectionException;
import model.Model;

/**
 * MakeUserInstruction instantiates an instance of a new instruction by saving a
 * list of variables needed for the instruction as well as a list of commands
 * needed to run the instruction.
 * 
 * @author Kyle
 *
 */
public class MakeUserInstruction extends AdvancedCommands {

	public static final int VARIABLES = 1;
	public static final int NUMBER_VARIABLE_LISTS = 2;

	public MakeUserInstruction(List<String> arguments) {
		super(arguments);
		validNumberVariables(arguments.size(),VARIABLES);
	}

	@Override
	public double execute(Model model, List<Queue<String>> variableLists)
			throws CommandException, CommandNotFoundException, ReflectionException, StateNotFoundException {

		try {
			model.getProgrammingModel().addCommand(stringArguments.get(0),
					new CommandDetails(variableLists.get(0), variableLists.get(1)));
		} catch (Exception e) {
			throw new CommandException(getCommandException("UserCommandInputs"));
		}
		return 1;
	}

}
