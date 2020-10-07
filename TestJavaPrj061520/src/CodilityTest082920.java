import java.util.ArrayList;
import java.util.HashSet;
public class CodilityTest082920 {
	public static void main(String[] args) {
		String s = String.valueOf(3);
		CodilityTest082920 c = new CodilityTest082920();
		int [] arr1 =  {4,2,2,4,2} ; int [] arr2 =  {2,3,2} ; int [] arr3 = {0,5,4,4,5,15} ; int [] arr4 = {4,4} ; int [] arr5 = {1, 2, 4, 3, 3, 4, 2, 3, 6, 5, 4, 5}; //
		// int result = c.solution(arr1);
		System.out.println(c.solution(arr1));
		System.out.println(c.solution(arr2));
		System.out.println(c.solution(arr3));
		System.out.println(c.solution(arr4));
		System.out.println(c.solution(arr5));
	}
    public int solution(int[] arr) {
		if ( (arr == null ) || ( arr.length == 0 ) )
			return 0;       
		if  ( arr.length == 2 ) 
			return 2;    
		int maxLen = 0;  int currentLength = 0; 		int prevNumAdded = 0 ; 
		HashSet hashSet = new HashSet();
		for ( int i = 0; i < arr.length; i++ )  
		{
			 if ( hashSet.add(arr[i]) == true )  
			 {
				 if (hashSet.size() <=2 )
				 {
					 currentLength++;
					 prevNumAdded = arr[i];
					 continue;		
				 }
				 else 
				 {
					 hashSet.clear();
					 hashSet.add(prevNumAdded);
					 hashSet.add(arr[i]);
					 prevNumAdded = arr[i];
					 if (maxLen < currentLength)
						 maxLen = currentLength ;	
					 currentLength = 2;
				 }
			 }	
			 else // Number Exists in Set.
			 {
				 prevNumAdded = arr[i];
				 currentLength++;
			 }
		}
		 if (maxLen < currentLength)
			 maxLen = currentLength ;
		return maxLen;
    }
}
