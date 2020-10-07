import java.util.*;
public class Codility090620Q1 {
	public static void main(String[] args) {
		int n = Runtime.getRuntime().availableProcessors();
		Codility090620Q1  c = new Codility090620Q1();
		int[] intArr = {1, 3, 6, 4, 1, 2 } ;
		String str = "" ;
		c.solution(str, intArr);
	}
	public int solution(String str, int[] costArr) {
		if (!validData(str, costArr))
			return 0;
		// iterate through each char in costArr, keep track of previous char, and index of that char
		int prevCharStartIndex = 0; int minTotalCost = 0;
		char[] charArr = str.toCharArray();
		char prevChar = charArr[prevCharStartIndex];
		for (int i = 1; i <= charArr.length; i++) 
		{  // if current char is same as previous char, continue, else, find max cost, then increment total cost by
			// costs that do not equal min,  then replace prev char with current.
			if ( (i == costArr.length ) || ( prevChar != charArr[i] )) 
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
	private int getMinTotalCostsForChar(int[] costArr, int start, int end) 
	{
		int max = costArr[start]; 	int minTotalCosts = max;
		for (int i = start + 1; i < end; i++) 
		{
			minTotalCosts += costArr[i];
			if (costArr[i] > max) 
			{
				max = costArr[i];
			}
		}
		minTotalCosts -= max;
		return minTotalCosts;
	}
	boolean validData(String str, int[] costArr)
	{
		if( ( str == null ) || ( costArr == null ) || (str.length() < 2) || (str.length() != costArr.length ) )
			return false;
		if (costArr.length > 100000)
			return false;	
		for (int i = 0; i < costArr.length; i++) 		
		{
			if (( costArr[i] < 0 ) || ( costArr[i] > 1000 ))
				return false;	
		}
		for (int i = 0; i < str.length(); i++) 		
		{
			if (! Character.isLowerCase(str.charAt(i)) )
				return false;	
		}		
		return true;
	}
}
