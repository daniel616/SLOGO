package commands.TurtleCommands;

import java.util.List;

import commands.CircleVariables;
import commands.Commands;
import exceptions.CommandException;
import exceptions.StateNotFoundException;
import gui.TurtleScreen;
import turtleDetails.ReadableState;
import turtleDetails.Turtle;

/**
 * Parent class of all Turtle commands.
 * 
 * @author Kyle
 *
 */
public abstract class TurtleCommands extends Commands {

	public TurtleCommands(List<String> args) throws CommandException {
		super(args);
		stringsToDoubles();
	}

	protected double normalizeHeading(double heading) {
		double newHeading = heading % CircleVariables.CIRCLE_DEGREES.value();

		if (newHeading < 0) {
			return CircleVariables.CIRCLE_DEGREES.value() + newHeading;
		}
		return newHeading;
	}

	protected double move(Turtle t, boolean isForward) throws StateNotFoundException {
		ReadableState lastState = t.getFinalTurtleState();

		double newX = lastState.getX() + (isForward ? 1.0 : -1.0) * doubleArguments.get(0)
				* Math.cos(lastState.getHeading() * CircleVariables.DEGREES_TO_RADIANS.value());
		double newY = lastState.getY() - (isForward ? 1.0 : -1.0) * doubleArguments.get(0)
				* Math.sin(lastState.getHeading() * CircleVariables.DEGREES_TO_RADIANS.value());

		double validX = newLocation(newX);
		double validY = newLocation(newY);
		
		t.setPosition(validX, validY);

		return doubleArguments.get(0);
	}

	protected double newLocation(double newX) {
		
		double screenEdge = TurtleScreen.SCREEN_SIZE / 2 - TurtleScreen.TURTLE_SIZE/2;

		if (newX > screenEdge) {
			newX = screenEdge;
		}
		if (newX < -screenEdge) {
			newX = -screenEdge;
		}

		return newX;

	}

}