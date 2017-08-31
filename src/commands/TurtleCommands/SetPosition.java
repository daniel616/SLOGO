package commands.TurtleCommands;

import java.util.List;
import java.util.Queue;

import exceptions.CommandException;
import exceptions.StateNotFoundException;
import model.Model;
import turtleDetails.Turtle;

/**
 * SetPosition move the Turtle to the entered location and returns the distance
 * moved to get from its previous location to its new location.
 * 
 * @author Kyle
 *
 */
public class SetPosition extends TurtleCommands {

	public static final int VARIABLES = 2;
	public static final int NUMBER_VARIABLE_LISTS = 0;

	public SetPosition(List<String> args) throws CommandException {
		super(args);
		validNumberVariables(args.size(),VARIABLES);
	}

	@Override
	public double execute(Model model, List<Queue<String>> variableLists)
			throws StateNotFoundException, CommandException {
		Turtle turtle=model.getTurtleModel().getMainTurtle();
		double previousX=turtle.getFinalTurtleState().getX();
		double previousY=turtle.getFinalTurtleState().getY();
		
		Double xLocation = newLocation(doubleArguments.get(0));
		Double yLocation = newLocation(-doubleArguments.get(1));

		model.getTurtleModel().getMainTurtle().setPosition(xLocation, yLocation);
		return Math.sqrt(Math.pow(xLocation - previousX, 2) + Math.pow(yLocation - previousY, 2));
	}

}
