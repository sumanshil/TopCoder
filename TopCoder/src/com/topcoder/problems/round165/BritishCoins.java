package com.topcoder.problems.round165;
//http://community.topcoder.com/stat?c=problem_statement&pm=1862&rd=4630
public class BritishCoins
{
    public int[] coins(int pence)
    {
        int shillings = pence / 12;
        int penniesLeftOver = pence % 12;
        
        int pounds = shillings / 20;
        int shillingsLeftOver = shillings % 20;
        int totalPenniesLeft = (penniesLeftOver + shillingsLeftOver*12);
        shillings = (totalPenniesLeft)/ 12;
        int pennies = totalPenniesLeft % 12;
        //System.out.println(pounds +"\n"+ shillings+"\n"+ pennies);
        return new int[]{pounds, shillings, pennies};
    }

    public static void main(String[] args)
    {
        new BritishCoins().coins(533);
    }

}
