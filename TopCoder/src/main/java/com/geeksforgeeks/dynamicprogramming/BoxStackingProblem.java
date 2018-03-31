package com.geeksforgeeks.dynamicprogramming;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//http://www.geeksforgeeks.org/dynamic-programming-set-21-box-stacking-problem/
public class BoxStackingProblem {

    static class Box implements Comparable<Box>{

        int height;
        int width;
        int depth;
        public int compareTo(Box other) {
            if (other != null){
                if ((other.width * other.depth)>(this.width*this.depth)){
                    return -1;
                } else if ((other.width * other.depth)==(this.width*this.depth)){
                    
                } else {
                    return 1;
                }
                
            }
            return 0;
        }
        
        public Box(int h, int w, int d){
            this.height = h;
            this.width = w;
            this.depth = d;
        }
        
        public String toString(){
            StringBuffer sb = new StringBuffer();
            sb.append("Height "+this.height);
            sb.append(" Width "+this.width);
            sb.append(" Depth "+this.depth);
            return sb.toString();
        }
    }
    
    public void find(List<Box> list){
        //Collections.sort(list);
        List<Box> list1 = new ArrayList<Box>(3*list.size());
        for(Box box : list){
            list1.add(box);
            
            Box box1 = new Box(box.depth, box.height, box.width);
            list1.add(box1);
            
            box1 = new Box(box.width, box.depth, box.height);
            list1.add(box1);
        }
        Collections.sort(list1);
        
        System.out.println("Sorted List");
        for(Box box : list1){
            System.out.println(box);
        }
        
        int[] msh = new int[list.size()*3];
        int i = 0;
        for(Box b : list1){
            msh[i] = b.height;
            i++;
        }

        for(i = 1 ; i< list1.size(); i++){
            for(int j = 0 ; j < i ; j++){
                if (list1.get(j).width < list1.get(i).width
                    &&  list1.get(j).depth < list1.get(i).depth
                    && msh[i] < (msh[j]+list1.get(i).height) ){
                    msh[i] = msh[j]+list1.get(i).height;
                }
            }
        }
        
        int max = Integer.MIN_VALUE;
        for(int j = 0 ; j < msh.length ; j++){
            if (msh[j]> max){
                max = msh[j];
            }
        }
        System.out.println("Maximum height "+ max);
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
       List<Box> list = new ArrayList<Box>();
       list.add(new Box(4,6,7));
       list.add(new Box(1,2,3));
       list.add(new Box(4,5,6));
       list.add(new Box(10,12,32));
       
       new BoxStackingProblem().find(list);
    }

}
