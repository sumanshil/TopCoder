package com.geeksforgeeks.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
//http://www.geeksforgeeks.org/find-a-tour-that-visits-all-stations/
	
public class PetrolPump {

	 static class Pump{
		public int petrol;
		public int distanceToNextPump;
		public Pump(int petrol, int distanceToNextPump){
			this.petrol = petrol;
			this.distanceToNextPump = distanceToNextPump;
		}
	}
	 
	 public static void solution1(List<Pump> arr){
		 int index =0;
		 int cost=0 ;
		 for(int i = 0 ; i < arr.size() ; i++){
			 cost=0;
			 int j = i;
			 while(true){
				 
				 if (j >= arr.size()){
					 j = arr.size() - j;
				 }
				 cost += arr.get(j).petrol-arr.get(j).distanceToNextPump;
				 if (cost <0){
					 break;
				 }
				 j++;
				 if (j == i){
					 break;
				 }
			 }
			 if (cost > 0){
				 index = i;
                 break;				 
			 }
		 }
		 if(cost > 0){
			 System.out.println("Starting petrol pump index "+index);
		 }
		 
	 }
	 
	 public static void solution2(List<Pump> arr){
		 Queue<Integer> queue = new LinkedList<Integer>();
		 int currentCount = 0;
		 int pendingCount = 0;
		 for(int i = 0 ; i < arr.size() ; i++){
			 currentCount += (arr.get(i).petrol-arr.get(i).distanceToNextPump);
			 if(currentCount<0){
				 pendingCount+=currentCount;
				 currentCount = 0;
				 while(!queue.isEmpty()){
					 queue.remove();
				 }
			 }else{
				 queue.add(i);
			 }
		 }
		 
		 if (currentCount>0){
			 int i = queue.peek();
			 
			 if ((currentCount + pendingCount) >=0){
				 System.out.println("Result "+queue.remove());
			 }
		 }
	 }
	 
	 public void solution3(List<Pump> list){
		 Queue<Integer> q = new LinkedList<Integer>();
		 
		 int start = 0;
		 int length = list.size();
		 int end = start;
		 int cost = 0; 
		 while((start)%length != end){
			 cost+=list.get(end).petrol -list.get(end).distanceToNextPump;
			 if (cost < 0 ){
				 while(!q.isEmpty()){
					 q.remove();
				 }
				 start = (start+1)%length;
				 end = start;
			 }else{
				 q.add(end);
				 end = end+1;
			 }
			 
		 }
		 
		 
	 }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
        List<Pump> list = new ArrayList<Pump>(); 
//        list.add(new Pump(4, 6));
//        list.add(new Pump(6, 5));
//        list.add(new Pump(7, 3));
//        list.add(new Pump(4, 5));
        
        list.add(new Pump(3, 2));
        list.add(new Pump(2, 12));
        list.add(new Pump(10, 1));
        list.add(new Pump(1, 1));
        
        solution2(list);
	}

}
