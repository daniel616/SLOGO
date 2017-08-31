package commands.AdvancedCommands;

import java.util.List;
import java.util.Queue;

import exceptions.CommandException;
import model.Model;

/**
 * MakeVariable takes in a variable name and a corresponding value and saves
 * them for future use by the user.
 * 
 * @author Kyle
 *
 */
public class MakeVariable extends AdvancedCommands {

	public static final int VARIABLES = 2;
	public static final int NUMBER_VARIABLE_LISTS = 0;

	public MakeVariable(List<String> args) {
		super(args);
		validNumberVariables(args.size(),VARIABLES);
	}

	@Override
	public double execute(Model model, List<Queue<String>> variableLists) throws CommandException {
		double variableValue;
		
		if(stringArguments.get(0).charAt(0) != ':'){
			throw new CommandException(getCommandException("InvalidVariableName"));
		}
		try {
			variableValue = Double.parseDouble(stringArguments.get(1));
		} catch (Exception e) {
			throw new CommandException(getCommandException("VariableInput"));
		}
		model.getProgrammingModel().addVariable(stringArguments.get(0), stringArguments.get(1));
		return variableValue;
	}

}
