package gui;

import java.util.ResourceBundle;

import controls.Controller;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.util.Callback;

/**
 * This class creates a special Combobox that hold either indices matched with
 * images or with color squares so that the user can select these in the GUI
 * to change colors or images used in the program.
 * 
 * Created March 6th, 2017
 * 
 * @author Jake Conroy
 *
 */

public class Chooser extends ComboBox<IndexInfo> {
	
	private static final int IMAGE_SIZE = 25;
	private String info;
	private String myTitle;
	private ObservableMap<Integer, String> myObservedMap;
	private boolean myType; //true is color chooser and false is image chooser
	
	public Chooser(Controller ct, String title, boolean type) {
		myType = type;
		myTitle = title;
		this.setPromptText(ct.getResourceBundle().getString(myTitle));
		if (myType) {
			myObservedMap = ct.colorPalette();
		} else {
			myObservedMap = ct.imageOptions();
		}
		make();
	}
	
	public void setLanguage(ResourceBundle resource){
		setPromptText(resource.getString(myTitle));
	}
	
	public String getInfo() {
		return this.getValue().getInfo();
	}
	
	private void make() {
		for (Integer i : myObservedMap.keySet()) {
			this.getItems().add(new IndexInfo(i, myObservedMap.get(i), myObservedMap));
		}
		this.setCellFactory(new Callback<ListView<IndexInfo>, ListCell<IndexInfo>>() {
			@Override public ListCell<IndexInfo> call(ListView<IndexInfo> p) {
		         return new ListCell<IndexInfo>() {
		        	 Label index = new Label();
		        	 Label color = new Label();
		        	 private final HBox cell;
		             { 
		            	 setContentDisplay(ContentDisplay.GRAPHIC_ONLY); 
		            	 cell = new HBox();
		            	 cell.getChildren().addAll(index, color);
		             }
		             @Override protected void updateItem(IndexInfo item, boolean empty) {
		                 super.updateItem(item, empty);
		                 if (item == null || empty) {
		                     setGraphic(null);
		                 } else {
		                	 index.setText(item.getIndex()+"");
		                	 if (myType) {
		                		 info = item.getInfo();
		                		 item.getMap().addListener(new MapChangeListener<Integer, String>() {
		                	            @SuppressWarnings("rawtypes")
		                				@Override
		                	            public void onChanged(MapChangeListener.Change change) {
		                	            	info = item.getInfo();
		                	            	color.setGraphic(new ColorCell(Color.web(info)));
		                	            }
		                	        });
			                	 color.setGraphic(new ColorCell(Color.web(info)));
		                	 } else {
			                	 ImageView turtle = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(
			                			 item.getInfo()),IMAGE_SIZE, IMAGE_SIZE, true, true));
			                	 color.setGraphic(turtle);
		                	 }
		                	 setGraphic(cell);
		                 }
		            }
		       };
			 }
		});
	}	
}