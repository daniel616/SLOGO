package commands.MathCommands;

import java.util.List;
import java.util.Queue;

import commands.CircleVariables;
import exceptions.CommandException;
import model.Model;

/**
 * Cosine returns the cosine of the angle entered, assuming the angle is in
 * degrees.
 * 
 * @author Kyle
 *
 */
public class Cosine extends MathCommands {

	public static final int VARIABLES = 1;
	public static final int NUMBER_VARIABLE_LISTS = 0;

	public Cosine(List<String> args) throws CommandException {
		super(args);
		validNumberVariables(args.size(),VARIABLES);
	}

	@Override
	public double execute(Model model, List<Queue<String>> variableLists) {
		return Math.cos(doubleArguments.get(0) * CircleVariables.DEGREES_TO_RADIANS.value());
	}

}
