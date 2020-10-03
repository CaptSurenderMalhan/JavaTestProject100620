import java.util.Arrays;
public class AmazonTest081420 {  // Amazon FinTech Test 08/14/20 - Friday. Solution - Capt. Surender Malhan.
	public static void main(String[] args) {
		runTestSuite();
	}
	static void runTestSuite()
	{
		int [] wallNull = null; int [] wallInvalid = {1,2 };  // Test Data
		int [] wall1 =  {2,0,2}; int [] wall2 =  {3,0,0,2,0,4};  int [] wall3 =  {0,6,0,3,0,2,0,4,0};   // Test Data
		int [] wall4 =  {0,6,0,3,0,4,0,2,0}; int [] wall5 =  {6,3,4,0,2};  int [] wall6 = {0,0,3,2,0,0};  // Test Data
		int[] wall7 = {0,0,4,1,0,4}; int[] wall8 = {4};int [] wall9 =  {7,6,0,3,0,4,1,2,4};
		test(wallNull, 0);
		test(wallInvalid, 0);
		test(wall1, 2);
		test(wall2, 10);
		test(wall3, 15);
		test(wall4, 11);
		test(wall5, 3);
		test(wall6, 0);
		test(wall7, 7);
		test(wall8, 0);
		test(wall9, 14);
	}
	static void test( int [] wall , int nExpectedResult)
	{  //unitsOfWaterTrapped  // getUnitsOfWaterWalterMarin081920
		int nResult = getUnitsOfWaterTrapped( wall );
		if ( nResult  == nExpectedResult )  //Arrays.toString(blocks)   // getArrayAsString
			System.out.println( "Test Passed: Input: " + Arrays.toString(wall) + ". Expected Result: " + nExpectedResult + ". Received Result: " + nResult);
		else
			System.out.println( "Test Failed: Input: " + Arrays.toString(wall) + ". Expected Result: " + nExpectedResult + ". Received Result: " + nResult);
	}
	static String getArrayAsString(int [] intArr )
	{
		if (intArr == null)
			return "Null" ;
		StringBuffer sb = new StringBuffer();
		sb.append("[ ");
		for (int i = 0 ; i < intArr.length ; i++)
		{
			sb.append(intArr[i]);
			if ( i < intArr.length )
				sb.append(", ");
		}
		sb.append(" ]");
		return sb.toString();
	}
	static int getUnitsOfWaterTrapped( int [] wallArr)
	{  
	    if (( wallArr == null) || (wallArr.length < 3)) // minimum length of array has to be 3 so as for the wall to hold water.
	    	return 0;
	    // One simple optimization - to find starting wall position, ending wall position and second max wall height.
		int  unitsFilled = 0 ; int leftWallPosn = -1, rightWallPosn = 0, leftWallHeight, rightWallHeight, minHeight = 0, maxHeight = 0, secondMaxHeight = 0; 
	    for (int i = 0; i < wallArr.length ; i++)
	    {
	        if (wallArr[i] > 0)  //[3,0,0,2,0,4],
	        {
	        	if ( leftWallPosn == -1) 
	        		{  leftWallPosn = i; leftWallHeight = wallArr[i] ; }
	        	else 
	        		{ rightWallPosn = i; rightWallHeight = wallArr[i] ; }
	        	if ( minHeight >= wallArr[i])
	        		minHeight = wallArr[i];
	        	if ( maxHeight <= wallArr[i])
	        	{
	        		if ( secondMaxHeight <= maxHeight)
	        			secondMaxHeight = maxHeight;  
	        		maxHeight = wallArr[i];
	        	}
	        	else if ( secondMaxHeight <= wallArr[i])
	        		secondMaxHeight = wallArr[i];
	        }
	    } // [3,0,0,2,0,4],  [0,6,0,3,0,2,0,4,0],  {0,6,0,3,0,4,0,2,0}; {6,3,4,0,2};
	    // We can think of wallArr as a 2D array -- we have 2 for loops - 
	    // Outer for loop - Raising Level / Height - Vertical -  +Y Axis. Inner loop - Left to Right - Horozontal -- X-Axis.
	    for (int height = 0 ; height < secondMaxHeight ; height++) // Height of Water Rising - One Level at a time.  
	    {
		    for (int i = leftWallPosn + 1; i < rightWallPosn ; i++)   // Inner Loop - Filling in - Left to Right at each Level.
		    { //structureArr[i] is < both left max wall ht and right max wall ht. -- WRONG CONDITION -- if   (( structureArr[i] < leftMaxWallHt(structureArr , i ) )  && ( structureArr[i] < rightMaxWallHt( structureArr , i ) ) )
		    	// both left max wall ht and right max wall ht are > structureArr[i] and > current height.
		    	if   ( (height >= wallArr[i] ) &&  ( height < leftMaxWallHt(wallArr , i ) )  && ( height < rightMaxWallHt( wallArr , i )  ) )
	        		unitsFilled++;
		    }
	   }
        return unitsFilled;
	}
	static int leftMaxWallHt( int [] structureArr, int i ) 
	{ //  from current position in array i - travel left to find max wall height.
		int leftMax = 0;
		for (int j = 0 ; j < i; j++)
		{
			if ( leftMax < structureArr[j])
				leftMax = structureArr[j];
		}
		return leftMax;
	}
	static int rightMaxWallHt( int [] structureArr, int i ) 
	{//  from current position in array i - travel right to find max wall height.
		int rightMax = 0;
		for (int j = i + 1 ; j < structureArr.length; j++)
		{
			if ( rightMax < structureArr[j])
				rightMax = structureArr[j];
		}
		return rightMax;		
	}	
} 
/**
            |
|* * * * |
|* * | * |
|* * | * |

Given an array of non-negative integers representing height of blocks where the width of each block is 1, 
compute how much water can be trapped in between blocks after raining.

For example, with the structure [2,0,2] like below:  
|  |  
|_|

Here we can trap 2 units of water.

Example2: With the structure [3,0,0,2,0,4], we can trap 10 units of water.  
         |
|        |
|     |  |
|_ _|_|
------------------------------------------------------
End of Question.
This below he drew to explain how 10 units of water - each star is one unit of water.
            |
|* * * * |
|* * | * |
|* * | * |

 3 3 1 3 = 10 
**/