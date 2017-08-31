package commands.MultipleTurtleCommands;

import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import exceptions.CommandException;
import exceptions.StateNotFoundException;
import exceptions.UnrecognizedIDException;
import exceptions.ParserExceptions.ParserException;
import model.Model;
import parser.Parser;

/**
 * Ask temporarily overrides the list of active Turtles to run a group of
 * commands on a specified group of Turtles.
 * 
 * @author Kyle
 *
 */
public class Ask extends MultipleTurtles {

	public static final int VARIABLES = 0;
	public static final int NUMBER_VARIABLE_LISTS = 2;

	public Ask(List<String> arguments) {
		super(arguments);
		validNumberVariables(arguments.size(), VARIABLES);
	}

	@Override
	public double execute(Model model, List<Queue<String>> variableLists)
			throws CommandException, StateNotFoundException, ParserException, UnrecognizedIDException {

		Set<Integer> mainActiveTurtles = model.getTurtleModel().getActiveIDs();

		Set<Integer> temporaryActiveTurtles = new HashSet<Integer>();

		try {
			variableLists.get(0).forEach(c -> temporaryActiveTurtles.add(Integer.parseInt(c)));
		} catch (Exception e) {
			throw new CommandException(getCommandException("AskIDs"));
		}

		model.getTurtleModel().deactivateTurtles(mainActiveTurtles);
		model.getTurtleModel().activateTurtles(temporaryActiveTurtles);

		Parser parser = new Parser(model);
		double finalCommand = parser.addCode(variableLists.get(1), model.getLanguageModel().readOnlyLanguage().getValue());

		model.getTurtleModel().deactivateTurtles(temporaryActiveTurtles);
		model.getTurtleModel().activateTurtles(mainActiveTurtles);

		return finalCommand;
	}

}
