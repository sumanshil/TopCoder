package com.careercup.amazon;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureTest
{

	public static void main(String[] args) throws InterruptedException,
	                                              ExecutionException
	{
		CompletableFuture completableFutureToBeCompleted2 = CompletableFuture.supplyAsync( ( ) -> {
			for( int i = 0; i < 10; i-- )
			{
				System.out.println( "i " + i );
			}
			return 10;
			} );

//		CompletableFuture completor = CompletableFuture.supplyAsync( ( ) -> {
//			System.out.println( "completing the other" );
//			completableFutureToBeCompleted2.complete( 222 );
//			return 10;
//		} );
//		
//		System.out.println( completor.get() );
//		System.out.println( completableFutureToBeCompleted2.get() );		
	}

}
