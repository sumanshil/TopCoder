//http://community.topcoder.com/stat?c=problem_statement&pm=179&rd=51
package com.topcoder.problems.Invitational2001.round2;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FSM {

	static class FsmState
	{
		String name;
		Map<String, FsmEdge> symbolToEdge = new HashMap<String, FsmEdge>();
	}
	
	static class FsmEdge
	{
		String source;
		String target;
		String symbol;
		String function;
		
		public FsmEdge(String source, String target, String symbol, String function)
		{
			this.source   = source;
			this.target   = target;
			this.symbol   = symbol;
			this.function = function;
		}		
	}
	
	private Map<String, FsmState> nameToState = new HashMap<String, FsmState>();
	
	public String[] runFSM(String symbols, String states, String[] edges)
	{
		for(String edge : edges)
		{
			String[] parameters = edge.split("\\s+");
			String source = parameters[0];
			String target = parameters[1];
			String symbol = parameters[2];
			String function = parameters[3];
			FsmEdge fsmEdge = new FsmEdge(source, target, symbol, function);
			
			if ( nameToState.containsKey(source) )
			{
			    FsmState state = nameToState.get(source);
			    if (state.symbolToEdge.containsKey(symbol))
			    {
			    	//List<FsmEdge> fsmEdges = state.symbolToEdge.get(symbol);
			    	//fsmEdges.add(fsmEdge);
			    }
			    else
			    {
			    	state.symbolToEdge.put(symbol, fsmEdge);
			    }
			}
			else
			{
			     FsmState newState = new FsmState();
			     newState.name = source;
			     newState.symbolToEdge.put(symbol, fsmEdge);
			     nameToState.put(source, newState);
			}
		}
		
		String[] stateArr = states.trim().split("\\s+");
		String startState = stateArr[0];
		this.states = stateArr;
		
		
		String[] symbolsArr = symbols.trim().split("\\s+");
		this.symbols = symbolsArr;
		String currentStateName = startState;
		List<String> resultList = new LinkedList<String>();
		while(true)
		{
			FsmState currentState = nameToState.get(currentStateName);
			String symbol = getNextSymbol();
			if (symbol == null)
			{
				break;
			}
			
			if (symbol.length() == 0)
			{
				continue;
			}
			else if (currentState == null || currentState.symbolToEdge == null)
			{
				resultList.add("ERROR");
			}
			else
			{
				if ( currentState.symbolToEdge.containsKey(symbol) )
				{
					FsmEdge edge = currentState.symbolToEdge.get(symbol);
					if ( edge != null)
					{
						currentStateName = edge.target;
						resultList.add(edge.function+"("+edge.symbol+")");
					}
					else
					{
						resultList.add("ERROR");
					}
				}
				else
				{
					resultList.add("ERROR");
				}
			}
		}
		return (String[])resultList.toArray(new String[0]);
	}
	
	private int symbolIndex;
	private String[] symbols;
	private String getNextSymbol()
	{
		return (symbolIndex < symbols.length) ? symbols[symbolIndex++] : null;
	}
	
	private int stateIndex;
	private String[] states;
	private String getNextState()
	{
		return (stateIndex < states.length) ? states[stateIndex++] : null;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
//        String states = "Zerocents Fivecents Tencents Fifteencents Twentycents";
//        String[] edges = {
//        		"Zerocents  Fivecents  nickel needMoreOne",
//        		"Zerocents  Tencents dime   needMoreTwo",
//        		"Fivecents  Fifteencents dime   needMoreThree",
//        		"Fivecents  Tencents nickel needMoreFour",
//        		"Tencents Fifteencents nickel needMoreFive",
//        		"Tencents Twentycents dime   releasingCandyOne",
//        		"Fifteencents Twentycents nickel releasingCandyTwo",
//        		"Fifteencents Twentycents dime   releasingCandyThree",
//        		"Twentycents Fivecents  nickel needMoreSix",
//        		"Twentycents Tencents dime   needMoreSeven"
//        		};
        //String symbols="nickel dime dime";
        //String symbols = "dime oops dime";
        //String symbols="dime nickel dime dime dime nickel";
//		String symbols = " a   a  a b  b   c";
//		String states = " stateZero stateOne stateTwo";		
//		String[]  edges=
//				{"stateZero stateOne a doSomething",
//				 "stateOne stateTwo b doNothing",
//				 "stateTwo stateZero c doEverything"};

//		String symbols = " a   a  a b  b   c";
//		String states = " stateZero stateOne stateTwo";		
//		String[]  edges=

        String[] result = new FSM().runFSM(	"a a", "x y", new String[] {"x y a go", "y x a goagain"});
        for(String str : result)
        {
        	System.out.println(str);
        }
	}

}
