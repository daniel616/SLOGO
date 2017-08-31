package xml;
import java.io.File;

import model.Model;

public class FrontReader extends XMLReader{
	
	private Model model;
	
	public FrontReader(Model m, File f) throws Exception {
		super(f);
		model = m;
		readFile();
	}
	
	public void setFile(File f) throws Exception{
		changeFile(f);
		readFile();
	}
	private void readFile() throws Exception {
		model.getLanguageModel().setLanguage(readRoot());
		readFrontEnd();
	}
	private void readFrontEnd() throws Exception {
		model.getVisualModel().setBackgroundColor(Integer.parseInt(read(FrontWriter.BACKGROUND_COLOR)));
		model.getTurtleModel().setImage(Integer.parseInt(read(FrontWriter.IMAGE_NUM)));
		model.getTurtleModel().setNumberTurtles(Integer.parseInt(read(FrontWriter.NUM_TURTLES)));
		model.getTurtleModel().setTrailColor((Integer.parseInt(read(FrontWriter.TRAIL_COLOR))));
	}
	private String read(String name){
		return getByName(name).item(0).getTextContent();
	}
}