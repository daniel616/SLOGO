package commands.BooleanCommands;

import java.util.List;

import commands.Commands;
import exceptions.CommandException;

/** Parent class of all boolean commands.
 * 
 * @author Kyle
 *
 */
public abstract class BooleanCommands extends Commands {
	
	public BooleanCommands(List<String> args) throws CommandException {
		super(args);
		stringsToDoubles();
	}

}
