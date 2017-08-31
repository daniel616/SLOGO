package parser;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import exceptions.CommandException;
import exceptions.StateNotFoundException;
import exceptions.UnrecognizedIDException;
import exceptions.ParserExceptions.GroupException;
import exceptions.ParserExceptions.ParserException;
import model.Model;

public class GroupHandler{
	private Model model;
	private Queue<String> code;
	private int amount;
	private boolean dependance;
	private String name;

	public GroupHandler(Model m, Queue<String> groupInfo, int varNeeded, boolean pDependance) {
		model = m;
		amount = varNeeded; 
		name = groupInfo.poll();
		code = groupInfo;
		dependance = pDependance;
	}

	public double handle() throws ParserException, StateNotFoundException, CommandException, UnrecognizedIDException {
		if(amount == 1)
			return oneHandler();
		else if(dependance)
			return stackHandle();
		throw new GroupException(name);
	}

	private double stackHandle() throws ParserException, StateNotFoundException, CommandException, UnrecognizedIDException {
		Stack<String> st = getStack(code);
		Stack<String> stack = getStack(st);
		while(stack.size()!=1){
			Parser p = new Parser(model);
			Queue<String> info = new LinkedList<>();
			info.add(name);
			info.add(stack.pop());
			info.add(stack.pop());
			stack.add(""+p.addCode(info, model.getLanguageModel().readOnlyLanguage().getValue()));
		}
		return Double.parseDouble(stack.pop());
	}

	private Stack<String> getStack(Collection<String> c) {
		Stack<String> st = new Stack<String>();
		st.addAll(c);
		return st;
	}

	private double oneHandler() throws ParserException, StateNotFoundException, CommandException, UnrecognizedIDException {
		Parser p = new Parser(model);
		Queue<String> info = new LinkedList<>();
		info.addAll(Arrays.asList(new String[] {name, "(", "sum"}));
		info.addAll(code);
		info.add(")");
		return p.addCode(info, model.getLanguageModel().readOnlyLanguage().getValue());
	}
}