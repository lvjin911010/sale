package com.atguigu.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


class ShareResource
{
	
	private int flag = 1;
	private Lock lock = new ReentrantLock();
	private Condition c1 = lock.newCondition();
	private Condition c2 = lock.newCondition();
	private Condition c3 = lock.newCondition();
	
	
	
	public void printA(int totalLoop)
	{
		lock.lock();
		try 
		{
			//1 判断
			while(flag != 1)
			{
				c1.await();
			}
			//2 干活
			for (int i = 1; i <=5; i++) 
			{
				System.out.println(Thread.currentThread().getName()+"\t"+i+"\t totalLoop: "+totalLoop);
			}
			//3 通知
			flag = 2;
			c2.signal();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}		
	}
	
	public void printB(int totalLoop)
	{
		lock.lock();
		try 
		{
			//1 判断
			while(flag != 2)
			{
				c2.await();
			}
			//2 干活
			for (int i = 1; i <=10; i++) 
			{
				System.out.println(Thread.currentThread().getName()+"\t"+i+"\t totalLoop: "+totalLoop);
			}
			//3 通知
			flag = 3;
			c3.signal();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}		
	}	
	
	public void printC(int totalLoop)
	{
		lock.lock();
		try 
		{
			//1 判断
			while(flag != 3)
			{
				c3.await();
			}
			//2 干活
			for (int i = 1; i <=15; i++) 
			{
				System.out.println(Thread.currentThread().getName()+"\t"+i+"\t totalLoop: "+totalLoop);
			}
			//3 通知
			flag = 1;
			c1.signal();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}		
	}
	
}


/**
 *  * 题目：三个线程，按照A-》B-》C顺序打印，要求A打印5次，B打印10次 ，C打印15次，共计10轮
 * @author zhouyang
 * @version 创建时间：2017年11月3日  下午4:02:06
 */
public class ThreadDemo05 {

	public static void main(String[] args)
	{
		ShareResource sr = new ShareResource();
		
		new Thread(() ->
		{
			for (int i = 1; i <=10; i++) 
			{
				sr.printA(i);
		}
		}, "A").start();
		
		new Thread(() ->
		{
			for (int i = 1; i <=10; i++) 
			{
				sr.printB(i);
			}
		}, "B").start();
		
		new Thread(() ->
		{
			for (int i = 1; i <=10; i++) 
			{
				sr.printC(i);
				System.out.println();
			}
		}, "C").start();
	}
}
