package commands.DisplayCommands;

import java.util.List;
import commands.Commands;
import exceptions.CommandException;


/**
 * Display commands is the parent class of all commands that update to view
 * displayed to the user.
 * 
 * @author Kyle
 *
 */
public abstract class DisplayCommands extends Commands {

	public DisplayCommands(List<String> arguments) throws CommandException {
		super(arguments);
		stringsToDoubles();
	}

}
