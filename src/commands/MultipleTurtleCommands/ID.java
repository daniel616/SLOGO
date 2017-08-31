package commands.MultipleTurtleCommands;

import java.util.List;
import java.util.Queue;

import exceptions.StateNotFoundException;
import model.Model;

/**
 * ID returns the ID value of the primary active Turtle.
 * 
 * @author Kyle
 *
 */
public class ID extends MultipleTurtles {

	public static final int VARIABLES = 0;
	public static final int NUMBER_VARIABLE_LISTS = 0;

	public ID(List<String> arguments) {
		super(arguments);
		validNumberVariables(arguments.size(),VARIABLES);
	}

	@Override
	public double execute(Model model, List<Queue<String>> variableLists) throws StateNotFoundException {
		return model.getTurtleModel().getMainTurtle().getID();
	}

}
