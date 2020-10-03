import java.util.Arrays;
public class AmazonInterviewQ_081920WM {
	public static void main(String[] args) {
		getUnitsOfWaterLastBlockHeightEqualToFirstTest();
		getUnitsOfWaterLastBlockHeightGreaterThanFirstTest();
		getUnitsOfWaterLastBlockHeightLessThanFirstTest();
		getUnitsOfWaterOneBlockTest();
		getUnitsOfWaterLargestHeightBetweenBlocksTest();
		getUnitsOfWaterNullTest();
		getUnitsOfWaterLeadingZerosTest();
		getUnitsOfWaterTrailingZerosTest();
		getUnitsOfWaterLeadingAndTrailingZerosTest();
		test1();
		test2();
		}
	static void test1() {
		System.out.println("::test1");
		int[] blocks = {0, 6, 0, 3, 0, 4, 0, 2, 0};
		System.out.println("Blocks = " + Arrays.toString(blocks));
		int expectedUnits = 11;
		System.out.println("Expected Units of water = " + expectedUnits);
		int units = getUnitsOfWater(blocks);
		System.out.println("Units of water = " + units);
		assert expectedUnits == units;
		System.out.println();
	}
	static void test2() {
		System.out.println("::test2");
		int[] blocks = {6, 3, 4, 0, 2};
		System.out.println("Blocks = " + Arrays.toString(blocks));
		int expectedUnits = 3;
		System.out.println("Expected Units of water = " + expectedUnits);
		int units = getUnitsOfWater(blocks);
		System.out.println("Units of water = " + units);
		assert expectedUnits == units;
		System.out.println();
	}
	static void getUnitsOfWaterLastBlockHeightEqualToFirstTest() {
		System.out.println("::getUnitsOfWaterLastBlockHeightEqualToFirstTest");
		int[] blocks = {2,0,2};
		System.out.println("Blocks = " + Arrays.toString(blocks));
		int expectedUnits = 2;
		System.out.println("Expected Units of water = " + expectedUnits);
		int units = getUnitsOfWater(blocks);
		System.out.println("Units of water = " + units);
		assert expectedUnits == units;
		System.out.println();
	}
	static void getUnitsOfWaterLastBlockHeightGreaterThanFirstTest() {
		System.out.println("::getUnitsOfWaterLastBlockHeightGreaterThanFirstTest");
		int[] blocks = {3,0,0,2,0,4};
		System.out.println("Blocks = " + Arrays.toString(blocks));
		int expectedUnits = 10;
		System.out.println("Expected Units of water = " + expectedUnits);
		int units = getUnitsOfWater(blocks);
		System.out.println("Units of water = " + units);
		assert expectedUnits == units;
		System.out.println();
	}
	static void getUnitsOfWaterLastBlockHeightLessThanFirstTest() {
		System.out.println("::getUnitsOfWaterLastBlockHeightLessThanFirstTest");
		int[] blocks = {3,1,0,4,1,2};
		System.out.println("Blocks = " + Arrays.toString(blocks));
		int expectedUnits = 6;
		System.out.println("Expected Units of water = " + expectedUnits);
		int units = getUnitsOfWater(blocks);
		System.out.println("Units of water = " + units);
		assert expectedUnits == units;
		System.out.println();
	}
	static void getUnitsOfWaterOneBlockTest() {
		System.out.println("::getUnitsOfWaterOneBlockTest::");
		int[] blocks = {1};
		System.out.println("Blocks = " + Arrays.toString(blocks));
		int expectedUnits = 0;
		System.out.println("Expected Units of water = " + expectedUnits);
		int units = getUnitsOfWater(blocks);
		System.out.println("Units of water = " + units);
		assert expectedUnits == units;
		System.out.println();
	}
	static void getUnitsOfWaterNullTest() {
		System.out.println("::getUnitsOfWaterNullTest");
		int[] blocks = null;
		System.out.println("Blocks = " + Arrays.toString(blocks));
		int expectedUnits = 0;
		System.out.println("Expected Units of water = " + expectedUnits);
		int units = getUnitsOfWater(blocks);
		System.out.println("Units of water = " + units);
		assert expectedUnits == units;
		System.out.println();
	}
	static void getUnitsOfWaterLargestHeightBetweenBlocksTest() {
		System.out.println("::getUnitsOfWaterLargestHeightBetweenBlocksTest");
		int[] blocks = {1,0,0,4,1,0,2};
		System.out.println("Blocks = " + Arrays.toString(blocks));
		int expectedUnits = 5;
		System.out.println("Expected Units of water = " + expectedUnits);
		int units = getUnitsOfWater(blocks);
		System.out.println("Units of water = " + units);
		assert expectedUnits == units;
		System.out.println();
	}
	
	static void getUnitsOfWaterLeadingZerosTest() {
		System.out.println("::getUnitsOfWaterLeadingZerosTest");
		int[] blocks = {0,0,4,1,0,4};
		System.out.println("Blocks = " + Arrays.toString(blocks));
		int expectedUnits = 7;
		System.out.println("Expected Units of water = " + expectedUnits);
		int units = getUnitsOfWater(blocks);
		System.out.println("Units of water = " + units);
		assert expectedUnits == units;
		System.out.println();
	}
	static void getUnitsOfWaterTrailingZerosTest() {
		System.out.println("::getUnitsOfWaterTrailingZerosTest");
		int[] blocks = {4,0,3,0,0};
		System.out.println("Blocks = " + Arrays.toString(blocks));
		int expectedUnits = 3;
		System.out.println("Expected Units of water = " + expectedUnits);
		int units = getUnitsOfWater(blocks);
		System.out.println("Units of water = " + units);
		assert expectedUnits == units;
		System.out.println();
	}
	static void getUnitsOfWaterLeadingAndTrailingZerosTest() {
		System.out.println("::getUnitsOfWaterLeadingAndTrailingZerosTest");
		int[] blocks = {0,0,3,2,0,0};
		System.out.println("Blocks = " + Arrays.toString(blocks));
		int expectedUnits = 0;
		System.out.println("Expected Units of water = " + expectedUnits);
		int units = getUnitsOfWater(blocks);
		System.out.println("Units of water = " + units);
		assert expectedUnits == units;
		System.out.println();
	}
	/**
	 * 
	 * @param blocks
	 * @return
	 */
	static int getUnitsOfWater(int[] blocks) {
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

}
/**
Given an array of non-negative integers representing height of blocks where the width of each block is 1, 
compute how much water can be trapped in between blocks after raining.

For example, with the structure [2,0,2] like below:  
| |  
|_|

Here we can trap 2 units of water.

Example2: With the structure [3,0,0,2,0,4], we can trap 10 units of water.  
      |
|     |
|   | |
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