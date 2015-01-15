package com.topcoder.problems.round145;
//http://community.topcoder.com/stat?c=problem_statement&pm=1130&rd=4530
public class VendingMachine 
{
     class Column
     {
    	 int[] shelfs;
    	 public Column(int length)
    	 {
    		 shelfs = new int[length];
    	 }
    	 
    	 public void setValue(int shelfNo,
    			              int value)
    	 {
    		 shelfs[shelfNo] = value;
    	 }
    	 
    	 public int getValue(int shelfNo)
    	 {
    		 return shelfs[shelfNo];
    	 }
    	 
    	 public int getSum()
    	 {
    		 int sum = 0;
    		 for(int i = 0 ; i < shelfs.length ; i++)
    		 {
    			 sum += shelfs[i];
    		 }
    		 return sum;
    	 }
     }
     
     public int motorUse(String[] prices, String[] purchases)
     {
    	 int shelfLength  = prices.length;
    	 
    	 String[] arr1    = prices[0].split("\\s+");
    	 int columnLength = arr1.length;
    	 Column[] columns = new Column[columnLength];
    	 for(int i = 0 ; i < columns.length ; i++)
    	 {
    		 columns[i] = new Column(shelfLength);
    	 }
    	 int index = 0;
    	 for(String price : prices)
    	 {
    		 String[] arr = price.split("\\s+");
    		 for(int j = 0 ; j < arr.length ; j++)
    		 {
    			 columns[j].setValue(index, Integer.parseInt(arr[j]));
    		 }
    		 index++;
    	 }
    	 
    	 int result = 0; // contains rotational value;
    	 int currentMaxColumnIndex = getMaxIndex(columns);
    	 int minMove               = calculateMinMove(0,
    			                                      currentMaxColumnIndex,
    			                                      columns.length);
    	 result += minMove;
    	 int currentColumn = currentMaxColumnIndex;
    	 for(int i = 0 ; i < purchases.length ; i++)
    	 {
    		 String[]   arr = purchases[i].split(":");
    		 String  second = arr[1];
    		 int       time = Integer.parseInt(second);
    		 String[]  arr2 = arr[0].split(",");
    		 int      shelf = Integer.parseInt(arr2[0]);
    		 int     column = Integer.parseInt(arr2[1]);
    		 if (columns[column].getValue(shelf) == 0)
    		 {
    			 // INVALID PURCHASE
    			 return -1;
    		 }
    		 columns[column].setValue(shelf, 0);
    		 if (column != currentColumn)
    		 {
		    	       minMove = calculateMinMove(currentColumn,
                                                  column,
                                                  columns.length);
		    	        result += minMove;
		    	 currentColumn = column;

    		 }
    		 if( (i+1) < purchases.length)
    		 {
    			 int nextPurchaseTime = getNextPurchaseTime(purchases[i+1]);
    			 if (nextPurchaseTime - time >= 5)
    			 {
    				 currentMaxColumnIndex = getMaxIndex(columns);
    		    	 minMove               = calculateMinMove(currentColumn,
    		    			                                  currentMaxColumnIndex,
    		    			                                  columns.length);
    		    	 result                += minMove;
    		    	 currentColumn         = currentMaxColumnIndex;
    			 }
    			 else
    			 {
    				 currentColumn = column;
    			 }
    		 }    		     		 
    		 else
    		 {
    			 currentColumn = column;
    		 }
    	 }
		 currentMaxColumnIndex = getMaxIndex(columns);
    	               minMove = calculateMinMove(currentColumn,
    			                                  currentMaxColumnIndex,
    			                                  columns.length);
    	 result += minMove;    	
    	 return result;
     }
     
    
     private int getNextPurchaseTime(String time)
     {
         String[] arr   = time.split(":");
		 String  second = arr[1];
		 int     retVal = Integer.parseInt(second);
		 return retVal;
     }
    
    
	private int calculateMinMove(int currentIndex,
			                     int currentMaxColumnIndex,
			                     int length)
	{
        int move1 = Math.abs(currentMaxColumnIndex - currentIndex);
		int move2 = length - move1;
	    return Math.min(move1, move2);
	}

	private int getMaxIndex(Column[] columns)
	{
        int max    = Integer.MIN_VALUE;	    
        int retVal = 0;
        int index  = 0;
        for(Column col : columns)
        {
        	if (col.getSum() > max)
        	{
        		retVal = index;
        		max    = col.getSum();
        	}
        	index++;
        }
        return retVal;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		int result = new VendingMachine().motorUse(new String[]{"368 7960 8282 593 303 95 13 1230", "74 370 134 6316 8 535 61 2435", "9240 43 215 8853 37 42 92 6403", "482 687 508 9602 4345 5152 5624 3113", "70 5 6193 8 94 1323 5171 4776", "4237 7 7 1 636 736 1 8441", "516 2773 33 4772 1740 57 156 2800", "4739 35 99 5050 459 2 9104 2", "12 9029 5 91 95 7039 5 2", "6 1 83 3062 864 665 8500 1268", "7922 8347 58 1352 3753 133 4548 6", "2051 1 583 1399 8354 17 347 3115", "1094 5178 774 1 63 49 2 90", "966 8090 1 8467 356 148 7906 27", "9832 4 4 892 9128 34 5034 429", "5 5257 8949 35 276 1996 93 1", "6657 334 84 33 9908 2388 9332 85", "943 70 153 9488 998 6274 1 9180", "238 133 42 5 889 7576 9446 4254", "1424 9142 6685 58 57 6974 71 8258", "8 2236 63 4039 1 194 788 565", "6953 3 99 487 5 5952 7468 7639", "9846 8012 7705 8 7149 3 94 8608", "7853 5 4935 1296 66 9 4346 2277", "253 6836 89 7534 7304 8 1 70"},
				                                   new String[] {"1,7:7", "2,4:8", "11,4:10", "8,7:18"});
		
		System.out.println(result);

	}

}
