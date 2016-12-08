package com.chat;

import java.util.HashMap;
import java.util.Map;

public class AI {
	public static final Map<String, String> map = new HashMap<String, String>();
	
	public static final String defaultMsg = "Sorry. Can't understand.";
	
	private static void initialize() {
		// Some sample conversations.
		// Add more if you need.
		map.put("hi", "Hello");
		map.put("hello", "Hello");
		map.put("hey", "Hello");
		
		map.put("How are you? ", "I am fine. How about you?");
		map.put("I am fine", "Good");
		
		map.put("Good bye", "Bye. Have a great day.");
		map.put("bye", "Bye. Have a great day.");
	}
	
	public static String getMsg(String input) {
		
		if(null == map || map.size() == 0) {
			initialize();
		}
		
		for(Map.Entry<String, String> entry : map.entrySet()) {
			// split byt spaces even though you have multiple spaces.
			String[] split = input.split("\\s+");
			for(String str : split) {
				
				// Ignore 1 character splits.
				if(str.length() > 2) {
					if(entry.getKey().toLowerCase().contains(str)) {
						return entry.getValue();
					}
				}
			}
		}
		
		return defaultMsg;
	}
}
