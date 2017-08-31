package commands.MathCommands;

import java.util.List;
import java.util.Queue;

import exceptions.CommandException;
import model.Model;

/** Returns value of Pi.
 * 
 * @author Kyle
 *
 */
public class Pi extends MathCommands {
	
	public static final int VARIABLES = 0;
	public static final int NUMBER_VARIABLE_LISTS = 0;

	public Pi(List<String> args) throws CommandException {
		super(args);
		validNumberVariables(args.size(),VARIABLES);
	}

	public double execute(Model model, List<Queue<String>> variableLists){
		return Math.PI;
	}
}
