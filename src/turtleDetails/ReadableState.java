package turtleDetails;

public interface ReadableState{	
	public int getID();
	
	public double getX();

	public double getY();

	public double getHeading();

	public boolean penDown();

	public String getColor();

	public boolean turtleShowing();
	
	public double getPenSize();
	
	public boolean isActive();
	
	public int imageIndex();
	
	public String toString();
}
