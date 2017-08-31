package parser;


import static java.util.stream.Collectors.toList;

import java.util.LinkedList;
//import java.util.Map;
import java.util.Queue;
//import java.util.Set;

import exceptions.CommandException;
import exceptions.StateNotFoundException;
import exceptions.UnrecognizedIDException;
import exceptions.ParserExceptions.ParserException;
import model.Model;

public class ManyParser extends Parser{

	private Model model;

	public ManyParser(Model pModel) throws ParserException {
		super(pModel);
		model = pModel;
	}

	public void parseInfo(String info) throws ParserException, StateNotFoundException, CommandException, UnrecognizedIDException{
		addCode(info, model.getLanguageModel().readOnlyLanguage().getValue());
		turtleManager(getCode());
	}

	private void turtleManager(Queue<String> code) throws ParserException, StateNotFoundException, CommandException, UnrecognizedIDException {
		if(!code.isEmpty()){
			Queue<String> prov = new LinkedList<>();
			while(code.peek()!=null && !getCommandClass(code.peek()).equals("Tell"))
				prov.add(code.poll());
			executeCode(prov);
			if(code.peek()!=null)
				executeTell(code);
		}
	}

	private void executeCode(Queue<String> prov) throws ParserException, StateNotFoundException, CommandException, UnrecognizedIDException {
		Parser p = new Parser(model);
		//int size = model.getTurtleModel().getActiveIDs().size();
		for(int i: model.getTurtleModel().getActiveIDs()){
			//size--;
			model.getTurtleModel().setMainTurtle(i);
			//Map<String, String> variables = model.getProgrammingModel().readOnlyVariables();
			runTurtle(prov, p); 
			//cleanModel(variables, size);
		}
	}

	/*private void cleanModel(Map<String, String> vars, int size) {
		if(size>0){
			Set<String> oldVars = model.getProgrammingModel().readOnlyVariables().keySet();
			oldVars.forEach(c -> model.getProgrammingModel().removeVariable(c));
			vars.forEach((c, b) -> model.getProgrammingModel().addVariable(c, b));
		}
	}*/

	private void runTurtle(Queue<String> prov, Parser p) throws ParserException, StateNotFoundException, CommandException, UnrecognizedIDException {
		p.addCodeAsList(prov.stream().map(d -> d = new String(d)).collect(toList()), model.getLanguageModel().readOnlyLanguage().getValue());
	}

	private void executeTell(Queue<String> code) throws ParserException, StateNotFoundException, CommandException, UnrecognizedIDException {
		Queue<String> prov = new LinkedList<>();
		while(code.peek()!=null && !code.peek().equals("]"))
			prov.add(code.poll());
		prov.add(code.poll());
		new Parser(model).addCode(prov, model.getLanguageModel().readOnlyLanguage().getValue());
		turtleManager(code);
	}
}
