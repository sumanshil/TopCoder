package com.topcoder.problems.round165;

import java.util.ArrayList;
import java.util.List;

import com.topcoder.problems.round165.Scheduling.Task;

public class TryRecursion
{
    class Task
    {
        int index;
        int time;
        List<Task> dependentTasks;
        int currentActiveDependencis;
        int completionTime;
        Task ( int index,
               List<Task> list,
               int dependencies,
               int time)
        {
            this.index = index;
            this.dependentTasks = list;
            this.currentActiveDependencis = dependencies;
            this.time = time;
        }

        public boolean dependsOn(Task currentRunningTask)
        {
            for ( Task t1 : this.dependentTasks )
            {
                if ( t1.index == currentRunningTask.index )
                {
                    return true;
                }
            }
            return false;
        }
        
    }

    public void tryRecursion(int[] arr)
    {
        boolean[] visited = new boolean[arr.length];
        tryRecursion(arr,
                     visited,
                     0,
                     0,
                     0,
                     new int[arr.length],
                     new int[arr.length],
                     0,
                     0);
        
    }

    private void tryRecursion(int[] arr,
                              boolean[] visited,
                              int arrIndex,
                              int index1,
                              int index2,
                              int[] arr1,
                              int[] arr2,
                              int delay1,
                              int delay2)
    {
        if ( allVisited(visited) )
        {
            System.out.println("index1 "+index1);
            for ( int i = 0 ; i < index1 ; i++)
            {
                System.out.print(arr1[i]+" ");
            }
            System.out.println();
            System.out.println("index2 "+index2);
            for ( int i = 0 ; i < index2 ; i++)
            {
                System.out.print(arr2[i]+" ");
            }
            System.out.println();
            System.out.println("=============>");
            return;
        }
        
        for ( int i = 0 ; i < arr.length ; i++ )
        {
            if (!visited[i])
            {
                if ( index1 == index2 )
                {
                    visited[i] = true;
                    arr1[index1] = arr[i];
                    tryRecursion(arr,
                                 visited,
                                 arrIndex,
                                 index1+1,
                                 index2,
                                 arr1,
                                 arr2,
                                 delay1,
                                 delay2);
                    arr2[index2] = arr[i];
                    tryRecursion(arr,
                            visited,
                            arrIndex,
                            index1,
                            index2+1,
                            arr1,
                            arr2,
                            delay1,
                            delay2);
                    visited[i] = false;
                }   
            }
        }
        
    }

    private boolean allVisited(boolean[] visited)
    {
        // TODO Auto-generated method stub
        return false;
    }

    private int min = Integer.MAX_VALUE;
    public int fastest(String[] dag)
    {
        Task[] tasks = parseAndTransform(dag);
        solveRecursive(tasks,
                       new boolean[tasks.length],// visited?
                       0,// index1
                       0,// index2
                       new Task[tasks.length],// cpu1
                       new Task[tasks.length],// cpu2
                       0, //delay1
                       0); //delay2
        return min;
    }
    
    
    private void solveRecursive(Task[] tasks,
                                boolean[] visited,
                                int cpu1Index,
                                int cpu2Index,
                                Task[] cpu1,
                                Task[] cpu2,
                                int delay1,
                                int delay2)
    {
        if ( allTasksFinished(visited) )
        {
            int sum1 = 0;
            for ( int i = 0 ; i < cpu1Index ; i++ )
            {
                sum1 += cpu1[i].time;
                System.out.print(cpu1[i].index+" ");
            }
            System.out.println("("+sum1+")");
            System.out.println();
            
            int sum2 = 0;
            for ( int i = 0 ; i < cpu2Index ; i++ )
            {
                sum2 += cpu2[i].time;
                System.out.print(cpu2[i].index+" ");
            }
            System.out.println("("+sum2+")");
            System.out.println("\n===============");
            int max = sum1 > sum2 ? sum1 : sum2;
            if ( max < this.min )
            {
                this.min = max;
            }            
            return;
        }
        for ( Task t : tasks )
        {
            if ( isEligibleToRun(t, visited) )
            {                
                if ( delay1 == delay2)
                {
                     visited[t.index] = true; 
                     cpu1[cpu1Index] = t;
                     solveRecursive(tasks,
                                    visited,
                                    cpu1Index+1,
                                    cpu2Index,
                                    cpu1,
                                    cpu2,
                                    delay1+t.time,
                                    delay2);
                     cpu2[cpu2Index] = t;
                     solveRecursive(tasks,
                                    visited,
                                    cpu1Index,
                                    cpu2Index+1,
                                    cpu1,
                                    cpu2,
                                    delay1,
                                    delay2+t.time);                     
                     visited[t.index] = false;
                }
                else if ( delay1 > delay2 )
                {
                    Task currentRunningTask = cpu1[cpu1Index-1];
                    if (!t.dependsOn(currentRunningTask))
                    {
                        visited[t.index] = true; 
                        cpu1[cpu1Index] = t;
                        solveRecursive(tasks,
                                       visited,
                                       cpu1Index+1,
                                       cpu2Index,
                                       cpu1,
                                       cpu2,
                                       delay1+t.time,
                                       delay2);
                        visited[t.index] = false;
                        Task t1 = cpu1[cpu2Index];
                        if (!t.dependsOn(t1))
                        {
                            visited[t.index] = true; 
                            cpu2[cpu2Index] = t;
                            solveRecursive(tasks,
                                           visited,
                                           cpu1Index,
                                           cpu2Index+1,
                                           cpu1,
                                           cpu2,
                                           delay1,
                                           delay2+t.time);
                            visited[t.index] = false;
                        }
                                                
                    }
                    else
                    {
                        visited[t.index] = true; 
                        cpu1[cpu1Index] = t;
                        solveRecursive(tasks,
                                       visited,
                                       cpu1Index+1,
                                       cpu2Index,
                                       cpu1,
                                       cpu2,
                                       delay1+t.time,
                                       delay2);
                        visited[t.index] = false;
                    }
                }
                else if ( delay2 > delay1 )
                {
                    Task currentRunningTask = cpu2[cpu2Index-1];
                    if (!t.dependsOn(currentRunningTask))
                    {
                        visited[t.index] = true; 
                        cpu1[cpu1Index] = t;
                        solveRecursive(tasks,
                                       visited,
                                       cpu1Index+1,
                                       cpu2Index,
                                       cpu1,
                                       cpu2,
                                       delay1+t.time,
                                       delay2);
                        Task t1 = cpu1[cpu2Index];
                        visited[t.index] = false;
                        if (!t.dependsOn(t1))
                        {
                            visited[t.index] = true;
                            cpu2[cpu2Index] = t;
                            solveRecursive(tasks,
                                           visited,
                                           cpu1Index,
                                           cpu2Index+1,
                                           cpu1,
                                           cpu2,
                                           delay1,
                                           delay2+t.time);
                            visited[t.index] = false; 
                        }
                                               
                    }
                    else
                    {
                        visited[t.index] = true;
                        cpu2[cpu2Index] = t;
                        solveRecursive(tasks,
                                       visited,
                                       cpu1Index,
                                       cpu2Index+1,
                                       cpu1,
                                       cpu2,
                                       delay1,
                                       delay2+t.time);                     
                        visited[t.index] = false;                                                
                    }
                }
                
            }
        }
    }

    private boolean isEligibleToRun(Task t, boolean[] visited)
    {
        if ( visited[t.index] )
            return false;
        
        for ( Task t1 : t.dependentTasks )
        {
            if (!visited[t1.index])
                return false;
        }
        return true;
    }

    private Task[] parseAndTransform(String[] dag)
    {
        int index = 0;
        Task[] retVal = new Task[dag.length];
        for ( String str : dag )
        {
            String[] arr = str.split(":");
            int time  = Integer.parseInt(arr[0]);
            List<Task> dependencies = new ArrayList<Task>();
            if ( arr.length > 1 )
            {
                String[] dependenciesArr = arr[1].split(",");
                for ( int j = 0 ; j < dependenciesArr.length ; j++ )
                {
                    int dependency = Integer.parseInt(dependenciesArr[j]);
                    dependencies.add(retVal[dependency]);
                }
            }
            
            retVal[index] = new Task(index,
                                     dependencies,
                                     dependencies == null ? 0 : dependencies.size(),
                                     time);
            index++;
        }
        return retVal;
    }

    private boolean allTasksFinished(boolean[] visited)
    {
        for ( int i = 0 ; i < visited.length ; i++ )
        {
            if ( !visited[i] )
            {
                return false; 
            }
        }
        return true;
    }

    public static void main(String[] args)
    {
        int result = new TryRecursion().fastest(new String[]{ "10:", "5:", "5:1", "5:1", "5:2,3" });        
        System.out.println(result);
    }

}
