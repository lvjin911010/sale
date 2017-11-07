package com.atguigu.my;

import javax.security.auth.login.Configuration;
import javax.sound.midi.SoundbankResource;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author 吕金 【caohongri@outlook.com】
 * @Date: 2017/11/3
 * @Time: 18:26
 * @User: 凡
 */
class Number{
    private int flag = 1;
    private static int number = 1;
    Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
        public void  write(){
            lock.lock();
            try {
                while (flag==3){
                    c1.await();
                }
              flag++;
                System.out.print(number++);
                c2.signalAll();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
        public void read(){
           lock.lock();
            try {
                while (flag != 3){
                    c2.await();
                }
                System.out.print((char)(64+number/2));
                flag = 1;
                c1.signalAll();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }
}
public class MyThred {

    public static void main(String[] args) {
        Number number = new Number();
       new Thread(() -> {
           for (int i = 0; i < 52; i++) {
            number.write();
           }
       }).start();
        new Thread(() -> {
            for (int i = 0; i < 26; i++) {
                number.read();
            }
        }).start();
        }


}
