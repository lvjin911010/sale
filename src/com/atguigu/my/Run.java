package com.atguigu.my;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author 吕金 【caohongri@outlook.com】
 * @Date: 2017/11/3
 * @Time: 20:16
 * @User: 凡
 */
class number{
    private int num = 0;
    Lock lock = new ReentrantLock();
    public void jia(){
        lock.unlock();
        try {
            System.out.println(num+1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void  jian(){
        lock.lock();
        try {
            System.out.println(num-1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
public class Run {
    public static void main(String[] args) {

    }
}
