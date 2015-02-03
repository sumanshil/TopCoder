
package com.topcoder.problems.round165;
//
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
//
////http://community.topcoder.com/stat?c=problem_statement&pm=1879&rd=4630
//public class Scheduling
//{
//    class Task
//    {
//        int index;
//        int time;
//        List<Task> dependentTasks;
//        int currentActiveDependencis;
//        
//        Task ( int index,
//               List<Task> list,
//               int dependencies,
//               int time)
//        {
//            this.index = index;
//            this.dependentTasks = list;
//            this.currentActiveDependencis = dependencies;
//            this.time = time;
//        }
//        
//        public boolean isDependent(Task task)
//        {
//            for ( Task t : dependentTasks )
//            {
//                if ( t.index == task.index )
//                {
//                    return true;
//                }
//            }
//            return false;
//        }
//        
//        
//    }
//    
//    class TaskComparator implements Comparator<Task>
//    {
//        @Override
//        public int compare(Task o1, Task o2)
//        {
//            return o1.time > o2.time ? -1 : 1;
//        }
//    }
//    
//    //  "10:","10:"
//    //  "9:", "9:"
//    //  "8:", "7:"
//    //  "6:", "5:"
//    //  "2:", "5:
//    //   2  ,  1
//    
////    public int fastest(String[] dag)
////    {
////        Task[] taskArr= parseAndTransform(dag);
////        boolean[] processedTask = new boolean[taskArr.length];
////        int time1 = 0;
////        int time2 = 0;
////        
////        Task time1_task = null;
////        Task time2_task = null;
////        while(isTaskLeft(processedTask))
////        {
////            List<Task> tasks = getNextTasks(processedTask,
////                                            taskArr);
////            Collections.sort(tasks, new TaskComparator());
////            if (tasks.size() >= 2)
////            {
////                int startIndex = 0;
////                while( startIndex < tasks.size())
////                {
////                    if ( tasks.size() - startIndex >= 2 )
////                    {
////                        Task t1 = tasks.get(startIndex++);
////                        Task t2 = tasks.get(startIndex++);
////                        if ( time1 == time2 )
////                        {
////                            time1 += t1.time;
////                            time2 += t2.time;
////                            time1_task = t1;
////                            time2_task = t2;
////                        }
////                        else if ( time1 < time2 )
////                        {
////                            // time2 is running
////                            // check if longer task is dependent on the running task
////                            if (time2_task.isDependent(t1))
////                            {
////                                time2 += t1.time; 
////                                time1 += t2.time;
////                                time1_task = t2;
////                                time2_task = t1;                                
////                            }
////                            else
////                            {
////                                time2 += t2.time; 
////                                time1 += t1.time;  
////                                time1_task = t1;
////                                time2_task = t2;                                                                
////                            }
////                            
////                        }
////                        else if ( time2 < time1 )
////                        {
////                            // time1_task is running
////                            // check if longer task is dependent on the running task
////                            if (time1_task.isDependent(t1))
////                            {
////                                time1 += t1.time; 
////                                time2 += t2.time;
////                                time1_task = t1;
////                                time2_task = t2;                                
////                            }
////                            else
////                            {
////                                time1 += t2.time; 
////                                time2 += t1.time;  
////                                time1_task = t2;
////                                time2_task = t1;                                                                
////                            }                 
////                        }   
////                        processedTask[t1.index] = true;
////                        for ( Task dependent : t1.dependentTasks)
////                            
////                        {
////                            dependent.currentActiveDependencis--;
////                        }
////                        
////                        processedTask[t2.index] = true;
////                        for ( Task dependent : t2.dependentTasks)
////                            
////                        {
////                            dependent.currentActiveDependencis--;
////                        }
////                    }
////                    else
////                    {
////                        Task t = tasks.get(startIndex++);
////                        if ( time1 == time2 )
////                        {
////                            time1 += t.time;
////                            time1_task = t;
////                        }
////                        else if ( time1 < time2 )
////                        {
////                            // time2_task is running
////                            if ( time2_task.isDependent(t))
////                            {
////                                time2 += t.time;
////                                time2_task = t;
////                            }
////                            else
////                            {
////                                time1 += t.time;
////                                time1_task = t;                                
////                            }
////                            
////                        }
////                        else if ( time2 < time1 )
////                        {
////                            // time1_task is running
////                            if ( time1_task.isDependent(t))
////                            {
////                                time1 += t.time;
////                                time1_task = t;
////                            }
////                            else
////                            {
////                                time2 += t.time;
////                                time2_task = t;                                
////                            }
////                        }
////                        
////                        processedTask[t.index] = true;
////                        for ( Task dependent : t.dependentTasks)
////                            
////                        {
////                            dependent.currentActiveDependencis--;
////                        }
////                    }
////                }
////            }
////            else
////            {
////                for( Task t : tasks)
////                {
////                    if ( time1 == time2 )
////                    {
////                        time1 += t.time;
////                        time1_task = t;
////                    }
////                    else if ( time1 > time2 )
////                    {
////                        //time1 is running
////                        if ( time1_task.isDependent(t))
////                        {
////                            time1 += t.time;
////                            time1_task = t;
////                        }
////                        else
////                        {
////                            time2 += t.time;
////                            time2_task = t;                                
////                        }
////                    }
////                    else 
////                    {
////                        if ( time2_task.isDependent(t))
////                        {
////                            time2 += t.time;
////                            time2_task = t;
////                        }
////                        else
////                        {
////                            time1 += t.time;
////                            time1_task = t;                                
////                        }
////                    }
////                    
////                    processedTask[t.index] = true;
////                    for ( Task dependent : t.dependentTasks)
////                        
////                    {
////                        dependent.currentActiveDependencis--;
////                    }
////                }
////            }
////        }
////        return time1 > time2 ? time1 : time2;
////    }
//    
//    
//    private List<Task> getNextTasks(boolean[] processedTask,
//                                    Task[] taskArr)
//    {
//        List<Task> retVal = new ArrayList<Task>();
//        int index = 0;
//        for ( Task t : taskArr)
//        {
//            if ( t.currentActiveDependencis == 0 && !processedTask[index++])
//            {
//                retVal.add(t);
//            }
//        }
//        return retVal;
//    }
//
//
//    private boolean isTaskLeft(boolean[] processedTask)
//    {
//        for ( int i = 0 ; i < processedTask.length ; i++)
//        {
//            if ( !processedTask[i] )
//            {
//                return true;
//            }
//        }
//        return false;
//    }
//
//
//    private Task[] parseAndTransform(String[] dag)
//    {
//        int index = 0;
//        Task[] retVal = new Task[dag.length];
//        for ( String str : dag )
//        {
//            String[] arr = str.split(":");
//            int time  = Integer.parseInt(arr[0]);
//            Task[] dependencies = null;
//            if ( arr.length > 1 )
//            {
//                String[] dependenciesArr = arr[1].split(",");
//                dependencies = new Task[dependenciesArr.length];
//                for ( int j = 0 ; j < dependenciesArr.length ; j++ )
//                {
//                    int dependency = Integer.parseInt(dependenciesArr[j]);
//                    dependencies[j] = retVal[dependency];
//                }
//            }
//            
//            retVal[index] = new Task(index,
//                                     new ArrayList<Task>(),
//                                     dependencies == null ? 0 : dependencies.length,
//                                     time);
//            if ( dependencies != null )
//            {
//                for ( Task depTask : dependencies )
//                {
//                    depTask.dependentTasks.add(retVal[index]);
//                }
//            }
//            index++;
//        }
//        return retVal;
//    }
//    public static void main(String[] args)
//    {
//        String[] dag =  {"2:", "10:", "10:", "6:", "5:", "7:", "9:", "8:", "2:", "1:", "5:", "9:"};
//        //String[] dag = {"1:","2:","4:","8:","6:","3:","7:","5:","9:","5:","10:","3:"};
//        int result = new Scheduling().fastest(dag);
//        //  "10:", "10:"
//        //  "9:", "9:"
//        //  "8:", "7:"
//        //  "6:", "5:"
//        //  "2:", "5:
//        //   2  ,  1
//
//        System.out.println(result);
//        
//    }
//
//}

public class Scheduling
{
  class Task
  {
      int index;
      int time;
      List<Task> dependentTasks;
      int currentActiveDependencis;
      
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
      
  }

    public int fastest(String[] dag)
    {
        Task[] tasks = parseAndTransform(dag);
        boolean[] completed = new boolean[tasks.length];
        int best = solve(tasks,
                         completed,
                         -1,
                          0);
        return best;
    }
    
      private int solve(Task[] tasks,
                         boolean[] completed,
                         int currentTaskIndex,
                         int delay)
    {
        if ( allTheTasksCompleted(completed) )
        {
            return delay;
        }
        
        int best = Integer.MAX_VALUE;
        for ( Task t : tasks)
        {
            if ( isEligibleToRun(t, completed))
            {
                if ( t.time > delay)
                {
                    completed[t.index] = true;
                    best = Math.min(best, delay+ solve(tasks,
                                                       completed,
                                                       t.index,
                                                       t.time - delay));
                    completed[t.index] = false;
                }
                else
                {
                    best = Math.min(best, t.time +solve(tasks,
                                                        completed,
                                                        currentTaskIndex,
                                                        delay - t.time));                 
                }
            }            
        }
        
        if ( currentTaskIndex != -1)
        {
            best = Math.min(best , delay+ solve(tasks,
                                                completed,
                                                -1,
                                                0));
        }
        return best;
    }

    private boolean isEligibleToRun(Task t, boolean[] completed)
    {
        if (completed[t.index])
            return false;
        List<Task> list = t.dependentTasks;
        for ( Task t1 : list )
        {
            if ( !completed[t1.index] )
            {
                return false;
            }
        }
        return true;
    }

    private boolean allTheTasksCompleted(boolean[] completed)
    {
        for ( int i = 0 ; i < completed.length  ; i++ )
        {
            if ( !completed[i] )
            {
                return false;
            }
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
    
    public static void main(String[] args)
    {
        String[] input = {"3:", "2:", "4:", "7:0,1,2"};
        int result = new Scheduling().fastest(input);
        System.out.println(result);
    }
    
}