package com.topcoder.problems.round14;
//http://community.topcoder.com/stat?c=problem_solution&cr=150758&rd=3013&pm=105
import java.util.*;

public class Regex
{
  static final int CHAR = 1, DOT = 2, AST = 3, PAR = 4;
 
  static class Node
  {
    int type;
    char ch;
    Node next, child, prev;
 
    public Node(int type, char ch, Node child, Node prev)
    {
      this.type = type;
      this.ch = ch;
      this.child = child;
      this.prev = prev;
    }
  }
 
  Node regex;
 
  Node parse(String s)
  {
    System.out.println("parse '" + s + "'");
    Node first = null;
    Node cur = null;
 
    for (int j = 0; j < s.length(); ++j)
    {
      Node newNode = null;
      System.out.println(":" + s.charAt(j));
      switch (s.charAt(j))
      {
      case '.':
        newNode = new Node(DOT, ' ', null, cur);
        if (cur != null)
          cur.next = newNode;
        else
          first = newNode;
        cur = newNode;
        break;
      case '*':
        System.out.println("HERE");
        if (cur == null)
          return null;
        traverse(cur, 5);
        newNode = new Node(AST, ' ', cur, cur.prev);
        
        if (cur == first)
          first = newNode;
        if (cur.prev != null)
          cur.prev.next = newNode;
        cur = newNode;
        break;
      case '(':
        int nParen = 0;
        int k;
        for (k = j; k < s.length(); ++k)
        {
          if (s.charAt(k) == '(')
            ++nParen;
          else if (s.charAt(k) == ')')
            if (--nParen == 0)
              break;
        }
        newNode = new Node(PAR, ' ', parse(s.substring(j+1, k)), cur);
        if (cur != null)
          cur.next = newNode;
        else
          first = newNode;
        cur = newNode;
        j = k;
        break;
      default:
        newNode = new Node(CHAR, s.charAt(j), null, cur);
        if (cur != null)
          cur.next = newNode;
        else
          first = newNode;
        cur = newNode;
      }
    }
 
    return first;
  }
 
  boolean match(String s, Node n)
  {
    //System.out.println("Match '" + s + "'");
    //traverse(n, 1);
    int pos = 0;
 
    for (; n != null; n = n.next)
    {
      //System.out.println(">>" + n.type);
      switch (n.type)
      {
      case CHAR:
        if (pos >= s.length())
          return false;
        if (s.charAt(pos) != n.ch)
          return false;
        ++pos;
        break;
      case DOT:
        if (pos >= s.length())
          return false;
        ++pos;
        break;
      case AST:
        //System.out.println("AST");
        for (int j = pos+1; j <= s.length(); ++j)
          if (match(s.substring(pos, j), n.child) && match(s.substring(j), n))
            return true;
        break;
      case PAR:
        for (int j = pos+1; j <= s.length(); ++j)
          if (match(s.substring(pos, j), n.child) && match(s.substring(j), n.next))
            return true;
        return false;
      }
 
    }
 
    //System.out.println("RETURN " + (pos == s.length()) + "," + pos + "," + s.length());
    return pos == s.length();
  }
 
  void traverse(Node n, int i)
  {
    if (n == null)
      return;
    for (int j = 0; j < i; ++j)
      System.out.print(" ");
    System.out.print(n.type);
    if (n.type == CHAR)
      System.out.print(n.ch);
    System.out.println();
    if (n.child != null)
      traverse(n.child, i+1);
    if (n.next != null)
      traverse(n.next, i);
  }
 
  public ArrayList match(ArrayList text, String pattern)
  {
    regex = parse(pattern);
    traverse(regex, 0);
 
    ArrayList ret = new ArrayList();
    Iterator it = text.iterator();
    while (it.hasNext())
    {
      String s = (String)it.next();
      if (match(s, regex))
        ret.add(s);
    }
 
    return ret;
  }
  
  public static void main(String[] args){
      ArrayList al = new ArrayList();
//      al.add("ae");
      al.add("abde");
//      al.add("abcde");
//      al.add("abcccde");
//      al.add("abdbccdbcccdbdbde");
//      al.add("ace");
//      al.add("abd");
      String pattern =  "a(bc*d)*e";
      ArrayList<String> results = new Regex().match(al, pattern);
      for(String str : results){
          System.out.println(str);
      }
  }
}
