package com.topcoder.problems.round34;
//http://community.topcoder.com/stat?c=problem_statement&pm=183&rd=4005
public class HoldCost {

	public int calcHoldCost(int[] requirements,
			                int initialInventory,
			                int prodPerMonth,
			                int holdingCostPerUnit)
	{
		
		int result = calculateRecursive(initialInventory,
				                        prodPerMonth,
				                        holdingCostPerUnit,
				                        requirements,
				                        0,
				                        0);
		return result;
	}
	
	private int calculateRecursive(int priorInventory,
			                       int prodPerMonth,
			                       int holdingCostPerUnit,
			                       int[] requirements,
			                       int index,
			                       int totalHoldingCost) {
		if (index >= requirements.length)
		{
			return totalHoldingCost;
		}
		
		int holdingCostForCurrentMonth = priorInventory*
		                                 holdingCostPerUnit;
		int inventoryLevelForThisMonth = (priorInventory + prodPerMonth)
		                                  - requirements[index];
		if (inventoryLevelForThisMonth < 0)
			inventoryLevelForThisMonth = 0;
		return calculateRecursive(inventoryLevelForThisMonth,
				                  prodPerMonth,
				                  holdingCostPerUnit,
				                  requirements,
				                  index+1,
				                  totalHoldingCost+holdingCostForCurrentMonth);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
        int result = new HoldCost().calcHoldCost(new int[]{110, 70, 40, 20, 10, 100, 15, 40, 120, 70, 10, 80}, 70, 50, 15);
        System.out.println(result);
	}

}
