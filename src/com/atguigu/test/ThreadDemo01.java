package com.atguigu.test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


//private int number = 30;

//Field 
//method			

class Ticket
{
	private int number = 30;
	private Lock lock = new ReentrantLock();
		
	public void sale()
	{
		lock.lock();
		try
		{
			if(number > 0)
			{
				System.out.println(Thread.currentThread().getName()+"\t卖出第："+(number--)+"\t还剩下："+number);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
}


/**
 * 题目：3个售票员		卖出			30张票
 * 
 * @author zhouyang
 * @version 创建时间：2017年11月1日  下午2:50:44
 * 
 * 1	线程	操作	资源类
 * 2	高内聚	低耦合
 *
 * Thread(Runnable target, String name) 		Allocates a new Thread object.
 * 
 */
public class ThreadDemo01
{
	public static void main(String[] args)
	{
		Ticket ticket = new Ticket();
		
		/*new Thread(new Runnable() {
			@Override
			public void run()
			{
				for (int i = 1; i <=40; i++) 
				{
					ticket.sale();
				}
			}
		}, "AA").start();
		
		new Thread(new Runnable() {
			@Override
			public void run()
			{
				for (int i = 1; i <=40; i++) 
				{
					ticket.sale();
				}
			}
		}, "BB").start();
		new Thread(new Runnable() {
			@Override
			public void run()
			{
				for (int i = 1; i <=40; i++) 
				{
					ticket.sale();
				}
			}
		}, "CC").start();*/
		
		
		new Thread(() -> {for (int i = 1; i <=40; i++) {ticket.sale();}}, "AA").start();
		new Thread(() -> {for (int i = 1; i <=40; i++) {ticket.sale();}}, "BB").start();
		new Thread(() -> {for (int i = 1; i <=40; i++) {ticket.sale();}}, "CC").start();
		
		new Thread(() ->
		{
			
		}, "XXX").start();
	}
}
/**
 * 1	
 * class Test01 implements Runnable
 * 	{
 * 		public void run()
 * 	{
 * 	   ....
 * }
 * }
 * 
 * main()
 * {
 * 		Thread t1 = new Thread(new Test01);
 * 
 * 		t1.start;
 * 
 * }
 * =======================================================
 * 2	main()
 * {
 * 		new Thread(new Runnable() {
			@Override
			public void run()
			{
				for (int i = 1; i <=40; i++) 
				{
					ticket.sale();
				}
			}
		}, "BB").start();
 * 
 * }
 * =======================================================
 * 
 * 3	main()
 * {
 * 
 * 		new Thread(() ->
		{
			
		}, "XXX").start();
 * }
 */


