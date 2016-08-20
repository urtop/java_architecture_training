package com.bjsxt.test;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Mark on 2016/8/20.
 */
public class Worker implements Runnable {
    private ConcurrentLinkedQueue<Task> workqueue;
    private ConcurrentHashMap<String, Object> resultMap;

    @Override
    public void run() {
        while (true) {
            Task mytask = this.workqueue.poll();
            if (null == mytask) break;

            //开始处理业务
            Object output = handle(mytask);

            //把结果写回到结果队列里
            this.resultMap.put(Integer.toString(mytask.getId()), output);
        }

    }

    private Object handle(Task mytask) {
        Object output = null;

        //模拟处理任务的消耗时间
        try {
            Thread.sleep(500);

            //目前任务只是取到价格
            output = mytask.getPrice();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return output;

    }


    public void setWorkQueue(ConcurrentLinkedQueue<Task> workqueue) {
        this.workqueue = workqueue;
    }

    public void setResultMap(ConcurrentHashMap<String, Object> resultMap) {
        this.resultMap = resultMap;
    }

}
