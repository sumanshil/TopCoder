package com.topcoder.problems.round172;

/**
 * Created by sshil on 6/24/17.
 */
public class Fifteen1 {
    public boolean win( int[] p2 ) {
        for ( int i = 0; i < p2.length; i++ )
            for ( int j = i + 1; j < p2.length; j++ )
                for ( int k = j + 1; k < p2.length; k++ )
                    if ( p2[i] + p2[j] + p2[k] == 15 && p2[i] > 0 && p2[j] > 0 && p2[k] > 0 )
                        return true;
        return false;
    }

    public boolean go( boolean[] done, int[] p1, int[] p2 ) {
    /* Can p2 win? */
        if ( win( p2 ) )
            return true;

        boolean boo = false;
        for ( int i = 1; i <= 9; i++ )
            if ( !done[i] ) {
                done[i] = true;
                int j;
                for ( j = 0; j < 5; j++ ) if ( p1[j] == 0 ) { p1[j] = i; break; }
                boo |= go ( done, p2, p1 );
                p1[j] = 0;
                done[i] = false;
            }
        return !boo;
    }

    public String outcome(int[] moves) {
        boolean done[] = new boolean[ 15 ];
        int player[] = new int[5];
        int dealer[] = new int[5];

        for ( int i = 0; i < moves.length; i++ ) done[moves[i]] = true;
        for ( int i = 0; i < moves.length; i++ )
            if ( i % 2 == 0 )
                dealer[i/2] = moves[i];
            else
                player[i/2] = moves[i];

        for ( int i = 1; i <= 9; i++ )
            if ( !done[i] ) {
                done[i] = true;
                int j;
                for ( j = 0; j < 5; j++ ) if ( player[j] == 0 ) { player[j] = i; break; }
                if ( go( done, dealer, player ) ) {
                    return "WIN " + i;
                }
                player[j] = 0;
                done[i] = false;
            }
        return "LOSE";
    }

    public static void main(String[] args) {

        int[] arr = {6, 3, 7, 8, 1};
        String result = new Fifteen1().outcome(arr);
        System.out.println(result);
    }

}
