package com.geeksforgeeks.strings;

import java.util.Iterator;

/**
 * Created by sshil on 12/12/2015.
 */
public class RunLengthEncodingIterator implements Iterator<String> {

	private String encodedString;
	private int currentCount = 0;
	private int currentIndex = 0;
	private String currentChar = null;

	public RunLengthEncodingIterator(String encodedString){
		this.encodedString = encodedString;
		this.currentCount = Integer.parseInt(encodedString.charAt(currentIndex)+"");
		this.currentChar = encodedString.charAt(currentIndex+1)+"";
	}
	@Override
	public boolean hasNext() {
		return this.currentChar != null;
	}

	@Override
	public String next() {
		String retVal = currentChar;
		this.currentChar = calculateNextChar();
		this.currentCount--;
		return retVal;
	}

	private String calculateNextChar() {
		if (this.currentCount > 0) {
			return this.currentChar;
		} else {
			if (this.currentIndex + 3 < this.encodedString.length()){
				this.currentCount = Integer.parseInt(this.encodedString.charAt(this.currentIndex+2)+"");
				String nextChar = this.encodedString.charAt(this.currentIndex+3)+"";
				this.currentIndex = this.currentIndex+2;
				return nextChar;
			}
		}
		return null;
	}

	public static void main(String[] args) {
		Iterator<String> iterator = new RunLengthEncodingIterator("5d3c1e");
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
	}
}
