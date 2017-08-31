package commands.BooleanCommands;

import java.util.List;
import java.util.Queue;

import exceptions.CommandException;
import model.Model;

/** Not returns 1 if the value passed to the constructor is not equal to 0.
 * 
 * @author Kyle
 *
 */
public class Not extends BooleanCommands {
	
	public static final int VARIABLES = 1;
	public static final int NUMBER_VARIABLE_LISTS = 0;

	public Not(List<String> args) throws CommandException {
		super(args);
		validNumberVariables(args.size(),VARIABLES);
	}

	@Override
	public double execute(Model model, List<Queue<String>> variableLists) {
		return (doubleArguments.get(0) == 0)?1.0:0.0;
	}

}
