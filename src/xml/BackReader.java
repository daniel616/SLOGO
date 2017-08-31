package xml;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import commands.AdvancedCommands.CommandDetails;
import exceptions.ParserExceptions.LanguageNotFoundException;
import model.Model;

public class BackReader extends XMLReader{
	private Model model;

	public BackReader(Model m, File f) throws XMLException, LanguageNotFoundException {
		super(f);
		model = m;
		readFile();
	}
	
	public void setFile(File f) throws LanguageNotFoundException{
		changeFile(f);
		readFile();
	}

	private void readFile() throws LanguageNotFoundException {
		readBackEnd();
	}

	private void readBackEnd() throws LanguageNotFoundException {
		readVariables();
		readCommands();
	}

	private void readCommands() throws LanguageNotFoundException {
		model.getLanguageModel().setLanguage(readRoot());
		read(getByName(BackWriter.COMMAND), false);
	}

	private void readVariables() throws LanguageNotFoundException {
		model.getLanguageModel().setLanguage(readRoot());
		read(getByName(BackWriter.VARIABLE), true);
	}

	private void read(NodeList list, boolean type){
		for(int temp = 0; temp < list.getLength(); temp++) {
			if (list.item(temp).getNodeType() == Node.ELEMENT_NODE) {
				setRoot((Element) list.item(temp));
				if(type) addVariable();
				else addCommand();
			}
		}
	}

	private void addVariable() {
		model.getProgrammingModel().addVariable(getTextValue(BackWriter.NAME), getTextValue(BackWriter.VALUE));
	}
	
	private void addCommand() {
		model.getProgrammingModel().addCommand(getTextValue(BackWriter.NAME), new CommandDetails(getQueue(BackWriter.PARAMETER), getQueue(BackWriter.BODY)));		
	}

	private Queue<String> getQueue(String tagName) {
		Queue<String> com = new LinkedList<String>();
		com.addAll(Arrays.asList(getTextValue(tagName).split(" ")));
		return com;
	}
}