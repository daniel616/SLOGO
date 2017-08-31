package commands.DisplayCommands;

import java.util.List;
import java.util.Queue;

import exceptions.CommandException;
import model.Model;

/**
 * SetShape changes the image used for the Turtles based on an index that
 * corresponds to a specific image.
 * 
 * @author Kyle
 *
 */
public class SetShape extends DisplayCommands {

	public static final int VARIABLES = 1;
	public static final int NUMBER_VARIABLE_LISTS = 0;

	public SetShape(List<String> arguments) throws CommandException {
		super(arguments);
		validNumberVariables(arguments.size(),VARIABLES);
	}

	@Override
	public double execute(Model model, List<Queue<String>> variableLists) throws CommandException {
		try{
		model.getTurtleModel().setShape(Integer.parseInt(stringArguments.get(0)));
		} catch(Exception e){
			throw new CommandException(getCommandException("ShapeIndex"));
		}
		return doubleArguments.get(0);
	}

}
