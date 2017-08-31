package turtleDetails;

/**
 * 
 * @author Daniel
 * Stores variables representing the state of the turtle at some point in time.
 * Turtles store a "queue" of these states to transition between. Immutable.
 * Only Turtles can instantiate TurtleStates.
 */
public class TurtleState implements ReadableState{
	private int ID;
	private double x;
	private double y;
	private double heading;
	private boolean penDown;
	private String color;
	private boolean turtleShowing;
	private double penSize;
	private boolean isActive;
	private int imageIndex;
	
	public TurtleState(int ID, double x, double y, double heading, boolean penDown, 
			String color, boolean turtleShowing, double penSize, boolean isActive, int imageIndex) {
		this.ID=ID;
		this.x = x;
		this.y = y;
		this.heading = heading;
		this.penDown = penDown;
		this.color = color;
		this.turtleShowing = turtleShowing;
		this.penSize=penSize;
		this.isActive=isActive;
		this.imageIndex=imageIndex;
	}
	
	public TurtleState copy(){
		return new TurtleState(ID,x,y,heading,penDown,color,turtleShowing,penSize,isActive,imageIndex);
	}
	
	public int getID(){
		return ID;
	}
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getHeading() {
		return heading;
	}

	public boolean penDown() {
		return penDown;
	}

	public String getColor() {
		return color;
	}

	public boolean turtleShowing() {
		return turtleShowing;
	}	
	
	public double getPenSize(){
		return penSize;
	}
	
	public boolean isActive(){
		return isActive;
	}
	
	public int imageIndex(){
		return imageIndex;
	}
	
	public String toString(){
		String s ="x:"+x+",y:"+y+",heading:"+heading+",pendown:"+penDown+",Color:"+color+",TurtleShowing:"+turtleShowing;
		return s;
	}
	
	public void setPosition(double x, double y){
		this.x=x;
		this.y=y;
	}
	
	public void setHeading(double heading){
		this.heading=heading;
	}
	
	public void setPenDown(boolean penDown){
		this.penDown=penDown;
	}
	
	public void setColor(String color){
		this.color=color;
	}
	
	public void showTurtle(boolean show){
		this.turtleShowing=show;
	}
	
	public void setPenSize(double size){
		this.penSize=size;
	}
	
	public void setActive(boolean active){
		this.isActive=active;
	}
	
	public void setImageIndex(int index){
		this.imageIndex=index;
	}
}
