package com.cn;

public class MyThread extends Thread{

    @Override
    public void run(){
        System.out.println("子线程执行==="+this.getName());
    }

}
