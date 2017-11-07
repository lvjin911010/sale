package com.atguigu.entities;


import javax.sound.midi.SoundbankResource;

/**
 * 1	请说说请求转发+重定向的区别
 * 2	Servlet的生命周期
 * 3	HTTP协议的，谈谈你的理解
 * 4	什么是REST风格的架构
 * 5	给我说出5个常见的HTTP状态码，但不能是404和500
 * 6	AOP
 * 7	数据库的隔离级别
 * 8	数据库的索引，谈谈你的理解和优化
 * @author zhouyang
 * @version 创建时间：2017年11月1日  下午2:47:22
 */
public class Person 
{
	private Integer id;
	private String  personName;
	
	public Person() {}

	public Person(String personName)
	{
		super();
		this.personName = personName;
	}

	
	
	
	public Person(Integer id, String personName)
	{
		super();
		this.id = id;
		this.personName = personName;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getPersonName()
	{
		return personName;
	}

	public void setPersonName(String personName)
	{
		this.personName = personName;
	}
//
//	@Override
//	public int hashCode()
//	{
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((personName == null) ? 0 : personName.hashCode());
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj)
//	{
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Person other = (Person) obj;
//		if (personName == null) {
//			if (other.personName != null)
//				return false;
//		}
//		else if (!personName.equals(other.personName))
//			return false;
//		return true;
//	}

	public static void main(String[] args) {
		System.out.println((char)90);
	}
}
class Data{
	private int num = 1;
	private int flag = 1;
	private char cc = 'A';

	public synchronized void PrintNum() throws InterruptedException {
		if(flag==3) {
			this.wait();
		}
		flag ++;
		System.out.print(num++);
		this.notifyAll();
	}
	public synchronized void PrintChar() throws InterruptedException {
		while(flag != 3) {
			this.wait();
		}
		System.out.print(cc++);
		flag = 1;
		this.notifyAll();
	}
}
/**
 * 写两个线程，一个线程打印1~52，一个线程打印字母A~Z，要求用线程通信
 * 12A34B...5152Z
 * @author ming
 *
 */
 class ThreadTest01{
	public static void main(String[] args) {
		Data data = new Data();
		new Thread(() ->
		{
			for(int i = 0 ;i < 52;i++) {
				try {
					data.PrintNum();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, "").start();
		new Thread(() ->
		{
			for(int  i = 0;i< 26;i++) {
				try {
					data.PrintChar();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, "").start();
	}
}


