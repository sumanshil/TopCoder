package com.topcoder.problems.tco2003onlineround2;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by sshil on 10/25/2015.
 */
//http://community.topcoder.com/stat?c=problem_statement&pm=1019&rd=4703
public class Calculate {
    private Map<String, Integer> map = new HashMap<>();
    private String precedence = "(^*/+-";
    static  class ExpressionBoundary {
        int lowIndex;
        int highIndex;

        public ExpressionBoundary(int low, int high) {
            this.lowIndex =  low;
            this.highIndex = high;
        }
    }
    public 	int calc(String expression, String[] variables) {
        for (String str : variables){
            String[] arr = str.split("\\s+");
            String variableName = arr[0];
            int variableValue = Integer.parseInt(arr[1]);
            map.put(variableName, variableValue);
        }

        int result = calculateRecursive(expression);
        System.out.println(result);
        return result;

    }

    private int calculateRecursive(String expression) {
        Optional<ExpressionBoundary> parsingInfoOptional = parse(expression);
        if (!parsingInfoOptional.isPresent() && expression.length() > 0){
            try {
            return Integer.parseInt(expression);
            } catch (NumberFormatException e) {
                return map.get(expression);
            }
        }
        if (parsingInfoOptional.isPresent() &&
            isTerminalExpression(parsingInfoOptional.get(),expression)
            && !startWithParenthesis(expression)) {
            return evaluate(expression);
        }
        ExpressionBoundary parsingInfo = parsingInfoOptional.get();
        String leftExpression = "";
        if (parsingInfo.lowIndex > 0){
            leftExpression = expression.substring(0, parsingInfo.lowIndex);
        }

        String rightExpression = expression.substring(parsingInfo.highIndex + 1);
        int evaluatedValue;
        if (expression.charAt(parsingInfo.lowIndex) == '(') {
            evaluatedValue = calculateRecursive(expression.substring
                (parsingInfo.lowIndex+1, parsingInfo.highIndex));
        } else {
            evaluatedValue = calculateRecursive(expression.substring
                (parsingInfo.lowIndex, parsingInfo.highIndex+1));
        }
        return  calculateRecursive
            (leftExpression+evaluatedValue+rightExpression);
    }

    private boolean startWithParenthesis(String expression) {
        return expression.charAt(0) == '(';
    }

    private Optional<ExpressionBoundary> parse(String expression) {
        int leftParenthesisIndex = expression.indexOf(precedence.charAt(0));
        if (leftParenthesisIndex >= 0) {
            int rightParenthesisIndex = getRightParenthesis(expression,
                                                            leftParenthesisIndex);
            return Optional.ofNullable(new ExpressionBoundary(leftParenthesisIndex,
                                    rightParenthesisIndex));
        }

        int exponentiationIndex =  expression.indexOf(precedence.charAt(1));
        if (exponentiationIndex > 0) {
            int leftIndex = getLeftOperandIndex(exponentiationIndex,
                                                expression);
            int rightIndex = getRightOperandIndex(exponentiationIndex,
                                                  expression);
            return Optional.ofNullable(new ExpressionBoundary(leftIndex,
                                                       rightIndex));
        }

        int index1 = expression.indexOf('*');
        int index2 = expression.indexOf('/');

        Optional<ExpressionBoundary> parsingInfo = getParsingInfo(index1, index2,
                                                          expression);
        if (parsingInfo.isPresent()){
            return Optional.ofNullable(parsingInfo.get());
        }
        index1 = expression.indexOf('+', 1);
        index2 = expression.indexOf('-', 1);
        parsingInfo = getParsingInfo(index1, index2, expression);
        if (parsingInfo.isPresent()){
            return Optional.ofNullable(parsingInfo.get());
        }

        return Optional.empty();
    }

    private Optional<ExpressionBoundary> getParsingInfo(int index1, int index2, String
        expression){
        int index = getOperatorIndex(index1, index2);
        if (index > 0){
            int lowerIndex = getLeftOperandIndex(index, expression);
            int higherIndex = getRightOperandIndex(index, expression);
            return Optional.ofNullable(new ExpressionBoundary(lowerIndex,
                                                       higherIndex));
        }
        return Optional.empty();
    }

    private int getRightOperandIndex(int index, String expression) {
        index = index+1;
        if ( expression.charAt(index) == '-') {
            index = index+1;
        }
        while (index < expression.length()-1
               && !isOperator(expression.charAt(index))){
            index++;
        }
        return index == expression.length()-1 ? expression.length()-1 : index-1;
    }

    private int getLeftOperandIndex(int index, String expression) {
        index = index-1;
        while (index >= 0 && !isOperator(expression.charAt(index))){
            index--;
        }
        if (index >= 0
            && expression.charAt(index) =='-'
            && leadingCharIsOperator(index, expression)) {
            index--;
        }
        return index == 0 ? index : index+1;
    }

    private boolean leadingCharIsOperator(int index, String expression) {
        if (index-1 < 0){
            return false;
        } else if (isOperator(expression.charAt(index-1))){
            return true;
        }
        return false;
    }

    private boolean isOperator(char c) {
        return precedence.indexOf(c) >= 0;
    }

    private int getOperatorIndex(int index1, int index2) {
        if (index1 > 0 && index2 > 0){
            // both are present
            int lowerIndex =  index1 < index2 ?
                index1 : index2;
            return lowerIndex;
        } else if (index1 <= 0 && index2 > 0){
            return index2;
        } else if (index1 > 0 && index2 <= 0){
            return index1;
        } else {
            return -1;
        }
    }

    private int getRightParenthesis(String expression, int leftParenthesisIndex) {
        int leftParenthesisCount = 0;
        for ( int i = leftParenthesisIndex+1 ; i < expression.length() ; i++) {
            if (expression.charAt(i) == '('){
                leftParenthesisCount++;
            } else if (expression.charAt(i) == ')'){
                if (leftParenthesisCount == 0){
                    return  i;
                } else {
                    leftParenthesisCount--;
                }
            }
        }
        // throw exception;
        return -1;
    }

    private int evaluate(String expression) {
        int operatorIndex = getOperatorIndex(expression);
        String leftOperand = expression.substring(0, operatorIndex);
        String rightOperand = expression.substring(operatorIndex+1);
        int leftIntValue = replaceAndGetVariableValue(leftOperand);
        int rightIntValue = replaceAndGetVariableValue(rightOperand);
        char operator = expression.charAt(operatorIndex);
        return evaluate(leftIntValue, operator, rightIntValue);
    }

    private int replaceAndGetVariableValue(String leftOperand) {
        int leftIntValue ;
        try {
            leftIntValue = Integer.parseInt(leftOperand);
        } catch (Exception e) {
            leftIntValue = map.get(leftOperand);
        }
        return leftIntValue;
    }

    private int evaluate(int leftOperand, char operator, int rightOperand) {
        switch (operator){
            case '*' :
                return leftOperand * rightOperand;
            case '+' :
                return leftOperand + rightOperand;
            case '-' :
                return leftOperand - rightOperand;
            case '/' :
                return leftOperand/rightOperand;
            case '^' :
                return (int)Math.pow(leftOperand, rightOperand);
            default:
                return 0;
        }
    }

    private int getOperatorIndex(String expression) {
        for ( int i = 0 ; i < expression.length() ; i++) {
            if (precedence.indexOf(expression.charAt(i)) >= 0) {
                if (!signOperator(i, expression)) {
                    return i;
                }
            }
        }
        return -1;
    }

    private boolean signOperator(int index, String expression) {
        if (expression.charAt(index) == '-'
            && (((index-1 > 0)&& isOperator(expression.charAt(index-1)))
                || (index == 0))){
            return  true;
        }
        return false;
    }

    private boolean isTerminalExpression(ExpressionBoundary parsingInfo, String expression) {
        if (parsingInfo.lowIndex <= 0
            && parsingInfo.highIndex>= expression.length()-1) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
//        String expression = "x^y^x";
//        String[] variables = {"x 2","y 3"};

//        String expression = "x-y+z";
//        String[] variables = {"x 3","y 2","z 1"};
//        String expression = "x+y*z^(x+x)-y";
//        String[] variables = {"x 1","y 2","z 3"};
//        String expression = "x*y+3*x";
//        String[] variables = {"x 1", "y 11"};
//        String expression = "x^p*2^(2^p)/t^p^t+xx*n^v";
//        String[] variables = {"x 53", "xx 32", "p 3","t 2","n -1","v -21"};
        String expression = "0-(0-(0-b))-a";
        String[] variables = {"a -1", "b 1"};
        int result = new Calculate().calc(expression, variables);
        System.out.println(result);
    }

}
