package exceptions;

public class UnrecognizedIDException extends Exception{
	private static final long serialVersionUID = 1L;

	 public UnrecognizedIDException(int id) {
	        super("The turtle id " + id + " was not recognized.");
	 }
}
