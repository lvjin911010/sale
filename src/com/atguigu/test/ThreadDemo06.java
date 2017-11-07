package com.atguigu.test;

import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyQueue
{
	private Object obj;
	ReentrantReadWriteLock rwlock = new ReentrantReadWriteLock();
	
	
	public void setObj(Object obj)
	{
		rwlock.writeLock().lock();
		try 
		{
			this.obj = obj;
			System.out.println(Thread.currentThread().getName()+"\t writeThread:"+obj);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			rwlock.writeLock().unlock();
		}
	}
	
	public void getObj()
	{
		rwlock.readLock().lock();
		try 
		{
			System.out.println(Thread.currentThread().getName()+"\t"+obj);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			rwlock.readLock().unlock();
		}
	}
	
	
	
	
	
}

/**
 * 题目：读写锁,一个线程在写入，100个线程读取
 * @author zhouyang
 * @version 创建时间：2017年11月3日  下午4:49:29
 */
public class ThreadDemo06 
{
	public static void main(String[] args) throws InterruptedException
	{
		MyQueue q = new MyQueue();
		
		
		new Thread(() ->{
			q.setObj(new Random().nextInt(300));
		}, "A").start();
		
		//Thread.sleep(100);
		
		for (int i = 1; i <=100; i++) 
		{
			new Thread(() ->{q.getObj();}, String.valueOf(i)).start();			
		}
		
	}
}
