package com.bjsxt.test;

import java.util.Random;

/**
 * Created by Mark on 2016/8/20.
 */
public class test {
    public static void main(String[] args) {

        //根据实际机器的CPU线程数来决定worker的数量
        Master master = new Master(new Worker(), Runtime.getRuntime().availableProcessors());

        //随机产生价格
        Random r = new Random();

        for (int i = 1; i <= 100; i++) {
            Task t = new Task();
            t.setId(i);
            t.setName("任务==>" + i);
            t.setPrice(r.nextInt(1000));
            master.submit(t);
        }

        //执行任务
        master.execute();

        //记录开始时间
        long start = System.currentTimeMillis();



//        try {
//            Thread.sleep(6000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        int result =  master.getResult();
//        System.out.println(result);
        while (true) {
            if (master.isComplete()) {
                //记录结束时间
                long end = System.currentTimeMillis() - start;

                int result =  master.getResult();
                System.out.println("最终结果 : "+master.getResult()+" 耗时"+end );
                break;
            }
        }
    }
}
