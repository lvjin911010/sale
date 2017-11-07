package com.atguigu.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;


class MyThread implements Callable<Integer>
{
	@Override
	public Integer call() throws Exception
	{
		System.out.println("*****come in call()*****");
		return 200;
	}
}





/**awt/swing
 * 题目：java从1.5后多线程的获得方式
 * Callable接口的讲解
 * 
 * Thread(Runnable target, String name) Allocates a new Thread object. * 
 * 
 * @author zhouyang
 * @version 创建时间：2017年11月3日  上午9:32:43
 */
public class ThreadDemo02 {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException
	{
		FutureTask futureTask = new FutureTask(new MyThread());
		
		new Thread(futureTask,"AA").start();
		
		
		
		
		//biz method。。。。。。。。
		
		Integer result = (Integer) futureTask.get();//get方法尽量放在最后
		System.out.println("####result: "+result);
		
	}

}
