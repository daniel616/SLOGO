package commands.TurtleCommands;

import java.util.List;
import java.util.Queue;

import commands.CircleVariables;
import exceptions.CommandException;
import exceptions.StateNotFoundException;
import model.Model;
import turtleDetails.ReadableState;

/**
 * SetTowards makes the given Turtle point towards the given location. Returns
 * how far the Turtle turned.
 * 
 * @author Kyle
 *
 */
public class SetTowards extends TurtleCommands {

	public static final int VARIABLES = 2;
	public static final int NUMBER_VARIABLE_LISTS = 0;

	public SetTowards(List<String> args) throws CommandException {
		super(args);
		validNumberVariables(args.size(), VARIABLES);
	}

	@Override
	public double execute(Model model, List<Queue<String>> variableLists) throws StateNotFoundException {
		ReadableState lastState = model.getTurtleModel().getMainTurtle().getFinalTurtleState();

		double normalizedX = doubleArguments.get(0) - lastState.getX();
		double normalizedY = doubleArguments.get(1) + lastState.getY();
		double newHeading = calculateAngle(normalizedX, normalizedY, lastState);
		model.getTurtleModel().getMainTurtle().setHeading(newHeading);

		return Math.abs(newHeading - lastState.getHeading());
	}

	private double calculateAngle(double towardX, double towardY, ReadableState lastState) {
		if (towardX == 0) {
			if (towardY == 0) {
				return lastState.getHeading();
			} else if (towardY > 0) {
				return CircleVariables.FIRST_QUADRANT_END.value();
			} else {
				return CircleVariables.THIRD_QUADRANT_END.value();
			}
		}

		double radiansToDegrees = Math.atan2(towardY, towardX) * CircleVariables.RADIANS_TO_DEGREES.value();

		if (towardX > 0) {
			if (towardY > 0) {
				return radiansToDegrees;
			} else {
				return CircleVariables.FOURTH_QUADRANT_END.value() - Math.abs(radiansToDegrees);
			}
		} else {
			if (towardY > 0) {
				return CircleVariables.SECOND_QUADRANT_END.value() - Math.abs(radiansToDegrees);
			} else {
				return CircleVariables.FOURTH_QUADRANT_END.value() - Math.abs(radiansToDegrees);
			}
		}
	}

}
