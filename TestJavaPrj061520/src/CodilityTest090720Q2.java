public class CodilityTest090720Q2 { // Same as Q2 on 08/23/20 // 08/24/20
	public static void main(String[] args) {
		CodilityTest090720Q2  c = new CodilityTest090720Q2();
		int[] intArr = {1, 3, 6, 4, 1, 2 } ;
		String str = "" ;
		String result = c.solution(0,2,0); //  6,1,1
		System.out.println(result);
	}
    public String solution(int numA, int numB, int numC) {  // A, B, C
    	if ( (numA<0) || (numA > 100) || (numB<0) || (numB > 100) ||(numC<0) || (numC > 100) || ( numA + numB + numC == 0))
    		return "";
        StringBuilder sb = new StringBuilder();
        while ((numA > 0) || (numB > 0) || (numC > 0))
        {
        	if ((numA>=numB) && (numA>=numC))
        	{
        		if ((sb.lastIndexOf("aa"))== (sb.length()-2))
        		{
    				if (( numB==0) && (numC==0))
    					numA = 0;
    				else 
    					numA = numA-2;
        		}
        		if (numA > 1)
        		{
        			sb.append("aa");
        			numA = numA-2;
        		}
        		else if (numA == 1)
        		{
        			sb.append("a");
        			numA--;
        		}
        		if (numB>=numC)
        		{
            		if (numB > 1)
            		{
            			sb.append("bb");
            			numB = numB-2;
            		}
            		else if (numB == 1)
            		{
            			sb.append("b");
            			numB--;
            		}        			
        		}
        		else
        		{
            		if (numC > 1)
            		{
            			sb.append("cc");
            			numC = numC-2;
            		}
            		else if (numC == 1)
            		{
            			sb.append("c");
            			numC--;
            		}             			
        		}
        	} // END - if ((A>B) && (A>C))
        	else if ((numB>=numA) && (numB>=numC))
        	{
        		if ((sb.lastIndexOf("bb"))== (sb.length()-2))
        		{
    				if (( numA==0) && (numC==0))
    					numB = 0;
    				else 
    					numB = numB-2;		
        		}
        		if (numB > 1)
        		{
        			sb.append("bb");
        			numB = numB-2;
        		}
        		else if (numB == 1)
        		{
        			sb.append("b");
        			numB--;
        		}
        		if (numA>=numC)
        		{
            		if (numA > 1)
            		{
            			sb.append("aa");
            			numA = numA-2;
            		}
            		else if (numA == 1)
            		{
            			sb.append("a");
            			numA--;
            		}        			
        		}
        		else
        		{
            		if (numC > 1)
            		{
            			sb.append("cc");
            			numC = numC-2;
            		}
            		else if (numC == 1)
            		{
            			sb.append("c");
            			numC--;
            		}             			
        		}
        	} // END - else if ((B>A) && (B>C))
        	else if ((numC>=numA) && (numC>=numB))
        	{
        		if ((sb.lastIndexOf("cc"))== (sb.length()-2))
        		{
    				if (( numA==0) && (numB==0))
    					numC = 0;
    				else 
    					numC = numC-2;
        		}
        		if (numC > 1)
        		{
        			sb.append("cc");
        			numC = numC-2;
        		}
        		else if (numC == 1)
        		{
        			sb.append("c");
        			numC--;
        		}
        		if (numA>=numB)
        		{
            		if (numA > 1)
            		{
            			sb.append("aa");
            			numA = numA-2;
            		}
            		else if (numA == 1)
            		{
            			sb.append("a");
            			numA--;
            		}        			
        		}
        		else
        		{
            		if (numB > 1)
            		{
            			sb.append("bb");
            			numB = numB-2;
            		}
            		else if (numB == 1)
            		{
            			sb.append("b");
            			numB--;
            		}             			
        		}
        	} // END - else if ((C>A) && (C>B))
        }
    	return sb.toString();
    }
    int findMax(int numA, int numB, int numC)
    {
    	 int max = 0;
         if (numA > numB)
         	max = numA;
         else
        	 max = numB;
         if (numC > max)
          	max = numC;
         return max;    
    }
}
