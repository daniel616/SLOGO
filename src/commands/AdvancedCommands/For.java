package commands.AdvancedCommands;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import exceptions.CommandException;
import exceptions.StateNotFoundException;
import exceptions.UnrecognizedIDException;
import exceptions.ParserExceptions.ParserException;
import model.Model;

/**
 * For loops through all of the commands passed to it a number of times
 * determined by the start, end, and increment values passed to its constructor.
 * 
 * @author Kyle
 *
 */
public class For extends AdvancedCommands {

	public static final int VARIABLES = 0;
	public static final int NUMBER_VARIABLE_LISTS = 2;

	public For(List<String> arguments) {
		super(arguments);
		validNumberVariables(arguments.size(),VARIABLES);
	}

	@Override
	public double execute(Model model, List<Queue<String>> variableLists)
			throws CommandException, StateNotFoundException, ParserException, UnrecognizedIDException {

		String variable;
		try {
			variable = variableLists.get(0).poll();
		} catch (Exception e) {
			throw new CommandException(getCommandException("ForVariable"));
		}
		
		List<Double> parameters = new ArrayList<Double>();
			for(String parameter: variableLists.get(0)){
				try{
					parameters.add(Double.parseDouble(parameter));
				} catch(Exception e){
					parameters.add(checkVariables(parameter,model));
				}
			}

		if (variableLists.get(1) == null || variableLists.get(1).isEmpty()) {
			return 0;
		}

		return commandLoop(parameters.get(0), parameters.get(1), parameters.get(2), variableLists.get(1), variable, model);
	}

}
