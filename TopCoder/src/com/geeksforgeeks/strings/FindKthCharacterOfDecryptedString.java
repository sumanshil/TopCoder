package com.geeksforgeeks.strings;

/**
 * Created by sshil on 8/28/16.
 */
//http://www.geeksforgeeks.org/find-kth-character-of-decrypted-string/
public class FindKthCharacterOfDecryptedString {

    interface Iterator<T> {
        boolean hasNext();
        T next();
    }

    class SubStringInfo{
        String str;
        int count ;
        public SubStringInfo(String str, int count){
            this.str = str;
            this.count = count;
        }
    }
    class MyIterator implements Iterator<SubStringInfo>{

        private SubStringInfo subStringInfo;
        private StringBuilder subString = null;
        private StringBuilder count = null;
        private int index  = 0;
        private String input;

        public MyIterator(String input){
            this.input = input;
            findSubStr();
            findCount();
            if (subString != null && count != null) {
                subStringInfo = new SubStringInfo(subString.toString(), Integer.parseInt(count.toString()));
            }
        }

        @Override
        public boolean hasNext() {
            return subStringInfo != null;
        }

        @Override
        public SubStringInfo next() {
            SubStringInfo retVal = subStringInfo;
            findSubStr();
            findCount();
            if (subString != null && count != null) {
                subStringInfo = new SubStringInfo(subString.toString(), Integer.parseInt(count.toString()));
            } else {
                subStringInfo = null;
            }
            return retVal;
        }

        private void findSubStr(){
            if (index < input.length()) {
                subString = new StringBuilder();
                while(index < input.length() && input.charAt(index)>='a' && input.charAt(index) <= 'z') {
                    subString.append(input.charAt(index));
                    index++;
                }
            }
        }

        private void findCount(){
            if (index < input.length()) {
                count = new StringBuilder();
                while(index < input.length() && input.charAt(index)>='1' && input.charAt(index) <= '9') {
                    count.append(input.charAt(index));
                    index++;
                }
            }
        }

    }

    public void find(String str, int k){
        MyIterator myIterator = new MyIterator(str);
        int prevCount = 0;
        while (myIterator.hasNext()){
            SubStringInfo subStringInfo = myIterator.next();
            int currentLength  = prevCount + (subStringInfo.count*subStringInfo.str.length());
            if (k >= prevCount && k < currentLength){
                int targetIndex = k - prevCount;
                char c = getTargetChar(subStringInfo, targetIndex-1);
                System.out.println(c);
                break;
            }
            prevCount = currentLength;
        }


    }

    private char getTargetChar(SubStringInfo subStringInfo, int targetIndex) {
        int currentLength = 0;
        int i = 0 ;
        StringBuilder sb = new StringBuilder();
        while (targetIndex > currentLength-1 && i++ < subStringInfo.count){
            sb.append(subStringInfo.str);
            currentLength += subStringInfo.str.length();
        }
        return sb.toString().charAt(targetIndex);
    }

    public static void main(String[] args) {
        new FindKthCharacterOfDecryptedString().find("a2b2c3", 5);
    }
}
