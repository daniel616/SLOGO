package exceptions;

public class ColorException extends Exception{
	private static final long serialVersionUID = 1L;

	public ColorException (String s){
		super("Color "+s+"is uncrecognized. The back end uses hex values for red, green, blue and opacity to process color. "
				+ "'0xFF0000FF', for example, represents a fully opaque shade of red.");
	}

}
