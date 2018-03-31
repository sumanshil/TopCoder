package com.careercup.amazon;

public  class BlockingStackTest {

    final Producer producer;
    final Consumer consumer;

    public BlockingStackTest(int[] producerTasks, int size) {
        BlockingStack stack = new BlockingStack(size);
        producer = new Producer(stack, producerTasks);
        consumer = new Consumer(stack, producerTasks.length);
    }

    /**
    * Implements a Blocking Stack in LIFO manner.
    * The queue take n as an input to create an internal queue of size n tasks.
    */
    private class BlockingStack {
        final int size;
        final int[] tasks;
        int idx;

        public BlockingStack(int size) {
            this.size = size;
            this.tasks = new int[size];
        }

        /**
        * Adds a new task to the end of the stack.
        * The function blocks if the stack is full making sure the function returns only after adding the task.
        */
        public synchronized void add(int task) {
            while (idx == size) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Adding task at index "+ idx+ " task "+ task);
            tasks[idx++] = task;
            this.notify();
        }

        /**
        * Removes a task from the end of the stack.
        * The function blocks if the stack is empty making sure the function returns only after removing the task.
        */
        public synchronized int remove() {
            while (idx == 0){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            
            int retVal = tasks[--idx];
            System.out.println("Removing task at index "+ idx+ " task "+ retVal);
            this.notify();
            return retVal;
            
        }
    }

    private class Producer implements Runnable {
        final BlockingStack stack;
        final int[] tasks;

        Producer(BlockingStack stack, int[] tasks) {
            this.stack = stack;
            this.tasks = tasks;
        }

        public synchronized void run() {
            for (int task : tasks) {
            	//System.out.println(task);
                stack.add(task);
                if (task < 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private class Consumer implements Runnable {
        final BlockingStack stack;
        final int size;

        Consumer(BlockingStack stack, int size) {
            this.stack = stack;
            this.size = size;
        }

        public synchronized void run() {
            int i = 0;
            while (i < size) {
                //System.out.println(stack.remove());
            	stack.remove();
                i++;
                if (i < size) {
                    System.out.print(",");
                }
            }
        }
    }
    
    public static void main(String[] args)
    {
    	int[] elements = {1,2,3,-1,5,6};
    	
        BlockingStackTest test = new BlockingStackTest(elements, elements.length);
        new Thread(test.producer, "Producer").start();
        new Thread(test.consumer, "Consumer").start();        
    }
}

