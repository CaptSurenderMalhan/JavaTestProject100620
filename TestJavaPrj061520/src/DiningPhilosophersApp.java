
public class DiningPhilosophersApp {//Division, Remainder -- Modulus % : 10%3 = 1 ;  3%10 = 3 ; 3%5  = 3 ; 10/3 = 3 ;
	public static void main(String[] args) 
	{
		testDiningPhilosophers();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(DiningPhilosopher.sHaveEaten);		
	}
	static void testDiningPhilosophers()
	{
		new Thread(new DiningPhilosopher(1)).start();
		new Thread(new DiningPhilosopher(2)).start();
		new Thread(new DiningPhilosopher(3)).start();
		new Thread(new DiningPhilosopher(4)).start();
		new Thread(new DiningPhilosopher(5)).start();
	}
}
class DiningPhilosopher implements Runnable
{
	public static String sMsg = "";
	public static String sHaveEaten = "Have Eaten--";
	final static String sEating  = "EATING";
	final static String sThinking  = "THINKING";
	static String [] philosopherState = {sThinking, sThinking, sThinking, sThinking, sThinking  };
	final static Object  objLock  = new Object(); 
	volatile int nNextInLineToEat = 0;
	int nHasEatenNtimes = 0;
	int nMyId;
	DiningPhilosopher() { }
	DiningPhilosopher(int nMyId) { this.nMyId = nMyId ; }
	@Override
	public void run() {
		while (nHasEatenNtimes < 2)
		{
			System.out.println("Entered While Loop --" + nMyId);
			sMsg = "Entered While Loop --" + String.valueOf(nMyId);
			synchronized( objLock  )
			{
				System.out.println("Entered synchronized block --" + nMyId);
				int rightPid = nMyId - 1 ;
				if ( rightPid == 0 )
					rightPid = 5;				
				int leftPid = nMyId + 1; 
				if ( leftPid == 6 )
					leftPid = 1;
				if ( ( philosopherState[rightPid-1].equals(sThinking)) && (philosopherState[leftPid-1].equals(sThinking)))
				{  // array subscript is 0 based, so we minus 1
					try 
					{
						philosopherState[nMyId-1] = sEating ;
						Thread.sleep(50); // i.e. Eating for 50 ms
						philosopherState[nMyId-1] = sThinking ;
						System.out.println("Finished Eating --" + nMyId);
						sHaveEaten = sHaveEaten + String.valueOf(nMyId) + ", " ;
						nHasEatenNtimes++;
					} 
					catch (InterruptedException e) 
					{
						e.printStackTrace();
					}
					System.out.println("Calling notifyAll --" + nMyId);
					objLock.notifyAll();
				} 
				else
				{
					try {
						System.out.println("Calling wait --" + nMyId);
						objLock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
/* DiningPhilosophers P1 to P5, Forks F1 to F5. P1 has fork F1 to his Left and F2 to his Right.
 * P4 will have F4 on left and F5 on Right. P5 will have P5 on left and F1 on Right.
 Lets have some Discipline - all Philosophers must follow a schedule ---
 Lets keep it simple for now - P1P3, P2P4, P5P1; 
  Notes: You need to call wait() method on the object which is shared between two threads


 */
