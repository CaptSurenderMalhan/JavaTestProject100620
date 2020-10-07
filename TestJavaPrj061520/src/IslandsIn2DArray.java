
public class IslandsIn2DArray { //Division, Remainder -- Modulus % : 10%3 = 1 ;  3%10 = 3 ; 10/3 = 3 ;
	int [ ] [ ] twoDArray  =  { { 1,1, 0, 0,0 }, { 1,1, 0, 0,0  }, {0,0, 1, 0,0  } , { 0,0, 0, 1,1  }} ; 
	int currentIsland = -1 ;
	// int [ ] [ ] twoDArrayB  = new int [ 4 ] [ 5 ]
	public static void main(String[] args) {
		IslandsIn2DArray obj = new IslandsIn2DArray();
		obj.countIslands();
	}
	void countIslands()
	{
		boolean bHasIslands = true;
		while (bHasIslands)
		{
			for (int i = 0; i < twoDArray.length; i++) 
			{
				for (int j = 0; j < twoDArray[0].length; j++)
				{
					if (twoDArray[ i ] [ j ] == 1)
						markThisIsland( i,  j);
				}
			}			
		}
	}
	void markThisIsland(int i, int j)
	{
		if (( i < 0 ) || ( j < 0 ))
			return;
		if (( i >=  twoDArray.length ) || ( j >=  twoDArray.length ))
			return;		
		if (twoDArray[ i ] [ j ] == 1)
		{
			markThisIsland( i,  j);		
		}
	}
}
