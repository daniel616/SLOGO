package exceptions.ParserExceptions;

public class IncompleteListException extends ParserException {
	private static final long serialVersionUID = 1L;

	public IncompleteListException(String start, String end) {
        super("Missing " + start + " or " + end + " in list code.");
    }
}
