package com.amazon.springboot.helper;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Helper {
	
	public static Map<String, Integer> getMapObject(){
		Map<String, Integer>  alphaNumeric = new HashMap<String, Integer>();
		  alphaNumeric.put("A", 0);
		  alphaNumeric.put("B", 1);
		  alphaNumeric.put("C", 2);
		  alphaNumeric.put("D", 3);
		  alphaNumeric.put("E", 4);
		
		  return alphaNumeric;
	}
	
	public static LinkedList<Integer>[] getAdjcentList(){
		LinkedList<Integer> adj[] = new LinkedList[getMapObject().size()];
		for (int i = 0; i < getMapObject().size(); ++i)
			adj[i] = new LinkedList<>();
		
		return adj;
	}

}
