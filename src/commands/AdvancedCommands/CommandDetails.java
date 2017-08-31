package commands.AdvancedCommands;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CommandDetails {
	private Queue<String> variables;
	private Queue<String> command;
	
	public CommandDetails(Queue<String> variables,Queue<String> command){
		this.variables=variables;
		this.command=command;
	}
	
	public Queue<String> getVariables(){
		return (variables);
	}
	
	public Queue<String> getCommand(){
		return (command);
	}

	public List<Queue<String>> getBoth() {
		List<Queue<String>> ans = new LinkedList<>();
		ans.add(getVariables());
		ans.add(getCommand());
		return ans;
	}
}
