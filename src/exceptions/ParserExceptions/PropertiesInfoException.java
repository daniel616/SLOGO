package exceptions.ParserExceptions;

public class PropertiesInfoException extends ParserException {
	private static final long serialVersionUID = 1L;

    public PropertiesInfoException(String message) {
        super("The properties information by key " + message + " was not found.");
    }
}
