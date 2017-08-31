package model;

import java.util.HashMap;
import java.util.Map;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
/**
 *
 * Allows
 * 
 * @author Daniel
 *
 */
public class GlobalVisualOptionsModel {
	private ObservableMap<Integer, String> colorPalette;
	private ObservableMap<Integer, String> images;
	private ObjectProperty<Integer> backgroundColor;

	public GlobalVisualOptionsModel(){
		instantiate();
	}
	
	public ObservableValue<Integer> backgroundColor() {
		return backgroundColor;
	}
	
	public String colorAtIndex(int index){
		return colorPalette.get(index);
	}
	
	public ObservableValue<Integer> backgroundColorIndex() {
		return backgroundColor;
	}
	

	public ObservableMap<Integer, String> getPalette() {
		return colorPalette;
	}

	public void changeBackgroundPalette(int index, String color) {
		colorPalette.put(index, color);
	}
	public void setBackgroundColor(int index) {
		backgroundColor.setValue(index);
	}
	public ObservableMap<Integer, String> getImages() {
		return images;
	}
	
	public void setImages(ObservableMap<Integer, String> map) {
		images.clear();
		map.forEach((key, value) -> {
			images.put(key, value);
		});
	}

	private void instantiate(){
		Map<Integer, String> colorPaletteMap = new HashMap<Integer, String>();
		colorPaletteMap.put(1, "#FFFFFF"); //white
		colorPaletteMap.put(2, "#0000FF"); //blue
		colorPaletteMap.put(3, "#000000"); //black
		colorPaletteMap.put(4, "#008000"); //green
		colorPalette = FXCollections.observableMap(colorPaletteMap);

		Map<Integer, String> imagesMap = new HashMap<Integer, String>();
		imagesMap.put(1, "cute_GIF.gif"); //default
		imagesMap.put(2, "duvall.gif"); //Professor Duvall
		imagesMap.put(3, "squirtCROPPED.gif"); //Squirt from Finding Nemo
		images = FXCollections.observableMap(imagesMap);
		backgroundColor = new SimpleObjectProperty<Integer>(1);
	}
}