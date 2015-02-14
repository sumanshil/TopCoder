package com.topcoder.problems.round166;

public class MissingLetters
{
    public String getMissingLetters(String sentence)
    {
        int start = 'A';
        StringBuffer sb = new StringBuffer();
        for (int c = start ;  ; c++)
        {
            char c1 = (char)c;
            if (sentence.toUpperCase().indexOf(c1) < 0)
            {
                sb.append((char)c);
            }
            if ((char)c == 'Z')
                break;
        }
        return sb.toString();
    }
    
    
    public static void main(String[] args)
    {
        String str = "Lions, and tigers, and bears, oh my!";
        String result = new MissingLetters().getMissingLetters(str);
        System.out.println(result);

    }

}
