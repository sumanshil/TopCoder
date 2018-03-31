package com.topcoder.problems.round156;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
//http://community.topcoder.com/stat?c=problem_statement&pm=1777&rd=4585
public class DiskSpace
{
    static class Disk implements Comparable<Disk>
    {
        int used;
        int total;
        int free;
        public Disk(int used,
                    int total)
        {
            this.used = used;
            this.total = total;
            this.free = this.total - this.used;
        }
        
        public int compareTo(Disk otherDisk)
        {
            if (otherDisk.free < this.free)
              return -1;
            else if (otherDisk.free > this.free)
              return 1;
            
            return 0;
        }
        
        public String toString()
        {
            return "( "+this.used +" : "+ this.free+" )";
        }

        public void setUsed(int used)
        {            
            this.used = used;
            this.free = this.total - this.used;
        }
        
        public boolean hasData()
        {
            return this.used > 0;
        }
    }
    
    public  int minDrives(int[] used, int[] total)
    {
        int totalUsed = 0;
        for(int i = 0 ; i < used.length ; i++)
        {
            totalUsed += used[i];
        }
        
        Arrays.sort(total);
        
        for(int i = total.length-1 ; i >=0 ; i--)
        {
            totalUsed -= total[i];
            if (totalUsed <= 0)
                return total.length-i;
        }
        
        return total.length;
        
    }
    
    
    public  int minDrivesN(int[] used, int[] total)
    {
        List<Disk> list = new ArrayList<Disk>();
        for(int i = 0 ; i < used.length ; i++)
        {
            list.add(new Disk(used[i], total[i]));                        
        }
        Collections.sort(list);
        
        
        for(Disk d : list)
        {
            System.out.println(d);
        }
        
        int end = list.size()-1;
        
        int start = 0;
        
        while(end > start)
        {
            int currentUsed = list.get(end).used;
            int l = start;
            while(currentUsed > 0 && l < end)
            {
                int free = list.get(l).free;
                if (free > 0)
                {
                    if (free <= currentUsed)
                    {
                        list.get(l).setUsed(list.get(l).total);
                        currentUsed = currentUsed-free;
                    }
                    else
                    {
                        list.get(l).setUsed(list.get(l).used+currentUsed);
                        currentUsed = 0;
                    }
                    
                    list.get(end).setUsed(currentUsed);
                }
                l++;
            }
            if (currentUsed > 0)
            {
                break;
            }
            else
            {
                end--;
            }
        }
            
        int count = 0;
        
        for(Disk disk : list)
        {
            if (disk.hasData())
            {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args)
    {
        int[] used = {396, 224, 734, 57, 559, 375, 653, 449, 120, 23, 154, 551, 55, 714, 161, 359, 198, 198, 84, 373, 221, 189, 243, 855, 346, 518, 635, 277, 152, 204, 498, 810, 389, 14, 193, 797, 143, 96};
        int[] total = {550, 285, 818, 873, 608, 614, 958, 984, 157, 365, 602, 994, 849, 724, 358, 368, 489, 922, 538, 693, 568, 743, 748, 943, 679, 640, 637, 806, 548, 495, 766, 940, 866, 945, 565, 824, 526, 285};
        int result = new DiskSpace().minDrives(used, total);
        System.out.println(result);

    }
}
