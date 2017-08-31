package commands.MathCommands;

import java.util.List;

import commands.Commands;
import exceptions.CommandException;

/** Parent class of all Math Commands.
 * 
 * @author Kyle
 *
 */
public abstract class MathCommands extends Commands {
	
	public MathCommands(List<String> args) throws CommandException {
		super(args);
		stringsToDoubles();
	}
}
