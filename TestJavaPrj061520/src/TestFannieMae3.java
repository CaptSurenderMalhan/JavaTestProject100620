import java.util.Arrays;
public class TestFannieMae3 {
	public static void main(String[] args) {
		int[] nArr = { 0, 0, 1, 4, 0, 6, 0, 15, 0, 13, 0, 0,  0 }; // {1,0,3,-1,0,0,0,0,7,8,10,0,4,1};
		System.out.println(Arrays.toString(nArr));
		shiftZeroesToLeft(nArr);
		System.out.println(Arrays.toString(nArr));
	}
	static void shiftZeroesToLeft(int[] nArr) {
		int i = nArr.length - 1, j = i;
		//first, assign all non-zero elements to the end of the array
		while(i >= 0) {
			//skip zero elems 
			if(nArr[i] != 0) {
				//if arr[i] is non-zero, assign to arr[j] and then decrement j
				nArr[j] = nArr[i];
				j--;
			}
			i--;
		}
		//finally, assign zero to elems in the beginning of array
		while(j >= 0) {
			nArr[j--] = 0;
		}
	}
}
