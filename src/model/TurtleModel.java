package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import exceptions.RepeatedVariableNameException;
import exceptions.StateNotFoundException;
import exceptions.UnrecognizedIDException;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyListWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import turtleDetails.ReadableState;
import turtleDetails.Turtle;
import turtleDetails.TurtleTrail;

/**
 * Contains all the info that is specific to each running SLogo application. All
 * data structures are observable. Returns read only structures.
 * 
 * Needs to throw exceptions for unbound names.
 * 
 * @author Daniel
 *
 */
public class TurtleModel {
	private Set<Turtle> allTurtles;
	private Turtle mainTurtle;
	private ReadOnlyListWrapper<TurtleTrail> allTrails;
	private ObjectProperty<Integer> trailColor;
	private ObjectProperty<Integer> currentImage;
	private ObjectProperty<Integer> penSize;
	private SimpleBooleanProperty penDown;

	public TurtleModel() throws StateNotFoundException, UnrecognizedIDException {
		instantiate();
	}

	public Set<Turtle> getAllTurtles() {
		return allTurtles;
	}

	public void activateTurtles(Set<Integer> turtleNumbers) throws StateNotFoundException {
		for (Integer i : turtleNumbers) {
			boolean hasID = false;
			for (Turtle turtle : allTurtles) {
				if (turtle.getID() == i) {
					turtle.setActive(true);
					hasID = true;
				}
			}
			if (hasID == false) {
				Turtle generatedTurtle = new Turtle(i);
				allTurtles.add(generatedTurtle);
				generatedTurtle.setActive(true);
			}
		}
	}

	public void deactivateTurtles(Set<Integer> turtleNumbers) throws StateNotFoundException {
		for (Turtle t : allTurtles) {
			if (turtleNumbers.contains(t.getID())) {
				t.setActive(false);
			}
		}
	}

	public Set<Integer> getActiveIDs() throws StateNotFoundException {
		Set<Integer> indentification = new HashSet<>();
		for (Turtle turtle : allTurtles) {
			indentification.add(turtle.getFirstTurtleState().getID());
		}
		return indentification;
	}

	public Turtle getTurtle(int ID) throws UnrecognizedIDException, StateNotFoundException {
		for (Turtle turtle : allTurtles) {
			if (turtle.getFirstTurtleState().getID() == (ID)) {
				return turtle;
			}
		}
		throw new UnrecognizedIDException(ID);
	}

	public int numTurtles() {
		return allTurtles.size();
	}

	/**
	 * 
	 * @param names
	 *            Returns all turtles with names lying within the Set. Throws an
	 *            exception if the set contains a name which we don't actually
	 *            use.
	 * 
	 * @return
	 * @throws UnrecognizedIDException
	 * @throws StateNotFoundException
	 */
	public Set<Turtle> getTheseTurtles(Set<Integer> numbers) throws UnrecognizedIDException, StateNotFoundException {
		Set<Turtle> set = new HashSet<Turtle>();
		for (Integer ID : numbers) {
			boolean foundTurtle = false;
			for (Turtle turtle : allTurtles) {
				if (turtle.getFirstTurtleState().getID() == (ID)) {
					set.add(turtle);
					foundTurtle = true;
				}
			}
			if (foundTurtle == false) {
				throw new UnrecognizedIDException(ID);
			}
		}
		return set;
	}

	public void setMainTurtle(int x) throws StateNotFoundException, UnrecognizedIDException {
		for (Turtle t : allTurtles) {
			if (t.getFirstTurtleState().getID() == x) {
				mainTurtle = t;
				return;
			}
		}
		throw new UnrecognizedIDException(x);
	}

	public void addTurtle(int ID) throws StateNotFoundException, RepeatedVariableNameException {
		for (Integer i : turtleIDs()) {
			if (i == ID) {
				throw new RepeatedVariableNameException(i.toString());
			}
		}
		Turtle t = new Turtle(ID);
		allTurtles.add(t);
	}

	public void addTurtle() throws StateNotFoundException, RepeatedVariableNameException {
		Turtle t = new Turtle(allTurtles.size() + 1);
		allTurtles.add(t);
	}

	private Set<Integer> turtleIDs() throws StateNotFoundException {
		Set<Integer> set = new HashSet<>();
		for (Turtle turtle : allTurtles) {
			set.add(new Integer(turtle.getFirstTurtleState().getID()));
		}
		return set;
	}

	public Turtle getMainTurtle() {
		return mainTurtle;
	}

	public void resetTurtles() {
		allTurtles = new HashSet<Turtle>();
		Turtle t = new Turtle(1);
		allTurtles.add(t);
		mainTurtle = t;
	}

	public Set<ReadableState> currentTurtleStates() throws StateNotFoundException {
		Set<ReadableState> states = new HashSet<>();
		for (Turtle t : allTurtles) {
			states.add(t.getFirstTurtleState());
		}
		return states;
	}

	public void updateTurtles() throws StateNotFoundException {
		for (Turtle turtle : allTurtles) {
			if (turtle.numStates() > 1) {
				turtle.removeFirstState();
			}
		}
	}

	private void instantiate() throws StateNotFoundException, UnrecognizedIDException {
		allTrails = new ReadOnlyListWrapper<TurtleTrail>(FXCollections.observableList(new ArrayList<>()));
		allTurtles = new HashSet<Turtle>();

		trailColor = new SimpleObjectProperty<Integer>(1);
		penSize = new SimpleObjectProperty<Integer>(1);
		currentImage = new SimpleObjectProperty<Integer>(1);
		penDown = new SimpleBooleanProperty(true);

		Turtle t = new Turtle(1);
		allTurtles.add(t);
		setMainTurtle(1);
	}

	public ObservableList<TurtleTrail> getReadOnlyTrails() {
		return allTrails.getReadOnlyProperty();
	}

	public void removeTrail(TurtleTrail trail) {
		allTrails.remove(trail);
	}

	public void clearTrails() {
		allTrails.clear();
	}

	public void setNumberTurtles(int n)
			throws StateNotFoundException, RepeatedVariableNameException, UnrecognizedIDException {
		clearTrails();
		allTurtles.clear();
		if (n <= 0){
			n = 1;
		}
		for (int i = 1; i <= n; i++){
			addTurtle(i);
		}
	}

	public ObservableValue<Integer> trailColorIndex(){
		return trailColor;
	}

	public ObservableValue<Integer> penSizeIndex() {
		return penSize;
	}

	public ObservableValue<Integer> imageIndex() {
		return currentImage;
	}

	public ObservableValue<Boolean> penDown() {
		return penDown;
	}

	public void setImage(int index) {
		currentImage.set(index);
	}

	public void setPenSize(int size) {
		penSize.set(size);
	}

	public void setTrailColor(int index) {
		trailColor.set(index);
	}

	public void setShape(int index) {
		currentImage.setValue(index);
	}

	public void putPenDown(boolean value) {
		penDown.set(value);
	}

}