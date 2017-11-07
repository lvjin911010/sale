package com.atguigu.test;

import java.util.Arrays;

@FunctionalInterface
interface MyFoo
{
//	public void sayHello();
//	public void say886();
	
	public int add(int x, int y);
	
	default void sayHello()
	{
		System.out.println("*********sayHello");
	}
	default void sayHello2()
	{
		System.out.println("*********sayHello 2222");
	}
	
	public static void say886()
	{
		System.out.println("*********say886");
	}
	
}

/**
 * 1	有且仅有一个public abstract方法，(形参) -> { 方法体实现}
 * 2	情况同1，@FunctionalInterface没有标注，系统也会自动检测，但是如果新增了@FunctionalInterface，定义的时候只有有一个方法。
 * 3	default 默认方法实现
 * 4	静态方法引用导入
 * @author zhouyang
 * @version 创建时间：2017年11月1日  下午4:54:04
 */
public class LambdaDemo {

	public static void main(String[] args)
	{
//		MyFoo test = new MyFoo() {
//			@Override
//			public void sayHello()
//			{
//				System.out.println("****hello 0525");
//			}
//			@Override
//			public void say886()
//			{
//				// TODO Auto-generated method stub
//				
//			}
//		};
//		test.sayHello();
		
		
		MyFoo test = (a,b) -> {
			System.out.println("*****come in****");
			return a + b;
		};
		int result = test.add(1, 32);
		System.out.println(result);
		
		test.sayHello();
		test.sayHello2();
		
		
		MyFoo.say886();
		
		Arrays.asList(1,2,3,4,5).forEach(System.out::println);
		
		
	}

}
