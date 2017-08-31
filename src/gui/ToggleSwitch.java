package gui;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ToggleSwitch extends HBox {
	
	private String op1;
	private String op2;
	private Label lab;
	private Button tb;
	private String initial;
	private Label title;
	private SimpleBooleanProperty switchedOn;
	private static final int SIZE = 10;
	private EventHandler<ActionEvent> event;
	
	public ToggleSwitch(String t, String a, String b, String i){
		op1 = a;
		op2 = b;
		initial = i;
		title = new Label(t + " ");
		switchedOn = new SimpleBooleanProperty(false);
		lab = new Label();
		tb = new Button();
		event = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				toggle();
			}
			
		};
		
		makeToggleButton();
		
		this.getChildren().addAll(title, lab, tb);
		this.setSpacing(SIZE);
	}
	
	private void makeToggleButton(){
		lab.setText("" + initial);
		tb.setOnAction(event);
		lab.setOnMousePressed(e -> {
			toggle();
		});
		
		lab.setAlignment(Pos.CENTER);
		
		switchedOn.addListener((a,b,c) -> {
			if (c) {
				lab.setText(initial.equals(op1) ? op2 : op1);
				lab.toFront();
			} else {
				lab.setText(initial);
				tb.toFront();
			}
		});
	}
	
	public void setAction(EventHandler<ActionEvent> e){
		tb.setOnAction(e);
	}
	
	public Boolean isOn(){
		return switchedOn.get();
	}
	
	public void toggle(){
		switchedOn.set(!switchedOn.get());
	}
	
	public String getText(){
		return lab.getText();
	}
}
