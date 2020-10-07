import java.util.Arrays;
public class PrisonBreak062520WM {
	//Second algorithm. Wrote three tests.
	public static void main(String[] args) {
		test1();
		test2();
		test3();
	}
	static void test1() {
		System.out.println("Test 1:");
		int n = 6;
		int m = 6;
		int h[] = {4};
		int v[] = {2};
		System.out.println("n = " + n);
		System.out.println("m = " + m);
		System.out.println("h = " + Arrays.toString(h));
		System.out.println("v = " + Arrays.toString(v));
		int area = prison(n,m,h,v);//2 * 2 = 4
		System.out.println("area = " + area);
	}
	static void test2() {
		System.out.println("Test 2:");
		int n = 6;
		int m = 6;
		int h[] = {2,4,5,6};
		int v[] = {1,2,3,5};
		System.out.println("n = " + n);
		System.out.println("m = " + m);
		System.out.println("h = " + Arrays.toString(h));
		System.out.println("v = " + Arrays.toString(v));
		int area = prison(n,m,h,v);//4 * 2 = 8
		System.out.println("area = " + area);
	}
	static void test3() {
		System.out.println("Test 3:");
		int n = 8;
		int m = 8;
		int h[] = {2,3,8};
		int v[] = {1,8};
		System.out.println("n = " + n);
		System.out.println("m = " + m);
		System.out.println("h = " + Arrays.toString(h));
		System.out.println("v = " + Arrays.toString(v));
		int area = prison(n,m,h,v);//3 * 2 = 6
		System.out.println("area = " + area);
	}
	static int prison(int n, int m, int[] h, int[] v) {
		//n -- total num of horizontal bars 
		//m -- total num of vertical bars
		//h[] -- horizontal bars to be removed
		//v[] -- vertical bars to be removed
		int x = getMaxRemovedBars(n, h);// max num of horizontal bars in close proximity to be removed
		int y = getMaxRemovedBars(m, v);// max num of vertical bars in close proximity to be removed
		int area = (x + 1) * (y + 1);
		return area;
	}
	static int getMaxRemovedBars(int totalBars, int[] specificBars) {
		int maxRemovedBars = 0;
		int removedBars = 0;
		for(int i = 0; i < specificBars.length; i++) {
			removedBars++;
			for(int j = i + 1; j < specificBars.length; j++) {
				if(specificBars[i] + 1 != specificBars[j])
					break;
				i++;
				removedBars++;
			}
			if(removedBars > maxRemovedBars)
				maxRemovedBars = removedBars;
			removedBars = 0;
		}
		return maxRemovedBars;
	}
}