package exceptions.ParserExceptions;

public class GroupException extends ParserException {
	private static final long serialVersionUID = 1L;

    public GroupException(String message) {
        super("The command " + message + " is not defined in groups.");
    }
}
