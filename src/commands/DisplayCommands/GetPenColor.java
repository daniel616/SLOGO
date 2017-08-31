package commands.DisplayCommands;

import java.util.List;
import java.util.Queue;

import exceptions.CommandException;
import model.Model;

/**
 * GetPenColor returns the index corresponding to the current color of the pen
 * used to draw lines for Turtles.
 * 
 * @author Kyle
 *
 */
public class GetPenColor extends DisplayCommands {

	public static final int VARIABLES = 0;
	public static final int NUMBER_VARIABLE_LISTS = 0;

	public GetPenColor(List<String> arguments) throws CommandException {
		super(arguments);
		validNumberVariables(arguments.size(), VARIABLES);
	}

	@Override
	public double execute(Model model, List<Queue<String>> variableLists) {
		return model.getTurtleModel().trailColorIndex().getValue();
	}

}
