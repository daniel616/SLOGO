package exceptions;

public class RepeatedVariableNameException extends Exception{
	private static final long serialVersionUID = 1L;

	public RepeatedVariableNameException(String s){
		super("Variable name "+ s+ " is already used");
	}

}
