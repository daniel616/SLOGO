package commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.ResourceBundle;

import exceptions.CommandException;
import exceptions.StateNotFoundException;
import exceptions.UnrecognizedIDException;
import exceptions.ParserExceptions.CommandNotFoundException;
import exceptions.ParserExceptions.ParserException;
import exceptions.ParserExceptions.ReflectionException;
import model.Model;

/**
 * Top class of command hierarchy. Provides constructor for subclasses and
 * maintains lists of arguments needed for a single command.
 * 
 * @author Kyle
 *
 */
public abstract class Commands {

	protected List<String> stringArguments = new ArrayList<String>();
	protected List<Double> doubleArguments = new ArrayList<Double>();
	private final ResourceBundle COMMAND_EXCEPTIONS = ResourceBundle.getBundle("resources/commandExceptions");

	public Commands(List<String> arguments) {
		stringArguments = arguments;
	}

	protected void validNumberVariables(int variables, int arguments) {
		if (arguments != variables) {
			throw new RuntimeException(getCommandException("VariableNumber"));
		}
	}

	/**
	 * execute is implemented by every individual command. execute runs the
	 * specific command and returns the value that results from executing the
	 * command.
	 * 
	 * @param model
	 *            is the model containing the necessary data to run all commands
	 * @param variableLists
	 *            contains the information contained in brackets that is
	 *            required for commands to run
	 * @return result of executing a command
	 * @throws CommandException
	 * @throws CommandNotFoundException
	 * @throws ReflectionException
	 * @throws StateNotFoundException
	 * @throws ParserException
	 * @throws UnrecognizedIDException
	 */
	public abstract double execute(Model model, List<Queue<String>> variableLists)
			throws CommandException, CommandNotFoundException, ReflectionException, StateNotFoundException,
			ParserException, UnrecognizedIDException;

	protected void stringsToDoubles() throws CommandException {
		for (String s : stringArguments) {
			try {
				doubleArguments.add(Double.parseDouble(s));
			} catch (Exception e) {
				throw new CommandException(getCommandException("VariableNumber"));
			}
		}
	}

	protected String getCommandException(String exceptionCode) {
		try {
			return COMMAND_EXCEPTIONS.getString(exceptionCode);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
}
