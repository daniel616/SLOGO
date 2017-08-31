package commands;

/** CircleVariables provides commonly used variables related to circles.
 * 
 * @author Kyle
 *
 */
public enum CircleVariables {
	CIRCLE_DEGREES(360), 
	HALF_CIRCLE_DEGREES(180), 
	HALF_CIRCLE_RADIANS(Math.PI), 
	DEGREES_TO_RADIANS(HALF_CIRCLE_RADIANS.value() / HALF_CIRCLE_DEGREES.value()), 
	RADIANS_TO_DEGREES(HALF_CIRCLE_DEGREES.value() / HALF_CIRCLE_RADIANS.value()),
	FIRST_QUADRANT_END(90), 
	SECOND_QUADRANT_END(180), 
	THIRD_QUADRANT_END(270), 
	FOURTH_QUADRANT_END(360);

	private final double value;

	CircleVariables(double value) {
		this.value = value;
	}

	public double value() {
		return value;
	}
}
