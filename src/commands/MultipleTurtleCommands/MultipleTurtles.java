package commands.MultipleTurtleCommands;

import java.util.List;
import commands.Commands;

/** MultipleTurtles is the parent class of all commands that affect multiple Turtles.
 * 
 * @author Kyle
 *
 */
public abstract class MultipleTurtles extends Commands {

	public MultipleTurtles(List<String> arguments) {
		super(arguments);
	}
}
