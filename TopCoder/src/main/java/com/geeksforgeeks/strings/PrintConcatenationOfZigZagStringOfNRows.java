package com.geeksforgeeks.strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sshil on 1/9/2016.
 */
//http://www.geeksforgeeks.org/print-concatenation-of-zig-zag-string-form-in-n-rows/
public class PrintConcatenationOfZigZagStringOfNRows {

	public void print(String arr, int maxLevel) {
		Map<Integer, List<Character>> map = new HashMap<>();
		for ( int i = 0 ; i < maxLevel ; i++) {
			map.put(i, new ArrayList<>());
		}
		int levelCount = 0;
		int direction = 1;
		for ( int i = 0 ; i < arr.length() ; i++) {
			List<Character> list = map.get(levelCount);
			list.add(arr.charAt(i));
			levelCount = levelCount+direction;
			if (levelCount == maxLevel){
				direction = -1;
				levelCount = (levelCount+direction)+direction;

			} else if (levelCount == -1) {
				direction = 1;
				levelCount = (levelCount+direction)+direction;
			}

		}

		for ( int i = 0 ; i < maxLevel ; i++){
			List<Character> list = map.get(i);
			list.stream().forEach(e -> System.out.print(e));
		}
		System.out.println();
	}
	public static void main(String[] args) {
		String str = "GEEKSFORGEEKS";
		int n = 3;
		new PrintConcatenationOfZigZagStringOfNRows().print(str, 3);
	}
}
