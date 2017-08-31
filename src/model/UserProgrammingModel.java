package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import commands.AdvancedCommands.CommandDetails;
import javafx.beans.property.ReadOnlyListWrapper;
import javafx.beans.property.ReadOnlyMapWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

/**
 * Contains all the info that is specific to each running SLogo application. All
 * data structures are observable. Returns read only structures.
 * 
 * Needs to throw exceptions for unfound names.
 * 
 * @author Daniel
 *
 */
public class UserProgrammingModel {
	private ReadOnlyListWrapper<String> inputHistory;
	private ReadOnlyMapWrapper<String, CommandDetails> userCommands;
	private ReadOnlyMapWrapper<String, String> userVariables;

	public UserProgrammingModel() {
		instantiate();
	}

	public 	ObservableList<String> readOnlyHistory() {
		return inputHistory.getReadOnlyProperty();
	}

	public ObservableMap<String, CommandDetails> readOnlyCommands() {
		return userCommands.getReadOnlyProperty();
	}

	public ObservableMap<String, String> readOnlyVariables() {
		return userVariables.getReadOnlyProperty();
	}

	public void addHistory(String history) {
		inputHistory.add(history);
	}

	public void addCommand(String name, CommandDetails details) {
		userCommands.put(name, details);
	}

	public void addVariable(String variable, String values) {
		userVariables.put(variable, values);
	}

	public void removeVariable(String variable) {
		userVariables.remove(variable);
	}

	public Map<String, String> copyVariablesMap() {
		Map<String, String> copyVariablesMap = new HashMap<>();
		userVariables.forEach((c, b) -> copyVariablesMap.put(c, b));
		return copyVariablesMap;
	}

	private void instantiate() {
		inputHistory = new ReadOnlyListWrapper<String>(FXCollections.observableList(new ArrayList<String>()));
		userCommands = new ReadOnlyMapWrapper<String,CommandDetails>(FXCollections.observableMap(new HashMap<String, CommandDetails>()));
		userVariables = new ReadOnlyMapWrapper<String,String>(FXCollections.observableMap(new HashMap<String, String>()));
	}
}
