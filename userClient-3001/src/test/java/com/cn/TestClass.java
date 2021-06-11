package com.cn;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


@RunWith(SpringRunner.class)
public class TestClass {


    @Test
    public void test01(){
        System.out.println("主线程执行===");
        for (int i = 0; i < 10 ; i++) {
            MyThread myThread = new MyThread();
            myThread.start();
        }
    }


    @Test
    public void test(){
        long currentTimeMillis = System.currentTimeMillis();

        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5,6 ,3 , TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(3));

        for (int i = 0; i < 10; i++) {
            try {
                String task = "task" + i;
                System.out.println("创建任务并提交到线程池中："+task);
                threadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("执行任务");
                    }
                });
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        threadPool.shutdown();
        boolean loop = true;
        try {
            do{
                loop=!threadPool.awaitTermination(2,TimeUnit.SECONDS);
            }while (loop);
            if (loop!=true){
                System.out.println("所有线程执行完毕");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println("耗时："+(System.currentTimeMillis()-currentTimeMillis));
        }

    }

}
