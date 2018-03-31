package com.topcoder.problems.round169;

/**
 * Created by sshil on 12/2/2015.
 */
//https://community.topcoder.com/stat?c=problem_statement&pm=1876&rd=4650
public class Twain {

    interface MutateWord {
        String mutate(String sentence) throws Exception;
    }

    class Year1Mutation extends RepetitetiveMutation  {


        @Override
        protected String mutateWord(String word) throws Exception {
			String originalWord = word;
            if (word.startsWith("x")) {
                if (word.length() == 1){
                    return "z";
                } else {
                    word = "z" + word.substring(1);
                }
            }
            word =  word.replaceAll("x", "ks");
			if (originalWord.equals(word)){
				return  null;
			} else {
				return  word;
			}
        }

        @Override
        protected boolean isMutable(String word) {
            return word.startsWith("x") || word.indexOf('x') > 0;
        }
    }

    class Year2Mutation extends OneTimeMutation  {

        @Override
        public String mutate(String sentence) {
            return sentence.replaceAll("y", "i");
        }
    }

    //If a "c" is directly followed by an "e" or "i", change the "c" to an "s".
    class Year3Mutation extends RepetitetiveMutation  {

		//- If a "c" is directly followed by an "e" or "i", change the "c" to an "s".
        @Override
        protected String mutateWord(String word) {
			boolean isMutated = false;
			StringBuffer stringBuffer = new StringBuffer();
			for ( int i =0 ; i < word.length() ; i++){
				if (word.charAt(i) == 'c' && (i+1 < word.length() && (word.charAt(i+1) == 'e'|| word.charAt(i+1)=='i'))){
					isMutated = true;
					stringBuffer.append('s');
				} else {
					stringBuffer.append(word.charAt(i));
				}
			}
			return isMutated ? stringBuffer.toString() : null;
        }


        @Override
        protected boolean isMutable(String word) {
            int indexOfC = getIndex(word, "c");
            int indexOfE = getIndex(word, "e");
            int indexOfI = getIndex(word, "i");
            if ( indexOfC != -1 ) {
                if (indexOfE != -1 && indexOfC+1 == indexOfE ) {
                    return true;
                }

                if (indexOfI != -1 && indexOfC+1 == indexOfI){
                    return  true;
                }
            }
            return false;
        }

    }

    class Year6Mutation extends RepetitetiveMutation {

        // If a word starts with "kn" change the "kn" to an "n".
        @Override
        protected String mutateWord(String word) throws Exception {
			boolean isMutated = false;
			StringBuffer stringBuffer = new StringBuffer();
			for ( int i = 0 ; i < word.length() ; i++) {
				if (i == 0 && word.charAt(i) == 'k' && (i+1 < word.length() && word.charAt(i+1) == 'n')){
					isMutated = true;
					stringBuffer.append('n');
					i++;
				} else {
					stringBuffer.append(word.charAt(i));
				}
			}
			return isMutated ? stringBuffer.toString() : null;
        }

        @Override
        protected boolean isMutable(String word) {
            return word.startsWith("kn");
        }
    }

    class Year7Mutation extends RepetitetiveMutation {

        //Change all double consonants of the same letter to a single consonant.
		// A consonant is any letter that is not one of "a, e, i, o, u." (Example: "apple" becomes "aple").
		// Keep applying this rule as necessary (Example: "zzz" becomes "z".)

		@Override
        protected String mutateWord(String word) throws Exception {
			boolean isMutated = false;
			StringBuffer stringBuffer = new StringBuffer();
			for ( int i = 0 ; i < word.length() ; i++){
				if (isConsonant(word.charAt(i)) &&
					(i+1 <word.length() && isConsonant(word.charAt(i+1)) && word.charAt(i) == word.charAt(i+1))) {
					isMutated = true;
					stringBuffer.append(word.charAt(i));
					i++;
				} else {
					stringBuffer.append(word.charAt(i));
				}
			}
			return isMutated ? stringBuffer.toString() : null;
        }

        @Override
        protected boolean isMutable(String word) {
            for ( int i = 0 ; i < word.length()-1 ; i++){
                if (isConsonant(word.charAt(i))){
                    char c = word.charAt(i);
                    char h = word.charAt(i+1);
                    if ( c == h ) {
                        return true;
                    }
                }
            }
            return false;
        }

        private boolean isConsonant(char c) {
            return c != 'a' && c != 'e' && c != 'i' && c != 'o' && c != 'u';
        }
    }
    abstract class RepetitetiveMutation implements MutateWord {
        protected int getIndex(String word, String str) {
            return getIndex(word, str, 0);
        }

        protected int getIndex(String word, String str , int fromIndex) {
            return word.indexOf(str,fromIndex);
        }

        @Override
        public String mutate(String sentence) throws Exception {
            String[] words = sentence.split("\\s+");
            StringBuffer stringBuffer = new StringBuffer();
			for ( int i = 0 ; i < sentence.length() ; ) {
				if (sentence.charAt(i) ==' '){
					stringBuffer.append(' ');
					i++;
				} else {
					String word = getWord(i, sentence);
					String originalWord = word;
					String wordAfterMutation = word;
					while (true) {
						word = mutateWord(word);
						if (word == null) {
							break;
						}
						wordAfterMutation = word;
					}
					stringBuffer.append(wordAfterMutation);
					i = i + originalWord.length();
				}
			}
            return stringBuffer.toString().substring(0, stringBuffer.toString
                ().length());
        }

		private String getWord(int index, String sentence) {
			StringBuffer stringBuffer = new StringBuffer();
			for ( int i = index ; i < sentence.length() ; i++){
				if (sentence.charAt(i) == ' '){
					break;
				} else {
					stringBuffer.append(sentence.charAt(i));
				}
			}
			return stringBuffer.toString();
		}

		protected abstract String mutateWord(String word) throws Exception;

        protected abstract boolean isMutable(String word);
    }

    abstract class OneTimeMutation implements MutateWord {

    }


    class Year4Mutation extends RepetitetiveMutation {

		// If a "c" is directly followed by a "k", remove the "c". Keep applying this rule as necessary (Example: "cck" becomes "k".)
        @Override
        protected String mutateWord(String word) {
			StringBuffer stringBuffer = new StringBuffer();
			boolean isMutated = false;
			for ( int i = 0 ; i < word.length() ; i++){
				if (word.charAt(i) == 'c' && (i+1 < word.length() && word.charAt(i+1) == 'k')){
					isMutated = true;
					continue;
				} else {
					stringBuffer.append(word.charAt(i));
				}
			}
            return isMutated ? stringBuffer.toString() : null;
        }

        //If a "c" is directly followed by a "k", remove the "c".
        // Keep applying this rule as necessary (Example: "cck" becomes "k".)
        @Override
        protected boolean isMutable(String word) {
            int indexOfC = getIndexOfC(word);
            if (indexOfC != -1){
                int indexOfK = getIndexOfK(word, indexOfC);
                if (indexOfK != -1 && indexOfC+1 == indexOfK){
                    return true;
                }
            }
            return false;
        }

        private int getIndexOfK(String word, int indexOfC) {
            return word.indexOf('k', indexOfC);
        }

        private int getIndexOfC(String word) {
            return word.indexOf('c');
        }
    }

    class Year5Mutation extends RepetitetiveMutation {

        @Override
        protected String mutateWord(String word) throws Exception {
            if (word.startsWith("sch")){
                if (word.length() > 3){
                    return "sk"+word.substring(3);
                } else {
                    return "sk";
                }
            }

            //If a "ch" is directly followed by an "r", change the "ch" to a "k".
			boolean isMutated = false;
			StringBuffer stringBuffer = new StringBuffer();
            for ( int i = 0 ; i <word.length() ; i++) {
				if (word.charAt(i) == 'c' && ((((i+1) < word.length() && word.charAt(i+1) =='h')) && (i+2< word.length
					() && word.charAt(i+2) == 'r'))){
					isMutated = true;
					stringBuffer.append('k');
					i++;
				} else {
					stringBuffer.append(word.charAt(i));
				}
			}
			if (isMutated){
				return  stringBuffer.toString();
			}

            //change all "c"s that are not directly followed by an "h", to a
            //"k". (This includes all "c"s that are the last letter of a word.)
			isMutated = false;
			stringBuffer = new StringBuffer();
            for ( int i = 0 ; i < word.length() ; i++){
				if (word.charAt(i) == 'c' ){
					if ((i+1 < word.length() && word.charAt(i+1) != 'h') || (i == word.length()-1)){
						isMutated = true;
						stringBuffer.append('k');
					} else {
						stringBuffer.append(word.charAt(i));
					}
				} else {
					stringBuffer.append(word.charAt(i));
				}
			}
			return isMutated ? stringBuffer.toString() : null;
        }

        @Override
        protected boolean isMutable(String word) {

            if (word.startsWith("sch")){
                return true;
            }
            int indexOfch = getIndex(word,"ch");
            if (indexOfch != -1){
                int indexOfR = getIndex(word, "r", indexOfch);
                if (indexOfR != -1 && indexOfch+2 == indexOfR){
                    return true;
                }
            }
            int indexOfC = getIndex(word,"c");
            if (indexOfC != -1){
                int indexOfH = getIndex(word,"h", indexOfC);
                if (indexOfC+1 < word.length() && indexOfC+1 != indexOfH){
                    return true;
                }
            }
            return false;
        }
    }


    public String getNewSpelling(int year, String phrase) throws Exception {
        for ( int i = 0 ; i < year ; i++) {
            if ( i == 0){
                phrase = new Year1Mutation().mutate(phrase);
                System.out.println("Phrase after 1st year "+phrase);
            } else if (i == 1) {
                phrase = new Year2Mutation().mutate(phrase);
                System.out.println("Phrase after 2nd year "+phrase);
            } else if (i == 2) {
                phrase = new Year3Mutation().mutate(phrase);
                System.out.println("Phrase after 3rd year "+phrase);
            } else if (i == 3) {
                phrase = new Year4Mutation().mutate(phrase);
                System.out.println("Phrase after 4th year "+phrase);
            } else if (i == 4) {
                phrase = new Year5Mutation().mutate(phrase);
                System.out.println("Phrase after 5th year "+phrase);
            } else if (i == 5) {
                phrase = new Year6Mutation().mutate(phrase);
                System.out.println("Phrase after 6th year "+phrase);
            } else if (i == 6) {
                phrase = new Year7Mutation().mutate(phrase);
                System.out.println("Phrase after 7th year "+phrase);
            }
        }
        return phrase;
    }


    public static void main(String[] args) throws Exception {
        String result = new Twain().getNewSpelling(6, "magic circus giraffes suffer dazzling performances");
        System.out.println(result);
    }
}
