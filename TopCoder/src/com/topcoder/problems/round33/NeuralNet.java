package com.topcoder.problems.round33;
//http://community.topcoder.com/stat?c=problem_statement&pm=176&rd=4003
import java.util.Map;
import java.util.WeakHashMap;

public class NeuralNet {

	private final static double DELTA = 0.1;
	private static Map<Integer, double[]> map = new WeakHashMap<Integer, double[]>();
	static {
		map.put(0, new double[]{0.25, 0.25, 0.25, 0.25});
		map.put(1, new double[]{0.25, 0.25, 0.25, 0.25});
		map.put(2, new double[]{0.25, 0.25, 0.25, 0.25});
		map.put(3, new double[]{0.25, 0.25, 0.25, 0.25});
	}
	public int[] testTraining(int[] trainingInputs , int[] trainingOutputs , int[] testInputs )
	{
		int[] r = new int[4];
		if ( !inputValid(trainingInputs) )
		{
			for(int i = 0 ; i< 4 ; i++)
			{
				double finalResult = applyWeights(map.get(i), testInputs);
				r[i] = getInt(finalResult);
			}
			return r;
		}
		
		for( int i = 0 ; i < 4 ; i++ )
		{
			// i represent node number
			double[] weights = map.get(i);
			double result = 0;
			result += applyWeights(weights, trainingInputs);
			int intResult = getInt( result );			
			while(intResult != trainingOutputs[i])
			{
				//double[] newWeights = weights;
				if ( intResult < trainingOutputs[i] )
				{
					// increase the weights
					weights = increaseWeights( trainingInputs, weights );
					result = applyWeights(weights, trainingInputs);
					intResult = getInt(result);
				}
				else
				{
					// increase the weights
					weights = decreaseWeights( trainingInputs, weights );
					result = applyWeights(weights, trainingInputs);
					intResult = getInt(result);
				}
			}
			map.put(i, weights);
			double finalResult = applyWeights(weights, testInputs);
			r[i] = getInt(finalResult);
		}
        return r;
	}
	
	private boolean inputValid(int[] trainingInputs)
	{
		for(int i = 0 ; i < 4 ; i++)
		{
			if (trainingInputs[i] == 1)
			{
				return true;
			}
		}
		return false;
	}



	private int getInt(double result)
	{
	    if (result < 0.5)
	    {
	    	return 0;
	    }
	    return 1;
		
	}



	private double[] decreaseWeights(int[] trainingInputs, double[] weights)
	{
	    for( int i = 0 ; i < 4 ; i++ )
	    {
	    	if (trainingInputs[i] == 1)
	    	{
	    		weights[i] -= DELTA;
	    	}
	    }
		return weights;
	}

	private double[] increaseWeights(int[] trainingInputs, double[] weights) 
	{
	    for( int i = 0 ; i < 4 ; i++ )
	    {
	    	if (trainingInputs[i] == 1)
	    	{
	    		weights[i] += DELTA;
	    	}
	    }
		return weights;
	}



	private double applyWeights(double[] weights, int[] trainingInputs)
	{
		double result = 0;
		for(int j = 0 ; j < 4 ; j++)
		{
			result += (weights[j])*(trainingInputs[j]);
		}
          		
		return result;
	}
	public static void main(String[] args)
	{
		int[] trainingInputs  = { 1, 1, 0, 0} ;
		int[] trainingOutputs = { 1, 1, 0, 1};
		int[] testInputs      ={1, 0, 0, 0};
		int[] result = new NeuralNet().testTraining(trainingInputs, trainingOutputs,testInputs);
		for(int i : result)
		{
			System.out.println(i);
		}
	}
}
