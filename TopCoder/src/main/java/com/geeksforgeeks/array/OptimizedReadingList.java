package com.geeksforgeeks.array;
///http://www.geeksforgeeks.org/optimal-read-list-given-number-days/
public class OptimizedReadingList
{
    private int average = 0;
    private int min = Integer.MAX_VALUE;
    int[] result = null;
    
    public int[] findOptimized(int noOfDaysToFinished,
                              int[] pages)
    {
        
        int sum = 0;
        for (int i = 0 ; i < pages.length ; i++ )
        {
            sum += pages[i];
        }
        average = sum / pages.length;        
        findAllCombinations(noOfDaysToFinished,
                            pages.length,
                            pages);
        return result;
    }
    
    
    private void findAllCombinations(int noOfDaysToFinished,
                                     int noOfChapters,
                                     int[] pages)
    {
        int[] arr = new int[noOfDaysToFinished];
        findRecursive(noOfDaysToFinished,
                      noOfChapters,
                      0,
                      arr,
                      0,
                      pages);
    }
    
    
    private void findRecursive(int noOfDaysToFinished,
                               int noOfChapters,
                               int pos,
                               int[] arr,
                               int sumSoFar,
                               int[] pages)
    {
        if ( sumSoFar > noOfChapters )
        {
            return;
        }
        if ( pos == arr.length)
        {
            if (sumSoFar == noOfChapters)
            {
                for(int i = 0 ; i < arr.length ; i++)
                {
                    System.out.print(arr[i]);
                }
                System.out.println();
                checkForResult(arr,
                               pages);
            }            
            return;
        }
        
        for(int i = 1 ; i < noOfChapters ; i++)
        {
            arr[pos] = i;
            findRecursive(noOfDaysToFinished,
                          noOfChapters,
                          pos+1,
                          arr,
                          sumSoFar+i,
                          pages);
        }
        
    }
    
    private void checkForResult(int[] arr, int[] pages)
    {
        int startChapter = 0;
        int finalSum = 0;
        for ( int i = 0 ; i < arr.length ; i++ )
        {
            int maxChapterCanBeRead = arr[i];
            int sum = 0;
            for( int chapterIndex = startChapter ;
                 chapterIndex < startChapter+maxChapterCanBeRead ;
                 chapterIndex++)
            {
                sum += pages[chapterIndex];
            }
            finalSum += Math.abs(average-sum);
            startChapter = maxChapterCanBeRead;
        }
        if (finalSum < min)
        {
            result = new int[arr.length];
            for ( int i = 0 ; i < arr.length ; i++ )
            {
                result[i] = arr[i];
            }
            min = finalSum;
        }            
    }


    public static void main(String[] args)
    {
        int[] arr = {8, 5, 6, 12};
        int noOfDays = 3;
        int[] result = new OptimizedReadingList().findOptimized(noOfDays, arr);
        for ( int i = 0 ; i < result.length ; i++)
        {
            System.out.println(result[i]);
        }

    }

}
