public class AmazonTest081420V1 {
	public static void main(String[] args) {
		int units = 0;
		int [] wall1 =  {2,0,2}; int [] wall2 =  {3,0,0,2,0,4};  int [] wall3 =  {0,6,0,3,0,2,0,4,0};  int [] wall4 =  {0,6,0,3,0,4,0,2,0};
		int [] wall5 =  {6,3,4,0,2};
		units = unitsOfWaterTrapped( wall5 ); // 3 
		units = unitsOfWaterTrapped( wall1 ); // 2
		units = unitsOfWaterTrapped( wall2 ); // 10
		units = unitsOfWaterTrapped( wall3 ); // 15
		units = unitsOfWaterTrapped( wall4 ); // 11	
	}
	static int unitsOfWaterTrapped( int [] structureArr)
	{  // fw - first wall - Left Wall position, lw - last wall - Right Wall position, fwh-First Wall Height, lwh - LastWallHeigth minh, maxh 
	    int w1, w2, b, h1, units = 0 ; int lw = -1, rw = 0, lwh, rwh, minh = 0, maxh = 0, sndmaxh = 0; // sndmaxh - second max height
	    for (int i = 0; i < structureArr.length ; i++)
	    {
	        if (structureArr[i] > 0)  //[3,0,0,2,0,4],
	        {
	        	if ( lw == -1) 
	        		{  lw = i; lwh = structureArr[i] ; }
	        	else 
	        		{ rw = i; rwh = structureArr[i] ; }
	        	if ( minh >= structureArr[i])
	        		minh = structureArr[i];
	        	if ( maxh <= structureArr[i])
	        	{
	        		if ( sndmaxh <= maxh)
	        			sndmaxh = maxh;  
	        		maxh = structureArr[i];
	        	}
	        	else if ( sndmaxh <= structureArr[i])
	        		sndmaxh = structureArr[i];
	        }
	    } // [3,0,0,2,0,4],  [0,6,0,3,0,2,0,4,0],  {0,6,0,3,0,4,0,2,0}; {6,3,4,0,2};
	    for (int j = 0 ; j < sndmaxh ; j++) // Height - Water Rising - One Level at a time.  
	    {
		    for (int i = lw + 1; i < rw ; i++)   // Filling in - Left to Right at each Level.
		    { //structureArr[i] is < both left max wall ht and right max wall ht. -- WRONG CONDITION -- if   (( structureArr[i] < leftMaxWallHt(structureArr , i ) )  && ( structureArr[i] < rightMaxWallHt( structureArr , i ) ) )
		    	// both left max wall ht and right max wall ht are > structureArr[i] and j - current ht.
	        	//if   ( (j >= structureArr[i] ) &&  ( leftMaxWallHt(structureArr , i ) > structureArr[i]  )  && ( rightMaxWallHt( structureArr , i ) > structureArr[i] ) )
		    	if   ( (j >= structureArr[i] ) &&  ( j < leftMaxWallHt(structureArr , i ) )  && ( j < rightMaxWallHt( structureArr , i )  ) )
	        		units++;
		    }
	   }
        return units;
	}
	static int leftMaxWallHt( int [] structureArr, int i ) 
	{
		int leftMax = 0;
		for (int j = 0 ; j < i; j++)
		{
			if ( leftMax < structureArr[j])
				leftMax = structureArr[j];
		}
		return leftMax;
	}
	static int rightMaxWallHt( int [] structureArr, int i ) 
	{
		int rightMax = 0;
		for (int j = i + 1 ; j < structureArr.length; j++)
		{
			if ( rightMax < structureArr[j])
				rightMax = structureArr[j];
		}
		return rightMax;		
	}	
} /**
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