package commands.DisplayCommands;

import java.util.List;
import java.util.Queue;

import exceptions.CommandException;
import model.Model;

/**
 * GetShape returns the index of the current shape of used to display Turtles to
 * the user.
 * 
 * @author Kyle
 *
 */
public class GetShape extends DisplayCommands {

	public static final int VARIABLES = 0;
	public static final int NUMBER_VARIABLE_LISTS = 0;

	public GetShape(List<String> arguments) throws CommandException {
		super(arguments);
		validNumberVariables(arguments.size(),VARIABLES);
	}

	@Override
	public double execute(Model model, List<Queue<String>> variableLists) {
		return model.getTurtleModel().imageIndex().getValue();
	}

}
