package com.topcoder.problems.round156;
//http://community.topcoder.com/stat?c=problem_solution&cr=120816&rd=4585&pm=1788
public class SmartElevator
{
    int npeople = 0;
    int best = Integer.MAX_VALUE;
    int[] used = new int[5];
    
    public int timeWaiting(int[] arrivalTime,
                           int[] startingFloor,
                           int[] destinationFloor)
    {
        npeople = arrivalTime.length;
        search(0,1,0,arrivalTime, startingFloor, destinationFloor);
        return best;
        
    }

    private void search(int timeCompleted,
                        int startTime,
                        int peopleDropped,
                        int[] arrivalTime,
                        int[] startingFloor,
                        int[] destinationFloor)
    {
        if (peopleDropped == arrivalTime.length)
        {
            if (best > timeCompleted)
            {
                best = timeCompleted;
            }
            return;
        }
        
        // people need to be picked up
        for(int i =0 ; i < npeople; i++)
        {
            if(used[i] == 0)
            {
                used[i] = 1;
                search(Math.max(arrivalTime[i], timeCompleted+ Math.abs(startTime-startingFloor[i])),
                       startingFloor[i],
                       peopleDropped,
                       arrivalTime,
                       startingFloor,
                       destinationFloor);
                used[i] = 0;
            }
        }
        
        // people need to be dropped off
        for(int i =0 ; i < npeople; i++)
        {
            if(used[i] == 1)
            {
                used[i] = 2;
                search(timeCompleted+ Math.abs(startTime-destinationFloor[i]),
                       destinationFloor[i],
                       peopleDropped+1,
                       arrivalTime,
                       startingFloor,
                       destinationFloor);
                used[i] = 1;
            }
        }
    }
    
    public static void main(String[] args)
    {
        int[] arrivalTime = {1000,1200,1600,2000,2400};
        int[] start  = {500,500,500,500,500};
        int[] dest = {700,300,700,300,700};        
                
        int result = new SmartElevator().timeWaiting(arrivalTime, start, dest);
        System.out.println(result);
    }
}