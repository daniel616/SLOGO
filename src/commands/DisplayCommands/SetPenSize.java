package commands.DisplayCommands;

import java.util.List;
import java.util.Queue;

import exceptions.CommandException;
import model.Model;

/**
 * SetPenSize set the size of the pen that draws the lines of the Turtles based
 * on a size in pixels.
 * 
 * @author Kyle
 *
 */
public class SetPenSize extends DisplayCommands {

	public static final int VARIABLES = 1;
	public static final int NUMBER_VARIABLE_LISTS = 0;
	private static final int MAX_PEN_SIZE = 100;

	public SetPenSize(List<String> arguments) throws CommandException {
		super(arguments);
		validNumberVariables(arguments.size(), VARIABLES);
	}

	@Override
	public double execute(Model model, List<Queue<String>> variableLists) throws CommandException {
		try {
			int penSize = (int) Double.parseDouble(stringArguments.get(0));
			penSize = (penSize > MAX_PEN_SIZE) ? MAX_PEN_SIZE : penSize;
			model.getTurtleModel().setPenSize(penSize);
		} catch (Exception e) {
			throw new CommandException(getCommandException("PenSizeIndex"));
		}
		return doubleArguments.get(0);
	}

}
