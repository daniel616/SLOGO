package utilities;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MessageFactory {
	private MessageFactory() {
		
	}

	public static void showMessage(String text) {
		Alert message=new Alert(AlertType.INFORMATION);
		message.setTitle("Info");
		message.setContentText(text);
		message.show();
	}
}