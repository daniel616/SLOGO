package turtleDetails;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import exceptions.StateNotFoundException;
import javafx.beans.property.ReadOnlyListWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;

/**
 * @author Daniel
 * Stores an arraylist of states that functions as a queue. Uses the first and second states in the list to
 * create turtleTrails, and adds new states to the back of the list. You can only add to the back, but you can
 * look at both the front and the back. You can only remove from the back.
 * Throws exceptions if states aren't found.
 */
public class Turtle{
	private final int ID;
	private ReadOnlyListWrapper<TurtleState> myStates;
	private Set<TurtleTrail> myTrails;

	public Turtle(int ID, double x, double y, double heading, boolean penDown,
			String penColor, boolean turtleShowing, int penSize, boolean isActive, int image){
		this.ID=ID;
		myStates=new ReadOnlyListWrapper<TurtleState>(FXCollections.observableList(new ArrayList<TurtleState>()));
		myTrails=new HashSet<>();
		addTurtleState(x,y,heading,penDown,penColor,turtleShowing, penSize, isActive, image);
	}
	
	public Turtle(int ID){
		this(ID,0,0,0,true,Color.BLACK.toString(),true,3, true, 1);
	}
	
	//Returns a turtleTrail based on the first and second elements in TurtleStates.
	public TurtleTrail nextTrail() throws StateNotFoundException{
		if(myStates.size()<2){
			throw new StateNotFoundException(myStates.size());
		}
		
		TurtleState state0=myStates.get(0);
		TurtleState state1=myStates.get(1);
		TurtleTrail trail=new TurtleTrail(state0.getX(),state0.getY(),state1.getX(),
				state1.getY(),state1.penDown(),state1.getColor(),state0.getPenSize());
		return trail;
	}
	
	public void removeFirstState() throws StateNotFoundException{
		if(myStates.isEmpty()){
			throw new StateNotFoundException(0);
		}
		myStates.remove(0);
	}	
	
	//Adds a new element to the back of the list, corresponding to the latest change of state to be performed once the previous ones have been dealt with.
	//Makes the list look like a queue, because you can only add to the back.	
	public ReadableState getFinalTurtleState() throws StateNotFoundException {
		if(myStates.isEmpty()){
			throw new StateNotFoundException(0);
		}
		return myStates.get(myStates.size()-1);
	}
	
	public ReadableState getFirstTurtleState() throws StateNotFoundException {
		if(myStates.isEmpty()){
			throw new StateNotFoundException(0);
		}
		return myStates.get(0);
	}
	
	public Set<TurtleTrail> getTrails(){
		return myTrails;
	}
	
	public int numStates(){
		return myStates.size();
	}
	
	public int getID(){
		return ID;
	}
	
	public void setPosition(double x, double y) throws StateNotFoundException{
		TurtleState nextState=lastState().copy();
		nextState.setPosition(x,y);
		myStates.add(nextState);
	}
	
	public void setHeading(double heading) throws StateNotFoundException{
		TurtleState nextState=lastState().copy();
		nextState.setHeading(heading);
		myStates.add(nextState);
	}
	
	public void setPenDown(boolean penDown) throws StateNotFoundException{
		TurtleState nextState=lastState().copy();
		nextState.setPenDown(penDown);
		myStates.add(nextState);
	}
	
	public void setColor(String color) throws StateNotFoundException{
		TurtleState nextState=lastState().copy();
		nextState.setColor(color);
		myStates.add(nextState);
	}
	
	public void showTurtle(boolean show) throws StateNotFoundException{
		TurtleState nextState=lastState().copy();
		nextState.showTurtle(show);
		myStates.add(nextState);
	}
	
	public void setPenSize(double size) throws StateNotFoundException{
		TurtleState nextState=myStates.get(myStates.size()-1).copy();
		nextState.setPenSize(size);
		myStates.add(nextState);
	}
	
	public void setActive(boolean active) throws StateNotFoundException{
		TurtleState nextState=lastState().copy();
		nextState.setActive(active);
		myStates.add(nextState);
	}
	
	public void setImage(int index) throws StateNotFoundException{
		TurtleState nextState=lastState().copy();
		nextState.setImageIndex(index);
		myStates.add(nextState);
	}
	
	private TurtleState lastState() throws StateNotFoundException{
		if(myStates.isEmpty()){
			throw new StateNotFoundException(0);
		}
		
		return myStates.get(myStates.size()-1);
	}
	
	private void addTurtleState(double x, double y, double heading, boolean penDown, 
			String color, boolean turtleShowing, double penSize, boolean isActive, int imageIndex) {
		myStates.add(new TurtleState(ID, x,y,heading, penDown,color,turtleShowing, penSize,isActive, imageIndex));
	}
}