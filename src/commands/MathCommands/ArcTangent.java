package commands.MathCommands;

import java.util.List;
import java.util.Queue;

import commands.CircleVariables;
import exceptions.CommandException;
import model.Model;

/**
 * ArcTangent returns the inverse tangent of the entered degrees value.
 * 
 * @author Kyle
 *
 */
public class ArcTangent extends MathCommands {

	public static final int VARIABLES = 1;
	public static final int NUMBER_VARIABLE_LISTS = 0;

	public ArcTangent(List<String> args) throws CommandException {
		super(args);
		validNumberVariables(args.size(),VARIABLES);
	}

	@Override
	public double execute(Model model, List<Queue<String>> variableLists) {
		return Math.atan(doubleArguments.get(0)) * CircleVariables.RADIANS_TO_DEGREES.value();
	}

}
