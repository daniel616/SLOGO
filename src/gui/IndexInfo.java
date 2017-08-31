package gui;

import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import javafx.scene.layout.HBox;

/**
 * This class creates an HBox containing both an index and either an image that
 * could be chosen as the turtle or a color square that can set the background
 * and turtle trail color.
 * 
 * Created March 6th, 2017
 * 
 * @author Jake Conroy
 *
 */

public class IndexInfo extends HBox {

	private Integer index;
	private String info;
	private ObservableMap<Integer, String> myMap;
	
	public IndexInfo(Integer idx, String inf, ObservableMap<Integer, String> map) {
		index = idx;
		info = inf;
		myMap = map;
		myMap.addListener(new MapChangeListener<Integer, String>() {
            @SuppressWarnings("rawtypes")
			@Override
            public void onChanged(MapChangeListener.Change change) {
            	info = myMap.get(index);
            }
        });
	}
	
	public Integer getIndex() {
		return index;
	}
	
	public String getInfo() {
		return info;
	}
	
	public ObservableMap<Integer, String> getMap() {
		return myMap;
	}
}
