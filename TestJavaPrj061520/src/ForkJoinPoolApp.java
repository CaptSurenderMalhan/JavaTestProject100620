import java.util.Arrays;
import java.util.concurrent.RecursiveTask;
public class ForkJoinPoolApp {
	public static void main(String[] args) 
	{
		int [] nArr  = { 2,2,2,2,2,2,2,2,2,2};
		AddNumbersTask fjt = new AddNumbersTask(nArr);
		System.out.println(fjt.compute());		
	}
}
class AddNumbersTask extends RecursiveTask<Integer> {
	   final int nThreshHold = 2; // Task Size / Task Unit.
	   int [] nArr ;
	   AddNumbersTask (int [] nArr ) { this.nArr = nArr; }
	   public Integer compute() {
	     if (nArr.length <= nThreshHold)
	     {
	    	 int nSum = 0;
	    	for (int i = 0; i < nArr.length; i++)
	    		nSum += nArr[i];
	    	 return nSum;
	     }
	     int [] nArr1 ;
	     int [] nArr2 ; 
	     nArr1 = Arrays.copyOfRange(nArr, 0, (nArr.length)/2);
	     nArr2 = Arrays.copyOfRange(nArr, (nArr.length)/2, nArr.length);
        AddNumbersTask fjt1 = new AddNumbersTask(nArr1);
	     fjt1.fork();
	     AddNumbersTask fjt2 = new AddNumbersTask(nArr2);
	     return fjt2.compute() + fjt1.join();
	   }
 }
/*NOTE: The algorithm to break down the task involves copying each half of the array. This is not good. 
 * A better algo would be to provide values of i and j - to each task - add numbers from position i to j and pass in the same array. 
 * We may even hold the array as a static data member - so we dont need to pass it.
 * */
