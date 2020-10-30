import java.util.Arrays;
public class TestFannieMae1 { 
	public static void main(String[] args) {
		int [] nArr1 = { 0, 0, 1, 4, 0, 6, 0, 15, 0, 13, 0, 0, 7, 0 };
		shiftDigits(nArr1);
		System.out.println( Arrays.toString( nArr1));
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
}
