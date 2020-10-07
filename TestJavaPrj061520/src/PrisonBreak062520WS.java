import java.util.ArrayList;
import java.util.List;
public class PrisonBreak062520WS{  // William Shelton 06/25/20
	public static void main(String[] args) {
		int n = 6;
		int m = 6;
		//Sample 0
//		int n = 3;
//		int m = 3;
		//Sample 1
//		int n = 2;
//		int m = 2;
		//Sample 2
//		int n = 3;
//		int m = 2;
		List<Integer> h = new ArrayList<>(n);
		List<Integer> v = new ArrayList<>(m);
//		h.add(4);
//		v.add(2);
		//Sample 0
//		h.add(2);
//		v.add(2);
		//Sample 1
//		h.add(1);
//		v.add(2);
		//Sample 2
//		h.add(1);
//		h.add(2);
//		h.add(3);
//		v.add(1);
//		v.add(2);
		//Consecutive bars
//		h.add(2);
//		v.add(4);
//		v.add(5);
		//Non-consecutive bars
		h.add(1);
		h.add(2);
		h.add(4);
		h.add(5);
		h.add(6);
		v.add(1);
		v.add(2);
		v.add(4);
		v.add(5);
		v.add(6);
		int output = prison2(n, m, h, v);
		System.out.println(output);
	}
	public static int prison(int n, int m, List<Integer> h, List<Integer> v){
		return (h.size()+1)*(v.size()+1);
	}
	public static int prison2(int n, int m, List<Integer> h, List<Integer> v){
		int height = 1;
		int width = 1;
		int heightMax = 0;
		int widthMax = 0;
		for(int i = 0; i < h.size(); i++){
			if(i+1 == h.size()){
				height++;
				if(height > heightMax)
					heightMax = height;
			}else{
				if(h.get(i)+1 == h.get(i+1)){
					height++;
				}else{
					if(height > heightMax)
						heightMax = height;
					height = 1;
				}
			}
		}
		for(int i = 0; i < v.size(); i++){
			if(i+1 == v.size()){
				width++;
				if(width > widthMax)
					widthMax = width;
			}else{
				if(v.get(i)+1 == v.get(i+1)){
					width++;
				}else{
					if(width > widthMax)
						widthMax = width;
					width = 1;
				}
			}
		}
		return heightMax * widthMax;
	}
}