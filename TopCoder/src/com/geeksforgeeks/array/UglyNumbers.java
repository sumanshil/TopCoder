package com.geeksforgeeks.array;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sshil on 1/9/2016.
 */
public class UglyNumbers {

	int[] arr = {2, 3, 5};
	Set<Integer> set = new HashSet<>();
	public void calculate(int limit){
		int count = 0;
		set.add(0);
		set.add(1);
		for (int i = 2 ; ;i++){
			if (count == limit){
				break;
			}
			int remainder = 0;
			int divider = 0;
			for (int j = 0; j < arr.length; j++) {
				if (i >= arr[j]) {
					remainder = i % arr[j];
					divider = i / arr[j];
					if (remainder == 0){
						break;
					}
				}
			}

			if (remainder == 0){
				if (set.contains(divider)){
					count++;
					System.out.println(i);
					set.add(i);
				}
			}
		}
	}


	public static void main(String[] args) {
		new UglyNumbers().calculate(11);
	}
}
