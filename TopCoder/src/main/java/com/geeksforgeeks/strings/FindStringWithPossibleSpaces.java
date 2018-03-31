package com.geeksforgeeks.strings;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sshil on 1/27/2016.
 */
public class FindStringWithPossibleSpaces {

	private List<String> list = new ArrayList<>();
	public void find(String str){
		if (str == null){
			StringBuffer stringBuffer = new StringBuffer();
			for (String str1 : list){
				stringBuffer.append(str1);
			}
			System.out.println(stringBuffer.toString());
			return;
		}
		if ( str.length() == 1 ) {
			StringBuffer stringBuffer = new StringBuffer();
			for (String str1 : list){
				stringBuffer.append(str1);
			}
			stringBuffer.append(str);
			System.out.println(stringBuffer.toString());
			return;
		}

		for (int i = 0 ; i < str.length() ; i++) {
			String subStr1 = str.substring(0, i+1);
			list.add(subStr1+" ");
			String subStr2 = null;
			if (i+1 < str.length()){
				subStr2 = str.substring(i+1);
			}
			find(subStr2);
			list.remove(list.size()-1);
		}

	}
	public static void main(String[] args) {
		new FindStringWithPossibleSpaces().find("abcd");
	}
}
