package com.topcoder.problems.round30;

public class MuseumTour {
	public int mostImpressiveTour(int[] artPerRoom){
		int[] leftSum = new int[artPerRoom.length];
		leftSum[0] = artPerRoom[0];
		for(int i = 1 ; i < artPerRoom.length ; i++){
			int x = artPerRoom[i];
			for(int j = 0 ; j< i ; j++){
				if (artPerRoom[j] < x && leftSum[j]+x > leftSum[i]){
					leftSum[i] = leftSum[j]+x;
				}
			}
		}
		int max = leftSum[0];
		for(int i = 1; i < leftSum.length ; i++){
			if (leftSum[i] > max){
				max = leftSum[i];
			}
		}
		return max;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
        //int[] arr ={1, 2, 3};
		//int[] arr = {3, 2, 1};
		//int[] arr = {100, 1, 2, 3, 4, 5};
		//int[] arr = 	{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40};
		//int[] arr = 	{50, 60, 2};
		//int[] arr = 	{10, 20, 30, 40, 50}	;
		//int[] arr = {1, 100, 50, 62}	;
		//int[] arr = {1, 2, 1, 2, 3, 1, 2, 3, 4, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6, 7, 1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 50};
		//int[] arr = {50, 100, 60, 70, 80, 2, 1, 2, 1, 2, 1, 2, 3, 4, 5, 6, 5, 4, 3, 2, 1, 2, 3, 4, 102, 1, 2, 3, 4, 3, 2, 60, 50, 3, 10, 1, 1, 1, 2, 2, 2, 3, 4, 4, 5, 5, 6, 6, 6, 6};
		//int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30};
		//int[] arr = 	{1, 2, 3, 4, 5, 4, 3, 2, 1, 2, 3, 4, 5, 1, 12, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 22, 33, 44, 55, 66, 77, 88, 99, 1, 2, 3, 7, 8, 9, 54, 239, 43, 6};
		//int[] arr = 	{1, 101, 2, 102, 3, 103, 4, 104, 5, 105, 200, 106, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92};
		//int[] arr = 	{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50};
		int[] arr = 	{1, 2, 3, 4, 5, 4, 3, 2, 1, 2, 3, 4, 5, 1, 12, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 22, 33, 44, 55, 66, 77, 88, 99, 1, 2, 3, 7, 8, 9, 54, 239, 43, 6};
        int result = new MuseumTour().mostImpressiveTour(arr);
        System.out.println(result);
	}

}
