package exceptions;

public class RepeatedFunctionNameException extends Exception{
	private static final long serialVersionUID = 1L;

	public RepeatedFunctionNameException(String s){
		super(s);
	}
}
