package myConcurrent;

public class myConsumer extends Thread{
	ThreadLocal<Integer> xLocal= new ThreadLocal<Integer>();
	static int a=5;
	myQueueSingleton q= myQueueSingleton.Enum.ins1.getQueue_enum();
	public static void main(String[] args) {	
		myConsumer m = new myConsumer();
		new Thread(m,"1-").start();
		new Thread(m,"2-").start();
		new Thread(m,"3-").start();
	}
	

	public void run() {	
		while (true) {
				String s = q.getQ().remove();
				System.out.println(Thread.currentThread().getName()+"--consumer--"+s);
		}
	}
}