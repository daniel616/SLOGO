package exceptions.ParserExceptions;

public class LanguageNotFoundException extends ParserException {
	private static final long serialVersionUID = 1L;

    public LanguageNotFoundException(String message) {
        super("The language " + message + " was not found.");
    }
}
