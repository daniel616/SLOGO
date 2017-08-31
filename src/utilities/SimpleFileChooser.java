package utilities;

import java.io.File;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class SimpleFileChooser {
	private SimpleFileChooser() {
		
	}
	
	public static File chooseFile(String fileName) {
		FileChooser fc = new FileChooser();
		fc.setInitialDirectory(new File(fileName));
		return fc.showOpenDialog(new Stage());
	}
}
