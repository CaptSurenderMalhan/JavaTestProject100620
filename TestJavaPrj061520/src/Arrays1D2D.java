import java.util.Arrays;
public class Arrays1D2D {  // Division, Remainder -- Modulus % : 10%3 = 1 ;  3%10 = 3 ; 10/3 = 3 ;
	public static void main(String[] args) {
		Arrays1D2D a = new Arrays1D2D();
		a.testLearn2DArrays();
	}
	void testLearn2DArrays() // in class Arrays1D2D, Eclipse Luna, TestJavaPrj061520
	{
		int [] [] my2DArray = {{1,2} ,{ 2, 3} , {4,5}, {5,6}, {6,7}, {7,8} }; 
		int [] arr1Da = my2DArray [1];  // { 2, 3 }
		int [] arr1Db = my2DArray [5];  // { 7, 8 }
		System.out.println("my2DArray.length = " + my2DArray.length + ". my2DArray[0].length = " + my2DArray[0].length ) ;
		System.out.println( my2DArray.length ); // Prints 6
		System.out.println( my2DArray[ 0 ].length ); // Prints 2
		System.out.println( my2DArray[ 5 ] [ 1 ] ); // Prints 8
		int [] [] myArray1 = new int [ 6 ] [ 2 ];
		System.out.println( myArray1.length ); // Prints 6
		System.out.println( myArray1[ 0 ].length ); // Prints 2		
		int [ ] [ ] twoDArray  = new int [ 3 ] [ 4 ]; // new int [Yaxis] [Xaxis]		// { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, {9, 10, 11, 12 } }
		// twoDArray = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, {9, 10, 11, 12 } }  -- we CANNOT do such assignment on next line. 
		int [ ] [ ] twoDArrayB  =  { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, {9, 10, 11, 12 } } ;  // Have to do this on same line.
		int [] arr1Dc = twoDArrayB [ 2 ];  //  {9, 10, 11, 12 }  - Zero Based Index -- 3rd Element.
		// Init 2D Array -- Fill in some Data
		int data = 1;
		System.out.println("twoDArray.length = " + twoDArray.length + ". twoDArray[0].length = " + twoDArray[0].length ) ;
		for (int i = 0; i < twoDArray.length; i++) 
		{
			for (int j = 0; j < twoDArray[0].length; j++)
				twoDArray[ i ] [ j ] = data++; 
		}
		print2DArray( my2DArray ) ;
		print2DArray( twoDArray ) ;
		print2DArray( twoDArrayB ) ;
	}
	public void print2DArray(int [ ] [ ] twoDArray)
	{
		if ( (twoDArray == null ) || ( twoDArray.length == 0 ) )
			return;
		for ( int i = 0; i < twoDArray.length; i++ ) // Print only 1st Dimension - Along X-Axix
			System.out.print( Arrays.toString( twoDArray[ i ] )); // Arrays.toString(oneDArray) -- Prints a 1D Array as eg. [2,3,4]
		System.out.println(); // go to next line.
		for (int i = 0; i < twoDArray.length; i++) // Print each Entry / Item / Element in 2D Array
		{
			for (int j = 0; j < twoDArray[0].length; j++)
				System.out.print( twoDArray[ i ] [ j ] + ", " ); // Arrays.toString(oneDArray) -- Prints a 1D Array as eg. [2,3,4]
			System.out.println(); // go to next line.
		}
	}
}
/*
my2DArray.length = 6. my2DArray[0].length = 2
6
2
8
6
2
twoDArray.length = 3. twoDArray[0].length = 4
[1, 2][2, 3][4, 5][5, 6][6, 7][7, 8]
1, 2, 
2, 3, 
4, 5, 
5, 6, 
6, 7, 
7, 8, 
[1, 2, 3, 4][5, 6, 7, 8][9, 10, 11, 12]
1, 2, 3, 4, 
5, 6, 7, 8, 
9, 10, 11, 12, 
[1, 2, 3, 4][5, 6, 7, 8][9, 10, 11, 12]
1, 2, 3, 4, 
5, 6, 7, 8, 
9, 10, 11, 12, 
--------------------------
NOTE -- if we have a Matrix like this -- 
1, 2, 3, 4, 
5, 6, 7, 8, 
9, 10, 11, 12, 
Then to Represent it we declare an array like this ---
		int [ ] [ ] twoDArray  = new int [ 3 ] [ 4 ]; // new int [Yaxis] [Xaxis]		// { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, {9, 10, 11, 12 } }
		int [ ] [ ] twoDArrayB  =  { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, {9, 10, 11, 12 } } ;  // Have to do this on same line.
 */
