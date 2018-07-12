package myConcurrent;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import myConcurrent.myQueueSingleton.innerClass;

public class myProducer {
	private Lock lock =new ReentrantLock();
	private ReadWriteLock rwLock = new ReentrantReadWriteLock(false);
	
	private boolean runFlag = true;
	public static void main(String[] args) throws InterruptedException {
	
		
		
		ExecutorService poolC = Executors.newCachedThreadPool();
		myConsumer c= new myConsumer();
		poolC.submit(c,"ccc");
		
		
		ExecutorService pool = Executors.newFixedThreadPool(3);
		myProducer mProducer = new myProducer();
		produce p= mProducer.new produce();
		produce p1= mProducer.new produce();
		produce p2= mProducer.new produce();
		List<produce> pList = new LinkedList<myProducer.produce>();
		pList.add(p);
		pList.add(p);
		pList.add(p2);
	//	List<Future<String>> futureList = pool.invokeAll(pList);
		Future<?> future = pool.submit(p);
		Future<?> future1 = pool.submit(p);
		System.out.println(future);
/* 通过thread 建立新线程
		FutureTask<String> fTask = new FutureTask<String>(p);
		Thread test = new Thread(fTask,"test");
		test.start();
*/			
		mProducer.forTest();
		System.out.println("=====over=====");	
	}
	
	public class produce implements Callable<String>{
		ArrayBlockingQueue<String> q =myQueueSingleton.Enum.ins1.getQueue_enum().getQ();
		int i =0;
		AtomicInteger aInteger = new AtomicInteger(0);
		
		public String call() throws InterruptedException{
			
			while (runFlag) {		
				while (aInteger.get()<10) {		
			//	while (i++<10) {	
					//	Thread.sleep(100);
					
					if (lock.tryLock()) {
						q.add(Thread.currentThread().getName()+"--"+aInteger.get());
						System.out.println(Thread.currentThread().getName()
								+"produce__"+aInteger.getAndIncrement()+"~~"+i);
					}
					lock.unlock();
				}		
			}
			return "over";	
		}
		
	}
	
	public void forTest() {
		AtomicStampedReference<Integer> aStampedReference = new AtomicStampedReference<Integer>(1, 19);
		int[] hold = new int[10]; 
		System.out.println( aStampedReference.getStamp() );
		System.out.println( aStampedReference.attemptStamp(1, 10) );
		System.out.println( aStampedReference.compareAndSet(1, 2, 10, 11) );
		System.out.println( aStampedReference.getReference() );
		System.out.println( aStampedReference.get(hold) );
		System.out.println( aStampedReference.weakCompareAndSet(2, 1, 10, 12) );
		System.out.println( aStampedReference.weakCompareAndSet(2, 1, 11, 12) );
		System.out.println( aStampedReference.compareAndSet(1, 2, 10, 11) );
	}
}











