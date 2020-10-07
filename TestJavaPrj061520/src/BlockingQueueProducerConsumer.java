import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
public class BlockingQueueProducerConsumer {
	public static void main(String[] args) {//Division, Remainder -- Modulus % : 10%3 = 1 ;  3%10 = 3 ; 3%5  = 3 ; 10/3 = 3 ;
		//testProducerConsumerUsingBlockingQueue();
		//testProducerConsumerUsingMyBlockingQueueUsingReentrantLock();
		testProducerConsumerUsingMyBlockingQueueUsingWaitNotify();
	}
	static void testProducerConsumerUsingMyBlockingQueueUsingWaitNotify()
	{
		MyBlockingQueueWN bq = new MyBlockingQueueWN(2);
		new Thread(new ProducerUsingMyBQ(bq)).start();
		new Thread(new ConsumerUsingMyBQ(bq)).start();
		new Thread(new ProducerUsingMyBQ(bq)).start();
		new Thread(new ConsumerUsingMyBQ(bq)).start();
	}		
	static void testProducerConsumerUsingMyBlockingQueueUsingReentrantLock()
	{
		MyBlockingQueueRL bq = new MyBlockingQueueRL(5);
		new Thread(new ProducerUsingMyBQ(bq)).start();
		new Thread(new ConsumerUsingMyBQ(bq)).start();
		new Thread(new ProducerUsingMyBQ(bq)).start();
		new Thread(new ConsumerUsingMyBQ(bq)).start();
		new Thread(new ProducerUsingMyBQ(bq)).start();
		new Thread(new ConsumerUsingMyBQ(bq)).start();		
	}	
	static void testProducerConsumerUsingBlockingQueue()
	{
		BlockingQueue bq = new ArrayBlockingQueue(2);
		new Thread(new ProducerUsingBQ(bq)).start();
		new Thread(new ConsumerUsingBQ(bq)).start();
	}
}
interface IMyBlockingQueue <E>
{
	void put( E item ) throws InterruptedException ;
	E take( ) throws InterruptedException ;
}
class MyBlockingQueueWN <E>  implements IMyBlockingQueue <E> // MyBlockingQueue Using Wait and Notify
{
	Queue <E> myQueue;
	int nSize = 10;
	Object notFull;
	Object notEmpty;
	MyBlockingQueueWN( int nSize)
	{
		myQueue = new LinkedList<>() ;  this.nSize = nSize; 
	}
	@Override
	public synchronized void put( E item ) throws InterruptedException 
	{ // implicit lock, instance lock, wait and notify has to called on the same object/instance - that is synchronized
		while (myQueue.size() == nSize)
			wait(); // it is full hence wait.
		myQueue.add( item );
		notifyAll();	
	}
	public synchronized E take( ) throws InterruptedException 
	{
		while (myQueue.size() == 0)
			wait(); // it is empty hence wait
		E item = myQueue.remove();
		notifyAll();	
		return item;
	}	
}
class MyBlockingQueueRL <E> implements IMyBlockingQueue <E> // MyBlockingQueueRL Using Reentrant Lock
{
	Queue <E> myQueue;
	int nSize = 10;
	ReentrantLock lock ;
	Condition notFull;
	Condition notEmpty;
	MyBlockingQueueRL( int nSize)
	{
		myQueue = new LinkedList<>() ;  this.nSize = nSize; 
		lock = new ReentrantLock();
		notFull = lock.newCondition();
		notEmpty = lock.newCondition();
	}
	public void put( E item ) throws InterruptedException 
	{
		lock.lock();
		try 
		{
			while (myQueue.size() == nSize)
				notEmpty.await(); // it is full hence wait.
			myQueue.add( item );
			notFull.signalAll();	
		} 
		finally
		{
			lock.unlock();
		}	
	}
	public  E take( ) throws InterruptedException 
	{
		lock.lock();
		try 
		{
			while (myQueue.size() == 0)
				notFull.await(); // it is empty hence wait
			E item = myQueue.remove();
			notEmpty.signalAll();	
			return item;
		} 
		finally
		{
			lock.unlock();
		}
	}	
}
class ProducerUsingMyBQ implements Runnable  // MyBlockingQueueRL Using Reentrant Lock
{
	IMyBlockingQueue bq ;
	ProducerUsingMyBQ() {} 
	ProducerUsingMyBQ(IMyBlockingQueue bq ) { this.bq = bq; }
	@Override
	public void run() {
		int nMessageCount = 0;
		while (nMessageCount < 10)
		{
			try {
				bq.put(nMessageCount); // Will Wait if Queue is Full.
				System.out.println("Placed on Queue--" + nMessageCount);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			nMessageCount++;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Exiting Producer Thread" );
	}
}
class ConsumerUsingMyBQ implements Runnable  // MyBlockingQueueRL Using Reentrant Lock
{
	IMyBlockingQueue bq ;   // MyBlockingQueueRL Using Reentrant Lock
	ConsumerUsingMyBQ() {} 
	ConsumerUsingMyBQ(IMyBlockingQueue bq ) { this.bq = bq; }
	@Override
	public void run() {
		int nMessageCount = 0;
		while (nMessageCount < 10)
		{
			try {
				int nMsg = (int) bq.take();  // Will Wait if Queue is Empty
				System.out.println("Removed from Queue--" + nMessageCount);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			nMessageCount++;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Exiting Consumer Thread" );
	}
}
//--------------------------------------------------------------------------------
class ProducerUsingBQ implements Runnable
{
	BlockingQueue bq ;
	ProducerUsingBQ() {} 
	ProducerUsingBQ(BlockingQueue bq ) { this.bq = bq; }
	@Override
	public void run() {
		int nMessageCount = 0;
		while (nMessageCount < 10)
		{
			try {
				bq.put(nMessageCount); // Will Wait if Queue is Full.
				System.out.println("Placed on Queue--" + nMessageCount);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			nMessageCount++;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Exiting Producer Thread" );
	}
}
class ConsumerUsingBQ implements Runnable
{
	BlockingQueue bq ;
	ConsumerUsingBQ() {} 
	ConsumerUsingBQ(BlockingQueue bq ) { this.bq = bq; }
	@Override
	public void run() {
		int nMessageCount = 0;
		while (nMessageCount < 10)
		{
			try {
				int nMsg = (int) bq.take();  // Will Wait if Queue is Empty
				System.out.println("Removed from Queue--" + nMessageCount);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			nMessageCount++;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Exiting Consumer Thread" );
	}
}
/* Producer writes to Queue. If Queue is Full Producer is in Wait State.
 Consumer  reads from Queue. If Queue is Full Consumer is in Wait State.
 They Signal each other to come out of Wait State.
 BlockingQueue implements this algo.
 */
