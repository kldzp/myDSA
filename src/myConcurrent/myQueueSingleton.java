package myConcurrent;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class myQueueSingleton{
	private ArrayBlockingQueue<String> myQueue = new ArrayBlockingQueue<String>(2);
//	private ArrayBlockingQueue<String> myQueue = new ArrayBlockingQueue<String>(2);
	private PriorityQueue<String> priorityQueue ;
	//private volatile static myQueueSingleton ins = null;
	int a =0;
	private myQueueSingleton() {

	}

	public static class innerClass{
		public static myQueueSingleton ins = new myQueueSingleton();
	}
	public static myQueueSingleton getQueue_innerClass() {
		return innerClass.ins;
	}
	
	public static enum Enum{
		ins1;
		private myQueueSingleton ins ;
		//利用构造函数在jvm中只执行一次的机制
		private Enum() {
			ins=new myQueueSingleton();
		}
		public myQueueSingleton getQueue_enum() {
			return ins;
		}
	}
	
	public ArrayBlockingQueue<String> getQ() {
		return myQueue;
	}
	
	public static void main(String[] args) throws InterruptedException {
		myQueueSingleton test = myQueueSingleton.Enum.ins1.getQueue_enum();
		test.myQueue.add("a");
		myQueueSingleton test1 = myQueueSingleton.Enum.ins1.getQueue_enum();
		test1.myQueue.add("b");

		System.out.println(test.myQueue);
	}

}
