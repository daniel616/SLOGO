package commands.DisplayCommands;

import java.util.List;
import java.util.Queue;

import exceptions.CommandException;
import model.Model;

/**
 * SetBackground sets the background color of the Turtle's screen based on an
 * index corresponding to a specific color
 * 
 * @author Kyle
 *
 */
public class SetBackground extends DisplayCommands {

	public static final int VARIABLES = 1;
	public static final int NUMBER_VARIABLE_LISTS = 0;

	public SetBackground(List<String> arguments) throws CommandException {
		super(arguments);
		validNumberVariables(arguments.size(), VARIABLES);
	}

	@Override
	public double execute(Model model, List<Queue<String>> variableLists) throws CommandException {
		try {
			model.getVisualModel().setBackgroundColor(Integer.parseInt(stringArguments.get(0)));
		} catch (Exception e) {
			throw new CommandException(getCommandException("BackgroundColorIndex"));
		}
		return doubleArguments.get(0);
	}

}
