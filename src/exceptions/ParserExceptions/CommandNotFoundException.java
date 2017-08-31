package exceptions.ParserExceptions;

public class CommandNotFoundException extends ParserException {
	private static final long serialVersionUID = 1L;

    public CommandNotFoundException(String message) {
        super("The command " + message + " was not found.");
    }
}
