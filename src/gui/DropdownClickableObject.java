package gui;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

public class DropdownClickableObject extends ComboBox<String> {

	private ObservableList<String> observableList;
	
	public DropdownClickableObject(ObservableList<String> list){
		observableList = list;
	}
	
	public DropdownClickableObject(){
	
	}
	
	public void addCommand(String s){
		observableList.add(s);
	}
	
	public DropdownClickableObject returnCB(){
		return this;
	}
}
