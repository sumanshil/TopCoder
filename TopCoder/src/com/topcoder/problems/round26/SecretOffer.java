package com.topcoder.problems.round26;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

//http://community.topcoder.com/stat?c=problem_statement&pm=152&rd=3025
public class SecretOffer {

	static class X{
		int number;
		int index;
		List<Integer> list;
		public X(int number, int index, List<Integer> list){
		    this.list = list;
		    this.number = number;
		    this.index = index;		    
		}
	}
	String[] numbersString = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
	public long translate1(String message){
		message = message.toLowerCase();
		int[] arr= new int[message.length()];		
		for(int i = 0 ; i < arr.length ; i++){
			arr[i]= -1;
		}		
		message = message.toLowerCase();
		int[] marker = new int[message.length()];
		Queue<Integer> queue = new LinkedList<Integer>();
		for(int i = 0 ; i < message.length() ; i++){
			queue.add(i);
		}
		Map<Integer, List<Integer>> indexMap = new HashMap<Integer, List<Integer>>();
		while(!queue.isEmpty()){			
			int i = queue.peek();
			System.out.println(i);
			if (marker[i] == 1)
				continue;
			for(int k = 0 ; k < numbersString.length ; k++ ){
				System.out.println(k);
				int index = i;
			//	str1 = str1.toLowerCase();
				String str = numbersString[k].toLowerCase();
				if (str.charAt(0) != message.charAt(i))
					continue;
				int count = 0;
				List<Integer> messageIndexList = new LinkedList<Integer>();
				for(int j = 0 ; j < str.length() && index < message.length() ; ){			
					if (str.charAt(j) == message.charAt(index)){
						if (j < str.length()-1){
							count++;
							messageIndexList.add(index);
							j++;
							index++;
						} else if (j == str.length()-1){
							count++;
							j++;
							messageIndexList.add(index);
							index++;
							break;
						} else if (j < str.length()-1 && marker[j] == 1){
							j++;
						} else {
							j++;
						}
					} else {
						index++;
					}
					
				}
				int lastIndex = 0;
				boolean matchFound = false;
				if (count == str.length()){					
					for(Integer intx : messageIndexList){
						marker[intx] = 1;
						lastIndex = intx;
						matchFound = true;
					}
				} else if (count < str.length()){
					for(Integer intx : messageIndexList){
						marker[intx] = 0;
					}
				}
				
				if (matchFound && arr[lastIndex] != -1){
					// Need to do some more work here
					int old = arr[lastIndex];
					if (old > k){
						arr[lastIndex] = k;
						char c1 = message.charAt(lastIndex);
				        while(lastIndex < message.length()){
				        	if (message.charAt(lastIndex) == c1 && arr[lastIndex] == -1){
				        		arr[lastIndex] = old;
				        		setMarker(marker, messageIndexList);
				        	} else if (message.charAt(lastIndex) == c1 && arr[lastIndex] > old){
				        		int n = arr[lastIndex];
				        		arr[lastIndex] = old;
				        		List<Integer> oldIndexList = indexMap.get(lastIndex);
				        		unsetMarker(marker, oldIndexList);
				        		setMarker(marker,messageIndexList);
				        		messageIndexList = oldIndexList;
				        		old = n;
				        	}
				        	lastIndex++;
				        }
					}
				} else if (matchFound) {
					arr[lastIndex] = k;
					indexMap.put(index, messageIndexList);
				}				
			}
		}				
		
		int result = 0;
		for(int i = 0 ; i < arr.length ; i++){
			if (arr[i] != -1){
				result = (result * 10 )+ arr[i];
			}
		}
		return result;
	}
	
	public long translate(String message){
		int[] arr= new int[message.length()];
		for(int i = 0 ; i< arr.length ; i++){
			arr[i] = -1;
		}
		message = message.toLowerCase();
		Map<Integer, X> map = new HashMap<Integer, X>();
		
		int[] marker = new int[message.length()];
		Queue<Integer> queue = new LinkedList<Integer>();
		for(int i = 0 ; i < message.length() ; i++){
			queue.add(i);
		}
        int[] visitCount = new int[message.length()];
		while(!queue.isEmpty()){
			int i = queue.poll();
		//	System.out.println(i);
			if (marker[i] == 1)
				continue;
			for(int k = 0 ; k < numbersString.length ; k++ ){
			//	System.out.println(k);
				int index = i;
			//	str1 = str1.toLowerCase();
				String str = numbersString[k].toLowerCase();
				if (str.charAt(0) != message.charAt(i))
					continue;
				int count = 0;
				List<Integer> messageIndexList = new LinkedList<Integer>();
				for(int j = 0 ; j < str.length() && index < message.length() ; ){			
					if (str.charAt(j) == message.charAt(index)){
						if (visitCount[i] == 0){
							count++;
							messageIndexList.add(index);
							j++;
							index++;
						} else if (visitCount[i] > 0 && marker[index] == 0){
							count++;
							messageIndexList.add(index);
							j++;
							index++;							
						} else {
							index++;
						}
					} else {
						index++;
					}					
				}
				int lastIndex = 0;
				boolean matchFound = false;
				if (count == str.length()){					
					for(Integer intx : messageIndexList){
						//marker[intx] = 1;
						lastIndex = intx;
						matchFound = true;
					}
				} else if (count < str.length()){
					for(Integer intx : messageIndexList){
						//marker[intx] = 0;
					}
				}	
				
				if (matchFound){
					if (arr[lastIndex]== -1){
						// we can safe put it at lastIndex
						// first we need to check if we have to dethrone some of the candidates
						X x = new X(k, lastIndex, messageIndexList);
			            for(Integer intx : messageIndexList){
			            	if (map.containsKey(intx)){
                                 X old = map.get(intx);
                                 List<Integer> list = old.list;
                                 // mark the indexes as free
                                 for(int inty  : list){
                                	 if (inty < intx){
                                		 queue.add(inty);
                                		 visitCount[inty]++;
                                	 }
                            		 marker[inty] = 0;
                                 }
                                 // 
                                 arr[old.index] = -1;
			            	}
			            	marker[intx] = 1;
			            	map.put(intx, x);
			            }		
			            
			            arr[lastIndex] = k;
					} else {
						int oldVal = arr[lastIndex];
						if (oldVal > k){
							// we can safe put it at lastIndex
							// first we need to check if we have to dethrone some of the candidates
							X x = new X(k, lastIndex, messageIndexList);
				            for(Integer intx : messageIndexList){
				            	if (map.containsKey(intx) && map.get(intx) != null){
	                                 X old = map.get(intx);
	                                 List<Integer> list = old.list;
	                                 // mark the indexes as free
	                                 for(int inty  : list){
	                                	 if (inty < intx){
	                                		 queue.add(inty);
	                                	 }
	                            		 marker[inty] = 0;
	                            		 map.put(inty, null);
	                                 }
	                                 // 
	                                 arr[old.index] = -1;
				            	}
				            	marker[intx] = 1;
				            	map.put(intx, x);
				            }						            
				            arr[lastIndex] = k;							
						}
					}
				}
			}
		}
		int result = 0;
		for(int i = 0 ; i < arr.length ; i++){
			if (arr[i] != -1){
				result = (result * 10 )+ arr[i];
			}
		}
		return result;

	}
	
	private void print(int[][] matrix, int row, int column) {
		for(int i = 0 ; i < row ; i++){
			for(int j = 0 ; j < column ; j++){
				System.out.print(matrix[i][j]+" ");
			}
			System.out.println();
		}
		
	}
	
	private void setMarker(int[] marker, List<Integer> list){
		for(Integer i : list){
			marker[i] = 1;
		}
	}

	private void unsetMarker(int[] marker, List<Integer> list){
		for(Integer i : list){
			marker[i] = 0;
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String message = "The Final Fantasy movie is now in theaters";
		//String message = "txwyoznkep";
		//String message = "fisivex";
		 //String message = "neightine";
		//String message = "The Final Fantasy movie is now in theaters";
        long result = new SecretOffer().translate(message);
        System.out.println(result);
	}

}
