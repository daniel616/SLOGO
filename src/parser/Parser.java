package parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import commands.Commands;
import commands.AdvancedCommands.DoUserCommand;
import exceptions.CommandException;
import exceptions.StateNotFoundException;
import exceptions.ParserExceptions.*;
import model.Model;
import exceptions.UnrecognizedIDException;

public class Parser {
	private static final List<String> COMMAND_PACKAGES = Arrays
			.asList(new String[] { "commands.TurtleCommands.", "commands.MathCommands.", "commands.BooleanCommands.",
					"commands.AdvancedCommands.", "commands.MultipleTurtleCommands.", "commands.DisplayCommands." });
	private static final String SYNTAX_LOCATION = "resources/languages/Syntax";
	private static final String WHITESPACE = "Whitespace";
	private static final String NEWLINE = "Newline";
	private static final String COMMAND = "Command";
	private static final String COMMENT = "#";
	private static final String LIST_START = "[";
	private static final String LIST_END = "]";
	private static final String GROUP_START = "(";
	private static final String GROUP_END = ")";
	private static final String VARIABLE = "Variable";

	private Model model;
	private Queue<String> code;
	private PropertiesHolder commands;
	private PropertiesHolder syntax;

	public Parser(Model pModel) throws ParserException {
		model = pModel;
		code = new LinkedList<String>();
		commands = new PropertiesHolder();
		syntax = new PropertiesHolder();
		syntax.addInfo(SYNTAX_LOCATION);
	}

	protected Queue<String> getCode() {
		return code;
	}

	public void setLanguage(String language) throws ParserException {
		commands.addInfo("resources/languages/" + language);
	}

	public void clearCode() {
		code = new LinkedList<>();
	}

	public double addCode(Queue<String> info, String lan)
			throws ParserException, StateNotFoundException, CommandException, UnrecognizedIDException {
		setLanguage(lan);
		code = info;
		return smartParse();
	}

	public double addCodeAsList(List<String> info, String lan)
			throws ParserException, StateNotFoundException, CommandException, UnrecognizedIDException {
		return addCode(new LinkedList<>(info), lan);
	}

	public void addCode(String info, String lan)
			throws ParserException, StateNotFoundException, CommandException, UnrecognizedIDException {
		setLanguage(lan);
		deleteComments(info);
	}

	private void deleteComments(String info) {
		for (String line : info.split(syntax.getValue(NEWLINE).toString()))
			for (String r : line.split(COMMENT)[0].split(syntax.getValue(WHITESPACE).toString()))
				if (r.length() != 0)
					code.add(r);
	}

	private double smartParse()
			throws StateNotFoundException, CommandException, ParserException, UnrecognizedIDException {
		double ans = 0;
		while (!code.isEmpty())
			ans = parse();
		return ans;
	}

	private double parse() throws ParserException, StateNotFoundException, CommandException, UnrecognizedIDException {
		try {
			return groupHandle();
		} catch (IncompleteListException e) {
			String comName = code.poll();
			List<String> list = getParameters(comName);
			return executeCommand(createCommand(list, comName), comName);
		}
	}

	private int getPackageLocation(String name, int val) throws CommandNotFoundException {
		if (val >= COMMAND_PACKAGES.size())
			throw new CommandNotFoundException(name);
		try {
			Class.forName(COMMAND_PACKAGES.get(val) + name);
			return val;
		} catch (ClassNotFoundException e) {
			return getPackageLocation(name, val + 1);
		}
	}

	private String getCommandPackage(String name) throws CommandNotFoundException {
		return COMMAND_PACKAGES.get(getPackageLocation(name, 0));
	}

	private String getCommandLocation(String name) throws CommandNotFoundException, ClassNotFoundException {
		return getCommandPackage(name) + name;
	}

	private Class<?> getClazz(String name) throws CommandNotFoundException, ClassNotFoundException {
		return Class.forName(getCommandLocation(getCommandClass(name)));
	}

	protected String getCommandClass(String name) throws CommandNotFoundException {
		try {
			return commands.getKey(name);
		} catch (ParserException e) {
			return "";
		}
	}

	private int getAmount(String name, String cons)
			throws CommandNotFoundException, ReflectionException, ParserException {
		try {
			return (int) getClazz(name).getDeclaredField(cons).get(null);
		} catch (CommandNotFoundException | ClassNotFoundException e1) {
			try {
				return model.getProgrammingModel().readOnlyCommands().get(name).getVariables().size();
			} catch (NullPointerException e2) {
				throw new CommandNotFoundException(name);
			}
		} catch (SecurityException | IllegalAccessException | IllegalArgumentException e) {
			throw new ReflectionException();
		} catch (NoSuchFieldException e) {
			throw new CommandClassIncompleteException(cons + " constant field", name);
		}
	}

	private List<String> getParameters(String name)
			throws StateNotFoundException, CommandException, ParserException, UnrecognizedIDException {
		List<String> parameters = new LinkedList<>();
		while (parameters.size() != getAmount(name, "VARIABLES"))
			if (GROUP_START.equals(code.peek()))
				parameters.add(groupHandle() + "");
			else if (COMMAND.equals(syntax.getKey(code.peek())) && !getCommandClass(name).equals("MakeUserInstruction"))
				parameters.add(parse() + "");
			else if (VARIABLE.equals(syntax.getKey(code.peek())) && getCommandClass(name).equals("MakeVariable")
					&& parameters.size() == 0)
				parameters.add(code.poll());
			else if (model.getProgrammingModel().readOnlyVariables().containsKey(code.peek()))
				parameters.add(model.getProgrammingModel().readOnlyVariables().get(code.poll()));
			else
				parameters.add(code.poll());
		return parameters;
	}

	private Commands createCommand(List<String> parameters, String name) throws ParserException, CommandException {
		try {
			return (Commands) getClazz(name).getDeclaredConstructor(List.class).newInstance(parameters);
		} catch (ClassNotFoundException e) {
			throw new CommandNotFoundException(name);
		} catch (Exception e) {
			if (model.getProgrammingModel().readOnlyCommands().get(name) != null)
				return new DoUserCommand(parameters);
			throw new CommandClassIncompleteException("Constructor method", name);
		}
	}

	private double executeCommand(Commands c, String n) throws StateNotFoundException, CommandException, ParserException, UnrecognizedIDException {

		return c.execute(model, retrieveList(c, n));
	}

	private List<Queue<String>> retrieveList(Commands c, String n) throws ParserException {
		if (c instanceof DoUserCommand)
			return model.getProgrammingModel().readOnlyCommands().get(n).getBoth();
		else
			return getLists(n);
	}

	private List<Queue<String>> getLists(String name) throws ParserException {
		List<Queue<String>> lists = new ArrayList<>();
		while (lists.size() != getAmount(name, "NUMBER_VARIABLE_LISTS")) {
			if (LIST_START.equals(code.peek())) {
				ListGetter lg = new ListGetter(code);
				lists.add(lg.getList(LIST_START, LIST_END));
				code = lg.getLeft();
			}
		}
		return lists;
	}

	private double groupHandle()
			throws ParserException, StateNotFoundException, CommandException, UnrecognizedIDException {
		if (GROUP_START.equals(code.peek())) {
			ListGetter lg = new ListGetter(code);
			Queue<String> gIn = lg.getList(GROUP_START, GROUP_END);
			code = lg.getLeft();
			return new GroupHandler(model, gIn, getAmount(gIn.peek(), "VARIABLES"),
					getPackageLocation(getCommandClass(gIn.peek()), 0) == 1
							|| getPackageLocation(getCommandClass(gIn.peek()), 0) == 2).handle();
		}
		throw new IncompleteListException(GROUP_START, GROUP_END);
	}
}