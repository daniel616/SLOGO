package gui;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * A simple class that creates a specific type of colored square for use in the
 * IndexInfo and Chooser classes
 * 
 * Created March 6th, 2017
 * 
 * @author Jake Conroy
 *
 */

public class ColorCell extends Rectangle{
	
	private static final int DIMENSION = 25;
		
	public ColorCell(Color color) {
		this.setHeight(DIMENSION);
		this.setWidth(DIMENSION);
		this.setFill(color);
	}

}
