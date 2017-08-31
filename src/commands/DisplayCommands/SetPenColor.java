package commands.DisplayCommands;

import java.util.List;
import java.util.Queue;

import exceptions.CommandException;
import model.Model;

/**
 * SetPenColor sets the pen color of all the Turtles based on an index
 * corresponding to a specific color
 * 
 * @author Kyle
 *
 */
public class SetPenColor extends DisplayCommands {

	public static final int VARIABLES = 1;
	public static final int NUMBER_VARIABLE_LISTS = 0;

	public SetPenColor(List<String> arguments) throws CommandException {
		super(arguments);
		validNumberVariables(arguments.size(),VARIABLES);
	}

	@Override
	public double execute(Model model, List<Queue<String>> variableLists) throws CommandException {
		try{
		model.getTurtleModel().setTrailColor(Integer.parseInt(stringArguments.get(0)));
		}catch (Exception e){
			throw new CommandException(getCommandException("PenColorIndex"));
		}
		return doubleArguments.get(0);
	}

}
