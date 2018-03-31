package com.topcoder.problems.round34;
//http://community.topcoder.com/stat?c=problem_statement&pm=185&rd=4005
public class MRP 
{

	public int calcOrderSize(int[] forecast, int initialInventory, int costPerUnit, int
			costPerOrder, int leadTime, int holdingCostPerUnit)
	{		
		int bestCost = Integer.MAX_VALUE;
		int bestOrderSize = 0;
		for( int ii = 3; ii <= 2000 ; ii++)
		{
   			    cost(forecast,
			        initialInventory,
			        costPerUnit,
			        costPerOrder,
			        leadTime,
			        holdingCostPerUnit,
			        ii);
			if (this.totalCost!= -1 && this.totalCost < bestCost)
			{
				bestCost = this.totalCost;
				bestOrderSize = this.orderSize;
			}
		}
		return bestOrderSize;
	}
	private void cost(int[] forecast,
			         int initialInventory,
			         int costPerUnit,
			         int costPerOrder,
			         int leadTime,
			         int holdingCostPerUnit,
			         int orderSize) {
		
		int cost = 0;
		int[] orderInPipeline = new int[forecast.length];
		boolean successful = true;
		for( int j = 0 ; j < 12 ; j++)
		{
			int inventoryNeeded = 0;
			//if ( forecast[j] <= initialInventory )
			{
				if ( (12- j) < leadTime)
				{
					successful = false;
					break;
				}
				if (forecast[j] >= initialInventory)
				{
					inventoryNeeded = forecast[j]-initialInventory;
				}
				else
				{
					inventoryNeeded = 0;
				}
				int minOrderNo = inventoryNeeded/orderSize;
				if ((inventoryNeeded%orderSize)>0)
				{
					minOrderNo++;
				}
				if (minOrderNo == 0)
					minOrderNo++;
				int totalOrderSize = minOrderNo*orderSize;
				orderInPipeline[j+leadTime] = totalOrderSize;
				if (orderInPipeline[j] > 0)
				{
					if (orderInPipeline[j] >= inventoryNeeded)
					{
						int extraInventory = orderInPipeline[j]-inventoryNeeded;
						int holdingCosts=0;
						if (initialInventory > 0)
						{
							holdingCosts = initialInventory*holdingCostPerUnit;
						}
						int orderCost = minOrderNo*costPerOrder;
						int unitCost  = totalOrderSize*costPerUnit;
						cost += (holdingCosts+orderCost+unitCost);
						initialInventory = extraInventory;
					}
					else
					{
						// we might have to abort
						successful = false;
						break;
					}
				}
				else
				{
					// check if there are sufficient stock to proceed
					// otherwise we need to abort
					successful = false;
					break;					
				}
			}
		}
		                                
		if (successful)
		{
			this.totalCost = cost;
			this.orderSize =  orderSize;
		}
		else
		{
			this.totalCost = -1;
			this.orderSize =  -1;			
		}
		System.out.println("For order size "+orderSize +" total cost is "+this.totalCost);
	}
	
	private int orderSize = 0;
	private int totalCost = 0;
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
        //int result = new MRP().calcOrderSize(new int[]{10,10,10,10,10,10,10,10,10,10,10,10},3,1,3,0,1);
		//int result = new MRP().calcOrderSize(new int[]{10,10,10,10,10,10,10,10,10,10,10,10},5,1,2,0,1);
		int result = new MRP().calcOrderSize(new int[]{1,1,1,1,1,1,1,1,1,1,1,1},0,1,5,0,1);
        System.out.println(result);

	}

}
