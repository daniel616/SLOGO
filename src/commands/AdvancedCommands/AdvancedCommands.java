package commands.AdvancedCommands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import commands.Commands;
import exceptions.CommandException;
import exceptions.StateNotFoundException;
import exceptions.UnrecognizedIDException;
import exceptions.ParserExceptions.ParserException;
import model.Model;
import parser.Parser;

/**
 * Parent class of all Advanced commands such as loops, if statements, and
 * variable and command makers.
 * 
 * @author Kyle
 *
 */
public abstract class AdvancedCommands extends Commands {

	public AdvancedCommands(List<String> arguments) {
		super(arguments);
	}

	protected Queue<String> cloneQueue(Queue<String> variableList) {
		Queue<String> newQueue = new LinkedList<String>();
		Iterator<String> iter = variableList.iterator();
		while (iter.hasNext()) {
			newQueue.add(iter.next());
		}
		return newQueue;
	}

	protected double commandLoop(double start, double end, double increment, Queue<String> commands,
			String variableName, Model model)
			throws CommandException, ParserException, StateNotFoundException, UnrecognizedIDException {
		MakeVariable maker;
		Parser parser;
		double finalCommand = 0;
		for (double i = start; i <= end; i += increment) {
			String[] variableParameters = { variableName, "" + i };
			maker = new MakeVariable(Arrays.asList(variableParameters));
			maker.execute(model, new ArrayList<Queue<String>>());

			parser = new Parser(model);
			finalCommand = parser.addCode(cloneQueue(commands), model.getLanguageModel().readOnlyLanguage().getValue());
		}

		model.getProgrammingModel().removeVariable(variableName);
		return finalCommand;
	}

	protected double checkVariables(String variableName, Model model) throws CommandException {
		try {
			return Double.parseDouble(model.getProgrammingModel().readOnlyVariables().get(variableName));

		} catch (Exception e) {
			throw new CommandException(getCommandException("NotDouble"));
		}
	}

}
