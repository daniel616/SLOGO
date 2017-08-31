package parser;

import java.util.LinkedList;
import java.util.Queue;

import exceptions.ParserExceptions.*;

public class ListGetter {
	private Queue<String> code;

	public ListGetter(Queue<String> info) {
		code = info;
	}

	public Queue<String> getLeft(){
		return code;
	}

	public Queue<String> getList(String start, String end) throws IncompleteListException {
		if(start.equals(code.peek())) {
			code.poll();
			Queue<String> prov = getListInfo(start, end);
			code.poll();
			return prov;
		} else{
			throw new IncompleteListException(start, end);
		}
	}

	private Queue<String> getListInfo(String start, String end) {
		Queue<String> prov = new LinkedList<>();
		int i = 0;
		while (true) {
			if (start.equals(code.peek()))
				i++;
			if (i == 0 && end.equals(code.peek()))
				break;
			if (end.equals(code.peek()))
				i--;
			prov.add(code.poll());
		}
		return prov;
	}
}