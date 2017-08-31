package commands.BooleanCommands;

import java.util.List;
import java.util.Queue;

import exceptions.CommandException;
import model.Model;

/** Less returns one if the first of two arguments is less than the second argument and zero otherwise.
 * 
 * @author Kyle
 *
 */
public class LessThan extends BooleanCommands {
	
	public static final int VARIABLES = 2;
	public static final int NUMBER_VARIABLE_LISTS = 0;

	public LessThan(List<String> args) throws CommandException {
		super(args);
		validNumberVariables(args.size(),VARIABLES);
	}

	@Override
	public double execute(Model model, List<Queue<String>> variableLists) {
		return (doubleArguments.get(0) < doubleArguments.get(1))?1.0:0.0;
	}
}
