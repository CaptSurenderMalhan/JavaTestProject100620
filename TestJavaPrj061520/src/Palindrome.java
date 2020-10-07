import java.util.Arrays;
public class Palindrome {
	String sPalidromeSubStr = null;
	public static void main(String[] args) {
		Palindrome  c = new Palindrome();
		int[] intArr = {1, 3, 6, 4, 1, 2 } ;
		c.runTestSuite();
	}
	void runTestSuite()
	{
		test("abcecbb", false);  //  abcecbb ; abccbb, abba, abcba
		test("abccbb", false);
		test("abba", true);
		test("abcba", true);
		test("abcbab", false);
		
	}

	void test( String str , boolean bExpectedResult)
	{  //unitsOfWaterTrapped  // getUnitsOfWaterWalterMarin081920
		boolean bResult = isPalindrome( str );
		if ( bResult  == bExpectedResult )  //Arrays.toString(blocks)   // getArrayAsString
			System.out.println( "Test Passed: Input: " + str + ". Expected Result: " + bExpectedResult + ". Received Result: " + bResult);
		else
			System.out.println( "Test Failed: Input: " + str + ". Expected Result: " + bExpectedResult + ". Received Result: " + bResult);
	}
    public boolean isPalindrome(String str) {
		if ( (str == null ) || ( str.length() < 2 ) )
			return false;        	
    	int left = 0; int right = 0; int strLen =  str.length();
    	char [ ] charArr = str.toCharArray();
    	if ( ( strLen % 2 ) == 1) // aabaa  aabbaa  abcba
    	{
    		left = (strLen / 2) - 1;  //  aabaa  0 based.
    		right = left + 2;
    	}
    	else 
    	{
    		left = strLen / 2 - 1;  // aabbaa  abba  abcbab
    		right = left + 1;    		
    	}
    	//System.out.println();
    	for (int i = 0; i < str.length(); i++)  // abccbb
    	{ // left ptr moves left, right ptr moves right
    		if ( left >= 0 && right < str.length() && charArr[left] == charArr[right] )
    		{
    			left--;  right++;
    		}  
    		else
    			break;
    	} //  //  abcecbb ; abccbb, abba, abcba ---From API --  "hamburger".substring(4, 8) returns "urge"  see below
    	sPalidromeSubStr = str.substring( left + 1, right ); // "smiles".substring(1, 5) returns "mile"
    	if (str.length() == right - left - 1)
    		return true;
    	else
    		return false;
    }
}
/* public String substring(int beginIndex, int endIndex)  // indexes are 0 based.
The substring begins at the specified beginIndex and extends to the character at index endIndex - 1. 
Thus the length of the substring is endIndex-beginIndex.
Examples:    "hamburger".substring(4, 8) returns "urge"
"smiles".substring(1, 5) returns "mile"
Parameters:   beginIndex - the beginning index, inclusive.
endIndex - the ending index, exclusive.
*/