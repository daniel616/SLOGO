package commands.TurtleCommands;

import java.util.List;
import java.util.Queue;

import exceptions.CommandException;
import exceptions.StateNotFoundException;
import model.Model;
import turtleDetails.Turtle;

public class Home extends TurtleCommands {

	public static final int VARIABLES = 0;
	public static final int NUMBER_VARIABLE_LISTS = 0;

	public Home(List<String> args) throws CommandException {
		super(args);
		validNumberVariables(args.size(), VARIABLES);
	}

	public double execute(Model model, List<Queue<String>> variableLists) throws StateNotFoundException {
		Turtle turtle=model.getTurtleModel().getMainTurtle();
		double previousX=turtle.getFinalTurtleState().getX();
		double previousY=turtle.getFinalTurtleState().getY();
		model.getTurtleModel().getMainTurtle().setPosition(0, 0);

		return Math.sqrt(Math.pow(previousX, 2) + Math.pow(previousY, 2));
	}
}
