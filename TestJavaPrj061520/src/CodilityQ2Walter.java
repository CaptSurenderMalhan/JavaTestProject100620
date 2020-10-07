import java.util.Arrays;
import java.util.stream.IntStream;
public class CodilityQ2Walter{
	public static void main(String[] args) {
		solutionTest();
	}
	public static void solutionTest() {
		CodilityQ2Walter codilityQ2 = new CodilityQ2Walter();
		//test null
		int distance = codilityQ2.solution(null);
		int expected = 0;
		assert expected == distance;
		System.out.println("Input: " + null + "; Output: " + distance + "; Expected Output: " + expected);
		//test blocks length less than 2
		int[] blocks = {1};
		distance = codilityQ2.solution(blocks);
		expected = 0;
		assert expected == distance;
		System.out.println("Input: " + Arrays.toString(blocks) + "; Output: " + distance + "; Expected Output: " + expected);
		//
		blocks = IntStream.of(2,6,8,5).toArray();
		distance = codilityQ2.solution(blocks);
		expected = 3;
		assert expected == distance;
		System.out.println("Input: " + Arrays.toString(blocks) + "; Output: " + distance + "; Expected Output: " + expected);
		//
		blocks = IntStream.of(1,5,5,2,6).toArray();
		distance = codilityQ2.solution(blocks);
		expected = 4;
		assert expected == distance;
		System.out.println("Input: " + Arrays.toString(blocks) + "; Output: " + distance + "; Expected Output: " + expected);
		//two blocks of same height
		blocks = IntStream.of(1,1).toArray();
		distance = codilityQ2.solution(blocks);
		expected = 2;
		assert expected == distance;
		System.out.println("Input: " + Arrays.toString(blocks) + "; Output: " + distance + "; Expected Output: " + expected);
	}
	public int solution(int[] blocks) {
		if (blocks == null || blocks.length < 2) {
			return 0;
		}
		int longestDistance = 0;
		int distance;
		for (int i = 0; i < blocks.length; i++) {
			distance = getDistance(blocks, i);
			if (distance > longestDistance) {
				longestDistance = distance;
			}
		}
		return longestDistance;
	}
	private int getDistance(int[] blocks, int currIndex) {
		int distance = 1;
		distance += getDistanceFromLeftBlocks(blocks, currIndex);
		distance += getDistanceFromRightBlocks(blocks, currIndex);
		return distance;
	}
	private int getDistanceFromLeftBlocks(int[] blocks, int currIndex) {
		int distance = 0;
		int largestHeight = blocks[currIndex];
		for (int i = currIndex - 1; i >= 0; i--) {
			if (blocks[i] < largestHeight) {
				break;
			} else if (blocks[i] > largestHeight) {
				largestHeight = blocks[i];
				distance++;
			} else {
				distance++;
			}
		}
		return distance;
	}
	private int getDistanceFromRightBlocks(int[] blocks, int currIndex) {
		int distance = 0;
		int largestHeight = blocks[currIndex];
		for (int j = currIndex + 1; j < blocks.length; j++) {
			if (blocks[j] < largestHeight) {
				break;
			} else if (blocks[j] > largestHeight) {
				largestHeight = blocks[j];
				distance++;
			} else {
				distance++;
			}
		}
		return distance;
	}
}
/**  * There are N blocks, numbered from 0 to N-1, arranged in a row. A
 * couple of frogs were sitting together on one block when they had a
 * terrible quarrel. Now they want to jump away from one another so
 * that the distance between them will be as large as possible. The
 * distance between blocks numbered J and K, where J <= K, is
 * computed as K - J + 1. The frogs can only jump up, meaning that
 * they can move from one block to another only if the two blocks are
 * adjacent and the second block is of the same or greater height as
 * the first. What is the longest distance that they can possibly create
 * between each other, if they also chose to sit on the optimal starting
 * block initially?
 * @author Walter J  */