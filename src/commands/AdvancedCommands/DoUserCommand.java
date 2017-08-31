package commands.AdvancedCommands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import exceptions.CommandException;
import exceptions.StateNotFoundException;
import exceptions.UnrecognizedIDException;
import exceptions.ParserExceptions.ParserException;
import model.Model;
import parser.Parser;


/** DoUserCommand executes a command previously instantiated by the user.
 * 
 * @author Kyle
 *
 */
public class DoUserCommand extends AdvancedCommands {
	
	public static final int NUMBER_VARIABLE_LISTS = 0;

	public DoUserCommand(List<String> arguments) throws CommandException {
		super(arguments);
	}

	@Override
	public double execute(Model model, List<Queue<String>> variableLists)
			throws CommandException, StateNotFoundException, ParserException, UnrecognizedIDException {
		
		Map<String,String> globalVariables = model.getProgrammingModel().copyVariablesMap();
		
		MakeVariable maker;
		Queue<String> variableNames = cloneQueue(variableLists.get(0));
		for(String variableValue: stringArguments){
			String[] variableParameters = {variableNames.poll(),variableValue};
			maker = new MakeVariable(Arrays.asList(variableParameters));
			maker.execute(model, new ArrayList<Queue<String>>());
		}
		
		Parser parser = new Parser(model);
		double finalCommandValue = parser.addCode(cloneQueue(variableLists.get(1)), model.getLanguageModel().readOnlyLanguage().getValue());
		
		variableNames.forEach(c -> model.getProgrammingModel().removeVariable(c));
		globalVariables.forEach((c,b) -> model.getProgrammingModel().addVariable(c,b));
		
		return finalCommandValue;
	}

}
