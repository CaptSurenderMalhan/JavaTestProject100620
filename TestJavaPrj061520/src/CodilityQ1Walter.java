import java.util.Arrays;
import java.util.stream.IntStream;
public class CodilityQ1Walter { // see problem statement at end.
	public static void main(String[] args) {
		int[] costArr = {0,1,2};
		int x = costArr[ costArr.length];
		solutionTest();
	}
	public static void solutionTest() {
		CodilityQ1Walter codilityQ1 = new CodilityQ1Walter();
		//test for null
		String str = null;
		int[] costArr = {0,1,2};
		int minTotalCost = codilityQ1.solution(str, costArr);
		int expected = 0;
		assert expected == minTotalCost;
		System.out.println("Inputs: S = " + str + ", C = " + Arrays.toString(costArr) + "; Output: " + minTotalCost + "; Expected Output: " + expected);
		//test for unequal length
		str = "ab";
		minTotalCost = codilityQ1.solution(str, costArr);
		expected = 0;
		assert expected == minTotalCost;
		System.out.println("Inputs: S = " + str + ", C = " + Arrays.toString(costArr) + "; Output: " + minTotalCost + "; Expected Output: " + expected);
		//test for length less than 2
		str = "a";
		costArr = IntStream.of(2).toArray();
		minTotalCost = codilityQ1.solution(str, costArr);
		expected = 0;
		assert expected == minTotalCost;
		System.out.println("Inputs: S = " + str + ", C = " + Arrays.toString(costArr) + "; Output: " + minTotalCost + "; Expected Output: " + expected);
		//test two same chars in middle
		str = "abccbd";
		costArr = IntStream.of(0,1,2,3,4,5).toArray();
		minTotalCost = codilityQ1.solution(str, costArr);
		expected = 2;
		assert expected == minTotalCost;
		System.out.println("Inputs: S = " + str + ", C = " + Arrays.toString(costArr) + "; Output: " + minTotalCost + "; Expected Output: " + expected);
		//test each char having 1 duplicate adjacent char
		str = "aabbcc";
		costArr = IntStream.of(1,2,1,2,1,2).toArray();
		minTotalCost = codilityQ1.solution(str, costArr);
		expected = 3;
		assert expected == minTotalCost;
		System.out.println("Inputs: S = " + str + ", C = " + Arrays.toString(costArr) + "; Output: " + minTotalCost + "; Expected Output: " + expected);
		//test all char being the same
		str = "aaaa";
		costArr = IntStream.of(3,4,5,6).toArray();
		minTotalCost = codilityQ1.solution(str, costArr);
		expected = 12;
		assert expected == minTotalCost;
		System.out.println("Inputs: S = " + str + ", C = " + Arrays.toString(costArr) + "; Output: " + minTotalCost + "; Expected Output: " + expected);
		//test having non-adjacent duplicate chars
		str = "ababa";
		costArr = IntStream.of(10,5,10,5,10).toArray();
		minTotalCost = codilityQ1.solution(str, costArr);
		expected = 0;
		assert expected == minTotalCost;
		System.out.println("Inputs: Str = " + str + ", CostArray = " + Arrays.toString(costArr) + "; Output: " + minTotalCost + "; Expected Output: " + expected);
	}
	public int solution(String str, int[] costArr) {
		if(str == null || costArr == null || str.length() != costArr.length || costArr.length < 2) {
			return 0;
		} 	// iterated through each char and elems in costArr, keep track of prev char, and index of that char
		char[] charArr = str.toCharArray();
		int prevCharStartIndex = 0; int minTotalCost = 0;
		char prevChar = charArr[prevCharStartIndex];
		for (int i = 1; i <= costArr.length; i++) 
		{  // if current char is same as prev char, continue, else, find max cost, then increment total cost by
			// costs that do not equal min,  then replace prev char with current.
			if (i == costArr.length || prevChar != charArr[i]) 
			{
				if (prevCharStartIndex + 1 != i) 
				{
					minTotalCost += getMinTotalCostsForChar(costArr, prevCharStartIndex, i);
				}
				if (i < costArr.length) 
				{
					prevChar = charArr[i];
					prevCharStartIndex = i;
				}
			}
		}
		return minTotalCost;
	}
	private int getMinTotalCostsForChar(int[] costArr, int start, int end) {
		int max = costArr[start];
		int minTotalCosts = max;
		for (int i = start + 1; i < end; i++) {
			minTotalCosts += costArr[i];
			if (costArr[i] > max) {
				max = costArr[i];
			}
		}
		minTotalCosts -= max;
		return minTotalCosts;
	}
}
/**
 * You are given a string S. Deletion of the K-th letter of S costs C[K].
 * After deleting a letter, the costs of deleting other letters do not
 * change. For example, for S - "ab" and C - [1,3], after deleting 'a',
 * deletion of 'b' will still cost 3.
 * 
 * You want to delete some letters from S to obtain a string without
 * two identical letters next to each other. What is the minimum total
 * cost of deletions to achieve such a string?
 * @author Walter J
 *
 */