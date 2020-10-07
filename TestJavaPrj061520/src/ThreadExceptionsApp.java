import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
class GlobalVars 
{ // From Executors also we can save in hashset the thread objects from executor pool.
	static Set <Thread> threadHashSet  = new HashSet <> (); 
	static Map <Thread, MyUncaughtExceptionHandler> hashMapThreadHandler = new HashMap();
	static int nNumTimesToReStart  = 2;
}
public class ThreadExceptionsApp { // Eclipse Luna Service Release 1a (4.4.1)
	// static List <Thread> threadList = new ArrayList <> ();   
	public static void main(String[] args)
	   {
		 testUsingThreadClass();
		//   testUsingExecutors();
	   }
	   static void testUsingThreadClass()
	   {
			  Thread.setDefaultUncaughtExceptionHandler(new DefaultExceptionHandler());
		      MyTask myTask = new MyTask();
		      Thread myThread = new Thread(myTask);
		      System.out.printf("Thread: %s\n", myThread.getId());
		      myThread.start();
		      try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		      printThreadStatus();
	   }
	   static void printThreadStatus()
	   {
		      for ( Thread thread : GlobalVars.threadHashSet  )
		      {
		    	  ExceptionHandlerForMyTask exceptionHandlerForMyTask =  (ExceptionHandlerForMyTask) thread.getUncaughtExceptionHandler();
		    	  if (exceptionHandlerForMyTask == null)
	    		   	  System.out.printf("testUsingThreadClass --- Thread status: %s : %s\n", thread.getId(), thread.getState());		    	  
		    	else
		    		   	  System.out.printf("testUsingThreadClass --- Thread status: %s : %s : MyExceptionID-- %s\n", thread.getId(), thread.getState(), 
		    			  																									exceptionHandlerForMyTask.getMyExceptionId());		    	  
		      }		
		      System.out.println("Now Printing MyUncaughtExceptionHandler ID ------------");
		      for ( Thread thread : GlobalVars.hashMapThreadHandler.keySet()  )
		      {
		    	  MyUncaughtExceptionHandler exceptionHandlerForMyTask =  GlobalVars.hashMapThreadHandler.get(thread);
		    	  if (exceptionHandlerForMyTask == null)
	    		   	  System.out.printf("testUsingThreadClass --- Thread status: %s : %s\n", thread.getId(), thread.getState());		    	  
		    	else
		    		   	  System.out.printf("testUsingThreadClass --- Thread status: %s : %s : MyExceptionID-- %s\n", thread.getId(), thread.getState(), 
		    			  																									exceptionHandlerForMyTask.getMyExceptionId());		    	  
		      }			      
	   }
	   static void testUsingExecutors()
	   {
		   ThreadPoolExecutor threadPool =  (ThreadPoolExecutor)  Executors.newFixedThreadPool(1);
			threadPool.execute( new MyTask());
			threadPool.execute( new MyTask());
			threadPool.execute( new MyTask());
			int poolSize = threadPool.getPoolSize();
			System.out.printf("threadPool.getPoolSize() = : %s\n", poolSize);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	      printThreadStatus();
			poolSize = threadPool.getPoolSize();
			System.out.printf("threadPool.getPoolSize() = : %s\n", poolSize);
			System.out.printf("threadPool.getCorePoolSize() = : %s\n", threadPool.getCorePoolSize());
			System.out.printf("threadPool.getActiveCount() = : %s\n", threadPool.getActiveCount());
			threadPool.shutdown();	
	   }	   
}
class MyTask implements Runnable
{
	@Override
   public void run()
   { //Before begining the Task, Set UncaughtExceptionHandler - for "This Task and This Thread".
	   Thread myThread = Thread.currentThread();
	   GlobalVars.threadHashSet.add(myThread);
	   ExceptionHandlerForMyTask  exceptionHandlerForMyTask = new ExceptionHandlerForMyTask();
	   GlobalVars.hashMapThreadHandler.put( myThread, exceptionHandlerForMyTask);
	   myThread.setUncaughtExceptionHandler(exceptionHandlerForMyTask);
	   System.out.printf("Thread: %s\n", myThread.getId());
      System.out.println(Integer.parseInt("123"));
      System.out.println(Integer.parseInt("234"));
      System.out.println(Integer.parseInt("345"));
      System.out.println(Integer.parseInt("XYZ")); //This will cause NumberFormatException
      System.out.println(Integer.parseInt("456"));
   }
}
interface MyUncaughtExceptionHandler extends Thread.UncaughtExceptionHandler
{
	public int getMyExceptionId();
}
class ExceptionHandlerForMyTask implements MyUncaughtExceptionHandler
{ // This code is executed by the same thread in which Uncaught Exception occurred. 
   static int nExceptionCount = 0;
   int nMyExceptionId = 0;
	public void uncaughtException(Thread thread, Throwable e)
   {
      System.out.printf("An exception has been captured\n");
      System.out.printf("Thread: %s\n", thread.getId());
      Thread currentThread = Thread.currentThread(); // static method
      System.out.printf("Current Thread in method uncaughtException: %s\n", currentThread.getId());
      System.out.printf("Exception: %s: %s\n", e.getClass().getName(), e.getMessage());
      System.out.printf("Stack Trace: \n");
      e.printStackTrace(System.out);
      System.out.printf("Thread status: %s\n", thread.getState());
      nExceptionCount++;
      nMyExceptionId = nExceptionCount ; 
      if (GlobalVars.nNumTimesToReStart  > 0 )
      { // Just for demo, ReStart only 2 times.
    	  GlobalVars.nNumTimesToReStart  = GlobalVars.nNumTimesToReStart  - 1 ;
    	  //new Thread( new MyTask() ).start(); // Restart the Thread / Task -- in a new thread.
    	  MyTask myTask = new MyTask();
    	  Thread myThread = new Thread(myTask);
	      System.out.printf("Thread: %s\n", myThread.getId());
	      myThread.start();    	  
      }
   }
	public int getMyExceptionId() { return nMyExceptionId; }
}
class DefaultExceptionHandler implements Thread.UncaughtExceptionHandler
{ // This code is executed by the same thread in which Uncaught Exception occurred. 
	public void uncaughtException(Thread thread, Throwable e)
   {
      System.out.printf("In DefaultExceptionHandler ----- \n");
      System.out.printf("Thread: %s\n", thread.getId());
      Thread currentThread = Thread.currentThread(); // static method
      System.out.printf("Current Thread in method uncaughtException: %s\n", currentThread.getId());
      System.out.printf("Exception: %s: %s\n", e.getClass().getName(), e.getMessage());
      System.out.printf("Stack Trace: \n");
      e.printStackTrace(System.out);
      System.out.printf("In DefaultExceptionHandler -- Thread status: %s\n", thread.getState());
   }
}
/*  In Class Thread we have this Nested / Inner Interface.
     @FunctionalInterface
    public interface UncaughtExceptionHandler 
    {
         * Method invoked when the given thread terminates due to the given uncaught exception.
         * Any exception thrown by this method will be ignored by the Java Virtual Machine.
        void uncaughtException(Thread t, Throwable e);
    }
Note - here below, the same Thread Id gets printed in all places where we have code to print thread id.
Thread Id is a data member in Thread object.
 OUTPUT ----
Thread: 8
Thread: 8
123
234
345
An exception has been captured
Thread: 8
Current Thread in method uncaughtException: 8
Exception: java.lang.NumberFormatException: For input string: "XYZ"
Stack Trace: 
java.lang.NumberFormatException: For input string: "XYZ"
	at java.lang.NumberFormatException.forInputString(NumberFormatException.java:65)
	at java.lang.Integer.parseInt(Integer.java:580)
	at java.lang.Integer.parseInt(Integer.java:615)
	at MyTask.run(ThreadExceptionsApp.java:23)
	at java.lang.Thread.run(Thread.java:745)
Thread status: RUNNABLE
Thread: 10
Thread: 10
123
234
345
An exception has been captured
Thread: 10
Current Thread in method uncaughtException: 10
Exception: java.lang.NumberFormatException: For input string: "XYZ"
Stack Trace: 
java.lang.NumberFormatException: For input string: "XYZ"
	at java.lang.NumberFormatException.forInputString(NumberFormatException.java:65)
	at java.lang.Integer.parseInt(Integer.java:580)
	at java.lang.Integer.parseInt(Integer.java:615)
	at MyTask.run(ThreadExceptionsApp.java:23)
	at java.lang.Thread.run(Thread.java:745)
Thread status: RUNNABLE
*/