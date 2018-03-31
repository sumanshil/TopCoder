//package com.topcoder.problems;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//
//public class SpellingCorrector1 {
//    static class Pair
//    {
//        private String word;
//        private byte dist; 
//        // dist is byte because dist<=128.
//        // Moreover, dist<=6 in real application
//
//        public Pair(String word,byte dist)
//        {
//            this.word = word;
//            this.dist = dist;
//        }
//
//        public String getWord()
//        {
//            return word;
//        }
//
//        public int getDist()
//        {
//            return dist;
//        }
//    }
//
//
//    public static void main(String[] args) throws Exception
//    {
//
//        HashSet<String> usedWords;
//        HashSet<String> dict;
//        ArrayList<String> corrections;
//        ArrayDeque<Pair> states;
//
//        usedWords = new HashSet<String>();
//        corrections = new ArrayList<String>();
//        dict = new HashSet<String>();
//        states = new ArrayDeque<Pair>();
//
//        // populate dictionary. In real usage should be populated from prepared file.
//        dict.add("Jeniffer");
//        dict.add("Jeniffert"); //depth 2 test
//
//        usedWords.add("Jniffer");
//        states.add(new Pair("Jniffer", (byte)0));
//        while(!states.isEmpty())
//        {
//            Pair head = states.pollFirst();
//            //System.out.println(head.getWord()+" "+head.getDist());
//            if(head.getDist()<=2) 
//            {
//                // checking reached  depth.
//                //4 is the first depth where we don't generate anything  
//
//                // swap adjacent letters
//                for(int i=0;i<head.getWord().length()-1;i++)
//                {
//                    // swap i-th and i+1-th letters
//                    String newWord = head.getWord().substring(0,i)+head.getWord().charAt(i+1)+head.getWord().charAt(i)+head.getWord().substring(i+2);
//                    // even if i==curWord.length()-2 and then i+2==curWord.length 
//                    //substring(i+2)  doesn't throw exception and returns empty string
//                    // the same for substring(0,i) when i==0
//
//                    if(!usedWords.contains(newWord))
//                    {
//                        usedWords.add(newWord);
//                        if(dict.contains(newWord))
//                        {
//                            corrections.add(newWord);
//                        }
//                        states.addLast(new Pair(newWord, (byte)(head.getDist()+1)));
//                    }
//                }
//
//                 // insert letters 
//                for(int i=0;i<=head.getWord().length();i++)
//                      for(char ch='a';ch<='z';ch++)
//                      {
//                            String newWord = head.getWord().substring(0,i)+ch+head.getWord().substring(i);
//                            if(!usedWords.contains(newWord))
//                            {
//                                usedWords.add(newWord);
//                                if(dict.contains(newWord))
//                                {
//                                    corrections.add(newWord);
//                                }
//                                states.addLast(new Pair(newWord, (byte)(head.getDist()+1)));
//                            }
//                      }
//            }
//        }
//
//        for(String correction:corrections)
//        {
//          System.out.println("Did you mean "+correction+"?");
//        }
//
//        usedWords.clear();
//        corrections.clear();
//        // helper data structures must be cleared after each generateCorrections call - must be empty for the future usage. 
//
//    }
//}
