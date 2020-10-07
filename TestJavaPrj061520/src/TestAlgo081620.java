import java.util.HashMap;
public class TestAlgo081620 {//Division, Remainder -- Modulus % : 10%3 = 1 ;  3%10 = 3 ; 10/3 = 3 ;
	public static void main(String[] args) {
	//	findPeakElementTest();
		SplitStringByNewLineExample();
		int []  numArr = { 2,7,11,15 } ;  int target = 9;
		findTwoNumbersInArrayWhoseSumEqualsTarget(numArr, target);
		int [] numArr1 =  {1,0,2,0,0,3,4,0,5,0}; int [] numArr2 =  {3,0,0,2,0,4};  int [] numArr3 =  {0,6,0,3,0,2,0,4,0};   // Test Data
		shiftAllZerosInArrayToTheEnd(numArr1);
	}
	public static void SplitStringByNewLineExample ()
	{
        StringBuilder sb = new StringBuilder("");
        sb.append("aaa \n");
        sb.append("bbb   \r\n");
        sb.append("ccc\n");
        sb.append("\n");
        sb.append("ddd\r\n");
        sb.append("\r\n");
        sb.append("eee\n");

        String text = sb.toString();
        System.out.println("---Original---");
        System.out.println(text);

        System.out.println("---Split---");
        int count = 1;

        // split by new line
        String[] lines = text.split("\\r?\\n");
        for (String line : lines) {
            System.out.println("line " + count++ + " : " + line);
        }
	}
	
	public static int [] findTwoNumbersInArrayWhoseSumEqualsTarget( int [ ] numArr, int target )
	{ // Two Sum. Return as array. Input int []  numArr = { 2,7,11,15 } ;  int target = 9;  Output result = {0,1}
		int [] result = new int [2]; int difference = 0;
		HashMap < Integer, Integer> myMap = new HashMap(); 
		 for (int i = 0 ; i < numArr.length; i++)
		{
			 difference = target - numArr[i] ;
			 if (myMap.containsKey(difference))
			 {
				 result[0] = myMap.get(difference);
				 result[1] = i;  // this has to be second line so as to get output in ascending order like {0.1}
				 return result;
			 }
			 else
				 myMap.put(numArr[i], i);
		}
		return result;
	}
	public static void shiftAllZerosInArrayToTheEnd( int [ ] numArr )
	{ // An Array has integers and zeros. Shift all Zeros to the end.
	    if (( numArr == null) || (numArr.length < 2)) 
	    	return ;
	    int index = 0;
	    for (int i = 0 ; i < numArr.length; i++)
	    {
	    	if ( ! (numArr[i] == 0) )
	    		numArr[index++] = numArr[i];
	    }
	    for (int i = index ; i < numArr.length; i++)
	    {
	    		numArr[i] = 0;
	    }	    
	}
	public static int reverseDigitsOfInteger(int num)
	{ // ip- 123  ; op - 321
		if ((num == 0 ) || ((num > -10) && (num < 10)))
				return num;
		boolean isNegative = false;
		if (num < 0) // negative , make it positive number
		{
			num *= -1;
			isNegative = true;
		}
		long reversedNum = 0;
		while (num > 0)
		{
			reversedNum = (reversedNum * 10) + (num%10);  // 123 % 10 = 3
			num = num/10; // 123/10 = 12 
		}
		if ( reversedNum > Integer.MAX_VALUE)
			return 0;
		if (isNegative)
			reversedNum *= -1;
		int reversedNumInt = (int) reversedNum;
		//return reversedNumInt;
		return (int) (isNegative ? (-1) * reversedNum : reversedNum); // can also write this way.
		
	}
	public static void findPeakElementTest()
	{
		int [ ] numArr1 = { 1,2,3,1 };  int [ ] numArr2 = { 1,2,1,3,5,6,4 }; int [ ] numArr3 = { 1,2,3,4,5,6,4 }; 
		int posn = 0;
		posn = findPeakElement( numArr2 );
		posn = findPeakElement( numArr2 );
		posn = findPeakElement( numArr3 );		
	}
	public static int findPeakElement( int [ ] numArr )
	{ // Binary Search for 1st Peak
		int mid = 0 , left = 0, right = numArr.length - 1;
		while ( left < right )
		{
			mid = left + (right - left)/2;
			if ( numArr[ mid ] < numArr[ mid + 1])
				left =  mid + 1;
			else
				right =  mid ;
		}
		return left;
	}
}
/*Reverse Digits in an integer. If after reversing the integer value is greater than max int val or less than min int val then return 0 
 * -------------------------------------------------------
 * A peak element is an element that is greater than its neighbors.
 * Given an input array nums where nums[i] != nums[i+1], find a peak element and return its index.
 * If Array has multiple peaks - return any one of the peaks.
 *  Input: {1,2,3,1}  Output - 2  -- i.e. third element 3. 3 is peak and fn should return the index number 2.
 * Input: {1,2,1,3,5,6,4}  Output - 1 or 5 -- i.e. Peak Element is either 2 or 6. 
 */
		