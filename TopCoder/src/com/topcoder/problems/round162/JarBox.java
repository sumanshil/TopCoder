package com.topcoder.problems.round162;
//http://community.topcoder.com/stat?c=problem_statement&pm=1586&rd=4615
public class JarBox
{
    public int numJars(int radius, int woodlength)
    {
        int result = Integer.MIN_VALUE;
        
        for(int ballCount = 1;; ballCount++)
        {
            int width = 2*radius*ballCount;
            int height = 2*radius;
            if (2*(width+height) <= woodlength)
            {
                result = Math.max(result, ballCount);
            }
            else
            {
                break;
            }
        }
        for(int ballCount = 2 ; ; ballCount++)
        {
            int count1 = executeAllRowsEvenNumber(ballCount,
                                                  2*radius*ballCount+radius,
                                                  woodlength,
                                                  radius);
            if (count1 == 0)
                break;
            else
            {
                result = Math.max(result, count1);
            }
        }

        for(int ballCount = 2 ; ; ballCount++)
        {

            int count2 = executeEvenAndOddRowDiff(ballCount,
                                                  2*radius*ballCount,
                                                  woodlength,
                                                  radius);
            if (count2 == 0)
                break;
            else
            {
                result = Math.max(result, count2);
            }
        }
        return result;
    }
    
    
    private int executeEvenAndOddRowDiff(int ballCount,
                                         int width,
                                         int woodLength,
                                         int radius)
    {
        boolean odd = false;
        int totalBallCount = ballCount + (--ballCount);
        int lineCount = 2;
        double height = Math.sqrt(3)*radius*(lineCount-1)+(2*radius);
        int result = 0;
        while(2*(height+width)<= woodLength)
        {
            result = totalBallCount;
            if (!odd)
            {
                ballCount++;                
            }
            else
            {
                ballCount--;                
            }
            totalBallCount += ballCount;
            odd = !odd;
            lineCount++;
            height = Math.sqrt(3)*radius*(lineCount-1)+(2*radius);
        }
        return result;
    }
  
    
    private int executeAllRowsEvenNumber(int ballCount,
                                         int width,
                                         int woodLength,
                                         int radius)
    {
        int lineCount = 2;
        double height = Math.sqrt(3)*radius*(lineCount-1)+(2*radius);
        int totalBallCount = 2*ballCount;
        
        int result = 0;
        while(2*(height+width) <= woodLength)
        {
            result = totalBallCount;
            lineCount++;
            totalBallCount += ballCount;
            height = Math.sqrt(3)*radius*(lineCount-1)+(2*radius);
        }
        return result;
    }
    
    
    public static void main(String[] args)
    {
        int result = new JarBox().numJars(3, 5506);
        System.out.println(result);

    }

}
