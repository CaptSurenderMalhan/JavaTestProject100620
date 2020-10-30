import java.util.*;
public class TestFannieMae2 { 
	public static void main(String[] args) {
		int [] nArr1 = { 1, 2, 0, 0, 0 };  
		shiftDigits2(nArr1);
		System.out.println(  Arrays.toString( nArr1));
		int [] nArr2 = { 0, 0, 1, 4, 0, 6, 0, 15, 0, 13, 0, 0,  0 };
		shiftDigits2(nArr2);
		System.out.println(  Arrays.toString( nArr2));		
		int [] nArr3 = { 1, 4, 0, 6, 0, 15, 0, 13, 0, 0,  0 };
		shiftDigits2(nArr3);
		System.out.println(  Arrays.toString( nArr3));			
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
}
