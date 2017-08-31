package commands.DisplayCommands;

import java.util.List;
import java.util.Queue;

import exceptions.CommandException;
import javafx.scene.paint.Color;
import model.Model;

/**
 * SetPalette corresponds an index value with a specific color that is
 * determined by its (r,g,b) value.
 * 
 * @author Kyle
 *
 */
public class SetPalette extends DisplayCommands {

	public static final int VARIABLES = 4;
	public static final int NUMBER_VARIABLE_LISTS = 0;

	public SetPalette(List<String> arguments) throws CommandException {
		super(arguments);
		validNumberVariables(arguments.size(),VARIABLES);
	}

	@Override
	public double execute(Model model, List<Queue<String>> variableLists) throws CommandException {
		try {
			Color newColor = Color.web(Color.rgb(Integer.parseInt(stringArguments.get(1)),
					Integer.parseInt(stringArguments.get(2)), Integer.parseInt(stringArguments.get(3))).toString());
			model.getVisualModel().changeBackgroundPalette(Integer.parseInt(stringArguments.get(0)),
					newColor.toString());
		} catch (Exception e) {
			throw new CommandException(getCommandException("PaletteColorValues"));
		}
		
		return Double.parseDouble(stringArguments.get(0));
	}

}
