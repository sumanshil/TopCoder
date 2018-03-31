package com.topcoder.problems.wed091013;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
//http://community.topcoder.com/stat?c=problem_solution&cr=150758&rd=3009&pm=92
public class Expressions {
    //private char[] charArray = {'/','*','+','-'};
    private Map<Character, Integer> map = new HashMap<Character, Integer>();
    
    public int evaluate(String expr){
      //System.out.println(expr);
      if (expr == null)
        return 0;
   
      StringBuffer rhs = new StringBuffer();
   
      char ch;
      int paren = 0;
      int nZero = 0;
      for (int j = expr.length()-1; j >= 0; --j)
      {
        ch = expr.charAt(j);
   
        if (paren == 0 && ch == '+')
          return evaluate(expr.substring(0, j)) + evaluate(rhs.toString());
        else if (paren == 0 && ch == '-')
          return evaluate(expr.substring(0, j)) - evaluate(rhs.toString());
        else
        {
          if (ch == ')')
            ++paren;
          else if (ch == '(')
            if (--paren == 0)
              ++nZero;
   
          rhs.insert(0, ch);
        }
      }
   
      if (nZero == 1 && expr.charAt(0) == '(' && expr.charAt(expr.length()-1) == ')')
        return evaluate(expr.substring(1, expr.length()-1));
   
      rhs = new StringBuffer();
   
      for (int j = expr.length()-1; j >= 0; --j)
      {
        ch = expr.charAt(j);
   
        if (paren == 0 && ch == '*')
          return evaluate(expr.substring(0, j)) * evaluate(rhs.toString());
        else if (paren == 0 && ch == '/')
          return evaluate(expr.substring(0, j)) / evaluate(rhs.toString());
        else
        {
          if (ch == ')')
            ++paren;
          else if (ch == '(')
            --paren;
          rhs.insert(0, ch);
        }
      }
      try
      {
        return Integer.parseInt(expr);
      }
      catch (NumberFormatException e)
      {
        return 0;
      }
    }    
//    public int evaluate(String param){
//        map.put('(', 7);
//        map.put(')', 6);
//        map.put('*', 5);        
//        map.put('/', 4);
//        map.put('+', 3);
//        map.put('-', 2);
//        
//        Stack<Character> operatorStack = new Stack<Character>();
//        Stack<Integer> operandStack = new Stack<Integer>();
//        StringBuffer number = new StringBuffer();
//        for(int i = 0 ; i < param.length() ; i++){
//            char c = param.charAt(i);
//            if (isOperator(c)){
//                if (c == ')'){
//                    storeNumber(number, operandStack);
//                    number = new StringBuffer();
//                    while(operatorStack.peek() != '('){
//                        int operand1 = operandStack.pop();
//                        int operand2 = operandStack.pop();
//                        char operator = operatorStack.pop();
//                        int result = operate(operand2, operand1, operator);
//                        operandStack.push(result);
//                    }
//                    operatorStack.pop();
//                } else if (c == '('){
//                    operatorStack.push(c);
//                } else {
//                    if (!operatorStack.isEmpty() && operatorStack.peek()!='(' && getPriority(c,operatorStack.peek()) > 0){
//                        storeNumber(number, operandStack);
//                        number = new StringBuffer(); 
//                        while(!operatorStack.isEmpty()&& operatorStack.peek()!='(' && getPriority(c,operatorStack.peek()) > 0){
//                            int operand1 = operandStack.pop();
//                            int operand2 = operandStack.pop();
//                            char operator = operatorStack.pop();
//                            int result = operate(operand2, operand1, operator);
//                            operandStack.push(result);
//                        }
//                        operatorStack.push(c);
//                    } else {
//                        storeNumber(number, operandStack);
//                        number = new StringBuffer();                                                
//                        operatorStack.push(c);
//                    }
//                }                               
//            } else {
//                //operandStack.push(c);
//                number.append(c);
//            }
//        }
//        
//        if (number.length() >0){
//            operandStack.push(Integer.parseInt(number.toString()));
//        }
//        
//        while(!operatorStack.isEmpty()){
//            char c= operatorStack.pop();
//            int i1 = operandStack.pop();
//            int i2 = operandStack.pop();
//            int result = operate(i2, i1, c);            
//            operandStack.push(result);
//        }
//        
//        return operandStack.pop();
//    }

    private void storeNumber(StringBuffer number, Stack<Integer> operandStack) {
        if (number.length() >0){
            int num= Integer.parseInt(number.toString());
            operandStack.push(num);
        }
        
    }

    // return > 1 if peek of the stack has priority than the current operator
    private int getPriority(char c, char peek) {        
        return map.get(peek) >= map.get(c) ? 1 : -1;   
    }


    private int operate(int c1, int c2, char operator){
        int result=-1;
        switch(operator){
            case '+':
                //result = (((int)c1)-48)+(((int)c2)-48);
                result = c1+c2;
                break;
            case '-':
                //result = (((int)c1)-48)-(((int)c2)-48);
                result = c1-c2;
                break;
            case '*':
                //result = (((int)c1)-48)*(((int)c2)-48);
                result = c1*c2;
                break;
            case '/':
                //result = (((int)c1)-48)/(((int)c2)-48);
                result = c1/c2;
                break;                
        }
        return result;
    }
    
    private boolean isOperator(char c) {        
        return (c=='+'||c=='-'||c=='/'||c=='*'||c=='('||c==')');
    }
    
    public static void main(String[] args){
        int c =0;
        System.out.println(Character.forDigit(c, 10));
        //String str = "6+2*(3*(1+1))-6/(2+3)";
        //String str = "6*2+(3*(1+1))/6-(2+3)";
        //String str = "4*8*3-4-4-3-1";
        //String str = "4*8+3";
        //String str = "1/2/3/4/5+394-128/3*21-1-2+3-2/4";
        //String str = "99*(((5+6*23/5)*4+315)+(4-2)*4)/4+75/5";
        //String str = "((5+6*23/5)*4+315)";
        //String str = "14*(3+(2+3)/4*2)/13";
        //String str = "14*(2+3)/13";
        String str = "(14*(((2+3))))/13";
        int result = new Expressions().evaluate(str);
        System.out.println(result);
    }
}
