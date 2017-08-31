package commands.MathCommands;

import java.util.List;
import java.util.Queue;

import commands.CircleVariables;
import exceptions.CommandException;
import model.Model;

/** Tangent returns the tangent of the entered degrees.
 * 
 * @author Kyle
 *
 */
public class Tangent extends MathCommands {
	
	public static final int VARIABLES = 1;
	public static final int NUMBER_VARIABLE_LISTS = 0;

	public Tangent(List<String> args) throws CommandException {
		super(args);
		validNumberVariables(args.size(),VARIABLES);
	}

	@Override
	public double execute(Model model, List<Queue<String>> variableLists) {
		return Math.tan(doubleArguments.get(0)*CircleVariables.DEGREES_TO_RADIANS.value());
	}
	
}
