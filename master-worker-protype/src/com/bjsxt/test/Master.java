package com.bjsxt.test;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Mark on 2016/8/20.
 */
public class Master {
    //1.高并发队列存放需要分配出去的任务队列
    private ConcurrentLinkedQueue<Task> workqueue = new ConcurrentLinkedQueue<>();

    //2 使用hashmap保存所有的work对象，一个对象对应一个线程
    private HashMap<String, Thread> workers = new HashMap<>();

    //3使用一个高并发容器去保存任务执行结果
    private ConcurrentHashMap<String, Object> resultMap = new ConcurrentHashMap<>();

    //4 构造方法初始化worker
    public Master(Worker worker, int workerCount) {

        //任务队列的引用要赋给具体的worker,从而可以得到具体的任务
        worker.setWorkQueue(workqueue);

        worker.setResultMap(resultMap);
        for (int i = 0; i <= workerCount; i++) {

            workers.put("子节点=>" + Integer.toString(i), new Thread(worker));
        }
    }

    //5 提交任务
    public void submit(Task task) {
        this.workqueue.add(task);
    }


    //6 让所有worker都开始工作
    public void execute() {
        for (Map.Entry<String, Thread> worker : workers.entrySet()) {
            worker.getValue().start();
        }
    }


    //判断线程是否执行完毕
    public boolean isComplete() {
        for (Map.Entry<String, Thread> worker : workers.entrySet()) {
            if (worker.getValue().getState() != Thread.State.TERMINATED) {
                return false;
            }
        }
        return true;
    }


    //汇总结果集
    public int getResult() {
        int final_result = 0;
        for (Map.Entry<String, Object> result : resultMap.entrySet()) {
            final_result += (Integer) result.getValue();
        }
        return final_result;
    }
}
