package turtleDetails;

/**
 * 
 * @author Daniel
 * Stores the parameters needed to generate a trail on the front end. Immutable.
 */
public class TurtleTrail {
	private final double x1;
	private final double x2;
	private final double y1;
	private final double y2;
	private final boolean penDown;
	private final String penColor;
	private final double penSize;

	public TurtleTrail(double x1, double y1, double x2, double y2, 
			boolean penDown, String penColor, double penSize) {
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		this.penDown = penDown;
		this.penColor = penColor;
		this.penSize=penSize;
	}

	public double getX1() {
		return x1;
	}

	public double getX2() {
		return x2;
	}

	public double getY1() {
		return y1;
	}

	public double getY2() {
		return y2;
	}

	public boolean penDown() {
		return penDown;
	}

	public String penColor() {
		return penColor;
	}
	
	public double penSize(){
		return penSize;
	}
}
