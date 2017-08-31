package xml;

import model.Model;

public class FrontWriter extends XMLWriter {
	public static final String IMAGES_LIST = "ImagesList";
	public static final String IMAGE_NUM = "ImageNumber";
	public static final String BACKGROUND_COLOR = "BackgroundColor";
	public static final String NUM_TURTLES = "NumberOfTurtles";
	public static final String TRAIL_COLOR = "TrailColor";

	private Model model;

	public FrontWriter(Model m, String add) throws XMLException {
		super(add);
		model = m;
		writeElements();
	}

	@Override
	protected void writeElements() throws XMLException {
		writeRoot(model.getLanguageModel().readOnlyLanguage().getValue());
		writeFrontEnd();
		saveFile();
	}

	private void writeFrontEnd() {
		writeElement(BACKGROUND_COLOR, model.getVisualModel().backgroundColorIndex().getValue().toString(), "1");
		//writeElement(IMAGES_LIST, model.getVisualModel().getImages().toString(), "");
		writeElement(IMAGE_NUM, model.getTurtleModel().imageIndex().getValue().toString(), "");
		writeElement(NUM_TURTLES, model.getTurtleModel().getAllTurtles().size()+"", "1");
		writeElement(TRAIL_COLOR, model.getTurtleModel().trailColorIndex().getValue().toString(), "1");
	}

	private void writeElement(String name, String value, String def) {
		if(value == null)
			value = def;
		addElement(name, value);
	}
}
