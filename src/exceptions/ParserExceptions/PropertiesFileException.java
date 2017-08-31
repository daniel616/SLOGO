package exceptions.ParserExceptions;

public class PropertiesFileException extends ParserException {
	private static final long serialVersionUID = 1L;

    public PropertiesFileException(String message) {
        super("The properties file name " + message + " was not found.");
    }
}
