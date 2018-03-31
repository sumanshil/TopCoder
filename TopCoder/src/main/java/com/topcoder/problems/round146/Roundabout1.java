package com.topcoder.problems.round146;

import java.util.*;
public class Roundabout1 {
  String north;
  String west;
  String south;
  String east;
  String[] Q = {"", "", "", ""};
  String[] r = {"", "", "", ""};
  void putInQ(int t) {
    for (int dir = 0; dir < 4; dir++) {
      if (dir == 0) {
        if (t < north.length() && north.charAt(t) != '-') {
          Q[dir] = Q[dir] + north.charAt(t);
        }
      }
      if (dir == 1) {
        if (t < west.length() && west.charAt(t) != '-') {
          Q[dir] = Q[dir] + west.charAt(t);
        }
      }
      if (dir == 2) {
        if (t < south.length() && south.charAt(t) != '-') {
          Q[dir] = Q[dir] + south.charAt(t);
        }
      }
      if (dir == 3) {
        if (t < east.length() && east.charAt(t) != '-') {
          Q[dir] = Q[dir] + east.charAt(t);
        }
      }
    }
  }
  
  
  int getMinWait() {
    while (north.length() > 0 && north.charAt(north.length() - 1) == '-') {
      north = north.substring(0, north.length() - 1);
    }
    while (west.length() > 0 && west.charAt(west.length() - 1) == '-') {
      west = west.substring(0, west.length() - 1);
    }
    while (south.length() > 0 && south.charAt(south.length() - 1) == '-') {
      south = south.substring(0, south.length() - 1);
    }
    while (east.length() > 0 && east.charAt(east.length() - 1) == '-') {
      east = east.substring(0, east.length() - 1);
    }
    int minwait = north.length();
    minwait = Math.max(minwait, west.length());
    minwait = Math.max(minwait, south.length());
    minwait = Math.max(minwait, east.length());
    return minwait;
  }
  
  
  void printState() {
    System.out.print("Q:");
    for (int i = 0; i < 4; i++) {
      System.out.print((Q[i].length() > 0 ? Q[i] : "-") + " ");
    }
    System.out.print("r:");
    for (int i = 0; i < 4; i++) {
      System.out.print((r[i].length() > 0 ? r[i] : "-"));
    }
    System.out.println();
  }
  
  
  public int clearUpTime(String north, String east, String south, String west) {
    int t = 0;
    this.north = north;
    this.west = west;
    this.south = south;
    this.east = east;
    int minwait = getMinWait();
    while (true) {
      putInQ(t);
      printState();
      String[] nextr = {"", "", "", ""};
      String[] nextq = {"", "", "", ""};
      for (int i = 0; i < 4; i++) {
        nextq[i] = Q[i];
      }
      if (Q[0].length() > 0 && Q[1].length() > 0 && Q[2].length() > 0 && Q[3].length() > 0) {
        if (r[3].length() == 0) {
          nextr[0] = Q[0].substring(0, 1);
          nextq[0] = Q[0].substring(1);
        }
      } else {
        for (int dir = 0; dir < 4; dir++) {
          if (Q[dir].length() > 0) {
            int left = (dir + 3) % 4;
            if (Q[left].length() == 0 && r[left].length() == 0) {
              nextr[dir] = Q[dir].substring(0, 1);
              nextq[dir] = Q[dir].substring(1);
            }
          }
        }
      }
      //remove cars
      for (int dir = 0; dir < 4; dir++) {
        if (r[dir].length() > 0) {
          if (r[dir].charAt(0) == 'N' && dir == 0) {
            r[dir] = "";
          } else if (r[dir].charAt(0) == 'W' && dir == 1) {
            r[dir] = "";
          } else if (r[dir].charAt(0) == 'S' && dir == 2) {
            r[dir] = "";
          } else if (r[dir].charAt(0) == 'E' && dir == 3) {
            r[dir] = "";
          }
        }
      }
      //rotate old cars
      for (int dir = 0; dir < 4; dir++) {
        if (r[dir].length() > 0) {
          nextr[(dir + 1) % 4] = r[dir];
        }
      }
      r = nextr;
      Q = nextq;
      boolean done = true;
      for (int i = 0; i < 4; i++) {
        if (Q[i].length() > 0) {
          done = false;
        }
        if (r[i].length() > 0) {
          done = false;
        }
      }
      if (t > 1000 || done && t >= minwait) {
        break;
      }
      t++;
    }
    return t == 0 ? 0 : t + 1;
  }
  
	public static void main(String[] args) 
	{
		int result = new Roundabout1().clearUpTime("--", "--", "WE", "-S");
		//int result = new Roundabout().clearUpTime("--S", "---", "---", "---");
		//int result = new Roundabout().clearUpTime("WWW","NNN","---","---");
		//int result = new Roundabout().clearUpTime("E------", "-------", "-------", "-------");
		//int result = new Roundabout().clearUpTime("ES","N","E","");//NW
		//int result = new Roundabout().clearUpTime("E","-N","W","-S");
		//int result = new Roundabout().clearUpTime("S", "---", "N", "---");
		//int result = new Roundabout1().clearUpTime("-----------E", "-----------W", "-----------N", "-----------S");
		System.out.println(result);

	}
  
  
}