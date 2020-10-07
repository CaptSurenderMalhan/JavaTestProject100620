import java.util.Arrays;
public class CodilityTest082420 { // To Study Arrays see class - Arrays1D2D // TEST 08/24/20: began around 1am.
	public static void main(String[] args) {  //int [] arr = {1, 2, 3};  // {1, 3, 6, 4, 1, 2
		CodilityTest082420 c = new CodilityTest082420();
		int [] [] myArray = {{1,2} ,{ 2, 3} , {4,5}, {5,6}, {6,7}, {7,8} }; 
		int [] arr4 = myArray [1]; 
		int [] arr2 =  {4, 11, 3, 4, 11, 2, 2, 10, 6, 11, 4, 10}; // {4, 11, 3, 4, 11, 2, 2, 10, 6, 11, 4, 10};    [ [4, 11],[3, 4],[11, 2],[2, 10],[6, 11],[4, 10] ]
		int [] arr1 = {1, 2, 4, 3, 3, 4, 2, 3, 6, 5, 4, 5};  //{4, 3, 3, 4, 1, 2, 2, 3, 6, 5, 4, 5};  [ [4, 3],[3, 4],[1, 2],[2, 3],[6, 5],[4, 5] ] 
		int min = 20;
		for (int i=0; i<arr1.length-1; i++)
		{
			if (min > arr1[i])
				min = arr1[i];
		}
		String result = c.solution(arr1);
		System.out.println(result);
	}
	public void printDominoes(int [ ] [ ] twoDArray)
	{
		for (int i=0; i<twoDArray.length; i++)
			System.out.print(Arrays.toString( twoDArray[ i ] ));
	}
	void initPyramidLevelArrays( int [] [] pyramidLevelOne, int [] [] pyramidLevelTwo, int [] [] pyramidLevelThree )
	{ // 
	 	pyramidLevelOne = new int [3] [2]; 		
		pyramidLevelTwo = new int [2] [2]; 		
		pyramidLevelThree = new int [1] [2];
	}
    public String solution(int[] arr) {  // [[4, 3],[3, 4],[1, 2],[2, 3],[6, 5],[4, 5]]   ;; 
    	// Algorithm -- we need to find 1st domino for 1st level of the pyramid [1, 2].
    	// Then we find 1st domino for 2nd level [2, x] ;     	// then we find 2nd domino for 1st level [x,y]
    	// then we find 2nd domino for 2nd level [ y,z] ;     	// then we find 3nd domino for 1st level [z,a]
    	// then we see if the last domino left is [ x,y]
    	if ( (arr == null ) || (arr.length < 12) )
    		return "NO";
		int [] [] dominoesArr = new int [6] [2];
		int [] domino ; 
		int n = 0;
		for (int i=0; i<arr.length; i=i+2) //  []
		{ // Identify Dominos
			if ((arr[i] < 1) || (arr[i] > 12))  // Invalid Data
				return "NO";
			domino = new int [2];
			domino[0] = arr[i];
			domino[1] = arr[i+1];
			dominoesArr[n++] = domino;
		}
		printDominoes(dominoesArr);
		// Take a Domino and for it find Top and Right dominoes. 
		int [] firstDomino ;
		boolean bFoundNextDomino = false;    	int [] [] pyramidLevelOne = new int [3] [2]; 		
		int [] [] pyramidLevelTwo = new int [2] [2]; 		int [] [] pyramidLevelThree = new int [1] [2];
		for (int i = 0; i < dominoesArr.length-1 ; i++) // This loop is to find the first domino at level 1.   //    [  [4, 3],[3, 4],[1, 2],[2, 3],[6, 5],[4, 5]  ]
		{ // in this loop one by one pick up domino which will be the first - left most domino at 1st level and try to build the pyramid.
			initPyramidLevelArrays(pyramidLevelOne, pyramidLevelTwo, pyramidLevelThree);
			int [] dominoPosn = {1,1,1,1,1,1}; // Correct to declare this here. To keep track of dominos which have been used
			dominoPosn[i] = 0; // To keep track of dominos which have been used i.e. placed on the pyramid in current iteration/attempt to build pyramid.
			firstDomino = dominoesArr[i] ;
			pyramidLevelOne[0] = dominoesArr[i] ; //   1st Domino for Level 1
			for (int j = 0; j < 2 ; j++) 
			{ // For loop to Swap 1st element when not found.
				if (j==1)   // [[4, 3],[3, 4],[1, 2],[2, 3],[6, 5],[4, 5]]
				{
					initPyramidLevelArrays(pyramidLevelOne, pyramidLevelTwo, pyramidLevelThree);
					pyramidLevelOne[0] = swapDominoElements( firstDomino );
				} //    [  [4, 3],[3, 4],[1, 2],[2, 3],[6, 5],[4, 5]  ]
				pyramidLevelTwo[0] = getNextDomino(dominoesArr , pyramidLevelOne[0], dominoPosn );
				if (pyramidLevelTwo[0][0] ==0) // not found
					continue;
				pyramidLevelThree[0] = getNextDomino(dominoesArr , pyramidLevelTwo[0], dominoPosn ); // This order is required.
				if (pyramidLevelThree[0][0] ==0) // not found
					continue;	
				pyramidLevelOne[1] = getNextDomino(dominoesArr , pyramidLevelTwo[0], dominoPosn );
				if (pyramidLevelOne[1][0] ==0) // not found
					continue;	
				pyramidLevelTwo[1] = getNextDomino(dominoesArr , pyramidLevelOne[1], dominoPosn ); 
				if (pyramidLevelTwo[1][0] ==0) // not found
					continue;	
				pyramidLevelOne[2] = getNextDomino(dominoesArr , pyramidLevelTwo[1], dominoPosn );
				if (pyramidLevelOne[2][0] ==0) // not found
					continue;	
				if ( (pyramidLevelThree[0][1] != pyramidLevelTwo[1][0]) )
					continue;
				if (( builtPyramid(dominoPosn)) && ( (pyramidLevelThree[0][1] == pyramidLevelTwo[1][0]) ) )
					return "YES";
			}
		}
    	return "NO";
    }
    boolean builtPyramid(int [] dominoPosn)
    {
    	for (int i = 0; i < dominoPosn.length-1 ; i++) 
    	{
    		if (dominoPosn[i] == 1)
    			return false;
    	}
    	return true;
    }
    int [] getNextDomino(int [] [] dominoesArr, int [] currentDomino, int [] dominoPosn)
    {  //    [  [4, 3],[3, 4],[1, 2],[2, 3],[6, 5],[4, 5]  ]
    	int [] nextDomino = {0, 0};
    	boolean bFoundNextDomino = false; 
		for (int j = 0; j < dominoesArr.length ; j++) 
		{ //  for current domino, first nextDomino 
			if (dominoPosn[ j ] == 0) // if domino at this position in dominoesArr has already been used then skip / continue
				continue;
			if ( ( currentDomino[ 1 ] ==  dominoesArr[ j ] [ 0 ] ) )
			{
				bFoundNextDomino = true;
				nextDomino = dominoesArr[j] ;
				dominoPosn[ j ] = 0; // mark this domino as used
				return nextDomino;
			}	else if ( ( currentDomino[ 1 ] ==  dominoesArr[ j ] [ 1 ] ) )
			{
				bFoundNextDomino = true;
				nextDomino = dominoesArr[j] ;
				dominoPosn[ j ] = 0; // mark this domino as used		
				return swapDominoElements(nextDomino);
			} //    [  [4, 3],[3, 4],[1, 2],[2, 3],[6, 5],[4, 5]  ]
		}	    	
    	return nextDomino;
    }
    int [] swapDominoElements( int [] domino)
    {
		int temp = 0; // swap
		temp = domino[0] ;
		domino[0] = domino[1];
		domino[1] = temp; 
		return domino;
    }
}
/*
 * public String solution(int[] arr) {  // [[4, 3],[3, 4],[1, 2],[2, 3],[6, 5],[4, 5]]   ;; 
    	// Algorithm -- we need to find 1st domino for 1st level of the pyramid [1, 2].
    	// Then we find 1st domino for 2nd level [2, x]
    	// then we find 2nd domino for 1st level [x,y]
    	// then we find 2nd domino for 2nd level [ y,z]
    	// then we find 3nd domino for 1st level [z,a]
    	// then we see if the last domino left is [ x,y]
    	if ((arr == null )||(arr.length < 12))
    		return "NO";
		int [] [] dominoesArr = new int [6] [2];
		int [] domino ; 
		int j = 0;
		for (int i=0; i<arr.length; i=i+2) //  []
		{ // Identify Dominos
			domino = new int [2];
			domino[0] = arr[i];
			domino[1] = arr[i+1];
			dominoesArr[j++] = domino;
		}
		printDominoes(dominoesArr);
		int [] [] pyramidLevel1 = new int [3] [2];
		int [] [] pyramidLevel2 = new int [2] [2];
		int [] [] pyramidLevel3 = new int [1] [2];
		boolean bHasDominos = true;
		int [] dummyDomino = {0,0};
		int [] nextDomino =  {20,20};
		// get 1st Domino for Level 1
		for (int i = 0; i< dominoesArr.length-1 ; i++) // [[4, 3],[3, 4],[1, 2],[2, 3],[6, 5],[4, 5]]
		{// work in progress..... sorry incomplete
			if ( ( nextDomino[0] >  dominoesArr[i][0] ) || ( nextDomino[0] >  dominoesArr[i][1] )   )
			{
				nextDomino = dominoesArr[i] ;
				if (nextDomino[0] > nextDomino[1])
				{ // swap
					int temp = 0;
					temp = nextDomino[0] ;
					nextDomino[0] = nextDomino[1];
					nextDomino[1] = temp; 
				}
				dominoesArr[i] = dummyDomino;
			}
		}
		pyramidLevel1[0] = nextDomino;
		// Find 1st domino for 2nd level  
		//......
		// sorry incomplete
    	return "";
    }
 */
