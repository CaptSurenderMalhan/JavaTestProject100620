import java.util.Arrays;
import java.util.Arrays;
public class AmazonTestWalterMarin {  // Amazon FinTech Test 08/14/20 - Friday.
	public static void main(String[] args) {
		runTestSuite();
	}
	static void runTestSuite()
	{
		int [] wallNull = null; int [] wallInvalid = {1,2 };  // Test Data
		int [] wall1 =  {2,0,2}; int [] wall2 =  {3,0,0,2,0,4};  int [] wall3 =  {0,6,0,3,0,2,0,4,0};   // Test Data
		int [] wall4 =  {0,6,0,3,0,4,0,2,0}; int [] wall5 =  {6,3,4,0,2};  int [] wall6 = {0,0,3,2,0,0};  // Test Data
		int[] wall7 = {0,0,4,1,0,4}; int[] wall8 = {4}; int [] wall9 =  {7,6,0,3,0,4,1,2,4};
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
		int nResult = getUnitsOfWater( wall );
		if ( nResult  == nExpectedResult )  //Arrays.toString(blocks)   // getArrayAsString
			System.out.println( "Test Passed: Input: " + Arrays.toString(wall) + ". Expected Result: " + nExpectedResult + ". Received Result: " + nResult);
		else
			System.out.println( "Test Failed: Input: " + Arrays.toString(wall) + ". Expected Result: " + nExpectedResult + ". Received Result: " + nResult);
	}
	//------------------------------08/21/20---------------------------------------------
	static int getUnitsOfWater(int[] blocks) {
		if(blocks == null || blocks.length <= 2) {
			return 0;
		}
		int start = 0;
		int end = blocks.length - 1;
		start = getStartAfterLeadingZeros(blocks, start, end);
		end = getEndBeforeTrailingZeros(blocks, start, end);
		if(end - start <= 1) {
			return 0;
		}
		int units;
		if(blocks[start] <= blocks[end]) {
			units = getUnitsOfWaterFromStartToEnd(blocks, start, end);
		} else {
			units = getUnitsOfWaterFromEndToStart(blocks, start, end);
		}
		return units;
	}
	/**
	 * If we have leading zeros, then start after the leading zeros. 
	 * If height of starting index is less than height of next index, then start with next index instead.
	 * @param blocks
	 * @param start
	 * @param end
	 * @return
	 */
	private static int getStartAfterLeadingZeros(int[] blocks, int start, int end) {
		while((blocks[start] == 0 || blocks[start] < blocks[start + 1]) && end > start) {
			start++;
		}
		return start;
	}
	/**
	 * If we have trailing zeros, then end before the trailing zeros.
	 * If height of ending index is less than height of previous index, then end with previous index instead.
	 * @param blocks
	 * @param start
	 * @param end
	 * @return
	 */
	private static int getEndBeforeTrailingZeros(int[] blocks, int start, int end) {
		while((blocks[end] == 0 || blocks[end] < blocks[end - 1]) && end > start) {
			end--;
		}
		return end;
	}
	/**
	 * Only called by getUnitsOfWater, and only if height of starting block is less than or equal to ending block.
	 * Calculate units of water that fills the blocks between starting and ending block.
	 * If a block with a height greater than starting block is detected, then it is used for calculating units of water too.
	 * @param blocks
	 * @param start
	 * @param end
	 * @return
	 */
	private static int getUnitsOfWaterFromStartToEnd(int[] blocks, int start, int end) {
		int units = 0;
		int largestHeightBeforeEnd = blocks[start];
		for(int i = start + 1; i < end; i++) {
			if(blocks[i] > largestHeightBeforeEnd) {
				largestHeightBeforeEnd = blocks[i];
			} else {
				if(largestHeightBeforeEnd <= blocks[end]) {
					units += largestHeightBeforeEnd - blocks[i];
				} else {
					units += blocks[end] - blocks[i];
				}
			}
		}
		return units;
	}
	/**
	 * Only called by getUnitsOfWater, and only if height of starting block is greater than ending block.
	 * Calculate units of water that fills the blocks between ending and starting block.
	 * If a block with a height greater than ending block is detected, then it is used for calculating units of water too.
	 * @param blocks
	 * @param start
	 * @param end
	 * @return
	 */
	private static int getUnitsOfWaterFromEndToStart(int[] blocks, int start, int end) {
		int units = 0;
		int largestHeightAfterStart = blocks[end];
		for(int i = end - 1; i > start; i--) {
			if(blocks[i] > largestHeightAfterStart) {
				largestHeightAfterStart = blocks[i];
			} else {
				if(largestHeightAfterStart <= blocks[start]) {
					units += largestHeightAfterStart - blocks[i];
				} else {
					units += blocks[start] - blocks[i];
				}
			}
		}
		return units;
	}
	//---------------------------------------------------------------------------------------
	static int getUnitsOfWaterWalterMarin081920(int[] blocks) {
		if(blocks == null || blocks.length <= 2) {
			return 0;
		}
		int units = 0;
		int diff;
		int start = 0;
		int end = blocks.length - 1;
		while(blocks[start] == 0 && start > end) {//if there are leading zeros, start after them.
			start++;
		}
		while(blocks[end] == 0 && end > start) {//if there are trailing zeros, end before them.
			end--;
		}
		if(end - start <= 1) {
			return 0;
		}
		int h = blocks[start];
		int x = 0;
		for(int i = start + 1; i < end; i++) {
			diff = h - blocks[i];
			if(diff > 0) {//as long as height of current block less than a previous block height
				units += diff;//increment unit of water by the diff
				x++;
			} else if(diff < 0) {//if height of block greater than initial block, replace it
				h = blocks[i];
				x = 0;
			}
		}
		if(blocks[end] < h) { //if height of last block less than largest height, fix units
			diff = h - blocks[end];
			units -= (diff * x); //remove units of water that would have spilled over last block
		}
		return units;
	}
	static int getUnitsOfWaterWalterMarinV2081820(int[] blocks) {
		if(blocks == null || blocks.length < 2) {
			return 0;
		}
		int units = 0;
		int diff;
		int h = blocks[0];
		int end = blocks.length - 1;
		int x = 0;
		for(int i = 1; i < end; i++) {
			diff = h - blocks[i];
			if(diff > 0) {//as long as height of current block less than a previous block height
				units += diff;//increment unit of water by the diff
				x++;
			} else if(diff < 0) {//if height of block greater than initial block, replace it
				h = blocks[i];
				x = 0;
			}
		}
		if(blocks[end] < h) { //if height of last block less than largest height, fix units
			diff = h - blocks[end];
			units -= (diff * x); //remove units of water that would have spilled over last block
		}
		return units;
	}
	static int getUnitsOfWaterWalterMarin(int[] blocks) {
		int units = 0;
		int diff;
		int firstH = blocks[0];
		for(int i = 1; i < blocks.length; i++) {
			diff = firstH - blocks[i];
			if(diff > 0) {
				units += diff;
			}
		}
		return units;
	}
	static int unitsOfWaterTrapped( int [] wallArr)
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
	    // Outer for loop - Raising Level / Height - Vertical -  +Y Axix. Inner loop - Left to Right - Horozontal -- X-Axis.
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