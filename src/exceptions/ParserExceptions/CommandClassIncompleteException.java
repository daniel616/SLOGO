package exceptions.ParserExceptions;

public class CommandClassIncompleteException extends ParserException {
	private static final long serialVersionUID = 1L;

    public CommandClassIncompleteException(String field, String message) {
        super("The " + field + " in class " + message + " was not found.");
    }
}
