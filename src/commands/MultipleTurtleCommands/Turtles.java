package commands.MultipleTurtleCommands;

import java.util.List;
import java.util.Queue;

import model.Model;

/**
 * Turtles returns the number of Turtles that currently exist.
 * 
 * @author Kyle
 *
 */
public class Turtles extends MultipleTurtles {

	public static final int VARIABLES = 0;
	public static final int NUMBER_VARIABLE_LISTS = 0;

	public Turtles(List<String> arguments) {
		super(arguments);
		validNumberVariables(arguments.size(),VARIABLES);
	}

	@Override
	public double execute(Model model, List<Queue<String>> variableLists) {
		return model.getTurtleModel().numTurtles();
	}

}
