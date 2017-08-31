package xml;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.w3c.dom.Element;

import commands.AdvancedCommands.CommandDetails;
import model.Model;

public class BackWriter extends XMLWriter {
	public static final String COMMAND = "Command";
	public static final String PARAMETER = "Parameter";
	public static final String VARIABLE = "Variable";
	public static final String NAME = "Name";
	public static final String VALUE = "Value";
	public static final String BODY = "Body";

	private Model model;

	public BackWriter(Model m, String add) throws XMLException {
		super(add);
		model = m;
		writeElements();
	}

	@Override
	protected void writeElements() throws XMLException {
		writeRoot(model.getLanguageModel().readOnlyLanguage().getValue());
		writeBackEnd();
		saveFile();
	}

	private void writeBackEnd() {
		writeVariables();
		writeCommands();
	}

	private void writeVariables() {
		Map<String, String> variables = model.getProgrammingModel().readOnlyVariables();
		for (String s : variables.keySet())
			write(VARIABLE, Arrays.asList(NAME, VALUE), Arrays.asList(s, variables.get(s)));
	}

	private void writeCommands() {
		Map<String, CommandDetails> commands = model.getProgrammingModel().readOnlyCommands();
		for (String s : commands.keySet())
			write(COMMAND, Arrays.asList(NAME, PARAMETER, BODY), Arrays.asList(s, new PersonalQueue(commands.get(s).getVariables()).toString(), new PersonalQueue(commands.get(s).getCommand()).toString()));
	}
	private void write(String name, List<String> key, List<String> value){
		Element var = createElement(name);
		for(int i = 0; i<key.size(); i++)
			addElement(var, key.get(i), value.get(i));
		appendChild(var);
	}
}