package basic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_basic {
	// 변수 처리 
	static int T;
	static long Res;

	// union-find by rank
	static int[] parent;
	static int[] rank;
	
	public static void main(String[] args) throws Exception {
		// 파일 처리 
		FileInputStream fi = new FileInputStream(new File(Solution_basic.class.getResource("").getPath() + "basic"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for(int t=1; t<=T; t++){
			Res = 0;
			sb.setLength(0);
			sb.append("#").append(t).append(" ");
			
			// 경우의 수 구성하기 (순열)
			int[] arr = {1, 2, 3};		
//			order(arr, 0, arr.length);
			
			// Union-find 함수
			int N=10;
			rank   = new int[N];
			parent = new int[N];
			for(int i=0; i<N; i++){
				parent[i] = i;
				rank[i] = 0; // 높이, 순위 등 우선순위
			}
			
			// 이분 탐색 (오름차순으로 정렬된 수열에서 특정수를 찾는 경우)
			int[] arr2 = {1, 2, 3, 4, 5, 6, 7, 8};
			int[] arr3 = {1, 4, 3, 5, 9, 2, 7, 6};
			int[] arr4 = {8, 7, 6, 5, 4, 3, 2, 1};
			
//			System.out.println(binarySearch(arr2, 0, arr4.length, 8));
//			Arrays.sort(arr3, 0, 8);
//			System.out.println(Arrays.binarySearch(arr3, 0, 8, 9));
			
			
			// 최대공약수
			int a = 72;
			int b = 30;
			System.out.println(GCD(a, b));
			// 최소공배수 
			System.out.println(a*b/GCD(a, b));
			
			// 
			
			
			
			sb.append(Res);
			System.out.println(sb.toString());
		}		
	} // end main
	
	// 경우의 수 구성하기 (순열)
	static void order(int[] arr, int depth, int size){
		if(depth == size){
			for(int i=0; i<arr.length; i++){
				System.out.print(arr[i] + ",");
			}
			System.out.println();
			return;
		}
		
		for(int i=depth; i<size; i++){
			swap(arr, i, depth);
			order(arr, depth + 1, size);
			swap(arr, i, depth);
		}
	}
	static void swap(int[] arr, int x, int y){
		int temp = arr[x];
		arr[x] = arr[y];
		arr[y]= temp;
	}
	
	static int find(int a){
		if(parent[a] == a) return a;
		return find(parent[a]);
	}
	static void union(int a, int b){
		int mA = find(a);
		int mB = find(b);
		
		if(mA == mB) return;
		
		// parent[mB] = mA;
		// 항상 순위(높이)가 높은쪽으로 합친다.
		if(rank[mA] < rank[mB]){
			parent[mA] = mB;
		}else{
			parent[mB] = mA;
			
			// 순위(높이)가 같다면 합친 후 한단계 올린다. 
			if(rank[mA] == rank[mB]) rank[mA]++;
		}
	}
	
	
	// 이분 탐색
	static int binarySearch(int[] arr, int leftidx, int rigthidx, int key){
		int left  = leftidx;  // low
		int right = rigthidx - 1; // high
		int mid, midValue;

		while(left <= right){
//			mid = (right + left)/2;
			mid = (right + left) >>> 1;
			midValue = arr[mid];
			if(midValue == key) return mid;
			
			if(midValue > key){ // 오름 차순일 경우 (반대로 하면 내림 차순)
				right = mid - 1;
			}else{
				left = mid + 1;
			}
		}
		return -(left + 1);
	}
	
	
	// 최대공약수
	static int GCD(int a, int b){
		int temp;
		if(a < b){
			temp = a;
			a = b;
			b = temp;
		}
		// GCD(a, b) = GCD(b, r)
		while(b != 0){
			temp = a%b; // a를 b로 나눈 나머지
			a = b;
			b = temp;
		}
		return a;
	}
}