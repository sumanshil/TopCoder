package com.topcoder.problems.round20;
//http://community.topcoder.com/stat?c=problem_statement&pm=134&rd=3020
public class Sentence {

	public String fix(String input){
		boolean isSentenceStart = true;
		boolean isWordEnd = false;
		StringBuffer result = new StringBuffer();
		for(int i = 0 ; i < input.length() ; i++){
			char c = input.charAt(i);
			if (isSentenceStart){
				if (c != ' '){
					result.append(Character.toUpperCase(c));
					isSentenceStart = false;
				}
			} else {
				if (c == ' '){
					isWordEnd = true;
				} else {
					if (isWordEnd && c =='.'){						
						result.append(c);
						isWordEnd = false;
					} else if (isWordEnd) {
						result.append(' ');
						result.append(c);
						isWordEnd = false;						
					} else if (!isWordEnd){
						result.append(c);
					}
				}
			}
		}
		if (result.charAt(result.length()-1) != '.'){
			result.append('.');
		}
		return result.toString();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//String str ="This is correct.";
		//String str =  	" the Germans and Romans play good Bridge ";
		//String str =  	" hi .";
		//String str =  	"a";
		//String str =  	"hE e BBB ddls ." 		;
		//String str =  	"This IS anOTHER CapIt";
		//String str =  	"mAny to one";
		//String str = " one two three";
		String str =  	" B a ";
		String result = new Sentence().fix(str);
		System.out.println(result);

	}

}
