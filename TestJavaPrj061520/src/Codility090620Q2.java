public class Codility090620Q2 {
	public static void main(String[] args) {
		Codility090620Q2  c = new Codility090620Q2();
		int[] intArr = {1, 3, 6, 4, 1, 2 } ;
		String str = "" ;
		c.solution(intArr);
	}
	public int solution(int[] blockArr) {
		if ( (blockArr == null) || (blockArr.length < 2)) 
			return 0;
		int distance = 0; int maxDistance = 0; 	
		for (int i = 0; i < blockArr.length; i++) 
		{
			if ((blockArr[i] < 1) || (blockArr[i] > 1000000000) ) // Invalid Data - Values have to be in this range.
				return 0; 
			distance = 1;
			distance += getMaxDistanceFromLeftBlock(blockArr, i);
			distance += getMaxDistanceFromRightBlock(blockArr, i);	
			if (distance > maxDistance) 
				maxDistance = distance;
		}
		return maxDistance;
	}
	private int getMaxDistanceFromLeftBlock(int[] blockArr, int currIndex) {
		int distance = 0;  		int maxHeight = blockArr[currIndex];
		for (int i = currIndex - 1; i >= 0; i--) 
		{
			if (blockArr[i] < maxHeight) 
				break;
			else if (blockArr[i] > maxHeight) 
			{
				maxHeight = blockArr[i];
				distance++;
			} 
			else 
				distance++;
		}
		return distance;
	}
	private int getMaxDistanceFromRightBlock(int[] blockArr, int currIndex) 
	{
		int distance = 0;  		int maxHeight = blockArr[currIndex];
		for (int i = currIndex + 1; i < blockArr.length; i++) 
		{
			if (blockArr[i] < maxHeight) 
				break;
			else if (blockArr[i] > maxHeight) 
			{
				maxHeight = blockArr[i];
				distance++;
			}
			else 
				distance++;
		}
		return distance;
	}
}
/*
 * 	private int getDistance(int[] blockArr, int currIndex) {
		int distance = 1;
		distance += getMaxDistanceFromLeftBlock(blockArr, currIndex);
		distance += getMaxDistanceFromRightBlock(blockArr, currIndex);
		return distance;
	}
 */
