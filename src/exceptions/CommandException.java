package exceptions;

/** CommandException creates exceptions related to errors in the Command
 * class and all of its subclasses.
 * 
 * @author Kyle
 *
 */
public class CommandException extends Exception {

	private static final long serialVersionUID = -3757411232269808439L;
	
	public CommandException(String message) {
		super(message);
	}

	public CommandException(Throwable cause) {
		super(cause);
	}

	public CommandException(String message, Throwable cause) {
		super(message, cause);
	}


}
