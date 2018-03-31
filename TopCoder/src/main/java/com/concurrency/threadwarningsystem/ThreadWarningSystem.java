package com.concurrency.threadwarningsystem;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.*;

/**
 * Created by sshil on 7/20/17.
 */
public class ThreadWarningSystem {

    private final Timer threadCheck = new Timer("Thread monitor", true);
    private final ThreadMXBean mbean = ManagementFactory.getThreadMXBean();

    private final Collection<Listener> listeners = new ArrayList<>();

    private static final int DEADLOCK_CHECK_PERIOD = 500;

    private static final int THREAD_NUMBER_CHECK_PERIOD = 20;
    private static final int MAX_STACK_DEPTH = 30;
    private boolean threadThresholdNotified = false;
    private Set deadlockedThreads = new HashSet();

    public ThreadWarningSystem(){
        threadCheck.schedule(new TimerTask() {
            @Override
            public void run() {
                long[] ids = mbean.findMonitorDeadlockedThreads();
                if (ids != null && ids.length > 0) {
                    for ( Long l : ids) {
                        if (!deadlockedThreads.contains(l)) {
                            deadlockedThreads.add(l);
                            ThreadInfo ti = mbean.getThreadInfo(l, MAX_STACK_DEPTH);
                            fireDeadlockDetected(ti);
                        }
                    }
                }
            }
        }, 10, DEADLOCK_CHECK_PERIOD);
    }

    public ThreadWarningSystem(final int threadThreshold){
        this();
        threadCheck.schedule(new TimerTask() {
            @Override
            public void run() {
                if (mbean.getThreadCount() > threadThreshold) {
                    if (!threadThresholdNotified) {
                        fireThresholdExceeded();
                        threadThresholdNotified = true;
                    }
                } else {
                    threadThresholdNotified = false;
                }
            }
        }, 10, THREAD_NUMBER_CHECK_PERIOD);
    }


    private void fireDeadlockDetected(ThreadInfo thread){
        synchronized (listeners){
            for (Listener l : listeners) {
                l.deadlockDetected(thread);
            }
        }
    }

    private void fireThresholdExceeded() {
        ThreadInfo[] allThreads = mbean.getThreadInfo(mbean.getAllThreadIds());
        synchronized (listeners) {
            for (Listener l : listeners) {
                l.thresholdExceeded(allThreads);
            }
        }
    }

    public boolean addListener(Listener l) {
        synchronized (listeners) {
            return listeners.add(l);
        }
    }

    public boolean removeListener(Listener l) {
        synchronized (listeners) {
            return listeners.remove(l);
        }
    }

    public interface Listener{
        void deadlockDetected(ThreadInfo threadInfo);
        void thresholdExceeded(ThreadInfo[] allThreads);
    }
}
