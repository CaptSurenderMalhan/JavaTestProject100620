import java.util.ArrayList;
import java.util.List;
public class PrisonBreakWS {  // William Shelton
	public static void main(String[] args) {
//		int n = 6;
//		int m = 6;
//		int n = 3;
//		int m = 3;
//		int n = 2;
//		int m = 2;
		int n = 3;
		int m = 2;
		List<Integer> h = new ArrayList<>();
		List<Integer> v = new ArrayList<>();
//		h.add(4);
//		v.add(2);
//		h.add(2);
//		v.add(2);
//		h.add(1);
//		v.add(2);
		h.add(1);
		h.add(2);
		h.add(3);
		v.add(1);
		v.add(2);
		int output = prison(n, m, h, v);
		System.out.println(output);
	}
	public static int prison(int n, int m, List<Integer> h, List<Integer> v){
		return (h.size()+1)*(v.size()+1);
	}
}