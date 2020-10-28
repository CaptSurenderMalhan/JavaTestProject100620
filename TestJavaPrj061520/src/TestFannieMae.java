import java.util.*;
public class TestFannieMae { 
	public static void main(String[] args) {
		int [] nArr1 = { 1, 2, 0, 0, 0 };  //{ 0, 0, 1, 4, 0, 6, 0, 15, 0, 13, 0, 0,  0 };
		shiftDigits2(nArr1);
		System.out.println(  Arrays.toString( nArr1));
		int [] nArr2 = { 0, 0, 1, 4, 0, 6, 0, 15, 0, 13, 0, 0,  0 };
		shiftDigits2(nArr2);
		System.out.println(  Arrays.toString( nArr2));		
		int [] nArr3 = { 1, 4, 0, 6, 0, 15, 0, 13, 0, 0,  0 };
		shiftDigits2(nArr3);
		System.out.println(  Arrays.toString( nArr3));			
//		int [] nArr2 = { 1, 4, 6, 15, 13, 7 };
//		System.out.println( "Min Dist = " +  findMinDis(nArr2) );
	}
	static void shiftDigits2(int [ ] nArr)
	{
		for (int i = (nArr.length - 1) ; i > 0 ; i--)
		{
			int j = 0;
			if (nArr[i] == 0 )
			{
				j = i;
				while ( (j < nArr.length) && (nArr[j] == 0) )
				{
					nArr[j] = nArr[j - 1];
					nArr[j - 1] = 0;
					j++;
				}
			}
		}
	}	

	static void shiftDigits(int [ ] nArr)
	{
		for (int i = 0; i < nArr.length ; i++)
		{
				if (nArr[i] == 0 )
					continue;
				else
				{
					shiftDigits(nArr, i);
					break;
				}
		}
	}
	static void shiftDigits(int [ ] nArr, int nStartPosn)
	{
		int nNonZeroDigitPosn = -1;
		for (int i = nStartPosn; i < nArr.length ; i++)
		{
			if (nNonZeroDigitPosn == -1)
			{
				if (nArr[i] != 0 )
					nNonZeroDigitPosn = i;
			}
			if (nArr[i] == 0 )
			{
				shiftDigits(nArr, nNonZeroDigitPosn, i);
				nArr[nNonZeroDigitPosn] = 0;
				nNonZeroDigitPosn++;
			}
		}
	}	
	static void shiftDigits(int [ ] nArr, int nNonZeroDigitPosn, int nZeroDigitPosn)
	{
		int nZeroPosn = nNonZeroDigitPosn;
		for (int i = nZeroDigitPosn; i > nNonZeroDigitPosn ; i--)
		{
			nArr[i] = nArr[i-1] ;
		}
	}	
	static int findMinDis( int [ ] nArr )  // Walter Marin Test Kim from Fannie Mae 10/15/20: THU
	{
		int nMin = Integer.MAX_VALUE;
		for (int i = 0; i < nArr.length ; i++)
		{
			for (int j = i+1; j < nArr.length ; j++)
			{
				int nTemp = Math.abs(nArr[i] - nArr[j]);
				if (nTemp< nMin)
					nMin = nTemp;
			}			
		}
		return nMin;
	}
}
