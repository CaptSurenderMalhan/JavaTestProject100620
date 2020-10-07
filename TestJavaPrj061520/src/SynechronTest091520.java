import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;
// Division, Remainder -- Modulus % : 10%3 = 1 ;  3%10 = 3 ; 10/3 = 3 ;
public class SynechronTest091520 { // 3 PM 09/15/20, TUE.
	public static void main(String[] args) {
		//testUsingMyRunnableTask();
		//testUsingMyRunnableTaskUsingSemaphore();
		//testUsingMyRunnableTaskAndExecutorService();
		//testUsingMyRunnableTaskUsingReEntrantLock();
		//testUsingMyRunnableTaskWithThread(); // Create Thread in Constructor of MyRunnableTaskWithThread
		testUsingMyRunnableTaskUsingAnotherObjectsLock();
		//testUsingMyThread();
	}
	static void testSemaphore() // Test Concepts
	{
		Semaphore s = new Semaphore( 12 );
		int x = -1;
		try {
			s.acquire(12);
			x = s.availablePermits();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
	}
	static void testUsingMyRunnableTaskUsingReEntrantLock()
	{ 
		ExecutorService threadPool = Executors.newFixedThreadPool(3);
		threadPool.execute( new MyRunnableTaskUsingReEntrantLock(1));
		threadPool.execute( new MyRunnableTaskUsingReEntrantLock(2));
		threadPool.execute( new MyRunnableTaskUsingReEntrantLock(3));
		threadPool.shutdown();	
	}	
	static void testUsingMyRunnableTaskAndExecutorService()
	{
		ExecutorService threadPool = Executors.newFixedThreadPool(3);
		threadPool.execute( new MyRunnableTask(1));
		threadPool.execute( new MyRunnableTask(2));
		threadPool.execute( new MyRunnableTask(3));
		threadPool.shutdown();	
	}	
	static void testUsingMyRunnableTaskWithThread()
	{ // Create Thread in Constructor of MyRunnableTaskWithThread - Just for Fun, Think out of the box.
		new MyRunnableTaskWithThread(1);
		new MyRunnableTaskWithThread(2);
		new MyRunnableTaskWithThread(3);
	}		
	static void testUsingMyRunnableTaskUsingSemaphore()
	{
		Thread t1 = new Thread( new MyRunnableTaskUsingSemaphore(1));
		Thread t2 = new Thread( new MyRunnableTaskUsingSemaphore(2));
		Thread t3 = new Thread( new MyRunnableTaskUsingSemaphore(3));
		t1.start();
		t2.start();
		t3.start();		
	}	
	//MyRunnableTaskUsingAnotherObjectsLock
	static void testUsingMyRunnableTaskUsingAnotherObjectsLock()
	{  // Alternate Syntax, when we dont need to hold on to reference to thread object.
		new Thread( new MyRunnableTaskUsingAnotherObjectsLock(1)).start(); 
		new Thread( new MyRunnableTaskUsingAnotherObjectsLock(2)).start();
		new Thread( new MyRunnableTaskUsingAnotherObjectsLock(3)).start();
	}	
	static void testUsingMyRunnableTask()
	{
		Thread t1 = new Thread( new MyRunnableTask(1));
		Thread t2 = new Thread( new MyRunnableTask(2));
		Thread t3 = new Thread( new MyRunnableTask(3));
		t1.start();
		t2.start();
		t3.start();		
	}	
	static void testUsingMyThread()
	{
		MyThread t1 = new MyThread(1);
		MyThread t2 = new MyThread(2);
		MyThread t3 = new MyThread(3);
		t1.start();
		t2.start();
		t3.start();		
	}
} // ---------- END OF -- CLASS SynechronTest091520 --------------------
class MyRunnableTaskUsingReEntrantLock implements Runnable
{
	static int nNowPrinted = 0;
	ReentrantLock  reEntrantLock = new ReentrantLock(  );
	int nToPrint = 0;
	MyRunnableTaskUsingReEntrantLock() {}
	MyRunnableTaskUsingReEntrantLock(int nToPrint) { this.nToPrint = nToPrint;} 
	public void run()
	{
		while ( nNowPrinted  < 12)
		{
			try {
				reEntrantLock.lock();
				if (( nNowPrinted + 1) == nToPrint)
				{
					System.out.println(nToPrint);
					nNowPrinted = nToPrint;
					nToPrint = nToPrint + 3;
				}
				reEntrantLock.unlock();
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}				
		}
	}
} // END OF -- MyRunnableTaskUsingSemaphore

class MyRunnableTaskUsingSemaphore implements Runnable
{ // A Semaphore is a MuteEx when the number of resources / count is 1
	// Here the resource is -- "Printing to the Console" -- Coordinating/Controlling - who gets to print.
	// Or we can say the resource is -- nNowPrinted
	// Or, Critical Section is the lines of code - that is between acquiring and releasing the lock 
	static int nNowPrinted = 0;
	Semaphore semaphore = new Semaphore( 1 );
	int nToPrint = 0;
	MyRunnableTaskUsingSemaphore() {}
	MyRunnableTaskUsingSemaphore(int nToPrint) { this.nToPrint = nToPrint;} 
	public void run()
	{
		while ( nNowPrinted  < 12)
		{
			try {
				semaphore.acquire();
				if (( nNowPrinted + 1) == nToPrint)
				{
					System.out.println(nToPrint);
					nNowPrinted = nToPrint;
					nToPrint = nToPrint + 3;
				}
				semaphore.release();
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}				
		}
	}
} // END OF -- MyRunnableTaskUsingSemaphore
class MyThread extends Thread
{
	static int nNowPrinted = 0;
	static Class classObj  = MyThread.class;
	int nToPrint = 0;
	MyThread() {}
	MyThread(int nToPrint) { this.nToPrint = nToPrint;} 
	public void run()
	{
		while ( nNowPrinted  < 12)
		{
			synchronized (classObj)
			{
				if (( nNowPrinted + 1) == nToPrint)
				{
					System.out.println(nToPrint);
					nNowPrinted = nToPrint;
					nToPrint = nToPrint + 3;
				}
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}  // END OF -- MyThread
class MyRunnableTask implements Runnable
{
	static int nNowPrinted = 0;
	static Class classObj  = MyThread.class;
	int nToPrint = 0;
	MyRunnableTask() {}
	MyRunnableTask(int nToPrint) { this.nToPrint = nToPrint;} 
	public void run()
	{
		while ( nNowPrinted  < 12)
		{
			synchronized (classObj)
			{
				if (( nNowPrinted + 1) == nToPrint)
				{
					System.out.println(nToPrint);
					nNowPrinted = nToPrint;
					nToPrint = nToPrint + 3;
				}
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
} // ----------------- END OF MyRunnableTask
class MyRunnableTaskUsingAnotherObjectsLock implements Runnable
{ // Using another Object's Lock
	static int nNowPrinted = 0;
	static Object object  = new Object();
	int nToPrint = 0;
	MyRunnableTaskUsingAnotherObjectsLock() {}
	MyRunnableTaskUsingAnotherObjectsLock(int nToPrint) { this.nToPrint = nToPrint;} 
	public void run()
	{
		while ( nNowPrinted  < 12)
		{
			synchronized (object)
			{
				if (( nNowPrinted + 1) == nToPrint)
				{
					System.out.println(nToPrint);
					nNowPrinted = nToPrint;
					nToPrint = nToPrint + 3;
				}
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
} // ----------------- END OF MyRunnableTask
class MyRunnableTaskWithThread implements Runnable
{
	static int nNowPrinted = 0;
	static Class classObj  = MyThread.class;
	int nToPrint = 0;
	MyRunnableTaskWithThread() {}
	MyRunnableTaskWithThread(int nToPrint) 
	{  // Just for fun, interesting way to create thread in constructor of a Runnable Task
		this.nToPrint = nToPrint;
		new Thread(this).start();  // Just for fun, think out of the box, think different.
	} 
	public void run()
	{
		while ( nNowPrinted  < 12)
		{
			synchronized (classObj)
			{
				if (( nNowPrinted + 1) == nToPrint)
				{
					System.out.println(nToPrint);
					nNowPrinted = nToPrint;
					nToPrint = nToPrint + 3;
				}
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
} // ----------------- END OF MyRunnableTaskWithThread
// 1st thread -- 1, 4, 7, 10 , 2nd -- 2, 5, 8, 11 ; 3rd -- 3, 6, 9, 12