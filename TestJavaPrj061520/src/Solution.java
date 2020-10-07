import java.util.*;
public class Solution {
    public static void main(String[] args) {
    	boolean result = isPalindrome( 1221);
    	int [] [] game  ={ {0,1,1,0,0}, {0,1,0,0,1}, {0,0,0, 1,1 }, {0,0,1, 1,0 }};
    	for (int i = 0 ; i < game.length ; i++)
    	{
	    	System.out.println( "canWin( 1, game)" +  "  " +  canWin( 1, game[i]));
	    	System.out.println("canWin( 2, game)" +  "  " +  canWin( 2, game[i]));
	    	System.out.println("canWin( 3, game)" +  "  " +  canWin( 3, game[i]));
    	}
    }
	public static boolean isPalindrome(int x) {
        boolean result = false;
        String s1 = String.valueOf(x);
        StringBuffer sb = new StringBuffer(s1);
        StringBuffer sb2 = sb.reverse();
        return sb.equals(sb2);
    }
    public static int[] twoSum(int[] nums, int target) 
    {
        int [] result = new int [2];
        for (int i = 0 ; i < nums.length; i++ )    
        {
            for (int j = i+1 ; j < nums.length ; i++ )    
            {
            	if (nums[i] + nums[j] == target)
            	{
            		result[0] = nums[i] ;
            		result[1] = nums[j] ;
            		return result;
            	}
            }
        }
        return result;
    }
 // 7.40am
    public static boolean canWin(int leap, int[] game) {
        // Return true if you can win the game; otherwise, return false.
    	int n = game.length;  //  {0,0,0,1,1,0};   {0,0,0,1,1,1};  // canWin( 1, game) ; canWin( 2, game)
    	int i = 0;  int count1s = 0;
    	if  ((i == (n-1) ) || ( (i + leap) >= n ))
    		return true;
    	while ((i < n) && ( (i + count1s) < n))
    	{
	    	if (count1s > leap)
	    		return false;
	    	else if  ( ( (i + leap) < n ) && ( game[i+leap] == 0)) 
	    	{
	    		i = i + leap;
    	    	if  ((i == (n-1) ) || ( (i + leap) >= n ))
    	    		return true;  
	    	}    		
	    	else if ( game[i+1] == 0)
    		{
    			i++;
    	    	if  ((i == (n-1) ) || ( (i + leap) >= n ))
    	    		return true;  
    		}
    		else
    			count1s++;
    	}
    	System.out.println("Total 1s  --- " + count1s + "          " + "i --- " + i);
    	return false;
    }

    public static boolean canWin1(int leap, int[] game) {
        // Return true if you can win the game; otherwise, return false.
    	int n = game.length;  //  {0,0,0,1,1,0};   {0,0,0,1,1,1};
    	int i = 0;  int count1s = 0;
    	if  ((i == (n-1) ) || ( (i + leap) >= n ))
    		return true;
    	while ((i < n) && ( (i + count1s) < n))
    	{
    		if ( game[i+1] == 0)
    		{
    			i++;
    	    	if  ((i == (n-1) ) || ( (i + leap) >= n ))
    	    		return true;  
    		}
	    	else if  ( ( (i + leap) < n ) && ( game[i+leap] == 0)) 
	    	{
	    		i = i + leap;
    	    	if  ((i == (n-1) ) || ( (i + leap) >= n ))
    	    		return true;  
	    	}
    		else
    			count1s++;
    	}
    	return false;
    }
    public static void main1(String[] args) {
        Scanner scan = new Scanner(System.in);
        int q = scan.nextInt();
        while (q-- > 0) {
            int n = scan.nextInt();
            int leap = scan.nextInt();
            
            int[] game = new int[n];
            for (int i = 0; i < n; i++) {
                game[i] = scan.nextInt();
            }
            System.out.println( (canWin(leap, game)) ? "YES" : "NO" );
        }
        scan.close();
    }
}