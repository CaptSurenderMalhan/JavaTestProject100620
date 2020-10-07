
public class TestAlgo080820 {//Division, Remainder -- Modulus % : 10%3 = 1 ;  3%10 = 3 ; 10/3 = 3 ;
	static int [] intArr1 = {21, 4, 8, 2 };
	public static void main(String[] args) {
		System.out.println( intArr1 .toString() );
		System.out.println( bubbleSort4(intArr1) );
	}
	static int [] bubbleSort( int [] intArr)
	{ // Bubble Sort
		int temp , in , out ;
		for (int backward = intArr.length - 1 ; backward > 1 ; backward--)  // Outer Loop Backward.
		{
			for (int forward = 0 ; forward < backward ; forward++)
			{
				if ( intArr[forward]  > intArr[ forward +1] )
				{
					temp = intArr[ forward ] ;
					intArr[ forward ] = intArr[ forward  + 1 ];
					intArr[ forward  + 1 ] = temp;
				}
			}
		}
		return intArr;
	}
    static int [] bubbleSort4(int arr[])
    {
        for (int i = 0; i < arr.length - 1; i++)
            for (int j = 0; j < arr.length - 1; j++)
                if (arr[j] > arr[j+1])
                {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
        return arr;
    }
    static int [] bubbleSort2(int arr[])
    {
        for (int i = 0; i < arr.length - 1; i++)
            for (int j = 0; j < arr.length - i - 1; j++)
                if (arr[j] > arr[j+1])
                {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
        return arr;
    }
    static int [] bubbleSort3(int[] arr) 
    {  
        int temp = 0;  
         for(int i = 0; i < arr.length; i++)
         {  
                 for(int j=1; j < ( arr.length - i ); j++)
                 {  
                          if(arr[j-1] > arr[j])
                          {  
                                 temp = arr[j-1];  
                                 arr[j-1] = arr[j];  
                                 arr[j] = temp;  
                         }  
                 }  
         }  
         return arr;
    }      
}
