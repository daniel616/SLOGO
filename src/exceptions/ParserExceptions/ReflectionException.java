package exceptions.ParserExceptions;

public class ReflectionException extends ParserException {
	private static final long serialVersionUID = 1L;

    public ReflectionException() {
        super("Error using reflection.");
    }
}
