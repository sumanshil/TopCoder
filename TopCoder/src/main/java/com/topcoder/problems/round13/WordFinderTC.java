package com.topcoder.problems.round13;

import java.util.*;
public class WordFinderTC {
  int dirx[] = new int[]{-1, 0, 1, 1, 1, 0, -1, -1};
  int diry[] = new int[]{-1, -1, -1, 0, 1, 1, 1, 0};
  public ArrayList findWords (ArrayList puzzle, ArrayList words) {
    char[][] p = new char[((String)puzzle.get(0)).length()][puzzle.size()];
    ArrayList al = new ArrayList();
    for (int x = 0; x < words.size(); x++) {
      al.add("Not Found");
    }
 
    int width = p[0].length;
    int height = p.length;
    for (int x = 0; x < puzzle.size(); x++) {
      String t = (String)puzzle.get(x);
      for (int y = 0; y < t.length(); y++) {
        p[y][x] = t.charAt(y);
      }
    }
    for (int x = 0; x < ((String)puzzle.get(0)).length(); x++) {
      for (int y = 0; y < puzzle.size(); y++) {
        for (int z = 0; z < 8; z++) {
          int cx = x, cy = y;
          String temp = "";
          while (cx >= 0 && cx < height && cy >= 0 && cy < width) {
            temp += p[cx][cy];
            set(al, y+1, x+1, index(words, temp));
            cx += dirx[z]; cy += diry[z];
          //  System.out.print(temp + " ");
          }
        }        
      }
    }
    return al;
  }
  public void set(ArrayList al, int x, int y, int i) {
    if (i == -1) return;
    al.set(i, x + "," + y);
  }
  public int index(ArrayList w, String t) {
    for (int x = 0; x < w.size(); x++) {
      if (((String)w.get(x)).equals(t)) return x;
    }
    return -1;
  }
  
  public static void main(String[] args){
      ArrayList puzzle = new ArrayList();
      puzzle.add("ETAQAZRPNCLJ");
      puzzle.add("YKCURTAEOAAI");
      puzzle.add("EJEETCRNOCVT");
      puzzle.add("BCCECOAILCAS");
      puzzle.add("ACJCTCMTLCGI");
      puzzle.add("SPEEDRBOATCA");
      puzzle.add("AUTOMOAZBILE");
      puzzle.add("TRRUCKCIAIAU");
      puzzle.add("OTUANAAENALP");
      puzzle.add("AURPIHSTOMOE");
      
      ArrayList word = new ArrayList();
      word.add("AUTOMOBILE");
      word.add("JET");
      word.add("TRUCK");
      word.add("BALLOON");
      word.add("CAR");
      word.add("VAN");
      word.add("BOAT");
      word.add("PLANE");
      word.add("TRAIN");
      word.add("SHIP");
      word.add("CAB");

      ArrayList result = new WordFinderTC().findWords(puzzle, word);
      System.out.println(result);

  }
}
