import java.util.Arrays;
public class PrisonBreak_1stAlgo062520WM {
	public static void main(String[] args) {
		int n = 2;
		int m = 2;
		int x = 1;
		int[] h = new int[n];
		for(int i =0; i < h.length; i++) {
			h[i] = i + 1;
		}
		int y = 1;
		int[] v = new int[m];
		for(int i =0; i < v.length; i++) {
			v[i] = i + 1;
		}
		System.out.println("n = " + n);
		System.out.println("m = " + m);
		System.out.println("h = " + Arrays.toString(h));
		System.out.println("v = " + Arrays.toString(v));
		System.out.println("x = " + x);
		System.out.println("y = " + y);
		int area = prison(n,m,h,v,x,y);
		System.out.println(area);
	}
	static int prison(int n, int m, int[] h, int[] v, int x, int y) {
		int area;
		//get horizontal size for largest area
		int hSize;
		if(x > n)
			hSize = n + 1;
		else
			hSize = x + 1;
		//get vertical size for largest area
		int vSize;
		if(y > m)
			vSize = m + 1;
		else
			vSize = y + 1;
		area = hSize * vSize;
		return area;
	}

}
