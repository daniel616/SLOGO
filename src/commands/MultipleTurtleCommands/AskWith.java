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
import turtleDetails.Turtle;

/**
 * AskWith checks if a boolean condition returns true for all Turtles that
 * currently exist. If the condition is true for a Turtle, the commands passed
 * to execute will be executed for that Turtle.
 * 
 * @author Kyle
 *
 */
public class AskWith extends MultipleTurtles {

	public static final int VARIABLES = 0;
	public static final int NUMBER_VARIABLE_LISTS = 2;

	public AskWith(List<String> arguments) {
		super(arguments);
		validNumberVariables(arguments.size(), VARIABLES);
	}

	@Override
	public double execute(Model model, List<Queue<String>> variableLists)
			throws CommandException, StateNotFoundException, ParserException, UnrecognizedIDException {

		Set<Integer> mainActiveTurtles = model.getTurtleModel().getActiveIDs();
		model.getTurtleModel().deactivateTurtles(mainActiveTurtles);
		double finalCommand = updateProperTurtles(model, variableLists);
		model.getTurtleModel().activateTurtles(mainActiveTurtles);

		return finalCommand;
	}

	private double updateProperTurtles(Model model, List<Queue<String>> variableLists)
			throws ParserException, StateNotFoundException, CommandException, UnrecognizedIDException {
		double finalCommand = 0;

		Parser parser = new Parser(model);
		Set<Integer> currentTurtle = new HashSet<Integer>();
		for (Turtle turtle: model.getTurtleModel().getAllTurtles()) {
			model.getTurtleModel()
					.deactivateTurtles(new HashSet<Integer>(model.getTurtleModel().getMainTurtle().getID()));
			currentTurtle.add(turtle.getID());
			model.getTurtleModel()
					.activateTurtles(currentTurtle);
			if (parser.addCode(variableLists.get(0), model.getLanguageModel().readOnlyLanguage().getValue()) != 0) {
				finalCommand = parser.addCode(variableLists.get(1), model.getLanguageModel().readOnlyLanguage().getValue());
			}
		}		
		model.getTurtleModel().deactivateTurtles(new HashSet<Integer>(model.getTurtleModel().getMainTurtle().getID()));
		return finalCommand;
	}
	
	
}
