package com.atguigu.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData
{
	private int number = 0;
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	
	public void increment() throws InterruptedException
	{
		lock.lock();
		try 
		{
			while(number != 0)
			{
				condition.await();//this.wait();
			}
			++number;
			System.out.println(Thread.currentThread().getName()+"\t"+number);
			condition.signalAll();//this.notifyAll();			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
	
	public void decrement() throws InterruptedException
	{
		lock.lock();
		try 
		{
			while(number == 0)
			{
				condition.await();//this.wait();
			}
			--number;
			System.out.println(Thread.currentThread().getName()+"\t"+number);
			condition.signalAll();//this.notifyAll();			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}	
	
	
	/*public synchronized void increment() throws InterruptedException
	{
		while(number != 0)
		{
			this.wait();
		}
		++number;
		System.out.println(Thread.currentThread().getName()+"\t"+number);
		this.notifyAll();
	}
	
	public synchronized void decrement() throws InterruptedException
	{
		while(number == 0)
		{
			this.wait();
		}
		--number;
		System.out.println(Thread.currentThread().getName()+"\t"+number);
		this.notifyAll();
	}*/
}


/**
 * 题目：两个线程对初始值为零的一个变量进行操作，实现一个线程加1，一个线程减一，来10轮。
 * 
 * @author zhouyang
 * @version 创建时间：2017年11月3日  上午10:47:56
 */
public class ThreadDemo03 {

	public static void main(String[] args)
	{
		ShareData sd = new ShareData();
		
		new Thread(() ->{
			for (int i = 1; i <=10; i++) 
			{
				try {
					Thread.sleep(200);
					sd.increment();
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, "A").start();
		
		new Thread(() ->{
			for (int i = 1; i <=10; i++) 
			{
				try {
					Thread.sleep(300);
					sd.decrement();
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, "B").start();
		
		new Thread(() ->{
			for (int i = 1; i <=10; i++) 
			{
				try {
					Thread.sleep(400);
					sd.increment();
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, "C").start();
		
		new Thread(() ->{
			for (int i = 1; i <=10; i++) 
			{
				try {
					Thread.sleep(500);
					sd.decrement();
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, "D").start();		
		
	}
}






