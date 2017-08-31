package commands.TurtleCommands;

import java.util.List;
import java.util.Queue;

import exceptions.CommandException;
import exceptions.StateNotFoundException;
import model.Model;

public class SetHeading extends TurtleCommands {

	public static final int VARIABLES = 1;
	public static final int NUMBER_VARIABLE_LISTS = 0;

	public SetHeading(List<String> args) throws CommandException {
		super(args);
		validNumberVariables(args.size(),VARIABLES);
	}

	@Override
	public double execute(Model model, List<Queue<String>> variableLists) throws StateNotFoundException {
		double normalizedHeading = normalizeHeading(doubleArguments.get(0));
		model.getTurtleModel().getMainTurtle().setHeading(normalizedHeading);

		return normalizedHeading;
	}

}
