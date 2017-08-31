package xml;

import java.util.Queue;

public class PersonalQueue {
	private Queue<String> queue;
	
	public PersonalQueue(Queue<String> q){
		queue = q; 
	}
	
	@Override
	public String toString(){
		String ans = "";
		for(String s: queue){
			ans += (s+" ");
		}
		return ans.substring(0, ans.length()-1);
	}
}
