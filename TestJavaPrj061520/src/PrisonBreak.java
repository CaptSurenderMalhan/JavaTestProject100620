import java.util.ArrayList;
import java.util.List;
public class PrisonBreak { // File Created 06/15/20 (got this info from file explorer file properties.)
	public static void main(String[] args) {
		List <Integer> h = new ArrayList();
		List <Integer> v = new ArrayList();
		h.add(2);
		h.add(3);
//		h.add(5);
//		h.add(6);
//		h.add(8);
//		h.add(10);
//		h.add(12);
		v.add(3);
		v.add(4);
		v.add(7);
		v.add(9);
		long res = area(h , v);
		System.out.println(res);
	}
	static long area( List <Integer> h, List <Integer> v ) // this works
	{ // 2, 5, 6, 7, 9, 10
		int h1 =1; int v1 = 1 ; int h1m = 1 ; int v1m = 1;
		for( int i=0; i < h.size() -1 ; i++)
		{ 
			if (h.get(i) + 1 == h.get(i+1))
			{
				h1 = h1 + 1;
			}
			else
			{
				if (h1 !=1)
					h1 = h1+1;
				if (h1 > h1m)
					h1m = h1;
				h1 = 1;
			}
		} // 3,6,7,8
		if (h1 ==1)
			h1 = h1+1;
		if (h1 > h1m)
			h1m = h1;		
		for( int i=0; i < v.size() -1 ; i++)
		{ 
			if (v.get(i) + 1 == v.get(i+1))
			{
				v1 = v1 + 1;
			}
			else
			{
				if (v1 !=1)
					v1 = v1+1;				
				if (v1 > v1m)
					v1m = v1;
				v1 = 1;
			}
		}	
		if (v1 ==1)
			v1 = v1+1;				
		if (v1 > v1m)
			v1m = v1;		
		return h1m * v1m;
	}
	static long area1( List <Integer> h, List <Integer> v )
	{ // 2, 5, 6, 7, 9, 10
		int h1 =1; int v1 = 1 ; int h1m = 1 ; int v1m = 1;
		for( int i=0; i < h.size()  ; i++)
		{ 
			if (i < h.size() -1)
			{
				if (h.get(i) + 1 == h.get(i+1))
				{
					h1 = h1 + 1;
				}
			}
			if (h.get(i) + 1 != h.get(i+1))
			{
				if (h1 !=1)
					h1 = h1+1;
				if (h1 > h1m)
					h1m = h1;
				h1 = 1;
			}
		} // 3,6,7,8
		for( int i=0; i < v.size()  ; i++)
		{ 
			if (i < v.size() -1)
			{
				if (v.get(i) + 1 == v.get(i+1))
				{
					v1 = v1 + 1;
				}
			}
			if (v.get(i) + 1 != v.get(i+1))
			{
				if (v1 !=1)
					v1 = v1+1;				
				if (v1 > v1m)
					v1m = v1;
				v1 = 1;
			}
		}	
		return h1m * v1m;
	}
}
